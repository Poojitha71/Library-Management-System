package com.example.demo.dto;

import java.time.LocalDate;

public class AdminBorrowDTO {

    private String userName;
    private String email;

    private String bookTitle;
    private String author;

    private LocalDate borrowDate;
    private LocalDate returnDate;

    private boolean returned;

    private long fine;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDate getBorrowDate() {
		return borrowDate;
	}

	public void setBorrowDate(LocalDate borrowDate) {
		this.borrowDate = borrowDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public long getFine() {
		return fine;
	}

	public void setFine(long fine) {
		this.fine = fine;
	}

	@Override
	public String toString() {
		return "AdminBorrowDTO [userName=" + userName + ", email=" + email + ", bookTitle=" + bookTitle + ", author="
				+ author + ", borrowDate=" + borrowDate + ", returnDate=" + returnDate + ", returned=" + returned
				+ ", fine=" + fine + "]";
	}

    
}