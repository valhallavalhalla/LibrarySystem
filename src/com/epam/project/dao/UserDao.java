package com.epam.project.dao;

import java.util.Map;

import com.epam.project.model.entities.Order;
import com.epam.project.model.entities.User;
import com.epam.project.model.entities.Book;

public interface UserDao extends GenericDao<User> {
	
	User authorize(User reader);
	
	Map<Order, Book> getUserBookOrders(User user);

}
