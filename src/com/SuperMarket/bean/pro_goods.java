package com.SuperMarket.bean;
/**
* 编写商品信息类
* @author JamsF
* @version 创建时间：2020年4月12日 下午10:05:43
*/
public class pro_goods {
	private String GoodsID;//商品编号
	private String GoodsName;//商品名称
	private double MarketPrice;//进价
	private double SaPrice;//售价
	private String CTime;//生产日期
	private String STime;//有效期
	public String getGoodsID() {
		return GoodsID;
	}
	public void setGoodsID(String goodsID) {
		GoodsID = goodsID;
	}
	public String getGoodsName() {
		return GoodsName;
	}
	public void setGoodsName(String goodsName) {
		GoodsName = goodsName;
	}
	public double getMarketPrice() {
		return MarketPrice;
	}
	public void setMarketPrice(double marketPrice) {
		MarketPrice = marketPrice;
	}
	public double getSaPrice() {
		return SaPrice;
	}
	public void setSaPrice(double saPrice) {
		SaPrice = saPrice;
	}
	public String getCTime() {
		return CTime;
	}
	public void setCTime(String cTime) {
		CTime = cTime;
	}
	public String getSTime() {
		return STime;
	}
	public void setSTime(String sTime) {
		STime = sTime;
	}
	
	

}
