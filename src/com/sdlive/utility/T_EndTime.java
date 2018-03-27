package com.sdlive.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 树袋老师
 * 
 * @author 作者 xpp
 * @version 创建时间：2017-11-29 下午4:46:01 类说明
 */

public class T_EndTime {

  public String getEndTime(String keStartTime, String keLongTime) {
    // int longTime = Integer.valueOf(keLongTime).intValue();
    // int hour = longTime/60;

    DateFormat df = new SimpleDateFormat("HH:mm:ss");
    try {
      // 1开始时间转成秒
      Date dt2 = df.parse(keStartTime);
      int hours = dt2.getHours();
      int min = dt2.getMinutes();
      int second = dt2.getSeconds();

      int countSecond = hours * 3600 + min * 60 + second;
      int old_startTime = countSecond;
      // 2上课时长转成秒
      int old_longTime_fen = Integer.valueOf(keLongTime).intValue();
      int old_longTime = old_longTime_fen * 60;// KeLongTime 分钟转秒
      // 3相加得出结束时间，秒的形式
      int old_endTime = old_startTime + old_longTime;
      // 4把秒转换成时
      // 5剩下的秒转换成分
      // 6剩下的秒
      int h = 0;
      int d = 0;
      int s = 0;
      int temp = old_endTime % 3600;
      if (old_endTime > 3600) {
        h = old_endTime / 3600;
        if (temp != 0) {
          if (temp > 60) {
            d = temp / 60;
            if (temp % 60 != 0) {
              s = temp % 60;
            }
          } else {
            s = temp;
          }
        }//如果temp==0就表示是整时了，时分秒都是默认的0
      } else {
        d = old_endTime / 60;
        if (old_endTime % 60 != 0) {
          s = old_endTime % 60;
        }
      }
      // 7拼接出需要的时分秒格式字符串
      String hds = h + ":" + d + ":" + s;
      return hds;

    } catch (ParseException e) {
      e.printStackTrace();
      System.out.println("T_EndTime字符串转成日期错误");
      String hds = "00" + ":" + "00" + ":" + "00";
      return hds;
    }

  }// end method

}// end class
