package com.gfss.hr_portal_backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gfss.hr_portal_backend.entity.AnnouncementEntity;

@Repository
public interface AnnouncementRepository extends MongoRepository<AnnouncementEntity, String> {

    // Search announcements by title (case-insensitive, partial match)
    List<AnnouncementEntity> findByTitleContainingIgnoreCase(String title);

    // Fetch by custom announcementId
    AnnouncementEntity findByAnnouncementId(String announcementId);

    // Delete by custom announcementId
    void deleteByAnnouncementId(String announcementId);

    boolean existsByAnnouncementId(String announcementId);
}
