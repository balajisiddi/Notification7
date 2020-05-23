package com.sectorseven.service.admin.impl;

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
import com.sectorseven.model.admin.Parents;
import com.sectorseven.model.common.OtpToken;
import com.sectorseven.model.common.SMS;
import com.sectorseven.model.school.Student;
import com.sectorseven.model.util.CommonMethods;
import com.sectorseven.model.util.MailEngine;
import com.sectorseven.model.util.SMSProcessor;
import com.sectorseven.model.util.UserDefinedException;
import com.sectorseven.repository.admin.IdSequenceRepository;
import com.sectorseven.repository.admin.ParentsRepository;
import com.sectorseven.repository.common.OtpRepository;
import com.sectorseven.repository.student.StudentRepository;
import com.sectorseven.service.admin.AdministratorAuthorityService;
import com.sectorseven.service.admin.AuthorityService;
import com.sectorseven.service.admin.ParentsService;

import javassist.NotFoundException;

@Transactional
@Service
public class ParentsServiceImpl implements ParentsService {

	@Autowired
	private ParentsRepository parentsRepository;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
	private AdministratorAuthorityService administratorAuthorityService;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private IdSequenceRepository sequenceRepository;

	@Autowired
	private OtpRepository otpRepository;

	@Autowired
	private SMSProcessor smsProcessor;
	
	
	@Override
	public List<Parents> findByFatherMobileOrFatherEmail(Long fatherMobile, String fatherEmail) {
		return parentsRepository.findByFatherMobileOrFatherEmail(fatherMobile, fatherEmail);
	}
	
	@Override
	public List<Parents> findByEmailOrContactNoAndIdNotIn(String fatherEmail, Long fatherMobile, Long id) {
		return parentsRepository.findByFatherEmailOrFatherMobileAndIdNotIn(fatherEmail, fatherMobile, id);
	}

	@Override
	public Parents saveParentData(Parents parents) throws Exception{
		String password = CommonMethods.generatePassword(8);
		parents.setUserName(generateUserName(parents.getFatherName(), parents.getMotherName()));
		parents.setPassword(CommonMethods.decodePassword(password));
		parents.setDecryptPassword(password);
		parents.setAuthority(AdminRole.ROLE_PARENT.name());
		parents.setStatus(Status.Active);
		Parents parent=parentsRepository.save(parents);
		Set<AdminRole> roles = new HashSet<AdminRole>();
		roles.add(AdminRole.ROLE_PARENT);
		saveAuthorities(roles, parent);
		return parents;
	}
	@Override
	public Parents update(Parents parent) {
		return parentsRepository.save(parent);
	}
	private void saveAuthorities(Set<AdminRole> roles,Parents parent) throws Exception{
        for (AdminRole role : roles) {
        	AdministratorAuthority administratorAuthority = new AdministratorAuthority();
            Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(role.name());
           // administratorAuthority.setAdministrator(administrator);
            administratorAuthority.setUserName(parent.getUserName());
            administratorAuthority.setDecryptPassword(parent.getPassword());
            administratorAuthority.setAuthority(authority);
            administratorAuthority.setUserType(role.name());
            administratorAuthority.setUser(parent.getId());
            administratorAuthority.setStatus(Status.Active);
            administratorAuthorityService.saveAdministratorAuthority(administratorAuthority);
        }
    }

	@Override
	public Parents findAdministratorByUsername(String username) throws Exception{
		return parentsRepository.findByUserName(username);
	}

	@Override
	public List<Student> getChildsByParent(Parents parents) {
		return studentRepository.findByParents(parents);
	}
	@Override
	public String generateUserName(String fatherName,String motherName) {
		IdSequence sequence = sequenceRepository.findByModule(SequenceModule.MENTOR_MODULE);
		if (null == sequence) {
			sequence = new IdSequence();
			sequence.setModule(SequenceModule.MENTOR_MODULE);
			sequence.setSequenceNo(0);
		}
			long sequenceId = sequence.getSequenceNo() + 1;
			String str = "0000";
	        str = str.substring(String.valueOf(sequenceId).length(), str.length());
	        String parentId =  String.valueOf(fatherName).substring(1,3)+String.valueOf(motherName).substring(1,3)+ str + sequenceId;
	        sequence.setModule(SequenceModule.MENTOR_MODULE);
	        sequence.setSequenceNo(sequenceId);
	        sequenceRepository.save(sequence);
			 
        return parentId;
	}

