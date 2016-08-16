package com.library.dao;

import java.util.List;
import java.util.Map;

import com.library.model.entities.Book;
import com.library.model.entities.Order;
import com.library.model.entities.User;

public interface OrderDao extends GenericDao<Order>{

	List<Order> getUserOrders(User user);

	void setOrderStatus(int orderId, boolean isProcessed);

	List<Order> getOrdersToProcess();
	
}
