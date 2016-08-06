package com.epam.project.model.entities;

/**
 * Class contains ordering information of book.
 * 
 * @author Andrii Shchavinskyi
 */
public class Order {
	
	private int id;
	private int bookId;
	private int userId;
	private boolean isProcessed;
	
	public Order(int bookId, int userId) {
		this.bookId = bookId;
		this.userId = userId;
	}
	
	public Order(int bookId, int userId, boolean isProcessed) {
		this(bookId, userId);
		this.isProcessed = isProcessed;
	}
	
	public Order(int id, int bookId, int userId, boolean isProcessed) {
		this(bookId, userId, isProcessed);
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int readerId) {
		this.userId = readerId;
	}

	public boolean getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

}
