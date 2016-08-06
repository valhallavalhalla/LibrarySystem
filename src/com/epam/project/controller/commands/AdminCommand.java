package com.epam.project.controller.commands;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.model.OrderService;
import com.epam.project.model.entities.Book;
import com.epam.project.model.entities.Order;
import com.epam.project.model.entities.User;

public class AdminCommand implements Command {

	private OrderService orderService = OrderService.getInstance();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<Order, Map<User, Book>> ordersToProcess = orderService.getOrdersToProcess();
		
				for (Map.Entry<Order, Map<User, Book>> entry : ordersToProcess.entrySet())
				{
					for (Map.Entry<User, Book> info : entry.getValue().entrySet())
					{
					    if (info.getKey().getLogin() == null) {
					    	throw new RuntimeException();
					    }
					}
				}

		
		request.setAttribute("ordersToProcess", ordersToProcess);
		return JspNames.ADMIN_PAGE;
		
	}

}
