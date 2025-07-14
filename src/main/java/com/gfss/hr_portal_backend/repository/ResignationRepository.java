package com.gfss.hr_portal_backend.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gfss.hr_portal_backend.entity.Resignation;

@Repository
public interface ResignationRepository extends MongoRepository<Resignation, String> 
{
//	    List<Resignation> findByEmpId(String empId);
	   
	    Resignation findByRegId(String regId);
	    
	    boolean existsByRegId(String regId);
	    
	    void deleteByRegId(String regId);

	    List<Resignation> findByEmpNameContainingIgnoreCase(String empName);
	    
//	    Resignation findByIdAndStatus(String id, Resignation.Status status);
}
