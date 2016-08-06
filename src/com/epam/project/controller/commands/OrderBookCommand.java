package com.epam.project.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.project.model.OrderService;
import com.epam.project.model.entities.User;

public class OrderBookCommand implements Command {
	
	private OrderService orderService = OrderService.getInstance();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession httpSession = request.getSession();
		User reader = (User) httpSession.getAttribute("user");
		int bookId = Integer.valueOf(request.getParameter("idBook"));
		if (orderService.orderBook(reader, bookId)) {
			return CommandList.PROFILE.getCommand().execute(request, response);	
		}	
		return CommandList.PROFILE.getCommand().execute(request, response);
	}

}
