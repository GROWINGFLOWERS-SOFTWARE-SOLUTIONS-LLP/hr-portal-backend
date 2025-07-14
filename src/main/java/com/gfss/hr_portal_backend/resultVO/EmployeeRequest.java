package com.gfss.hr_portal_backend.resultVO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class EmployeeRequest 
{
	private String empId;
	 
    @NotBlank(message = "First name is mandatory")

    private String firstName;
 
    @NotBlank(message = "Last name is mandatory")

    private String lastName;
 
    @NotBlank(message = "Department is mandatory")

    private String department;
 
    @NotBlank(message = "Manager name is mandatory")

    private String manager;
 
    @NotBlank(message = "Mobile number is mandatory")

    @Size(min = 10, max = 10, message = "Mobile number must be 10 digits")

    private String mobile;
 
    @Email(message = "EmailId should be a valid email address")

    @NotBlank(message = "Email is mandatory")

    private String emailId;
 
    @NotBlank(message = "Password is mandatory")

    private String password;
 
    private String oldpassword;
 
    @NotBlank(message = "Role is mandatory")

    private String role;
 
    @NotBlank(message = "Joining date is mandatory")

    private String joiningDate;
 
    @NotBlank(message = "Address is mandatory")

    private String address;
 
    @NotBlank(message = "CreatedBy is mandatory")

    private String createdBy;
 
    @NotBlank(message = "ModifiedBy is mandatory")

    private String modifiedBy;

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(String joiningDate) {
		this.joiningDate = joiningDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
    
    

}
