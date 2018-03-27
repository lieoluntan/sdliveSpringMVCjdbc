package com.sdlive.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class DBUtility {
	private static Connection connection = null;
	
	private static DataSource ds = null;
	  
	  static{
	      try{

	          Context initCtx = new InitialContext();
	          Context envCtx = (Context)initCtx.lookup("java:comp/env");
	          ds = (DataSource) envCtx.lookup("aaaPoolName");    
	//根据<Resource>元素的name属性值到JNDI容器中检索连接池对象        
	      }catch (Exception e) {
	          System.out.println("连接池：第一步,找context异常");
	          throw new ExceptionInInitializerError(e);
	      }
	  }

    public static Connection getConnection() {
        if (connection != null)
            return connection;
        else {
            try {
            	Properties prop = new Properties();
                InputStream inputStream = DBUtility.class.getClassLoader().getResourceAsStream("/config.properties");
                prop.load(inputStream);
                String driver = prop.getProperty("driver");
                String url = prop.getProperty("url");
                String user = prop.getProperty("user");
                String password = prop.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return connection;
        }

    }//end method
    
    
    /** 
     *  依次关闭ResultSet、Statement、Connection 
     *  若对象不存在则创建一个空对象 
     * @param rs 
     * @param st 
     * @param pst 
     * @param conn 
     */  
    public static void close(ResultSet rs, Statement st, Connection conn) {
      if(rs!=null) {
        try{
            rs.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }//end if(rs!=null)
    if(st!=null) {
        try{
            st.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }//end if(st!=null)
    if(conn!=null) {
        try{
            conn.close();
            System.out.println("连接池关闭");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }//end if(conn!=null)
}// end method close
    
    public static Connection open() {

      System.out.println("连接池：打开");
      Connection aconn = null;
        try {
          return ds.getConnection();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }  //利用数据源获取连接
      return aconn;
  }//end method

}//end class
