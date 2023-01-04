package com.fuel50.mood.tracker.vo;

import java.util.List;

import com.fuel50.mood.tracker.enums.MoodStatus;

public class MoodTrackerResponse {
	
	public MoodTrackerResponse(MoodStatus status, Integer qty, List<String> messages) {
		this.status = status != null ? status.getDescription() : "";
		this.qty = qty;
		this.messages = messages;
	}
	
	private String status;
	private Integer qty;
	private List<String> messages;

	public String getStatus() {
		return status;
	}
	
	public Integer getQty() {
		return qty;
	}
	
	public List<String> getMessages() {
		return messages;
	}
}
