package com.gfss.hr_portal_backend.service;

import java.util.List;

import com.gfss.hr_portal_backend.entity.EventEntity;
import com.gfss.hr_portal_backend.resultVO.EventRequest;

public interface EventService {

    EventEntity addEvent(EventRequest request);

    EventEntity updateEvent(String eventId, EventRequest request);

    boolean deleteEvent(String eventId);

    EventEntity getEventByEventId(String eventId);

    List<EventEntity> getAllEvents();

    List<EventEntity> searchEventByTitle(String title);
}
