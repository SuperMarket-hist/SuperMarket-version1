package com.SuperMarket.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SuperMarket.bean.staff;
import com.SuperMarket.utils.DoRegist;

/**
 * Servlet implementation class checkIdAvailable
 */
@WebServlet("/checkIdAvailable")
public class checkIdAvailable extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkIdAvailable() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 *
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @return 返回一个Boolean类型的结果，false为不可添加，true为允许添加
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		String staffId = request.getParameter("staffId");//获取Ajax传来的待检测员工Id
		
		staff checkStaffId = new staff();//新建一个用户以便调用检查方法
		checkStaffId.setStaffid(staffId);//设置员工用户的ID
		boolean checkResult = false;
		
		try {
			checkResult = DoRegist.selectuser(checkStaffId);//检查是否具有重复ID
			pw.print(checkResult);//送回检测结果
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
