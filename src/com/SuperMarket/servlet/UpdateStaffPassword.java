package com.SuperMarket.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SuperMarket.utils.DoSelect;
import com.SuperMarket.utils.DoUpdate;
import com.SuperMarket.utils.MD5Demo;

/**
 * Servlet implementation class UpdateStaffPassword
 * 更新员工密码
 */
@WebServlet("/UpdateStaffPassword")
public class UpdateStaffPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStaffPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		String staffid = request.getParameter("staffid");
		String oldpass = request.getParameter("oldpass");
		String newpass = request.getParameter("newpass");
		
		String fpass = MD5Demo.md5(MD5Demo.md5(staffid) + oldpass);//将明文password按照约定进行MD5加密，与数据库的值进行对比
		String Newfpass = MD5Demo.md5(MD5Demo.md5(staffid) + newpass);//加密后的新密码
		
		boolean compare = false;//匹配标志
		boolean flag = false;//修改标志
		try {
			if(DoSelect.DoSelectStaffPassword(staffid).equals(fpass)) {
				//密码匹配成功，开始修改
				compare = true;
				flag = DoUpdate.DoUpdatePassword(staffid, Newfpass);
			}
			else
				compare = false;//匹配失败
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(compare == true && flag == true)
			//修改成功
			pw.print(1);
		else if(compare == false)
			//匹配失败
			pw.print(2);
		else
			//修改失败
			pw.print(3);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
