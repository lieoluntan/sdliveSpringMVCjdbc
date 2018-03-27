package com.sdlive.system.service;

import java.util.ArrayList;
import java.util.List;

import com.sdlive.system.model.UserPK_Role;
import com.sdlive.utility.M_msg;

/**
 * 
 * @author 罗成峰
 * @date 2017-12-27下午1:06:20
 * @version 1.0
 */
public interface UserPK_RoleService {

	M_msg getMsg();

	ArrayList<UserPK_Role>getListByuse(String userPkid);

	String insert (UserPK_Role userPK_Role);

	String delete(String uuid);
	
	String deleteByuse(String userPKid);
	
	ArrayList<UserPK_Role>getListByContr(String roleid);
	
	/**
	 * 
	 * @param list
	 * 
	 * @return
	 */
	List<String>getByUserid(List<String>list);
	
}
