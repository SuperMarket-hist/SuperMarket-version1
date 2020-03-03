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
* @version ����ʱ�䣺2020��3��2�� ����9:45:54
*/
public class JDBCTool {
	private static DataSource dataSource;
	static {
		try {
			Properties p = new Properties();
				p.load(JDBCTool.class.getClassLoader().getResourceAsStream("druid.properties"));
			//��������Դ����
			dataSource = new DruidDataSource();
			//��������
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
			throw new RuntimeException("����lian����ʧ�ܣ�" + e.getMessage());
		}
	}
	public Connection getConn() {
		//����ֱ��ͨ������Դ��ȡ���õ�����
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("��ȡ����ʧ�ܣ�" + e.getMessage());
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
				//�����ӻ��س���
				//ʹ��druid��ȡ�����ӣ�ֱ�ӹرռ���
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("δ�ܳɹ��ر�" + e.getMessage());
		}
	}
}
