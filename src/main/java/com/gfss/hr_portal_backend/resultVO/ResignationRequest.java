package com.gfss.hr_portal_backend.resultVO;

import java.time.LocalDate;

import lombok.Data;
@Data
public class ResignationRequest
{
	private String empName;
	private LocalDate resignDate;
	private LocalDate lastWorkingDate;
	private String reason;
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public LocalDate getResignDate() {
		return resignDate;
	}
	public void setResignDate(LocalDate resignDate) {
		this.resignDate = resignDate;
	}
	public LocalDate getLastWorkingDate() {
		return lastWorkingDate;
	}
	public void setLastWorkingDate(LocalDate lastWorkingDate) {
		this.lastWorkingDate = lastWorkingDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "ResignationRequest [empName=" + empName + ", resignDate=" + resignDate + ", lastWorkingDate="
				+ lastWorkingDate + ", reason=" + reason + "]";
	}
	

}
