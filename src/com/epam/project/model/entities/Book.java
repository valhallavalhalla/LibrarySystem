package com.epam.project.model.entities;

/**
 * Class contains info about book.
 * 
 * @author Andrii Shchavinskyi
 */
public class Book {
	
	private int id;
	private String title;
	private String author;
	private int amountAvailable;
	
	public Book(int id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}
	
	public Book(int id, String title, String author, int amountAvailable) {
		this(id, title, author);
		this.amountAvailable = amountAvailable;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
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

	public int getAmountAvailable() {
		return amountAvailable;
	}

	public void setAmountAvailable(int amountAvailable) {
		this.amountAvailable = amountAvailable;
	}

}
