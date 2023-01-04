package com.fuel50.mood.tracker.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fuel50.mood.tracker.service.MoodTrackerService;
import com.fuel50.mood.tracker.vo.MoodStatusVO;
import com.fuel50.mood.tracker.vo.MoodTrackerResponse;
import com.fuel50.mood.tracker.vo.MoodTrackerVO;

@RestController
@RequestMapping("api/v1")
public class MoodTrackerController {

	@Autowired
	private MoodTrackerService service;
	
	private Logger logger = LoggerFactory.getLogger(MoodTrackerController.class);

	/**
	 * Test if the API is running
	 * @return
	 */
	@GetMapping("/ping")
	public String ping() {
		logger.debug("API is working");
		return "API is working";
	}
	
	@PostMapping("/")
	public ResponseEntity<?> add(@Valid @RequestBody MoodTrackerVO moodTracker) {
		try {
			logger.debug("Saving a new mood");
			service.save(moodTracker);
			return ResponseEntity.status(HttpStatus.CREATED).body("Your rating has been successfully submitted");
		} catch (Exception e) {
			logger.error("An error ocurred on saving the object", e);
			return ResponseEntity.internalServerError().body(String.format("Ooops... %s", e.getMessage()));
		}

	}

	@GetMapping("/")
	public ResponseEntity<?> getAllMoodsFromToday() {
		try {
			logger.debug("Getting a list of Mood of the day");
			List<MoodTrackerResponse> moodsOfDay = service.getOverallMoodOfDay();
			return ResponseEntity.ok(moodsOfDay);
		} catch (Exception e) {
			logger.error("An error ocurred on getting the list of Moods of the day", e);
			return ResponseEntity.internalServerError().body(String.format("Ooops... %s", e.getMessage()));
		}
	}
	@GetMapping("/statuses")
	public ResponseEntity<?> getAllMoodsStatuses() {
		try {
			logger.debug("Getting a list of MoodStatus");
			List<MoodStatusVO> moodsList = service.getAllMoodStatuses();
			return ResponseEntity.ok(moodsList);
		} catch (Exception e) {
			logger.error("An error ocurred on getting the list of MoodStatus", e);
			return ResponseEntity.internalServerError().body(String.format("Ooops... %s", e.getMessage()));
		}
	}
}
