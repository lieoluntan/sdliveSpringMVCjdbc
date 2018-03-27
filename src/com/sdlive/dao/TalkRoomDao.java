package com.sdlive.dao;

import java.lang.reflect.Array;
import java.util.ArrayList;

import com.sdlive.model.TalkRoom;

public interface TalkRoomDao {
	/**
	 * 新增房间
	 */
	public boolean insert(TalkRoom tr);
	/**
	 * 修改房间
	 */
	public boolean update(TalkRoom tr);
	/**
	 * 删除房间
	 */
	public boolean delete(TalkRoom tr);
	/**
	 * 查询房间
	 */
	public ArrayList<TalkRoom> getList();
	/**
	 * 修改开关
	 */
	public boolean updateOnOff(String uuid,String oac);
	/**
	 * 根据房间号查询
	 */
	public ArrayList<TalkRoom> getOne(TalkRoom tr);
}
