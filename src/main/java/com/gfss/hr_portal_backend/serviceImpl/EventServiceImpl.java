package com.gfss.hr_portal_backend.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfss.hr_portal_backend.entity.EventEntity;
import com.gfss.hr_portal_backend.repository.EventRepository;
import com.gfss.hr_portal_backend.resultVO.EventRequest;
import com.gfss.hr_portal_backend.service.EventService;
import com.gfss.hr_portal_backend.utils.IdGenerateUtil;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public EventEntity addEvent(EventRequest request) {
        EventEntity event = new EventEntity();
        event.setTitle(request.getTitle());
        event.setDescription(request.getDescription());
        event.setDate(request.getDate());
        event.setLocation(request.getLocation());
        event.setOrganizer(request.getOrganizer());

        // Step 1: Insert to generate MongoDB _id
        event = eventRepository.insert(event);

        // Step 2: Convert ObjectId to numeric string and set as eventId
        event.setEventId(IdGenerateUtil.convertObjectIdToNumeric(event.getId()));

        // Step 3: Save again with eventId
        return eventRepository.save(event);
    }

    @Override
    public EventEntity updateEvent(String eventId, EventRequest request) {
        Optional<EventEntity> optional = eventRepository.findByEventId(eventId);
        if (optional.isPresent()) {
            EventEntity event = optional.get();
            event.setTitle(request.getTitle());
            event.setDescription(request.getDescription());
            event.setDate(request.getDate());
            event.setLocation(request.getLocation());
            event.setOrganizer(request.getOrganizer());
            return eventRepository.save(event);
        }
        return null;
    }

    @Override
    public boolean deleteEvent(String eventId) {
        if (eventRepository.existsByEventId(eventId)) {
            eventRepository.deleteByEventId(eventId);
            return true;
        }
        return false;
    }

    @Override
    public EventEntity getEventByEventId(String eventId) {
        return eventRepository.findByEventId(eventId).orElse(null);
    }

    @Override
    public List<EventEntity> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<EventEntity> searchEventByTitle(String title) {
        return eventRepository.findByTitleContainingIgnoreCase(title);
    }
}
