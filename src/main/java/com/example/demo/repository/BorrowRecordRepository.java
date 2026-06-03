package com.example.demo.repository;

import com.example.demo.entity.BorrowRecord;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    List<BorrowRecord> findByUser(User user);
    List<BorrowRecord> findAll();
}