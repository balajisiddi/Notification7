package com.sectorseven.service.School.Impl;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sectorseven.model.Enum.AdminRole;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.school.School;
import com.sectorseven.model.school.SchoolAdministratorAuthority;
import com.sectorseven.model.school.SchoolUsers;
import com.sectorseven.model.util.EmailTemplateReader;
import com.sectorseven.model.util.MailEngine;
import com.sectorseven.repository.School.SchoolAdministratorAuthorityRepository;
import com.sectorseven.repository.School.SchoolRepository;
import com.sectorseven.repository.School.SchoolUserRepository;
import com.sectorseven.repository.admin.AuthorityRepository;
import com.sectorseven.service.School.SchoolService;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService{

	private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Autowired
	private SchoolRepository repository;
	
	@Autowired
	private SchoolUserRepository schoolUserRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private SchoolAdministratorAuthorityRepository schoolAuthorityRepository;

	@Override
	public School findById(long id) {
		return repository.findOne(id);
	}

	@Override
	public List<School> findByStatus(Status active) {
		return repository.findByStatus(active);
	}

	@Override
	public void save(School school) {
		repository.save(school);
		//School school2 = findById(school.getId());
		
		SchoolUsers schoolUsers = new SchoolUsers();
		schoolUsers.setFirstName(school.getSchoolName());
		schoolUsers.setEmail(school.getEmail());
		schoolUsers.setUserName(school.getEmail());
		String password = generatePassword(8);
		schoolUsers.setDecryptPassword(password);
		schoolUsers.setPassword(passwordEncoder.encode(password));
		schoolUsers.setSchool(school);
		schoolUsers.setStatus(Status.Active);
		schoolUsers.setPhone(school.getContactNo());
		schoolUsers.setAuthority(AdminRole.ROLE_SCHOOL_ADMIN);
		schoolUserRepository.save(schoolUsers);
		
		
		Authority authority = authorityRepository.findAdministratorsAuthorityByAuthorityCode(AdminRole.ROLE_SCHOOL_ADMIN.name());
		
		SchoolAdministratorAuthority administratorAuthority = new SchoolAdministratorAuthority();
		administratorAuthority.setAuthority(authority);
		administratorAuthority.setSchoolUser(schoolUsers);
		administratorAuthority.setStatus(Status.Active);
		schoolAuthorityRepository.save(administratorAuthority);
		
		// Send mail credentials to school:
		
		EmailTemplateReader reader = new EmailTemplateReader();
		try {
		String content = 	reader.readUserEmailTemplate(school.getSchoolName(), schoolUsers.getUserName(), password);
			MailEngine.sendMail(content, "Welcome to Sector Seven", schoolUsers.getEmail());
		} catch (IOException e) { 
			e.printStackTrace();
			
		}
	}

	@Override
	public School findByEmailAndStatus(String email, Status active) {
		return repository.findByEmailAndStatus(email, active);
	}

	@Override
	public List<School> findByEmailOrContactNoAndStatus(String email, long contactNo, Status active) {
		return repository.findByEmailOrContactNoAndStatus(email, contactNo, active);
	}
	
	
	 private static SecureRandom random = new SecureRandom();
	
	/* (non-Javadoc)
	 *	This method returns digits alphanumerical password 
	 */
	 @Override
		public String generatePassword(int len) {
	    	String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        String ALPHA = "abcdefghijklmnopqrstuvwxyz";
	        String NUMERIC = "0123456789";
	       // String SPECIAL_CHARS = "!@#$%^&*_=+-/";
	       
	        String result = "";
	        String dic =  ALPHA_CAPS + ALPHA +NUMERIC;
	        for (int i = 0; i < len; i++) {
	            int index = random.nextInt(dic.length());
	            result += dic.charAt(index);
	        }
	        return result;
	        }

	@Override
	public List<School> findByEmailOrContactNoOrSchoolCode(String email, Long contactNo, String schoolCode) {
		return repository.findByEmailOrContactNoOrSchoolCode(email, contactNo, schoolCode);
	}

	@Override
	public List<School> findAll() {
		return repository.findAll();
	}

	@Override
	public void update(School school) {
		repository.save(school);
	}

	@Override
	public List<School> findByEmailOrContactNoOrSchoolCodeAndIdNotIn(String email, Long contactNo, String schoolCode,
			Long id) {
		return repository.findByEmailOrContactNoOrSchoolCodeAndIdNotIn(email, contactNo, schoolCode, id);
	}
	
	
}
