package com.sdlive.system.service;

import java.util.ArrayList;

import com.sdlive.system.model.Resource;
import com.sdlive.utility.M_msg;

/**
 * 
 * 树袋老师
 * 
 * @author xuerenjie
 * @version 创建时间：2017-12-26 上午11:24:38
 * 
 */
public interface ResourceService {
	M_msg getMsg();
	String insert(Resource resource);

	String delete(String uuid);

	String update(Resource resource);

	Resource getByUuid(String uuid);

	ArrayList<Resource> getList();

	// 根据name查询信息，如果有信息，说明name存在；
	ArrayList<Resource> getListbyName(String name);
	
	String insert_batch(ArrayList<Resource> PR_List);
	
}
