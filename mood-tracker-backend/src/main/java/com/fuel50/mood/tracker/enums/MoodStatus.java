package com.fuel50.mood.tracker.enums;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MoodStatus {

    HAPPY ("Happy"),
    NORMAL ("Just normal really"),
    MEH ("A bit \"meh\""),
    GRUMPY ("Grumpy"),
    STRESSED ("Stressed out – not a happy camper");

    @JsonValue
    private final String description;

    MoodStatus(String description) {
    	this.description = description;
	}
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

	public String getDescription() {
        return description;
    }
}