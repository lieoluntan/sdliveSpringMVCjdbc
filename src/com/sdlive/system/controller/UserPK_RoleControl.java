package com.sdlive.system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdlive.model.BackResult;
import com.sdlive.system.model.UserPK_Role;
import com.sdlive.system.service.UserPK_RoleService;
import com.sdlive.system.service.impl.UserPK_RoleServiceImpl;
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
 * @date 2017-12-27下午6:37:49
 * @version 1.0
 */
public class UserPK_RoleControl {

	UserPK_RoleService userPK_roleService = new UserPK_RoleServiceImpl();
	  BackResult backResult = new BackResult("信息值,默认", "请求值,默认", null);
	  public M_msg m_msg = new M_msg();
	  
	  protected void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {

	    this.doPost(request, response);
	  }// end doGet
	  
	  protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
	    // TODO doPost
	    response.setContentType("text/html;charset=utf-8");
	    PrintWriter out = response.getWriter();

	    // 1 获取url问号后面的Query 参数
	    String qqiu = request.getParameter("qqiu");
	    String userPkid = request.getParameter("userPkid");

	    if (qqiu.equals("test") || qqiu.equals("add") || qqiu.equals("delete") || qqiu.equals("edit")
	        || qqiu.equals("getOne") || qqiu.equals("deleteByCour")|| qqiu.equals("getListByEmp")) {
	      // 2 将前台json数据字符串转成实体对象
	      T_DataControl t_data = new T_DataControl();
	      String str = t_data.getRequestPayload(request); //固定，基本不改
	      UserPK_Role userPK_role = new UserPK_Role();//(根据实体类改)
	      if (str != null && str != "" && str.length() != 0) { // 非空判断，防止前台传空报500服务器错误中的空指针
	        Map<String, Object> map = t_data.JsonStrToMap(str); //固定，基本不改
	        T_DataMap2Bean t_map2bean = new T_DataMap2Bean();
	        userPK_role = t_map2bean.MapToUserPK_Role(map); //(根据实体类改)
	      } else {
	        System.out.println("前台传入post请求体数据为空，请联系管理员！");
	      }

	      // 3 执行qqiu里面的增或删或改或查 的操作
	      qqiuChoice(qqiu, userPK_role);
	    } else if (qqiu.equals("getListByCour")) {
	      // TODO 待完成
	      ArrayList<UserPK_Role> resultList = userPK_roleService.getListByuse(userPkid);
	      backResult.setMessage("信息值：成功");
	      backResult.setQingqiu("getListByCour课程员工查询单条记录");
	      backResult.setData(resultList);
	    }
	    //批量新增
	    else if(qqiu.equals("add_batch")){
	    	T_DataControl t_date = new T_DataControl();
	    	String str = t_date.getRequestPayload(request);
	    	System.out.println(str);
	    	JsonParser parser = new JsonParser();
	    	JsonArray jsonArray = parser.parse(str).getAsJsonArray();
	    	
	    	Gson gson = new Gson();
	    	ArrayList<UserPK_Role> pr_List = new ArrayList<UserPK_Role>();
	    	for(JsonElement one : jsonArray){
	    		UserPK_Role pr = gson.fromJson(one, UserPK_Role.class);
	    		pr_List.add(pr);
	    	}
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

	  }// end method doPost 主入口
	  
	  private void qqiuChoice(String qqiu, UserPK_Role userPK_Role) {
	    // TODO Auto-generated method stub
	    boolean test = false;
	    boolean add = false;
	    boolean delete = false;
	    boolean edit = false;
	    boolean getOne = false;
	    boolean deleteByuse = false;
	    boolean getListByContr = false;
	    boolean getListByuse = false;
	    
	    test = qqiu.equals("test");
	    add = qqiu.equals("add");
	    delete = qqiu.equals("delete");
	    edit = qqiu.equals("edit");
	    getOne = qqiu.equals("getOne");
	    deleteByuse = qqiu.equals("getListByuse");
	    getListByContr = qqiu.equals("getListByContr");
	    getListByuse = qqiu.equals("getListByuse");
	     if (test) {
	      backResult.setMessage("信息值,测试成功");
	      backResult.setQingqiu("test新增");
	      ArrayList<String> resultList = new ArrayList<String>();
	      resultList.add("UserPK_Role内容值,测试成功1");
	      resultList.add("UserPK_Role内容值,测试成功2");
	      resultList.add("UserPK_Role内容值,测试成功3");
	      backResult.setData(resultList);
	    }
	    if (add) {
	      String result = userPK_roleService.insert(userPK_Role);
	      ArrayList<String> resultList = new ArrayList<String>();
	      resultList.add(result);
	      m_msg = userPK_roleService.getMsg();
	      backResult.setMessage("信息值  "+m_msg.getAddMsg());
	      backResult.setQingqiu("add新增");
	      backResult.setData(resultList);
	      m_msg.cleanMsg();
	    }
	    if (delete) {
	    System.out.println("aaa");
	     String result = userPK_roleService.delete(userPK_Role.getUuid());
	     ArrayList<String> resultList = new ArrayList<String>();
	     backResult.setMessage("信息值：成功");
	      backResult.setQingqiu("delete删除" + userPK_Role.getUuid());
	      backResult.setData(resultList);
	    }
	    if (deleteByuse) {
	      String result = userPK_roleService.deleteByuse(userPK_Role.getUserPkid());
	  
	      ArrayList<String> resultList = new ArrayList<String>();
	      resultList.add(result);
	      backResult.setMessage("信息值：成功");
	      backResult.setQingqiu("delete删除" + userPK_Role.getUuid());
	      backResult.setData(resultList);
	    }
	    if(getListByuse){
	      
	    }
	    if(getListByContr){
	      ArrayList<UserPK_Role> resultList = userPK_roleService.getListByContr(userPK_Role.getRoleid());
	      backResult.setMessage("信息值：成功");
	      backResult.setQingqiu("getOne查询单条记录");
	      backResult.setData(resultList);
	    }
	  }// end method qqiuChoice
	
}
