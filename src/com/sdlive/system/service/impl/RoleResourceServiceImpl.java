
package com.sdlive.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sdlive.system.dao.RoleResourceDao;
import com.sdlive.system.dao.impl.RoleResourceDaoImpl;
import com.sdlive.system.model.Role;
import com.sdlive.system.model.RoleResource;
import com.sdlive.system.service.RoleResourceService;
import com.sdlive.utility.M_msg;

/**
 * 
 * 树袋老师
 * 
 * @author xuerenjie
 * @version 创建时间：2017-12-27 上午11:19:31
 * 
 */
public class RoleResourceServiceImpl implements RoleResourceService {
	private RoleResourceDao roleResourceDao = new RoleResourceDaoImpl();

	@Override
	public List<String> getRsbyRoleid(List<String> rolelist) {
		// TODO Auto-generated method stub
		// 先判断集合为不为空
		List<String> list = null;
		if (!rolelist.isEmpty() && rolelist.size() != 0 && rolelist != null) {

			list = roleResourceDao.getRsbyRoleid(rolelist);
			return list;
		} else {
			return null;
		}

	}
	@Override
	public String insert(RoleResource roleResource) {
		// TODO Auto-generated method stub
		roleResource.setUuid(null);

		roleResource.setUuid(UUID.randomUUID().toString());
		System.out.println("^^在RoleResourceServiceImpl收到数据 ："
				+ roleResource.toString() + "收到结束!");
		boolean daoFlag = roleResourceDao.insert(roleResource);
		if (daoFlag) {
			return roleResource.getUuid();
		} else {
			return "插入不成功,dao层执行有出错地方,请联系管理员";
		}
	}

	@Override
	public String delete(String uuid) {
		// TODO Auto-generated method stub
		if (uuid != null && uuid != "") {
			boolean daoFlag = roleResourceDao.delete(uuid);

			if (daoFlag) {
				return uuid;
			} else {
				return "删除不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "RoleResourceServiceImpl delete方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}

	@Override
	public String update(RoleResource roleResource) {
		// TODO Auto-generated method stub
		String uuid = roleResource.getUuid();
		if (uuid != null && uuid != "") {
			boolean daoFlag = roleResourceDao.update(roleResource);
			if (daoFlag) {
				return uuid;
			} else {
				return "修改不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "RoleResourceServiceImpl update方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}

	@Override
	public RoleResource getByUuid(String uuid) {
		// TODO Auto-generated method stub
		if (uuid != null && uuid != "") {
			RoleResource classRoom = roleResourceDao.getByUuid(uuid);
			return classRoom;
		} else {
			System.out
					.println("RoleResourceServiceImpl getByUuid方法中的uuid为空，或格式不正确，请联系管理员");
			RoleResource classRoomX = new RoleResource();
			return classRoomX;
		}
	}

	@Override
	public ArrayList<RoleResource> getList() {
		// TODO Auto-generated method stub
		ArrayList<RoleResource> classRoomlist = roleResourceDao.getList();

		return classRoomlist;
	}

	@Override
	public M_msg getMsg() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String insert_batch(ArrayList<RoleResource> PR_List) {
		// TODO Auto-generated method stub
		int count = 0;
		for (RoleResource one : PR_List) {
			// TODO 此处加if判断，包围后面的插入操作，要没有冲突才能做新增语句
			try {

				// 单个插入操作
				one.setUuid(null);
				one.setUuid(UUID.randomUUID().toString());
				boolean daoFlag = roleResourceDao.insert(one);
				if (daoFlag) {
					System.out.println("插入成功");
					count++;
				} else {
					System.out.println("已存在重复记录");
				}
				// 单个插入操作

			} catch (Exception e) {
				System.out.println("insert_batch查询冲突有错误");
			}
		}// end for 结束for循环
		String recount = String.valueOf(count);
		return recount;
	}

	@Override
	public ArrayList<RoleResource> getListbyName(String roleid,
			String resourceid) {
		// TODO Auto-generated method stub
		if (roleid != null && roleid != "" && roleid.length() != 0
				&& resourceid != null && resourceid != ""
				&& resourceid.length() != 0) {

			return roleResourceDao.getListbyName(roleid, resourceid);
		} else {
			System.out
					.println("RoleResourceServiceImpl getListbyName方法中的name为空，或格式不正确，请联系管理员");
		}
		return null;
	}

	//根据角色id返回角色资源列表
	@Override
	public ArrayList<String> getListbyRoleUuid(Role role) {
		// TODO Auto-generated method stub
		String RoleUuid = role.getUuid();
		ArrayList<String> rsList = roleResourceDao.getListbyRoleUuid(RoleUuid);
		return rsList;
	}
}

