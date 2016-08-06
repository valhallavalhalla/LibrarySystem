package com.epam.project.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.epam.project.dao.BookDao;
import com.epam.project.dao.DaoFactory;
import com.epam.project.dao.OrderDao;
import com.epam.project.dao.UserDao;

/**
 * Class provides access to library database with JDBC
 * and methods to get entities DAO.
 * 
 * @author Andrii Shchavinskyi
 */
public class JdbcDaoFactory implements DaoFactory {

	private static DataSource ds;
	private static final Logger logger = LogManager.getLogger(JdbcDaoFactory.class);
	
    public JdbcDaoFactory() {
    	try {
    		InitialContext ic = new InitialContext();
			//ds = (DataSource) ic.lookup("jdbc/LibraryDB");
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/LibraryDB");
		} catch (NamingException e) {
			logger.error(e.getMessage());
			throw new RuntimeException(e);
		}
	}

    static Connection getConnection() throws SQLException{
    	return ds.getConnection();
    }

	@Override
	public BookDao getBookDao() {
		return new JdbcBookDao();
	}

	@Override
	public OrderDao getOrderDao() {
		return new JdbcOrderDao();
	}

	@Override
	public UserDao getReaderDao() {
		return new JdbcUserDao();
	}
	
}
