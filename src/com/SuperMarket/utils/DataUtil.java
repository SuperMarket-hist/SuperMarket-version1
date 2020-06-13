package com.SuperMarket.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
* 
* @author JamsF
* @version 创建时间：2020年6月12日 下午5:19:05
*/
public class DataUtil {
	/**
	 * 
	 * @Title: timeStamp2Date
	 * @Description: 将时间戳格式的订单号转换为日期格式，用于在报表数据的存储
	 * @author JamsF
	 * @date 2020年6月12日下午5:19:19
	 * @param seconds
	 * @return 转换为日期格式的字符串
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public static java.sql.Date timeStamp2Date(String seconds) throws NumberFormatException, ParseException {
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        Date d = sdf.parse(sdf.format(new Date(Long.valueOf(seconds)))); 
        return new java.sql.Date(d.getTime());
    }
	
	/**
	 * 
	 * @Title: getDayBefore7
	 * @Description: 计算当前日期的七天前日期数据
	 * @author JamsF
	 * @date 2020年6月13日上午7:34:49
	 * @param data
	 * @return 返回当前日期七天前的日期数据
	 * @throws ParseException
	 */
	public static java.sql.Date getDayBefore7(java.sql.Date data) throws ParseException{
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		int day1 = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day1 - 7);
		java.sql.Date d = UtilDate2SqlDate(c.getTime());
		return d;
	}
	
	/**
	 * 
	 * @Title: getDayBefore30
	 * @Description: 计算当前日期的30天前日期数据
	 * @author JamsF
	 * @date 2020年6月13日上午7:35:48
	 * @param data
	 * @return 返回当前日期30天前的日期数据
	 * @throws ParseException
	 */
	public static java.sql.Date getDayBefore30(java.sql.Date data) throws ParseException{
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		int day1 = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day1 - 30);
		java.sql.Date d = UtilDate2SqlDate(c.getTime());
		return d;
	}
	
	/**
	 * 
	 * @Title: UtilDate2SqlDate
	 * @Description: 将输入的java.util.Date数据转换为java.sql.Date数据
	 * @author JamsF
	 * @date 2020年6月13日上午7:36:12
	 * @param UtilDate
	 * @return 转化后的java.sql.Date数据
	 * @throws ParseException
	 */
	public static java.sql.Date UtilDate2SqlDate(java.util.Date UtilDate) throws ParseException{
		String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);  
        Date d = sdf.parse(sdf.format(UtilDate)); 
        return new java.sql.Date(d.getTime());
	}
	
	/**
	 * 
	 * @Title: strToDate
	 * @Description: 将String类型的日期数据转换为java.sql.Date类型
	 * @author JamsF
	 * @date 2020年6月13日上午8:58:05
	 * @param strDate
	 * @return 转换后的java.sql.Date数据
	 */
	public static java.sql.Date strToDate(String strDate) {  
        String str = strDate;  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");  
        java.util.Date d = null;  
        try {  
            d = format.parse(str);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        java.sql.Date date = new java.sql.Date(d.getTime());  
        return date;  
    }  

}
