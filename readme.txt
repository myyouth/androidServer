1、创建数据库表
//创建条码信息存储表		
create Table t_BarcodeInfo (
	id int primary key identity(1,1),
	barcodNumber varchar(50),
	materialID int,
	materielNumber varchar(50),
	materialName varchar(50),
	materialQty decimal,
	materialUnitID int,
	materialUnitName varchar(50),
	materialBatch varchar(50),
	stockID int,
	stockNumber varchar(50),
	stockName varchar(50),
	stockPlaceID int,
	stockPlaceNumber varchar(50),
	stockPlaceName varchar(50),
	supplyID int,
	supplyName varchar(50),
	departmentID int,
	departmentName varchar(50),
	typeName varchar(50),
	typeNumber varchar(50),
	status int,(1:入库 2:出库),
	ZBM varchar(50),
	qc int,(286:合格 287:不合格)
	createTime dateTime,
	isUse int(0:停用 1:启用)
)
//创建条码操作明细
create Table t_BarcodeCZInfo (
	id int primary key identity(1,1),
	barcodNumber varchar(50),
	czId int,(1:入库 2:出库  )
	czType varchar(50),
	czTypeName varchar(50),
	createTime dateTime
)



//条码规则
create Table t_BarcodeRules (
	id int primary key identity(1,1),
	ruleName varchar(50),
	fixed varchar(50),
	serialNumber varchar(50)
)

//插入条码规则
insert into t_BarcodeRules (ruleName,fixed,serialNumber) values ('ZJFC','ZJFC','1')
insert into t_BarcodeRules (ruleName,fixed,serialNumber) values ('ZJFCBZX','ZJFCBZX','1')

//创建条码包装箱包存储表
create table t_BoxBarcodeInfo (
	id int primary key identity(1,1),
	boxBarcodNumber varchar(50),
	mateBarcodNumber varchar(50),
	createTime dateTime
)

//登录
create Table t_BOS200000013 (
	FID int,
	FName varchar(50),
	FNumber varchar(50),
	FFullNumber varchar(50),
	FBase int
)
insert into t_BOS200000013 values (1000,123,123,123,255);

//用户
CREATE TABLE t_user_info(
  user_id int PRIMARY KEY,
  user_name varchar(50),
  user_number varchar(50),
  user_password varchar(50),
  create_time dateTime
);
insert into t_user_info values (0,'administrator',0,'lr6o5lvoua065iaeiq17g4alrgfhv0cf','')//密码:888888

//角色
CREATE TABLE t_role(
  role_id int PRIMARY KEY identity(1,1),
  role_name varchar(50),
  create_time dateTime
);

//权限
CREATE TABLE t_power(
  power_id int PRIMARY KEY identity(1,1),
  power_name varchar(50),
  create_time dateTime
);
//生产领料、采购入库、生产入库、产品检验、来料检验、销售出库、库存查询


//用户角色关系
CREATE TABLE t_user_role(
  use_role_id int PRIMARY KEY identity(1,1),
  user_id int NOT NULL,
  role_id int NOT NULL ,
  create_time dateTime
);

//角色权限关系
CREATE TABLE t_role_power(
  role_power_id int PRIMARY KEY identity(1,1),
  role_id int NOT NULL,
  power_id int NOT NULL,
  create_time dateTime
);























