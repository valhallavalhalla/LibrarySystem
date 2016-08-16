package com.library.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.library.dao.UserDao;
import com.library.model.entities.Book;
import com.library.model.entities.Order;
import com.library.model.entities.User;

public class JdbcUserDao implements UserDao {
	
	private static final Logger logger = LogManager.getLogger(JdbcUserDao.class);

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
					 	user = new User();
		            	user.setId(rs.getInt("id"));
		            	user.setLogin(rs.getString("login"));
		            	user.setPassword(rs.getString("password"));
		            	user.setIsAdmin(rs.getBoolean("is_admin"));
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
            	User user = new User();
            	user.setId(rs.getInt("id"));
            	user.setLogin(rs.getString("login"));
            	user.setPassword(rs.getString("password"));
            	user.setIsAdmin(rs.getBoolean("is_admin"));
                users.add(user);
            }
    		return users;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } 
	}
	
	@Override
	public User authorize(String login, String password) {
		String sql = "SELECT * FROM users"
				+ " WHERE login = ? AND password = ?";
		
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
        	logger.error(e.getMessage(), e);
            throw new RuntimeException(e);
        } 
	}

}
