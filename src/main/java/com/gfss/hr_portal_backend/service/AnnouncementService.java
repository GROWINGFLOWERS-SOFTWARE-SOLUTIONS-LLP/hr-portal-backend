package com.gfss.hr_portal_backend.service;

import java.util.List;

import com.gfss.hr_portal_backend.entity.AnnouncementEntity;
import com.gfss.hr_portal_backend.resultVO.AnnouncementRequest;

public interface AnnouncementService {

    AnnouncementEntity createAnnouncement(AnnouncementRequest request);

    AnnouncementEntity updateAnnouncement(String announcementId, AnnouncementRequest request);

    boolean deleteAnnouncement(String announcementId);

    AnnouncementEntity getAnnouncementById(String announcementId);

    List<AnnouncementEntity> getAllAnnouncements();

    List<AnnouncementEntity> searchAnnouncementsByTitle(String title);
}
