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
		try {
			response.setCharacterEncoding("utf-8");
			request.setCharacterEncoding("utf-8");
			String staffid = request.getParameter("staffid");	//员工编号
			String staffname = request.getParameter("staffname");	//员工姓名
			String password = request.getParameter("password");	//登录密码
			int type = Integer.parseInt(request.getParameter("type"));	//员工类型：1.管理员，2.仓库管理员，3.收银员
			double salary = Double.parseDouble(request.getParameter("salary"));	//员工薪资
			int dataflag = Integer.parseInt(request.getParameter("dataflag"));	//帐号状态：1.可用，2.禁用
			String createtime = request.getParameter("createtime");	//入职时间
			String fpass = MD5Demo.md5(MD5Demo.md5(staffid) + password);//将明文password按照约定进行MD5加密，与数据库的值进行对比
			String selectStaff = "select staffid from staff where staffid='" + staffid + "'";//查找是否有重名用户
			String addUserSql = "INSERT INTO staff VALUE('" + staffid + "','" + staffname + "','" + fpass + "'," + type + "," + salary + "," + dataflag + ",'" + createtime + "')";
			String insertResult = null;//设置插入结果返回标志
			Connection getConn = null;
			getConn = JDBCTool.getConn();//获取链接
			PreparedStatement psta = null;
			psta = (PreparedStatement) getConn.prepareStatement(selectStaff);
			boolean canInsert = true;//设置插入允许标志
			ResultSet rscanInsert = psta.executeQuery();
			while(rscanInsert.next()) {
				String staffId = rscanInsert.getString(1);
				if(staffId.equals(staffid)) {
					canInsert = false;//查询不到相同staffid，允许插入，修改标志位为1
					break;
				}				
			}
			if(canInsert == true) {
				//进行插入
				int rsInsert = psta.executeUpdate(addUserSql);//插入成功返回受影响的行数
				if(rsInsert == 1) {
					insertResult = "1";//设置插入成功时的返回标志
					request.setAttribute("insertResult", insertResult);
					request.getRequestDispatcher("WEB-INF/SuperMarket/Register.html").forward(request, response);
					//添加用户成功，返回标志1，返回原网页，前端网页弹窗提醒
					JDBCTool.closeAll(psta,getConn);//断开连接，释放资源
				}
				else {
					insertResult = "2";//设置插入失败时的返回标志
					request.setAttribute("insertResult", insertResult);
					request.getRequestDispatcher("WEB-INF/SuperMarket/Register.html").forward(request, response);
					//添加用户失败，返回标志2，返回原网页，前端网页弹窗提醒
					JDBCTool.closeAll(psta,getConn);//断开连接，释放资源
				}
			}
			else {
				//canInsert标志位为false，当前id不允许插入，返回标志3
				insertResult = "3";//当前用户不允许插入
				request.setAttribute("insertResult", insertResult);
				request.getRequestDispatcher("WEB-INF/SuperMarket/index.html").forward(request, response);
				JDBCTool.closeAll(psta,getConn);//断开连接，释放资源
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
