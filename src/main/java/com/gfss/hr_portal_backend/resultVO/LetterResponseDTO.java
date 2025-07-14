package com.gfss.hr_portal_backend.resultVO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class LetterResponseDTO
{
	private String employeeName;
    private String letterType;
    private String fileName;
    private LocalDate uploadDate;
    
    
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getLetterType() {
		return letterType;
	}
	public void setLetterType(String letterType) {
		this.letterType = letterType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public LocalDate getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(LocalDate uploadDate) {
		this.uploadDate = uploadDate;
	}

}
