package com.SuperMarket.bean;
/**
* 用于显示在仓库管理员视图下的商品信息
* @author JamsF
* @version 创建时间：2020年5月13日 下午3:17:33
*/
public class store_goods {
	private String GoodsID;//商品编号
	private String GoodsName;//商品名称
	private String Specs;//商品规格
	private String Unit;//商品单位
	private double MarketPrice;//进价
	private double SaPrice;//售价
	private double Discount;//折扣
	private String CTime;//生产日期
	private String STime;//有效期
	private String Category;//商品类别
	private String Factory;//供应商
	private int GoodsStock;//商品库存量
	private int WarnStock;//库存预警值（库存量少于该值，发生预警）
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
	public String getSpecs() {
		return Specs;
	}
	public void setSpecs(String specs) {
		Specs = specs;
	}
	public String getUnit() {
		return Unit;
	}
	public void setUnit(String unit) {
		Unit = unit;
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
	public double getDiscount() {
		return Discount;
	}
	public void setDiscount(double discount) {
		Discount = discount;
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
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getFactory() {
		return Factory;
	}
	public void setFactory(String factory) {
		Factory = factory;
	}
	public int getGoodsStock() {
		return GoodsStock;
	}
	public void setGoodsStock(int goodsStock) {
		GoodsStock = goodsStock;
	}
	public int getWarnStock() {
		return WarnStock;
	}
	public void setWarnStock(int warnStock) {
		WarnStock = warnStock;
	}
	
	
}
