package com.sdlive.system.model;

/**
 * 
 * 树袋老师
 * 
 * @author xuerenjie
 * @version 创建时间：2017-12-27 上午10:52:32 角色资源表，角色与资源之间的权限关系
 */
public class RoleResource {
	private String uuid;// 主键
	private String roleid;// 角色id
	private String resourceid;// 资源id

	public RoleResource(String uuid, String roleid, String resourceid) {
		super();
		this.uuid = uuid;
		this.roleid = roleid;
		this.resourceid = resourceid;
	}

	public RoleResource() {

	}

	@Override
	public String toString() {
		return "角色资源 [uuid=" + uuid + ", roleid=" + roleid + ", resourceid="
				+ resourceid + "]";
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getResourceid() {
		return resourceid;
	}

	public void setResourceid(String resourceid) {
		this.resourceid = resourceid;
	}

}
