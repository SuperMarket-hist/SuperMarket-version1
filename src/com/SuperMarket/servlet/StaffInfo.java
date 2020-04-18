package com.SuperMarket.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.SuperMarket.bean.staff;

/**
 * Servlet implementation class StaffInfo
 */
@WebServlet("/StaffInfo")
public class StaffInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/* (non-Javadoc)  
	 * <p>Title: doPost</p>  
	 * <p>Description:查询员工信息 </p>  
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException  
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)  
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		int type = Integer.parseInt(request.getParameter("staffType"));
		String key = request.getParameter("key");
		StringBuffer sb = new staff().staffQuery(type, key);
		HttpSession session = request.getSession();
		session.setAttribute("search", sb);
		response.sendRedirect("StaffInfo.jsp");
		doGet(request, response);
	}

}
