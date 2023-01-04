package com.fuel50.mood.tracker.vo;

public class MoodStatusVO {
	
	public MoodStatusVO(String status, String description) {
		this.status = status;
		this.description = description;
	}
	
	private String status;
	private String description;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
