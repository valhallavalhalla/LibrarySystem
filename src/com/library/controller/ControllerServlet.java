package com.library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.controller.commands.Command;
import com.library.controller.commands.CommandList;

/**
 * Main servlet. Represents a controller part of MVC pattern.
 * 
 * @author Andrii Shchavinskyi
 */
@WebServlet
public class ControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter("command");
		Command command = CommandList.valueOf(commandName).getCommand();
		String redirectPage = command.execute(request, response);
		request.getRequestDispatcher(redirectPage).forward(request, response);
		//response.sendRedirect(request.getContextPath() + "/" + redirectPage);
	}

}
