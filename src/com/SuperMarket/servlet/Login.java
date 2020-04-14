package com.SuperMarket.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SuperMarket.bean.staff;
import com.SuperMarket.utils.DoLogin;
import com.SuperMarket.utils.MD5Demo;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			
			String STAFFID = request.getParameter("inputUsername3");//获取staffid
			String PASSWORD = request.getParameter("inputPassword3");//获取明文password
			String fpass = MD5Demo.md5(MD5Demo.md5(STAFFID) + PASSWORD);//将明文password按照约定进行MD5加密，与数据库的值进行对比
			
			staff userstaff = new staff();//新建员工
			userstaff.setStaffid(STAFFID);
			userstaff.setPassword(fpass);//设置员工的id与密码
			
			int flag = DoLogin.userLogin(userstaff);			
			if(flag == 1) {
				//密码正确，允许登录
				System.out.println("添加cookit");
				Cookie inputUsername = new Cookie("inputUsername3",STAFFID);
				Cookie inputPassword = new Cookie("inputPassword3", fpass);
				response.addCookie(inputUsername);
				response.addCookie(inputPassword);
				request.getRequestDispatcher("WEB-INF/SuperMarket/Register.html").forward(request, response);
			}
			else if(flag == 2){
				//密码错误
				request.getRequestDispatcher("failed.html").forward(request, response);
			}
			else {
				//账户冻结
				PrintWriter out = response.getWriter();
				out.print("账户已被冻结");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		};

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
