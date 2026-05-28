package com.example.demo.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.BookRequestDTO;
import com.example.demo.dto.BookResponseDTO;
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
	
	@GetMapping("/test")
    public String test() {
        return "Protected API Working";
    }
	@PostMapping("/add/book")
	public BookResponseDTO addBook(@Valid @RequestBody BookRequestDTO dto) {
	    return bookService.addBook(dto);
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
	
	@PutMapping("/update/{id}")
	public Book updateBook(@PathVariable Long id,
	                       @Valid @RequestBody Book book) {
	    return bookService.updateBook(id, book);
	}
	
	@GetMapping("/search/title")
	public List<Book> searchByTitle(@RequestParam String title) {
	    return bookService.searchByTitle(title);
	}

	@GetMapping("/search/author")
	public List<Book> searchByAuthor(@RequestParam String author) {
	    return bookService.searchByAuthor(author);
	}
	
	@GetMapping("/page")
	public Page<Book> getBooks(@RequestParam int page,
	                           @RequestParam int size) {
	    return bookService.getBooks(page, size);
	}
}
