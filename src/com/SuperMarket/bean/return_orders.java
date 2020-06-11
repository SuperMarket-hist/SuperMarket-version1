package com.SuperMarket.bean;
/**
* 
* @author JamsF
* @version 创建时间：2020年6月9日 下午10:28:44
*/
public class return_orders {
	private String OrderNo;//订单编号
	private String GoodsId;//商品编号
	private String GoodsName;//商品名称
	private double SaPrice;//销售单价
	private double GoodsNum;//商品数量
	private double GoodsMoney;//商品小结
	private String StaffName;//收银员姓名
	private String VIPId;//会员编号
	
	public String getVIPId() {
		return VIPId;
	}
	public void setVIPId(String vIPId) {
		VIPId = vIPId;
	}
	public String getOrderNo() {
		return OrderNo;
	}
	public void setOrderNo(String orderNo) {
		OrderNo = orderNo;
	}
	public String getGoodsId() {
		return GoodsId;
	}
	public void setGoodsId(String goodsId) {
		GoodsId = goodsId;
	}
	public String getGoodsName() {
		return GoodsName;
	}
	public void setGoodsName(String goodsName) {
		GoodsName = goodsName;
	}
	public double getSaPrice() {
		return SaPrice;
	}
	public void setSaPrice(double saPrice) {
		SaPrice = saPrice;
	}
	public double getGoodsNum() {
		return GoodsNum;
	}
	public void setGoodsNum(double goodsNum) {
		GoodsNum = goodsNum;
	}
	public double getGoodsMoney() {
		return GoodsMoney;
	}
	public void setGoodsMoney(double goodsMoney) {
		GoodsMoney = goodsMoney;
	}
	public String getStaffName() {
		return StaffName;
	}
	public void setStaffName(String staffName) {
		StaffName = staffName;
	}
	
}
