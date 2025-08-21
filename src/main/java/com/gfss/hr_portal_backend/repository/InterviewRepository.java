package com.gfss.hr_portal_backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.gfss.hr_portal_backend.entity.InterviewEntity;

import java.util.Optional;

public interface InterviewRepository extends MongoRepository<InterviewEntity, String> {
    
    Optional<InterviewEntity> findByInterviewId(String interviewId);
    void deleteByInterviewId(String interviewId);
    
    // 🔹 Search by title (case-insensitive)
    Page<InterviewEntity> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    // 🔹 Search by HR name (case-insensitive)
    Page<InterviewEntity> findByHrNameContainingIgnoreCase(String hrName, Pageable pageable);

    // 🔹 Search by candidate name inside List<String>
    @Query("{ 'candidateName': { $regex: ?0, $options: 'i' } }")
    Page<InterviewEntity> searchByCandidateName(String candidateName, Pageable pageable);

    // 🔹 Search by candidate email inside List<String>
    @Query("{ 'candidateEmail': { $regex: ?0, $options: 'i' } }")
    Page<InterviewEntity> searchByCandidateEmail(String candidateEmail, Pageable pageable);
}
