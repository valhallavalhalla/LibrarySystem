package com.epam.project.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.project.dao.UserDao;
import com.epam.project.model.entities.Book;
import com.epam.project.model.entities.Order;
import com.epam.project.model.entities.User;

public class JdbcUserDao implements UserDao {

	@Override
	public boolean insert(User user) {
		try (Connection cn = JdbcDaoFactory.getConnection();
				Statement query = cn.createStatement()) {
			query.executeUpdate("INSERT INTO users"
				+ "(login, password)"
				+ "VALUES ('" + user.getLogin() + "', '" + user.getPassword() + "')");
    		return true;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } 
	}

	@Override
	public boolean update(User e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(User e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User findById(int id) {
		String sql = "SELECT * FROM users WHERE id = ?";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(sql);) {
			query.setInt(1, id);
			User user = null;
			try(ResultSet rs = query.executeQuery()) {
				 while (rs.next()) {
		                new User(rs.getInt(1),
		                		rs.getString(2),
		                        rs.getString(3),
		                        rs.getBoolean(4));
		            }
			}
    		return user;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } 
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT * FROM users";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(sql);
				ResultSet rs = query.executeQuery();) {
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                users.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getBoolean(4)));
            }
    		return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } 
	}
	
	@Override
	public User authorize(User user) {
		String sql = "SELECT * FROM users"
				+ " WHERE login = ? AND password = ?";
		
		String login = user.getLogin();
		String password = user.getPassword();
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(sql);) {
			query.setString(1, login);
			query.setString(2, password);
			User authorizedUser = null;
			try (ResultSet rs = query.executeQuery();) {
				while (rs.next()) {
	            	authorizedUser = new User();
	                authorizedUser.setId(rs.getInt("id"));
	                authorizedUser.setLogin(rs.getString("login"));
	                authorizedUser.setPassword(rs.getString("password"));
	                authorizedUser.setIsAdmin(rs.getBoolean("is_admin"));
	            }
			}
    		return authorizedUser;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } 
	}
	
	@Override
	public Map<Order, Book> getUserBookOrders(User user) {
		String sql = "SELECT orders.id, orders.book_id, orders.user_id, orders.is_processed,"
				+ " books.id, books.title, books.author"
				+ " FROM books "
				+ " JOIN orders "
				+ " ON books.id = orders.book_id "
				+ " WHERE orders.user_id = '" + user.getId() + "'";
		
		try (Connection cn = JdbcDaoFactory.getConnection();
				PreparedStatement query = cn.prepareStatement(sql);) {
			Map<Order, Book> userBookOrders = new HashMap<>();
			try (ResultSet rs = query.executeQuery();) {
				while (rs.next()) {
	                userBookOrders.put(
	                		new Order(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getBoolean(4)),
	                		new Book(rs.getInt(5), rs.getString(6), rs.getString(7)));
	            }	
			} 
			return userBookOrders;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
	}

}
