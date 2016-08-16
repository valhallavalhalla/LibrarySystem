package com.library.model;

import java.util.List;

import com.library.dao.DaoFactory;
import com.library.dao.jdbc.JdbcDaoFactory;
import com.library.model.entities.Book;

public class BookService {
	
	private static DaoFactory daoFactory;
	
	private static BookService instance;
	
	private BookService() {
		daoFactory = new JdbcDaoFactory();
	}
	
	public static BookService getInstance() {
		if (instance == null) {
			instance = new BookService();
		}
		return instance;
	}
	
	public List<Book> getAllBooks() {
		return daoFactory.getBookDao().findAll();
	}
	
}
