package com.example.demo.dto;

public class BookResponseDTO {

    private Long id;
    private String title;
    private String author;
    private String isbn;
    private boolean available;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	@Override
	public String toString() {
		return "BookResponseDTO [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn
				+ ", available=" + available + "]";
	}
    
    
}