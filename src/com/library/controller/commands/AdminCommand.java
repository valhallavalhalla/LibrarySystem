package com.library.controller.commands;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.model.OrderService;
import com.library.model.entities.Book;
import com.library.model.entities.Order;
import com.library.model.entities.User;

public class AdminCommand implements Command {

	private OrderService orderService = OrderService.getInstance();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Order> ordersToProcess = orderService.getOrdersToProcess();
		request.setAttribute("ordersToProcess", ordersToProcess);
		return JspNames.ADMIN_PAGE;
		
	}

}
