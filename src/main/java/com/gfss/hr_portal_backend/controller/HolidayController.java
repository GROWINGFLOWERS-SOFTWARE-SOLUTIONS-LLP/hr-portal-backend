package com.gfss.hr_portal_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.gfss.hr_portal_backend.entity.HolidayEntity;
import com.gfss.hr_portal_backend.resultVO.ApiResponse;
import com.gfss.hr_portal_backend.resultVO.HolidayRequest;
import com.gfss.hr_portal_backend.service.HolidayService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class HolidayController {

    @Autowired
    private HolidayService holidayService;

    @PostMapping("/holidays")
    public ResponseEntity<ApiResponse<HolidayEntity>> createHoliday(@Valid @RequestBody HolidayRequest request) {
        HolidayEntity holiday = holidayService.addHoliday(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("Success", "Holiday Created", holiday));
    }

    @PutMapping("/holidays/{holidayId}")
    public ResponseEntity<ApiResponse<HolidayEntity>> updateHoliday(@PathVariable String holidayId,
            @Valid @RequestBody HolidayRequest request) {
        HolidayEntity updated = holidayService.updateHoliday(holidayId, request);
        return ResponseEntity.ok(new ApiResponse<>("Success", "Holiday Updated", updated));
    }

    @DeleteMapping("/holidays/{holidayId}")
    public ResponseEntity<ApiResponse<Void>> deleteHoliday(@PathVariable String holidayId) {
        holidayService.deleteHoliday(holidayId);
        return ResponseEntity.ok(new ApiResponse<>("Success", "Holiday Deleted", null));
    }

    @GetMapping("/holidays/{holidayId}")
    public ResponseEntity<ApiResponse<HolidayEntity>> getHolidayById(@PathVariable String holidayId) {
        HolidayEntity holiday = holidayService.getHolidayByHolidayId(holidayId);
        return ResponseEntity.ok(new ApiResponse<>("Success", "Holiday Retrieved", holiday));
    }

    @GetMapping("/holidays")
    public ResponseEntity<ApiResponse<List<HolidayEntity>>> getAllHolidays() {
        List<HolidayEntity> holidays = holidayService.getAllHolidays();
        return ResponseEntity.ok(new ApiResponse<>("Success", "All Holidays", holidays));
    }
}
