package com.gfss.hr_portal_backend.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event {

	private String id;
	private String title;
	private String description;
	private LocalDate date;
	private String location;
	private String organizer;
//	private String eventImages;

}
