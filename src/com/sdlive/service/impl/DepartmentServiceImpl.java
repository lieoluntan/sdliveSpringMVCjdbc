package com.sdlive.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sdlive.dao.DepartmentDao;
import com.sdlive.dao.impl.DepartmentDaoImpl;
import com.sdlive.model.Department;
import com.sdlive.service.DepartmentService;

/*
 * @author 刘鑫
 * @date 2018-1-29 13:23
 */
@Service("departmentServiceImpl")
public class DepartmentServiceImpl implements DepartmentService {
	@Resource(name="departmentDaoImpl")
	private DepartmentDao departmentDao;
	Logger logger = Logger.getLogger(DepartmentServiceImpl.class);

	@Override
	public String insert(Department department) {
		// TODO Auto-generated method stub
		String flag = this.getDepaartmentByName1(department);
		if (flag.equals("yes")) {
			return flag;
		} else {
			department.setUuid(null);
			department.setUuid(UUID.randomUUID().toString());
			System.out.println("^^在DepartmentServiceImpl收到数据 ："
					+ department.toString() + "收到结束!");
			boolean daoFlag = departmentDao.insert(department);
			if (daoFlag) {
				return department.getUuid();
			} else {
			  logger.error("插入不成功,dao层执行有出错地方,请联系管理员");
				return "插入不成功,dao层执行有出错地方,请联系管理员";
			}
		}
	}

	@Override
	public String delete(String uuid) {
		// TODO Auto-generated method stub
		
		if (uuid != null && uuid != "") {
			boolean daoFlag = departmentDao.delete(uuid);
			if (daoFlag) {
				return uuid;
			} else {
			  logger.error("删除不成功,dao层执行有出错地方,请联系管理员");
				return "删除不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "DepartmentServiceImpl delete方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}

	@Override
	public String update(Department department) {
		// TODO Auto-generated method stub
		String flag = this.getDepaartmentByName1(department);
		if (flag.equals("yes")) {
			return flag;
		} else {
			String uuid = department.getUuid();
			if (uuid != null && uuid != "") {
				boolean daoFlag = departmentDao.update(department);
				if (daoFlag) {
					return uuid;
				} else {
				  logger.error("修改不成功,dao层执行有出错地方,请联系管理员");
					return "修改不成功,dao层执行有出错地方,请联系管理员";
				}
			} else {
				String msg = "DepartmentServiceImpl update方法中的uuid为空，或格式不正确，请重新选择";
				System.out.println(msg);
				return msg;
			}
		}
	}

	@Override
	public Department getByUuid(String uuid) {
		// TODO Auto-generated method stub
		if (uuid != null && uuid != "") {
			return departmentDao.getByUuid(uuid);
		} else {
			System.out
					.println("DepartmentServiceImpl getByUuid方法中的uuid为空，或格式不正确，请联系管理员");
			return new Department();
		}
	}

	@Override
	public ArrayList<Department> getList() {
		// TODO Auto-generated method stub
		return departmentDao.getList();
	}

	@Override
	public String getDepartmentByName(Department dM) {
		// TODO Auto-generated method stub
		String flag = "";
		List<Department> depList = departmentDao.getdMByName(dM);
		for (Department dM2 : depList) {
			if (dM2.getUuid() != null) {
				flag = "(有重名)" + dM2.getName();
				return flag;
			}
		}
		flag = "(无重名)" + dM.getName();
		return flag;
	}

	@Override
	public String getDepaartmentByName1(Department dM) {
		// TODO Auto-generated method stub
		String flag = "";
		List<Department> depList = departmentDao.getdMByName(dM);
		for (Department dM2 : depList) {
			String uuid2 = dM2.getUuid();
			boolean flagSelf = uuid2.equals(dM.getUuid());
			boolean flagNotSelf = !flagSelf;
			if (flagNotSelf) {
				if (dM2.getUuid() != null) {
					flag = "yes";
					return flag;
				}
			}
		}
		flag = "no";
		return flag;
	}

	@Override
	public String getonoff(Department department) {
		// TODO Auto-generated method stub
		String uuid = department.getUuid();
		if (uuid != null && uuid != "") {
			String oac = department.getOpenAndclose();
			boolean daoFlag = departmentDao.updateOnOff(uuid, oac);
			if (daoFlag) {
			  
				return "操作成功";
			} else {
			  logger.error(oac+"开关失败,dao层执行有出错地方,请联系管理员");
				return "操作失败,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "DepartmentServiceImpl getonoff方法中的uuid为空，或格式不正确，请重新选择";
			logger.error(msg);
			System.out.println(msg);
			return msg;
		}
	}

}
