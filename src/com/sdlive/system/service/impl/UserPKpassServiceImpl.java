package com.sdlive.system.service.impl;

import com.sdlive.system.dao.UserPKpassDao;
import com.sdlive.system.dao.impl.UserPKpassDaoImpl;
import com.sdlive.system.model.UserPK;
import com.sdlive.system.service.UserPKpassService;
/*
 * 
 * @author 刘鑫
 * @date  ‎2018‎年‎1‎月‎24‎日‎ ‎17‎:‎08‎:‎38
 */
public class UserPKpassServiceImpl implements UserPKpassService {
	UserPKpassDao upd=new UserPKpassDaoImpl();
	
	@Override
	public boolean updatePassword(UserPK userPK) {
		// TODO Auto-generated method stub
		
		return upd.updateUserPassword(userPK);
	}

}
