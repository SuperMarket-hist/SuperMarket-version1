package com.SuperMarket.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.SuperMarket.bean.staff;
import com.database.pool.JDBCTool;

/**
* 
* @author JamsF
* @version 创建时间：2020年5月13日 上午8:47:05
*/
public class DoUpdate {
	
	/**
	 * 
	 * @Title: updateVipCount
	 * @Description: 执行更新会员积分的方法
	 * @author 灵风 
	 * @date 2020年5月13日上午8:48:10
	 * @param UserId
	 * @param UserScore
	 * @return boolean
	 */
	public static boolean DoUpdateVipCount(String UserId,int UserScore) {
		int ResultInt = 0;
		
		String sqlUpdate = "update vip_cus set UserScore=? where UserId =?";
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(sqlUpdate);

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
		return flag;
		
	}
	
	/**
	 * 
	 * @Title: updateWallet
	 * @Description: 按需求修改总账数据
	 * @author 灵风
	 * @date 2020年5月13日上午8:52:33
	 * @param element
	 * @param vlaue
	 * @return
	 */
	public static boolean DoUpdateWallet(String element,String vlaue) {
		
		boolean flag = false;
		String sqlUpdate = "update wallet set "+element+"="+vlaue+"";
		
		int result = 0;
		result = JDBCTool.executeUpdate(sqlUpdate);
		if(result > 0) {
			System.out.println("修改成功");
			flag = true;
		}
		
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
		
		Boolean checkstaffAvailable = DoSelect.DoSelectStaff(staff);//检查要修改的用户是否存在
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
			
		if(UpdateResult == 1)
			return true;
		else
			return false;
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
		
		PreparedStatement psta = JDBCTool.getConn().prepareStatement(SaleUpdateGoodsSQL);
		psta.setDouble(1, GoodsNumber);
		psta.setString(2, GoodsId);
		
		int UpdateResult = psta.executeUpdate();
		
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
	 * @return
	 * @throws SQLException
	 */
	public static boolean DoReturnUpdateGoods(String GoodsId,double GoodsNumber) throws SQLException {

		boolean Updateflag = false;
		
		String SaleUpdateGoodsSQL ="UPDATE wst_goods SET GoodsStock = ? WHERE GoodsId = ?";
		
		PreparedStatement psta = JDBCTool.getConn().prepareStatement(SaleUpdateGoodsSQL);
		psta.setDouble(1, GoodsNumber);
		psta.setString(2, GoodsId);
		
		int UpdateResult = psta.executeUpdate();
		
		if(UpdateResult == 1)
			Updateflag = true;
		
		return Updateflag;
	}

}
