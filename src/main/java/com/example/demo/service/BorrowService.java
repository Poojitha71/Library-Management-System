package com.example.demo.service;

import com.example.demo.dto.BorrowResponseDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
        record.setDueDate(LocalDate.now().plusDays(7));
        record.setReturned(false);

        return borrowRepo.save(record);
    }
    
    public long calculateFine(BorrowRecord record) {

        if (!record.isReturned()) {
            return 0; // not returned yet
        }

        if (record.getReturnDate().isAfter(record.getDueDate())) {
            long daysLate = ChronoUnit.DAYS.between(
                    record.getDueDate(),
                    record.getReturnDate()
            );
            return daysLate * 10; // ₹10 per day
        }

        return 0;
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
    
     public List<BorrowResponseDTO> getUserBorrowHistory(Long userId) {

        if (!userRepo.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }

        List<BorrowRecord> records = borrowRepo.findByUserId(userId);

        return records.stream().map(this::mapToDTO).toList();
    }
    
    private BorrowResponseDTO mapToDTO(BorrowRecord record) {

        BorrowResponseDTO dto = new BorrowResponseDTO();

        dto.setBookTitle(record.getBook().getTitle());
        dto.setAuthor(record.getBook().getAuthor());
        dto.setBorrowDate(record.getBorrowDate());
        dto.setReturnDate(record.getReturnDate());
        dto.setReturned(record.isReturned());
        dto.setFine(calculateFine(record));

        return dto;
    }

}