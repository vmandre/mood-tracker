package com.fuel50.mood.tracker.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fuel50.mood.tracker.enums.MoodStatus;
import com.fuel50.mood.tracker.vo.MoodTrackerVO;

@Entity(name = "mood_tracker")
public class MoodTrackerEntity {
	
	public MoodTrackerEntity() { }
	
	public MoodTrackerEntity(MoodTrackerVO vo) {
		this.status = vo.getStatus();
		this.message = vo.getMessage();
		this.createdAt = LocalDateTime.now();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "CUST_SEQ")
	@Column(name = "MOOD_ID")
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	@NotNull
	private MoodStatus status;

	@Column(length = 350)
	private String message;

	@Column(name = "CREATED_AT", nullable = false)
	@NotNull
	private LocalDateTime createdAt;

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public MoodStatus getStatus() {
		return status;
	}

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
