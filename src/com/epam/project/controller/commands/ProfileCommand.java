package com.epam.project.controller.commands;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.model.UserService;
import com.epam.project.model.entities.Order;
import com.epam.project.model.entities.User;
import com.epam.project.model.entities.Book;

public class ProfileCommand implements Command {
	
	private UserService userService = UserService.getInstance();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User currentUser = (User) request.getSession().getAttribute("user");
		Map<Order, Book> userBookOrders = userService.getUserBookOrders(currentUser);
		request.setAttribute("userBookOrders", userBookOrders);
		return JspNames.PROFILE_PAGE;
	}

}
