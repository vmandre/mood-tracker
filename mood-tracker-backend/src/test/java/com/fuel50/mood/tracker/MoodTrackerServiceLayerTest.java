package com.fuel50.mood.tracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.fuel50.mood.tracker.entity.MoodTrackerEntity;
import com.fuel50.mood.tracker.enums.MoodStatus;
import com.fuel50.mood.tracker.repository.MoodTrackerRepository;
import com.fuel50.mood.tracker.service.MoodTrackerService;
import com.fuel50.mood.tracker.vo.MoodTrackerResponse;
import com.fuel50.mood.tracker.vo.MoodTrackerVO;

@ExtendWith(MockitoExtension.class)
class MoodTrackerServiceLayerTest {
	
	@Mock
	private MoodTrackerRepository repository;
	
	@Autowired
	@InjectMocks
	private MoodTrackerService service;
	
	private List<MoodTrackerEntity> moodList;
	private MoodTrackerEntity moodHappy;
	private MoodTrackerEntity moodGrumpy;
	private MoodTrackerEntity moodNormal;

	@BeforeEach
	void setUp() throws Exception {
		moodList = new ArrayList<MoodTrackerEntity>();
		

		moodHappy = new MoodTrackerEntity(new MoodTrackerVO(MoodStatus.HAPPY, "What a day"));
		moodNormal = new MoodTrackerEntity(new MoodTrackerVO(MoodStatus.NORMAL));
		moodGrumpy = new MoodTrackerEntity(new MoodTrackerVO(MoodStatus.GRUMPY, "I should sleep all day"));

		moodList.add(moodHappy);
		moodList.add(moodNormal);
		moodList.add(moodGrumpy);
	}

	@AfterEach
	void tearDown() throws Exception {
		moodList = null;
	}

	@Test
	void givenMoodToAddShouldReturnAddedMood() {
		MoodTrackerVO vo = new MoodTrackerVO();
		vo.setStatus(MoodStatus.HAPPY);

		MoodTrackerEntity entity = new MoodTrackerEntity(vo);
		
	     //stubbing
	     when(repository.save(any())).thenReturn(entity);
	     service.save(vo);

	     verify(repository, times(1)).save(any());
		
	}

	@Test
	void getAllMoodRecords_success() {
		
		// mock to return the data
		LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
		LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);
		Mockito.when(repository.findByCreatedAtBetween(startOfDay, endOfDay)).thenReturn(moodList);

		List<MoodTrackerResponse> moodList2 = service.getOverallMoodOfDay();

		assertEquals(moodList.size(), moodList2.size());
	}

}
