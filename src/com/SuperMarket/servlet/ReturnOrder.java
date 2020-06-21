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

import com.SuperMarket.bean.profit_info;
import com.SuperMarket.bean.store_goods;
import com.SuperMarket.utils.DataUtil;
import com.SuperMarket.utils.DoSelect;
import com.SuperMarket.utils.DoUpdate;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ReturnOrder
 */
@WebServlet("/ReturnOrder")
public class ReturnOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReturnOrder() {
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
		String UserId = JSONObject.fromObject(jsonstr).getString("VIPId");//得到会员账号
		JSONArray json = JSONObject.fromObject(jsonstr).getJSONArray("Order");//得到商品数组
		double walletsale = 0;//退货商品总额
		double profitsale = 0;//退货商品利润
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
			profitsale += (goods.getMarketPrice()) * GoodsNum;//计算总利润
			try {
				result =DoUpdate.DoReturnUpdateGoods(GoodsId, GoodsNum);//商品入库
				result =DoUpdate.DoReturnDelectOrders(OrderId, GoodsId);//删除销售记录
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//更新总账
		try {
			result =DoUpdate.DoUpdateWallet(0 - walletsale);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//更新积分
		try {
			result =DoUpdate.DoUpdateVipCount(UserId, (int)(0 - walletsale));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//更新报表数据
		profit_info pf = new profit_info();
		try {
			pf.setDate(DataUtil.timeStamp2Date(OrderId));
			pf.setProfit(0 - profitsale);
			pf.setSaleMoney(0 - walletsale);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			result = DoUpdate.DoUpdateProfitInfo(pf);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pw.print(result);
	}

}
