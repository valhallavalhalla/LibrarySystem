package com.library.controller.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Command interface for command pattern.
 * 
 * @author Andrii Shchavinsky
 */
public interface Command {
	
	String execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;

}
