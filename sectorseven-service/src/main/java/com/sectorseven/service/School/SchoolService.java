package com.sectorseven.service.School;

import java.util.List;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.school.School;

public interface SchoolService {

	School findById(long id);

	List<School> findByStatus(Status active);

	void save(School school);

	School findByEmailAndStatus(String email, Status active);

	String generatePassword(int len);

	List<School> findByEmailOrContactNoAndStatus(String email, long contactNo, Status active);

	List<School> findByEmailOrContactNoOrSchoolCode(String email, Long contactNo, String schoolCode);

	List<School> findAll();

	void update(School school);

	List<School> findByEmailOrContactNoOrSchoolCodeAndIdNotIn(String email, Long contactNo, String schoolCode, Long id);

	
}
