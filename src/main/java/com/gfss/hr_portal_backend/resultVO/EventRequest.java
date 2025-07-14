package com.gfss.hr_portal_backend.resultVO;

import java.time.LocalDate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventRequest {

    @NotBlank(message = "Title is required.")
    private String title;

    private String description;

    @NotNull(message = "Date is required.")
    private LocalDate date;

    @NotBlank(message = "Location is required.")
    private String location;

    @NotBlank(message = "Organizer is required.")
    private String organizer;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}
}
