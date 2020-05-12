/**
* <p>Title: DoUpdateVipCount.java</p>  
* <p>Description: </p>  
* <p>Copyright: southwind (c) 2020</p>   
* @author 灵风  
* @date 2020年5月12日  
* @version 1.0  
 */
package com.SuperMarket.utils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.database.pool.JDBCTool;


/**
 * <p>Title: DoUpdateVipCount</p>  
 * <p>Description: 修改会员积分</p>  
 * @author 灵风 
 * @date 2020年5月12日 
 */
public class DoUpdateVipCount {
	/* 按照编号修改会员积分 */
	public boolean updateVipCount(String UserId,int UserScore) {
		String sqlUpdate = "update vip_cus set UserScore=? where UserId =?";
		System.out.println(sqlUpdate);
		PreparedStatement ps = JDBCTool.executePreparedStatement(sqlUpdate);
		int result = 0;
		boolean flag = false;
		try {
			ps.setInt(1, UserScore);
			ps.setString(2, UserId);
			result = ps.executeUpdate();
			if(result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("更新失败");
			e.printStackTrace();
		}
		return flag;
		
	}
}
