package com.epam.project.dao.jdbc;

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

import com.epam.project.dao.OrderDao;
import com.epam.project.model.entities.Book;
import com.epam.project.model.entities.Order;
import com.epam.project.model.entities.User;

public class JdbcOrderDao implements OrderDao {
	
	private static final Logger logger = LogManager.getLogger(JdbcDaoFactory.class);

	@Override
	public boolean insert(Order order) {
		String sql = "INSERT INTO orders (book_id, user_id) VALUES (?, ?)";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(sql)) {
			query.setInt(1, order.getBookId());
			query.setInt(2, order.getUserId());
    		return query.execute();
        } catch (SQLException e) {
        	logger.error(e.getMessage());
            throw new RuntimeException(e);
        } 
	}

	@Override
	public boolean update(Order e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Order e) {
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
		String sql = "SELECT * FROM orders";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(sql);) {
			List<Order> order = new ArrayList<>();
			try (ResultSet rs = query.executeQuery();) {
				while (rs.next()) {
	                order.add(new Order(rs.getInt(1),
	                        rs.getInt(2),
	                        rs.getInt(3),
	                        rs.getBoolean(4)));
	            }
			}            
    		return order;
        } catch (SQLException e) {
        	logger.error(e.getMessage());
            throw new RuntimeException(e);
        } 
	}
	
	@Override
	public List<Order> getUserOrders(User user) {
		String sql = "SELECT * FROM orders WHERE reader_id = ?";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
			PreparedStatement query = cn.prepareStatement(sql);) {
			List<Order> readerOrders = new ArrayList<>();
			query.setInt(1, user.getId());
				try (ResultSet rs = query.executeQuery();) {
				while (rs.next()) {
					readerOrders.add(new Order(rs.getInt(1),
							rs.getInt(2),
							rs.getInt(3),
							rs.getBoolean(4)));
				}
			}
	    	return readerOrders;
		} catch (SQLException e) {
        	logger.error(e.getMessage());
            throw new RuntimeException(e);
        }
	}

	@Override
	public void serOrderStatus(int orderId, boolean isProcessed) {
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
	public Map<Order, Map<User, Book>> getOrdersToProcess() {
		String sql = "SELECT orders.id, orders.book_id, orders.user_id, orders.is_processed,"
				+ "	users.id, users.login, users.password, users.is_admin,"
				+ " books.id, books.title, books.author"
				+ " FROM orders"
				+ " JOIN books"
				+ " ON orders.book_id = books.id"
				+ " JOIN users"
				+ " ON orders.user_id = users.id"
				+ " WHERE orders.is_processed = false";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(sql);) {
			Map<Order, Map<User, Book>> ordersToProcess = new HashMap<>();
			try (ResultSet rs = query.executeQuery();) {
				while (rs.next()) {
					Map<User, Book> additionalInfoMap = new HashMap<>();
					additionalInfoMap.put(
							new User(rs.getInt(5), rs.getString(6),	rs.getString(7),rs.getBoolean(8)),
            				new Book(rs.getInt(9), rs.getString(10), rs.getString(11)));
	                ordersToProcess.put(
	                		new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4)),
	                		additionalInfoMap);
	            }	
			} 
			return ordersToProcess;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
	}

}
