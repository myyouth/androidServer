package com.zjKingdee.androidServer.entity;

public class QCPurchaseReceiptEntity {
	private String fposname;//业务类型
    private String fBatchNo;
    private String fmateNum;
    private String fmateName;
    private String FSupplyID;
    private String fsupplyname; //供应商
    private String fcheckSum;
    private String passSum;
    private String ngSum;
    private String fBillNo;
    private String fBillId;
    private String safeKeepingId;
    private String shipperId;
	public String getSafeKeepingId() {
		return safeKeepingId;
	}
	public void setSafeKeepingId(String safeKeepingId) {
		this.safeKeepingId = safeKeepingId;
	}
	public String getShipperId() {
		return shipperId;
	}
	public void setShipperId(String shipperId) {
		this.shipperId = shipperId;
	}
	public String getfBillId() {
		return fBillId;
	}
	public void setfBillId(String fBillId) {
		this.fBillId = fBillId;
	}
	public String getfBillNo() {
		return fBillNo;
	}
	public void setfBillNo(String fBillNo) {
		this.fBillNo = fBillNo;
	}
	public String getFposname() {
		return fposname;
	}
	public void setFposname(String fposname) {
		this.fposname = fposname;
	}
	public String getfBatchNo() {
		return fBatchNo;
	}
	public void setfBatchNo(String fBatchNo) {
		this.fBatchNo = fBatchNo;
	}
	public String getFmateNum() {
		return fmateNum;
	}
	public void setFmateNum(String fmateNum) {
		this.fmateNum = fmateNum;
	}
	public String getFmateName() {
		return fmateName;
	}
	public void setFmateName(String fmateName) {
		this.fmateName = fmateName;
	}
	public String getFSupplyID() {
		return FSupplyID;
	}
	public void setFSupplyID(String fSupplyID) {
		FSupplyID = fSupplyID;
	}
	public String getFsupplyname() {
		return fsupplyname;
	}
	public void setFsupplyname(String fsupplyname) {
		this.fsupplyname = fsupplyname;
	}
	public String getFcheckSum() {
		return fcheckSum;
	}
	public void setFcheckSum(String fcheckSum) {
		this.fcheckSum = fcheckSum;
	}
	public String getPassSum() {
		return passSum;
	}
	public void setPassSum(String passSum) {
		this.passSum = passSum;
	}
	public String getNgSum() {
		return ngSum;
	}
	public void setNgSum(String ngSum) {
		this.ngSum = ngSum;
	}
    
}
