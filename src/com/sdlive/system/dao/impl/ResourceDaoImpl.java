package com.sdlive.system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sdlive.system.dao.ResourceDao;
import com.sdlive.system.model.Resource;
import com.sdlive.utility.DBUtility;

/**
 * 
 * 树袋老师
 * 
 * @author xuerenjie
 * @version 创建时间：2017-12-26 上午10:56:38
 * 
 */
public class ResourceDaoImpl implements ResourceDao {
	private Connection connection;
	boolean daoFlag = false;

	public ResourceDaoImpl() {
		// connection = DBUtility.open();
		System.out.println("connection对象在ResourceDaoImpl连接!");
	}

	@Override
	public boolean insert(Resource resource) {
		PreparedStatement preparedStatement = null; // 关闭数据库连接insert和update和delete用到
		ArrayList<Resource> list = this.getListbyName(resource.getName());
		if (!list.isEmpty() && list != null && list.size() != 0) {

			return false;
		} else {
			try {
				connection = DBUtility.open();// 打开数据库连接
				preparedStatement = connection
						.prepareStatement("insert into t_resource(uuid,name) values (?,?)");
				// Parameters start with 1
				preparedStatement.setString(1, resource.getUuid());
				preparedStatement.setString(2, resource.getName());

				preparedStatement.executeUpdate();

				System.out.println("^^在执行ResourceDaoImpl中的添加insert");
				daoFlag = true;
				return daoFlag;
			} catch (SQLException e) {
				System.out
						.println("^^在执行ResourceDaoImpl中insert,出现sql语法执行错误，请联系管理员!");
				e.printStackTrace();
				daoFlag = false;
				return daoFlag;
			} finally {
				ResultSet rs = null;
				DBUtility.close(rs, preparedStatement, connection);
			}// finally关闭jdbc与数据库连接\
		}
	}

	@Override
	public boolean delete(String uuid) {
		// TODO Auto-generated method stub
		PreparedStatement PSdelete = null; // 关闭数据库连接insert和update和delete用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			// Parameters start with 1
			PSdelete = connection
					.prepareStatement("DELETE FROM t_resource WHERE uuid = ? ");
			PSdelete.setString(1, uuid);
			PSdelete.executeUpdate();
			System.out.println("^^在执行ResourceDaoImpl中的删除delete");
			
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out
					.println("^^在执行ResourceDaoImpl中delete,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, PSdelete, connection);
		}// finally关闭jdbc与数据库连接
	}

	@Override
	public boolean update(Resource resource) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null; // 关闭数据库连接insert和update和delete用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			preparedStatement = connection
					.prepareStatement("UPDATE t_resource SET name = ? WHERE uuid = ? ");
			// Parameters start with 1
			preparedStatement.setString(1, resource.getName());

			preparedStatement.setString(2, resource.getUuid());
			preparedStatement.executeUpdate();

			System.out.println("^^在执行ResourceDaoImpl中的修改update");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out
					.println("^^在执行ResourceDaoImpl中update,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
		}// finally关闭jdbc与数据库连接
	}

	@Override
	public Resource getByUuid(String uuid) {
		// TODO Auto-generated method stub
		Resource resourceResult = new Resource();

		Statement statement = null;// finally关闭数据库连接
		ResultSet rs = null;// 关闭数据库连接get和getlist会用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_resource WHERE uuid ="
					+ "'" + uuid + "'");
			while (rs.next()) {
				Resource resource = new Resource();
				resource.setUuid(rs.getString("uuid"));
				resource.setName(rs.getString("name"));

				resourceResult = resource;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ResourceDaoImpl的getByUuid查询失败");

			Resource resourceX = new Resource();
			resourceX.setUuid("ResourceDaoImpl失败返回的uuid");
			return resourceX;
		} finally {
			DBUtility.close(rs, statement, connection);
		}// finally关闭jdbc与数据库连接

		return resourceResult;
	}

	@Override
	public ArrayList<Resource> getList() {
		// TODO Auto-generated method stub

		ArrayList<Resource> ResourceList = new ArrayList<Resource>();
		Statement statement = null;// finally关闭数据库连接
		ResultSet rs = null;// 关闭数据库连接get和getlist会用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_resource");
			while (rs.next()) {

				Resource resource = new Resource();
				resource.setUuid(rs.getString("uuid"));
				resource.setName(rs.getString("name"));

				ResourceList.add(resource);
			}

			return ResourceList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ResourceDaoImpl的getList查询失败");

			Resource resourceX = new Resource();
			resourceX.setUuid("ResourceDaoImpl的getList失败返回的uuid");
			ArrayList<Resource> ResourceListX = new ArrayList<Resource>();
			ResourceListX.add(resourceX);
			return ResourceListX;
		} finally {
			DBUtility.close(rs, statement, connection);
		}// finally关闭jdbc与数据库连接

	}

	@Override
	public ArrayList<Resource> getListbyName(String name) {
		// TODO Auto-generated method stub
		ArrayList<Resource> ResourceList = new ArrayList<Resource>();

		Statement statement = null;// finally关闭数据库连接
		ResultSet rs = null;// 关闭数据库连接get和getlist会用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_resource WHERE name ="
					+ "'" + name + "'");
			while (rs.next()) {
				Resource resource = new Resource();
				resource.setUuid(rs.getString("uuid"));
				resource.setName(rs.getString("name"));

				ResourceList.add(resource);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ResourceDaoImpl的getByUuid查询失败");

			// Resource resourceX = new Resource();
			// resourceX.setUuid("ResourceDaoImpl失败返回的name");
			// return resourceX;
		} finally {
			DBUtility.close(rs, statement, connection);
		}// finally关闭jdbc与数据库连接

		return ResourceList;

	}

	@Override
	public String insert_batch(ArrayList<Resource> PR_List) {
		// TODO Auto-generated method stub
		return null;
	}
}
