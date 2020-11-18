package com.zjKingdee.androidServer.entity;
/**
 * QC检测项
 * @author Yinqi				
 * @date 2020年9月17日
 */
public class QCJCXEntity {
	public String qcEntry;
	public String qcNumber;
	public String qcName;
	public String isOk;
	public String getIsOk() {
		return isOk;
	}
	public void setIsOk(String isOk) {
		this.isOk = isOk;
	}
	public String getQcEntry() {
		return qcEntry;
	}
	public void setQcEntry(String qcEntry) {
		this.qcEntry = qcEntry;
	}
	public String getQcNumber() {
		return qcNumber;
	}
	public void setQcNumber(String qcNumber) {
		this.qcNumber = qcNumber;
	}
	public String getQcName() {
		return qcName;
	}
	public void setQcName(String qcName) {
		this.qcName = qcName;
	}
	
}
