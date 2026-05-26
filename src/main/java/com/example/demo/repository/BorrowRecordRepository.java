package com.example.demo.repository;

import com.example.demo.entity.BorrowRecord;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
	List<BorrowRecord> findByUserId(Long userId);
}