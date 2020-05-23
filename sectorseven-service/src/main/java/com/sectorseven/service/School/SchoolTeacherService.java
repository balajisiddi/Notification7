package com.sectorseven.service.School;

import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.school.School;
import com.sectorseven.model.school.SchoolTeacher;
import com.sectorseven.model.school.SchoolUsers;
import com.sectorseven.model.util.UserDefinedException;
import com.sectorseven.req.UpdateRequest;

import javassist.NotFoundException;

public interface SchoolTeacherService {

	List<SchoolTeacher> findByEmailOrContactNo(String email, Long contactNo);

	List<SchoolTeacher> findByEmailOrContactNoAndIdNotIn(String email, Long contactNo, Long id);

	SchoolUsers save(SchoolTeacher schoolTeacher, boolean excelOrNot) throws Exception;

	void update(SchoolTeacher schoolTeacher);

	SchoolTeacher findAdministratorByUsername(String username) throws Exception;
	
	SchoolUsers findSchoolUserByUsername(String username) throws Exception;

	List<SchoolTeacher> findByStatus(Status active);

	SchoolTeacher findById(Long id) throws Exception;
	
	void updateSchoolTeacher(SchoolTeacher existSchoolTeacher);

	List<SchoolTeacher> findByStatusAndSchool(Status active, School school);

	SchoolTeacher updateTeacher(UpdateRequest user, Long userId) throws Exception;

	SchoolTeacher forgotPassword(String username) throws Exception;

	String verifyOtp(String otp, Long userId, String userType,String emailId,String mobileNo) throws Exception;

	String changePassword(String password,  Long userId) throws Exception;

	void updateProfileImage(SchoolTeacher schoolTeacher);

	SchoolTeacher updatedescTeacher(String desc, Long userId) throws Exception;

	String sendOtp(String emailId, Long userId, String userType) throws Exception;
 
	public String sendMobileOtp(String phoneNo, Long userId, String userType) throws Exception;

}
