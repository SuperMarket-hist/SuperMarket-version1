package com.SuperMarket.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SuperMarket.bean.profit_info;
import com.SuperMarket.utils.DataUtil;
import com.SuperMarket.utils.DoSelect;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SelectTableInfo7
 */
@WebServlet("/SelectTableInfo7")
public class SelectTableInfo7 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectTableInfo7() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		String getEndDate = request.getParameter("EndDate");
		java.sql.Date EndDate = DataUtil.strToDate(getEndDate);
		
		ArrayList<profit_info> list = new ArrayList<profit_info>();
		
		try {
			java.sql.Date StartDate = DataUtil.getDayBefore7(EndDate);
			list = DoSelect.SelectTableInfo(StartDate, EndDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JSONArray DateArr = new JSONArray();
		JSONArray MoneyArr = new JSONArray();
		JSONArray ProfitArr = new JSONArray();
		
		for (profit_info Info : list) {
			DateArr.add(Info.getDate().toString());
			MoneyArr.add(Info.getSaleMoney());
			ProfitArr.add(Info.getProfit());
		}
		
		JSONObject jsonobj = new JSONObject();
		jsonobj.put("Date", DateArr);
		jsonobj.put("SaleMoney", MoneyArr);
		jsonobj.put("Profit", ProfitArr);
		
		String ReturnStr = jsonobj.toString();
		
		pw.print(ReturnStr);
		
	}

}
