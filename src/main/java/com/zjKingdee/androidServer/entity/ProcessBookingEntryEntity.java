package com.zjKingdee.androidServer.entity;

import java.math.BigDecimal;

public class ProcessBookingEntryEntity {
	private String transferCardID;//流转卡ID
	private String transferCardNumber;//流转卡编码
	private String processID;//工序ID
	private String userID1;//报工用户1
	private String inputNumber1;//良品数1
	private String badNumber1;//不良数1
	private String taskTime1;//工时1
	private String userID2;//报工用户2
	private String inputNumber2;//良品数2
	private String badNumber2;//不良数2
	private String taskTime2;//工时2
	private String userID3;//报工用户3
	private String inputNumber3;//良品数3
	private String badNumber3;//不良数3
	private String taskTime3;//工时3
	private String badID1;//不良项目ID1
	private String ng1;//不良项目数量1
	private String badID2;//不良项目ID2
	private String ng2;//不良项目数量2
	private String qualificateRate;//合格率
	private String totalInputNumber;//合计投入
	private String totalOutputNumber;//合计产出
	private String totalTaskTime;//工序合计时间
	private String dateTime;//报工时间
	
	public ProcessBookingEntryEntity(Integer userID1, Integer inputNumber1,
			Integer badNumber1, BigDecimal taskTime1, Integer userID2, Integer inputNumber2, Integer badNumber2,
			BigDecimal taskTime2, Integer userID3, Integer inputNumber3, Integer badNumber3, BigDecimal taskTime3, Integer badID1,
			Integer ng1,Integer badID2, Integer ng2) {
		super();
		this.userID1 = String.valueOf(userID1);
		this.inputNumber1 = String.valueOf(inputNumber1);
		this.badNumber1 = String.valueOf(badNumber1);
		this.taskTime1 = String.valueOf(taskTime1);
		this.userID2 = String.valueOf(userID2);
		this.inputNumber2 = String.valueOf(inputNumber2);
		this.badNumber2 = String.valueOf(badNumber2);
		this.taskTime2 = String.valueOf(taskTime2);
		this.userID3 = String.valueOf(userID3);
		this.inputNumber3 = String.valueOf(inputNumber3);
		this.badNumber3 = String.valueOf(badNumber3);
		this.taskTime3 = String.valueOf(taskTime3);
		this.badID1 = String.valueOf(badID1);
		this.ng1 = String.valueOf(ng1);
		this.badID2 = String.valueOf(badID2);
		this.ng2 = String.valueOf(ng2);
	}
	public String getTransferCardNumber() {
		return transferCardNumber;
	}
	public void setTransferCardNumber(String transferCardNumber) {
		this.transferCardNumber = transferCardNumber;
	}
	public String getProcessID() {
		return processID;
	}
	public void setProcessID(String processID) {
		this.processID = processID;
	}
	public String getUserID1() {
		return userID1;
	}
	public void setUserID1(String userID1) {
		this.userID1 = userID1;
	}
	public String getInputNumber1() {
		return inputNumber1;
	}
	public void setInputNumber1(String inputNumber1) {
		this.inputNumber1 = inputNumber1;
	}
	public String getBadNumber1() {
		return badNumber1;
	}
	public void setBadNumber1(String badNumber1) {
		this.badNumber1 = badNumber1;
	}
	public String getTaskTime1() {
		return taskTime1;
	}
	public void setTaskTime1(String taskTime1) {
		this.taskTime1 = taskTime1;
	}
	public String getUserID2() {
		return userID2;
	}
	public void setUserID2(String userID2) {
		this.userID2 = userID2;
	}
	public String getInputNumber2() {
		return inputNumber2;
	}
	public void setInputNumber2(String inputNumber2) {
		this.inputNumber2 = inputNumber2;
	}
	public String getBadNumber2() {
		return badNumber2;
	}
	public void setBadNumber2(String badNumber2) {
		this.badNumber2 = badNumber2;
	}
	public String getTaskTime2() {
		return taskTime2;
	}
	public void setTaskTime2(String taskTime2) {
		this.taskTime2 = taskTime2;
	}
	public String getUserID3() {
		return userID3;
	}
	public void setUserID3(String userID3) {
		this.userID3 = userID3;
	}
	public String getInputNumber3() {
		return inputNumber3;
	}
	public void setInputNumber3(String inputNumber3) {
		this.inputNumber3 = inputNumber3;
	}
	public String getBadNumber3() {
		return badNumber3;
	}
	public void setBadNumber3(String badNumber3) {
		this.badNumber3 = badNumber3;
	}
	public String getTaskTime3() {
		return taskTime3;
	}
	public void setTaskTime3(String taskTime3) {
		this.taskTime3 = taskTime3;
	}
	public String getBadID1() {
		return badID1;
	}
	public void setBadID1(String badID1) {
		this.badID1 = badID1;
	}
	public String getNg1() {
		return ng1;
	}
	public void setNg1(String ng1) {
		this.ng1 = ng1;
	}
	public String getBadID2() {
		return badID2;
	}
	public void setBadID2(String badID2) {
		this.badID2 = badID2;
	}
	public String getNg2() {
		return ng2;
	}
	public void setNg2(String ng2) {
		this.ng2 = ng2;
	}
	public String getTransferCardID() {
		return transferCardID;
	}
	public void setTransferCardID(String transferCardID) {
		this.transferCardID = transferCardID;
	}
	public String getQualificateRate() {
		return qualificateRate;
	}
	public void setQualificateRate(String qualificateRate) {
		this.qualificateRate = qualificateRate;
	}
	public String getTotalInputNumber() {
		return totalInputNumber;
	}
	public void setTotalInputNumber(String totalInputNumber) {
		this.totalInputNumber = totalInputNumber;
	}
	public String getTotalOutputNumber() {
		return totalOutputNumber;
	}
	public void setTotalOutputNumber(String totalOutputNumber) {
		this.totalOutputNumber = totalOutputNumber;
	}
	public String getTotalTaskTime() {
		return totalTaskTime;
	}
	public void setTotalTaskTime(String totalTaskTime) {
		this.totalTaskTime = totalTaskTime;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
}
