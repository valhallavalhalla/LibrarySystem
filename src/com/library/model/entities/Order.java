package com.library.model.entities;

/**
 * Class contains information about ordering book.
 * 
 * @author Andrii Shchavinskyi
 */
public class Order {
	
	private int id;
	private Book book;
	private User user;
	private boolean isProcessed;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean getIsProcessed() {
		return isProcessed;
	}

	public void setIsProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + id;
		result = prime * result + (isProcessed ? 1231 : 1237);
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (book == null) {
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (id != other.id)
			return false;
		if (isProcessed != other.isProcessed)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", book=" + book + ", user=" + user
				+ ", isProcessed=" + isProcessed + "]";
	}
	
}
