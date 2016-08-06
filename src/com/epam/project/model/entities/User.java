package com.epam.project.model.entities;

/**
 * Class contains info about library reader.
 * 
 * @author Andrii Shchavinskyi
 */
public class User {
	
	private int id;
	private String login;
	private String password;
	private boolean isAdmin;
	
	public User() {
		
	}
	
	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}
	
	public User(String login, String password, boolean isAdmin) {
		this(login, password);
		this.isAdmin = isAdmin;
	}
	
	public User(int id, String login, String password, boolean isAdmin) {
		this(login, password, isAdmin);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
