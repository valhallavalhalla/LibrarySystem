package com.epam.project.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.epam.project.model.entities.User;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */

//@WebFilter
public class AuthenticationFilter implements Filter {

	private static final String LOGIN = "login";
	private static final String USER_ATTRIBUTE = "user";
	private String REGISTRATION_PAGE;
	private String LOGIN_PAGE;
	private String INDEX_PAGE;

	public AuthenticationFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	@Override
	public void destroy() {
	
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
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

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.REGISTRATION_PAGE = fConfig.getInitParameter("registration");
		this.LOGIN_PAGE = fConfig.getInitParameter("login");
		this.INDEX_PAGE = fConfig.getInitParameter("index");
	}

}