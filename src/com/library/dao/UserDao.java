package com.library.dao;

import java.util.List;
import java.util.Map;

import com.library.model.entities.Book;
import com.library.model.entities.Order;
import com.library.model.entities.User;

public interface UserDao extends GenericDao<User> {
	
	User authorize(String login, String password);

}
