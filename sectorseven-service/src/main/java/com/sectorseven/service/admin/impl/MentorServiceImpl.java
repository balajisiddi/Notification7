package com.sectorseven.service.admin.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sectorseven.model.Enum.AdminRole;
import com.sectorseven.model.Enum.SequenceModule;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.admin.AdministratorAuthority;
import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.admin.IdSequence;
import com.sectorseven.model.admin.Mentor;
import com.sectorseven.model.common.CareerCategory;
import com.sectorseven.model.common.MentorFollowers;
import com.sectorseven.model.common.OtpToken;
import com.sectorseven.model.common.SMS;
import com.sectorseven.model.school.Student;
import com.sectorseven.model.util.CommonMethods;
import com.sectorseven.model.util.EmailTemplateReader;
import com.sectorseven.model.util.MailEngine;
import com.sectorseven.model.util.SMSProcessor;
import com.sectorseven.model.util.UserDefinedException;
import com.sectorseven.repository.admin.IdSequenceRepository;
import com.sectorseven.repository.admin.MentorFollowersRepository;
import com.sectorseven.repository.admin.MentorRepository;
import com.sectorseven.repository.common.CareerCategoryRepository;
import com.sectorseven.repository.common.OtpRepository;
import com.sectorseven.req.UpdateRequest;
import com.sectorseven.service.admin.AdministratorAuthorityService;
import com.sectorseven.service.admin.AuthorityService;
import com.sectorseven.service.admin.MentorService;

import javassist.NotFoundException;

@Transactional
@Service
public class MentorServiceImpl implements MentorService{

	@Autowired
	private MentorRepository mentorRepository;
	
	@Autowired
	private AdministratorAuthorityService administratorAuthorityService;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private MentorFollowersRepository mentorFollowersRepo;
	
	@Autowired
	private IdSequenceRepository sequenceRepository;

	@Autowired
	private OtpRepository otpRepository;
	
	@Autowired
	private SMSProcessor smsProcessor;
	
	@Autowired
	private CareerCategoryRepository careerCategoryRepository;
	


	@Override
	public List<Mentor> findByEmailOrContactNo(String email, Long mobile) {
		return mentorRepository.findByEmailOrMobile(email, mobile);
	}

	@Override
	public List<Mentor> findByEmailOrContactNoAndIdNotIn(String email, Long mobile, Long id) {
		return mentorRepository.findByEmailOrMobileAndIdNotIn(email, mobile, id);
	}

	@Override
	public Mentor save(Mentor mentor, boolean excelornot) throws Exception{
		Mentor mento=null;
		if(mentor.getOccupationCategory()!=null) {
			CareerCategory careercat=careerCategoryRepository.findOne(mentor.getOccupationCategory().getId());
			mentor.setStatus(Status.Active);
			String password = CommonMethods.generatePassword(8);
			mentor.setPassword(CommonMethods.decodePassword(password));
			mentor.setDecryptPassword(password);
			mentor.setAuthority(AdminRole.ROLE_MENTOR.name());
			String userName = generateUserName(mentor.getExpertise());
			mentor.setUserName(userName);
			mentor.setExpertise(careercat.getCategoryName());
			mentor.setOccupationCategory(careercat);
			mento=mentorRepository.save(mentor);
			Set<AdminRole> roles = new HashSet<AdminRole>();
			roles.add(AdminRole.ROLE_MENTOR);
			saveAuthorities(roles, mentor);
			if(!excelornot) {
				EmailTemplateReader reader = new EmailTemplateReader();
				try {
				String content = reader.readUserEmailTemplate(mentor.getAuthority(), mentor.getUserName(), mentor.getDecryptPassword());
					MailEngine.sendMail(content, "Welcome to Sector Seven", "balaji.siddi@gmail.com");
				} catch (IOException e) { 
					e.printStackTrace();
				}
			}
		}
		return mento;
	}

	@Override
	public void update(Mentor mentor) {
		mentorRepository.save(mentor);
	}
	@Override
	public Mentor findById(long menotrId) throws Exception{
		Mentor mentor = mentorRepository.findOne(menotrId);
		if(mentor==null){
			throw new UserDefinedException("User Not Found");
		}
		return mentor;
	}
	
	@Override
	public List<Mentor> findByStatus(Status active) {
		return mentorRepository.findByStatus(active);
	}
	
	   
	
