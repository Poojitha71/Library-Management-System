package com.example.demo.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Book;

public interface BookRepository extends JpaRepository<Book,Long> {
	List<Book> findByTitleContainingIgnoreCase(String title);
	List<Book> findByAuthorContainingIgnoreCase(String author);
	long countByAvailableTrue();
	long countByAvailableFalse();
}
