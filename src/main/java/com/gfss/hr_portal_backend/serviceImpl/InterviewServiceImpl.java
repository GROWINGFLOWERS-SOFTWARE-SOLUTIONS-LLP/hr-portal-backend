package com.gfss.hr_portal_backend.serviceImpl;

import com.gfss.hr_portal_backend.entity.InterviewEntity;
import com.gfss.hr_portal_backend.repository.InterviewRepository;
import com.gfss.hr_portal_backend.resultVO.InterviewDTO;
import com.gfss.hr_portal_backend.service.EmailService;
import com.gfss.hr_portal_backend.service.InterviewService;
import com.gfss.hr_portal_backend.utils.IdGenerateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    private InterviewRepository interviewRepository;
    
    @Autowired
    private EmailService emailService;

    @Override
    public InterviewEntity createInterview(InterviewDTO dto) {
        InterviewEntity entity = new InterviewEntity();
        entity.setHrName(dto.getHrName());
        entity.setCandidateName(dto.getCandidateName());
        entity.setCandidateEmail(dto.getCandidateEmail());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setInterviewDate(dto.getInterviewDate());

        InterviewEntity saved = interviewRepository.save(entity);
        saved.setInterviewId(IdGenerateUtil.convertObjectIdToNumeric(saved.getId()));
        
        // ✅ Send Email after scheduling interview
        emailService.sendInterviewNotification(
                saved.getCandidateName(),
                saved.getCandidateEmail(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getInterviewDate(),
                saved.getHrName()
        );

        return interviewRepository.save(saved);
    }


    @Override
    public Optional<InterviewEntity> getInterviewById(String interviewId) {
        return interviewRepository.findByInterviewId(interviewId);
    }

    @Override
    public InterviewEntity updateInterview(String interviewId, InterviewDTO dto) {
        Optional<InterviewEntity> optional = interviewRepository.findByInterviewId(interviewId);

        if (optional.isEmpty()) {
            throw new RuntimeException("Interview not found with id: " + interviewId);
        }

        InterviewEntity entity = optional.get();
        entity.setHrName(dto.getHrName());
        entity.setCandidateName(dto.getCandidateName());
        entity.setCandidateEmail(dto.getCandidateEmail());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setInterviewDate(dto.getInterviewDate());

        return interviewRepository.save(entity);
    }

    @Override
    public void deleteInterview(String interviewId) {
        Optional<InterviewEntity> optional = interviewRepository.findByInterviewId(interviewId);
        optional.ifPresent(interviewRepository::delete);
    }

    @Override
    public Page<InterviewEntity> searchByTitle(String title, Pageable pageable) {
        return interviewRepository.findByTitleContainingIgnoreCase(title, pageable);
    }

    @Override
    public Page<InterviewEntity> searchByHrName(String hrName, Pageable pageable) {
        return interviewRepository.findByHrNameContainingIgnoreCase(hrName, pageable);
    }

    @Override
    public Page<InterviewEntity> searchByCandidateName(String candidateName, Pageable pageable) {
        return interviewRepository.searchByCandidateName(candidateName, pageable);
    }

    @Override
    public Page<InterviewEntity> searchByCandidateEmail(String candidateEmail, Pageable pageable) {
        return interviewRepository.searchByCandidateEmail(candidateEmail, pageable);
    }

    @Override
    public Page<InterviewEntity> getAllInterviews(Pageable pageable) {
        return interviewRepository.findAll(pageable);
    }
}
