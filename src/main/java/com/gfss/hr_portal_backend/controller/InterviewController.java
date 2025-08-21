package com.gfss.hr_portal_backend.controller;

import com.gfss.hr_portal_backend.resultVO.InterviewDTO;
import com.gfss.hr_portal_backend.entity.InterviewEntity;
import com.gfss.hr_portal_backend.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    // ✅ Create
    @PostMapping
    public ResponseEntity<InterviewEntity> createInterview(@RequestBody InterviewDTO interviewDTO) {
        return ResponseEntity.ok(interviewService.createInterview(interviewDTO));
    }

    // ✅ Get by custom interviewId
    @GetMapping("/{interviewId}")
    public ResponseEntity<InterviewEntity> getInterview(@PathVariable String interviewId) {
        return interviewService.getInterviewById(interviewId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ Update
    @PutMapping("/{interviewId}")
    public ResponseEntity<InterviewEntity> updateInterview(@PathVariable String interviewId,
                                                           @RequestBody InterviewDTO interviewDTO) {
        return ResponseEntity.ok(interviewService.updateInterview(interviewId, interviewDTO));
    }

    // ✅ Delete
    @DeleteMapping("/{interviewId}")
    public ResponseEntity<Void> deleteInterview(@PathVariable String interviewId) {
        interviewService.deleteInterview(interviewId);
        return ResponseEntity.noContent().build();
    }

    // ✅ Search by title
    @GetMapping("/search/title")
    public ResponseEntity<Page<InterviewEntity>> searchByTitle(@RequestParam String title, Pageable pageable) {
        return ResponseEntity.ok(interviewService.searchByTitle(title, pageable));
    }

    // ✅ Search by HR name
    @GetMapping("/search/hr")
    public ResponseEntity<Page<InterviewEntity>> searchByHr(@RequestParam String hrName, Pageable pageable) {
        return ResponseEntity.ok(interviewService.searchByHrName(hrName, pageable));
    }

    // ✅ Search by candidate name
    @GetMapping("/search/candidate-name")
    public ResponseEntity<Page<InterviewEntity>> searchByCandidateName(@RequestParam String candidateName, Pageable pageable) {
        return ResponseEntity.ok(interviewService.searchByCandidateName(candidateName, pageable));
    }

    // ✅ Search by candidate email
    @GetMapping("/search/candidate-email")
    public ResponseEntity<Page<InterviewEntity>> searchByCandidateEmail(@RequestParam String candidateEmail, Pageable pageable) {
        return ResponseEntity.ok(interviewService.searchByCandidateEmail(candidateEmail, pageable));
    }
}
