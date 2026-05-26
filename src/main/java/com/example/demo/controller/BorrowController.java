package com.example.demo.controller;

import com.example.demo.entity.BorrowRecord;
import com.example.demo.service.BorrowService;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import com.example.demo.dto.BorrowResponseDTO;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    private final BorrowService service;

    public BorrowController(BorrowService service) {
        this.service = service;
    }

    @PostMapping
    public BorrowRecord borrowBook(@RequestParam Long userId,
                                  @RequestParam Long bookId) {
        return service.borrowBook(userId, bookId);
    }

    @PostMapping("/return")
    public BorrowRecord returnBook(@RequestParam Long recordId) {
        return service.returnBook(recordId);
    }
    
    @GetMapping("/{id}/borrowed-books")
    public List<BorrowResponseDTO> getBorrowedBooks(@PathVariable Long id) {
        return service.getUserBorrowHistory(id);
    }
}