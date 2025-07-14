package com.gfss.hr_portal_backend.entity;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection ="myletters")
public class Letter {
	    @Id
	    private String id;
	    private String letterId;
	    private String empName; 
		private String empId;
        private String letterType;
	    private String fileName;
	    private byte[] fileData;
	    private LocalDate uploadDate;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
//		public String getEmployeeName() {
//			return employeeName;
//		}
//		public void setEmployeeName(String employeeName) {
//			this.employeeName = employeeName;
//		}
		public String getLetterType() {
			return letterType;
		}
		public void setLetterType(String letterType) {
			this.letterType = letterType;
		}
		 public String getLetterId() {
				return letterId;
			}
			public void setLetterId(String letterId) {
				this.letterId = letterId;
			}
		
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		public byte[] getFileData() {
			return fileData;
		}
		public void setFileData(byte[] fileData) {
			this.fileData = fileData;
		}
		public LocalDate getUploadDate() {
			return uploadDate;
		}
		public void setUploadDate(LocalDate uploadDate) {
			this.uploadDate = uploadDate;
		}
		public String getEmpId() {
			return empId;
		}
		public void setEmpId(String empId) {
			this.empId = empId;
		}
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		@Override
		public String toString() {
			return "Letter [id=" + id + ", letterId=" + letterId + ", empName=" + empName + ", empId=" + empId
					+ ", letterType=" + letterType + ", fileName=" + fileName + ", fileData="
					+ Arrays.toString(fileData) + ", uploadDate=" + uploadDate + "]";
		}
		
}
