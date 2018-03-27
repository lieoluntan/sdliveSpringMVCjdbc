package com.sdlive.system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.sdlive.controller.DepartmentController;
import com.sdlive.model.BackResult;
import com.sdlive.system.model.Resource;
import com.sdlive.system.service.ResourceService;
import com.sdlive.system.service.impl.ResourceServiceImpl;
import com.sdlive.utility.M_msg;
import com.sdlive.utility.T_DataControl;
import com.sdlive.utility.T_DataMap2Bean;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

/**
 * 
 * 树袋老师
 * 
 * @author xuerenjie
 * @version 创建时间：2017-12-26 下午5:27:16
 * 
 */
public class ResourceController extends HttpServlet {
	ResourceService resourceService = new ResourceServiceImpl();
	BackResult backResult = new BackResult("信息值,默认", "请求值,默认", null);
	public M_msg m_msg = new M_msg();
	Logger logger = Logger.getLogger(ResourceController.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// TODO doPost
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		// 1 获取url问号后面的Query 参数
		String qqiu = request.getParameter("qqiu");

		if (qqiu.equals("test") || qqiu.equals("add") || qqiu.equals("delete")
				|| qqiu.equals("edit") || qqiu.equals("getOne")) {
			// 2 将前台json数据字符串转成实体对象
			T_DataControl t_data = new T_DataControl();
			String str = t_data.getRequestPayload(request);

			Resource resource = new Resource();
			if (str != null && str != "" && str.length() != 0) { // 非空判断，防止前台传空报500服务器错误中的空指针
				Map<String, Object> map = t_data.JsonStrToMap(str);
				T_DataMap2Bean t_map2bean = new T_DataMap2Bean();
				resource = t_map2bean.MapToResource(map);
			} else {
				System.out.println("前台传入post请求体数据为空，请联系管理员！");
			}

			// 3 执行qqiu里面的增或删或改或查 的操作
			qqiuChoice(qqiu, resource);
		} else if (qqiu.equals("list")) {
			// TODO 待完成
			ArrayList<Resource> resultList = resourceService.getList();
			backResult.setMessage("信息值：成功");
			backResult.setQingqiu("list查询列表");
			backResult.setData(resultList);

		} else if (qqiu.equals("add_batch")) {// 批量添加
			// start前台数据转换
			// 拿到本地JSON 并转成String
			T_DataControl t_data = new T_DataControl();
			String str = t_data.getRequestPayload(request);
			if (str != null && str != "" && str.length() != 0) {
				System.out.println(str);
				// Json的解析类对象
				JsonParser parser = new JsonParser();
				// 将JSON的String 转成一个JsonArray对象
				JsonArray jsonArray = parser.parse(str).getAsJsonArray();

				Gson gson = new Gson();
				ArrayList<Resource> pr_List = new ArrayList<Resource>();

				// 加强for循环遍历JsonArray
				for (JsonElement one : jsonArray) {
					// 使用GSON，直接转成Bean对象
					Resource pr = gson.fromJson(one, Resource.class);
					pr_List.add(pr);
				}
				System.out.println("数组转换出来的列表数据!!!!!" + pr_List);
				// end前台数据转换
				String count = resourceService.insert_batch(pr_List);
				backResult.setMessage("信息值：成功" + "插入数量" + count);
				backResult.setQingqiu("add_batch查询列表");
				ArrayList<Resource> resultList = new ArrayList<Resource>();
				backResult.setData(resultList);
			} else {
				backResult.setMessage("信息值：失败,插入数量:0");
				backResult.setQingqiu("add_batch批量添加");
				ArrayList<String> resultList = new ArrayList<String>();
				resultList.add("前台传入的数据为空,或格式不对,请联系前端开发!");
				backResult.setData(resultList);
			}

		}

		else {
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

	private String getRequestPayload(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	private void qqiuChoice(String qqiu, Resource resource) {
		// TODO Auto-generated method stub
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
			backResult.setMessage("test测试成功");
			backResult.setQingqiu("test新增");
			ArrayList<String> resultList = new ArrayList<String>();
			resultList.add("内容值,测试成功1");
			resultList.add("内容值,测试成功2");
			resultList.add("内容值,测试成功3");
			backResult.setData(resultList);
		}
		if (add) {
			String result = resourceService.insert(resource);
			ArrayList<String> resultList = new ArrayList<String>();
			resultList.add(result);
			backResult.setMessage("信息值：" + result);
			backResult.setQingqiu("add新增");
			backResult.setData(resultList);
		}
		if (delete) {
			String result = resourceService.delete(resource.getUuid());
			ArrayList<String> resultList = new ArrayList<String>();
			resultList.add(result);
			backResult.setMessage("信息值：成功");
			backResult.setQingqiu("delete删除" + resource.getUuid());
			backResult.setData(resultList);
		}
		if (edit) {
			String result = resourceService.update(resource);
			ArrayList<String> resultList = new ArrayList<String>();
			resultList.add(result);
			backResult.setMessage("信息值：成功");
			backResult.setQingqiu("edit修改");
			backResult.setData(resultList);
		}
		if (getOne) {
			Resource result = resourceService.getByUuid(resource.getUuid());
			ArrayList<Resource> resultList = new ArrayList<Resource>();
			resultList.add(result);
			backResult.setMessage("信息值：成功");
			backResult.setQingqiu("getOne查询单条记录");
			backResult.setData(resultList);
		}
	}
}
