package com.sdlive.model;

public class TalkRoom {
  
  private String uuid;
  private String createDate;
  private String modifyDate;
  private String createPeople;
  private String modifyPeople;
  

  private String urlRemark;
  private String openAndclose;    //布尔值为true表示open打开，为false表示close关闭
  
  //第三方接口使用字段start
  private String keyTalk;    //企业key---talk第三方接口(固定值)
  private int serialTalk; //房间编号---talk第三方接口
  private String roomNameTalk;//房间名---talk第三方接口
  private int roomtypeTalk;//房间类型
  private int starttimeTalk;//房间开始时间
  private int endtimeTalk;//房间结束时间
  private String chairmanpwd;//老师密码
  private String assistantpwd;//助教密码
  private String patrolpwd;//巡课密码
  private int passwordrequired;//进入房间学生是否需要密码, 0:否 ，1:是
  private String confuserpwd;//学生密码
  
  private int videotype;//视频分辨率,默认为0,在构造函数中默认
  private int videoframerate;//视频帧率，默认为10，在构造函数中默认
  private int confusernum;//教室点数 选填
  private int autoopenav;//自动开启音视频 0:不开启   1:开启 ，默认为1开启
  
  
//第三方接口使用字段end
  
  
  public TalkRoom() {
    
    this.keyTalk = "yil97lLwpd6uELjB"; //测试默认key
    
    this.passwordrequired=1; //学生登陆默认都要密码
    this.videotype=0;        //视频分辨率,默认为0
    this.videoframerate=10;  //视频帧率，默认为10
    this.autoopenav=1;       //自动开启音视频，默认为1开启
    
  }


  @Override
  public String toString() {
    return "TalkRoom 开始[uuid=" + uuid  + ", openAndclose=" + openAndclose
        + ", keyTalk=" + keyTalk + ", serialTalk=" + serialTalk + ", roomNameTalk=" + roomNameTalk
        + ", roomtypeTalk=" + roomtypeTalk + ", starttimeTalk=" + starttimeTalk + ", endtimeTalk="
        + endtimeTalk + ", chairmanpwd=" + chairmanpwd + ", assistantpwd=" + assistantpwd
        + ", patrolpwd=" + patrolpwd + ", passwordrequired=" + passwordrequired + ", confuserpwd="
        + confuserpwd + ", videotype=" + videotype + ", videoframerate=" + videoframerate
        + ", confusernum=" + confusernum + ", autoopenav=" + autoopenav + "]结束";
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





  public String getOpenAndclose() {
    return openAndclose;
  }


  public void setOpenAndclose(String openAndclose) {
    this.openAndclose = openAndclose;
  }


  public String getKeyTalk() {
    return keyTalk;
  }


  public void setKeyTalk(String keyTalk) {
    this.keyTalk = keyTalk;
  }


  public int getSerialTalk() {
    return serialTalk;
  }


  public void setSerialTalk(int serialTalk) {
    this.serialTalk = serialTalk;
  }


  public String getRoomNameTalk() {
    return roomNameTalk;
  }


  public void setRoomNameTalk(String roomNameTalk) {
    this.roomNameTalk = roomNameTalk;
  }


  public int getRoomtypeTalk() {
    return roomtypeTalk;
  }


  public void setRoomtypeTalk(int roomtypeTalk) {
    this.roomtypeTalk = roomtypeTalk;
  }


  public int getStarttimeTalk() {
    return starttimeTalk;
  }


  public void setStarttimeTalk(int starttimeTalk) {
    this.starttimeTalk = starttimeTalk;
  }


  public int getEndtimeTalk() {
    return endtimeTalk;
  }


  public void setEndtimeTalk(int endtimeTalk) {
    this.endtimeTalk = endtimeTalk;
  }


  public String getChairmanpwd() {
    return chairmanpwd;
  }


  public void setChairmanpwd(String chairmanpwd) {
    this.chairmanpwd = chairmanpwd;
  }


  public String getAssistantpwd() {
    return assistantpwd;
  }


  public void setAssistantpwd(String assistantpwd) {
    this.assistantpwd = assistantpwd;
  }


  public String getPatrolpwd() {
    return patrolpwd;
  }


  public void setPatrolpwd(String patrolpwd) {
    this.patrolpwd = patrolpwd;
  }


  public int getPasswordrequired() {
    return passwordrequired;
  }


  public void setPasswordrequired(int passwordrequired) {
    this.passwordrequired = passwordrequired;
  }


  public String getConfuserpwd() {
    return confuserpwd;
  }


  public void setConfuserpwd(String confuserpwd) {
    this.confuserpwd = confuserpwd;
  }


  public int getVideotype() {
    return videotype;
  }


  public void setVideotype(int videotype) {
    this.videotype = videotype;
  }


  public int getVideoframerate() {
    return videoframerate;
  }


  public void setVideoframerate(int videoframerate) {
    this.videoframerate = videoframerate;
  }


  public int getConfusernum() {
    return confusernum;
  }


  public void setConfusernum(int confusernum) {
    this.confusernum = confusernum;
  }


  public int getAutoopenav() {
    return autoopenav;
  }


  public void setAutoopenav(int autoopenav) {
    this.autoopenav = autoopenav;
  }


  public String getUrlRemark() {
    return urlRemark;
  }


  public void setUrlRemark(String urlRemark) {
    this.urlRemark = urlRemark;
  }
  
  
  
  
  
  

}//end class TalkRoom
