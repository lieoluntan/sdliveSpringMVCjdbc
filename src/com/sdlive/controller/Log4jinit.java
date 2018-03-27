package com.sdlive.controller;

import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.sdlive.controller.Log4jinit;

/**
 *树袋老师
 * @author 作者 xpp
 * @version 创建时间：2018-2-5 下午1:45:04
 * 类说明
 */

public class Log4jinit extends HttpServlet {
  static Logger logger = Logger.getLogger(Log4jinit.class);

  public Log4jinit() {

  }
  
      public void init() {
//        String file = getInitParameter("log4j");
        String prefix = getServletContext().getRealPath("/");   
        String file = getInitParameter("log4j"); 
        if (file != null) {
            logger.error("----开始找log4j文件sdlive");
//            PropertyConfigurator.configure(file);
            PropertyConfigurator.configure(prefix+file);  
            System.out.println("----加载自定义的log4j.properties成功了sdlive");
    
        }
     }//end method  init()

}//end class
