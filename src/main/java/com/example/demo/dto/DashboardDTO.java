package com.example.demo.dto;

public class DashboardDTO {

    private long totalBooks;
    private long availableBooks;
    private long borrowedBooks;
    private long totalUsers;
	public long getTotalBooks() {
		return totalBooks;
	}
	public void setTotalBooks(long totalBooks) {
		this.totalBooks = totalBooks;
	}
	public long getAvailableBooks() {
		return availableBooks;
	}
	public void setAvailableBooks(long availableBooks) {
		this.availableBooks = availableBooks;
	}
	public long getBorrowedBooks() {
		return borrowedBooks;
	}
	public void setBorrowedBooks(long borrowedBooks) {
		this.borrowedBooks = borrowedBooks;
	}
	public long getTotalUsers() {
		return totalUsers;
	}
	public void setTotalUsers(long totalUsers) {
		this.totalUsers = totalUsers;
	}
	@Override
	public String toString() {
		return "DashboardDTO [totalBooks=" + totalBooks + ", availableBooks=" + availableBooks + ", borrowedBooks="
				+ borrowedBooks + ", totalUsers=" + totalUsers + "]";
	}

    
}