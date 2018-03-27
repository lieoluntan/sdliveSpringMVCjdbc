package com.sdlive.system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sdlive.system.dao.RoleDao;
import com.sdlive.system.model.Role;
import com.sdlive.system.model.UserPK;
import com.sdlive.system.model.UserPK_Role;
import com.sdlive.utility.DBUtility;

/**
 * 
 * @author 罗成峰
 * @date 2017-12-27上午11:06:28
 * @version 1.0
 */
public class RoleDaoImpl implements RoleDao{
	
	 private Connection connection;
	  boolean dao = false;
	  public RoleDaoImpl() {
//	    connection = DBUtility.open();
	    System.out.println("connection对象在RoleDaoImpl连接!");
	  }
	  @Override
	public boolean insert(Role role) {
		// TODO Auto-generated method stub
		  PreparedStatement preparedStatement = null;
		 try {
			 connection = DBUtility.open();
		      preparedStatement = connection.prepareStatement("insert into t_role(uuid,name,remark) values (?,?,?)");
		      preparedStatement.setString(1, role.getUuid());
		      preparedStatement.setString(2, role.getName());
		      preparedStatement.setString(3, role.getRemark());
		      preparedStatement.executeUpdate();
		      System.out.println("^^在执行RoleDaoImpl中的添加insert");
		      dao = true;
		      return dao;
		    } catch (SQLException e) {
		      System.out.println("^^在执行RoleDaoImpl中insert,出现sql语法执行错误，请联系管理员!");
		      e.printStackTrace();
		      dao = false;
		      return dao;
		    }finally{
		      ResultSet rs = null; 
		      DBUtility.close(rs, preparedStatement, connection);   
		     }
	}
	  @Override
	public boolean delete(String uuid) {
		// TODO Auto-generated method stub
		 PreparedStatement PSdelete = null; 
		    try {
		      connection = DBUtility.open();
		       PSdelete = connection.prepareStatement("DELETE FROM t_role WHERE uuid = ? ");
		      PSdelete.setString(1, uuid);
		      PSdelete.executeUpdate();
		      System.out.println("^^在执行RoleDaoImpl中的删除delete");
		      dao = true;
		      return dao;
		    } catch (SQLException e) {
		      e.printStackTrace();
		      System.out.println("^^在执行RoleDaoImpl中delete,出现sql语法执行错误，请联系管理员!");
		      dao = false;
		      return dao;
		    }finally{
		      ResultSet rs = null; 
		      DBUtility.close(rs, PSdelete, connection);   
		     }
	}

