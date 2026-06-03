package com.example.demo.service;

import com.example.demo.dto.AdminBorrowDTO;
import com.example.demo.dto.BorrowResponseDTO;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    // =========================
    // BORROW BOOK (SECURE)
    // =========================
    public BorrowRecord borrowBook(String email, Long bookId) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));

        if (!book.isAvailable()) {
            throw new RuntimeException("Book is already borrowed");
        }

        book.setAvailable(false);
        bookRepo.save(book);

        BorrowRecord record = new BorrowRecord();
        record.setUser(user);
        record.setBook(book);
        record.setBorrowDate(LocalDate.now());
        record.setDueDate(LocalDate.now().plusDays(7));
        record.setReturned(false);

        return borrowRepo.save(record);
    }

    // =========================
    // RETURN BOOK
    // =========================
    public BorrowRecord returnBook(String email, Long recordId) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        BorrowRecord record = borrowRepo.findById(recordId)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));

        // Optional safety check (important)
        if (!record.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Not allowed to return this book");
        }

        record.setReturned(true);
        record.setReturnDate(LocalDate.now());

        Book book = record.getBook();
        book.setAvailable(true);
        bookRepo.save(book);

        return borrowRepo.save(record);
    }

    // =========================
    // BORROW HISTORY (SECURE)
    // =========================
    public List<BorrowResponseDTO> getUserBorrowHistory(String email) {

        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<BorrowRecord> records = borrowRepo.findByUser(user);

        return records.stream()
                .map(this::mapToDTO)
                .toList();
    }

    // =========================
    // FINE CALCULATION
    // =========================
    public long calculateFine(BorrowRecord record) {

        if (!record.isReturned()) {
            return 0;
        }

        if (record.getReturnDate().isAfter(record.getDueDate())) {
            long daysLate = ChronoUnit.DAYS.between(
                    record.getDueDate(),
                    record.getReturnDate()
            );
            return daysLate * 10;
        }

        return 0;
    }

    private BorrowResponseDTO mapToDTO(BorrowRecord record) {

        BorrowResponseDTO dto = new BorrowResponseDTO();

        dto.setRecordId(record.getId());

        dto.setBookId(record.getBook().getId());

        dto.setBookTitle(record.getBook().getTitle());

        dto.setAuthor(record.getBook().getAuthor());

        dto.setBorrowDate(record.getBorrowDate());

        dto.setReturnDate(record.getReturnDate());

        dto.setReturned(record.isReturned());

        dto.setFine(calculateFine(record));

        return dto;
    }
    
    public List<AdminBorrowDTO> getAllBorrowRecords() {

        return borrowRepo.findAll()
                .stream()
                .map(record -> {

                    AdminBorrowDTO dto = new AdminBorrowDTO();

                    dto.setUserName(record.getUser().getName());
                    dto.setEmail(record.getUser().getEmail());

                    dto.setBookTitle(record.getBook().getTitle());
                    dto.setAuthor(record.getBook().getAuthor());

                    dto.setBorrowDate(record.getBorrowDate());
                    dto.setReturnDate(record.getReturnDate());

                    dto.setReturned(record.isReturned());

                    dto.setFine(calculateFine(record));

                    return dto;
                })
                .toList();
    }
}