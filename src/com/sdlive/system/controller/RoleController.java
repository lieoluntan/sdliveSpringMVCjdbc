package com.sdlive.system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sdlive.model.BackResult;
import com.sdlive.system.model.Role;
import com.sdlive.system.service.RoleResourceService;
import com.sdlive.system.service.RoleService;
import com.sdlive.system.service.UserPKService;
import com.sdlive.system.service.impl.RoleResourceServiceImpl;
import com.sdlive.system.service.impl.RoleServiceImp;
import com.sdlive.system.service.impl.UserPKServiceImpl;
import com.sdlive.utility.M_msg;
import com.sdlive.utility.T_DataControl;
import com.sdlive.utility.T_DataMap2Bean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * 
 * @author 罗成峰
 * @date 2017-12-26上午10:44:01
 * @version 1.0
 */
public class RoleController extends HttpServlet {

	private Connection connection;
	private RoleResourceService roleResourceService = new RoleResourceServiceImpl();
	Logger logger = Logger.getLogger(RoleController.class);
	RoleService roleService = new RoleServiceImp();
	UserPKService userPKService = new UserPKServiceImpl();
	BackResult backResult = new BackResult("信息值,默认", "请求值,默认", null);
	public M_msg m_msg = new M_msg();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String qqiu = request.getParameter("qqiu");

		if (qqiu.equals("test") || qqiu.equals("add") || qqiu.equals("delete")
				|| qqiu.equals("edit") || qqiu.equals("getOne")) {

			T_DataControl t_data = new T_DataControl();
			String str = t_data.getRequestPayload(request);
			Role role = new Role();
			if (str != null && str != "" && str.length() != 0) {
				Map<String, Object> map = t_data.JsonStrToMap(str);
				T_DataMap2Bean t_map2bean = new T_DataMap2Bean();
				role = t_map2bean.MapToUserRole(map);
			} else {
				System.out.println("前台传入post请求体数据为空，请联系管理员！");
			}
			qqiuChoice(qqiu, role);
		} else if (qqiu.equals("list")) {
			List list = new ArrayList();
			List rsList = new ArrayList();

			ArrayList<Role> roleList = new ArrayList<Role>();
			ArrayList<Role> newRoleList = new ArrayList<Role>();

			Role role = new Role();
			roleList = roleService.getList();

			for (Role one : roleList) {
				System.out.println(one.getName());
				rsList = roleResourceService.getListbyRoleUuid(one);
				one.setRsList(rsList);
				newRoleList.add(one);
			}
			System.out.println("完整的role列表" + newRoleList);

			String msg = "成功";
			backResult.setMessage("信息值：" + m_msg.getGetOneMsg());
			backResult.setData(newRoleList);

		} else if (qqiu.equals("add_batch")) {// 批量添加
			// start前台数据转换
			// 拿到本地JSON 并转成String
			T_DataControl t_data = new T_DataControl();
			String str = t_data.getRequestPayload(request);
			System.out.println(str);
			// Json的解析类对象
			JsonParser parser = new JsonParser();
			// 将JSON的String 转成一个JsonArray对象
			JsonArray jsonArray = parser.parse(str).getAsJsonArray();

			Gson gson = new Gson();
			ArrayList<Role> pr_List = new ArrayList<Role>();

			// 加强for循环遍历JsonArray
			for (JsonElement one : jsonArray) {
				// 使用GSON，直接转成Bean对象
				Role pr = gson.fromJson(one, Role.class);
				pr_List.add(pr);
			}
			System.out.println("数组转换出来的列表数据!!!!!" + pr_List);
			// end前台数据转换
			String count = roleService.insert_batch(pr_List);
			backResult.setMessage("信息值：成功" + "插入数量" + count);
			backResult.setQingqiu("add_batch查询列表");
			ArrayList<Role> resultList = new ArrayList<Role>();
			backResult.setData(resultList);
		} else {
			System.out.println("qqiu请求参数  " + qqiu + "  不规范");
		}
		Gson gson = new Gson();
		// 4 执行完qqiuChoice里面操作后的全局返回值backResult对象,转成json格式
		String back = gson.toJson(backResult);
		System.out.println("最后back值是：" + back);
		// 5 将json格式的back传给前台
		out.write(back);
		out.flush();
		out.close();

	}

	private void qqiuChoice(String qqiu, Role role) {
		boolean test = false;
		boolean add = false;
		boolean delete = false;
		boolean edit = false;
		boolean getOne = false;

		test = qqiu.equals("test");
		add = qqiu.equals("add");
		delete = qqiu.equals("delete");
		edit = qqiu.equals("edit");
		getOne = qqiu.equals("getOne");

		if (test) {
			logger.error("test");
			backResult.setMessage("信息值,测试成功");
			backResult.setQingqiu("test新增");
			ArrayList<String> resultList = new ArrayList<String>();
			resultList.add("内容值,测试成功1");
			resultList.add("内容值,测试成功2");
			resultList.add("内容值,测试成功3");
			backResult.setData(resultList);
		}
		if (add) {
			ArrayList resultList = new ArrayList();
			String result = roleService.insert(role);
			resultList.add(result);
			backResult.setMessage(result);
			backResult.setQingqiu("add新增");
			backResult.setData(resultList);
		}
		if (delete) {
			String result = roleService.delete(role.getUuid());
			ArrayList<String> resultList = new ArrayList<String>();
			resultList.add(result);
			backResult.setMessage("信息值：成功");
			backResult.setQingqiu("delete删除" + role.getUuid());
			backResult.setData(resultList);
		}
		if (edit) {
			System.out.println(role.getRemark());
			String result = roleService.update(role);
			ArrayList<String> resultList = new ArrayList<String>();
			resultList.add(result);
			backResult.setMessage("信息值：成功");
			backResult.setQingqiu("edit修改");
			backResult.setData(resultList);
		}
		if (getOne) {
			Role result = roleService.getByUuid(role.getUuid());
			ArrayList<Role> resultList = new ArrayList<Role>();
			resultList.add(result);
			backResult.setMessage("信息值：成功");
			backResult.setQingqiu("getOne查询单条记录");
			backResult.setData(resultList);
		}

	}
}