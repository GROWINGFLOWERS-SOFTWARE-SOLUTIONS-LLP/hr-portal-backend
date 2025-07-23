package com.gfss.hr_portal_backend.resultVO;

import java.util.List;

public class DashboardSummaryDTO 
{
	private long totalEmployees;
    private long totalResignations;
    private List<HolidayCalenderDTO> upcomingHolidays;

    public DashboardSummaryDTO() {}

    public DashboardSummaryDTO(long totalEmployees, long totalResignations, List<HolidayCalenderDTO> upcomingHolidays) {
        this.totalEmployees = totalEmployees;
        this.totalResignations = totalResignations;
        this.upcomingHolidays = upcomingHolidays;
    }

    public long getTotalEmployees() { return totalEmployees; }
    public void setTotalEmployees(long totalEmployees) { this.totalEmployees = totalEmployees; }

    public long getTotalResignations() { return totalResignations; }
    public void setTotalResignations(long totalResignations) { this.totalResignations = totalResignations; }

    public List<HolidayCalenderDTO> getUpcomingHolidays() { return upcomingHolidays; }
    public void setUpcomingHolidays(List<HolidayCalenderDTO> upcomingHolidays) { this.upcomingHolidays = upcomingHolidays; }
}
