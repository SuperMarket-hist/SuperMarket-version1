package com.SuperMarket.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SuperMarket.bean.pro_goods;
import com.SuperMarket.utils.DoAdd;

/**
 * Servlet implementation class AddGoods
 */
@WebServlet("/AddGoods")
public class AddGoods extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddGoods() {
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
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		pro_goods pg = new pro_goods();
		pg.setGoodsID(request.getParameter("GoodsID"));
		pg.setCategory(request.getParameter("Category"));
		pg.setCTime(request.getParameter("CTime"));
		pg.setDiscount(Double.parseDouble(request.getParameter("Discount")));
		pg.setFactory(request.getParameter("Factory"));
		pg.setGoodsName(request.getParameter("GoodsName"));
		pg.setMarketPrice(Double.parseDouble(request.getParameter("MarketPrice")));
		pg.setSaPrice(Double.parseDouble(request.getParameter("SaPrice")));
		pg.setSpecs(request.getParameter("Specs"));
		pg.setSTime(request.getParameter("STime"));
		pg.setUnit(request.getParameter("Unit"));
		
		int result = 0;
		try {
			result = new DoAdd().DoAddGoods(pg);
			if(result == 1)
				result = DoAdd.DoIncomeGood(pg.getGoodsID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.print(result);
	}

}
