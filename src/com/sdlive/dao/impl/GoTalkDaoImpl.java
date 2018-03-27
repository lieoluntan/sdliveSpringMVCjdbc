package com.sdlive.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sdlive.dao.GoTalkDao;
import com.sdlive.model.Department;
import com.sdlive.model.GoTalk;
import com.sdlive.utility.DBUtility;

/**
 *树袋老师
 * @author 作者 毕书富
 * @version 创建时间:2018-3-23 下午6:28:00
 * 类说明
 */
@Repository("GoTalkDaoImpl")
public class GoTalkDaoImpl implements GoTalkDao{
	private Connection connection;
	boolean daoFlag = false;

	public GoTalkDaoImpl() {
		System.out.println("connection对象在GoTalkDaoImpl连接!");
	}

	@Override
	public boolean insert(GoTalk goTalk) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		connection = DBUtility.open();
		
		try {
			preparedStatement = connection
					.prepareStatement("insert INTO t_gotalk(uuid,name,username,usertype,ts,userpassword,extradata,jumpurl,domain,auth,serialTalk,pid,servername,openAndclose,urlRemark) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, goTalk.getUuid());
			preparedStatement.setString(2, goTalk.getName());
			preparedStatement.setString(3, goTalk.getUsername());
			preparedStatement.setInt(4, goTalk.getUsertype());
			preparedStatement.setInt(5, goTalk.getTs());
			preparedStatement.setString(6, goTalk.getUserpassword());
			preparedStatement.setString(7, goTalk.getExtradata());
			preparedStatement.setString(8, goTalk.getJumpurl());
			preparedStatement.setString(9, goTalk.getDomain());
			preparedStatement.setString(10, goTalk.getAuth());
			preparedStatement.setInt(11, goTalk.getSerialTalk());
			preparedStatement.setInt(12, goTalk.getPid());
			preparedStatement.setString(13, goTalk.getServername());
			preparedStatement.setString(14, "open");
			preparedStatement.setString(15, goTalk.getUrlRemark());
			preparedStatement.executeUpdate();
			System.out.println("^^在执行GoTalkDaoImpl中的添加insert");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("^^在执行GoTalkDaoImpl中insert,出现sql语法执行错误，请联系管理员!");
			e.printStackTrace();
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
		}
	}

