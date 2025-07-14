package com.gfss.hr_portal_backend.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfss.hr_portal_backend.entity.HolidayEntity;
import com.gfss.hr_portal_backend.repository.HolidayRepository;
import com.gfss.hr_portal_backend.resultVO.HolidayRequest;
import com.gfss.hr_portal_backend.service.HolidayService;

@Service
public class HolidayServiceImpl implements HolidayService {

    @Autowired
    private HolidayRepository holidayRepository;

    private String convertObjectIdToNumeric(String objectId) {
        StringBuilder numericId = new StringBuilder();
        for (char ch : objectId.toCharArray()) {
            numericId.append((int) ch);
        }
        return numericId.toString();
    }

    @Override
    public HolidayEntity addHoliday(HolidayRequest request) {
        HolidayEntity holiday = new HolidayEntity();
        holiday.setDate(request.getDate());
        holiday.setHolidayName(request.getHolidayName());
        holiday.setDescription(request.getDescription());

        // Insert to generate _id
        holiday = holidayRepository.insert(holiday);

        // Convert Mongo _id to numeric string and set as holidayId
        holiday.setHolidayId(convertObjectIdToNumeric(holiday.getId()));

        return holidayRepository.save(holiday);
    }

    @Override
    public HolidayEntity updateHoliday(String holidayId, HolidayRequest request) {
        Optional<HolidayEntity> optional = holidayRepository.findByHolidayId(holidayId);
        if (optional.isPresent()) {
            HolidayEntity holiday = optional.get();
            holiday.setDate(request.getDate());
            holiday.setHolidayName(request.getHolidayName());
            holiday.setDescription(request.getDescription());
            return holidayRepository.save(holiday);
        }
        return null;
    }

    @Override
    public boolean deleteHoliday(String holidayId) {
        if (holidayRepository.existsByHolidayId(holidayId)) {
            holidayRepository.deleteByHolidayId(holidayId);
            return true;
        }
        return false;
    }

    @Override
    public HolidayEntity getHolidayByHolidayId(String holidayId) {
        return holidayRepository.findByHolidayId(holidayId).orElse(null);
    }

    @Override
    public List<HolidayEntity> getAllHolidays() {
        return holidayRepository.findAll();
    }
}
