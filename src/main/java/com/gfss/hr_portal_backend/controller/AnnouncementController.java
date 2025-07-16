package com.gfss.hr_portal_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.gfss.hr_portal_backend.entity.AnnouncementEntity;
import com.gfss.hr_portal_backend.resultVO.AnnouncementRequest;
import com.gfss.hr_portal_backend.resultVO.ApiResponse;
import com.gfss.hr_portal_backend.service.AnnouncementService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    // Create
    @PostMapping("/announcement")
    public ResponseEntity<ApiResponse<AnnouncementEntity>> create(@Valid @RequestBody AnnouncementRequest request) {
        AnnouncementEntity saved = announcementService.createAnnouncement(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("success", "Announcement created successfully", saved));
    }

    // Update
    @PutMapping("/announcement/{announcementId}")
    public ResponseEntity<ApiResponse<AnnouncementEntity>> update(
            @PathVariable String announcementId,
            @Valid @RequestBody AnnouncementRequest request) {
        AnnouncementEntity updated = announcementService.updateAnnouncement(announcementId, request);
        if (updated != null) {
            return ResponseEntity.ok(new ApiResponse<>("success", "Announcement updated successfully", updated));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>("error", "Announcement not found", null));
    }

    // Delete
    @DeleteMapping("/announcement/{announcementId}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String announcementId) {
        if (announcementService.deleteAnnouncement(announcementId)) {
            return ResponseEntity.ok(new ApiResponse<>("success", "Announcement deleted successfully", null));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>("error", "Announcement not found", null));
    }

    // Get By ID
    @GetMapping("/announcement/{announcementId}")
    public ResponseEntity<ApiResponse<AnnouncementEntity>> getById(@PathVariable String announcementId) {
        AnnouncementEntity found = announcementService.getAnnouncementById(announcementId);
        if (found != null) {
            return ResponseEntity.ok(new ApiResponse<>("success", "Announcement fetched", found));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse<>("error", "Announcement not found", null));
    }

    // Get All
    @GetMapping("/announcement")
    public ResponseEntity<ApiResponse<List<AnnouncementEntity>>> getAll() {
        return ResponseEntity.ok(new ApiResponse<>("success", "All announcements fetched",
                announcementService.getAllAnnouncements()));
    }

    // Search by Title
    @GetMapping("/announcement/search")
    public ResponseEntity<ApiResponse<List<AnnouncementEntity>>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(new ApiResponse<>("success", "Search results",
                announcementService.searchAnnouncementsByTitle(title)));
    }
}
