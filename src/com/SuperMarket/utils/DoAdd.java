package com.SuperMarket.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.SuperMarket.bean.orders;
import com.SuperMarket.bean.pro_goods;
import com.SuperMarket.bean.vip_cus;
import com.database.pool.JDBCTool;

/**
* 
* @author JamsF
* @version 创建时间：2020年5月12日 下午9:54:31
*/
public class DoAdd {
	
	/**
	 * 
	 * @Title: DoAddOrders
	 * @Description: 执行添加订单方法
	 * @author JamsF
	 * @date 2020年5月12日下午10:16:59
	 * @param orers
	 * @return int
	 * @throws SQLException
	 */
	public int DoAddOrders(orders orers) throws SQLException {
		
		String AddOrderSQL = "INSERT INTO orders VALUES (?,?,?,?,?)";
		
		PreparedStatement psta =  JDBCTool.executePreparedStatement(AddOrderSQL);
		
		psta.setString(1, orers.getOrderNo());
		psta.setString(2, orers.getGoodsID());
		psta.setString(3, orers.getUserId());
		psta.setDouble(4, orers.getGoodsNum());
		psta.setString(5, orers.getStaffid());
		
		int InsertResult = psta.executeUpdate();
		
		return InsertResult;
	}
	
	/**
	 * 
	 * @Title: DoAddGoods
	 * @Description: 执行添加商品的方法
	 * @author JamsF
	 * @date 2020年5月12日下午10:54:04
	 * @param Goods
	 * @return int
	 * @throws SQLException
	 */
	public int DoAddGoods(pro_goods Goods) throws SQLException {
		
		String AddGoodSQL = "INSERT INTO pro_goods VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(AddGoodSQL);
		
		psta.setString(1, Goods.getGoodsID());
		psta.setString(2, Goods.getGoodsName());
		psta.setString(3, Goods.getSpecs());
		psta.setString(4, Goods.getUnit());
		psta.setDouble(5, Goods.getMarketPrice());
		psta.setDouble(6, Goods.getSaPrice());
		psta.setDouble(7, Goods.getDiscount());
		psta.setString(8, Goods.getCTime());
		psta.setString(9, Goods.getSTime());
		psta.setString(10, Goods.getCategory());
		psta.setString(11, Goods.getFactory());
		
		int  InsertResult = psta.executeUpdate();
		
		return InsertResult;
	}
	
	/**
	 * 
	 * @Title: DoAddVip
	 * @Description: 执行注册会员的方法
	 * @author JamsF
	 * @date 2020年5月12日下午10:58:44
	 * @param Vip
	 * @return
	 * @throws SQLException
	 */
	public int DoAddVip(vip_cus Vip) throws SQLException {
		
		String AddVipSQL = "INSERT INTO vip_cus VALUES (?,?,?)";
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(AddVipSQL);
		
		psta.setString(1, Vip.getUserId());
		psta.setString(2, Vip.getUserName());
		psta.setInt(3, Vip.getUserScore());
		
		int  InsertResult = psta.executeUpdate();
		
		return InsertResult;
	}

}