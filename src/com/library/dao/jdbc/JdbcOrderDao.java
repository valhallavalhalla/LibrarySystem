package com.library.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.library.dao.OrderDao;
import com.library.model.entities.Book;
import com.library.model.entities.Order;
import com.library.model.entities.User;

public class JdbcOrderDao implements OrderDao {
	
	private static final Logger logger = LogManager.getLogger(JdbcOrderDao.class);

	@Override
	public boolean insert(Order order) {
		String sql = "INSERT INTO orders (book_id, user_id) VALUES (?, ?)";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(sql)) {
			query.setInt(1, order.getBook().getId());
			query.setInt(2, order.getUser().getId());
    		return query.execute();
        } catch (SQLException e) {
        	logger.error(e.getMessage());
            throw new RuntimeException(e);
        } 
	}

	@Override
	public boolean update(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Order order) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean deleteById(int id) {
		String sql = "DELETE FROM orders WHERE id = ?";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(sql);) {
			query.setInt(1, id);
            return query.execute();
        } catch (SQLException e) {
        	logger.error(e.getMessage());
            throw new RuntimeException(e);
        } 
	}

	@Override
	public Order findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> findAll() {
		String sql = "SELECT orders.id, orders.book_id, orders.user_id, orders.is_processed,"
				+ "	users.id, users.login, users.password, users.is_admin,"
				+ " books.id, books.title, books.author, books.amount_available"
				+ " FROM orders"
				+ " JOIN books"
				+ " ON orders.book_id = books.id"
				+ " JOIN users"
				+ " ON orders.user_id = users.id";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(sql);) {
			List<Order> orders = new ArrayList<>();
			try (ResultSet rs = query.executeQuery();) {
				while (rs.next()) {
					Order order = new Order();
					Book book = new Book();
					User user = new User();
					
					book.setId(rs.getInt(9));
					book.setTitle(rs.getString(10));
					book.setAuthor(rs.getString(11));
					book.setAmountAvailable(rs.getInt(12));
					
					user.setId(rs.getInt(5));
					user.setLogin(rs.getString(6));
					user.setPassword(rs.getString(7));
					user.setIsAdmin(rs.getBoolean(8));
					
					order.setId(rs.getInt(1));
					order.setBook(book);
					order.setUser(user);
					order.setIsProcessed(rs.getBoolean(4));
					
	                orders.add(order);
	            }
			}            
    		return orders;
        } catch (SQLException e) {
        	logger.error(e.getMessage());
            throw new RuntimeException(e);
        } 
	}
	
	@Override
	public List<Order> getUserOrders(User userToGet) {
		String sql = "SELECT orders.id, orders.book_id, orders.user_id, orders.is_processed,"
				+ "	users.id, users.login, users.password, users.is_admin,"
				+ " books.id, books.title, books.author, books.amount_available"
				+ " FROM orders"
				+ " JOIN books"
				+ " ON orders.book_id = books.id"
				+ " JOIN users"
				+ " ON orders.user_id = users.id"
				+ " WHERE users.id = ?";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
			PreparedStatement query = cn.prepareStatement(sql);) {
			List<Order> userOrders = new ArrayList<>();
			query.setInt(1, userToGet.getId());
				try (ResultSet rs = query.executeQuery();) {
				while (rs.next()) {
					Order order = new Order();
					Book book = new Book();
					User user = new User();
					
					book.setId(rs.getInt(9));
					book.setTitle(rs.getString(10));
					book.setAuthor(rs.getString(11));
					book.setAmountAvailable(rs.getInt(12));
					
					user.setId(rs.getInt(5));
					user.setLogin(rs.getString(6));
					user.setPassword(rs.getString(7));
					user.setIsAdmin(rs.getBoolean(8));
					
					order.setId(rs.getInt(1));
					order.setBook(book);
					order.setUser(user);
					order.setIsProcessed(rs.getBoolean(4));
					
					userOrders.add(order);
				}
			}
	    	return userOrders;
		} catch (SQLException e) {
        	logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
	}

	@Override
	public void setOrderStatus(int orderId, boolean isProcessed) {
		String sql = "UPDATE orders SET is_processed = ? WHERE id = ?";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(sql)) {
			query.setBoolean(1, isProcessed);
			query.setInt(2, orderId);
			query.executeUpdate();
        } catch (SQLException e) {
        	logger.error(e.getMessage());
            throw new RuntimeException(e);
        } 
	}

	@Override
	public List<Order> getOrdersToProcess() {
		String sql = "SELECT orders.id, orders.book_id, orders.user_id, orders.is_processed,"
				+ "	users.id, users.login, users.password, users.is_admin,"
				+ " books.id, books.title, books.author, books.amount_available"
				+ " FROM orders"
				+ " JOIN books"
				+ " ON orders.book_id = books.id"
				+ " JOIN users"
				+ " ON orders.user_id = users.id"
				+ " WHERE orders.is_processed = false";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(sql);) {
			List<Order> ordersToProcess = new ArrayList<>();
			try (ResultSet rs = query.executeQuery();) {
				while (rs.next()) {
					Order order = new Order();
					Book book = new Book();
					User user = new User();
					
					book.setId(rs.getInt(9));
					book.setTitle(rs.getString(10));
					book.setAuthor(rs.getString(11));
					book.setAmountAvailable(rs.getInt(12));
					
					user.setId(rs.getInt(5));
					user.setLogin(rs.getString(6));
					user.setPassword(rs.getString(7));
					user.setIsAdmin(rs.getBoolean(8));
					
					order.setId(rs.getInt(1));
					order.setBook(book);
					order.setUser(user);
					order.setIsProcessed(rs.getBoolean(4));
	                ordersToProcess.add(order);
	            }
			} 
			return ordersToProcess;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
	}

}