	@Override
	public boolean delete(String uuid) {
		// TODO Auto-generated method stub
		PreparedStatement PSdelete = null;
		connection = DBUtility.open();
		
		try {
			PSdelete = connection
					.prepareStatement("delete from t_gotalk where uuid='"
							+ uuid + "'");
			PSdelete.executeUpdate();
			System.out.println("^^在执行DepartmGoTalkDaoImplentDaoImpl中的删除delete");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("^^在执行GoTalkDaoImpl中delete,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, PSdelete, connection);
		}
	}

	@Override
	public boolean updateOnOff(String uuid, String openAclose) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		connection = DBUtility.open();
		try {
			preparedStatement = connection
					.prepareStatement("update t_gotalk set openAndclose='"
							+ openAclose + "' where uuid='" + uuid + "'");
			preparedStatement.executeUpdate();
			System.out.println("在执行GoTalkDaoImpl中的修改updateOnOff");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("^^在执行GoTalkDaoImpl中updateOnOff,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
		}
	}

	@Override
	public GoTalk getByUuid(String uuid) {
		// TODO Auto-generated method stub
		GoTalk goTalkResult = new GoTalk();
		Statement statement = null;
		ResultSet rs = null;
		connection = DBUtility.open();
		try {
			statement = connection.createStatement();
			rs = statement
					.executeQuery("select * from t_gotalk where uuid='"
							+ uuid + "'");
			while (rs.next()) {
				GoTalk goTalk = new GoTalk();
				goTalk.setName(rs.getString("name"));
				goTalk.setUuid(rs.getString("uuid"));
				goTalk.setUrlRemark(rs.getString("urlRemark"));
				goTalk.setOpenAndclose(rs.getString("openAndclose"));
				
				goTalk.setDomain(rs.getString("domain"));
				goTalk.setSerialTalk(rs.getInt("serialTalk"));
				goTalk.setUsername(rs.getString("username"));
				goTalk.setUsertype(rs.getInt("usertype"));
				goTalk.setPid(rs.getInt("pid"));
				goTalk.setTs(rs.getInt("ts"));
				goTalk.setAuth(rs.getString("auth"));
				goTalk.setUserpassword(rs.getString("userpassword"));
				goTalk.setServername(rs.getString("servername"));
				goTalk.setExtradata(rs.getString("extradata"));
				goTalk.setJumpurl(rs.getString("jumpurl"));
				goTalkResult = goTalk;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("GoTalkDaoImpl的查询单个一键登录直播");
			GoTalk goTalk = new GoTalk();
			goTalk.setUuid("GoTalkDaoImpl失败返回的uuid");
			return goTalk;
		} finally {
			DBUtility.close(rs, statement, connection);
		}
		return goTalkResult;
	}

	@Override
	public boolean update(GoTalk goTalk) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		connection = DBUtility.open();
		try {
			preparedStatement = connection
					.prepareStatement("UPDATE t_gotalk SET name=?,username=?,usertype=?,ts=?,userpassword=?,extradata=?,jumpurl=? WHERE uuid=?");
			preparedStatement.setString(1, goTalk.getName());
			preparedStatement.setString(2, goTalk.getUsername());
			preparedStatement.setInt(3, goTalk.getUsertype());
			preparedStatement.setInt(4, goTalk.getTs());
			preparedStatement.setString(5, goTalk.getUserpassword());
			preparedStatement.setString(6, goTalk.getExtradata());
			preparedStatement.setString(7, goTalk.getJumpurl());
			preparedStatement.setString(8, goTalk.getUuid());
			preparedStatement.executeUpdate();
			System.out.println("^^在执行GoTalkDaoImpl中的修改update");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("^^在执行GoTalkDaoImpl中update,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
		}
	}

	@Override
	public List<GoTalk> getGoTalkByUuid(GoTalk goTalk) {
		// TODO Auto-generated method stub
		List<GoTalk> goTalkList=new ArrayList<GoTalk>();
		Statement statement=null;
		ResultSet rs=null;
		connection = DBUtility.open();
		try {
			statement=connection.createStatement();
			rs=statement.executeQuery("select * from t_gotalk where uuid='"+goTalk.getUuid()+"'");
			while(rs.next()){
				GoTalk goT=new GoTalk();
				goT.setUuid(rs.getString("uuid"));
				goTalkList.add(goT);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("GoTalkDaoImpl中的getGoTalkByUuid查询失败");
		}finally{
			DBUtility.close(rs, statement, connection);
		}
		
		return goTalkList;
	}

	@Override
	public ArrayList<GoTalk> getList() {
		// TODO Auto-generated method stub
		ArrayList<GoTalk> goTalkList = new ArrayList<GoTalk>();
		Statement statement = null;
		ResultSet rs = null;
		connection = DBUtility.open();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_gotalk");
			while (rs.next()) {
				GoTalk goTalk = new GoTalk();
				
				goTalk.setUuid(rs.getString("uuid"));
				goTalk.setName(rs.getString("name"));
				goTalk.setUrlRemark(rs.getString("urlRemark"));
				goTalk.setOpenAndclose(rs.getString("openAndclose"));
				
				goTalk.setDomain(rs.getString("domain"));
				goTalk.setSerialTalk(rs.getInt("serialTalk"));
				goTalk.setUsername(rs.getString("username"));
				goTalk.setUsertype(rs.getInt("usertype"));
				goTalk.setPid(rs.getInt("pid"));
				goTalk.setTs(rs.getInt("ts"));
				goTalk.setAuth(rs.getString("auth"));
				goTalk.setUserpassword(rs.getString("userpassword"));
				goTalk.setServername(rs.getString("servername"));
				goTalk.setExtradata(rs.getString("extradata"));
				goTalk.setJumpurl(rs.getString("jumpurl"));
				
				goTalkList.add(goTalk);
			}
			return goTalkList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("GoTalkDaoImpl的查询部门列表失败");
			GoTalk goTalk = new GoTalk();
			goTalk.setUuid("GoTalkDaoImpl查询失败返回的uuid");
			ArrayList<GoTalk> listGoTalk = new ArrayList<GoTalk>();
			listGoTalk.add(goTalk);
			return goTalkList;
		} finally {
			DBUtility.close(rs, statement, connection);
		}
	}

}
