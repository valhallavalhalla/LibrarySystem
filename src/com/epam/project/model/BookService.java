package com.epam.project.model;

import java.util.List;

import com.epam.project.dao.DaoFactory;
import com.epam.project.dao.jdbc.JdbcDaoFactory;
import com.epam.project.model.entities.Book;

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
