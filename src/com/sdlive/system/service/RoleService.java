package com.sdlive.system.service;

import java.util.ArrayList;
import java.util.List;

import com.sdlive.system.model.Role;

/**
 * 
 * @author 罗成峰
 * @date 2017-12-27上午11:06:59
 * @version 1.0
 */
public interface RoleService {

	String insert(Role Role);
	
	String delete(String uuid);
	
	String update(Role Role);
	
	Role getByUuid(String uuid);
	
	ArrayList<Role>getList();

	String insert_batch(ArrayList<Role> pr_List);
	
	List<String>getRole(String uuid);
}