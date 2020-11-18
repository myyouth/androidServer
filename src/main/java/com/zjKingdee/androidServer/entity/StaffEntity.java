package com.zjKingdee.androidServer.entity;

public class StaffEntity {
	private String staffID;//职员ID
	private String staffNumber;//职员编码
	private String staffName;//职员名称
	public StaffEntity(Integer staffID, String staffNumber, String staffName) {
		super();
		this.staffID = String.valueOf(staffID);
		this.staffNumber = staffNumber;
		this.staffName = staffName;
	}
	public String getStaffID() {
		return staffID;
	}
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
	public String getStaffNumber() {
		return staffNumber;
	}
	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
}
