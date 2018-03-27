package com.sdlive.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.sdlive.dao.TalkRoomDao;
import com.sdlive.model.Department;
import com.sdlive.model.TalkRoom;
import com.sdlive.utility.DBUtility;

@Repository("talkRoomDaoImpl")
public class TalkRoomDaoImpl implements TalkRoomDao{

	private Connection connection;
	boolean daoFlag = false;

	public TalkRoomDaoImpl() {
		System.out.println("connection对象在TalkRoomDaoImpl连接!");
	}
	
	@Override
	public boolean insert(TalkRoom tr) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		connection = DBUtility.open();
		
		try {
			preparedStatement = connection
					.prepareStatement("insert into t_talkroom(uuid,urlRemark,openAndclose,keyTalk,serialTalk,roomNameTalk,roomtypeTalk,starttimeTalk,endtimeTalk,chairmanpwd,assistantpwd,patrolpwd,passwordrequired,confuserpwd,videotype,videoframerate,confusernum,autoopenav,createDate,modifyDate,createPeople,modifyPeople) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, tr.getUuid());
			preparedStatement.setString(2, tr.getUrlRemark());
			preparedStatement.setString(3, "open");
			preparedStatement.setString(4, tr.getKeyTalk());
			preparedStatement.setInt(5, tr.getSerialTalk());
			preparedStatement.setString(6, tr.getRoomNameTalk());
			preparedStatement.setInt(7, tr.getRoomtypeTalk());
			preparedStatement.setInt(8, tr.getStarttimeTalk());
			preparedStatement.setInt(9, tr.getEndtimeTalk());
			preparedStatement.setString(10, tr.getChairmanpwd());
			preparedStatement.setString(11, tr.getAssistantpwd());
			preparedStatement.setString(12, tr.getPatrolpwd());
			preparedStatement.setInt(13, tr.getPasswordrequired());
			preparedStatement.setString(14, tr.getConfuserpwd());
			preparedStatement.setInt(15, tr.getVideotype());
			preparedStatement.setInt(16, tr.getVideoframerate());
			preparedStatement.setInt(17, tr.getConfusernum());
			preparedStatement.setInt(18, tr.getAutoopenav());
			preparedStatement.setString(19, tr.getCreateDate());
			preparedStatement.setString(20, tr.getModifyDate());
			preparedStatement.setString(21, tr.getCreatePeople());
			preparedStatement.setString(22, tr.getModifyPeople());
			preparedStatement.executeUpdate();
			System.out.println("^^在执行TalkRoomDaoImpl中的添加insert");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("^^在执行TalkRoomDaoImpl中insert,出现sql语法执行错误，请联系管理员!");
			e.printStackTrace();
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
		}
	}

	@Override
	public boolean update(TalkRoom tr) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		connection = DBUtility.open();
		try {
			preparedStatement = connection
					.prepareStatement("update t_talkroom set roomNameTalk=?,roomtypeTalk=?,starttimeTalk=?,endtimeTalk=?,chairmanpwd=?,assistantpwd=?,patrolpwd=?,passwordrequired=?,confuserpwd=? where serialTalk=?");
			preparedStatement.setString(1, tr.getRoomNameTalk());
			preparedStatement.setInt(2, tr.getRoomtypeTalk());
			preparedStatement.setInt(3, tr.getStarttimeTalk());
			preparedStatement.setInt(4, tr.getEndtimeTalk());
			preparedStatement.setString(5, tr.getChairmanpwd());
			preparedStatement.setString(6, tr.getAssistantpwd());
			preparedStatement.setString(7, tr.getPatrolpwd());
			preparedStatement.setInt(8, tr.getPasswordrequired());
			preparedStatement.setString(9, tr.getConfuserpwd());
			preparedStatement.setInt(10, tr.getSerialTalk());
			preparedStatement.executeUpdate();
			System.out.println("^^在执行TalkRoomDaoImpl中的修改update");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("^^在执行TalkRoomDaoImpl中update,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
		}
	}

