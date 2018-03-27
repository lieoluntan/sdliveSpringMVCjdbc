
package com.sdlive.system.service;

import java.util.ArrayList;
import java.util.List;

import com.sdlive.system.model.Role;
import com.sdlive.system.model.RoleResource;
import com.sdlive.utility.M_msg;

/**
 * 
 * 树袋老师
 * 
 * @author xuerenjie
 * @version 创建时间：2017-12-27 上午11:15:47 角色资源业务
 */
public interface RoleResourceService {
	M_msg getMsg();

	/**
	 * 批量添加角色资源
	 * 
	 * @param PR_List
	 * @return
	 */
	String insert_batch(ArrayList<RoleResource> PR_List);

	/**
	 * 根据角色id查询资源id
	 * 
	 * @param list
	 *            角色id的集合
	 * 
	 * @return list 存放资源id
	 */
	List<String> getRsbyRoleid(List<String> list);

	/**
	 * 添加资源
	 * 
	 * @param roleResource
	 *            角色资源对象
	 * @return
	 */
	public String insert(RoleResource roleResource);

	/**
	 * 根据主键删除资源
	 * 
	 * @param uuid
	 *            主键
	 * @return
	 */

	public String delete(String uuid);

	/**
	 * 修改资源
	 * 
	 * @param roleResource
	 *            角色资源对象
	 * 
	 * @return
	 */
	public String update(RoleResource roleResource);

	/**
	 * 根据主键查询
	 * 
	 * @param uuid
	 *            主键
	 * @return 角色资源对象
	 */
	public RoleResource getByUuid(String uuid);

	/**
	 * 遍历所有资源
	 * 
	 * @return 资源信息
	 */
	public ArrayList<RoleResource> getList();
	
	ArrayList<RoleResource> getListbyName(String roleid,
			String resourceid);
	//根据角色id返回角色资源列表
	ArrayList<String> getListbyRoleUuid(Role role);
}

