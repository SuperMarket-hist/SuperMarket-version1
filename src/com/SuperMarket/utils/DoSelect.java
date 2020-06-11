package com.SuperMarket.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.SuperMarket.bean.pro_goods;
import com.SuperMarket.bean.return_orders;
import com.SuperMarket.bean.staff;
import com.SuperMarket.bean.store_goods;
import com.SuperMarket.bean.vip_cus;
import com.SuperMarket.bean.wallet;
import com.database.pool.JDBCTool;

/**
* 
* @author JamsF
* @version 创建时间：2020年5月13日 上午8:31:48
*/
public class DoSelect {
	
	/**
	 * 
	 * @Title: selectuser
	 * @Description: 检查数据库中是否存在相同的staffid
	 * @author JamsF
	 * @date 2020年5月13日上午8:32:30
	 * @param userstaff
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean DoSelectStaffid(String staffid) throws SQLException {
		/*
		 * 当存在用户时，设置flag为true；相反则设置为false
		 */
		
		String selectStaff = "select staffid from staff where staffid=?";//查询该用户名的信息
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(selectStaff);
		psta.setString(1, staffid);
		
		ResultSet rs = psta.executeQuery();
		boolean flag = false;
		if(!rs.next()) {
			//查询结果为空，用户不存在，修改flag为false
			flag = false;
		}
		else
			//查询结果不为空，用户存在，修改flag为false
			flag = true;
		JDBCTool.close();
		return flag;
	}
	
	/**
	 * 
	 * @Title: DoSelectStaff
	 * @Description: 执行查询指定员工信息的方法
	 * @author JamsF
	 * @date 2020年5月13日上午9:24:28
	 * @param staffid
	 * @return staff
	 * @throws SQLException
	 */
	public static staff DoSelectStaff(String staffid) throws SQLException {

		staff returnstaff = new staff();
		
		String selectStaff = "select * from staff where staffid=?";//查询该用户名的信息
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(selectStaff);
		psta.setString(1, staffid);
		
		ResultSet rs = psta.executeQuery();
		
		while(rs.next()) {
			returnstaff.setStaffid(staffid);
			returnstaff.setStaffname(rs.getString(2));
			returnstaff.setType(rs.getInt(4));
			returnstaff.setSalary(rs.getDouble(5));
			returnstaff.setDataflag(rs.getInt(6));
			returnstaff.setCreatetime(rs.getString(7));
		}
		JDBCTool.close();
		return returnstaff;
	}
	
	/**
	 * 
	 * @Title: DoSelectStaffType
	 * @Description: 为权限控制的filter提供查询指定员工职位的方法
	 * @author JamsF
	 * @date 2020年5月13日下午2:34:30
	 * @param staffid
	 * @return int
	 * @throws SQLException
	 */
	public static int DoSelectStaffType(String staffid) throws SQLException {
		
		int StaffType = 0;
		
		String selectStaff = "select type from staff where staffid=?";//查询指定员工的职位
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(selectStaff);
		psta.setString(1, staffid);
		
		ResultSet rs = psta.executeQuery();
		
		while(rs.next()) {
			StaffType = rs.getInt(1);
		}
		JDBCTool.close();
		return StaffType;
	}
	
	/**
	 * 
	 * @Title: DoSelectStaff
	 * @Description: 执行查询所有员工信息的方法
	 * @author JamsF
	 * @date 2020年5月13日上午9:28:44
	 * @return ArrayList<staff>
	 * @throws SQLException
	 */
	public static ArrayList<staff> DoSelectStaff() throws SQLException {

		ArrayList<staff> list = new ArrayList<staff>();
		
		String selectStaff = "SELECT * FROM `staff` ORDER BY dataflag DESC,type ASC";//查询用户信息
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(selectStaff);
		
		ResultSet rs = psta.executeQuery();
		
		while(rs.next()) {
			staff returnstaff = new staff();
			returnstaff.setStaffid(rs.getString(1));
			returnstaff.setStaffname(rs.getString(2));
			returnstaff.setType(rs.getInt(4));
			returnstaff.setSalary(rs.getDouble(5));
			returnstaff.setDataflag(rs.getInt(6));
			returnstaff.setCreatetime(rs.getString(7));
			list.add(returnstaff);
		}
		JDBCTool.close();
		return list;
	}
	
	/**
	 * 
	 * @Title: DoSelectWallet
	 * @Description: 执行查询总账信息的方法
	 * @author 灵风
	 * @date 2020年5月13日上午9:32:58
	 * @return wallet
	 */
	public static wallet DoSelectWallet() {
		
		wallet wt = new wallet();
		
		String sqlQuery = "select * from wallet WHERE id = 1";
		
		ResultSet rs = JDBCTool.executeQuery(sqlQuery);
			
		if(rs != null) {
			try {
				while(rs.next()) {
					wt.setIncome(rs.getDouble(2));
					wt.setOutcome(rs.getDouble(1));
					wt.setProfit(rs.getDouble(3));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JDBCTool.close();
		return wt;	
		}
	
	/**
	 * 
	 * @Title: DoSelectGoods
	 * @Description: 为前台收银提供Ajax根据商品id查询商品信息方法
	 * @author JamsF
	 * @date 2020年5月13日下午2:56:21
	 * @param GoodsId
	 * @return pro_goods
	 * @throws SQLException
	 */
	public static pro_goods DoSelectGoods(String GoodsId) throws SQLException {
		pro_goods Goods = new pro_goods();
		
		String SelectGoodsSQL = "SELECT * FROM pro_goods WHERE GoodsId = ?";
		
		PreparedStatement psta = JDBCTool.getConn().prepareStatement(SelectGoodsSQL);
		psta.setString(1, GoodsId);
		ResultSet rs = psta.executeQuery();
		
		while(rs.next()) {
			Goods.setGoodsID(GoodsId);
			Goods.setGoodsName(rs.getString(2));
			Goods.setSpecs(rs.getString(3));
			Goods.setUnit(rs.getString(4));
			Goods.setSaPrice(rs.getDouble(6));
			Goods.setDiscount(rs.getDouble(7));
			Goods.setCTime(rs.getString(8));
			Goods.setSTime(rs.getString(9));
			Goods.setCategory(rs.getString(10));
			Goods.setFactory(rs.getString(11));			
		}
		JDBCTool.close();
		return Goods;
	}
	
	/**
	 * 
	 * @Title: DoSelectGoods
	 * @Description: 为仓库管理员提供查询指定商品信息的方法
	 * @author JamsF
	 * @date 2020年5月13日下午3:23:10
	 * @return store_goods
	 * @throws SQLException
	 */
	public static store_goods DoSelectGoodsInfo(String GoodsId) throws SQLException {
				
		String SelectGoodsSQL = "SELECT pro_goods.`GoodsId`,GoodsName,Specs,Unit,MarketPrice,SaPrice,Discount,CTime,STime,Category,Factory,wst_goods.GoodsStock,wst_goods.`WarnStock` FROM pro_goods,wst_goods WHERE pro_goods.`GoodsId` = wst_goods.`GoodsId` AND wst_goods.`GoodsId`=?";
		
		PreparedStatement psta = JDBCTool.getConn().prepareStatement(SelectGoodsSQL);
		psta.setString(1, GoodsId);
		
		ResultSet rs = psta.executeQuery();

		store_goods Goods = new store_goods();
		
		while(rs.next()) {
			Goods.setGoodsID(rs.getString(1));
			Goods.setGoodsName(rs.getString(2));
			Goods.setSpecs(rs.getString(3));
			Goods.setUnit(rs.getString(4));
			Goods.setMarketPrice(rs.getDouble(5));
			Goods.setSaPrice(rs.getDouble(6));
			Goods.setDiscount(rs.getDouble(7));
			Goods.setCTime(rs.getString(8));
			Goods.setSTime(rs.getString(9));
			Goods.setCategory(rs.getString(10));
			Goods.setFactory(rs.getString(11));	
			Goods.setGoodsStock(rs.getInt(12));
			Goods.setWarnStock(rs.getInt(13));
		}
		JDBCTool.close();
		return Goods;
	}
	
	/**
	 * 
	 * @Title: DoSelectGoodsNum
	 * @Description: 查询指定商品库存数量
	 * @author JamsF
	 * @date 2020年5月26日下午7:36:03
	 * @param GoodsId
	 * @return int
	 * @throws SQLException
	 */
	public static double DoSelectGoodsNum(String GoodsId) throws SQLException {
		int result = -1;
		String SelectGoods = "SELECT GoodsStock from wst_goods WHERE GoodsId = ?";
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(SelectGoods);
		psta.setString(1, GoodsId);
		
		ResultSet rs = psta.executeQuery();
		
		if(rs.next())
			result = rs.getInt(1);
		JDBCTool.close();
		return result;
	}
	
	
	/**
	 * 
	 * @Title: DoSelectGoods
	 * @Description: 为仓库管理员提供查询所有商品信息的方法
	 * @author JamsF
	 * @date 2020年5月13日下午3:23:10
	 * @return ArrayList<store_goods>
	 * @throws SQLException
	 */
	public static ArrayList<store_goods> DoSelectAllGoodsInfo() throws SQLException {
		
		ArrayList<store_goods> list = new ArrayList<store_goods>();
		
		String SelectGoodsSQL = "SELECT pro_goods.`GoodsId`,GoodsName,Specs,Unit,MarketPrice,SaPrice,Discount,CTime,STime,Category,Factory,wst_goods.GoodsStock,wst_goods.`WarnStock` FROM pro_goods,wst_goods WHERE pro_goods.`GoodsId` = wst_goods.`GoodsId`";
		
		PreparedStatement psta = JDBCTool.getConn().prepareStatement(SelectGoodsSQL);
		
		ResultSet rs = psta.executeQuery();
		
		while(rs.next()) {
			store_goods Goods = new store_goods();
			Goods.setGoodsID(rs.getString(1));
			Goods.setGoodsName(rs.getString(2));
			Goods.setSpecs(rs.getString(3));
			Goods.setUnit(rs.getString(4));
			Goods.setMarketPrice(rs.getDouble(5));
			Goods.setSaPrice(rs.getDouble(6));
			Goods.setDiscount(rs.getDouble(7));
			Goods.setCTime(rs.getString(8));
			Goods.setSTime(rs.getString(9));
			Goods.setCategory(rs.getString(10));
			Goods.setFactory(rs.getString(11));	
			Goods.setGoodsStock(rs.getInt(12));
			Goods.setWarnStock(rs.getInt(13));
			list.add(Goods);
		}
		JDBCTool.close();
		return list;
	}
	
	
	/**
	 * 
	 * @Title: DoSelectWarnGoods
	 * @Description: 为仓库管理员提供查询库存预警商品信息的方法
	 * @author JamsF
	 * @date 2020年5月13日下午3:33:51
	 * @param warndate
	 * @return ArrayList<store_goods>
	 * @throws SQLException
	 */
	public static ArrayList<store_goods> DoSelectWarnGoods() throws SQLException{
		
		ArrayList<store_goods> list = new ArrayList<store_goods>();
		
		String SelectWarnGoodsSQL = "SELECT pro_goods.`GoodsId`,GoodsName,Specs,Unit,MarketPrice,SaPrice,Discount,CTime,STime,Category,Factory,wst_goods.GoodsStock,wst_goods.`WarnStock` FROM pro_goods,wst_goods WHERE pro_goods.`GoodsId` = wst_goods.`GoodsId` AND GoodsStock < WarnStock";
		
		PreparedStatement psta = JDBCTool.getConn().prepareStatement(SelectWarnGoodsSQL);
		
		ResultSet rs = psta.executeQuery();
		
		while(rs.next()) {
			store_goods Goods = new store_goods();
			Goods.setGoodsID(rs.getString(1));
			Goods.setGoodsName(rs.getString(2));
			Goods.setSpecs(rs.getString(3));
			Goods.setUnit(rs.getString(4));
			Goods.setMarketPrice(rs.getDouble(5));
			Goods.setSaPrice(rs.getDouble(6));
			Goods.setDiscount(rs.getDouble(7));
			Goods.setCTime(rs.getString(8));
			Goods.setSTime(rs.getString(9));
			Goods.setCategory(rs.getString(10));
			Goods.setFactory(rs.getString(11));	
			Goods.setGoodsStock(rs.getInt(12));
			Goods.setWarnStock(rs.getInt(13));
			list.add(Goods);
		}
		JDBCTool.close();
		return list;
		
	}
	
	
	/**
	 * 
	 * @Title: DoSelectWarnGoods
	 * @Description: 为仓库管理员提供查询临期商品信息的方法
	 * @author JamsF
	 * @date 2020年5月13日下午3:33:51
	 * @param warndate
	 * @return ArrayList<store_goods>
	 * @throws SQLException
	 */
	public static ArrayList<store_goods> DoSelectDateGoods(String warndate) throws SQLException{
		
		ArrayList<store_goods> list = new ArrayList<store_goods>();
		
		String SelectWarnGoodsSQL = "SELECT pro_goods.`GoodsId`,GoodsName,Specs,Unit,MarketPrice,SaPrice,Discount,CTime,STime,Category,Factory,wst_goods.GoodsStock,wst_goods.`WarnStock` FROM pro_goods,wst_goods WHERE pro_goods.`GoodsId` = wst_goods.`GoodsId` AND pro_goods.`STime` < ?";
		
		PreparedStatement psta = JDBCTool.getConn().prepareStatement(SelectWarnGoodsSQL);
		psta.setString(1, warndate);
		
		ResultSet rs = psta.executeQuery();
		
		while(rs.next()) {
			store_goods Goods = new store_goods();
			Goods.setGoodsID(rs.getString(1));
			Goods.setGoodsName(rs.getString(2));
			Goods.setSpecs(rs.getString(3));
			Goods.setUnit(rs.getString(4));
			Goods.setMarketPrice(rs.getDouble(5));
			Goods.setSaPrice(rs.getDouble(6));
			Goods.setDiscount(rs.getDouble(7));
			Goods.setCTime(rs.getString(8));
			Goods.setSTime(rs.getString(9));
			Goods.setCategory(rs.getString(10));
			Goods.setFactory(rs.getString(11));	
			Goods.setGoodsStock(rs.getInt(12));
			Goods.setWarnStock(rs.getInt(13));
			list.add(Goods);
		}
		JDBCTool.close();
		return list;
		
	}
	
	/**
	 * 
	 * @Title: DoSelectGoodsIdAvailable
	 * @Description: 查询货号是否合法
	 * @author JamsF
	 * @date 2020年6月7日下午6:42:59
	 * @param GoodsId
	 * @return 合法返回true，不合法返回false
	 * @throws SQLException
	 */
	public static boolean DoSelectGoodsIdAvailable(String GoodsId) throws SQLException {
		
		boolean flag = false;
		
		String SelectSQL = "SELECT GoodsId from wst_goods WHERE GoodsId = ?";
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(SelectSQL);
		psta.setString(1, GoodsId);
		
		ResultSet rs = psta.executeQuery();
		
		if(!rs.next()) {
			//查询结果为空，用户不存在，修改flag为false
			flag = false;
		}
		else
			//查询结果不为空，用户存在，修改flag为false
			flag = true;
		JDBCTool.close();
		return flag;
	}
	
	/**
	 * 
	 * @Title: DoSelectOrders
	 * @Description: 为退货业务实现查询订单的方法
	 * @author JamsF
	 * @date 2020年5月13日下午3:07:02
	 * @param OrderNo
	 * @return ArrayList<orders>
	 * @throws SQLException
	 */
	public static ArrayList<return_orders> DoSelectOrders(String OrderNo) throws SQLException {
		
		ArrayList<return_orders> list = new ArrayList<return_orders>();
		
		String SelectOrderSQL = "SELECT o.OrderNo,o.GoodsId,g.GoodsName,g.SaPrice,o.GoodsNum,(o.GoodsNum * g.SaPrice * g.Discount),s.staffname,o.UserId FROM orders o,pro_goods g,staff s WHERE o.staffid = s.staffid AND o.GoodsId = g.GoodsId AND o.OrderNo = ?";
		
		PreparedStatement psta = JDBCTool.getConn().prepareStatement(SelectOrderSQL);
		psta.setString(1, OrderNo);
		
		ResultSet rs = psta.executeQuery();
		
		while(rs.next()) {
			return_orders Orders = new return_orders();
			Orders.setOrderNo(rs.getString(1));
			Orders.setGoodsId(rs.getString(2));
			Orders.setGoodsName(rs.getString(3));
			Orders.setSaPrice(rs.getDouble(4));
			Orders.setGoodsNum(rs.getDouble(5));
			Orders.setGoodsMoney(rs.getDouble(6));
			Orders.setStaffName(rs.getString(7));
			Orders.setVIPId(rs.getString(8));
			list.add(Orders);
		}
		JDBCTool.close();
		return list;
	}
	
	/**
	 * 
	 * @Title: DoSelectVIPScore
	 * @Description: 执行查询指定会员积分事务
	 * @author JamsF
	 * @date 2020年5月26日下午7:13:49
	 * @param UserId
	 * @return int
	 * @throws SQLException
	 */
	public static int DoSelectVIPScore(String UserId) throws SQLException {
		int result = -1;
		String SelectVIPScore = "SELECT UserScore from vip_cus where UserId = ?";
		
		PreparedStatement psta = JDBCTool.getConn().prepareStatement(SelectVIPScore);
		psta.setString(1, UserId);
		
		ResultSet rs = psta.executeQuery();
		
		if(rs.next())
			result = rs.getInt(1);
		JDBCTool.close();
		return result;
	}
	
	/**
	 * 
	 * @Title: DoSelectVIPCount
	 * @Description: 执行查询会员信息的方法
	 * @author JamsF
	 * @date 2020年6月8日下午5:03:37
	 * @param UserId
	 * @return 查询的会员对象
	 * @throws SQLException
	 */
	public static vip_cus DoSelectVIPCount(String UserId) throws SQLException {
		
		String SelectVIPCount = "SELECT * from vip_cus where UserId = ?";
		
		vip_cus VIPCount = new vip_cus();
		
		PreparedStatement psta = JDBCTool.getConn().prepareStatement(SelectVIPCount);
		psta.setString(1, UserId);
		
		ResultSet rs = psta.executeQuery();
		
		while(rs.next()) {
			VIPCount.setUserId(rs.getString(1));
			VIPCount.setUserName(rs.getString(2));
			VIPCount.setUserScore(rs.getInt(3));
		}
		
		JDBCTool.close();
		
		return VIPCount;
	}
	
}
