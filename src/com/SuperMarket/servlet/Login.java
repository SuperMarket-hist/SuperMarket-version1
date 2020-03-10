package com.SuperMarket.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.SuperMarket.utils.MD5Demo;
import com.database.pool.JDBCTool;
import com.mysql.jdbc.PreparedStatement;

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
			String STAFFID = request.getParameter("inputUsername3");//获取staffid
			String PASSWORD = request.getParameter("inputPassword3");//获取明文password
			System.out.println("ID:" + STAFFID);
			System.out.println("PASSWORD:" + PASSWORD);
			String fpass = MD5Demo.md5(MD5Demo.md5(STAFFID) + PASSWORD);//将明文password按照约定进行MD5加密，与数据库的值进行对比
			System.out.println("FINALPASS:" + fpass);
			Connection getConn = null;
			getConn = JDBCTool.getConn();
			System.out.println(getConn);
			String selectStaff = "select * from staff where staffid='" + STAFFID + "'";
			PreparedStatement psta = null;
			psta = (PreparedStatement) getConn.prepareStatement(selectStaff);
			ResultSet rs = psta.executeQuery();
			boolean flag = false;
			while (rs.next()) {
				String userN = rs.getString(1);
				String passW = rs.getString(3);
				int stype = rs.getInt(6);
				if(userN.equals(STAFFID)&&passW.equals(fpass)) {
					if(stype == 1)
						flag = true;
					break;
				}
			}
			if(flag) {
				request.getRequestDispatcher("WEB-INF/SuperMarket/Register.html").forward(request, response);
			}
			else {
				request.getRequestDispatcher("failed.html").forward(request, response);
			}
			JDBCTool.closeAll(psta,getConn);//断开连接，释放资源
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
