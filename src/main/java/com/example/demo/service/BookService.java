package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {
	
	private final BookRepository repository;
	
	public BookService(BookRepository repository) {
		this.repository = repository;
	}
	
	public Book addBook(Book book) {
		return repository.save(book);
	}
	
	public List<Book> getAllBooks(){
		return repository.findAll();
	}
	
	public Book getBook(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public void deleteBook(Long id) {
		repository.deleteById(id);
	}

}
