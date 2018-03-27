package com.sdlive.system.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.sdlive.system.controller.ResourceController;
import com.sdlive.system.dao.ResourceDao;
import com.sdlive.system.dao.impl.ResourceDaoImpl;
import com.sdlive.system.model.Resource;
import com.sdlive.system.service.ResourceService;
import com.sdlive.utility.M_msg;

/**
 * 
 * 树袋老师
 * 
 * @author xuerenjie
 * @version 创建时间：2017-12-26 上午11:31:58
 * 
 */
public class ResourceServiceImpl implements ResourceService {
	private ResourceDao resourceDao = new ResourceDaoImpl();
	public M_msg m_msg = new M_msg();
	Logger logger = Logger.getLogger(ResourceServiceImpl.class);

	@Override
	public String insert(Resource resource) {
		// TODO Auto-generated method stub

		resource.setUuid(null);

		resource.setUuid(UUID.randomUUID().toString());
		System.out.println("^^在ResourceServiceImpl收到数据 ：" + resource.toString()
				+ "收到结束!");
		
		boolean daoFlag = resourceDao.insert(resource);
		if (daoFlag) {
			return "新增资源成功";
		} else {
			return "已存在重复名字";
		}
	}
	@Override
	public String delete(String uuid) {
		// TODO Auto-generated method stub
		if (uuid != null && uuid != "") {
			boolean daoFlag = resourceDao.delete(uuid);

			if (daoFlag) {
				return uuid;
			} else {
			  logger.error("删除不成功,dao层执行有出错地方,请联系管理员");
				return "删除不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "ResourceServiceImpl delete方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}

	@Override
	public String update(Resource resource) {
		// TODO Auto-generated method stub
		String uuid = resource.getUuid();
		if (uuid != null && uuid != "") {

			boolean daoFlag = resourceDao.update(resource);

			if (daoFlag) {
				return uuid;
			} else {
			  logger.error("修改不成功,dao层执行有出错地方,请联系管理员");
				return "修改不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "ResourceServiceImpl update方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}

	@Override
	public Resource getByUuid(String uuid) {
		// TODO Auto-generated method stub
		if (uuid != null && uuid != "") {
			// ClassRoom classRoom = classRoomDao.getByUuid(uuid);
			Resource resource = resourceDao.getByUuid(uuid);
			return resource;
		} else {
			System.out
					.println("ResourceServiceImpl getByUuid方法中的uuid为空，或格式不正确，请联系管理员");
			// ClassRoom classRoomX= new ClassRoom();
			Resource resourceX = new Resource();
			return resourceX;
		}
	}

	@Override
	public ArrayList<Resource> getList() {
		// TODO Auto-generated method stub
		ArrayList<Resource> resourcelist = resourceDao.getList();

		return resourcelist;
	}

	@Override
	public ArrayList<Resource> getListbyName(String name) {
		// TODO Auto-generated method stub
		if (name != null && name != "") {

			return resourceDao.getListbyName(name);
		} else {
			System.out
					.println("ResourceServiceImpl getListbyName方法中的name为空，或格式不正确，请联系管理员");
		}
		return null;
	}

	@Override
	public M_msg getMsg() {
		// TODO Auto-generated method stub
		return m_msg;
	}

	@Override
	public String insert_batch(ArrayList<Resource> PR_List) {
		// TODO Auto-generated method stub
		int count = 0;
		for (Resource one : PR_List) {
			// TODO 此处加if判断，包围后面的插入操作，要没有冲突才能做新增语句
			try {

				// 单个插入操作
				one.setUuid(null);
				one.setUuid(UUID.randomUUID().toString());
				boolean daoFlag = resourceDao.insert(one);
				if (daoFlag) {
					System.out.println(one.getName()+"插入成功");
					count++;
				} else {
					System.out.println(one.getName()+"已存在重复名字");
				}
				// 单个插入操作

			} catch (Exception e) {
				System.out.println("insert_batch查询冲突有错误");
			}
		}// end for 结束for循环
		String recount = String.valueOf(count);
		return recount;
	}// end method insert_batch

}
