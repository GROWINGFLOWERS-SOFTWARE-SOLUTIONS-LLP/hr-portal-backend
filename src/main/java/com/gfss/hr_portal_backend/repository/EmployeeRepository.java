package com.gfss.hr_portal_backend.repository;
 
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.gfss.hr_portal_backend.entity.EmployeeEntity;
 
@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeEntity, String> {
	EmployeeEntity findByEmpId(String empId);
	boolean existsByEmpId(String empId);
	void deleteByEmpId(String empId);
	EmployeeEntity findByEmailId(String emailId);
	// To find Role
	List<EmployeeEntity> findByRole(String role);
	
	 Optional<EmployeeEntity> findByFirstNameAndLastName(String firstName, String lastName);
	   

}