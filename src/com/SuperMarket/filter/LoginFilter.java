package com.SuperMarket.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(	filterName = "LoginFilter",
		 	urlPatterns = {"*.html"})
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		StringBuffer url = req.getRequestURL();
		
		String urlname = url.toString();
		
		if(!urlname.equals("http://localhost:8080/SuperMarket-vresion1/Login.html")) {
			if(!isLogin(req)) {
				resp.sendRedirect("http://localhost:8080/SuperMarket-vresion1/Login.html");
			}
			else {
				// pass the request along the filter chain
				chain.doFilter(request, response);
			}
		}
		else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}

	}
	
	private boolean isLogin(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null && cookies.length > 0) {
			String inputStaffName = "";
			String inputStaffid = "";
			for(Cookie  cookie : cookies) {
				if((cookie.getName( )).compareTo("inputStaffName") == 0) { 
					inputStaffName = cookie.getValue();
				}
				else if((cookie.getName( )).compareTo("inputStaffid") == 0) {
					inputStaffid = cookie.getValue();
				}
			}
			if(inputStaffName.equals("") || inputStaffid.equals("")) {
				//cookie中内容为空
				return false;
			}
			else {
				return true;
			}
		}
		else {
			return false;
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
