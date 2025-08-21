package com.gfss.hr_portal_backend.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfss.hr_portal_backend.entity.AnnouncementEntity;
import com.gfss.hr_portal_backend.repository.AnnouncementRepository;
import com.gfss.hr_portal_backend.resultVO.AnnouncementRequest;
import com.gfss.hr_portal_backend.service.AnnouncementService;
import com.gfss.hr_portal_backend.utils.IdGenerateUtil;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementRepository announcementRepository;

    @Override
    public AnnouncementEntity createAnnouncement(AnnouncementRequest request) {
        AnnouncementEntity entity = new AnnouncementEntity();

        entity.setTitle(request.getTitle());
        entity.setDescription(request.getDescription());
        entity.setDate(request.getDate());

        // Step 1: Save to get MongoDB-generated id
        entity = announcementRepository.insert(entity);

        // Step 2: Set announcementId using generated _id
        entity.setAnnouncementId(IdGenerateUtil.convertObjectIdToNumeric(entity.getId()));

        // Step 3: Save again with custom announcementId
        return announcementRepository.save(entity);
    }

    @Override
    public AnnouncementEntity updateAnnouncement(String announcementId, AnnouncementRequest request) {
        AnnouncementEntity existing = announcementRepository.findByAnnouncementId(announcementId);
        if (existing != null) {
            existing.setTitle(request.getTitle());
            existing.setDescription(request.getDescription());
            existing.setDate(request.getDate());
            return announcementRepository.save(existing);
        }
        return null;
    }

    @Override
    public boolean deleteAnnouncement(String announcementId) {
        if (announcementRepository.existsByAnnouncementId(announcementId)) {
            announcementRepository.deleteByAnnouncementId(announcementId);
            return true;
        }
        return false;
    }

    @Override
    public AnnouncementEntity getAnnouncementById(String announcementId) {
        return announcementRepository.findByAnnouncementId(announcementId);
    }

    @Override
    public List<AnnouncementEntity> getAllAnnouncements() {
        return announcementRepository.findAll();
    }

    @Override
    public List<AnnouncementEntity> searchAnnouncementsByTitle(String title) {
        return announcementRepository.findByTitleContainingIgnoreCase(title);
    }
}
