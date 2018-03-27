package com.sdlive.system.dao;

import java.util.ArrayList;

import com.sdlive.system.model.UserPK_Role;
/**
 * 
 * @author 罗成峰
 * @date 2017-12-27下午1:41:41
 * 
 */
public interface UserPK_RoleDao {
	/**
	 * 
	 * @param userPkid
	 * @return
	 */
	ArrayList<UserPK_Role>getListByuse(String userPkid);
	/**
	 * 
	 * @param userPK_Role
	 * @return
	 */
	boolean insert(UserPK_Role userPK_Role);
	/**
	 * 
	 * @param uuid
	 * @return
	 */
	boolean delete(String uuid);
	/**
	 * 
	 * @param userPkid
	 * @return
	 */
	boolean deleteByuse(String userPkid);
	/**
	 * 
	 * @param roleid
	 * @return
	 */
	 public ArrayList<UserPK_Role>getListByRole(String roleid);
	ArrayList<UserPK_Role> getListByRole(String roleid, String userPkid);
}
