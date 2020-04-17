package com.SuperMarket.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.SuperMarket.bean.staff;
import com.database.pool.JDBCTool;

/**
* 
* @author JamsF
* @version 创建时间：2020年4月11日 下午10:23:59
* 编写用户登录公共类，减少重复代码
*/
public class DoLogin {
	
	/**
	 * 
	    * @Title: userLogin
	    * @Description: TODO(执行登录事务)
	    * @param @param userstaff
	    * @param @return
	    * @param @throws SQLException    参数
	    * @return int    返回类型
	    * @throws
	 */
	public static int userLogin(staff userstaff) throws SQLException {
		
		/*
		 * 返回数据说明：
		 * 1、允许登录
		 * 2、密码错误
		 * 3、账号被冻结
		 */
		
		Connection getConn = null;
		getConn = JDBCTool.getConn();//获取连接
		
		String selectStaff = "select * from staff where staffid=?";//查询该用户名的信息
		java.sql.PreparedStatement psta = getConn.prepareStatement(selectStaff);
		psta.setString(1, userstaff.getStaffid());
		
		ResultSet rs = psta.executeQuery();
		int loginflag = 0;
		while (rs.next()) {
			String passW = rs.getString(3);//获取数据库中的密码
			int stype = rs.getInt(6);//获取帐号状态信息
			if(passW.equals(userstaff.getPassword())) {//密码匹配成功
				if(stype == 1) {
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
		JDBCTool.closeAll(psta,getConn);//断开连接，释放资源
		return loginflag;
	}

}
