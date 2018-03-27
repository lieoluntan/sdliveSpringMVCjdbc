package com.sdlive.system.model;
/**
 * 
 * @author 罗成峰
 * @date 2017-12-27下午6:38:56
 * @version 1.0
 */
public class UserPK_Role {

	private String uuid;
	private String userPkid;
	private String roleid;
	
	 public UserPK_Role() {
		    super();
		  }
	 public UserPK_Role(String uuid,String userPkid, String roleid) {
		    super();
		    this.uuid = uuid;
		    this.userPkid = userPkid;
		    this.roleid = roleid;
		  }
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUserPkid() {
		return userPkid;
	}
	public void setUserPkid(String userPkid) {
		this.userPkid = userPkid;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
}
