package com.SuperMarket.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.SuperMarket.bean.staff;
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
	public static boolean DoSelectStaff(staff userstaff) throws SQLException {
		/*
		 * 当存在用户时，设置flag为true；相反则设置为false
		 */
		
		String selectStaff = "select * from staff where staffid=?";//查询该用户名的信息
		
		PreparedStatement psta = JDBCTool.executePreparedStatement(selectStaff);
		psta.setString(1, userstaff.getStaffid());
		
		ResultSet rs = psta.executeQuery();
		boolean flag = false;
		if(!rs.next()) {
			//查询结果为空，用户不存在，修改flag为false
			flag = false;
		}
		else
			//查询结果不为空，用户存在，修改flag为false
			flag = true;
		
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
		
		return returnstaff;
	}
	
	/**
	 * 
	 * @Title: DoSelectStaff
	 * @Description: 执行查询所有员工信息的方法
	 * @author JamsF
	 * @date 2020年5月13日上午9:28:44
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<staff> DoSelectStaff() throws SQLException {

		ArrayList<staff> list = new ArrayList<staff>();
		
		String selectStaff = "select * from staff";//查询该用户名的信息
		
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
	public wallet DoSelectWallet() {
		
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

}
