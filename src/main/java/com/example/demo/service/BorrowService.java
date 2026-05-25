package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowService {

    private final UserRepository userRepo;
    private final BookRepository bookRepo;
    private final BorrowRecordRepository borrowRepo;

    public BorrowService(UserRepository userRepo,
                         BookRepository bookRepo,
                         BorrowRecordRepository borrowRepo) {
        this.userRepo = userRepo;
        this.bookRepo = bookRepo;
        this.borrowRepo = borrowRepo;
    }

    public BorrowRecord borrowBook(Long userId, Long bookId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is already borrowed");
        }

        book.setAvailable(false);

        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        record.setReturned(false);

        return borrowRepo.save(record);
    }

    public BorrowRecord returnBook(Long recordId) {

        BorrowRecord record = borrowRepo.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));

        record.setReturned(true);
        record.setReturnDate(LocalDate.now());

        Book book = record.getBook();
        book.setAvailable(true);

        return borrowRepo.save(record);
    }
}