package com.sdlive.system.model;

import java.util.List;

/**
 * 
 * @author 罗成峰
 * @date 2017-12-27上午11:06:46
 * @version 1.0
 */
public class Role {

	private String uuid;
	private String name;
	private String createDate;
	private String modifyDate;
	private String createPeople;
	private String modifyPeople;
	private String remark;
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	private List<String> rsList;//存放角色id
	
	public Role(){
		
	}
	
	public List<String> getRsList() {
		return rsList;
	}
	public void setRsList(List<String> rsList) {
		this.rsList = rsList;
	}
	public Role(String uuid,String name,String createDate,String modifyDate,String createPeople,String modifyPeople,String remark,
			List<String> rsList){
		super();
		this.uuid = uuid;
		this.name = name;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.createPeople = createPeople;
		this.modifyPeople = modifyPeople;
		this.remark = remark;
		this.rsList = rsList;
	}
	@Override
	public String toString() {
		return "Role [uuid=" + uuid + ", name=" + name
				+ ", createDate=" + createDate + ", modifyDate="
				+ modifyDate + ", createPeople=" + createPeople
				+ ", modifyPeople=" + modifyPeople + ", remark=" + remark
				+ ", rsList=" + rsList + "]";
	}
	
	 public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getCreatePeople() {
		return createPeople;
	}
	public void setCreatePeople(String createPeople) {
		this.createPeople = createPeople;
	}
	public String getModifyPeople() {
		return modifyPeople;
	}
	public void setModifyPeople(String modifyPeople) {
		this.modifyPeople = modifyPeople;
	}
}
