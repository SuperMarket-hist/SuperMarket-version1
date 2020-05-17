package com.SuperMarket.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SuperMarket.bean.staff;
import com.SuperMarket.utils.DoSelect;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class StaffIfo
 * 查询全部員工信息查詢
 */
@WebServlet("/StaffIfo")
public class StaffIfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffIfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter pw = response.getWriter();
		
		ArrayList<staff> list = new ArrayList<staff>();
		try {
			list = DoSelect.DoSelectStaff();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("查询员工信息失败");
			e.printStackTrace();
		}
		JSONArray ja = JSONArray.fromObject(list);//json本来就是一个字符串，只不过是有格式的字符串,因此需要转换成字符串
		pw.print(ja.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
