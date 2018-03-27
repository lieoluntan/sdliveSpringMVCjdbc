package com.sdlive.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sdlive.dao.GoTalkDao;
import com.sdlive.model.GoTalk;
import com.sdlive.service.GoTalkService;
import com.sdlive.utilityTalk.HttpUtils;
import com.sdlive.utilityTalk.MD5Util;
import com.sdlive.utilityTalk.ParamsBean;
import com.sdlive.utilityTalk.RequestCallback;

/**
 *树袋老师
 * @author 作者 毕书富
 * @version 创建时间:2018-3-23 下午6:29:04
 * 类说明
 */
@Service("GoTalkServiceImpl")
public class GoTalkServiceImpl implements GoTalkService{
	@Resource(name="GoTalkDaoImpl")
	private GoTalkDao goTalkDao;
	Logger logger = Logger.getLogger(GoTalkServiceImpl.class);
	MD5Util md5=new MD5Util();
	
	@Override
	public String insert(GoTalk goTalk) {
		// TODO Auto-generated method stub
		goTalk.setUuid(UUID.randomUUID().toString());
		
		GoTalk defaultGoTalk=new GoTalk();
		
		goTalk.setKeyTalk(defaultGoTalk.getKeyTalk());
		goTalk.setDomain(defaultGoTalk.getDomain());
		goTalk.setSerialTalk(defaultGoTalk.getSerialTalk());
		goTalk.setUsertype(defaultGoTalk.getUsertype());
		goTalk.setPid(defaultGoTalk.getPid());
		
		String authStr =goTalk.getKeyTalk()+goTalk.getTs()+goTalk.getSerialTalk()+goTalk.getUsertype();
		String auth=md5.MD5(authStr);
		goTalk.setAuth(auth);
		
		getserverarea();
		String servicename="asa";
		goTalk.setServername(servicename);
		
		String username = md5.encode(goTalk.getUsername());
		List<ParamsBean> list = new ArrayList<ParamsBean>();
		
		String Url = "http://global.talk-cloud.net/WebAPI/";
		String url = Url + "entry/";
		list.add(new ParamsBean("domain",goTalk.getDomain())); 
		list.add(new ParamsBean("serial",goTalk.getSerialTalk())); 
		list.add(new ParamsBean("username",username)); 
		list.add(new ParamsBean("usertype",goTalk.getUsertype()));
		list.add(new ParamsBean("pid", goTalk.getPid()));
		list.add(new ParamsBean("ts",goTalk.getTs())); 
		list.add(new ParamsBean("auth",auth)); 
		
		String pwd=goTalk.getUserpassword();
		String userpassword = null;
		try {
			userpassword = md5.encrypts(pwd, goTalk.getKeyTalk());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		list.add(new ParamsBean("userpassword",userpassword)); 
		list.add(new ParamsBean("extradata", goTalk.getExtradata()));
		list.add(new ParamsBean("servername", servicename));
		list.add(new ParamsBean("jumpurl", "crm.shudailaoshi.com"));
		String urlRemark=HttpUtils.httpSend(url, list, new RequestCallback()
		{
			public void callBack(String res)
			{
				//System.out.println(res);
			}
		});
		list.add(new ParamsBean("urlRemark", urlRemark));
		goTalk.setUrlRemark(urlRemark);
		boolean daoFlag=goTalkDao.insert(goTalk);
		if (daoFlag) {
			return goTalk.getUuid();
		} else {
		  logger.error("插入不成功,dao层执行有出错地方,请联系管理员");
			return "插入不成功,dao层执行有出错地方,请联系管理员";
		}
	}

	
	public static void getserverarea() {
		String url = "http://global.talk-cloud.net/ClientAPI/getserverarea";
		HttpUtils.httpSends(url, new RequestCallback() {
			public void callBack(String res) {
				System.out.println(res);
			}
		});
	}


	@Override
	public String delete(String uuid) {
		// TODO Auto-generated method stub
		if (uuid != null && uuid != "") {
			boolean daoFlag = goTalkDao.delete(uuid);
			if (daoFlag) {
				return uuid;
			} else {
			  logger.error("删除不成功,dao层执行有出错地方,请联系管理员");
				return "删除不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "GoTalkServiceImpl delete方法中的uuid为空，或格式不正确，请重新选择";
			System.out.println(msg);
			return msg;
		}
	}


	@Override
	public String getonoff(GoTalk goTalk) {
		// TODO Auto-generated method stub
		String uuid = goTalk.getUuid();
		if (uuid != null && uuid != "") {
			String oac = goTalk.getOpenAndclose();
			boolean daoFlag = goTalkDao.updateOnOff(uuid, oac);
			if (daoFlag) {
			  
				return "操作成功";
			} else {
			  logger.error(oac+"开关失败,dao层执行有出错地方,请联系管理员");
				return "操作失败,dao层执行有出错地方,请联系管理员";
			}
		} else {
			String msg = "GoTalkServiceImpl getonoff方法中的uuid为空，或格式不正确，请重新选择";
			logger.error(msg);
			System.out.println(msg);
			return msg;
		}
	}


	@Override
	public GoTalk getByUuid(String uuid) {
		// TODO Auto-generated method stub
		if (uuid != null && uuid != "") {
			return goTalkDao.getByUuid(uuid);
		} else {
			System.out
					.println("GoTalkServiceImpl getByUuid方法中的uuid为空，或格式不正确，请联系管理员");
			return new GoTalk();
		}
	}


	@Override
	public String update(GoTalk goTalk) {
		// TODO Auto-generated method stub
		List<GoTalk> list = goTalkDao.getGoTalkByUuid(goTalk);
		boolean daoFlag=false;
		if (list!=null&&list.size()>0) {
			String uuid = goTalk.getUuid();
			if (uuid != null && uuid != "") {
				daoFlag = goTalkDao.update(goTalk);
			}
			if (daoFlag) {
				return uuid;
			} else {
			  logger.error("修改不成功,dao层执行有出错地方,请联系管理员");
				return "修改不成功,dao层执行有出错地方,请联系管理员";
			}
		} else {
				String msg = "GoTalkServiceImpl update方法中的uuid为空，或格式不正确，请重新选择";
				System.out.println(msg);
				return msg;
			}
		}


	@Override
	public ArrayList<GoTalk> getList() {
		// TODO Auto-generated method stub
		return goTalkDao.getList();
	}
	
}
