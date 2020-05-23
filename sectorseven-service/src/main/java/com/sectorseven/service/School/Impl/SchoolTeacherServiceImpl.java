package com.sectorseven.service.School.Impl;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sectorseven.model.Enum.AdminRole;
import com.sectorseven.model.Enum.SequenceModule;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.admin.AdministratorAuthority;
import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.admin.IdSequence;
import com.sectorseven.model.common.OtpToken;
import com.sectorseven.model.common.SMS;
import com.sectorseven.model.school.School;
import com.sectorseven.model.school.SchoolAdministratorAuthority;
import com.sectorseven.model.school.SchoolTeacher;
import com.sectorseven.model.school.SchoolUsers;
import com.sectorseven.model.util.CommonMethods;
import com.sectorseven.model.util.EmailTemplateReader;
import com.sectorseven.model.util.MailEngine;
import com.sectorseven.model.util.SMSProcessor;
import com.sectorseven.model.util.UserDefinedException;
import com.sectorseven.repository.School.SchoolAdministratorAuthorityRepository;
import com.sectorseven.repository.School.SchoolRepository;
import com.sectorseven.repository.School.SchoolTeacherRepository;
import com.sectorseven.repository.School.SchoolUserRepository;
import com.sectorseven.repository.admin.AuthorityRepository;
import com.sectorseven.repository.admin.IdSequenceRepository;
import com.sectorseven.repository.common.OtpRepository;
import com.sectorseven.req.UpdateRequest;
import com.sectorseven.service.School.SchoolTeacherService;
import com.sectorseven.service.admin.AdministratorAuthorityService;
import com.sectorseven.service.admin.AuthorityService;

import javassist.NotFoundException;

@Service
@Transactional
public class SchoolTeacherServiceImpl implements SchoolTeacherService {

	private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	 private static SecureRandom random = new SecureRandom();
	
	@Autowired
	private SchoolTeacherRepository teacherRepository;
	
	@Autowired
	private SchoolUserRepository schoolUserRepository;
	
	@Autowired
	private SchoolTeacherRepository schoolTeacherRepository;
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	@Autowired
	private SchoolAdministratorAuthorityRepository schoolAuthorityRepository;
	
	@Autowired
	private SMSProcessor smsProcessor;
	
	
	@Autowired
	private AdministratorAuthorityService administratorAuthorityService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private OtpRepository otpRepository;
	
	@Autowired
	private IdSequenceRepository sequenceRepository;
	
	@Override
	public List<SchoolTeacher> findByEmailOrContactNo(String email, Long contactNo) {
		return teacherRepository.findByEmailOrMobile(email, contactNo);
	}

