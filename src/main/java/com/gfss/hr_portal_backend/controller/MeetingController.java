package com.gfss.hr_portal_backend.controller;

import com.gfss.hr_portal_backend.resultVO.MeetingRequest;
import com.gfss.hr_portal_backend.entity.MeetingEntity;
import com.gfss.hr_portal_backend.resultVO.ApiResponse;
import com.gfss.hr_portal_backend.service.MeetingService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping("/meeting")
    public ApiResponse<MeetingEntity> createMeeting(@Valid @RequestBody MeetingRequest request) {
        MeetingEntity created = meetingService.createMeeting(request);
        return new ApiResponse<>("success", "Meeting created successfully", created);
    }

    @GetMapping("/meeting")
    public ApiResponse<List<MeetingEntity>> getAllMeetings() {
        List<MeetingEntity> meetings = meetingService.getAllMeetings();
        return new ApiResponse<>("success", "All meetings fetched", meetings);
    }

    @GetMapping("/meeting/{meetingId}")
    public ApiResponse<MeetingEntity> getMeeting(@PathVariable String meetingId) {
        MeetingEntity meeting = meetingService.getMeetingById(meetingId);
        return new ApiResponse<>("success", "Meeting fetched", meeting);
    }

    @PutMapping("/meeting/{meetingId}")
    public ApiResponse<MeetingEntity> updateMeeting(@PathVariable String meetingId,
                                                    @Valid @RequestBody MeetingRequest request) {
        MeetingEntity updated = meetingService.updateMeeting(meetingId, request);
        return new ApiResponse<>("success", "Meeting updated successfully", updated);
    }

    @DeleteMapping("/meeting/{meetingId}")
    public ApiResponse<String> deleteMeeting(@PathVariable String meetingId) {
        meetingService.deleteMeeting(meetingId);
        return new ApiResponse<>("success", "Meeting deleted successfully", meetingId);
    }
    @GetMapping("/meeting/filter")
    public ApiResponse<List<MeetingEntity>> filterMeetings(
            @RequestParam(required = false) String empId,
            @RequestParam(required = false) String topic) {

        List<MeetingEntity> results = meetingService.filterMeetings(empId, topic);
        return new ApiResponse<>("success", "Filtered meetings", results);
    }

}
