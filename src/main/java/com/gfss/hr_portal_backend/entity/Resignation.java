
	
	package com.gfss.hr_portal_backend.entity;

	import java.time.LocalDate;

	import org.springframework.data.annotation.Id;
	import org.springframework.data.mongodb.core.mapping.Document;

	@Document(collection = "resignations")
	public class Resignation
	{
		    @Id
		    private String id;
		    
		    private String regId;

		    public String getRegId() {
				return regId;
			}

			public void setRegId(String regId) {
				this.regId = regId;
			}

			

		    private String empName;

		    private LocalDate resignDate;

		    private LocalDate lastWorkingDate;

		    private Integer noticePeriodDays;
		    
//		    private Status status;
		    
		    private String status;
		    
		    private String reason;

//		    public enum Status {
//		        Pending,
//		        Accepted,
//		        Rejected
//		    }

		    

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}

		

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

			public Integer getNoticePeriodDays() {
				return noticePeriodDays;
			}

			public void setNoticePeriodDays(Integer noticePeriodDays) {
				this.noticePeriodDays = noticePeriodDays;
			}

			public String getReason() {
				return reason;
			}

			public void setReason(String reason) {
				this.reason = reason;
			}

			public String getStatus() {
				return status;
			}

			public void setStatus(String status) {
				this.status = status;
			}

			@Override
			public String toString() {
				return "Resignation [id=" + id + ", regId=" + regId + ", empName=" + empName
						+ ", resignDate=" + resignDate + ", lastWorkingDate=" + lastWorkingDate + ", noticePeriodDays="
						+ noticePeriodDays + ", status=" + status + ", reason=" + reason + "]";
			}
			

//			public Status getStatus() {
//				return status;
//			}
	//
//			public void setStatus(Status status) {
//				this.status = status;
//			}
		    
		    

	}



