package com.gfss.hr_portal_backend.service;

import java.util.List;
import java.util.Optional;

import com.gfss.hr_portal_backend.entity.Resignation;
import com.gfss.hr_portal_backend.resultVO.ResignationRequest;

public interface ResignationService
{
	Resignation saveResignation(ResignationRequest resignation);

    List<Resignation> getAllResignations();
    
    Resignation getResignationByRegId(String regId);

    Optional<Resignation> getResignationById(String id);

    Resignation updateResignation(String id, ResignationRequest resignation);
    
//    Resignation findByIdAndStatus(String id, Resignation.Status status);

//    void deleteResignation(String id);
    
    boolean deleteResignation(String id);

    List<Resignation> findByEmpNameContainingIgnoreCase(String empName);
}
