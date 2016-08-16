package com.library.dao;

public interface DaoFactory {

	BookDao getBookDao();
	OrderDao getOrderDao();
	UserDao getUserDao();
	
}
