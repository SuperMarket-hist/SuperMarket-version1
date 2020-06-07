package com.SuperMarket.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.SuperMarket.bean.orders;
import com.SuperMarket.bean.store_goods;
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
		
		/*//获取json字符串不能使用request.getParameter()!!!
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
		String line = null;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
		String jsonstr = sb.toString();//拿到json字符串
		System.out.println(jsonstr);*/
		String jsonstr = request.getParameter("JsonStr");
		
		String OrderId = JSONObject.fromObject(jsonstr).getString("OrderNo");//得到订单编号
		String UserId = JSONObject.fromObject(jsonstr).getString("UserId");//得到会员账号
		if(UserId.equals(""))
			UserId = "000";
		String StaffId = JSONObject.fromObject(jsonstr).getString("StaffId");//得到收银员编号
		JSONArray json = JSONObject.fromObject(jsonstr).getJSONArray("Order");//得到商品数组
		double walletsale = 0;//订单总额
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
		
		pw.print(result);
	}

}
