package com.epam.project.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.project.model.UserService;
import com.epam.project.model.entities.User;

public class LoginCommand implements Command {
	
	private UserService userService = UserService.getInstance();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		User authorizedReader = userService.authorize(new User(login, password));
		if (authorizedReader != null) {
			request.getSession().setAttribute("user", authorizedReader);
			if (authorizedReader.getIsAdmin()) {
				return CommandList.ADMIN.getCommand().execute(request, response);
			} else {
				return CommandList.PROFILE.getCommand().execute(request, response);
			}
		} else {
			return JspNames.LOGIN_PAGE;	
		}
	}

}
