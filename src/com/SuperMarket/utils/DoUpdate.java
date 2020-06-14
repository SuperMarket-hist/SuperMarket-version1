package com.SuperMarket.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.SuperMarket.bean.orders;
import com.SuperMarket.bean.profit_info;
import com.SuperMarket.bean.staff;
import com.SuperMarket.bean.wallet;
import com.database.pool.JDBCTool;

/**
* 
* @author JamsF
* @version 创建时间：2020年5月13日 上午8:47:05
*/
public class DoUpdate {
	public static String Lockvip_cus = "LOCK TABLES vip_cus WRITE";//为表加排他锁，防止出现读脏数据等错误
	public static String Lockwallet = "LOCK TABLES wallet WRITE";
	public static String Lockwst_goods = "LOCK TABLES wst_goods WRITE";
	public static String Lockprofit_info = "LOCK TABLES profit_info WRITE";
	public static String UnLockTables = "UNLOCK TABLES ";//解锁表
	
	/**
	 * 
	 * @Title: updateVipCount
	 * @Description: 执行更新会员积分的方法
	 * @author 灵风 
	 * @date 2020年5月13日上午8:48:10
	 * @param UserId
	 * @param UserScore
	 * @return boolean
	 * @throws SQLException 
	 */
	public static boolean DoUpdateVipCount(String UserId,int UserScore) throws SQLException {
		int ResultInt = 0;
		
		String sqlUpdate = "update vip_cus set UserScore=? where UserId =?";
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(Lockvip_cus);//加锁
		
		int Score = DoSelect.DoSelectVIPScore(UserId);
		Score += UserScore;
		
		psta = JDBCTool.executePreparedStatement(sqlUpdate);
		psta.setInt(1, Score);
		psta.setString(2, UserId);
		
		boolean flag = false;
		try {
			ResultInt = psta.executeUpdate();
			
			if(ResultInt > 0) {
				flag = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		psta = JDBCTool.executePreparedStatement(UnLockTables);//解锁
		JDBCTool.close();
		return flag;
		
	}
	
	/**
	 * 
	 * @Title: updateWallet
	 * @Description: 退货业务实现修改总账信息
	 * @author 灵风
	 * @date 2020年5月13日上午8:52:33
	 * @param element
	 * @param vlaue
	 * @return boolean
	 * @throws SQLException 
	 */
	public static boolean DoUpdateWallet(Double Returnmoney) throws SQLException {
		
		boolean flag = false;
		String sqlUpdate = "update wallet set income=? where id = 1";
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(Lockwallet);
		
		wallet Wallet = new wallet();
		Wallet = DoSelect.DoSelectWallet();
		
		Double Income = Wallet.getIncome();
		Income += Returnmoney;
		
		psta = JDBCTool.executePreparedStatement(sqlUpdate);
		psta.setDouble(1, Income);
		
		int result = psta.executeUpdate();
		if(result > 0) 
			flag = true;
		
		JDBCTool.executePreparedStatement(UnLockTables);
		JDBCTool.close();
		return flag;
	}
	
	/**
	 * 
	 * @Title: DoUpdateStaffInfo
	 * @Description: 为管理员提供修改员工信息的方法
	 * @author JamsF
	 * @date 2020年5月13日上午8:59:55
	 * @return int
	 * @throws SQLException 
	 */
	public static int DoUpdateStaffInfo(staff staff) throws SQLException {
		
		Boolean checkstaffAvailable = DoSelect.DoSelectStaffid(staff.getStaffid());//检查要修改的用户是否存在
		if(checkstaffAvailable == true) {
			//用户存在，执行修改
			String UpdateStaffInfoSQL = "UPDATE staff set staffname=?,password=?,type=?,salary=?,dataflag=?,createtime=? WHERE staffid=?";
			
			PreparedStatement psta = JDBCTool.executePreparedStatement(UpdateStaffInfoSQL);
			
			psta.setString(1, staff.getStaffname());
			psta.setString(2, staff.getPassword());
			psta.setInt(3, staff.getType());
			psta.setDouble(4, staff.getSalary());
			psta.setInt(5, staff.getDataflag());
			psta.setString(6, staff.getCreatetime());
			psta.setString(7, staff.getStaffid());
			
			int UpdateResult = psta.executeUpdate();
			JDBCTool.close();
			return UpdateResult;			
		}
		else
			//用户不存在，返回标志
			return -1;
	}
	
	/**
	 * 
	 * @Title: DoUpdatePassword
	 * @Description: 为员工提供修改密码功能的方法
	 * @author JamsF
	 * @date 2020年5月14日下午3:13:53
	 * @param staffid
	 * @param NewPassword
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean DoUpdatePassword(String staffid,String NewPassword) throws SQLException {
		
		String UpdateStaffInfoSQL = "UPDATE staff set password=? WHERE staffid=?";
			
		PreparedStatement psta = JDBCTool.executePreparedStatement(UpdateStaffInfoSQL);
		psta.setString(1, NewPassword);
		psta.setString(2, staffid);
		
		int UpdateResult = psta.executeUpdate();
		JDBCTool.close();	
		if(UpdateResult == 1)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @Title: DoResetPassword
	 * @Description: 员工密码重置方法，将密码重置为员工编号
	 * @author JamsF
	 * @date 2020年6月8日下午5:13:16
	 * @param staffid
	 * @return int 成功返回1，失败返回0，员工不存在返回-1
	 * @throws SQLException
	 */
	public static int DoResetPassword(String staffid) throws SQLException {
		
		Boolean checkstaffAvailable = DoSelect.DoSelectStaffid(staffid);//检查要修改的用户是否存在
		if(checkstaffAvailable == true) {
			//用户存在，执行修改
			
			String newPassword = MD5Demo.md5(MD5Demo.md5(staffid) + staffid);//将员工编号加密作为原始密码
			String UpdateStaffInfoSQL = "UPDATE staff set password=? WHERE staffid=?";
			
			PreparedStatement psta = JDBCTool.executePreparedStatement(UpdateStaffInfoSQL);
			
			psta.setString(1, newPassword);
			psta.setString(2, staffid);
			
			int UpdateResult = psta.executeUpdate();
			JDBCTool.close();
			return UpdateResult;			
		}
		else
			//用户不存在，返回标志
			return -1;
	}
	
	
	/**
	 * 
	 * @Title: DoUpdateStaffQuit
	 * @Description: 执行员工离职后的帐号禁用事务
	 * @author JamsF
	 * @date 2020年5月19日下午4:28:32
	 * @param staffid
	 * @return int
	 * @throws SQLException
	 */
	public static int DoUpdateStaffQuit(String staffid) throws SQLException {
		boolean checkIDAvailable = DoSelect.DoSelectStaffid(staffid);//检查员工是否存在
		if(checkIDAvailable == true) {
			String UpdateStaffDataflagSQL = "UPDATE staff set dataflag=0 WHERE staffid=?";
			
			PreparedStatement psta = JDBCTool.executePreparedStatement(UpdateStaffDataflagSQL);
			psta.setString(1, staffid);
			
			int UpdateResult = psta.executeUpdate();
			JDBCTool.close();
			if(UpdateResult == 1)
				return 1;//成功
			else
				return 0;//失败
		}
		else
			return -1;//员工不存在
	}
	
	
	/**
	 * 
	 * @Title: DoSaleUpdateGoods
	 * @Description: 商品销售后的仓库储存数量更改
	 * @author JamsF
	 * @date 2020年5月14日下午3:00:55
	 * @param GoodsId
	 * @param GoodsNumber
	 * @return boolean
	 * @throws SQLException 
	 */
	public static boolean DoSaleUpdateGoods(String GoodsId,double GoodsNumber) throws SQLException {
		
		boolean Updateflag = false;
		
		String SaleUpdateGoodsSQL ="UPDATE wst_goods SET GoodsStock = ? WHERE GoodsId = ?";
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(Lockwst_goods);
		
		GoodsNumber = DoSelect.DoSelectGoodsNum(GoodsId) - GoodsNumber;
		
		psta = JDBCTool.getConn().prepareStatement(SaleUpdateGoodsSQL);
		psta.setDouble(1, GoodsNumber);
		psta.setString(2, GoodsId);
		
		int UpdateResult = psta.executeUpdate();
		
		psta = JDBCTool.executePreparedStatement(UnLockTables);
		JDBCTool.close();
		if(UpdateResult == 1)
			Updateflag = true;
		
		return Updateflag;
	}
	
	/**
	 * 
	 * @Title: DoReturnUpdateGoods
	 * @Description: 商品退货的仓库储存数量更改
	 * @author JamsF
	 * @date 2020年5月14日下午3:08:23
	 * @param GoodsId
	 * @param GoodsNumber
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean DoReturnUpdateGoods(String GoodsId,double GoodsNumber) throws SQLException {

		boolean Updateflag = false;
		
		String SaleUpdateGoodsSQL ="UPDATE wst_goods SET GoodsStock = ? WHERE GoodsId = ?";
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(Lockwst_goods);
		
		GoodsNumber += DoSelect.DoSelectGoodsNum(GoodsId);
		
		psta = JDBCTool.getConn().prepareStatement(SaleUpdateGoodsSQL);
		psta.setDouble(1, GoodsNumber);
		psta.setString(2, GoodsId);
		
		int UpdateResult = psta.executeUpdate();
		
		psta = JDBCTool.executePreparedStatement(UnLockTables);
		JDBCTool.close();
		if(UpdateResult == 1)
			Updateflag = true;
		
		return Updateflag;
	}
	
	/**
	 * 
	 * @Title: DoReturnDelectOrders
	 * @Description: 执行退货业务的删除订单事务
	 * @author JamsF
	 * @date 2020年5月26日下午5:26:22
	 * @param OrderNo
	 * @param GoodsId
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean DoReturnDelectOrders(String OrderNo,String GoodsId) throws SQLException {
		int result= 0;
		String DropOrders = "DELETE FROM orders WHERE OrderNo = ? AND GoodsId = ?";
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(DropOrders);
		psta.setString(1, OrderNo);
		psta.setString(2, GoodsId);
		
		result = psta.executeUpdate();
		JDBCTool.close();
		if(result == 1)
			return true;
		else
			return false;
	}
	
	
	/**
	 * 
	 * @Title: DoSaleCreatOrders
	 * @Description: 为收银处提供添加订单的方法
	 * @author JamsF
	 * @date 2020年5月31日下午5:59:06
	 * @param orders
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean DoSaleCreatOrders(orders orders) throws SQLException {
		int result = 0;
		String CreatOrders = "INSERT INTO orders VALUES(?,?,?,?,?)";
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(CreatOrders);
		psta.setString(1, orders.getOrderNo());
		psta.setString(2, orders.getGoodsID());
		psta.setString(3, orders.getUserId());
		psta.setDouble(4, orders.getGoodsNum());
		psta.setString(5, orders.getStaffid());
		
		result = psta.executeUpdate();
		JDBCTool.close();
		if(result == 1)
			return true;
		else
			return false;
	}
	
	/**
	 * 
	 * @Title: DoUpdateProfitInfo
	 * @Description: 执行更新报表数据项的方法
	 * @author JamsF
	 * @date 2020年6月12日下午7:19:50
	 * @param Info
	 * @return 更新成功返回true，失败则返回false
	 * @throws SQLException
	 */
	public static boolean DoUpdateProfitInfo(profit_info Info) throws SQLException {
		int result = 0;
		
		String Updateprofit_infoSQL = "UPDATE profit_info SET SaleMoney = ?,Profit = ? WHERE Date = ?";
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(Lockprofit_info);//锁表
		
		profit_info OldInfo = DoSelect.DoSelectDateInTableInfo(Info.getDate());
		Info.setSaleMoney(Info.getSaleMoney() + OldInfo.getSaleMoney());
		Info.setProfit(Info.getProfit() + OldInfo.getProfit());
		
		psta = JDBCTool.executePreparedStatement(Updateprofit_infoSQL);
		psta.setDouble(1, Info.getSaleMoney());
		psta.setDouble(2, Info.getProfit());
		psta.setDate(3, Info.getDate());
		
		result = psta.executeUpdate();
		
		psta = JDBCTool.executePreparedStatement(UnLockTables);
		
		JDBCTool.close();
		if(result == 1)
			return true;
		else
			return false;
	}

}
