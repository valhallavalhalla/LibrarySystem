package com.epam.project.dao;

public interface DaoFactory {

	BookDao getBookDao();
	OrderDao getOrderDao();
	UserDao getReaderDao();
	
}
