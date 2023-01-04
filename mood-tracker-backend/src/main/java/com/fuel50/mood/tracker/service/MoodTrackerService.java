package com.fuel50.mood.tracker.service;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fuel50.mood.tracker.entity.MoodTrackerEntity;
import com.fuel50.mood.tracker.enums.MoodStatus;
import com.fuel50.mood.tracker.repository.MoodTrackerRepository;
import com.fuel50.mood.tracker.vo.MoodStatusVO;
import com.fuel50.mood.tracker.vo.MoodTrackerResponse;
import com.fuel50.mood.tracker.vo.MoodTrackerVO;

@Service
public class MoodTrackerService {

	@Autowired
	private MoodTrackerRepository repository;
	
	public void save(@Valid MoodTrackerVO vo) {
		MoodTrackerEntity entity = new MoodTrackerEntity(vo);

		repository.save(entity);
	}

	public List<MoodTrackerResponse> getOverallMoodOfDay() {
		LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
		LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);

		List<MoodTrackerEntity> listMoods = repository.findByCreatedAtBetween(startOfDay, endOfDay);

		List<MoodTrackerResponse> listMoodsResponse = new ArrayList<MoodTrackerResponse>();

		// Create a map of all moods using the status as key
		Map<Object, List<MoodTrackerEntity>> moodsPerStatus = listMoods.stream()
				  .collect(Collectors.groupingBy(MoodTrackerEntity::getStatus));
		
		for (Entry<Object, List<MoodTrackerEntity>> entry : moodsPerStatus.entrySet()) {
			MoodStatus status = (MoodStatus) entry.getKey();
			List<MoodTrackerEntity> moodTrackerList = entry.getValue();
			
			List<String> messages = moodTrackerList.stream()
					.filter(m -> StringUtils.isNotBlank(m.getMessage()))
					.map(MoodTrackerEntity::getMessage)
					.collect(toList());
			
			listMoodsResponse.add(new MoodTrackerResponse(status, moodTrackerList.size(), messages));
			
		}

		return listMoodsResponse;
	}

	public List<MoodStatusVO> getAllMoodStatuses() {
		List<MoodStatus> list = Arrays.asList(MoodStatus.values());
		List<MoodStatusVO> moodStatusVOs = new ArrayList<MoodStatusVO>();
		
		list.forEach(m -> {
			moodStatusVOs.add(new MoodStatusVO(m.name(), m.getDescription()));
		});
		
		return moodStatusVOs;
	}
}
