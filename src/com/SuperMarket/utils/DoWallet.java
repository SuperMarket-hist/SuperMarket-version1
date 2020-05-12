/**
* <p>Title: DoWallet.java</p>  
* <p>Description: </p>  
* <p>Copyright: southwind (c) 2020</p>   
* @author 灵风  
* @date 2020年5月12日  
* @version 1.0  
 */
package com.SuperMarket.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.SuperMarket.bean.wallet;
import com.database.pool.JDBCTool;

/**
 * <p>Title: DoWallet</p>  
 * <p>Description: </p>  
 * @author 灵风 
 * @date 2020年5月12日 
 */
public class DoWallet {
	/* 查询钱包 */
	public wallet walletQuery() {
	//	List<wallet> list = new ArrayList<wallet>();
		String sqlQuery = "select * from wallet";
		ResultSet rs = JDBCTool.executeQuery(sqlQuery);
		System.out.println(sqlQuery);
		wallet wt = new wallet();
		if(rs != null) {
			try {
				while(rs.next()) {
					//wallet wt = new wallet();
					wt.setIncome(rs.getDouble(2));
					wt.setOutcome(rs.getDouble(1));
					wt.setProfit(rs.getDouble(3));
				//	list.add(wt);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			System.out.println("查询失败");
		}
		JDBCTool.close();
		return wt;
		
	}

}
