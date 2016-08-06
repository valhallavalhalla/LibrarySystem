package com.epam.project.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.controller.commands.Command;
import com.epam.project.controller.commands.CommandList;

/**
 * Servlet implementation class Controller
 */
@WebServlet
public class ControllerServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String commandName = request.getParameter("command");
		Command command = CommandList.valueOf(commandName).getCommand();
		String goTo = command.execute(request, response);
		request.getRequestDispatcher(goTo).forward(request, response);
	}

}
