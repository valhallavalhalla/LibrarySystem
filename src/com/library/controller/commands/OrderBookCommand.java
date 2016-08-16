package com.library.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.model.OrderService;
import com.library.model.entities.Book;
import com.library.model.entities.User;

public class OrderBookCommand implements Command {
	
	private OrderService orderService = OrderService.getInstance();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");
		int bookId = Integer.valueOf(request.getParameter("bookId"));
		
		if (orderService.orderBook(user, bookId)) {
			return CommandList.PROFILE.getCommand().execute(request, response);	
		}	
		return CommandList.PROFILE.getCommand().execute(request, response);
	}

}
