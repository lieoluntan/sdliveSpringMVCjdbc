package com.sdlive.model;

public class Employee {
  
  private String uuid;
  private String createDate;
  private String modifyDate;
  private String createPeople;
  private String modifyPeople;
  
  private String name;
  private String empNum;
  private String phone;
  private String depart;
  private String job;
  private String permissionTempl;
  private String remark;
  
  private String claTeacher;//前台选项，是否为班主任
  private String sex;
  private String org;
  private String workDate;
  private String fullhalf;
  private String jobRemark;
  
  private String openAndclose;    //布尔值为true表示open打开，为false表示close关闭
  
  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getOrg() {
    return org;
  }

  public void setOrg(String org) {
    this.org = org;
  }

  public String getWorkDate() {
    return workDate;
  }

  public void setWorkDate(String workDate) {
    this.workDate = workDate;
  }

  public String getFullhalf() {
    return fullhalf;
  }

  public void setFullhalf(String fullhalf) {
    this.fullhalf = fullhalf;
  }

  public String getJobRemark() {
    return jobRemark;
  }

  public void setJobRemark(String jobRemark) {
    this.jobRemark = jobRemark;
  }

  public String getClaTeacher() {
    return claTeacher;
  }

  public void setClaTeacher(String claTeacher) {
    this.claTeacher = claTeacher;
  }

  public Employee(){}
  
  public Employee(String uuid,String name, String empNum, String phone, String depart, String job,
      String permissionTempl, String remark) {
    
    super();
    this.uuid = uuid;
    this.name = name;
    this.empNum = empNum;
    this.phone = phone;
    this.depart = depart;
    this.job = job;
    this.permissionTempl = permissionTempl;
    this.remark = remark;
  }

  @Override
  public String toString() {
    return "员工 [name=" + name + ", empNum=" + empNum + ", phone=" + phone + 
        ", depart=" + depart + ", job=" + job + ", permissionTempl=" + permissionTempl 
        +  ", remark=" + remark + 
        "]";
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmpNum() {
    return empNum;
  }

  public void setEmpNum(String empNum) {
    this.empNum = empNum;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getDepart() {
    return depart;
  }

  public void setDepart(String depart) {
    this.depart = depart;
  }

  public String getJob() {
    return job;
  }

  public void setJob(String job) {
    this.job = job;
  }

  public String getPermissionTempl() {
    return permissionTempl;
  }

  public void setPermissionTempl(String permissionTempl) {
    this.permissionTempl = permissionTempl;
  }


  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

public String getOpenAndclose() {
	return openAndclose;
}

public void setOpenAndclose(String openAndclose) {
	this.openAndclose = openAndclose;
}
  
  

}//end class Employee
