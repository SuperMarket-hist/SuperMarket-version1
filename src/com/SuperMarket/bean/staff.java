package com.SuperMarket.bean;

import java.sql.Date;

/**
* ��дԱ����
* @author JamsF
* @version ����ʱ�䣺2020��3��3�� ����4:23:56
*/
public class staff {
	private String staffid;	//Ա�����
	private String staffname;	//Ա������
	private String password;	//��¼����
	private int type;	//Ա�����ͣ�1������Ա	2:�ֿ����Ա	3���ۻ�Ա	4������Ա
	private int salary;	//Ա��н��
	private int dataflag;	//�ʺ�״̬��1������	0������
	private Date createtime;	//��ְʱ��
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
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getDataflag() {
		return dataflag;
	}
	public void setDataflag(int dataflag) {
		this.dataflag = dataflag;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
