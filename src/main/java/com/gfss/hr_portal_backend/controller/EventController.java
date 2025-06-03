package com.gfss.hr_portal_backend.controller;

import com.gfss.hr_portal_backend.entity.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final Map<String, Event> dummyEventStore = new HashMap<>();

    public EventController() {
        // Dummy sample event
        String id = UUID.randomUUID().toString();
        dummyEventStore.put(id, new Event(
                id,
                "Tech Conference",
                "A conference on latest tech trends.",
                LocalDate.of(2025, 7, 15),
                "Bangalore",
                "TechOrg"
        ));
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return new ArrayList<>(dummyEventStore.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        Event event = dummyEventStore.get(id);
        return (event != null) ? ResponseEntity.ok(event) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        String id = UUID.randomUUID().toString();
        event.setId(id);
        dummyEventStore.put(id, event);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable String id, @RequestBody Event event) {
        if (!dummyEventStore.containsKey(id)) {
            return ResponseEntity.notFound().build();
        }
        event.setId(id);
        dummyEventStore.put(id, event);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable String id) {
        if (dummyEventStore.remove(id) != null) {
            return ResponseEntity.ok("Event deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
