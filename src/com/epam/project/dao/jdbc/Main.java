package com.epam.project.dao.jdbc;

public class Main {

	public static void main(String[] args) {

		JdbcDaoFactory f = new JdbcDaoFactory();
		
		
		System.out.println(f.getReaderDao().findAll());

	}

}
