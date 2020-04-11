package com.SuperMarket.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.SuperMarket.bean.staff;
import com.database.pool.JDBCTool;

/**
* 
* @author JamsF
* @version 创建时间：2020年4月11日 下午11:07:09
* 编写用户注册公共类，减少重复代码
*/
public class DoRegist {
	
	/**
	 * 
	    * @Title: selectuser
	    * @Description: 检查数据库中是否存在相同的staffid
	    * @param @param userstaff
	    * @param @return
	    * @param @throws SQLException    参数
	    * @return boolean    返回类型
	    * @throws
	 */
	public static boolean selectuser(staff userstaff) throws SQLException {
		/*
		 * 当存在用户时，设置flag为false，不允许添加；相反则设置为true，允许添加
		 */
		Connection getConn = null;
		getConn = JDBCTool.getConn();//获取连接
		
		String selectStaff = "select * from staff where staffid=?";//查询该用户名的信息
		java.sql.PreparedStatement psta = getConn.prepareStatement(selectStaff);
		psta.setString(1, userstaff.getStaffid());
		
		ResultSet rs = psta.executeQuery();
		boolean flag = false;
		if(!rs.next()) {
			//查询结果为空，修改flag为true
			flag = true;
		}
		JDBCTool.closeAll(psta,getConn);//断开连接，释放资源
		return flag;
		
	}
	
	/**
	 * 
	    * @Title: userRegist
	    * @Description: 执行注册用户事务
	    * @param @param userstaff
	    * @param @return
	    * @param @throws SQLException    参数
	    * @return int    返回类型
	    * @throws
	 */
	public static int userRegist(staff userstaff) throws SQLException {
		
		/*
		 * 返回数据说明：
		 * 1、添加成功
		 * 2、添加失败
		 * 3、发现重复用户名
		 */		
		boolean finduser = DoRegist.selectuser(userstaff);//查找用户
		if(finduser == false) {
			//发现重复用户名，拒绝添加
			return 3;
		}
		
		else {
			//未找到重复用户名，允许添加
			
			Connection getConn = null;
			getConn = JDBCTool.getConn();//获取连接
			
			String addUserSql = "INSERT INTO staff VALUE(?,?,?,?,?,?,?)";
			java.sql.PreparedStatement psta = getConn.prepareStatement(addUserSql);
			psta.setString(1, userstaff.getStaffid());
			psta.setString(2, userstaff.getStaffname());
			psta.setString(3, userstaff.getPassword());
			psta.setInt(4, userstaff.getType());
			psta.setDouble(5, userstaff.getSalary());
			psta.setInt(6, userstaff.getDataflag());
			psta.setString(7, userstaff.getCreatetime());
			
			int rsInsert = psta.executeUpdate();//插入成功返回受影响的行数
			JDBCTool.closeAll(psta,getConn);//断开连接，释放资源
			if(rsInsert == 1) {
				return 1;
			}
			else
				return 2;
		}
		
	}
	
}
