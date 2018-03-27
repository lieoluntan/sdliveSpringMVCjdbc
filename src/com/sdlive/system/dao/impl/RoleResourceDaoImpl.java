
package com.sdlive.system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sdlive.system.dao.RoleResourceDao;
import com.sdlive.system.model.RoleResource;
import com.sdlive.utility.DBUtility;

/**
 * 
 * 树袋老师
 * 
 * @author xuerenjie
 * @version 创建时间：2017-12-27 上午11:08:06
 * 
 */
public class RoleResourceDaoImpl implements RoleResourceDao {

	private Connection connection;
	boolean daoFlag = false;

	public RoleResourceDaoImpl() {
		// connection = DBUtility.open();
		System.out.println("connection对象在RoleResourceImpl连接!");
	}

	@Override
	public List<String> getRsbyRoleid(List<String> list) {
		// TODO Auto-generated method stub
		RoleResource roleResourceResult = new RoleResource();
		Statement statement = null;// finally关闭数据库连接
		ResultSet rs = null;// 关闭数据库连接get和getlist会用到
		List<String> rsList = new ArrayList<String>();// 用来存放得到的资源id

		for (String roleid : list) {

			try {
				connection = DBUtility.open();// 打开数据库连接
				statement = connection.createStatement();
				rs = statement
						.executeQuery("select * from t_role_resource WHERE roleid ="
								+ "'" + roleid + "'");
				while (rs.next()) {
					RoleResource roleResource = new RoleResource();
					roleResource.setUuid(rs.getString("uuid"));
					roleResource.setRoleid(rs.getString("roleid"));
					roleResource.setResourceid(rs.getString("resourceid"));

					roleResourceResult = roleResource;
					rsList.add(roleResourceResult.getResourceid());
				}

			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("RoleResourceImpl的getByUuid查询失败");

			} finally {
				DBUtility.close(rs, statement, connection);
			}// finally关闭jdbc与数据库连接

			for (String a : rsList) {
				System.out.println(a);
			}
		}// end foreach

		// 已经得到了所有的资源id,现在要去重复
		Set set = new HashSet();
		List newRsList = new ArrayList();// 新的资源集合，用来存放去重复后的资源id
		for (String cd : rsList) {// foreach
			if (set.add(cd)) {
				newRsList.add(cd);
			}

		}// end forech 去重复完成，现在的newRsList就是不重复的所有资源id

		return newRsList;
	}

