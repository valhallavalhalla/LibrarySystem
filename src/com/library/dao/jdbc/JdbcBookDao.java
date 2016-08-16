package com.library.dao.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.library.dao.*;
import com.library.model.entities.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class JdbcBookDao implements BookDao {
	
	private static final Logger logger = LogManager.getLogger(JdbcBookDao.class);

	@Override
	public boolean insert(Book e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Book book) {
		String sql = "UPDATE books SET title = ?, author = ?, amount_available = ?"
				+ " WHERE id = ?";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(sql)) {
			query.setString(1, book.getTitle());
			query.setString(2, book.getAuthor());
			query.setInt(3, book.getAmountAvailable());
			query.setInt(4, book.getId());
			query.executeUpdate();
    		return true;
        } catch (SQLException e) {
        	logger.error(e.getMessage());
            throw new RuntimeException(e);
        } 
	}

	@Override 
	public boolean delete(Book e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean deleteById(int bookId) {
		String sql = "DELETE FROM books WHERE id = ?";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(sql);) {
			query.setInt(1, bookId);
            query.executeUpdate();
    		return true;
        } catch (SQLException e) {
        	logger.error(e.getMessage());
            throw new RuntimeException(e);
        } 
	}

	@Override
	public Book findById(int id) {
		String sql = "SELECT * FROM books WHERE id = ?";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(sql);) {
			query.setInt(1, id);
			ResultSet rs = query.executeQuery();
			Book book = new Book();
            while (rs.next()) {
            	book.setId(rs.getInt("id"));
            	book.setTitle(rs.getString("title"));
            	book.setAuthor(rs.getString("author"));
            	book.setAmountAvailable(rs.getInt("amount_available"));
            }
    		return book;
        } catch (SQLException e) {
        	logger.error(e.getMessage());
            throw new RuntimeException(e);
        } 
	}

	@Override
	public List<Book> findAll() {
		String sql = "SELECT * FROM books";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(sql);
				ResultSet rs = query.executeQuery();) {
            List<Book> books = new ArrayList<>();
            Book book = null;
            while (rs.next()) {
            	book = new Book();
            	book.setId(rs.getInt("id"));
            	book.setTitle(rs.getString("title"));
            	book.setAuthor(rs.getString("author"));
            	book.setAmountAvailable(rs.getInt("amount_available"));
                books.add(book);
            }
    		return books;
        } catch (SQLException e) {
        	logger.error(e.getMessage());
            throw new RuntimeException(e);
        } 
	}
	
	@Override
	public boolean decrementAvailableAmount(int bookId) {
		Book book = findById(bookId);
		book.setAmountAvailable(book.getAmountAvailable() - 1);
		return update(book);
	}
	
	@Override
	public boolean incrementAvailableAmount(int bookId) {
		Book book = findById(bookId);
		book.setAmountAvailable(book.getAmountAvailable() + 1);
		return update(book);
	}

}
