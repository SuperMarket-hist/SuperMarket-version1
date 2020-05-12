/**
* <p>Title: DoUpdateWallet.java</p>  
* <p>Description: </p>  
* <p>Copyright: southwind (c) 2020</p>   
* @author 灵风  
* @date 2020年5月12日  
* @version 1.0  
 */
package com.SuperMarket.utils;

import com.database.pool.JDBCTool;

import jdk.nashorn.internal.scripts.JD;

/**
 * <p>
 * Title: DoUpdateWallet
 * </p>
 * <p>
 * Description: 逐个修改总账数据
 * </p>
 * 
 * @author 灵风
 * @date 2020年5月12日
 */
public class DoUpdateWallet {
	public boolean updateWallet(String element,String vlaue) {
		// TODO Auto-generated constructor stub
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
}