	@Override
	public boolean update(Role Role) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null; //关闭数据库连接insert和update和delete用到
	    try {
	      connection = DBUtility.open();//打开数据库连接
	       preparedStatement = connection
	          .prepareStatement("UPDATE t_role SET name = ? , remark = ? WHERE uuid = ? ");
	      // Parameters start with 1
	      preparedStatement.setString(1, Role.getName());
	      preparedStatement.setString(2, Role.getRemark());
	      preparedStatement.setString(3, Role.getUuid());
	      preparedStatement.executeUpdate();

	      System.out.println("^^在执行RoleDaoImpl中的修改update");
	      dao = true;
	      return dao;
	    } catch (SQLException e) {
	      e.printStackTrace();
	      System.out.println("^^在执行RoleDaoImpl中update,出现sql语法执行错误，请联系管理员!");
	      dao = false;
	      return dao;
	    }finally{
	      ResultSet rs = null; 
	      DBUtility.close(rs, preparedStatement, connection);   
	     }//finally关闭jdbc与数据库连接  
	}

	@Override
	public Role getByUuid(String uuid) {
		 // TODO Auto-generated method stub
	    Role RoleResult = new Role();
	    Statement statement = null;//finally关闭数据库连接  
	    ResultSet rs = null;//关闭数据库连接get和getlist会用到
	    try {
	      connection = DBUtility.open();//打开数据库连接
	         statement = connection.createStatement();
	         rs = statement.executeQuery("select * from t_role WHERE uuid ="+"'"+uuid+"'");
	        while (rs.next()) {
	          Role role = new Role();
	          role.setUuid(rs.getString("uuid"));
	          role.setName(rs.getString("name"));
	          RoleResult=role;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("RoleDaoImpl的getByUuid查询失败");
	        Role roleX = new Role();
	        roleX.setUuid("RoleDaoImpl失败返回的uuid");
	        return roleX;
	    }finally{   
	      DBUtility.close(rs, statement, connection);   
	     }
	    return RoleResult;
	}
	
	@Override
	public ArrayList<Role> getList() {
		ArrayList<Role> roleList = new ArrayList<Role>();
	    Statement statement = null;
	    
	    ResultSet rs = null;
	    try {
	      connection = DBUtility.open();
	         statement = connection.createStatement();
	         rs = statement.executeQuery("select * from t_role");
	         while (rs.next()) {
	          Role role = new Role();
	          role.setUuid(rs.getString("uuid"));
	          role.setName(rs.getString("name"));
	          role.setRemark(rs.getString("remark"));
	          roleList.add(role);
	        }
	        
	        return roleList;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("RoleDaoImpl的getList查询失败");
	        Role roleX = new Role();
	        roleX.setUuid("RoleDaoImpl的getList失败返回的uuid");
	        ArrayList<Role> roleListX = new ArrayList<Role>();
	        roleListX.add(roleX);
	        return roleListX;
	    }finally{   
	      DBUtility.close(rs, statement, connection);   
	     }
	}
	@Override
	public List<String> getRole(String uuid) {
		// TODO Auto-generated method stub
		List<String> roleList = new ArrayList<String>();
		Role roleResult = new Role();
		Statement statement = null;
		ResultSet rs = null;// 关闭数据库连接get和getlist会用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			statement = connection.createStatement();
			rs = statement
					.executeQuery("select * from t_userPK_role WHERE userPkid ="
							+ "'" + uuid + "'");
			while (rs.next()) {
				UserPK_Role userPKRole = new UserPK_Role();
				userPKRole.setUuid(rs.getString("uuid"));
				userPKRole.setUserPkid(rs.getString("userPkid"));
				userPKRole.setRoleid(rs.getString("roleid"));
				
				roleList.add(userPKRole.getRoleid());
			}	
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("t_userPK的getByUuid查询失败");
			
		} finally {
			DBUtility.close(rs, statement, connection);
			System.out.println("getByuLogUser 调用了关闭数据库连接");
		}// finally关闭jdbc与数据库连接
		return roleList;
	}
	@Override
	public boolean insertRoleRs(Role role) {
		// TODO Auto-generated method stub
		 PreparedStatement preparedStatement = null;
		 try {
			 for (String rsid : role.getRsList()) {
				
			
			 connection = DBUtility.open();
		      preparedStatement = connection.prepareStatement("insert into t_role_resource(uuid,roleid,resourceid) values (?,?,?)");
		      preparedStatement.setString(1, UUID.randomUUID().toString());
		      preparedStatement.setString(2, role.getUuid());
		      preparedStatement.setString(3, rsid);
		      preparedStatement.executeUpdate();
		      System.out.println("^^在执行RoleDaoImpl中的添加insert");
		      dao = true;
			 }
		      return dao;
			
		    } catch (SQLException e) {
		      System.out.println("^^在执行RoleDaoImpl中insert,出现sql语法执行错误，请联系管理员!");
		      e.printStackTrace();
		      dao = false;
		      return dao;
		    }finally{
		      ResultSet rs = null; 
		      DBUtility.close(rs, preparedStatement, connection);   
		     }
	}
	@Override
	public Role getByName(String name) {
		// TODO Auto-generated method stub
		Role roleResult = new Role();
		Statement statement = null;
		ResultSet rs = null;
		
		try {
			connection = DBUtility.open();// 打开数据库连接
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_role WHERE name ="
					+ "'" + name + "'");
			while(rs.next()){
				Role role = new Role();
				role.setUuid(rs.getString("uuid"));
				role.setName(rs.getString("name"));
				
				roleResult = role;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("t_userPK的getByUuid查询失败");
			Role aX = new Role();
			aX.setUuid("t_role失败返回的uuid");
			return aX;
		}finally{
			DBUtility.close(rs, statement, connection);
			System.out.println("getByuuid 调用了关闭数据库连接");
		}
		return roleResult;
	}
}


