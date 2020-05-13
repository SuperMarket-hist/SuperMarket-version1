package com.SuperMarket.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.SuperMarket.bean.staff;
import com.database.pool.JDBCTool;

/**
* 
* @author JamsF
* @version 创建时间：2020年4月11日 下午10:23:59
*/
public class DoLogin {
	
	/**
	 * 
	 * @Title: userLogin
	 * @Description: 执行用户登录的方法
	 * @author JamsF
	 * @date 2020年5月13日上午9:35:13
	 * @param userstaff
	 * @return int
	 * @throws SQLException
	 */
	public static int userLogin(staff userstaff) throws SQLException {
		/*
		 * 返回数据说明：
		 * 1、允许登录
		 * 2、密码错误
		 * 3、账号被冻结
		 */
		int loginflag = 0;
		
		String selectStaff = "select * from staff where staffid=?";
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(selectStaff);
		psta.setString(1, userstaff.getStaffid());
		
		ResultSet rs = psta.executeQuery();
		
		while (rs.next()) {
			String passW = rs.getString(3);//获取数据库中的密码
			int dataflag = rs.getInt(6);//获取帐号状态信息
			if(passW.equals(userstaff.getPassword())) {//密码匹配成功
				if(dataflag == 1) {
					//帐号状态为可用
					loginflag = 1;
				}
				else {
					loginflag = 3;//账号冻结
				}
				break;
			}
			else 
				loginflag = 2;
		}
		return loginflag;
	}

}
