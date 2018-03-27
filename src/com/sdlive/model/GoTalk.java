package com.sdlive.model;
/**
 *树袋老师
 * @author 作者 xpp
 * @version 创建时间：2018-1-28 上午10:50:36
 * 类说明
 */

public class GoTalk {
  
  private String uuid;
  private String createDate;
  private String modifyDate;
  private String createPeople;
  private String modifyPeople;
  
  private String name;
  private String urlRemark;
  private String openAndclose;    //open打开，close关闭
  
//第三方接口使用字段start
  private String domain;    //公司域名---默认为test
  private int serialTalk; //房间编号---talk第三方接口
  private String username;//用户名,用户在教室中显示的名称
  private int usertype;// 用户类型,0:老师，1助教，2学员，默认为2
  private int pid;//第3方系统的用户id，默认为0
  private int ts;//时间戳
  private String auth;//校验码(由key+ts+serial+usertype)拼接后进行MD5加密
  private String userpassword;//用户密码,密码为128位AES加密串
  private String servername;//服务器名称
  private String extradata;//扩展数据，用户扩展数据，建议urlencode
  private String jumpurl;//课程结束后跳转到官网页 crm.shudailaoshi.com
  
  private String keyTalk;
  
//第三方接口使用字段end

  public GoTalk() {
    super();
    this.setKeyTalk("yil97lLwpd6uELjB"); //测试默认key
    this.domain = "test";
    this.serialTalk = 1017067284;
    this.usertype = 2;
    this.pid = 0;
  }

  public GoTalk(String uuid,String name,String urlRemark,String openAndclose,String domain,int serialTalk,String username,int usertype,int pid,int ts,String auth, 
		String userpassword,String servername,String extradata,String jumpurl,String createDate,String modifyDate,String createPeople,String modifyPeople) {
	    super();
	    this.uuid = uuid;
	    this.name = name;
	    this.urlRemark = urlRemark;
	    this.openAndclose = openAndclose;
	    this.domain = domain;
	    this.serialTalk = serialTalk;
	    this.username = username;
	    this.usertype = usertype;
	    this.pid = pid;
	    this.ts = ts;
	    this.auth = auth;
	    this.userpassword = userpassword;
	    this.servername = servername;
	    this.extradata = extradata;
	    this.jumpurl = jumpurl;
	    this.createDate = createDate;
	    this.modifyDate = modifyDate;
	    this.createPeople = createPeople;
	    this.modifyPeople = modifyPeople;
	  }
  
  


  @Override
  public String toString() {
    return "GoTalk 开始[uuid=" + uuid + ", name=" + name + ", urlRemark=" + urlRemark
        + ", openAndclose=" + openAndclose + ", domain=" + domain + ", serialTalk=" + serialTalk
        + ", username=" + username + ", usertype=" + usertype + ", pid=" + pid + ", ts=" + ts
        + ", auth=" + auth + ", userpassword=" + userpassword + ", servername=" + servername
        + ", extradata=" + extradata + ", jumpurl=" + jumpurl + "]结束";
  }






  public String getUuid() {
    return uuid;
  }






  public void setUuid(String uuid) {
    this.uuid = uuid;
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






  public String getName() {
    return name;
  }






  public void setName(String name) {
    this.name = name;
  }






  public String getUrlRemark() {
    return urlRemark;
  }






  public void setUrlRemark(String urlRemark) {
    this.urlRemark = urlRemark;
  }






  public String getOpenAndclose() {
    return openAndclose;
  }






  public void setOpenAndclose(String openAndclose) {
    this.openAndclose = openAndclose;
  }






  public String getDomain() {
    return domain;
  }






  public void setDomain(String domain) {
    this.domain = domain;
  }






  public int getSerialTalk() {
    return serialTalk;
  }






  public void setSerialTalk(int serialTalk) {
    this.serialTalk = serialTalk;
  }






  public String getUsername() {
    return username;
  }






  public void setUsername(String username) {
    this.username = username;
  }






  public int getUsertype() {
    return usertype;
  }






  public void setUsertype(int usertype) {
    this.usertype = usertype;
  }






  public int getPid() {
    return pid;
  }






  public void setPid(int pid) {
    this.pid = pid;
  }






  public int getTs() {
    return ts;
  }






  public void setTs(int ts) {
    this.ts = ts;
  }






  public String getAuth() {
    return auth;
  }






  public void setAuth(String auth) {
    this.auth = auth;
  }






  public String getUserpassword() {
    return userpassword;
  }






  public void setUserpassword(String userpassword) {
    this.userpassword = userpassword;
  }






  public String getServername() {
    return servername;
  }






  public void setServername(String servername) {
    this.servername = servername;
  }






  public String getExtradata() {
    return extradata;
  }






  public void setExtradata(String extradata) {
    this.extradata = extradata;
  }






  public String getJumpurl() {
    return jumpurl;
  }






  public void setJumpurl(String jumpurl) {
    this.jumpurl = jumpurl;
  }






  public String getKeyTalk() {
    return keyTalk;
  }






  public void setKeyTalk(String keyTalk) {
    this.keyTalk = keyTalk;
  }
  
  
  




}//end class
