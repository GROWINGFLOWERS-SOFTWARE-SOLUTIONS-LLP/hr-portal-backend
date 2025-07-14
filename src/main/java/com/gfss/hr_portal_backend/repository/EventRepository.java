package com.gfss.hr_portal_backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.gfss.hr_portal_backend.entity.EventEntity;

@Repository
public interface EventRepository extends MongoRepository<EventEntity, String> {

    Optional<EventEntity> findByEventId(String eventId);

    boolean existsByEventId(String eventId);

    void deleteByEventId(String eventId);

    List<EventEntity> findByTitleContainingIgnoreCase(String title);
}
