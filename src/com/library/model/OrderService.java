package com.library.model;

import java.util.List;

import com.library.dao.DaoFactory;
import com.library.dao.jdbc.JdbcDaoFactory;
import com.library.model.entities.Book;
import com.library.model.entities.Order;
import com.library.model.entities.User;

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
		if (daoFactory.getBookDao().findById(bookId).getAmountAvailable() <= 0) {
			return false;
		}
		Order order = new Order();
		Book book = daoFactory.getBookDao().findById(bookId);
		order.setBook(book);
		order.setUser(user);
		order.setIsProcessed(false);
		daoFactory.getOrderDao().insert(order);
		daoFactory.getBookDao().decrementAvailableAmount(book.getId());
		return true;
	}
	
	public void returnBook(User user, int bookId, int orderId) {
		daoFactory.getOrderDao().deleteById(orderId);
		daoFactory.getBookDao().incrementAvailableAmount(bookId);
	}

	public void processOrder(int orderId) {
		daoFactory.getOrderDao().setOrderStatus(orderId, true);
	}
	
	public boolean deleteById(int idOrder) {
		return daoFactory.getOrderDao().deleteById(idOrder);
	}

	public List<Order> getOrdersToProcess() {
		return daoFactory.getOrderDao().getOrdersToProcess();
	}
	
	public void refuseOrder(int orderId, int bookId) {
		daoFactory.getBookDao().incrementAvailableAmount(bookId);
		daoFactory.getOrderDao().deleteById(orderId);
	}
	
}
