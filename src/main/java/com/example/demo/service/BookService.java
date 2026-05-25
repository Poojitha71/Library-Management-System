package com.example.demo.service;

import java.util.List;
import org.springframework.data.domain.*;


import org.springframework.stereotype.Service;

import com.example.demo.dto.BookRequestDTO;
import com.example.demo.dto.BookResponseDTO;
import com.example.demo.entity.Book;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.BookRepository;

@Service
public class BookService {
	
	private final BookRepository repository;
	
	public BookService(BookRepository repository) {
		this.repository = repository;
	}
	
	private BookResponseDTO mapToResponse(Book book) {
	    BookResponseDTO dto = new BookResponseDTO();
	    dto.setId(book.getId());
	    dto.setTitle(book.getTitle());
	    dto.setAuthor(book.getAuthor());
	    dto.setIsbn(book.getIsbn());
	    dto.setAvailable(book.isAvailable());
	    return dto;
	}
	
	public BookResponseDTO addBook(BookRequestDTO dto) {

	    Book book = new Book();
	    book.setTitle(dto.getTitle());
	    book.setAuthor(dto.getAuthor());
	    book.setIsbn(dto.getIsbn());
	    book.setAvailable(dto.isAvailable());

	    Book saved = repository.save(book);

	    return mapToResponse(saved);
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
	
	public Book updateBook(Long id, Book updatedBook) {

	    Book existing = repository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

	    existing.setTitle(updatedBook.getTitle());
	    existing.setAuthor(updatedBook.getAuthor());
	    existing.setIsbn(updatedBook.getIsbn());
	    existing.setAvailable(updatedBook.isAvailable());

	    return repository.save(existing);
	}
	
	public List<Book> searchByTitle(String title) {
	    return repository.findByTitleContainingIgnoreCase(title);
	}

	public List<Book> searchByAuthor(String author) {
	    return repository.findByAuthorContainingIgnoreCase(author);
	}
	
	public Page<Book> getBooks(int page, int size) {
	    return repository.findAll(PageRequest.of(page, size));
	}
}