	@Override
	public boolean insert(RoleResource roleResource) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null; // 关闭数据库连接insert和update和delete用到
		ArrayList<RoleResource> list = this.getListbyName(
				roleResource.getRoleid(), roleResource.getResourceid());
		if (!list.isEmpty() && list != null && list.size() != 0) {

			return false;
		} else {
			try {
				connection = DBUtility.open();// 打开数据库连接
				preparedStatement = connection
						.prepareStatement("insert into t_role_resource(uuid,roleid,resourceid) values (?,?,?)");
				// Parameters start with 1
				preparedStatement.setString(1, roleResource.getUuid());
				preparedStatement.setString(2, roleResource.getRoleid());
				preparedStatement.setString(3, roleResource.getResourceid());

				preparedStatement.executeUpdate();

				System.out.println("^^在执行RoleResourceImpl中的添加insert");
				daoFlag = true;
				return daoFlag;
			} catch (SQLException e) {
				System.out
						.println("^^在执行RoleResourceImpl中insert,出现sql语法执行错误，请联系管理员!");
				e.printStackTrace();
				daoFlag = false;
				return daoFlag;
			} finally {
				ResultSet rs = null;
				DBUtility.close(rs, preparedStatement, connection);
			}// finally关闭jdbc与数据库连接
		}

	}

	@Override
	public boolean delete(String uuid) {
		// TODO Auto-generated method stub
		PreparedStatement PSdelete = null; // 关闭数据库连接insert和update和delete用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			// Parameters start with 1
			//WHERE roleid = ? 修改成正确的sql语句  where uuid = ?
			PSdelete = connection
					.prepareStatement("DELETE FROM t_role_resource WHERE uuid = ? ");
			PSdelete.setString(1, uuid);
			PSdelete.executeUpdate();

			System.out.println("^^在执行RoleResourceImpl中的删除delete");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out
					.println("^^在执行RoleResourceImpl中delete,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, PSdelete, connection);
		}// finally关闭jdbc与数据库连接
	}

	@Override
	public boolean update(RoleResource roleResource) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null; // 关闭数据库连接insert和update和delete用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			preparedStatement = connection
					.prepareStatement("UPDATE t_role_resource SET roleid = ?, resourceid = ?  WHERE uuid = ? ");
			// Parameters start with 1
			preparedStatement.setString(3, roleResource.getUuid());
			preparedStatement.setString(1, roleResource.getRoleid());
			preparedStatement.setString(2, roleResource.getResourceid());
			preparedStatement.executeUpdate();

			System.out.println("^^在执行RoleResourceImpl中的修改update");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out
					.println("^^在执行RoleResourceImpl中update,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
		}// finally关闭jdbc与数据库连接
	}

	@Override
	public RoleResource getByUuid(String uuid) {
		// TODO Auto-generated method stub
		RoleResource classRoomResult = new RoleResource();
		Statement statement = null;// finally关闭数据库连接
		ResultSet rs = null;// 关闭数据库连接get和getlist会用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			statement = connection.createStatement();
			rs = statement
					.executeQuery("select * from t_role_resource WHERE uuid ="
							+ "'" + uuid + "'");
			while (rs.next()) {
				RoleResource roleResource = new RoleResource();
				roleResource.setUuid(rs.getString("uuid"));
				roleResource.setRoleid(rs.getString("roleid"));
				roleResource.setResourceid(rs.getString("resourceid"));

				classRoomResult = roleResource;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("RoleResourceImpl的getByUuid查询失败");
			RoleResource classRoomX = new RoleResource();
			classRoomX.setUuid("RoleResourceImpl失败返回的uuid");
			return classRoomX;
		} finally {
			DBUtility.close(rs, statement, connection);
		}// finally关闭jdbc与数据库连接

		return classRoomResult;
	}

	@Override
	public ArrayList<RoleResource> getList() {
		// TODO Auto-generated method stub
		ArrayList<RoleResource> classRoomList = new ArrayList<RoleResource>();
		Statement statement = null;// finally关闭数据库连接
		ResultSet rs = null;// 关闭数据库连接get和getlist会用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_role_resource");
			while (rs.next()) {
				RoleResource roleResource = new RoleResource();
				roleResource.setUuid(rs.getString("uuid"));
				roleResource.setRoleid(rs.getString("roleid"));
				roleResource.setResourceid(rs.getString("resourceid"));

				classRoomList.add(roleResource);
			}

			return classRoomList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("RoleResourceImpl的getList查询失败");
			RoleResource classRoomX = new RoleResource();
			classRoomX.setUuid("RoleResourceImpl的getList失败返回的uuid");
			ArrayList<RoleResource> classRoomListX = new ArrayList<RoleResource>();
			classRoomListX.add(classRoomX);
			return classRoomListX;
		} finally {
			DBUtility.close(rs, statement, connection);
		}// finally关闭jdbc与数据库连接

	}

	@Override
	public ArrayList<RoleResource> getListbyName(String roleid,
			String resourceid) {
		// TODO Auto-generated method stub
		ArrayList<RoleResource> ResourceList = new ArrayList<RoleResource>();

		Statement statement = null;// finally关闭数据库连接
		ResultSet rs = null;// 关闭数据库连接get和getlist会用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			statement = connection.createStatement();
			rs = statement
					.executeQuery("select * from t_role_resource WHERE roleid ="
							+ "'"
							+ roleid
							+ "' and resourceid = "
							+ "'"
							+ resourceid + "'");
			while (rs.next()) {
				RoleResource roleResource = new RoleResource();
				roleResource.setUuid(rs.getString("uuid"));
				roleResource.setRoleid(rs.getString("roleid"));
				roleResource.setResourceid(rs.getString("resourceid"));
				ResourceList.add(roleResource);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("RoleResourceImpl的getByUuid查询失败");

			// Resource resourceX = new Resource();
			// resourceX.setUuid("ResourceDaoImpl失败返回的name");
			// return resourceX;
		} finally {
			DBUtility.close(rs, statement, connection);
		}// finally关闭jdbc与数据库连接

		return ResourceList;
	}

	
	//根据角色id返回角色资源列表
	@Override
	public ArrayList<String> getListbyRoleUuid(String RoleUuid) {
		// TODO Auto-generated method stub
		RoleResource roleResourceResult = new RoleResource();
		Statement statement = null;// finally关闭数据库连接
		ResultSet rs = null;// 关闭数据库连接get和getlist会用到
		ArrayList<String> rsidList = new ArrayList<String>();
			try {
				connection = DBUtility.open();// 打开数据库连接
				statement = connection.createStatement();
				rs = statement
						.executeQuery("select * from t_role_resource WHERE roleid ="
								+ "'" + RoleUuid + "'");
				
				while (rs.next()) {
					RoleResource roleResource = new RoleResource();
					roleResource.setUuid(rs.getString("uuid"));
					roleResource.setRoleid(rs.getString("roleid"));
					roleResource.setResourceid(rs.getString("resourceid"));
					roleResourceResult = roleResource;
					rsidList.add(roleResourceResult.getResourceid());
					
				}

				
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("RoleResourceImpl的getByUuid查询失败");

			} finally {
				DBUtility.close(rs, statement, connection);
			}// finally关闭jdbc与数据库连接
			
		return rsidList;
	}

	@Override
	public String insert_batch(ArrayList<RoleResource> PR_List) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteRoleRs(String uuid) {
		// TODO Auto-generated method stub
		PreparedStatement PSdelete = null; // 关闭数据库连接insert和update和delete用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			// Parameters start with 1
			PSdelete = connection
					.prepareStatement("DELETE FROM t_role_resource WHERE roleid = ? ");
			PSdelete.setString(1, uuid);
			PSdelete.executeUpdate();

			System.out.println("^^在执行RoleResourceImpl中的删除delete");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out
					.println("^^在执行RoleResourceImpl中delete,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, PSdelete, connection);
		}// finally关闭jdbc与数据库连接
	}

	@Override
	public boolean deleteByuse(String roleid) {
		// TODO Auto-generated method stub
		  PreparedStatement PSdelete = null; //关闭数据库连接insert和update和delete用到
		    try {
		    	connection = DBUtility.open();//打开数据库连接
		      // Parameters start with 1
		      PSdelete = connection.prepareStatement("DELETE FROM t_role_resource WHERE roleid = ? ");
		      PSdelete.setString(1, roleid);
		      PSdelete.executeUpdate();
		      System.out.println("^^在执行userPK_roleDaoImpl中的删除delete");
		      daoFlag = true;
		      return daoFlag;
		    } catch (SQLException e) {
		      e.printStackTrace();
		      System.out.println("^^在执行userPK_roleDaoImpl中deleteByuse,出现sql语法执行错误，请联系管理员!");
		      daoFlag = false;
		      return daoFlag;
		    }finally{
		      ResultSet rs = null; 
		      DBUtility.close(rs, PSdelete, connection);   
		     }
	}
}

