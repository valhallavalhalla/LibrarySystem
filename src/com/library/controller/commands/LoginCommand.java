package com.library.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.model.UserService;
import com.library.model.entities.User;

public class LoginCommand implements Command {
	
	private UserService userService = UserService.getInstance();

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		User authorizedUser = userService.authorize(login, password);
		if (authorizedUser != null) {
			request.getSession().setAttribute("user", authorizedUser);
			if (authorizedUser.getIsAdmin()) {
				return CommandList.ADMIN.getCommand().execute(request, response);
			} else {
				return CommandList.PROFILE.getCommand().execute(request, response);
			}
		} else {
			return JspNames.LOGIN_PAGE;	
		}
	}

}
