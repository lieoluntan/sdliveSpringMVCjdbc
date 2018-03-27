package com.sdlive.system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import com.sdlive.system.dao.UserPK_RoleDao;
import com.sdlive.system.model.UserPK_Role;
import com.sdlive.utility.DBUtility;
/**
 * 
 * @author 罗成峰
 * @date 2017-12-27下午5:05:48
 * 
 */
public class UserPK_RoleDaoImpl implements UserPK_RoleDao{
	private Connection connection;
	boolean daoFlag = false;
	
	public UserPK_RoleDaoImpl(){
		System.out.println("connection对象在UserPKDaoImpl连接!");
	}
	@Override
	public ArrayList<UserPK_Role> getListByuse(String userPkid) {
		// TODO Auto-generated method stub
		ArrayList<UserPK_Role> reList = new ArrayList<UserPK_Role>();
		Statement statement = null;	
		ResultSet rs = null;
		try {
			connection  = DBUtility.open();
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_userPk_role WHERE userPkid ="+"'"+userPkid+"'");
			while(rs.next()){
				UserPK_Role userpk_role = new UserPK_Role();
				userpk_role.setUuid(rs.getString("uuid"));
				userpk_role.setUserPkid(rs.getString("UserPkid"));
				userpk_role.setRoleid(rs.getString("roleid"));
				
				reList.add(userpk_role);
				
			}
			return reList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("UserPK_RoleDaoImpl的getListByuse查询失败");
			UserPK_Role errOne = new UserPK_Role();
			errOne.setUuid("UserPK_RoleDaoImpl的getListByuse失败返回的uuid");
			ArrayList<UserPK_Role> errList = new ArrayList<UserPK_Role>();
			errList.add(errOne);
			return errList;
		}finally{
			DBUtility.close(rs, statement, connection);
		}

}

	@Override
	
	public boolean insert(UserPK_Role userPK_Role) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null; //关闭数据库连接insert和update和delete用到
	    try {
	      connection = DBUtility.open();//打开数据库连接
	       preparedStatement = connection
	          .prepareStatement("insert into t_userPk_role(uuid,userPkid,Roleid) values (?,?,?)");
	      // Parameters start with 1
	      preparedStatement.setString(1, userPK_Role.getUuid());
	      preparedStatement.setString(2, userPK_Role.getUserPkid());
	      preparedStatement.setString(3, userPK_Role.getRoleid());
	      preparedStatement.executeUpdate();
	      System.out.println("^^在执行userPK_roleDaoImpl中的添加insert");
	      daoFlag = true;
	      return daoFlag;
	    } catch (SQLException e) {
	      System.out.println("^^在执行userPK_roleDaoImpl中insert,出现sql语法执行错误，请联系管理员!");
	      e.printStackTrace();
	      daoFlag = false;
	      return daoFlag;
	    }finally{
	      ResultSet rs = null; 
	      DBUtility.close(rs, preparedStatement, connection);   
	     }
	}

	@Override
	public boolean delete(String uuid) {
		// TODO Auto-generated method stub
		PreparedStatement PSdelete = null; //关闭数据库连接insert和update和delete用到
		
		try {
	    connection = DBUtility.open();//打开数据库连接
	    
	    // Parameters start with 1
	       PSdelete = connection.prepareStatement("DELETE FROM t_userPK_role WHERE uuid = ? ");
	      PSdelete.setString(1, uuid);
	      PSdelete.executeUpdate();

	      System.out.println("^^在执行userPK_roleDaoImpl中的删除delete");
	      daoFlag = true;
	      return daoFlag;
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("^^在执行userPK_roleDaoImpl中delete,出现sql语法执行错误，请联系管理员!");
	      daoFlag = false;
	      return daoFlag;
	    }finally{
	      ResultSet rs = null; 
	      DBUtility.close(rs, PSdelete, connection);  
	     }
	}

	@Override
	public boolean deleteByuse(String userPkid) {
		// TODO Auto-generated method stub
		  PreparedStatement PSdelete = null; //关闭数据库连接insert和update和delete用到
		    try {
		    	connection = DBUtility.open();//打开数据库连接
		      // Parameters start with 1
		      PSdelete = connection.prepareStatement("DELETE FROM t_userPK_role WHERE userPkid = ? ");
		      PSdelete.setString(1, userPkid);
		      PSdelete.executeUpdate();
		      System.out.println("^^在执行userPK_roleDaoImpl中的删除delete");
		      daoFlag = true;
		      return daoFlag;
		    } catch (SQLException e) {
		      e.printStackTrace();
		      System.out.println("^^在执行userPK_roleDaoImpl中deleteByuse,出现sql语法执行错误，请联系管理员!");
		      daoFlag = false;
		      return daoFlag;
		    }finally{
		      ResultSet rs = null; 
		      DBUtility.close(rs, PSdelete, connection);   
		     }
	}

	@Override
	public ArrayList<UserPK_Role> getListByRole(String roleid,String userPkid) {
		// TODO Auto-generated method stub
		   ArrayList<UserPK_Role> reList = new ArrayList<UserPK_Role>();
		    Statement statement = null;//finally关闭数据库连接  
		    ResultSet rs = null;//关闭数据库连接get和getlist会用到
		    try {
		      connection = DBUtility.open();//打开数据库连接
		         statement = connection.createStatement();
		         rs = statement.executeQuery("select * from t_userPK_role WHERE roleid ="+"'"+roleid+"'");
		        while (rs.next()) {
		        	UserPK_Role userPK_role = new UserPK_Role();
		        	userPK_role.setUuid(rs.getString("uuid"));
		        	userPK_role.setUserPkid(rs.getString("UserPkid"));
		        	userPK_role.setRoleid(rs.getString("Roleid"));
		        	reList.add(userPK_role);
		        }
		        
		        return reList;
		    } catch (SQLException e) {
		        e.printStackTrace();
		        System.out.println("userPK_roleDaoImpl的getListByuse查询失败");
		        UserPK_Role errOne = new UserPK_Role();
		        errOne.setUuid("userPK_roleDaoImpl的getListByuse失败返回的uuid");
		        ArrayList<UserPK_Role> errList = new ArrayList<UserPK_Role>();
		        errList.add(errOne);
		        return errList;
		    }finally{   
		      DBUtility.close(rs, statement, connection);   
		     }//finally关闭jdbc与数据库连接  

		  }
	@Override
	public ArrayList<UserPK_Role> getListByRole(String roleid) {
		// TODO Auto-generated method stub
		return null;
	}
}
