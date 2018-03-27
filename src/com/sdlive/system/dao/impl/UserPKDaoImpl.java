package com.sdlive.system.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sdlive.system.dao.UserPKDao;
import com.sdlive.system.model.Role;
import com.sdlive.system.model.UserPK;
import com.sdlive.system.model.UserPK_Role;
import com.sdlive.utility.DBUtility;

/**
 * 树袋老师
 * 
 * @author 作者 xpp
 * @version 创建时间：2017-11-24 下午12:42:55 类说明
 *          薛人杰+加入两个方法insertUserRole(添加用户角色)和deleteUserRole（删除用户角色）
 */

public class UserPKDaoImpl implements UserPKDao {

	private Connection connection;
	boolean daoFlag = false;

	public UserPKDaoImpl() {
		// connection = DBUtility.open();
		System.out.println("connection对象在ClaDaoImpl连接!");
	}

	@Override
	public boolean insert(UserPK userPK) {
		// TODO Auto-generated method stub
		System.out.println(userPK.getEmpUuid());
		PreparedStatement preparedStatement = null; // 关闭数据库连接insert和update和delete用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			preparedStatement = connection
					.prepareStatement("insert into t_userPK(uuid,uLogUser,uPassWord,uName,empUuid) values (?,?,?,?,?)");
			// Parameters start with 1
			preparedStatement.setString(1, userPK.getUuid());
			preparedStatement.setString(2, userPK.getuLogUser());
			preparedStatement.setString(3, userPK.getuPassWord());
			preparedStatement.setString(4, userPK.getuName());
			preparedStatement.setString(5, userPK.getEmpUuid());
			preparedStatement.executeUpdate();

			System.out.println("^^在执行t_userPK DaoImpl中的添加insert");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			System.out.println("^^在执行t_userPK 中insert,出现sql语法执行错误，请联系管理员!");
			e.printStackTrace();
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
			System.out.println("userPKDao insert 调用了关闭数据库连接");
		}// finally关闭jdbc与数据库连接
	}// edn method insert

	@Override
	public boolean insertUserRole(UserPK userPK) {
		// TODO Auto-generated method stub

		// userPK.setUuid(UUID.randomUUID().toString());
		PreparedStatement preparedStatement = null; // 关闭数据库连接insert和update和delete用到
		try {
			for (String userRole : userPK.getRoleList()) {// 添加用户角色表

				connection = DBUtility.open();// 打开数据库连接
				preparedStatement = connection
						.prepareStatement("insert into t_userpk_role(uuid,userPkid,Roleid) values (?,?,?)");
				// Parameters start with 1
				preparedStatement.setString(1, UUID.randomUUID().toString());
				preparedStatement.setString(2, userPK.getUuid());
				preparedStatement.setString(3, userRole);

				preparedStatement.executeUpdate();

				System.out.println("^^在执行t_userPK DaoImpl中的添加insert");
				daoFlag = true;
			}
			return daoFlag;

		} catch (SQLException e) {
			System.out.println("^^在执行t_userPK 中insert,出现sql语法执行错误，请联系管理员!");
			e.printStackTrace();
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
			System.out.println("userPKDao insert 调用了关闭数据库连接");
		}// finally关闭jdbc与数据库连接

	}// edn method insert

	@Override
	public boolean delete(String uuid) {
		// TODO Auto-generated method stub
		PreparedStatement PSdelete = null; // 关闭数据库连接insert和update和delete用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			PreparedStatement num = connection
					.prepareStatement("select sum(uName) from t_userPK_role where uuid=?");
			if (num != null) {
				System.out.println("请修改XXX用户,XXX用户的角色,再删除");
			}
			// Parameters start with 1
			PSdelete = connection
					.prepareStatement("DELETE FROM t_userPK WHERE uuid = ? ");

			PSdelete.setString(1, uuid);
			PSdelete.executeUpdate();

			System.out.println("^^在执行t_userPK中的删除delete");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("^^在执行t_userPK中delete,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, PSdelete, connection);
			System.out.println("userPKDao delete 调用了关闭数据库连接");
		}// finally关闭jdbc与数据库连接
	}// end method delete

	@Override
	public boolean update(UserPK userPK) {
		// TODO Auto-generated method stub
		PreparedStatement preparedStatement = null; // 关闭数据库连接insert和update和delete用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			preparedStatement = connection
					.prepareStatement("UPDATE t_userPK SET uLogUser = ?, uPassWord = ?,uName = ?,empUuid = ?  WHERE uuid = ? ");
			// Parameters start with 1
			preparedStatement.setString(1, userPK.getuLogUser());
			preparedStatement.setString(2, userPK.getuPassWord());
			preparedStatement.setString(3, userPK.getuName());
			preparedStatement.setString(4, userPK.getEmpUuid());
			preparedStatement.setString(5, userPK.getUuid());

			preparedStatement.executeUpdate();

			System.out.println("^^在执行t_userPK中的修改update");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("^^在执行t_userPK中update,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, preparedStatement, connection);
			System.out.println("userPKDao update 调用了关闭数据库连接");
		}// finally关闭jdbc与数据库连接
	}// end method update

	@Override
	public UserPK getByUuid(String uuid) {
		// TODO Auto-generated method stub
		UserPK userPKResult = new UserPK();
		Statement statement = null;// finally关闭数据库连接
		ResultSet rs = null;// 关闭数据库连接get和getlist会用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_userPK WHERE uuid ="
					+ "'" + uuid + "'");
			while (rs.next()) {
				UserPK userPK = new UserPK();
				userPK.setUuid(rs.getString("uuid"));
				userPK.setuLogUser(rs.getString("uLogUser"));
				userPK.setuPassWord(rs.getString("uPassWord"));
				userPK.setuName(rs.getString("uName"));
				userPK.setEmpUuid(rs.getString("empUuid"));
				userPKResult = userPK;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("t_userPK的getByUuid查询失败");
			UserPK aX = new UserPK();
			aX.setUuid("t_userPK失败返回的uuid");
			return aX;
		} finally {
			DBUtility.close(rs, statement, connection);
			System.out.println("userPKDao getByUuid 调用了关闭数据库连接");
		}// finally关闭jdbc与数据库连接

		return userPKResult;
	}// end method getByUuid

	@Override
	public ArrayList<UserPK> getList() {
		// TODO Auto-generated method stub
		ArrayList<UserPK> userPKList = new ArrayList<UserPK>();
		Statement statement = null;// finally关闭数据库连接
		ResultSet rs = null;// 关闭数据库连接get和getlist会用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_userPK");
			while (rs.next()) {
				UserPK userPK = new UserPK();
				userPK.setUuid(rs.getString("uuid"));
				userPK.setuLogUser(rs.getString("uLogUser"));
				userPK.setuPassWord(rs.getString("uPassWord"));
				userPK.setuName(rs.getString("uName"));
				userPK.setEmpUuid(rs.getString("empUuid"));
				userPKList.add(userPK);
			}
			return userPKList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ClaDaoImpl的getList查询失败");
			UserPK aX = new UserPK();
			aX.setUuid("UserPKDao的getList失败返回的uuid");
			ArrayList<UserPK> aXL = new ArrayList<UserPK>();
			aXL.add(aX);
			return aXL;
		} finally {
			DBUtility.close(rs, statement, connection);
			System.out.println("userPKDao getList 调用了关闭数据库连接");
		}// finally关闭jdbc与数据库连接

	}// emd method getList

	@Override
	public UserPK getByuLogUser(String uLogUser) {
		// TODO Auto-generated method stub
		UserPK userPKResult = new UserPK();
		Statement statement = null;// finally关闭数据库连接
		ResultSet rs = null;// 关闭数据库连接get和getlist会用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			statement = connection.createStatement();
			rs = statement
					.executeQuery("select * from t_userPK WHERE uLogUser ="
							+ "'" + uLogUser + "'");
			while (rs.next()) {
				UserPK userPK = new UserPK();
				userPK.setUuid(rs.getString("uuid"));
				userPK.setuLogUser(rs.getString("uLogUser"));
				userPK.setuPassWord(rs.getString("uPassWord"));
				userPK.setuName(rs.getString("uName"));
				userPK.setEmpUuid(rs.getString("empUuid"));
				userPKResult = userPK;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("t_userPK的getByUuid查询失败");
			UserPK aX = new UserPK();
			aX.setUuid("t_userPK失败返回的uuid");
			return aX;
		} finally {
			DBUtility.close(rs, statement, connection);
			System.out.println("getByuLogUser 调用了关闭数据库连接");
		}// finally关闭jdbc与数据库连接

		return userPKResult;
	}// end method getByUuid

	@Override
	public boolean deleteUserRole(String uuid) {
		// TODO Auto-generated method stub
		PreparedStatement PSdelete = null; // 关闭数据库连接insert和update和delete用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			// Parameters start with 1
			PSdelete = connection
					.prepareStatement("DELETE FROM t_userpk_role WHERE userPkid = ? ");
			PSdelete.setString(1, uuid);
			PSdelete.executeUpdate();

			System.out.println("^^在执行t_userPK中的删除delete用户的同时删除该用户关联的用户角色");
			daoFlag = true;
			return daoFlag;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("^^在执行t_userPK中delete,出现sql语法执行错误，请联系管理员!");
			daoFlag = false;
			return daoFlag;
		} finally {
			ResultSet rs = null;
			DBUtility.close(rs, PSdelete, connection);
			System.out.println("userPKDao deleteUserRole 调用了关闭数据库连接");
		}// finally关闭jdbc与数据库连接
	}

	@Override
	public ArrayList<UserPK> getList2(String uLogUser) {
		// TODO Auto-generated method stub
		ArrayList<UserPK> userPKList = new ArrayList<UserPK>();
		Statement statement = null;// finally关闭数据库连接
		ResultSet rs = null;// 关闭数据库连接get和getlist会用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			statement = connection.createStatement();
			rs = statement
					.executeQuery("SELECT tr.* FROM t_userpk_role tuserpk  LEFT JOIN t_userpk_role  tuser ON tuserpk.uuid  = tuser.userpkid LEFT JOIN t_role tr ON tr.uuid = tuserpk.roleid ");
			while (rs.next()) {
				UserPK userPK = new UserPK();
				userPK.setUuid(rs.getString("uuid"));
				userPK.setuLogUser(rs.getString("uLogUser"));
				userPK.setuPassWord(rs.getString("uPassWord"));
				userPK.setuName(rs.getString("uName"));
				userPK.setEmpUuid(rs.getString("empUuid"));
				userPKList.add(userPK);
			}
			return userPKList;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ClaDaoImpl的getList查询失败");
			UserPK aX = new UserPK();
			aX.setUuid("UserPKDao的getList失败返回的uuid");
			ArrayList<UserPK> aXL = new ArrayList<UserPK>();
			aXL.add(aX);
			return aXL;
		} finally {
			DBUtility.close(rs, statement, connection);
			System.out.println("userPKDao getList 调用了关闭数据库连接");
		}
	}

	@Override
	public List<String> getRole(String userPkid) {
		// TODO Auto-generated method stub
		List<String> roleList = new ArrayList<String>();// 存放角色id
		UserPK userPKResult = new UserPK();
		Statement statement = null;// finally关闭数据库连接
		ResultSet rs = null;// 关闭数据库连接get和getlist会用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			statement = connection.createStatement();
			rs = statement
					.executeQuery("select * from t_userPK_role WHERE userPkid ="
							+ "'" + userPkid + "'");
			while (rs.next()) {
				UserPK_Role userPKRole = new UserPK_Role();
				userPKRole.setUuid(rs.getString("uuid"));
				userPKRole.setUserPkid(rs.getString("userPkid"));
				userPKRole.setRoleid(rs.getString("roleid"));

				roleList.add(userPKRole.getRoleid());
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("t_userPK的getByUuid查询失败");

		} finally {
			DBUtility.close(rs, statement, connection);
			System.out.println("getByuLogUser 调用了关闭数据库连接");
		}// finally关闭jdbc与数据库连接
		return roleList;
	}

	@Override
	public UserPK getByUname(String nUame) {
		// TODO Auto-generated method stub
		UserPK userPKResult = new UserPK();
		Statement statement = null;// finally关闭数据库连接
		ResultSet rs = null;// 关闭数据库连接get和getlist会用到
		try {
			connection = DBUtility.open();// 打开数据库连接
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from t_userPK WHERE UName ="
					+ "'" + nUame + "'");
			while (rs.next()) {
				UserPK userPK = new UserPK();
				userPK.setUuid(rs.getString("uuid"));
				userPK.setuLogUser(rs.getString("uLogUser"));
				userPK.setuPassWord(rs.getString("uPassWord"));
				userPK.setuName(rs.getString("uName"));
				userPK.setEmpUuid(rs.getString("empUuid"));
				userPKResult = userPK;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("t_userPK的getByUuid查询失败");
			UserPK aX = new UserPK();
			aX.setUuid("t_userPK失败返回的uuid");
			return aX;
		} finally {
			DBUtility.close(rs, statement, connection);
			System.out.println("getByuLogUser 调用了关闭数据库连接");
		}// finally关闭jdbc与数据库连接

		return userPKResult;
	}
}
