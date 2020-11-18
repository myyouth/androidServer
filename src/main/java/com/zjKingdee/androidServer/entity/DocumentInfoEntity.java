package com.zjKingdee.androidServer.entity;
/**
 * 单据基本信息
 * @author Yinqi				
 * @date 2020年2月20日
 */
public class DocumentInfoEntity {
	private String id;
	private String mName;
	private String mNumber;
	
	public DocumentInfoEntity(String mName, String mNumber,Integer id) {
		super();
		this.mName = mName;
		this.mNumber = mNumber;
		this.id=String.valueOf(id);
	}
	
	public DocumentInfoEntity(String mNumber,Long id) {
		super();
		this.mNumber = mNumber;
		this.mName = "无";
		this.id=String.valueOf(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getmName() {
		return mName;
	}

	public void setmName(String mName) {
		this.mName = mName;
	}

	public String getmNumber() {
		return mNumber;
	}

	public void setmNumber(String mNumber) {
		this.mNumber = mNumber;
	}
	
	
	
}
