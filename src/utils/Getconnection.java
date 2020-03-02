package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
* 数据库连接
* @author JamsF
* @version 创建时间：2020年3月2日 下午1:25:58
*/
public class Getconnection {
	/**
	 *声明连接数据库的信息 
	 **/
	private static final String URL = "jdbc:mysql://47.95.255.64:3306/SuperMarket?useUnicode=true&characterEncoding=UTF-8";
	private static final String USERNAME = "SuperMarket";
	private static final String PASSWORD = "zRyyf3YskdZ854kJ";
	public static java.sql.Connection getConn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
