--------------------------------------
--2、意向学员表t_yxstudent

CREATE TABLE t_talkroom (
  uuid varchar(50) NOT NULL,
  
  urlRemark varchar(255) DEFAULT NULL,
  openAndclose varchar(10) DEFAULT NULL,
  
  keyTalk varchar(16) NOT NULL,
  serialTalk int NOT NULL,
  roomNameTalk varchar(50) NOT NULL,
  roomtypeTalk int NOT NULL,
  starttimeTalk int NOT NULL,
  endtimeTalk int NOT NULL,
  chairmanpwd varchar(16) NOT NULL,
  assistantpwd varchar(16) NOT NULL,
  patrolpwd varchar(16) NOT NULL,
  passwordrequired int NOT NULL,
  confuserpwd varchar(20) NOT NULL,
  videotype int NOT NULL,
  videoframerate int NOT NULL,
  confusernum int NOT NULL,
  autoopenav  int NOT NULL,
  
  createDate datetime DEFAULT NULL,
  modifyDate datetime DEFAULT NULL,
  createPeople varchar(50) DEFAULT NULL,
  modifyPeople varchar(50) DEFAULT NULL,
  
  PRIMARY KEY (uuid)
);


--2、gotalk表t_gotalk

CREATE TABLE t_gotalk (
  uuid varchar(50) NOT NULL,
  
  name varchar(30) NOT NULL,
  urlRemark varchar(255) DEFAULT NULL,
  openAndclose varchar(10) DEFAULT NULL,
  
  domain varchar(20) NOT NULL,
  serialTalk int NOT NULL,
  username varchar(20) NOT NULL,
  usertype int NOT NULL,
  pid int NOT NULL,
  ts int NOT NULL,
  auth varchar(32) NOT NULL,
  userpassword varchar(32) NOT NULL,
  servername varchar(32) NOT NULL,
  extradata varchar(32) DEFAULT NULL,
  jumpurl varchar(255) DEFAULT NULL,
  
  createDate datetime DEFAULT NULL,
  modifyDate datetime DEFAULT NULL,
  createPeople varchar(50) DEFAULT NULL,
  modifyPeople varchar(50) DEFAULT NULL,
  
  PRIMARY KEY (uuid)
);

--------------------------------------
-- 跟踪记录  t_record

CREATE TABLE t_record (
  uuid varchar(50) NOT NULL,
  yxstuUuid varchar(50) NOT NULL,
  recordDate varchar(50) DEFAULT NULL,
  remarkText varchar(250) DEFAULT NULL,
  
  createDate datetime DEFAULT NULL,
  modifyDate datetime DEFAULT NULL,
  createPeople varchar(50) DEFAULT NULL,
  modifyPeople varchar(50) DEFAULT NULL,
  
  PRIMARY KEY (uuid)
);

--------------------------------------
--3、员工表t_employee

CREATE TABLE t_employee (
  uuid varchar(50) NOT NULL,
  name varchar(30) NOT NULL,
  empNum varchar(30) DEFAULT NULL,
  phone varchar(30) DEFAULT NULL,
  depart varchar(30) DEFAULT NULL,
  job varchar(30) DEFAULT NULL,
  permissionTempl varchar(30) DEFAULT NULL,
  remark varchar(255) DEFAULT NULL,
  claTeacher varchar(30) NOT NULL,
  
  sex varchar(30) DEFAULT NULL,
  org varchar(30) DEFAULT NULL,
  workDate varchar(30) DEFAULT NULL,
  fullhalf varchar(30) DEFAULT NULL,
  jobRemark varchar(30) DEFAULT NULL,
  openAndclose varchar(10) DEFAULT NULL,
  
  createDate datetime DEFAULT NULL,
  modifyDate datetime DEFAULT NULL,
  createPeople varchar(50) DEFAULT NULL,
  modifyPeople varchar(50) DEFAULT NULL,
  
  PRIMARY KEY (uuid)
);

--------------------------------------
-- 手机号码表t_dphone

CREATE TABLE t_dphone (
  uuid varchar(50) NOT NULL,
  phoneNO varchar(50) NOT NULL,
  ditchUuid varchar(50) NOT NULL,
  empDitUuid varchar(50) NOT NULL,
  
  createDate datetime DEFAULT NULL,
  modifyDate datetime DEFAULT NULL,
  createPeople varchar(50) DEFAULT NULL,
  modifyPeople varchar(50) DEFAULT NULL,
  
  PRIMARY KEY (uuid)
);

--------------------------------------
-- 微信号码表t_dweixin

CREATE TABLE t_dweixin (
  uuid varchar(50) NOT NULL,
  weixinNO varchar(50) NOT NULL,
  ditchUuid varchar(50) NOT NULL,
  empDitUuid varchar(50) NOT NULL,
  
  createDate datetime DEFAULT NULL,
  modifyDate datetime DEFAULT NULL,
  createPeople varchar(50) DEFAULT NULL,
  modifyPeople varchar(50) DEFAULT NULL,
  
  PRIMARY KEY (uuid)
);

--------------------------------------



--------------------------------------


--------------------------------------


--------------------------------------


--------------------------------------


--------------------------------------
-- 部门表t_department

CREATE TABLE t_department (
  uuid varchar(50) NOT NULL,
  name varchar(50) NOT NULL,
  remark varchar(250) DEFAULT NULL,
  openAndclose varchar(10) DEFAULT NULL,
  
  createDate datetime DEFAULT NULL,
  modifyDate datetime DEFAULT NULL,
  createPeople varchar(50) DEFAULT NULL,
  modifyPeople varchar(50) DEFAULT NULL,
  
  PRIMARY KEY (uuid)
);

--------------------------------------
--9、角色表t_role

CREATE TABLE t_role (
  uuid varchar(50) NOT NULL,
  name varchar(50) NOT NULL,
  
  createDate datetime DEFAULT NULL,
  modifyDate datetime DEFAULT NULL,
  createPeople varchar(50) DEFAULT NULL,
  modifyPeople varchar(50) DEFAULT NULL,
  remark varchar(50)Not Null,
  PRIMARY KEY (uuid)
);

--------------------------------------
--9、资源表t_resource

CREATE TABLE t_resource (
  uuid varchar(50) NOT NULL,
  name varchar(50) NOT NULL,
  
  createDate datetime DEFAULT NULL,
  modifyDate datetime DEFAULT NULL,
  createPeople varchar(50) DEFAULT NULL,
  modifyPeople varchar(50) DEFAULT NULL,
  
  PRIMARY KEY (uuid)
);


--------------------------------------


--------------------------------------


--16、用户角色表t_userPK

CREATE TABLE t_userPK_role (
  uuid varchar(50) NOT NULL,
  userPkid varchar(50) NOT NULL,
  Roleid varchar(50) NOT NULL,
  PRIMARY KEY (uuid)
);

=======

--15、角色资源表t_role_resource

CREATE TABLE t_role_resource (
  uuid varchar(50) NOT NULL,
  roleid varchar(50) NOT NULL,
  resourceid varchar(50) NOT NULL,
  
  PRIMARY KEY (uuid)
);