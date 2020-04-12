package com.SuperMarket.bean;
/**
* 编写会员类
* @author JamsF
* @version 创建时间：2020年4月12日 下午10:09:53
*/
public class vip_cus {
	
	private String UserId;//会员编号
	private String UserName;//会员姓名
	private int UserScore;//会员积分
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public int getUserScore() {
		return UserScore;
	}
	public void setUserScore(int userScore) {
		UserScore = userScore;
	}
	
	

}
