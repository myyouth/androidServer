package com.zjKingdee.androidServer.entity;

public class BadDescriptionEntity {
	private String BadID;
	private String text;
	
	public BadDescriptionEntity(Integer badID, String text) {
		super();
		this.BadID = String.valueOf(badID);
		this.text = text;
	}
	public String getBadID() {
		return BadID;
	}
	public void setBadID(String badID) {
		BadID = badID;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
