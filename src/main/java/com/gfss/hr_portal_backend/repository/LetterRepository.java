	package com.gfss.hr_portal_backend.repository;
	
	import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
	import org.springframework.stereotype.Repository;
	
	import com.gfss.hr_portal_backend.entity.Letter;
	@Repository
	public interface LetterRepository extends MongoRepository<Letter, String>
	{
		boolean existsByLetterId(String letterId);

		void deleteByLetterId(String letterId);
//		List<Letter> findByEmployeeName(String employeeName);

		Letter findByLetterId(String letterId);
	}
