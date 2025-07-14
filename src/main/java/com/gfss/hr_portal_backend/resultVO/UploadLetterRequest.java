package com.gfss.hr_portal_backend.resultVO;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
@Data
public class UploadLetterRequest
{
	 private String employeeName;
	    private String letterType;
	    private MultipartFile file;
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
		public MultipartFile getFile() {
			return file;
		}
		public void setFile(MultipartFile file) {
			this.file = file;
		}
	    
}
