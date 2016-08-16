package com.library.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.model.OrderService;

public class ConfirmOrderCommand implements Command {
	
	private OrderService orderService = OrderService.getInstance();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		int orderId = Integer.valueOf(request.getParameter("idOrder"));
		orderService.processOrder(orderId);
		return CommandList.ADMIN.getCommand().execute(request, response);
	}

}