	private void saveAuthorities(Set<AdminRole> roles, Mentor mentor)  throws Exception{
        for (AdminRole role : roles) {
        	AdministratorAuthority administratorAuthority = new AdministratorAuthority();
            Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(role.name());
           // administratorAuthority.setAdministrator(administrator);
            administratorAuthority.setUserName(mentor.getUserName());
            administratorAuthority.setDecryptPassword(mentor.getPassword());
            administratorAuthority.setAuthority(authority);
            administratorAuthority.setUserType(role.name());
            administratorAuthority.setUser(mentor.getId());
            administratorAuthority.setStatus(Status.Active);
            administratorAuthorityService.saveAdministratorAuthority(administratorAuthority);
        }
    }  
	@Override
	public Mentor findAdministratorByUsername(String username) throws Exception{
		Mentor mentor=mentorRepository.findByUserName(username);
	   	// try {
		//SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
   	    //SimpleDateFormat pattern = new SimpleDateFormat("dd MMM yyyy");
   		 //Date convertedDate =(Date) formatter.parse(mentor.getDate_of_birth());
   		 //String evDate=pattern.format(convertedDate);
   		 mentor.setDate_of_birth(mentor.getDate_of_birth());
		/*} catch (ParseException e) {
			e.printStackTrace();
		}*/
		return mentor;
	}
	
	@Override
	public Mentor findMentorById(long menotrId) {
		Mentor mentor = mentorRepository.findOne(menotrId);
		return mentor;
	}
	@Override
	public Mentor updateMentor(UpdateRequest existMentor,long mentorId) throws Exception {
		Mentor mentor=mentorRepository.findOne(mentorId);
		if(existMentor.getFirstName()!=null){
			mentor.setFirstName(existMentor.getFirstName());
		}
		 if(existMentor.getLastName()!=null){
			 mentor.setLastName(existMentor.getLastName());
		}
		 if(existMentor.getEmail()!=null){
			 mentor.setEmail(existMentor.getEmail());
		}
		 if(existMentor.getMobile()!=0){
			 mentor.setMobile(existMentor.getMobile());
		}
		 if(existMentor.getDescription()!=null){
			 mentor.setDescription(existMentor.getDescription());
		}
		 else{
			 throw new UserDefinedException("User Not Found");
		 }
		return mentorRepository.save(mentor);
	}
	@Override
	public MentorFollowers findallMentorFollowersById(long id) {
		return mentorFollowersRepo.findOne(id);
	}	
  /*  
	@Override
    public Mentor findMentorByUsername(String userName, String userType) {
        return mentorRepository.findByUserName(userName);
        }*/
	@Override
	public String generateUserName(String expertize) {
		IdSequence sequence = sequenceRepository.findByModule(SequenceModule.MENTOR_MODULE);
		if (null == sequence) {
			sequence = new IdSequence();
			sequence.setModule(SequenceModule.MENTOR_MODULE);
			sequence.setSequenceNo(0);
		}
			long sequenceId = sequence.getSequenceNo() + 1;
			String str = "0000";
	        str = str.substring(String.valueOf(sequenceId).length(), str.length());
	        String mentorID =  String.valueOf(expertize).substring(2)+ str + sequenceId;
	        sequence.setModule(SequenceModule.MENTOR_MODULE);
	        sequence.setSequenceNo(sequenceId);
	        sequenceRepository.save(sequence);
			 
        return mentorID;
	}

	@Override
	public Mentor forgotPassword(String username) throws Exception {
		Mentor mentor=mentorRepository.findByUserName(username);
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(mentor.getAuthority());
		if(mentor!=null){
			saveOtp(mentor, authority);
		}
		else{
			throw new UserDefinedException("UserName Not In Our Records Please Enter Correct UserName");
		}
		return mentor;
	}
	public void saveOtp(Mentor mentor,Authority authority){
		OtpToken otpToken=otpRepository.findByUserAndAuthorityAndStatus(mentor.getId(),authority,Status.Active);
		 if(otpToken!=null){
			 otpToken.setStatus(Status.Inactive);
			 otpRepository.save(otpToken);
		 }
		
		 final String uuid = UUID.randomUUID().toString().substring(0, 6);
	        MailEngine.sendMail(uuid,"ForgotPassword",mentor.getEmail());
	        OtpToken otpTokenn=new OtpToken();
	        otpTokenn.setDateCreated(new Date());
	        otpTokenn.setOtp(uuid);
	        otpTokenn.setStatus(Status.Active);
	        otpTokenn.setUser(mentor.getId());
	        otpTokenn.setAuthority(authority);
	        otpRepository.save(otpTokenn);
	    
	}

