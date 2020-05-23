package com.sectorseven.service.School;

import java.util.List;

import com.sectorseven.model.school.SchoolUsers;

public interface SchoolUserService {

	SchoolUsers findAdministratorByUsername(String username);

	List<SchoolUsers> findByEmailOrContactNo(String email, Long contactNo);

	List<SchoolUsers> findByEmailOrContactNoAndIdNotIn(String email, Long contactNo, Long id);

}
