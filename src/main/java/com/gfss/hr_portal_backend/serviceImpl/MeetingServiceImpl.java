package com.gfss.hr_portal_backend.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfss.hr_portal_backend.resultVO.MeetingRequest;
import com.gfss.hr_portal_backend.entity.MeetingEntity;
import com.gfss.hr_portal_backend.repository.MeetingRepository;
import com.gfss.hr_portal_backend.service.MeetingService;
import com.gfss.hr_portal_backend.utils.IdGenerateUtil;

@Service
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Override
    public MeetingEntity createMeeting(MeetingRequest request) {
        MeetingEntity entity = new MeetingEntity();
//        entity.setEmpId(request.getEmpId());

        // ✅ Set hrId as custom empId of HR (not ObjectId)
//        entity.setHrId(request.getHrId());

        entity.setDate(request.getDate());
        entity.setTopic(request.getTopic());
        entity.setAttendees(request.getAttendees());
        entity.setConclusion(request.getConclusion());

        // Insert to generate MongoDB _id
        entity = meetingRepository.insert(entity);

        // Generate numeric meetingId
        String customId = IdGenerateUtil.convertObjectIdToNumeric(entity.getId());
        entity.setMeetingId(customId);

        return meetingRepository.save(entity);
    }

    @Override
    public List<MeetingEntity> getAllMeetings() {
        return meetingRepository.findAll();
    }

    @Override
    public MeetingEntity getMeetingById(String meetingId) {
        return meetingRepository.findByMeetingId(meetingId)
                .orElseThrow(() -> new RuntimeException("Meeting not found"));
    }

    @Override
    public MeetingEntity updateMeeting(String meetingId, MeetingRequest request) {
        MeetingEntity entity = getMeetingById(meetingId);
//        entity.setEmpId(request.getEmpId());

        // ✅ Set hrId as custom empId of HR
//        entity.setHrId(request.getHrId());

        entity.setDate(request.getDate());
        entity.setTopic(request.getTopic());
        entity.setAttendees(request.getAttendees());
        entity.setConclusion(request.getConclusion());

        return meetingRepository.save(entity);
    }

    @Override
    public void deleteMeeting(String meetingId) {
        if (!meetingRepository.existsByMeetingId(meetingId)) {
            throw new RuntimeException("Meeting not found");
        }
        meetingRepository.deleteByMeetingId(meetingId);
    }
    @Override
    public List<MeetingEntity> filterMeetings(String empId, String topic) {
        List<MeetingEntity> allMeetings = meetingRepository.findAll();

        return allMeetings.stream()
            .filter(m -> (topic == null || topic.isBlank() || m.getTopic().toLowerCase().contains(topic.toLowerCase())))
            .toList();
    }

}
