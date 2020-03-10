package com.database.pool;

import java.sql.Statement;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

/**
* 
* @author JamsF
* @version 创建时间：2020年3月2日 下午9:45:54
*/
public class JDBCTool {
	private static DataSource dataSource;
	static {
		try {
			Properties p = new Properties();
			InputStream is = JDBCTool.class.getClassLoader().getResourceAsStream("druid.properties");
			p.load(is);
			//创建数据源对象
			dataSource = DruidDataSourceFactory.createDataSource(p);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("创建连接池失败，" + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static DataSource getDataSource() {
		try {
			return dataSource;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static Connection getConn() {
		//可以直接通过数据源获取可用的链接
		try {
			if(dataSource.getConnection() == null) {
				System.out.println("JDBCTool未获取到链接");
			}
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("获取链接失败，" + e.getMessage());
		}
	}
	public static void closeAll(Statement st,ResultSet rs,Connection conn) {
		try {
			if(st != null) {
				st.close();
			}
			if(rs != null) {
				rs.close();
			}
			if(conn != null) {
				//将连接还回池中
				//使用druid获取的连接，直接关闭即可
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("未能成功关闭" + e.getMessage());
		}
	}
	public static void closeAll(Statement st,Connection conn){
		try {
			if(st != null) {
				st.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
