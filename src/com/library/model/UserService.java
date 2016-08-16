package com.library.model;

import java.util.List;

import com.library.dao.DaoFactory;
import com.library.dao.OrderDao;
import com.library.dao.UserDao;
import com.library.dao.jdbc.JdbcDaoFactory;
import com.library.model.entities.*;

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
	
	public boolean registerUser(String login, String password) {
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setIsAdmin(false);
		UserDao userDao = daoFactory.getUserDao();
		return userDao.insert(user);
	}
	
	public User authorize(String login, String password) {
		UserDao userDao = daoFactory.getUserDao();
		return userDao.authorize(login, password);
	}
	
	public List<Order> getUserBookOrders(User user) {
		OrderDao orderDao = daoFactory.getOrderDao();
		return orderDao.getUserOrders(user);
	}

}
