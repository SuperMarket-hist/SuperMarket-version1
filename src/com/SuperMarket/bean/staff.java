package com.SuperMarket.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.database.pool.JDBCTool;

/**
* 编写员工类
* @author JamsF
* @version 创建时间：2020年3月3日 下午4:23:56
*/

/**
 * <p>
 * Title: staff
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author 灵风
 * @date 2020年4月18日
 */

public class staff {
	private String staffid; // 员工编号
	private String staffname; // 员工姓名
	private String password; // 登录密码
	private int type; // 员工类型：1：超级管理员 2:仓库管理员 3：售货员 4：收银员
	private double salary; // 员工薪资
	private int dataflag; // 帐号状态：1：可用 0：禁用
	private String createtime; // 入职时间

	public String getStaffid() {
		return staffid;
	}

	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}

	public String getStaffname() {
		return staffname;
	}

	public void setStaffname(String staffname) {
		this.staffname = staffname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double d) {
		this.salary = d;
	}

	public int getDataflag() {
		return dataflag;
	}

	public void setDataflag(int dataflag) {
		this.dataflag = dataflag;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String string) {
		this.createtime = string;
	}

	
}
