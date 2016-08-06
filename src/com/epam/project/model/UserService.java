package com.epam.project.model;

import java.util.List;
import java.util.Map;

import com.epam.project.dao.DaoFactory;
import com.epam.project.dao.UserDao;
import com.epam.project.dao.jdbc.JdbcDaoFactory;
import com.epam.project.model.entities.Book;
import com.epam.project.model.entities.Order;
import com.epam.project.model.entities.User;

public class UserService {
	
	private static DaoFactory daoFactory;
	private static UserService instance;
	
	
	public UserService() {
		daoFactory = new JdbcDaoFactory();
	}
	
	public static UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}
	
	public boolean registerReader(User user) {
		UserDao readerDao = daoFactory.getReaderDao();
		return readerDao.insert(user);
	}
	
	public User authorize(User user) {
		UserDao readerDao = daoFactory.getReaderDao();
		return readerDao.authorize(user);
	}
	
	public Map<Order, Book> getUserBookOrders(User user) {
		UserDao userDao = daoFactory.getReaderDao();
		return userDao.getUserBookOrders(user);
	}

}
