package com.library.controller.commands;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.model.UserService;
import com.library.model.entities.Book;
import com.library.model.entities.Order;
import com.library.model.entities.User;

public class ProfileCommand implements Command {
	
	private UserService userService = UserService.getInstance();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User currentUser = (User) request.getSession().getAttribute("user");
		List<Order> userOrders = userService.getUserBookOrders(currentUser);
		request.setAttribute("userOrders", userOrders);
		return JspNames.PROFILE_PAGE;
	}

}
