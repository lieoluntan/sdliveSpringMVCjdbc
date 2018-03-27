package com.sdlive.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sdlive.dao.DepartmentDao;
import com.sdlive.model.Department;
import com.sdlive.utility.DBUtility;

/*
 * @author 刘鑫
 * @date 2018-1-29 11：11
 */
@Repository("departmentDaoImpl")
public class DepartmentDaoImpl implements DepartmentDao {
	private Connection connection;
	boolean daoFlag = false;

	public DepartmentDaoImpl() {
		System.out.println("connection对象在DepartmentDaoImpl连接!");
	}

	@Override
	public boolean insert(Department department) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		connection = DBUtility.open();
		
		try {
			preparedStatement = connection
					.prepareStatement("insert into t_department(uuid,name,remark,openAndclose) values(?,?,?,?)");
			preparedStatement.setString(1, department.getUuid());
			preparedStatement.setString(2, department.getName());
			preparedStatement.setString(3, department.getRemark());
			preparedStatement.setString(4, "open");
			preparedStatement.executeUpdate();
			System.out.println("^^在执行DepartmentDaoImpl中的添加insert");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out
					.println("^^在执行DepartmentDaoImpl中insert,出现sql语法执行错误，请联系管理员!");
			e.printStackTrace();
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
		}

	}

	@Override
	public List<Department> getdMByName(Department dM) {
		// 重名校验
		// TODO Auto-generated method stub
		List<Department> depList=new ArrayList<Department>();
		Statement statement=null;
		ResultSet rs=null;
		connection = DBUtility.open();
		try {
			statement=connection.createStatement();
			rs=statement.executeQuery("select * from t_department where name='"+dM.getName()+"'");
			while(rs.next()){
				Department department=new Department();
				department.setUuid(rs.getString("uuid"));
				depList.add(department);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DepartmentDaoImpl中的getdMByName查询失败");
		}finally{
			DBUtility.close(rs, statement, connection);
		}
		
		return depList;
	}

	@Override
	public boolean delete(String uuid) {
		// TODO Auto-generated method stub
		PreparedStatement PSdelete = null;
		connection = DBUtility.open();
		
		try {
			PSdelete = connection
					.prepareStatement("delete from t_department where uuid='"
							+ uuid + "'");
			PSdelete.executeUpdate();
			System.out.println("^^在执行DepartmentDaoImpl中的删除delete");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("^^在执行DepartmentDaoImpl中delete,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, PSdelete, connection);
		}

	}

	@Override
	public boolean update(Department department) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		connection = DBUtility.open();
		try {
			preparedStatement = connection
					.prepareStatement("update t_department set name=?,remark=? where uuid=?");
			preparedStatement.setString(1, department.getName());
			preparedStatement.setString(2, department.getRemark());
			preparedStatement.setString(3, department.getUuid());
			preparedStatement.executeUpdate();
			System.out.println("^^在执行DepartmentDaoImpl中的修改update");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("^^在执行DepartmentDaoImpl中update,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
		}
	}

	@Override
	public ArrayList<Department> getList() {
		// TODO Auto-generated method stub
		ArrayList<Department> departmentList = new ArrayList<Department>();
		Statement statement = null;
		ResultSet rs = null;
		connection = DBUtility.open();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_department");
			while (rs.next()) {
				Department department = new Department();
				department.setName(rs.getString("name"));
				department.setUuid(rs.getString("uuid"));
				department.setRemark(rs.getString("remark"));
				department.setOpenAndclose(rs.getString("openAndclose"));
				departmentList.add(department);
			}
			return departmentList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DepartmentDaoImpl的查询部门列表失败");
			Department department = new Department();
			department.setUuid("DepartmentDaoImpl查询失败返回的uuid");
			ArrayList<Department> listDepartment = new ArrayList<Department>();
			listDepartment.add(department);
			return listDepartment;
		} finally {
			DBUtility.close(rs, statement, connection);
		}

	}

	@Override
	public Department getByUuid(String uuid) {
		// TODO Auto-generated method stub
		Department departmentResult = new Department();
		Statement statement = null;
		ResultSet rs = null;
		connection = DBUtility.open();
		try {
			statement = connection.createStatement();
			rs = statement
					.executeQuery("select * from t_department where uuid='"
							+ uuid + "'");
			while (rs.next()) {
				Department department = new Department();
				department.setName(rs.getString("name"));
				department.setUuid(rs.getString("uuid"));
				department.setRemark(rs.getString("remark"));
				department.setOpenAndclose(rs.getString("openAndclose"));
				departmentResult = department;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DepartmentDaoImpl的查询单个部门失败");
			Department department = new Department();
			department.setUuid("DepartmentDaoImpl失败返回的uuid");
			return department;
		} finally {
			DBUtility.close(rs, statement, connection);
		}
		return departmentResult;
	}

	@Override
	public boolean updateOnOff(String uuid, String oac) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null;
		connection = DBUtility.open();
		try {
			preparedStatement = connection
					.prepareStatement("update t_department set openAndclose='"
							+ oac + "' where uuid='" + uuid + "'");
			preparedStatement.executeUpdate();
			System.out.println("在执行DepartmentDaoImpl中的修改update");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out
					.println("^^在执行DepartmentDaoImpl中updateOnOff,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
		}
	}

}
