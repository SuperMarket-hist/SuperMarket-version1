package com.database.pool;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;

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
				p.load(JDBCTool.class.getClassLoader().getResourceAsStream("druid.properties"));
			//创建数据源对象
			dataSource = new DruidDataSource();
			//设置属性
			DruidDataSource ds = (DruidDataSource)dataSource;
			ds.setUrl(p.getProperty("jdbcUrl"));
			ds.setPassword(p.getProperty("password"));
			ds.setUsername(p.getProperty("username"));
			ds.setInitialSize(new Integer(p.getProperty("initialSize")));
			ds.setMaxActive(new Integer(p.getProperty("maxActive")));
			ds.setMinIdle(new Integer(p.getProperty("minIdle")));
			ds.setMaxWait(new Long(p.getProperty("maxWait")));
			dataSource = ds;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("创建lian连池失败，" + e.getMessage());
		}
	}
	public Connection getConn() {
		//可以直接通过数据源获取可用的链接
		try {
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
}
