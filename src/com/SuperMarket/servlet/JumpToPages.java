package com.SuperMarket.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class JumpToPages
 */
@WebServlet("/JumpToPages")
public class JumpToPages extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JumpToPages() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			HttpSession session = request.getSession();//获取session
			if(session.getAttribute("type").toString().equals("1")) {
				//超级管理员登录
				response.sendRedirect("http://localhost:8080/SuperMarket-vresion1/SuperMarket/index.html");
			}
			else if(session.getAttribute("type").toString().equals("2")) {
				//仓库管理员登录
				response.sendRedirect("http://localhost:8080/SuperMarket-vresion1/SuperMarket/StorePage/StoreDepartment.html");
			}
			else if(session.getAttribute("type").toString().equals("3")) {
				//办公室员工登录
				response.sendRedirect("http://localhost:8080/SuperMarket-vresion1/SuperMarket/OfficePage/OfficeDepartment.html");
			}
			else if(session.getAttribute("type").toString().equals("4")) {
				//收银员登录
				response.sendRedirect("http://localhost:8080/SuperMarket-vresion1/SuperMarket/SalePage/SaleDepartment.html");
			}
			else if(session.getAttribute("type").toString().equals("5")) {
				//进货部员工登录
				response.sendRedirect("http://localhost:8080/SuperMarket-vresion1/SuperMarket/IncomePage/IncomeDepartment.html");
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
