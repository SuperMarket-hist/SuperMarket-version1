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
	public boolean DoUpdateVipCount(String UserId,int UserScore) {
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
	public boolean DoUpdateWallet(String element,String vlaue) {
		
		boolean flag = false;
		String sqlUpdate = "update wallet set "+element+"="+vlaue+"";
		
		int result = 0;
		System.out.println(sqlUpdate);
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
	public int DoUpdateStaffInfo(staff staff) throws SQLException {
		
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

}
