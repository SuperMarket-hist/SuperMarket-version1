/**
* <p>Title: wallet.java</p>  
* <p>Description: </p>  
* <p>Copyright: southwind (c) 2020</p>   
* @author 灵风  
* @date 2020年5月12日  
* @version 1.0  
 */
package com.SuperMarket.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: wallet</p>  
 * <p>Description: 钱包账户been，有支出，收入，利润</p>  
 * @author 灵风 
 * @date 2020年5月12日 
 */
public class wallet {
	/**
	 * @return the outcome
	 */
	private double outcome;
	private double income;
	private double profit;
	
	public double getOutcome() {
		return outcome;
	}
	/**
	 * @param outcome the outcome to set
	 */
	public void setOutcome(double outcome) {
		this.outcome = outcome;
	}
	/**
	 * @return the income
	 */
	public double getIncome() {
		return income;
	}
	/**
	 * @param income the income to set
	 */
	public void setIncome(double income) {
		this.income = income;
	}
	/**
	 * @return the profit
	 */
	public double getProfit() {
		return profit;
	}
	/**
	 * @param profit the profit to set
	 */
	public void setProfit(double profit) {
		this.profit = profit;
	}

}
