package com.sdlive.dao;

import java.util.ArrayList;
import java.util.List;

import com.sdlive.model.GoTalk;

/**
 *树袋老师
 * @author 作者 毕书富
 * @version 创建时间:2018-3-23 下午6:27:43
 * 类说明
 */
public interface GoTalkDao {
	//新增一键直播
	public boolean insert(GoTalk goTalk);
	//删除
	public boolean delete(String uuid);
	//一键登录开关
	public boolean updateOnOff(String uuid,String openAclose);
	//一键登录直播单个查询
	public GoTalk getByUuid(String uuid);
	//修改一键登录直播信息
	public boolean update(GoTalk goTalk);
	//验证一键登录直播是否存在
	public List<GoTalk> getGoTalkByUuid(GoTalk goTalk);
	
	//查询所有一键登录直播
	public ArrayList<GoTalk> getList();
}
