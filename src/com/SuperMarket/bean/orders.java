package com.SuperMarket.bean;
/**
* 编写订单类
* @author JamsF
* @version 创建时间：2020年4月12日 下午10:01:14
*/
public class orders {
	private String OrderNo;//订单编号
	private String GoodsID;//商品编号
	private String UserId;//会员账号
	private double GoodsNum;//商品数量
	private String staffid;//收银员工号
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public String getGoodsID() {
		return GoodsID;
	}
	public void setGoodsID(String goodsID) {
		GoodsID = goodsID;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public double getGoodsNum() {
		return GoodsNum;
	}
	public void setGoodsNum(int goodsNum) {
		GoodsNum = goodsNum;
	}
	public String getStaffid() {
		return staffid;
	}
	public void setStaffid(String staffid) {
		this.staffid = staffid;
	}
	
	

}