	@Override
	public Parents updateParents(Parents parent, long parentId) throws Exception {
		Parents parents=parentsRepository.findOne(parentId);
		if(parent.getFatherEmail()!=null){
			parents.setFatherEmail(parent.getFatherEmail());
		}
		 if(parent.getFatherOccupation()!=null){
			parents.setFatherOccupation(parent.getFatherOccupation());
		}
		 if(parent.getFatherName()!=null){
			 parents.setFatherName(parent.getFatherName());
		}
		 if(parent.getFatherMobile()!=null){
			 parents.setFatherMobile(parent.getFatherMobile());
		}
		 if(parent.getMotherEmail()!=null){
				parents.setMotherEmail(parent.getMotherEmail());
			}
			 if(parent.getMotherOccupation()!=null){
				parents.setMotherOccupation(parent.getMotherOccupation());
			}
			 if(parent.getMotherName()!=null){
				 parents.setMotherName(parent.getMotherName());
			}
			 if(parent.getMotherMobile()!=null){
				 parents.setMotherMobile(parent.getMotherMobile());
			}
			 if(parent.getDescription()!=null){
				 parents.setDescription(parent.getDescription());
				}
			 if(parents==null){
				 throw new UserDefinedException("User Not Found");
			 }
		return parentsRepository.save(parents);
	
	}
	@Override
	public Parents forgotPassword(String username) throws Exception {
		Parents parents=parentsRepository.findByUserName(username);
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(parents.getAuthority());
		if(parents!=null){
			saveOtp(parents, authority);
		}
		else if(parents==null){
			throw new UserDefinedException("UserName Not In Our Records Please Enter Correct UserName");
		}
		return parents;
	}
	public void saveOtp(Parents mentor,Authority authority){
		OtpToken otpToken=otpRepository.findByUserAndAuthorityAndStatus(mentor.getId(),authority,Status.Active);
		 if(otpToken!=null){
			 otpToken.setStatus(Status.Inactive);
			 otpRepository.save(otpToken);
		 }
		
		 final String uuid = UUID.randomUUID().toString().substring(0, 6);
	        MailEngine.sendMail(uuid,"ForgotPassword",mentor.getFatherEmail());
	        OtpToken otpTokenn=new OtpToken();
	        otpTokenn.setDateCreated(new Date());
	        otpTokenn.setOtp(uuid);
	        otpTokenn.setStatus(Status.Active);
	        otpTokenn.setUser(mentor.getId());
	        otpTokenn.setAuthority(authority);
	        otpRepository.save(otpTokenn);
	    
	}

	@Override
	public String verifyOtp(String otp, Long user, String userType,String emailId,String mobileNo,Integer parent) throws Exception {
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
		String msg;
        OtpToken otpToken=otpRepository.findByUserAndAuthorityAndStatus(user,authority,Status.Active);
        Parents parents=parentsRepository.findOne(user);
        if(otpToken!=null){
           if(otpToken.getOtp().equals(otp)){
        	   otpToken.setStatus(Status.Inactive);
        	   otpRepository.save(otpToken);
        	   if(!(emailId.isEmpty())){
        		   if(parent==0){
            		   parents.setFatherEmail(emailId);
        		   }
        		   else if(parent==1){
        			   parents.setMotherEmail(emailId);
        		   }
        		   parentsRepository.save(parents);
        	   }
        	   else if((!mobileNo.isEmpty())){
        		   long mob = Long.parseLong(mobileNo);
        		   if(parent==0){
            		   parents.setFatherMobile(mob);
        		   }
        		   else if(parent==1){
        			   parents.setMotherMobile(mob);
        		   }
        		   parentsRepository.save(parents);
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
		Parents parents = parentsRepository.findOne(userId);
		AdministratorAuthority adminAuthority=administratorAuthorityService.findAdministratorAuthorityByUserName(parents.getUserName());
		if (parents!=null){
			adminAuthority.setDecryptPassword(CommonMethods.decodePassword(password));
			administratorAuthorityService.saveAdministratorAuthority(adminAuthority);
			parents.setPassword(CommonMethods.decodePassword(password));
			parents.setDecryptPassword(password);
			parents.setId(userId);
			parentsRepository.save(parents);
	        return "Password Changed Successfully";
		}
		else{
			throw new NotFoundException("Your Name Not in Our Records");

		}

	}

	@Override
	public Parents findParentById(Long parentId) throws Exception {
		Parents parents= parentsRepository.findOne(parentId);
		if(parents==null){
			throw new UserDefinedException("User Not Found");
		}
		return parents;
	}

	@Override
	public void updateProfileImage(Parents parents) {
		parentsRepository.save(parents);
	}

	

	@Override
	public Parents updatedescParents(String desc, Long userId) throws NotFoundException {
		Parents parents=parentsRepository.findOne(userId);
		if(parents!=null){
			parents.setDescription(desc);
			parents=parentsRepository.save(parents);
		}
		else{
			throw new NotFoundException("This User Not Found");
		}
		return parents;
			}
	@Override
	public String sendOtp(String emailId, Long userId, String userType) throws Exception {
		Parents parents=parentsRepository.findOne(userId);
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
		if(parents!=null){
			saveEmailOtp(parents, authority,emailId);
		}
		else{
			throw new UserDefinedException("UserName Not In Our Records Please Enter Correct UserName");
		}
		return "Sent Otp To your Registered Email";
		
	}
    
	public void saveEmailOtp(Parents parents,Authority authority,String emailId){
		OtpToken otpToken=otpRepository.findByUserAndAuthorityAndStatus(parents.getId(),authority,Status.Active);
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
	        otpTokenn.setUser(parents.getId());
	        otpTokenn.setAuthority(authority);
	        otpRepository.save(otpTokenn);
	    
	}
	@Override
	public String sendMobileOtp(String phoneNo, Long userId, String userType) throws Exception {
		Parents parents=parentsRepository.findOne(userId);
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
		if(parents!=null){
			saveMobileOtp(parents, authority,phoneNo);
		}
		else{
			throw new UserDefinedException("UserName Not In Our Records Please Enter Correct UserName");
		}
	 	return "Sent Otp To your Mobile Number";
	}

	public void saveMobileOtp(Parents parents,Authority authority,String phoneNo) throws Exception{
		OtpToken otpToken=otpRepository.findByUserAndAuthorityAndStatus(parents.getId(),authority,Status.Active);
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
	        otpTokenn.setUser(parents.getId());
	        otpTokenn.setAuthority(authority);
	        otpRepository.save(otpTokenn);
	    
	}
}
