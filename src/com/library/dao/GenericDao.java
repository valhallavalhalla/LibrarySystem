package com.library.dao;

import java.util.List;

/**
 * Generic interface for DAO pattern.
 * 
 * @author Andrii Shchavinskyi
 * @param <E> generic for type for class of used DAO.
 */
interface GenericDao<E> {
	
     boolean insert(E e);
     boolean update(E e);
     boolean delete(E e);
     boolean deleteById(int id);
     E findById(int id);
     List<E> findAll();

}
