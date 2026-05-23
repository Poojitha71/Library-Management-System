package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
@CrossOrigin
public class BookController {

	private final BookService bookService;
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	@PostMapping("/add/book")
	public Book addBook(@Valid @RequestBody Book book) {
		return bookService.addBook(book);
	}
	
	@GetMapping("/get/books")
	public List<Book> getAllBooks(){
		return bookService.getAllBooks();
	}
	
	@GetMapping("/get/book/{id}")
	public Book getBook(Long id) {
		return bookService.getBook(id);
	}
	
	@DeleteMapping("/delete/book/{id}")
	public void deleteBook(Long id) {
		bookService.deleteBook(id);
	}
}
