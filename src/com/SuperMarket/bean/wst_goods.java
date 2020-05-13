package com.SuperMarket.bean;
/**
* 编写商品库存类
* @author JamsF
* @version 创建时间：2020年4月12日 下午10:12:29
*/
public class wst_goods {
	
	private String GoodsId;//商品编号
	private double GoodsStock;//商品库存量
	private double WarnStock;//库存预警值（库存量少于该值，发生预警）
	public String getGoodsId() {
		return GoodsId;
	}
	public void setGoodsId(String goodsId) {
		GoodsId = goodsId;
	}
	public double getGoodsStock() {
		return GoodsStock;
	}
	public void setGoodsStock(int goodsStock) {
		GoodsStock = goodsStock;
	}
	public double getWarnStock() {
		return WarnStock;
	}
	public void setWarnStock(int warnStock) {
		WarnStock = warnStock;
	}
	
	

}
