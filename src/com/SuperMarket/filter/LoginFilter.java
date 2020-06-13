package com.SuperMarket.filter;

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
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(	filterName = "LoginFilter",
		 	urlPatterns = "*.html", dispatcherTypes = {}
			
		)
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
		
		/* 找到此页面的name */
		//int idx = url.lastIndexOf("/");
		//String urlname = url.substring(idx + 1);
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
		HttpSession session = request.getSession();
		//System.out.println(session.toString());
<<<<<<< HEAD
=======
		System.out.println(session.getAttribute("staffid"));
>>>>>>> 23d60c40ef5c0b3f32be62c8638c19f2abd8b4e8
		if(session != null) {
			if((session.getAttribute("staffid").equals(null)) && (session.getAttribute("type").equals(null))) {
				return false;
			}
			else
				return true;
<<<<<<< HEAD
		}
		else {
=======
		}else {
>>>>>>> 23d60c40ef5c0b3f32be62c8638c19f2abd8b4e8
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
