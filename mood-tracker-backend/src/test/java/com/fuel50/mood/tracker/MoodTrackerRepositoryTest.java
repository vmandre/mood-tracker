package com.fuel50.mood.tracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fuel50.mood.tracker.entity.MoodTrackerEntity;
import com.fuel50.mood.tracker.enums.MoodStatus;
import com.fuel50.mood.tracker.repository.MoodTrackerRepository;
import com.fuel50.mood.tracker.vo.MoodTrackerVO;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class MoodTrackerRepositoryTest {
	
	@Autowired
	private MoodTrackerRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		MoodTrackerVO vo = new MoodTrackerVO();
		vo.setStatus(MoodStatus.NORMAL);
		
		MoodTrackerEntity moodTracker = new MoodTrackerEntity(vo);
		repository.save(moodTracker);
	}

	@AfterEach
	void tearDown() throws Exception {
		repository.deleteAll();
	}

	@Test
	void saveMoodRecord_success() {
		MoodTrackerVO vo = new MoodTrackerVO();
		vo.setStatus(MoodStatus.HAPPY);
		
		MoodTrackerEntity moodTracker = new MoodTrackerEntity(vo);
		MoodTrackerEntity entity = repository.save(moodTracker);

		// Should exist an Id and the createdAt should be the same after saving the entity  
		assertNotNull(entity.getId());
		assertEquals(entity.getCreatedAt(), moodTracker.getCreatedAt());
	}
	
	@Test
	void getAllMoodRecords_success() {
		LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
		LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);

		List<MoodTrackerEntity> list = repository.findByCreatedAtBetween(startOfDay, endOfDay);
		
		// Should exist only one record for the current day  
		assertTrue(list.size() == 1);
	}

	@Test
	void getAllMoodRecords_fail() {
		LocalDateTime startOfDay = LocalDate.ofYearDay(2000, 20).atStartOfDay();
		LocalDateTime endOfDay = LocalDate.ofYearDay(2000, 20).atTime(LocalTime.MAX);
		
		List<MoodTrackerEntity> list = repository.findByCreatedAtBetween(startOfDay, endOfDay);
		
		// Given a date before the record saved, shouldn't return any record in the list. 
		assertTrue(list.size() == 0);
	}

}
