package com.SuperMarket.bean;
/**
* 
* @author JamsF
* @version 创建时间：2020年6月12日 下午6:53:24
*/
public class profit_info {
	private java.sql.Date Date;//日期
	private double SaleMoney;//营业额
	private double Profit;//净收益
	public java.sql.Date getDate() {
		return Date;
	}
	public void setDate(java.sql.Date date) {
		Date = date;
	}
	public double getSaleMoney() {
		return SaleMoney;
	}
	public void setSaleMoney(double saleMoney) {
		SaleMoney = saleMoney;
	}
	public double getProfit() {
		return Profit;
	}
	public void setProfit(double profit) {
		Profit = profit;
	}
	
}
