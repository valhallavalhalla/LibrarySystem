package com.library.dao;

import com.library.model.entities.Book;

public interface BookDao extends GenericDao<Book>{
	
	boolean decrementAvailableAmount(int bookId);
	boolean incrementAvailableAmount(int bookId);

}