	@Override
	public List<SchoolTeacher> findByEmailOrContactNoAndIdNotIn(String email, Long contactNo, Long id) {
		return teacherRepository.findByEmailOrMobileAndIdNotIn(email, contactNo, id);
	}
	private void saveAuthorities(Set<AdminRole> roles, SchoolUsers schoolUsers,SchoolTeacher schoolTeacher) throws Exception {
        for (AdminRole role : roles) {
        	AdministratorAuthority administratorAuthority = new AdministratorAuthority();
            Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(role.name());
           // administratorAuthority.setAdministrator(administrator);
            administratorAuthority.setUserName(schoolUsers.getUserName());
            administratorAuthority.setDecryptPassword(schoolUsers.getPassword());
            administratorAuthority.setAuthority(authority);
            administratorAuthority.setUserType(role.name());
            administratorAuthority.setStatus(Status.Active);
            administratorAuthority.setUser(schoolTeacher.getId());
            administratorAuthorityService.saveAdministratorAuthority(administratorAuthority);
        }
    }
	@Override
	public SchoolUsers save(SchoolTeacher schoolTeacher, boolean excelOrNot)  throws Exception{
		School school = schoolRepository.findById(schoolTeacher.getSchool().getId());
		schoolTeacher.setSchool(school);
		teacherRepository.save(schoolTeacher);
		
		SchoolUsers schoolUsers = new SchoolUsers();
		schoolUsers.setFirstName(schoolTeacher.getFirstName());
		schoolUsers.setLastName(schoolTeacher.getLastName());
		schoolUsers.setEmail(schoolTeacher.getEmail());
		String userName = generateUserName(schoolTeacher.getExpertise());
		schoolUsers.setUserName(userName);
		String password = generatePassword(8);
		schoolUsers.setDecryptPassword(password);
		schoolUsers.setPassword(passwordEncoder.encode(password));
		schoolUsers.setSchool(schoolTeacher.getSchool());
		schoolUsers.setStatus(Status.Active);
		schoolUsers.setPhone(schoolTeacher.getMobile());
		schoolUsers.setAuthority(AdminRole.ROLE_SCHOOL_TEACHER);
		Set<AdminRole> roles = new HashSet<AdminRole>();
		roles.add(AdminRole.ROLE_SCHOOL_TEACHER);
		saveAuthorities(roles, schoolUsers,schoolTeacher);
		schoolUserRepository.save(schoolUsers);
		
		Authority authority = authorityRepository.findAdministratorsAuthorityByAuthorityCode(AdminRole.ROLE_SCHOOL_TEACHER.name());
		SchoolAdministratorAuthority administratorAuthority = new SchoolAdministratorAuthority();
		administratorAuthority.setAuthority(authority);
		administratorAuthority.setSchoolUser(schoolUsers);
		administratorAuthority.setStatus(Status.Active);
		schoolAuthorityRepository.save(administratorAuthority);
		
		if(!excelOrNot) {
			// Send mail credentials to school:
			EmailTemplateReader reader = new EmailTemplateReader();
			try {
			String content = 	reader.readUserEmailTemplate("ROLE_SCHOOL_TEACHER", schoolUsers.getUserName(), schoolUsers.getDecryptPassword());
				MailEngine.sendMail(content, "Welcome to Sector Seven", "balaji.siddi@gmail.com");
			} catch (IOException e) { 
				e.printStackTrace();
				
			}	
		}
		else {
			
		}
		
		return schoolUsers;
	}
	public String generateUserName(String expertize) {
		IdSequence sequence = sequenceRepository.findByModule(SequenceModule.TEACHER_MODULE);
		if (null == sequence) {
			sequence = new IdSequence();
			sequence.setModule(SequenceModule.TEACHER_MODULE);
			sequence.setSequenceNo(0);
		}
			long sequenceId = sequence.getSequenceNo() + 1;
			String str = "0000";
	        str = str.substring(String.valueOf(sequenceId).length(), str.length());
	        String mentorID =  String.valueOf(expertize).substring(2)+ str + sequenceId;
	        sequence.setModule(SequenceModule.TEACHER_MODULE);
	        sequence.setSequenceNo(sequenceId);
	        sequenceRepository.save(sequence);
			 
        return mentorID;
	}
	/* (non-Javadoc)
	 *	This method returns digits alphanumerical password 
	 */
		public static String generatePassword(int len) {
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
	public void update(SchoolTeacher schoolTeacher) {
		teacherRepository.save(schoolTeacher);
	}
	
	/*public static void main(String[] args) {
		String n = generatePassword(6);
		System.out.println(n + " "+ passwordEncoder.encode(n));
	}*/

	@Override
	public SchoolTeacher findAdministratorByUsername(String username) {
		SchoolUsers schoolUser=schoolUserRepository.findByUserName(username);
		SchoolTeacher schoolTeacher= teacherRepository.findByEmail(schoolUser.getEmail());
		schoolTeacher.setUserName(schoolUser.getUserName());
		return schoolTeacher;
	}
	
	@Override
	public SchoolUsers findSchoolUserByUsername(String username) throws Exception {
		SchoolUsers schoolUsers=schoolUserRepository.findByUserName(username);
		if(schoolUsers==null){
			throw new UserDefinedException("UserName Not Found");
		}
		return schoolUsers;
	}

	@Override
	public List<SchoolTeacher> findByStatus(Status active) {
		return teacherRepository.findByStatus(active);
	}

	@Override
	public SchoolTeacher findById(Long id) throws Exception {
		SchoolTeacher st=teacherRepository.findById(id);
		if(st==null){
			throw new UserDefinedException("User Not Found");
		}
		SchoolUsers schoolUserss=schoolUserRepository.findSchoolUserByEmail(st.getEmail());
		st.setUserName(schoolUserss.getUserName());
		return st;
	}
	
	@Override
	public void updateSchoolTeacher(SchoolTeacher existSchoolTeacher) {
		teacherRepository.save(existSchoolTeacher);
	}


	@Override
	public List<SchoolTeacher> findByStatusAndSchool(Status active, School school) {
		return teacherRepository.findByStatusAndSchool(active,school);
	}

	@Override
	public SchoolTeacher updateTeacher(UpdateRequest schoolTeacherr, Long userId)  throws Exception{
		SchoolTeacher schoolTeacher=teacherRepository.findOne(userId);
		SchoolUsers schoolUsers=schoolUserRepository.findSchoolUserByEmail(schoolTeacher.getEmail());
		if(schoolTeacherr.getFirstName()!=null){
			schoolTeacher.setFirstName(schoolTeacherr.getFirstName());
			schoolUsers.setFirstName(schoolTeacherr.getFirstName());
		}
		 if(schoolTeacherr.getLastName()!=null){
			 schoolTeacher.setLastName(schoolTeacherr.getLastName());
			 schoolUsers.setLastName(schoolTeacherr.getLastName());
		}
		 if(schoolTeacherr.getEmail()!=null){
			 schoolTeacher.setEmail(schoolTeacherr.getEmail());
			 schoolUsers.setEmail(schoolTeacherr.getEmail());
		}
		 if(schoolTeacherr.getMobile()!=0){
			 schoolTeacher.setMobile(schoolTeacherr.getMobile());
			 schoolUsers.setPhone(schoolTeacherr.getMobile());
		}
		 if(schoolTeacherr.getDescription()!=null){
			 schoolTeacher.setDescription(schoolTeacherr.getDescription());
			}
		if(schoolTeacherr==null || schoolUsers==null){
			throw new UserDefinedException("User Not Found");
		}
		 schoolUserRepository.save(schoolUsers);
		return teacherRepository.save(schoolTeacher);
	}

	@Override
	public SchoolTeacher forgotPassword(String username) throws Exception {
		SchoolUsers schoolUsers=schoolUserRepository.findByUserName(username);
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(schoolUsers.getAuthority().name());
		SchoolTeacher schoolTeacher=schoolTeacherRepository.findByEmail(schoolUsers.getEmail());
		if(schoolUsers!=null){
			saveOtp(schoolTeacher, authority);
		}
		else{
			throw new UserDefinedException("UserName Not In Our Records Please Enter Correct UserName");
		}
		return schoolTeacher;
	}	
	public void saveOtp(SchoolTeacher schoolUsers,Authority authority){
		OtpToken otpToken=otpRepository.findByUserAndAuthorityAndStatus(schoolUsers.getId(),authority,Status.Active);
		 if(otpToken!=null){
			 otpToken.setStatus(Status.Inactive);
			 otpRepository.save(otpToken);
		 }
		
		 final String uuid = UUID.randomUUID().toString().substring(0, 6);
	        MailEngine.sendMail(uuid,"ForgotPassword",schoolUsers.getEmail());
	        OtpToken otpTokenn=new OtpToken();
	        otpTokenn.setDateCreated(new Date());
	        otpTokenn.setOtp(uuid);
	        otpTokenn.setStatus(Status.Active);
	        otpTokenn.setUser(schoolUsers.getId());
	        otpTokenn.setAuthority(authority);
	        otpRepository.save(otpTokenn);
	    
	}

	@Override
	public String verifyOtp(String otp, Long userId, String userType,String emailId,String mobileNo) throws Exception {
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
		String msg;
        OtpToken otpToken=otpRepository.findByUserAndAuthorityAndStatus(userId,authority,Status.Active);
        SchoolTeacher scTeacher=schoolTeacherRepository.findOne(userId);
        if(otpToken!=null){
           if(otpToken.getOtp().equals(otp)){
        	   otpToken.setStatus(Status.Inactive);
        	   otpRepository.save(otpToken);
        	   if(!(emailId.isEmpty())){
        			SchoolUsers schoolUser=schoolUserRepository.findSchoolUserByEmail(scTeacher.getEmail());
        			schoolUser.setEmail(emailId);
        		    scTeacher.setEmail(emailId);
        		    schoolTeacherRepository.save(scTeacher);
        		    schoolUserRepository.save(schoolUser);
        	   }
        	   else if((!mobileNo.isEmpty())){
        		   long mob = Long.parseLong(mobileNo);
        		   scTeacher.setMobile(mob);
        		   schoolTeacherRepository.save(scTeacher);
        	   }
                msg="verfied Successfully";
                return msg;
            }
            
          throw new UserDefinedException("Incorrect Otp")  ;
        }
        throw new UserDefinedException("OTP Expired Please send Once again");
	}

	@Override
	public String changePassword(String password,  Long userId) throws NotFoundException {
		SchoolTeacher schoolTeacher = schoolTeacherRepository.findOne(userId);
		SchoolUsers schoolUser=schoolUserRepository.findSchoolUserByEmail(schoolTeacher.getEmail());
		AdministratorAuthority adminAuthority=administratorAuthorityService.findAdministratorAuthorityByUserName(schoolUser.getUserName());
		if (schoolUser!=null){
			adminAuthority.setDecryptPassword(CommonMethods.decodePassword(password));
			administratorAuthorityService.saveAdministratorAuthority(adminAuthority);
			schoolUser.setPassword(CommonMethods.decodePassword(password));
			schoolUser.setDecryptPassword(password);
			schoolUserRepository.save(schoolUser);
	        return "Password Changed Successfully";
		}
		else{
			throw new NotFoundException("Your Name Not in Our Records");

		}
	}

	@Override
	public void updateProfileImage(SchoolTeacher schoolTeacher) {
       schoolTeacherRepository.save(schoolTeacher);		
	}

	@Override
	public SchoolTeacher updatedescTeacher(String desc, Long userId) throws NotFoundException {
		SchoolTeacher teacher=schoolTeacherRepository.findOne(userId);
		if(teacher!=null){
			teacher.setDescription(desc);
			teacher=schoolTeacherRepository.save(teacher);
		}
		else{
			throw new NotFoundException("This User Not Found");
		}
		return teacher;
		
	}
	@Override
	public String sendOtp(String emailId, Long userId, String userType) throws Exception {
		SchoolTeacher schoolTeacher=schoolTeacherRepository.findOne(userId);
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
		if(schoolTeacher!=null){
			saveEmailOtp(schoolTeacher, authority,emailId);
		}
		else{
			throw new UserDefinedException("UserName Not In Our Records Please Enter Correct UserName");
		}
		return "Sent Otp To your Registered Email";
		
	}
    
	public void saveEmailOtp(SchoolTeacher schoolteacher,Authority authority,String emailId){
		OtpToken otpToken=otpRepository.findByUserAndAuthorityAndStatus(schoolteacher.getId(),authority,Status.Active);
		 if(otpToken!=null){
			 otpToken.setStatus(Status.Inactive);
			 otpRepository.save(otpToken);
		 }
		
		 final String uuid = UUID.randomUUID().toString().substring(0, 6);
	        MailEngine.sendMail(uuid,"Change Email",emailId);
	        OtpToken otpTokenn=new OtpToken();
	        otpTokenn.setDateCreated(new Date());
	        otpTokenn.setOtp(uuid);
	        otpTokenn.setStatus(Status.Active);
	        otpTokenn.setUser(schoolteacher.getId());
	        otpTokenn.setAuthority(authority);
	        otpRepository.save(otpTokenn);
	    
	}
	@Override
	public String sendMobileOtp(String phoneNo, Long userId, String userType) throws Exception {
		SchoolTeacher schoolTeacher=schoolTeacherRepository.findOne(userId);
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
		if(schoolTeacher!=null){
			saveMobileOtp(schoolTeacher, authority,phoneNo);
		}
		else{
			throw new UserDefinedException("UserName Not In Our Records Please Enter Correct UserName");
		}
	 	return "Sent Otp To your Mobile Number";
	}

	public void saveMobileOtp(SchoolTeacher schoolTeacher,Authority authority,String phoneNo) throws Exception{
		OtpToken otpToken=otpRepository.findByUserAndAuthorityAndStatus(schoolTeacher.getId(),authority,Status.Active);
		 if(otpToken!=null){
			 otpToken.setStatus(Status.Inactive);
			 otpRepository.save(otpToken);
		 }
		    String numbers = "0123456789";
	        Random randome = new Random();
	        String otpStr = "";

	        for (int i = 0, len = numbers.length(); i < 6; i++) {
	            otpStr += numbers.charAt(randome.nextInt(len));
	        }
			String smsMessage = "E-nnovation never calls you asking for OTP. Please use this OTP " + otpStr;
	        SMS sms = new SMS();
	        sms.setsToPhoneNo(phoneNo);
	        sms.setsMessage(smsMessage);
	       // WorkerQueues.SMS_QUEUE.put(sms);
	        smsProcessor.sendSMS(sms);
		
	        OtpToken otpTokenn=new OtpToken();
	        otpTokenn.setDateCreated(new Date());
	        otpTokenn.setOtp(otpStr);
	        otpTokenn.setStatus(Status.Active);
	        otpTokenn.setUser(schoolTeacher.getId());
	        otpTokenn.setAuthority(authority);
	        otpRepository.save(otpTokenn);
	    
	}
}