	@Override
	public String verifyOtp(String otp, Long user, String userType,String emailId,String mobileNo) throws Exception {
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
		String msg;
        OtpToken otpToken=otpRepository.findByUserAndAuthorityAndStatus(user,authority,Status.Active);
        Mentor mentor=mentorRepository.findOne(user);
        if(otpToken!=null){
           if(otpToken.getOtp().equals(otp)){
        	   otpToken.setStatus(Status.Inactive);
        	   otpRepository.save(otpToken);
        	   if(!(emailId.isEmpty())){
        		   mentor.setEmail(emailId);
        		   mentorRepository.save(mentor);
        	   }
        	   else if((!mobileNo.isEmpty())){
        		   long mob = Long.parseLong(mobileNo);
        		   mentor.setMobile(mob);
        		   mentorRepository.save(mentor);
        	   }
                msg="verfied Successfully";
                return msg;
            }
            
          throw new UserDefinedException("Incorrect Otp");
        }
        throw new UserDefinedException("OTP Expired Please send Once again");		
	}

	@Override
	public String changePassword(String password, Long userId) throws NotFoundException {
		Mentor mentor = mentorRepository.findOne(userId);
		AdministratorAuthority adminAuthority=administratorAuthorityService.findAdministratorAuthorityByUserName(mentor.getUserName());
		if (mentor!=null){
			adminAuthority.setDecryptPassword(CommonMethods.decodePassword(password));
			administratorAuthorityService.saveAdministratorAuthority(adminAuthority);
			mentor.setPassword(CommonMethods.decodePassword(password));
			mentor.setDecryptPassword(password);
			mentor.setId(userId);
			mentorRepository.save(mentor);
	        return "Password Changed Successfully";
		}
		else{
			throw new NotFoundException("Your Name Not in Our Records");

		}

	}

	@Override
	public void updateProfileImage(Mentor mentor) {
	mentorRepository.save(mentor);
		
	}

	@Override
	public Mentor updatedescMentor(String desc, Long userId) throws NotFoundException {
		Mentor mentor=mentorRepository.findOne(userId);
		if(mentor!=null){
			mentor.setDescription(desc);
			mentor=mentorRepository.save(mentor);
		}
		else{
			throw new NotFoundException("Mentor Not found");
		}
		return mentor;
	}
	@Override
	public String sendOtp(String emailId, Long userId, String userType) throws Exception {
		Mentor mentor=mentorRepository.findOne(userId);
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
		if(mentor!=null){
			saveEmailOtp(mentor, authority,emailId);
		}
		else{
			throw new UserDefinedException("UserName Not In Our Records Please Enter Correct UserName");
		}
		return "Sent Otp To your Registered Email";
		
	}
    
	public void saveEmailOtp(Mentor mentor,Authority authority,String emailId){
		OtpToken otpToken=otpRepository.findByUserAndAuthorityAndStatus(mentor.getId(),authority,Status.Active);
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
	        otpTokenn.setUser(mentor.getId());
	        otpTokenn.setAuthority(authority);
	        otpRepository.save(otpTokenn);
	    
	}
	@Override
	public String sendMobileOtp(String phoneNo, Long userId, String userType) throws Exception {
		Mentor mentor=mentorRepository.findOne(userId);
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
		if(mentor!=null){
			saveMobileOtp(mentor, authority,phoneNo);
		}
		else{
			throw new UserDefinedException("UserName Not In Our Records Please Enter Correct UserName");
		}
	 	return "Sent Otp To your Mobile Number";
	}

	public void saveMobileOtp(Mentor mentor,Authority authority,String phoneNo) throws Exception{
		OtpToken otpToken=otpRepository.findByUserAndAuthorityAndStatus(mentor.getId(),authority,Status.Active);
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
	        otpTokenn.setUser(mentor.getId());
	        otpTokenn.setAuthority(authority);
	        otpRepository.save(otpTokenn);
	    
	}
}
