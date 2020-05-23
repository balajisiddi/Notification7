package com.sectorseven.service.admin;

import java.util.List;

import com.sectorseven.model.admin.Parents;
import com.sectorseven.model.school.Student;

import javassist.NotFoundException;

public interface ParentsService {

	List<Parents> findByFatherMobileOrFatherEmail(Long fatherMobile, String fatherEmail);
	
	List<Parents> findByEmailOrContactNoAndIdNotIn(String email, Long mobile, Long id);

	Parents saveParentData(Parents parents) throws Exception;
	
	Parents updateParents(Parents parent,long parentId) throws Exception;

	Parents update(Parents parent);
	
	Parents findAdministratorByUsername(String username) throws Exception;


	List<Student> getChildsByParent(Parents parents);
	
	public String generateUserName(String fatheName,String motherName);

	Parents forgotPassword(String username) throws Exception;

	String verifyOtp(String otp, Long userId, String userType,String emailId,String phoneNo,Integer parent) throws Exception;

	String changePassword(String password,  Long userId) throws NotFoundException;

	Parents findParentById(Long parentId) throws Exception;

	void updateProfileImage(Parents parents);

	Parents updatedescParents(String desc, Long userId) throws NotFoundException;

	String sendOtp(String emailId, Long userId, String userType) throws Exception;

	public String sendMobileOtp(String phoneNo, Long userId, String userType) throws Exception;

}
