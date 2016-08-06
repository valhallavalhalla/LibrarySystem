package com.epam.project.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.model.UserService;
import com.epam.project.model.entities.User;

public class RegistrationCommand implements Command {
	
	private UserService readerService = UserService.getInstance();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		readerService.registerReader(new User(login, password));
		return JspNames.LOGIN_PAGE;
	}

}
