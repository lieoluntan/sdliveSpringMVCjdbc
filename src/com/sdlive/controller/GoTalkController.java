package com.sdlive.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.sdlive.model.BackResult;
import com.sdlive.model.GoTalk;
import com.sdlive.service.impl.GoTalkServiceImpl;
import com.sdlive.utility.T_DataControl;
import com.sdlive.utility.T_DataMap2Bean;

/**
 *树袋老师
 * @author 作者 毕书富
 * @version 创建时间:2018-3-23 下午6:29:39
 * 类说明
 */
@Controller
@RequestMapping("/")
public class GoTalkController{
	private static final long serialVersionUID = -1060747765670586355L;
	
	Logger logger = Logger.getLogger(GoTalkController.class);
	@Resource(name="GoTalkServiceImpl")
	private GoTalkServiceImpl goTalkServiceImpl;
	BackResult backResult = new BackResult("信息值,默认", "请求值,默认", null);
	
	@RequestMapping("/aaGoTalk")
	  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response,String qqiu)
	      throws Exception {
	    // TODO Auto-generated method stub
	    response.setContentType("text/html;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    
//	    String testqqiu = request.getParameter("qqiu");//测试handleRequest 默认获取

	    if (qqiu.equals("test") ||qqiu.equals("add") || qqiu.equals("delete") || qqiu.equals("edit")
	            || qqiu.equals("getOne") || qqiu.equals("on_off")) {
	        T_DataControl t_data = new T_DataControl();
	        String str = t_data.getRequestPayload(request);
	        GoTalk goTalk = new GoTalk();
	        if (str != null && str != "" && str.length() != 0) {
	            Map<String, Object> map = t_data.JsonStrToMap(str);
	            T_DataMap2Bean t_map2bean = new T_DataMap2Bean();
	            goTalk = t_map2bean.MapToGoTalk(map);
	            goTalk.setOpenAndclose((String) map.get("openAndclose"));
	        } else {
	            System.out.println("前台传入数据为空，请联前台传入post请求体数系管理员！");
	        }
	        qqiuchocie(qqiu, goTalk);
	    } else if (qqiu.equals("list")) {
	        ArrayList<GoTalk> resultList = goTalkServiceImpl.getList();
	        backResult.setMessage("信息值：成功");
	        backResult.setQingqiu("list查询列表");
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
	    
//	    return null;
	    ModelAndView mview = new ModelAndView(back);
	    return mview;

	  }//end method ModelAndView
	  
	  private void qqiuchocie(String qqiu, GoTalk goTalk) {
		boolean test = false;
	    boolean add = false;
	    boolean delete = false;
	    boolean edit = false;
	    boolean getOne = false;
	    boolean on_off = false;
	    
	    test = qqiu.equals("test");
	    add = qqiu.equals("add");
	    delete = qqiu.equals("delete");
	    edit = qqiu.equals("edit");
	    getOne = qqiu.equals("getOne");
	    on_off = qqiu.equals("on_off");

	    if (test) {
	        logger.error("日志打印测试YXRecordController测试test方法,测试成功");      
	        backResult.setMessage("信息值,默认");
	        backResult.setQingqiu("请求值,默认");
	    }
	    if (add) {
	    	String result = goTalkServiceImpl.insert(goTalk);
	        ArrayList<String> resultList = new ArrayList<String>();
	        resultList.add(result);
	        backResult.setMessage("信息值：成功");
	        backResult.setQingqiu("add新增");
	        backResult.setData(resultList);
	    }
	    if (delete) {
	    	String result =goTalkServiceImpl.delete(goTalk.getUuid());
	    	ArrayList<String> resultList = new ArrayList<String>();
	        resultList.add(result);
	        logger.error("日志打印测试YXRecordController测试delete方法,测试成功");      
	        backResult.setMessage("信息值,成功");
	        backResult.setQingqiu("请求值,delete删除");
	        backResult.setData(resultList);
	    }
	    if (on_off) {
	        String oAc = goTalk.getOpenAndclose() + "";
	        String flagQqiu = "初始值";
	        String result = "初始值";
	        if (oAc.equals("open") || oAc.equals("close")) {
	            if (oAc.equals("open")) {
	                flagQqiu = "on";
	            }
	            if (oAc.equals("close")) {
	                flagQqiu = "off";
	            }
	            result = goTalkServiceImpl.getonoff(goTalk);
	        } else {
	            flagQqiu = "err";
	            result = "操作失败：开关参数不规范" + "(" + oAc + "),联系前端开发";
	            logger.error("操作失败：开关参数不规范" + "(" + oAc + "),联系前端开发");
	        }
	        ArrayList<String> resultList = new ArrayList<String>();
	        resultList.add(result);
	        backResult.setMessage(result);
	        backResult.setQingqiu(flagQqiu);
	        backResult.setData(resultList);
	    }
	    if (getOne) {
	        GoTalk result = goTalkServiceImpl.getByUuid(goTalk
	                .getUuid());
	        ArrayList<GoTalk> resultList = new ArrayList<GoTalk>();
	        resultList.add(result);
	        backResult.setMessage("信息值：成功");
	        backResult.setQingqiu("getOne查询单条记录");
	        backResult.setData(resultList);
	    }
	    if (edit) {
	        String result = goTalkServiceImpl.update(goTalk);
	        ArrayList<String> resultList = new ArrayList<String>();
	        resultList.add(result);
	        backResult.setMessage("信息值：成功");
	        backResult.setQingqiu("edit修改");
	        backResult.setData(resultList);
	    }
	    
	}
	}//end class
