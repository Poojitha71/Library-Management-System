package com.example.demo.controller;

import com.example.demo.entity.BorrowRecord;
import com.example.demo.service.BorrowService;
import com.example.demo.dto.AdminBorrowDTO;
import com.example.demo.dto.BorrowResponseDTO;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    private final BorrowService service;

    public BorrowController(BorrowService service) {
        this.service = service;
    }

    // =========================
    // BORROW BOOK (LOGIN USER)
    // =========================
    @PostMapping("/{bookId}")
    public BorrowRecord borrowBook(
            @PathVariable Long bookId,
            Principal principal
    ) {
        return service.borrowBook(principal.getName(), bookId);
    }

    // =========================
    // RETURN BOOK
    // =========================
    @PostMapping("/return/{recordId}")
    public BorrowRecord returnBook(
            @PathVariable Long recordId,
            Principal principal
    ) {
        return service.returnBook(principal.getName(), recordId);
    }

    // =========================
    // GET BORROWED BOOKS (USER)
    // =========================
    @GetMapping("/borrowed-books")
    public List<BorrowResponseDTO> getBorrowedBooks(
            Principal principal
    ) {
        return service.getUserBorrowHistory(principal.getName());
    }
    
    @GetMapping("/all")
    public List<AdminBorrowDTO> getAllBorrowRecords() {
        return service.getAllBorrowRecords();
    }
}