package com.gfss.hr_portal_backend.resultVO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {

	 private String empId;
	    private String firstName;
	    private String lastName;
	    private String department;
	    private String role;
	    private String mobile;
	    private String email;
	    private String joiningDate;
	    private String address;
		public String getEmpId() {
			return empId;
		}
		public String getFirstName() {
			return firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public String getDepartment() {
			return department;
		}
		public String getRole() {
			return role;
		}
		public String getMobile() {
			return mobile;
		}
		public String getEmail() {
			return email;
		}
		public String getJoiningDate() {
			return joiningDate;
		}
		public String getAddress() {
			return address;
		}

}
