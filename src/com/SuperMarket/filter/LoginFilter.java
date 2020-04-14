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
		System.out.println("LoginFilter doFilter");
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		StringBuffer url = req.getRequestURL();
		
		System.out.println("请求的URL：" + url.toString());
		/* 找到此页面的name */
		int idx = url.lastIndexOf("/");
		String urlname = url.substring(idx + 1);
		
		if(!urlname.equals("Login.html")) {
			System.out.println("不是登陆页面，进行过滤");
			if(!isLogin(req)) {
				System.out.println("没有登录过或者账号密码错误，跳转到登录界面");
				resp.sendRedirect("Login.html");
			}else {
				System.out.println("已经登录，进行下一步");
				// pass the request along the filter chain
				chain.doFilter(request, response);
			}
		}else {
			System.out.println("是登陆页面，不进行过滤");
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}

	}
	
	private boolean isLogin(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		String inputUsername3 = "";
		String inputPassword3 = "";
		
		if(cookies != null && cookies.length > 0) {
			for(Cookie  cookie : cookies) {
				if(cookie.getName().equals("inputUsername3")) {
					inputUsername3 = cookie.getValue();
				}else if(cookie.getName().equals("inputPassword3")) {
					inputPassword3 = cookie.getValue();
				}
			}
		}
		
		if(inputUsername3.equals("") || inputPassword3.equals("")) {
			System.out.println("cookit为空");
			return false;
		}else
			return true;
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
