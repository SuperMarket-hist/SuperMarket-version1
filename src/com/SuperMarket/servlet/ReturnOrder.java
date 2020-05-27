package com.SuperMarket.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SuperMarket.bean.store_goods;
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
		
		//获取json字符串不能使用request.getParameter()!!!
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
		
		JSONArray json = JSONArray.fromObject(sb.toString());//将json字符串转化为json数组
		System.out.println(json);
		double walletsale = 0;//退货商品总额
		String userId = "";//退货会员账号
		boolean result = false;//保存执行结果
		
		for(int i = 0;i < json.size();i++) {//遍历json数组
			JSONObject jsonObj = json.getJSONObject(i);
			String GoodsId = jsonObj.getString("GoodsId");//得到商品编号
			String OrderId = jsonObj.getString("OrderNo");//得到订单编号
			userId = jsonObj.getString("UserId");//得到会员账号
			double GoodsNum = jsonObj.getDouble("GoodsNum");//得到商品数量
			store_goods goods = new store_goods();
			try {
				goods = DoSelect.DoSelectGoodsInfo(GoodsId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			walletsale += (goods.getSaPrice()) * GoodsNum;//计算总销售额
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
			result =DoUpdate.DoUpdateVipCount(userId, (int)(0 - walletsale));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pw.print(result);
	}

}
