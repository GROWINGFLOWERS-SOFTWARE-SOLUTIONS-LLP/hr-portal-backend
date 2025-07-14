package com.gfss.hr_portal_backend.resultVO;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HolidayRequest {

    private String holidayId; // optional, for update only

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotBlank(message = "Holiday name is required")
    private String holidayName;

    @NotBlank(message = "Description is required")
    private String description;

    // Getter and Setter for holidayId
    public String getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(String holidayId) {
        this.holidayId = holidayId;
    }

    // Getter and Setter for date
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    // Getter and Setter for holidayName
    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    // Getter and Setter for description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
