package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DashboardDTO;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.UserRepository;

@Service
public class DashboardService {

    private final BookRepository bookRepo;
    private final UserRepository userRepo;

    public DashboardService(
            BookRepository bookRepo,
            UserRepository userRepo) {

        this.bookRepo = bookRepo;
        this.userRepo = userRepo;
    }

    public DashboardDTO getDashboardStats() {

        DashboardDTO dto = new DashboardDTO();

        dto.setTotalBooks(bookRepo.count());

        dto.setAvailableBooks(
                bookRepo.countByAvailableTrue());

        dto.setBorrowedBooks(
                bookRepo.countByAvailableFalse());

        dto.setTotalUsers(userRepo.count());

        return dto;
    }
}
