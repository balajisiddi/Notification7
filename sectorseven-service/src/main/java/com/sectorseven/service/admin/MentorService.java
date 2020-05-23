package com.sectorseven.service.admin;

import java.util.List;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.admin.Mentor;
import com.sectorseven.model.common.MentorFollowers;
import com.sectorseven.req.UpdateRequest;

import javassist.NotFoundException;

public interface MentorService {

	List<Mentor> findByEmailOrContactNo(String email, Long mobile);

	List<Mentor> findByEmailOrContactNoAndIdNotIn(String email, Long mobile, Long id);

	Mentor save(Mentor mentor, boolean excelornot) throws Exception;

	void update(Mentor mentor);

	List<Mentor> findByStatus(Status active);

	Mentor findById(long menotrId) throws Exception; 

	Mentor findAdministratorByUsername(String username) throws Exception;
	
	Mentor findMentorById(long mentorId);
	
	Mentor updateMentor(UpdateRequest existMentor,long mentorId) throws Exception;
	
	 MentorFollowers findallMentorFollowersById(long id);
	 
	// public Mentor findMentorByUsername(String userName, String userType);
	 
	 public String generateUserName(String string);

	Mentor forgotPassword(String username) throws Exception;

	String verifyOtp(String otp, Long user, String userType,String emailId,String phoneNo) throws Exception;

	String changePassword(String password, Long userId) throws NotFoundException;

	void updateProfileImage(Mentor mentor);

	Mentor updatedescMentor(String desc, Long userId) throws NotFoundException;

	String sendOtp(String emailId, Long userId, String userType) throws Exception;
	
	public String sendMobileOtp(String phoneNo, Long userId, String userType) throws Exception;


}
