package com.epam.project.controller.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.project.model.BookService;
import com.epam.project.model.entities.Book;

public class BookCatalogCommand implements Command {

	private BookService bookService = BookService.getInstance();
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<Book> books = bookService.getAllBooks();
		request.setAttribute("books", books);
		return JspNames.CATALOG_PAGE;
	}
}
