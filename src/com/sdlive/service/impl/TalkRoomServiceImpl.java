package com.sdlive.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sdlive.dao.TalkRoomDao;
import com.sdlive.model.TalkRoom;
import com.sdlive.service.TalkRoomService;
import com.sdlive.utilityTalk.HttpUtils;
import com.sdlive.utilityTalk.MD5Util;
import com.sdlive.utilityTalk.ParamsBean;
import com.sdlive.utilityTalk.RequestCallback;
@Service("talkRoomServiceImpl")
public class TalkRoomServiceImpl implements TalkRoomService{
	
	@Resource(name="talkRoomDaoImpl")
	private TalkRoomDao trd;
	Logger logger = Logger.getLogger(TalkRoomServiceImpl.class);
	@Override
	public String insert(TalkRoom tr) {
		// TODO Auto-generated method stub
		    String url = roomadd(tr);
		    tr.setUrlRemark(url);
			tr.setUuid(null);
			tr.setUuid(UUID.randomUUID().toString());
			tr.setSerialTalk(serialTalk);
			System.out.println("^^在TalkRoomServiceImpl收到数据 ："
					+ tr.toString() + "收到结束!");
			boolean daoFlag = trd.insert(tr);
			if (daoFlag) {
				return tr.getUuid();
			} else {
			  logger.error("插入不成功,dao层执行有出错地方,请联系管理员");
				return "插入不成功,dao层执行有出错地方,请联系管理员";
			}
	}
	@Override
	public String update(TalkRoom tr) {
		// TODO Auto-generated method stub
		if (tr.getSerialTalk() != 0 ) {
			  String url = roomdify(tr);
			  tr.setUrlRemark(url);
			boolean daoFlag = trd.update(tr);
			if (daoFlag) {
				return tr.getSerialTalk()+"";
			} else {
			  logger.error("修改不成功,dao层执行有出错地方,请联系管理员");
				return "修改不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "DepartmentServiceImpl update方法中的SerialTalk为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}
	public void roomdelete(TalkRoom tr){
		 List<ParamsBean> list = new ArrayList<ParamsBean>();
			String url = Url + "roomdelete/";
			list.add(new ParamsBean("key",tr.getKeyTalk())); //必填 企业id authkey 
			list.add(new ParamsBean("serial",tr.getSerialTalk()));//必填 房间号     
			HttpUtils.httpSend(url,list,new RequestCallback()
			{
				public void callBack(String res)
				{
					System.out.println(res);
					testCount("roomdelete",res);
				}
			});
	 }
	@Override
	public String delete(TalkRoom tr) {
		// TODO Auto-generated method stub
		if (tr.getSerialTalk() != 0 ) {
			roomdelete(tr);
			boolean daoFlag = trd.delete(tr);
			if (daoFlag) {
				return tr.getSerialTalk()+"";
			} else {
			  logger.error("删除不成功,dao层执行有出错地方,请联系管理员");
				return "删除不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "TalkRoomServiceImpl delete方法中的getSerialTalk为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}
	@Override
	public ArrayList<TalkRoom> getList() {
		// TODO Auto-generated method stub
		return trd.getList();
	}
	@Override
	public String updateOnOff(TalkRoom tr) {
		// TODO Auto-generated method stub
		if (tr.getUuid() != null && tr.getUuid() != "") {
			String oac = tr.getOpenAndclose();
			boolean daoFlag = trd.updateOnOff(tr.getUuid(), oac);
			if (daoFlag) {
			  
				return "操作成功";
			} else {
			  logger.error(oac+"开关失败,dao层执行有出错地方,请联系管理员");
				return "操作失败,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "DepartmentServiceImpl getonoff方法中的uuid为空，或格式不正确，请重新选择";
			logger.error(msg);
			System.out.println(msg);
			return msg;
		}
	}
	@Override
	public ArrayList<TalkRoom> getOne(TalkRoom tr) {
		// TODO Auto-generated method stub
		return trd.getOne(tr);
	}
	private static Map<String,String> errorDate = new HashMap<String,String>();
	private static String Url = "http://global.talk-cloud.net/WebAPI/";
	private static MD5Util md5 = null;
	private static int serialTalk = 0;
	public static void testCount(String Interface,String str){
		JSONObject object = JSONObject.fromObject(str);
		String value= object.getString("serial");
		if(value!=null || value != ""){
			serialTalk = Integer.parseInt(value);
			System.out.println(value);
		}else{
			//			System.out.println("接口="+Interface+":"+value);  
			System.out.println("获取房间失败");
			errorDate.put(Interface, value);
		}
	}
	public String roomdify(TalkRoom tr){
		md5 = new MD5Util();
	    List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "roomcreate/";
		String roomName = md5.encode(tr.getRoomNameTalk()+"&&&");
		list.add(new ParamsBean("key",tr.getKeyTalk())); //必填 企业id  authkey
		list.add(new ParamsBean("roomname",roomName)); //必填 房间名称必填；如果有中文请使用UTF8编码，特殊字符需使用urlencode转义
		list.add(new ParamsBean("roomtype", tr.getRoomtypeTalk()));//0:1对1   3：1对多
		/*int starts =time(); //当前时间戳
		int ends = starts+24*3600;*/
		list.add(new ParamsBean("starttime",tr.getStarttimeTalk())); //必填 房间开始时间戳(精确到秒)
		list.add(new ParamsBean("endtime",tr.getEndtimeTalk())); //必填 房间结束时间(精确到秒)
		list.add(new ParamsBean("chairmanpwd",tr.getChairmanpwd())); //必填 老师密码	必填，4=<长度<=16	
		list.add(new ParamsBean("assistantpwd", tr.getAssistantpwd()));//必填，助教密码4=<长度<=16
		list.add(new ParamsBean("patrolpwd", tr.getPatrolpwd()));//必填，巡课密码4=<长度<=16
		list.add(new ParamsBean("passwordrequired",tr.getPasswordrequired())); //选填 学生进入房间是否需要密码  0:否、1:是
		list.add(new ParamsBean("confuserpwd",tr.getConfuserpwd()));  //学生密码 passwordrequired = 1 时必填(4=<长度<=16)或者allowsidelineuser = 1 时必填
		list.add(new ParamsBean("videotype","1"));  //选填 视频分辨率 0：176x144   1：320x240   2：640x480   3：1280x720   4：1920x1080
		list.add(new ParamsBean("videoframerate",10));  //帧率10,15,20,25,30
		list.add(new ParamsBean("autoopenav",1));//0: 不自动开启  1：自动开启
		String urf = HttpUtils.httpSend(url, list, new RequestCallback() {

				public void callBack(String res) 
				{
					System.out.println(res);
					testCount("roommodify",res);

				}
		});
		return urf;
	 }
	public String roomadd(TalkRoom tr){
		md5 = new MD5Util();
	    List<ParamsBean> list = new ArrayList<ParamsBean>();
		String url = Url + "roomcreate/";
		String roomName = md5.encode(tr.getRoomNameTalk()+"&&&");
		list.add(new ParamsBean("key",tr.getKeyTalk())); //必填 企业id  authkey
		list.add(new ParamsBean("roomname",roomName)); //必填 房间名称必填；如果有中文请使用UTF8编码，特殊字符需使用urlencode转义
		list.add(new ParamsBean("roomtype", tr.getRoomtypeTalk()));//0:1对1   3：1对多
		/*int starts =time(); //当前时间戳
		int ends = starts+24*3600;*/
		list.add(new ParamsBean("starttime",tr.getStarttimeTalk())); //必填 房间开始时间戳(精确到秒)
		list.add(new ParamsBean("endtime",tr.getEndtimeTalk())); //必填 房间结束时间(精确到秒)
		list.add(new ParamsBean("chairmanpwd",tr.getChairmanpwd())); //必填 老师密码	必填，4=<长度<=16	
		list.add(new ParamsBean("assistantpwd", tr.getAssistantpwd()));//必填，助教密码4=<长度<=16
		list.add(new ParamsBean("patrolpwd", tr.getPatrolpwd()));//必填，巡课密码4=<长度<=16
		list.add(new ParamsBean("passwordrequired",tr.getPasswordrequired())); //选填 学生进入房间是否需要密码  0:否、1:是
		list.add(new ParamsBean("confuserpwd",tr.getConfuserpwd()));  //学生密码 passwordrequired = 1 时必填(4=<长度<=16)或者allowsidelineuser = 1 时必填
		list.add(new ParamsBean("videotype","1"));  //选填 视频分辨率 0：176x144   1：320x240   2：640x480   3：1280x720   4：1920x1080
		list.add(new ParamsBean("videoframerate",10));  //帧率10,15,20,25,30
		list.add(new ParamsBean("autoopenav",1));//0: 不自动开启  1：自动开启
		//		list.add(new ParamsBean("thirdroomid", "222111"));//这个是只有哒哒用
		String urf  = HttpUtils.httpSend(url, list, new RequestCallback()
		{
			public void callBack(String res)
			{
				System.out.println(res);
			    testCount("roomcreate",res);
			}
		});
		return urf;
 }
}
