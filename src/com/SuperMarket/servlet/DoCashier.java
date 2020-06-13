package com.SuperMarket.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SuperMarket.bean.orders;
import com.SuperMarket.bean.profit_info;
import com.SuperMarket.bean.store_goods;
import com.SuperMarket.utils.DataUtil;
import com.SuperMarket.utils.DoAdd;
import com.SuperMarket.utils.DoSelect;
import com.SuperMarket.utils.DoUpdate;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class DoCashier
 */
@WebServlet("/DoCashier")
public class DoCashier extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoCashier() {
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
		
		String jsonstr = request.getParameter("JsonStr");
		
		String OrderId = JSONObject.fromObject(jsonstr).getString("OrderNo");//得到订单编号
		String UserId = JSONObject.fromObject(jsonstr).getString("UserId");//得到会员账号
		if(UserId.equals(""))
			UserId = "000";
		String StaffId = JSONObject.fromObject(jsonstr).getString("StaffId");//得到收银员编号
		JSONArray json = JSONObject.fromObject(jsonstr).getJSONArray("Order");//得到商品数组
		double walletsale = 0;//订单总额
		double profit = 0;
		boolean result = false;//保存执行结果
		
		for(int i = 0;i < json.size();i++) {//遍历json数组
			JSONObject jsonObj = json.getJSONObject(i);
			String GoodsId = jsonObj.getString("GoodsId");//得到商品编号
			double GoodsNum = jsonObj.getDouble("GoodsNum");//得到商品数量
			store_goods goods = new store_goods();
			try {
				goods = DoSelect.DoSelectGoodsInfo(GoodsId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			walletsale += (goods.getSaPrice()) * GoodsNum;//计算总销售额
			profit += (goods.getMarketPrice()) * GoodsNum;//计算净收益
			try {
				result =DoUpdate.DoSaleUpdateGoods(GoodsId, GoodsNum);//商品出库
				
				orders temptorders = new orders();
				temptorders.setOrderNo(OrderId);
				temptorders.setGoodsID(GoodsId);
				temptorders.setGoodsNum(GoodsNum);
				temptorders.setUserId(UserId);
				temptorders.setStaffid(StaffId);
				
				result =DoUpdate.DoSaleCreatOrders(temptorders);//添加订单
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//更新总账
		try {
			result =DoUpdate.DoUpdateWallet(walletsale);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//更新积分
		try {
			result =DoUpdate.DoUpdateVipCount(UserId, (int)(walletsale));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//更新报表数据
		profit_info MoneyInfo = new profit_info();
		try {
			MoneyInfo.setDate(DataUtil.timeStamp2Date(OrderId));
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}//得到订单日期数据
		MoneyInfo.setSaleMoney(walletsale);
		MoneyInfo.setProfit(profit);
		try {
			boolean flag = DoSelect.DoCheckDateAvailable(MoneyInfo.getDate());
			if(flag == true)
				result = DoUpdate.DoUpdateProfitInfo(MoneyInfo);
			else
				result = DoAdd.InsertProfitInfo(MoneyInfo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pw.print(result);
	}

}
