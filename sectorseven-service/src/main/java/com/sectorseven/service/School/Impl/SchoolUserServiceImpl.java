package com.sectorseven.service.School.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sectorseven.model.school.SchoolUsers;
import com.sectorseven.repository.School.SchoolUserRepository;
import com.sectorseven.service.School.SchoolUserService;

@Service
@Transactional
public class SchoolUserServiceImpl implements SchoolUserService{

	@Autowired
	private SchoolUserRepository schoolUserRepository;

	@Override
	public SchoolUsers findAdministratorByUsername(String username) {
		return schoolUserRepository.findByUserName(username);
	}

	@Override
	public List<SchoolUsers> findByEmailOrContactNo(String email, Long contactNo) {
		return schoolUserRepository.findByEmailOrPhone(email, contactNo);
	}

	@Override
	public List<SchoolUsers> findByEmailOrContactNoAndIdNotIn(String email, Long contactNo, Long id) {
		return schoolUserRepository.findByEmailOrContactNoAndIdNotIn(email, contactNo, id);
	}
	
}
