package com.gfss.hr_portal_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfss.hr_portal_backend.resultVO.ApiResponse;
import com.gfss.hr_portal_backend.resultVO.DashboardSummaryDTO;
import com.gfss.hr_portal_backend.resultVO.HolidayCalenderDTO;

import com.gfss.hr_portal_backend.service.EmployeeService;
import com.gfss.hr_portal_backend.service.HolidayService;
import com.gfss.hr_portal_backend.service.ResignationService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController 
{
	 	@Autowired
	    private EmployeeService employeeService;

	    @Autowired
	    private HolidayService holidayService;

	    @Autowired
	    private ResignationService resignationService;

	    @GetMapping("/summary")
	    public ResponseEntity<ApiResponse<DashboardSummaryDTO>> getDashboardSummary() {
	        long totalEmployees = employeeService.countEmployees();
	        long totalResignations = resignationService.countResignations();
	        List<HolidayCalenderDTO> upcomingHolidays = holidayService.getNext4HolidayDTOs();

	        DashboardSummaryDTO dto = new DashboardSummaryDTO(
	                totalEmployees,
	                totalResignations,
	                upcomingHolidays
	        );

	        ApiResponse<DashboardSummaryDTO> response = new ApiResponse<>(
	                "SUCCESS",
	                "Dashboard summary fetched successfully",
	                dto
	        );

	        return ResponseEntity.ok(response);
	    }
	
}
