package com.sdlive.system.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdlive.model.BackResult;
import com.sdlive.system.model.UserPK;
import com.sdlive.system.service.UserPKService;
import com.sdlive.system.service.UserPKpassService;
import com.sdlive.system.service.impl.UserPKServiceImpl;
import com.sdlive.system.service.impl.UserPKpassServiceImpl;
import com.sdlive.utility.T_DataControl;
import com.sdlive.utility.T_DataMap2Bean;
import com.google.gson.Gson;
/*
 * @author 刘鑫
 * @date  ‎2018‎年‎1‎月‎24‎日‎ ‎17‎:‎18‎:‎31
 * 
 */
public class UserPKpassController extends HttpServlet{
	UserPKpassService ups=new UserPKpassServiceImpl();
	UserPKService userPKS=new UserPKServiceImpl();
	BackResult backResult = new BackResult("信息值,默认", "请求值,默认", null);
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		this.doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 1 获取url问号后面的Query 参数
		String qqiu = request.getParameter("qqiu");
		
		if(qqiu.equals("edit")){
			// 2 将前台json数据字符串转成实体对象
			T_DataControl t_data = new T_DataControl();
			String str = t_data.getRequestPayload(request);
			UserPK userPK = new UserPK();
			if(str != null && str != "" && str.length() != 0){
				Map<String, Object> map = t_data.JsonStrToMap(str);
				//单独取新密码
				String uNewPassWord = (String) map.get("uNewPassWord");
				System.out.println("单独取新密码"+uNewPassWord);
				//单独取新密码end
				T_DataMap2Bean t_map2bean = new T_DataMap2Bean();
				String oldPassword=(String) map.get("uPassWord");//取到旧密码
				String uLogUser=(String) map.get("uLogUser");
				userPK=userPKS.getUser(uLogUser);
				//System.out.println(userPK.getuPassWord()+"旧密码==========");
				ArrayList<String> resultList = new ArrayList<String>();
				if(oldPassword.equals(userPK.getuPassWord()) && !oldPassword.equals("") && !uNewPassWord.equals("")){
					userPK.setuPassWord(uNewPassWord);
					boolean result=ups.updatePassword(userPK);
					if(result){
						resultList.add("true");
						backResult.setMessage("信息值：成功");
						backResult.setQingqiu("edit修改");
						backResult.setData(resultList);
						}else{
							resultList.add("false");
							backResult.setMessage("信息值：失败");
							backResult.setQingqiu("edit修改");
							backResult.setData(resultList);
						}
				}else{
					resultList.add("false");
					backResult.setMessage("修改密码失败，用户名或密码不正确：用户名("+userPK.getuLogUser()+")密码("+oldPassword+")");
					backResult.setQingqiu("edit修改");
					backResult.setData(resultList);
				}
				
			}else{
				System.out.println("前台传入post请求体数据为空，请联系管理员！");
			}
		}else{
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
}
