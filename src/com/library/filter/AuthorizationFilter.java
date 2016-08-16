package com.library.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.library.model.entities.User;


/**
 * Authorization filter.
 * Redirect user to login page from any other page,
 * if there is no user object in session.
 * 
 * @author Andrii Shchavinskyi
 */
public class AuthorizationFilter implements Filter {

	private static final String LOGIN = "login";
	private static final String USER_ATTRIBUTE = "user";
	private String REGISTRATION_PAGE;
	private String LOGIN_PAGE;
	private String INDEX_PAGE;

	@Override
	public void destroy() {
	
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		User sessionUser = (User) req.getSession().getAttribute(USER_ATTRIBUTE);
		
		if ( (req.getRequestURL().toString().contains(LOGIN_PAGE))
				|| (req.getRequestURL().toString().contains(REGISTRATION_PAGE))
				|| (req.getRequestURL().toString().contains(INDEX_PAGE))) {
			
			chain.doFilter(request, response);
			
		}else if(sessionUser != null){
			chain.doFilter(request, response);
		} else {
			chain.doFilter(request, response);
			res.sendRedirect(LOGIN);
		}

	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.REGISTRATION_PAGE = fConfig.getInitParameter("registration");
		this.LOGIN_PAGE = fConfig.getInitParameter("login");
		this.INDEX_PAGE = fConfig.getInitParameter("index");
	}

}