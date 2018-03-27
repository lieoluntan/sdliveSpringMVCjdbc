package com.sdlive.system.model;

/**
 * 
 * 树袋老师
 * 
 * @author xuerenjie
 * @version 创建时间：2017-12-26 上午10:44:08
 * 
 */
public class Resource {

	private String uuid;
	private String name;
	private String createDate;
	private String modifyDate;
	private String createPeople;
	private String modifyPeople;
	private String remark;

	@Override
	public String toString() {
		return "Resource [uuid=" + uuid + ", name=" + name + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate + ", createPeople="
				+ createPeople + ", modifyPeople=" + modifyPeople + ", remark="
				+ remark + "]";
	}

	public Resource(String uuid, String name, String createDate,
			String modifyDate, String createPeople, String modifyPeople,
			String remark) {
		super();
		this.uuid = uuid;
		this.name = name;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.createPeople = createPeople;
		this.modifyPeople = modifyPeople;
		this.remark = remark;
	}

	public Resource() {
		// TODO Auto-generated constructor stub
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
