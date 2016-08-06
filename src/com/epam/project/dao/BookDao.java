package com.epam.project.dao;

import com.epam.project.model.entities.Book;

public interface BookDao extends GenericDao<Book>{
	
	boolean decrementAvailableAmount(int bookId);
	boolean incrementAvailableAmount(int bookId);

}
