package com.sdlive.service;

import java.util.ArrayList;

import com.sdlive.model.GoTalk;

/**
 *树袋老师
 * @author 作者 毕书富
 * @version 创建时间:2018-3-23 下午6:28:49
 * 类说明
 */
public interface GoTalkService {
	//新增一键直播
	public String insert(GoTalk goTalk);
	//删除
	public String delete(String uuid);
	//一键直播开关
	String getonoff(GoTalk goTalk);
	//查询单个一键登录直播
	GoTalk getByUuid(String uuid);
	//修改一键登录直播
	String update(GoTalk goTalk);
	//获取一键登录直播列表
	ArrayList<GoTalk> getList();
}
