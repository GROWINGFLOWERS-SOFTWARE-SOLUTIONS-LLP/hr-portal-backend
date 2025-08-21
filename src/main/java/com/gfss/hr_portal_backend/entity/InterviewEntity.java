package com.gfss.hr_portal_backend.entity;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Interviews")
public class InterviewEntity {

	@Id
	private String id; // MongoDB ObjectId

	private String interviewId; // Custom numeric ID derived from ObjectId
	private String hrName;
	private List<String> candidateName;
	private List<String> candidateEmail;
	private String title;
	private String description;
	private String interviewDate; // ISO string (yyyy-MM-dd)

	// Getters and Setters
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(String interviewId) {
		this.interviewId = interviewId;
	}

	public String getHrName() {
		return hrName;
	}

	public void setHrName(String hrName) {
		this.hrName = hrName;
	}

	public List<String> getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(List<String> candidateName) {
		this.candidateName = candidateName;
	}

	public List<String> getCandidateEmail() {
		return candidateEmail;
	}

	public void setCandidateEmail(List<String> candidateEmail) {
		this.candidateEmail = candidateEmail;
	}

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

	public String getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}
}
