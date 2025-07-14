package com.gfss.hr_portal_backend.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gfss.hr_portal_backend.entity.HolidayEntity;

@Repository
public interface HolidayRepository extends MongoRepository<HolidayEntity, String> {

    Optional<HolidayEntity> findByHolidayId(String holidayId);

    boolean existsByHolidayId(String holidayId);

    void deleteByHolidayId(String holidayId);
}
