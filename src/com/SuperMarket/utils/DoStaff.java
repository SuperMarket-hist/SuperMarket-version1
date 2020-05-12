package com.SuperMarket.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SuperMarket.bean.staff;
import com.database.pool.JDBCTool;


public class DoStaff {

	/*
	 * 员工信息更新 return boolean
	 */
	public boolean staffUpdate(String Staffid,staff staff) {
		String StaffUpdateSQL = "update staff set staffid='"
															+ staff.getStaffid() + 
												"'staffname='" + staff.getStaffname() +
												"'password='" + staff.getPassword() +
												"'type=" + staff.getType() + 
												"salary=" + staff.getSalary() + 
												"dataflag=" + staff.getDataflag() +
												"createtime='" + staff.getCreatetime() +
												"' where staff='" + Staffid +
												"'";
		int result = JDBCTool.executeUpdate(StaffUpdateSQL);
		if(result > 0) {
			System.out.println("更新员工" + staff.getStaffname() + "成功");
			return true;
		}
		else {
			System.out.println("更新员工" + staff.getStaffname() + "失败");
			return false;
		}
	}

	/*
	 * 员工信息查询 return boolean
	 * type:表中元素名称
	 * key:元素关键字
	 */
	public List<staff> staffQuery(int type, String key) {
		String querySQL = "select * from staff";
		if (key != null) {
			querySQL = "select * from staff where ? like %?%";
		}
		System.out.println(querySQL);
		PreparedStatement ps = JDBCTool.executePreparedStatement(querySQL);
		try {
			ps.setInt(1, type);
			ps.setString(2, key);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		List<staff> list = new ArrayList<staff>();
		boolean flag = false;
		try {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				staff sf = new staff();
				sf.setStaffid(rs.getString(1));
				sf.setStaffname(rs.getString(2));
				sf.setPassword(rs.getString(3));
				sf.setType(rs.getInt(4));
				sf.setSalary(rs.getDouble(5));
				sf.setDataflag(rs.getInt(6));
				sf.setCreatetime(rs.getString(7));
				list.add(sf);
			}
			JDBCTool.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}
}
