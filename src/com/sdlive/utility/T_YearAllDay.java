package com.sdlive.utility;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *树袋老师
 * @author 作者 xpp
 * @version 创建时间：2017-10-26 上午11:32:50
 * 类说明
 * 工具类:名字t_开头
 * 获取两年内所有日期,返回一个列表
 * 从第一年1月1号开始，第二年1月1号是中间，第三年1月1号结束
 * 也就是说只支持选择年份的1月1号到第二年年底的排课
 */

public class T_YearAllDay {
  
  public ArrayList<Date> getyearAllDay(Calendar cal) {
    int year = cal.get(Calendar.YEAR);    //获取年
    Calendar startCal = Calendar.getInstance();
    startCal.set(Calendar.YEAR, year);//年
    startCal.set(Calendar.MONTH, 0);//月份从0开始，到11结束,此处代表1月份
    startCal.set(Calendar.DAY_OF_MONTH, 0);//日,此处表示1号
    startCal.set(Calendar.HOUR, 20);//时
    startCal.set(Calendar.MINUTE, 20);//分
    startCal.set(Calendar.SECOND, 20);//秒
    Date startDate = startCal.getTime();
    
    Calendar endCal = Calendar.getInstance();
    endCal.set(Calendar.YEAR, year+2);//年
    endCal.set(Calendar.MONTH, 0);//月份从0开始，到11结束,此处代表1月份
    endCal.set(Calendar.DAY_OF_MONTH, 1);//日,此处表示1号
    endCal.set(Calendar.HOUR, 20);//时
    endCal.set(Calendar.MINUTE, 20);//分
    endCal.set(Calendar.SECOND, 20);//秒
    Date endDate = endCal.getTime();
    
    ArrayList<Date> relist = new ArrayList<Date>();
    // 测试此日期是否在指定日期之后  
    while (endDate.after(startCal.getTime()))  
    {  
     // 根据日历的规则，为给定的日历字段添加或减去指定的时间量  
      Date d1 = startCal.getTime();
      relist.add(d1);
      startCal.add(Calendar.DAY_OF_MONTH, 1);
    }
    return relist;  
    
  }//end method getyearAllDay

}//end class t_yearallday
