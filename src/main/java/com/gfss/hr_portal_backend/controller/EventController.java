package com.gfss.hr_portal_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.gfss.hr_portal_backend.entity.EventEntity;
import com.gfss.hr_portal_backend.resultVO.ApiResponse;
import com.gfss.hr_portal_backend.resultVO.EventRequest;
import com.gfss.hr_portal_backend.service.EventService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping("/events")
    public ResponseEntity<ApiResponse<EventEntity>> createEvent(@Valid @RequestBody EventRequest request) {
        EventEntity event = eventService.addEvent(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Success", "Event Created", event));
    }

    @PutMapping("/events/{eventId}")
    public ResponseEntity<ApiResponse<EventEntity>> updateEvent(@PathVariable String eventId,
            @Valid @RequestBody EventRequest request) {
        EventEntity updated = eventService.updateEvent(eventId, request);
        return ResponseEntity.ok(new ApiResponse<>("Success", "Event Updated", updated));
    }

    @DeleteMapping("/events/{eventId}")
    public ResponseEntity<ApiResponse<Void>> deleteEvent(@PathVariable String eventId) {
        eventService.deleteEvent(eventId);
        return ResponseEntity.ok(new ApiResponse<>("Success", "Event Deleted", null));
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<ApiResponse<EventEntity>> getEventById(@PathVariable String eventId) {
        EventEntity event = eventService.getEventByEventId(eventId);
        return ResponseEntity.ok(new ApiResponse<>("Success", "Event Retrieved", event));
    }

    @GetMapping("/events")
    public ResponseEntity<ApiResponse<List<EventEntity>>> getAllEvents() {
        List<EventEntity> events = eventService.getAllEvents();
        return ResponseEntity.ok(new ApiResponse<>("Success", "All Events", events));
    }

    @GetMapping("/events/search")
    public ResponseEntity<ApiResponse<List<EventEntity>>> searchByTitle(@RequestParam String title) {
        List<EventEntity> events = eventService.searchEventByTitle(title);
        return ResponseEntity.ok(new ApiResponse<>("Success", "Filtered Events", events));
    }
}
