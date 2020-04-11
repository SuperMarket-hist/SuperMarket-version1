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
import com.SuperMarket.utils.MD5Demo;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			
			String fpass = MD5Demo.md5(MD5Demo.md5(request.getParameter("staffid")) + request.getParameter("password"));//将明文password按照约定进行MD5加密，与数据库的值进行对比
			
			staff userstaff = new staff();//新建一个用户
			userstaff.setStaffid(request.getParameter("staffid"));	//添加员工编号
			userstaff.setStaffname(request.getParameter("staffname"));//添加员工姓名
			userstaff.setPassword(fpass);	//添加登录密码
			userstaff.setType(Integer.parseInt(request.getParameter("type")));	//添加员工类型：1.管理员，2.仓库管理员，3.收银员
			userstaff.setSalary(Double.parseDouble(request.getParameter("salary")));	//添加员工薪资
			userstaff.setDataflag(Integer.parseInt(request.getParameter("dataflag")));	//添加帐号状态：1.可用，2.禁用
			userstaff.setCreatetime(request.getParameter("createtime"));	//添加入职时间
			
			int insertResult = 2;
			try {
				insertResult = DoRegist.userRegist(userstaff);

				
				if(insertResult == 1) {
					request.setAttribute("1", insertResult);
					request.getRequestDispatcher("WEB-INF/SuperMarket/Register.html").forward(request, response);
					//添加用户成功，返回标志1，返回原网页，前端网页弹窗提醒
				}
				else if(insertResult == 2) {
					request.setAttribute("2", insertResult);
					request.getRequestDispatcher("WEB-INF/SuperMarket/Register.html").forward(request, response);
					//添加用户失败，返回标志2，返回原网页，前端网页弹窗提醒
				}
				else{
					request.setAttribute("3", insertResult);
					request.getRequestDispatcher("WEB-INF/SuperMarket/Register.html").forward(request, response);
					//发现重复用户名，添加用户失败，返回标志2，返回原网页，前端网页弹窗提醒
				}				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
