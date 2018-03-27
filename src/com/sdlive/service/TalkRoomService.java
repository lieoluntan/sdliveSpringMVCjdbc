package com.sdlive.service;

import java.util.ArrayList;

import com.sdlive.model.TalkRoom;

public interface TalkRoomService {
	/**
	 * 新增
	 */
	String insert(TalkRoom tr);
	/**
	 * 修改
	 */
	public String update(TalkRoom tr);
	/**
	 * 删除房间
	 */
	public String delete(TalkRoom tr);
	/**
	 * 查询房间
	 */
	public ArrayList<TalkRoom> getList();
	/**
	 * 修改开关
	 */
	public String updateOnOff(TalkRoom tr);
	/**
	 * 根据房间号查询
	 */
	public ArrayList<TalkRoom> getOne(TalkRoom tr);
}
