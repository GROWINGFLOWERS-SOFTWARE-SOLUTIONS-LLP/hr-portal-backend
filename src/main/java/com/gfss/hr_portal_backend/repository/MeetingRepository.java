package com.gfss.hr_portal_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gfss.hr_portal_backend.entity.MeetingEntity;

public interface MeetingRepository extends MongoRepository<MeetingEntity, String> {
    Optional<MeetingEntity> findByMeetingId(String meetingId);

    void deleteByMeetingId(String meetingId);

    boolean existsByMeetingId(String meetingId);
    
}
