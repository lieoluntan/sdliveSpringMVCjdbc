package com.sdlive.system.dao;

import java.util.ArrayList;
import java.util.List;

import com.sdlive.system.model.Role;

/**
 * 
 * @author 罗成峰
 * @date 2017-12-26上午11:08:54
 * @version 1.0
 */
public interface RoleDao {
public boolean insertRoleRs(Role role);
	public boolean insert(Role Role);
	
	public boolean delete(String uuid);
	
	public boolean update(Role Role);
	
	public Role getByUuid (String uuid);
	
	public ArrayList<Role>getList();
	
	List<String>getRole(String uuid);
	
	Role getByName(String name);
}
