package com.epam.project.model;

import java.util.List;
import java.util.Map;

import com.epam.project.dao.DaoFactory;
import com.epam.project.dao.jdbc.JdbcDaoFactory;
import com.epam.project.model.entities.Book;
import com.epam.project.model.entities.Order;
import com.epam.project.model.entities.User;

public class OrderService {
	
	private static DaoFactory daoFactory;
	private static OrderService instance;
	
	public OrderService() {
		daoFactory = new JdbcDaoFactory();
	}
	
	public static OrderService getInstance() {
		if (instance == null) {
			instance = new OrderService();
		}
		return instance;
	}
	
	public List<Order> getUserOrders(User user) {
		return daoFactory.getOrderDao().getUserOrders(user);
	}
	
	public boolean orderBook(User user, int bookId) {
		Book book = daoFactory.getBookDao().findById(bookId);
		if (book.getAmountAvailable() <= 0) {
			return false;
		}
		daoFactory.getOrderDao().insert(new Order(bookId, user.getId(), false));
		daoFactory.getBookDao().decrementAvailableAmount(bookId);
		return true;
	}
	
	public void returnBook(User user, int bookId, int orderId) {
		daoFactory.getOrderDao().deleteById(orderId);
		daoFactory.getBookDao().incrementAvailableAmount(bookId);
	}

	public void processOrder(int orderId) {
		daoFactory.getOrderDao().serOrderStatus(orderId, true);
	}
	
	public boolean deleteById(int idOrder) {
		return daoFactory.getOrderDao().deleteById(idOrder);
	}

	public Map<Order, Map<User, Book>> getOrdersToProcess() {
		return daoFactory.getOrderDao().getOrdersToProcess();
	}
	
	public void refuseOrder(int orderId, int bookId) {
		daoFactory.getBookDao().incrementAvailableAmount(bookId);
		daoFactory.getOrderDao().deleteById(orderId);
	}
	
}
