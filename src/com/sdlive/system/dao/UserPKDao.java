package com.sdlive.system.dao;

import java.util.ArrayList;
import java.util.List;

import com.sdlive.system.model.UserPK;

/**
 * 树袋老师
 * 
 * @author 作者 xpp
 * @version 创建时间：2017-11-24 下午12:42:03 类说明
 */

public interface UserPKDao {

	boolean insert(UserPK userPK);

	boolean delete(String uuid);

	boolean deleteUserRole(String uuid);

	boolean update(UserPK userPK);

	public UserPK getByUuid(String uuid);

	ArrayList<UserPK> getList();

	UserPK getByuLogUser(String uLogUser);
	
	UserPK getByUname(String uName);

	ArrayList<UserPK> getList2(String uLogUser);

	boolean insertUserRole(UserPK userPK);
	
	List<String> getRole(String userPkid);

}// end interface