	@Override
	public boolean delete(TalkRoom tr) {
		// TODO Auto-generated method stub
		PreparedStatement PSdelete = null;
		connection = DBUtility.open();
		
		try {
			PSdelete = connection
					.prepareStatement("delete from t_talkroom where serialTalk='"
							+ tr.getSerialTalk() + "'");
			PSdelete.executeUpdate();
			System.out.println("^^在执行TalkRoomDaoImpl中的删除delete");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("^^在执行TalkRoomDaoImpl中delete,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, PSdelete, connection);
		}
	}

	@Override
	public ArrayList<TalkRoom> getList() {
		// TODO Auto-generated method stub
		ArrayList<TalkRoom> departmentList = new ArrayList<TalkRoom>();
		Statement statement = null;
		ResultSet rs = null;
		connection = DBUtility.open();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_talkroom");
			while (rs.next()) {
				TalkRoom tr1 = new TalkRoom();
				tr1.setUuid(rs.getString("uuid"));
				tr1.setUrlRemark(rs.getString("urlRemark"));
				tr1.setOpenAndclose(rs.getString("openAndclose"));
				tr1.setKeyTalk(rs.getString("keyTalk"));
				tr1.setSerialTalk(rs.getInt("serialTalk"));
				tr1.setRoomNameTalk(rs.getString("roomNameTalk"));
				tr1.setRoomtypeTalk(rs.getInt("roomtypeTalk"));
				tr1.setStarttimeTalk(rs.getInt("starttimeTalk"));
				tr1.setEndtimeTalk(rs.getInt("endtimeTalk"));
				tr1.setChairmanpwd(rs.getString("chairmanpwd"));
				tr1.setAssistantpwd(rs.getString("assistantpwd"));
				tr1.setPatrolpwd(rs.getString("patrolpwd"));
				tr1.setPasswordrequired(rs.getInt("passwordrequired"));
				tr1.setConfuserpwd(rs.getString("confuserpwd"));
				tr1.setVideotype(rs.getInt("videotype"));
				tr1.setVideoframerate(rs.getInt("videoframerate"));
				tr1.setConfusernum(rs.getInt("confusernum"));
				tr1.setAutoopenav(rs.getInt("autoopenav"));
				tr1.setCreateDate(rs.getString("createDate"));
				tr1.setModifyDate(rs.getString("modifyDate"));
				tr1.setCreatePeople(rs.getString("createPeople"));
				tr1.setModifyPeople(rs.getString("modifyPeople"));
				departmentList.add(tr1);
			}
			return departmentList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("TalkRoomDaoImpl的查询部门列表失败");
			TalkRoom department = new TalkRoom();
			department.setUuid("TalkRoomDaoImpl查询失败返回的uuid");
			ArrayList<TalkRoom> listDepartment = new ArrayList<TalkRoom>();
			listDepartment.add(department);
			return listDepartment;
		} finally {
			DBUtility.close(rs, statement, connection);
		}

	}

	@Override
	public boolean updateOnOff(String uuid, String oac) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		connection = DBUtility.open();
		try {
			preparedStatement = connection
					.prepareStatement("update t_talkroom set openAndclose='"
							+ oac + "' where uuid='" + uuid + "'");
			preparedStatement.executeUpdate();
			System.out.println("在执行TalkRoomDaoImpl中的修改update");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("^^在执行TalkRoomDaoImpl中updateOnOff,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
		}
	}

	@Override
	public ArrayList<TalkRoom> getOne(TalkRoom tr) {
		// TODO Auto-generated method stub
		ArrayList<TalkRoom> departmentList = new ArrayList<TalkRoom>();
		Statement statement = null;
		ResultSet rs = null;
		connection = DBUtility.open();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_talkroom where serialTalk='"+tr.getSerialTalk()+"'");
			while (rs.next()) {
				TalkRoom tr1 = new TalkRoom();
				tr1.setUuid(rs.getString("uuid"));
				tr1.setUrlRemark(rs.getString("urlRemark"));
				tr1.setOpenAndclose(rs.getString("openAndclose"));
				tr1.setKeyTalk(rs.getString("keyTalk"));
				tr1.setSerialTalk(rs.getInt("serialTalk"));
				tr1.setRoomNameTalk(rs.getString("roomNameTalk"));
				tr1.setRoomtypeTalk(rs.getInt("roomtypeTalk"));
				tr1.setStarttimeTalk(rs.getInt("starttimeTalk"));
				tr1.setEndtimeTalk(rs.getInt("endtimeTalk"));
				tr1.setChairmanpwd(rs.getString("chairmanpwd"));
				tr1.setAssistantpwd(rs.getString("assistantpwd"));
				tr1.setPatrolpwd(rs.getString("patrolpwd"));
				tr1.setPasswordrequired(rs.getInt("passwordrequired"));
				tr1.setConfuserpwd(rs.getString("confuserpwd"));
				tr1.setVideotype(rs.getInt("videotype"));
				tr1.setVideoframerate(rs.getInt("videoframerate"));
				tr1.setConfusernum(rs.getInt("confusernum"));
				tr1.setAutoopenav(rs.getInt("autoopenav"));
				tr1.setCreateDate(rs.getString("createDate"));
				tr1.setModifyDate(rs.getString("modifyDate"));
				tr1.setCreatePeople(rs.getString("createPeople"));
				tr1.setModifyPeople(rs.getString("modifyPeople"));
				departmentList.add(tr1);
			}
			return departmentList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("TalkRoomDaoImpl的查询部门列表失败");
			TalkRoom department = new TalkRoom();
			department.setUuid("TalkRoomDaoImpl查询失败返回的uuid");
			ArrayList<TalkRoom> listDepartment = new ArrayList<TalkRoom>();
			listDepartment.add(department);
			return listDepartment;
		} finally {
			DBUtility.close(rs, statement, connection);
		}

	}

}
