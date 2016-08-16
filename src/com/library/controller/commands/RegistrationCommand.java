package com.library.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.model.UserService;
import com.library.model.entities.User;

public class RegistrationCommand implements Command {
	
	private UserService userService = UserService.getInstance();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		userService.registerUser(login, password);
		return JspNames.LOGIN_PAGE;
	}

}
