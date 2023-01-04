package com.fuel50.mood.tracker.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fuel50.mood.tracker.entity.MoodTrackerEntity;

@Repository
public interface MoodTrackerRepository extends CrudRepository<MoodTrackerEntity, Long> {
	
	public List<MoodTrackerEntity> findByCreatedAtBetween(LocalDateTime begin, LocalDateTime end);

}
