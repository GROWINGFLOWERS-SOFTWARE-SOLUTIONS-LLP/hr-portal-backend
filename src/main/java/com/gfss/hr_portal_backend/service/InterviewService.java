package com.gfss.hr_portal_backend.service;

import com.gfss.hr_portal_backend.resultVO.InterviewDTO;
import com.gfss.hr_portal_backend.entity.InterviewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface InterviewService {

    InterviewEntity createInterview(InterviewDTO interviewDTO);

    Optional<InterviewEntity> getInterviewById(String interviewId);

    InterviewEntity updateInterview(String interviewId, InterviewDTO interviewDTO);

    void deleteInterview(String interviewId);

    Page<InterviewEntity> searchByTitle(String title, Pageable pageable);

    Page<InterviewEntity> searchByHrName(String hrName, Pageable pageable);

    Page<InterviewEntity> searchByCandidateName(String candidateName, Pageable pageable);

    Page<InterviewEntity> searchByCandidateEmail(String candidateEmail, Pageable pageable);

    Page<InterviewEntity> getAllInterviews(Pageable pageable);
}
