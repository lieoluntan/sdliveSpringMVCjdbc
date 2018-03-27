package com.sdlive.system.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sdlive.system.dao.RoleDao;
import com.sdlive.system.dao.RoleResourceDao;
import com.sdlive.system.dao.impl.RoleDaoImpl;
import com.sdlive.system.dao.impl.RoleResourceDaoImpl;
import com.sdlive.system.model.Role;
import com.sdlive.system.model.RoleResource;
import com.sdlive.system.service.RoleResourceService;
import com.sdlive.system.service.RoleService;
/**
 * 
 * @author 罗成峰
 * @date 2017-12-27上午11:07:07
 * @version 1.0
 */
public class RoleServiceImp implements RoleService{
	
	private RoleDao roledao = new RoleDaoImpl();
	private RoleResourceDao roleResourceDao = new RoleResourceDaoImpl();
	private RoleResourceService roleResourceService = new RoleResourceServiceImpl();
		
	@Override
	public String insert(Role role) {
		// TODO Auto-generated method stub
		
		Role r=roledao.getByName(role.getName());
		
		if(r.getName()==null){
			
			role.setUuid(null);
			role.setUuid(UUID.randomUUID().toString());
			System.out.println("^^在RoleServiceImle收到数据:"+role.toString()+"收到结束!");
			boolean daoFlag = roledao.insert(role);
		    if(daoFlag)
		    {
		    	List<String> rsListNew = role.getRsList(); 
		    	for(String str:rsListNew){
		    		RoleResource roleResource = new RoleResource();
			    	roleResource.setRoleid(role.getUuid());
			    	roleResource.setResourceid(str);
			    	roleResourceService.insert(roleResource);
		    	}
		    return "新增角色成功";
		    }else{
		      return "插入角色失败";
		    }
		}else{
			
			return "角色名已存在";
		}
		
	}

	@Override
	public String delete(String uuid) {
		 // TODO Auto-generated method stub
	    if(uuid!=null&&uuid!="")
	    {
	      boolean dao = roledao.delete(uuid);
	      //roleResourceDao.delete(uuid);  修改为正确对的roleResourceDao.deleteByuse(uuid);
	      roleResourceDao.deleteByuse(uuid);
	     if(dao)
	        {
	        return uuid;
	        }else{
	          return "删除不成功,dao层执行有出错地方,请联系管理员";
	        }
	    }else{
	      String msg="RoleServiceImpl delete方法中的uuid为空，或格式不正确，请重新选择";
	      System.out.println(msg);
	      return msg;
	    }
	}

	@Override
	public String update(Role role) {
		  boolean daoFlag = false;
		String uuid = role.getUuid();
		    if(uuid!=null&&uuid!="")
		    {
		    	if(role.getRsList()== null){
		    		daoFlag = roledao.update(role);
		    	}else{
		    		daoFlag = roledao.update(role);
		    		roleResourceDao.deleteRoleRs(role.getUuid());
		    		roledao.insertRoleRs(role);
		    	}
		    	if(daoFlag)
		        {
		        return uuid;
		        }else{
		          return "修改不成功,dao层执行有出错地方,请联系管理员";
		        }
		    }else{
		      String msg="RoleServiceImpl update方法中的uuid为空，或格式不正确，请重新选择";
		      System.out.println(msg);
		      return msg;
		    }
	}

	@Override
	public Role getByUuid(String uuid) {
		  if(uuid!=null&&uuid!="")
		    {
		      Role role = roledao.getByUuid(uuid);
		    
		      return role;
		    }else{
		      System.out.println("RoleServiceImpl getByUuid方法中的uuid为空，或格式不正确，请联系管理员");
		      Role roleX= new Role();
		    return roleX;
		    }
	}

	@Override
	public ArrayList<Role> getList() {
		 ArrayList<Role> Rolelist = roledao.getList();
		 return Rolelist;
	}

	@Override
	public String insert_batch(ArrayList<Role> pr_List) {
		//步骤二，执行无冲突插入操作
	    int count = 0;
	    for (Role one : pr_List) {
	      try{
	    	one.setUuid(null);
	    	one.setUuid(UUID.randomUUID().toString());
	    	boolean daoFlag = roledao.insert(one);
	    	if(daoFlag){
	    		System.out.println(one.getName()+"插入成功");
	    		count++;
	    	}else{
	    		System.out.println(one.getName()+"已存在重复名字");
	    	}
	      }catch(Exception e){
	    	  System.out.println("insert_batch查询冲突有错误");
	      }
	   }
	    String recount = String.valueOf(count);
	    return recount;
	  }

	@Override
	public List<String> getRole(String uuid) {
		// TODO Auto-generated method stub
		return roledao.getRole(uuid);
	}
}
