package com.fuel50.mood.tracker.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fuel50.mood.tracker.enums.MoodStatus;

public class MoodTrackerVO {
	
	public MoodTrackerVO() {}
	
	public MoodTrackerVO(MoodStatus status) {
		this.status = status;
	}

	public MoodTrackerVO(MoodStatus status, String message) {
		this(status);
		this.message = message;
	}
	
	@NotNull(message = "Mood status is required")
	private MoodStatus status;

	@Size(max = 350)
	private String message;

	public MoodStatus getStatus() {
		return status;
	}

	public void setStatus(MoodStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}