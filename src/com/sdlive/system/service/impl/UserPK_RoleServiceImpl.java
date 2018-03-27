package com.sdlive.system.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.sdlive.system.dao.RoleDao;
import com.sdlive.system.dao.UserPKDao;
import com.sdlive.system.dao.UserPK_RoleDao;
import com.sdlive.system.dao.impl.RoleDaoImpl;
import com.sdlive.system.dao.impl.UserPKDaoImpl;
import com.sdlive.system.dao.impl.UserPK_RoleDaoImpl;
import com.sdlive.system.model.Role;
import com.sdlive.system.model.UserPK;
import com.sdlive.system.model.UserPK_Role;
import com.sdlive.system.service.UserPK_RoleService;
import com.sdlive.utility.DBUtility;
import com.sdlive.utility.M_msg;

/**
 * 
 * @author 罗成峰
 * @date 2017-12-27下午2:09:51
 * @version 1.0
 */
public class UserPK_RoleServiceImpl implements UserPK_RoleService{
	private Connection connection;
	private UserPK_RoleDao userpk_roledao = new UserPK_RoleDaoImpl();
	private UserPKDao userpkdao = new UserPKDaoImpl();
	private RoleDao roledao = new RoleDaoImpl();
	public M_msg m_msg = new M_msg();
	@Override
	public M_msg getMsg() {
		// TODO Auto-generated method stub
		return m_msg;
	}

	@Override
	public String insert(UserPK_Role userPK_Role) {
		// TODO Auto-generated method stub
		String userPkUuid = userPK_Role.getUserPkid();
		String roleUuid = userPK_Role.getRoleid();
		//判断数据库中是否存在重复关系
		ArrayList<UserPK_Role> userList = userpk_roledao.getListByuse(userPkUuid);
		
		for(UserPK_Role one : userList){
			String oneRoleUuid = one.getRoleid();
			if(roleUuid.equals(oneRoleUuid)){
				String msg = "不保存,数据库中已存在相同关系记录";
				m_msg.setAddMsg(msg);
				return msg;
			}
		}
		//1判断结束
		userPK_Role.setUuid(null);
		userPK_Role.setUuid(UUID.randomUUID().toString());
		System.out.println("^^在UserPK_RoleServiceImpl收到数据:" + userPK_Role.toString() + "收到结束!");
		return roleUuid;
	}

	@Override
	public String delete(String uuid) {
		// TODO Auto-generated method stub
		if(uuid!=null&&uuid!=""){
			boolean daoFlag = userpk_roledao.delete(uuid);
			userpkdao.delete(uuid);
			if(daoFlag){
				return uuid;
			}else{
				return "删除不成功,dao层执行有出错地方,请联系管理员";
			}
		}else{
			String msg="userPK_RoleServiceImpl delete方法中的uuid为空,或格式不正确,请重新选择";
			System.out.println("msg");
			return msg;
		}
	
	}

	@Override
	public String deleteByuse(String userPKid) {
		// TODO Auto-generated method stub
		if(userPKid!=null&&userPKid!=""&&userPKid.length() !=0){
			boolean daoFlag = userpk_roledao.deleteByuse(userPKid);
			
			if(daoFlag){
				return userPKid;
			}else{
				return "删除不成功,dao层执行有出错地方,请联系管理员";
				//System.out.println("@");
			}
		}else{
			String msg = "userPK_RoleServiceImpl delete方法中的uuid为空,或格式不正确,请重新选择";
			System.out.println(msg);
			return msg;
		}
}

	@Override
	public ArrayList<UserPK_Role> getListByContr(String roleid) {
		// TODO Auto-generated method stub
		ArrayList<UserPK_Role> list = userpk_roledao.getListByRole(roleid);
		ArrayList<UserPK_Role> reList = new ArrayList<UserPK_Role>();
		for(UserPK_Role one : list){
			
			String UserPkid = one.getUserPkid();
			String Roleid = one.getRoleid();
			UserPK user = userpkdao.getByUuid(UserPkid);
			Role role = roledao.getByUuid(roleid);
			String userName = user.getuName();
			String roleName = role.getName();
			
			UserPK_Role copyOne = new UserPK_Role();
			copyOne.setUserPkid(UserPkid);
			copyOne.setRoleid(Roleid);
			
			String oldUuid = one.getUuid();
			copyOne.setUuid(oldUuid);
			reList.add(copyOne);
		}
		return reList;
	}
	@Override
	public ArrayList<UserPK_Role> getListByuse(String userPkid) {
		// TODO Auto-generated method stub
		ArrayList<UserPK_Role> list = userpk_roledao.getListByRole(userPkid);
		ArrayList<UserPK_Role> reList = new ArrayList<UserPK_Role>();
		for(UserPK_Role one : list){
			//从用户表和角色表中找到用户名,用户表修改了名称,关联表也知道
			String UserPkid = one.getUserPkid();
			String Roleid = one.getRoleid();
			UserPK user = userpkdao.getByUuid(UserPkid);
			Role role = roledao.getByUuid(userPkid);
			String userName = user.getuName();
			String roleName = role.getName();
			
			UserPK_Role copyOne = new UserPK_Role();
			copyOne.setUserPkid(UserPkid);
			copyOne.setRoleid(Roleid);
			
			String oldUuid = one.getUuid();
			copyOne.setUuid(oldUuid);
			reList.add(copyOne);
	}
		return reList;
     }

	@Override
	public List<String> getByUserid(List<String> list) {
		// TODO Auto-generated method stub
		UserPK_Role userpkRoleResult = new UserPK_Role();
		Statement statement = null;
		ResultSet rs = null;
		List<String>rsList  = new ArrayList<String>();
		
		for(String userpkid :list){
			
			try {
				connection = DBUtility.open();
				statement  = connection.createStatement();
				rs = statement.executeQuery("select * from t_userpk_role WHERE userpkid = "
						+ "'" + userpkid + "'");
				while(rs.next()){
					UserPK_Role userpk_role = new UserPK_Role();
					userpk_role.setUuid(rs.getString("uuid"));
					userpk_role.setUserPkid(rs.getString("userpkid"));
					userpk_role.setRoleid(rs.getString("roleid"));
					
					userpkRoleResult = userpk_role;
					rsList.add(userpkRoleResult.getRoleid());
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("UserPK_RoleImpl的getByUuid查询失败");
			}finally{
				DBUtility.close(rs, statement, connection);
			}
			for(String a : rsList){
				System.out.println(a);
			}
		}
		//去角色资源表名字重复值
		Set set = new HashSet();
		List newRsList = new ArrayList();
		for(String cb : rsList){
			if(set.add(cb)){
				newRsList.add(cb);
			}
		}
		return newRsList;
	}
}