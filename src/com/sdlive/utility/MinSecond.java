package com.sdlive.utility;

import java.util.Date;

/**
 *树袋老师
 * @author 作者 xpp
 * @version 创建时间：2017-10-25 上午10:48:22
 * 
 * 类说明
 * 时分秒 变成 毫秒
 * 
 * 格式说明
 * Date格式转变成Long格式
 */

public class MinSecond {
  //预留变量
  String msg;
 
  public long getMinSecond(Date d) {
    int hours = d.getHours();
    int min = d.getMinutes();
    int second = d.getSeconds();
    
    int countSecond = hours*3600+min*60+second;
    long countMinSecond = (long)countSecond;
    return countMinSecond;
  }//end method getMsg



}//end class MinSecond
