package com.sdlive.system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sdlive.system.dao.UserPKpassDao;
import com.sdlive.system.model.UserPK;
import com.sdlive.utility.DBUtility;

/*
 * 树袋老师
 * @author  刘鑫
 * @date  ‎2018‎年‎1‎月‎24‎日‎ ‎16‎:‎29‎:‎51
 * */

public class UserPKpassDaoImpl implements UserPKpassDao {
	private Connection connection;
	boolean daoFlag = false;
	
	public UserPKpassDaoImpl(){
		System.out.println("connection对象在UserPKpassDaoImpl连接!");
	}

	@Override
	public boolean updateUserPassword(UserPK userPK) {
		// TODO Auto-generated method stub
		PreparedStatement ps=null;
		try{
			connection = DBUtility.open();
			ps=connection.prepareStatement("update t_userPK set uPassWord=? where uLogUser=?");
			ps.setString(1, userPK.getuPassWord());
			ps.setString(2, userPK.getuLogUser());
			
			ps.executeUpdate();
			System.out.println("^^在执行t_userPKpass中的修改update");
			daoFlag = true;
			return daoFlag;
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("^^在执行t_userPK中update,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		}finally{
			ResultSet rs = null;
			DBUtility.close(rs, ps, connection);
			System.out.println("userPKpassDao updatePassword方法 调用了关闭数据库连接");
		}

	}

	/**
	 * @param args
	 */
	

}
