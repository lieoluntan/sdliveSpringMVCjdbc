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
import com.sdlive.model.Department;
import com.sdlive.model.TalkRoom;
import com.sdlive.service.TalkRoomService;
import com.sdlive.utility.T_DataControl;
import com.sdlive.utility.T_DataMap2Bean;

@Controller
@RequestMapping("/")
public class TalkRoomController {
    private static final long serialVersionUID = -1060747765670586355L;
	
	Logger logger = Logger.getLogger(DepartmentController.class);
	
	BackResult backResult = new BackResult("信息值,默认", "请求值,默认", null);
	@Resource(name="talkRoomServiceImpl")
	private TalkRoomService trs;
	
	 @RequestMapping("/aaTalkRoom")
	  public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response,String qqiu)
	      throws Exception {
	    // TODO Auto-generated method stub
	    response.setContentType("text/html;charset=utf-8");
	    PrintWriter out = response.getWriter();
	    
	    if (qqiu.equals("add") || qqiu.equals("delete") || qqiu.equals("edit")
	            || qqiu.equals("getOne") || qqiu.equals("on_off")) {
	        T_DataControl t_data = new T_DataControl();
	        String str = t_data.getRequestPayload(request);
	        TalkRoom department = new TalkRoom();
	        if (str != null && str != "" && str.length() != 0) {
	            Map<String, Object> map = t_data.JsonStrToMap(str);
	            T_DataMap2Bean t_map2bean = new T_DataMap2Bean();
	            department = t_map2bean.MapToTalkRoom(map);
	            department.setOpenAndclose((String) map.get("openAndclose"));
	        } else {
	            System.out.println("前台传入数据为空，请联前台传入post请求体数系管理员！");
	        }
	        qqiuchocie(qqiu, department);
	    } else if (qqiu.equals("list")) {
	        ArrayList<TalkRoom> resultList = trs.getList();
	        backResult.setMessage("信息值：成功");
	        backResult.setQingqiu("list查询列表");
	        backResult.setData(resultList);
	    }else if(qqiu.equals("test")){
	    	logger.error("日志打印测试TalkRoomController测试test方法,测试成功");      
	        backResult.setMessage("信息值,测试成功");
	        backResult.setQingqiu("test新增");
	        ArrayList<String> resultList = new ArrayList<String>();
	        resultList.add("内容值,测试成功1");
	        resultList.add("内容值,测试成功2");
	        resultList.add("内容值,测试成功3意向学员TalkRoomController");
	        backResult.setData(resultList);
	    }else {
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
	  
	  private void qqiuchocie(String qqiu, TalkRoom department) {
	    boolean add = false;
	    boolean delete = false;
	    boolean edit = false;
	    boolean getOne = false;
	    boolean on_off = false;

	    add = qqiu.equals("add");
	    delete = qqiu.equals("delete");
	    edit = qqiu.equals("edit");
	    getOne = qqiu.equals("getOne");
	    on_off = qqiu.equals("on_off");

	    if (add) {
	        String result = trs.insert(department);
	        ArrayList<String> resultList = new ArrayList<String>();
	        resultList.add(result);
	        backResult.setMessage("新增成功");
	        backResult.setQingqiu(result == "yes" ? "yes" : "no");
	        backResult.setData(resultList);
	    }
	    if (delete) {
	        String result = trs.delete(department);
	        System.out.println("删除功能传进来的uuid================="
	                + department.getUuid());
	        ArrayList<String> resultList = new ArrayList<String>();
	        resultList.add(result);
	        backResult.setMessage("信息值：成功");
	        backResult.setQingqiu("delete删除" + department.getUuid());
	        backResult.setData(resultList);
	    }
	    if (edit) {
	       String result = trs.update(department);
	        ArrayList<String> resultList = new ArrayList<String>();
	        resultList.add(result);
	        backResult.setMessage("修改成功");
	        backResult.setQingqiu(result == "yes" ? "yes" : "no");
	        backResult.setData(resultList);
	    }
	    if (getOne) {
	        ArrayList<TalkRoom> result = trs.getOne(department);
	        backResult.setMessage("信息值：成功");
	        backResult.setQingqiu("getOne查询单条记录");
	        backResult.setData(result);
	    }
	    if (on_off) {
	        String oAc = department.getOpenAndclose() + "";
	        String flagQqiu = "初始值";
	        String result = "初始值";
	        if (oAc.equals("open") || oAc.equals("close")) {
	            if (oAc.equals("open")) {
	                flagQqiu = "on";
	            }
	            if (oAc.equals("close")) {
	                flagQqiu = "off";
	            }
	            result = trs.updateOnOff(department);
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
	}
}
