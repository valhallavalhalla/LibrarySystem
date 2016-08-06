package com.epam.project.dao;

import java.util.List;
import java.util.Map;

import com.epam.project.model.entities.Book;
import com.epam.project.model.entities.Order;
import com.epam.project.model.entities.User;

public interface OrderDao extends GenericDao<Order>{

	List<Order> getUserOrders(User user);

	void serOrderStatus(int orderId, boolean isProcessed);

	Map<Order, Map<User, Book>> getOrdersToProcess();
	
}
