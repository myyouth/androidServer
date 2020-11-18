package com.zjKingdee.androidServer.entity;

import java.math.BigDecimal;

public class ProcessBookingEntity {
	private String transferCardNumber;
	private String orderNumber;
	private String processNumber;
	private String processID;
	private String inputNumber;
	private String outputNumber;
	private String taskTime;
	private BigDecimal unitTime;
	private String badID;
	private String badNumber;
	private String badProject;
	private String userID;
	private String materialNumber;
	public ProcessBookingEntity(String transferCardNumber, String orderNumber, Integer processID, String processNumber,BigDecimal unitTime,Integer inputNumber,String materialNumber) {
		super();
		this.transferCardNumber = transferCardNumber;
		this.orderNumber = orderNumber;
		this.processNumber = processNumber;
		this.processID = String.valueOf(processID);
		this.unitTime=unitTime;
		this.inputNumber=String.valueOf(inputNumber);
		this.materialNumber=materialNumber;
	}
	
	public ProcessBookingEntity(String transferCardNumber, String orderNumber, String processID, String inputNumber,
			String outputNumber, String taskTime,  String badNumber, String badID, String userID) {
		super();
		this.transferCardNumber = transferCardNumber;
		this.processID = processID;
		this.inputNumber = inputNumber;
		this.outputNumber = outputNumber;
		this.taskTime = taskTime;
		this.badID = badID;
		this.badNumber = badNumber;
		this.userID=userID;
		this.orderNumber=orderNumber;
	}

	public String getTransferCardNumber() {
		return transferCardNumber;
	}
	public void setTransferCardNumber(String transferCardNumber) {
		this.transferCardNumber = transferCardNumber;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getProcessNumber() {
		return processNumber;
	}
	public void setProcessNumber(String processNumber) {
		this.processNumber = processNumber;
	}
	public String getProcessID() {
		return processID;
	}
	public void setProcessID(String processID) {
		this.processID = processID;
	}
	public String getInputNumber() {
		return inputNumber;
	}
	public void setInputNumber(String inputNumber) {
		this.inputNumber = inputNumber;
	}
	public String getOutputNumber() {
		return outputNumber;
	}
	public void setOutputNumber(String outputNumber) {
		this.outputNumber = outputNumber;
	}
	public String getTaskTime() {
		return taskTime;
	}
	public void setTaskTime(String taskTime) {
		this.taskTime = taskTime;
	}
	public String getBadID() {
		return badID;
	}
	public void setBadID(String badID) {
		this.badID = badID;
	}
	public String getBadNumber() {
		return badNumber;
	}
	public void setBadNumber(String badNumber) {
		this.badNumber = badNumber;
	}
	public String getBadProject() {
		return badProject;
	}
	public void setBadProject(String badProject) {
		this.badProject = badProject;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public BigDecimal getUnitTime() {
		return unitTime;
	}

	public void setUnitTime(BigDecimal unitTime) {
		this.unitTime = unitTime;
	}

	public String getMaterialNumber() {
		return materialNumber;
	}

	public void setMaterialNumber(String materialNumber) {
		this.materialNumber = materialNumber;
	}
	
}
