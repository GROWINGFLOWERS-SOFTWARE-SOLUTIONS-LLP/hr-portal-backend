package com.gfss.hr_portal_backend.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gfss.hr_portal_backend.entity.Letter;
import com.gfss.hr_portal_backend.resultVO.LetterResponseDTO;

public interface LetterService
{
	 
	
	    Letter savePdf(String employeeName, String letterType, MultipartFile file) throws Exception;
	    
	    Letter getLetterByLetterId(String letterId);
	   
//	     List<LetterResponseDTO> getAllLetters();
	    

	    
//	    List<Letter> findByEmployeeName(String employeeName);

//	    Letter getLetterById(String id) throws Exception;

	    Letter updateLetter(String id, String employeeName, String letterType, MultipartFile file) throws Exception;
	    
	    boolean deleteLetter(String id);

//	    void deleteLetter(String id) throws Exception;

	    byte[] downloadFile(String id) throws Exception;
	    public List<Letter> getAllLetters();

}
