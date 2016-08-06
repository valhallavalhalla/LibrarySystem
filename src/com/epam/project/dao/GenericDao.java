package com.epam.project.dao;

import java.util.List;

/**
 * Generic interface for DAO pattern.
 * 
 * @author Andrii Shchavinskyi
 * @param <E> generic for type of used class
 */
interface GenericDao<E> {
	
     boolean insert(E e);
     boolean update(E e);
     boolean delete(E e);
     boolean deleteById(int id);
     E findById(int id);
     List<E> findAll();

}
