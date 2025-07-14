package com.gfss.hr_portal_backend.service;

import com.gfss.hr_portal_backend.resultVO.MeetingRequest;
import com.gfss.hr_portal_backend.entity.MeetingEntity;
import java.util.List;

public interface MeetingService {
	MeetingEntity createMeeting(MeetingRequest request);

	List<MeetingEntity> getAllMeetings();

	MeetingEntity getMeetingById(String meetingId);

	MeetingEntity updateMeeting(String meetingId, MeetingRequest request);

	void deleteMeeting(String meetingId);

	List<MeetingEntity> filterMeetings(String empId, String topic);

}
