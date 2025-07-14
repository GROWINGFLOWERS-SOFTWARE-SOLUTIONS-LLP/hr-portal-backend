package com.gfss.hr_portal_backend.service;

import java.util.List;

import com.gfss.hr_portal_backend.entity.HolidayEntity;
import com.gfss.hr_portal_backend.resultVO.HolidayRequest;

public interface HolidayService {

    HolidayEntity addHoliday(HolidayRequest request);

    HolidayEntity updateHoliday(String holidayId, HolidayRequest request);

    boolean deleteHoliday(String holidayId);

    HolidayEntity getHolidayByHolidayId(String holidayId);

    List<HolidayEntity> getAllHolidays();
}
