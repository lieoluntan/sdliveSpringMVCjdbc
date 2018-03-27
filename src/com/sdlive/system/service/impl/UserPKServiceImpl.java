package com.sdlive.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sdlive.system.dao.RoleDao;
import com.sdlive.system.dao.UserPKDao;
import com.sdlive.system.dao.impl.RoleDaoImpl;
import com.sdlive.system.dao.impl.UserPKDaoImpl;
import com.sdlive.system.model.Role;
import com.sdlive.system.model.UserPK;
import com.sdlive.system.service.UserPKService;
import com.sdlive.utility.M_msg;

/**
 * 树袋老师 树袋老师
 * 
 * @author 作者 xpp
 * @version 创建时间：2017-11-24 下午12:39:00 类说明
 * @version 创建时间：2017-11-24 下午12:39:00 类说明
 *          薛人杰+添加了insert方法中判断了添加用户时是否给了用户角色权限,在delete方法中加入了是只修改用户资料还是资料和角色一起修改的判断
 */

public class UserPKServiceImpl implements UserPKService {

	private UserPKDao userPKDao = new UserPKDaoImpl();
	private RoleDao roleDao = new RoleDaoImpl();
//	private EmployeeDao empLoyeeDao=new EmployeeDaoImpl();
	public M_msg m_msg = new M_msg();

	@Override
	public M_msg getMsg() {
		// TODO Auto-generated method stub
		return m_msg;
	}

	@SuppressWarnings("unused")
	@Override
	public String insert(UserPK userPK) {
		// TODO Auto-generated method stub
		userPK.setUuid("");
		UserPK userPK1 = userPKDao.getByuLogUser(userPK.getuLogUser());
		UserPK userPK2 = userPKDao.getByUname(userPK.getuName());
		if (userPK1.getuLogUser() == null) {
			if (userPK2.getuName() == null) {
				userPK.setUuid(null);
				userPK.setUuid(UUID.randomUUID().toString());
				System.out.println("^^在userPKServiceImpl收到数据 ");
				boolean daoFlag = userPKDao.insert(userPK);
				userPKDao.insertUserRole(userPK);
				if (daoFlag) {
					return "新增用户成功";
				} else {
					return "插入不成功,dao层执行有出错地方,请联系管理员";
				}
			} else {
				return "已存在重复用户名";
			}

		} else {
			return "已存在重复登陆名";
		}

	}

	public String delete(String uuid) {
		// TODO Auto-generated method stub
		if (uuid != null && uuid != "") {
			boolean daoFlag = userPKDao.delete(uuid);
			userPKDao.deleteUserRole(uuid);
			if (daoFlag) {
				return uuid;
			} else {
				return "删除不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "userPKDao delete方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}

	}

	@Override
	public String update(UserPK userPK) {
		// TODO Auto-generated method stub
		boolean daoFlag = false;
		String uuid = userPK.getUuid();
		if (uuid != null && uuid != "") {
			// 如果不修改角色
			// 修改角色
			daoFlag = userPKDao.update(userPK);// 先对用户进行修改
			userPKDao.deleteUserRole(userPK.getUuid());// 然后删掉该用户在用户角色表中的记录
			userPKDao.insertUserRole(userPK);// 然后重新指定角色

			if (daoFlag) {
				return uuid;
			} else {
				return "修改不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "userPKService update方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}// end method update

	@Override
	public UserPK getByUuid(String uuid) {
		// TODO Auto-generated method stub
		if (uuid != null && uuid != "") {
			UserPK userPK = userPKDao.getByUuid(uuid);
//			Employee emp=empLoyeeDao.getByUuid(userPK.getEmpUuid());
//			userPK.setEmpName(emp.getName());
			Role role = new Role();
			List<Role> backRoleList = new ArrayList<Role>();
			List<String> roleList = roleDao.getRole(userPK.getUuid());// 根据用户id得到用户的所有角色id
			for (String roleid : roleList) {
				role = roleDao.getByUuid(roleid);// 根据角色id 的到角色对象
				System.out.println(role.getName() + "  " + role.getUuid());
				backRoleList.add(role);
			}

			userPK.setRole(backRoleList);

			return userPK;
		} else {
			System.out
					.println("UserPKServiceImpl getByUuid方法中的uuid为空，或格式不正确，请联系管理员");
			UserPK userPKx = new UserPK();
			return userPKx;
		}
	}// end method getByUuid

	@Override
	public ArrayList<UserPK> getList() {
		// TODO Auto-generated method stub
		ArrayList<UserPK> userPKlist = userPKDao.getList();
		
		
		for (UserPK userPK : userPKlist) {// 所有用户集合
//			Employee emp=empLoyeeDao.getByUuid(userPK.getEmpUuid());
//			userPK.setEmpName(emp.getName());
			
			Role role = new Role();
			List<Role> backRoleList = new ArrayList<Role>();
			List<String> roleList = roleDao.getRole(userPK.getUuid());// 根据用户id得到用户的所有角色id
			for (String roleid : roleList) {
				role = roleDao.getByUuid(roleid);// 根据角色id 的到角色对象
				System.out.println(role.getName() + "  " + role.getUuid());
				backRoleList.add(role);
			}

			userPK.setRole(backRoleList);

		}
		return userPKlist;
	}// end method getList()

	@Override
	public boolean judge(UserPK userPK) {
		// TODO Auto-generated method stub
		boolean flag = false;
		// 传进来的账户密码
		String uLogUser = userPK.getuLogUser();
		String uPassWord = userPK.getuPassWord();

		// 数据库里查的账户密码
		UserPK old = new UserPK();
		old = userPKDao.getByuLogUser(uLogUser);
		String uLogUser_Old = old.getuLogUser();
		String uPassWord_Old = old.getuPassWord();
		if (uLogUser.equals(uLogUser_Old)) {

			if (uPassWord.equals(uPassWord_Old)) {
				m_msg.setGetOneMsg("正确登录");
				flag = true;
			} else {
				m_msg.setGetOneMsg("密码错误");
			}

		} else {
			m_msg.setGetOneMsg("用户名错误");
		}

		return flag;
	}// end method judge

	@Override
	public UserPK getUser(String uLogUser) {
		// TODO Auto-generated method stub
		UserPK u = userPKDao.getByuLogUser(uLogUser);
//		Employee emp=empLoyeeDao.getByUuid(u.getEmpUuid());
//		u.setEmpName(emp.getName());
		System.out.println(u.getUuid());
		return u;
	}

	@Override
	public List<String> getRole(String userPkid) {
		// TODO Auto-generated method stub
		return userPKDao.getRole(userPkid);
	}

}// end class