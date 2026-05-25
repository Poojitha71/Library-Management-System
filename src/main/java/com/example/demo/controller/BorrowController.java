package com.example.demo.controller;

import com.example.demo.entity.BorrowRecord;
import com.example.demo.service.BorrowService;
import org.springframework.web.bind.annotation.*;

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
}