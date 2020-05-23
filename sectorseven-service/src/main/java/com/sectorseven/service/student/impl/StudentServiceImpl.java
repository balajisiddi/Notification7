package com.sectorseven.service.student.impl;

import java.io.IOException;
import java.util.Calendar;
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
import com.sectorseven.model.Enum.Hub;
import com.sectorseven.model.Enum.SequenceModule;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.admin.AcademicYear;
import com.sectorseven.model.admin.AdministratorAuthority;
import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.admin.IdSequence;
import com.sectorseven.model.admin.Mentor;
import com.sectorseven.model.admin.Parents;
import com.sectorseven.model.common.CareerCategory;
import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.CoCreationHub;
import com.sectorseven.model.common.Contribution;
import com.sectorseven.model.common.ContributionDocs;
import com.sectorseven.model.common.ExamPattern;
import com.sectorseven.model.common.Institutions;
import com.sectorseven.model.common.OtpToken;
import com.sectorseven.model.common.Responsibilities;
import com.sectorseven.model.common.SMS;
import com.sectorseven.model.common.SubCategoryDetails;
import com.sectorseven.model.common.TimeSpent;
import com.sectorseven.model.school.School;
import com.sectorseven.model.school.SchoolTeacher;
import com.sectorseven.model.school.Student;
import com.sectorseven.model.school.UserInterests;
import com.sectorseven.model.util.CommonMethods;
import com.sectorseven.model.util.EmailTemplateReader;
import com.sectorseven.model.util.MailEngine;
import com.sectorseven.model.util.SMSProcessor;
import com.sectorseven.model.util.UserDefinedException;
import com.sectorseven.repository.School.SchoolTeacherRepository;
import com.sectorseven.repository.admin.AcademicYearRepository;
import com.sectorseven.repository.admin.IdSequenceRepository;
import com.sectorseven.repository.admin.MentorRepository;
import com.sectorseven.repository.common.CareerCategoryRepository;
import com.sectorseven.repository.common.CareerSubCategoryRepository;
import com.sectorseven.repository.common.CoCreationHubRepository;
import com.sectorseven.repository.common.ContributionDocsRepository;
import com.sectorseven.repository.common.ContributionRepository;
import com.sectorseven.repository.common.ExamPatternRepository;
import com.sectorseven.repository.common.InstitutionRepository;
import com.sectorseven.repository.common.OtpRepository;
import com.sectorseven.repository.common.RolesAndResponseRepository;
import com.sectorseven.repository.common.SubCategoryDetailsRepository;
import com.sectorseven.repository.common.TimeSpentRepository;
import com.sectorseven.repository.common.UserInterestsRepository;
import com.sectorseven.repository.student.StudentRepository;
import com.sectorseven.req.UpdateRequest;
import com.sectorseven.service.School.SchoolService;
import com.sectorseven.service.admin.AdministratorAuthorityService;
import com.sectorseven.service.admin.AuthorityService;
import com.sectorseven.service.admin.ParentsService;
import com.sectorseven.service.student.StudentService;

import javassist.NotFoundException;

/**
 * @author RameshNaidu
 *
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	//private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	 
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private ParentsService parentsService;

	@Autowired
	private AcademicYearRepository ayRepository;

	@Autowired
	private IdSequenceRepository sequenceRepository;

	@Autowired
	private SchoolService schoolService;

	@Autowired
	private ContributionRepository contributionRepository;

	@Autowired
	private CareerSubCategoryRepository careerSubCateRepo;

	@Autowired
	private CareerCategoryRepository careerCategoryRepo;

	@Autowired
	private SubCategoryDetailsRepository subCategoryDetailsRepo;

	@Autowired
	private CoCreationHubRepository coCreationHubRepo;

	@Autowired
	private TimeSpentRepository timeSpentRepo;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private AdministratorAuthorityService administratorAuthorityService;
	
	@Autowired
	private MentorRepository mentorRepository;

	@Autowired
	private SchoolTeacherRepository schoolTeacherRepository;
	
	@Autowired
	private ContributionDocsRepository contDocRepo;
	
	@Autowired
	private RolesAndResponseRepository roleRepository;

	@Autowired
	private InstitutionRepository institutionRepository;
	
	@Autowired
	private ExamPatternRepository examPatternRepository;
	
	@Autowired
	private UserInterestsRepository userInterestRepository;


	@Autowired
	private OtpRepository otpRepository;
	
	@Autowired
	private AcademicYearRepository acedemicYearRepository;
	
	@Autowired
	private ContributionDocsRepository contributionDocsRepository;

	@Autowired
	private SMSProcessor smsProcessor;
	   

	
	@Override
	public Student findAdministratorByUsername(String username) throws Exception {
		Student student=  studentRepository.findByUserName(username);
		if(student.getAcedemicYear()!=null){
			student.setAcademicYear(student.getAcedemicYear().getName());
		}
		return student;
		
	}
	
	@Override
	public void save(Student student ) throws Exception{
		studentRepository.save(student);
		Set<AdminRole> roles = new HashSet<AdminRole>();
		roles.add(AdminRole.ROLE_STUDENT);
		saveAuthorities(roles,student);
	}

	@Override
	public void save(List<Student> excelStudents) {
		studentRepository.save(excelStudents);
	}

	@Override
	public Student findByMobile(Long mbl) {
		return studentRepository.findByMobile(mbl);
	}

	@Override
	public Student findByEmail(String emailID) {
		return studentRepository.findByEmail(emailID);
	}

	@Override
	public String generateUserName(String schoolCode) {
		AcademicYear academicYear = ayRepository.findByStatus(Status.Active);
		Calendar cal = Calendar.getInstance();
		cal.setTime(academicYear.getStartingDate());
		int year = cal.get(Calendar.YEAR);
		IdSequence sequence = sequenceRepository.findByModuleAndSchoolCodeAndAcademicYear(SequenceModule.STUDENT_MODULE, schoolCode, academicYear);
		if (null == sequence) {
			sequence = new IdSequence();
			sequence.setModule(SequenceModule.STUDENT_MODULE);
			sequence.setSchoolCode(schoolCode);
			sequence.setAcademicYear(academicYear);
			sequence.setSequenceNo(0);
		}
			long sequenceId = sequence.getSequenceNo() + 1;
			String str = "000000";
	        str = str.substring(String.valueOf(sequenceId).length(), str.length());
	        String studentID =   schoolCode + String.valueOf(year).substring(2) + str + sequenceId;
	        sequence.setModule(SequenceModule.STUDENT_MODULE);
			sequence.setSchoolCode(schoolCode);
			sequence.setAcademicYear(academicYear);
	        sequence.setSequenceNo(sequenceId);
	        sequenceRepository.save(sequence);
			 
        return studentID;
	}

	@Override
	public Student saveStudentAndParent(Student student, boolean excelornot) throws Exception{
		School school = schoolService.findById(student.getSchool().getId());
		SchoolTeacher schoolTeacher = schoolTeacherRepository.findById(student.getSchoolTeacher().getId());
		String userName = generateUserName(school.getSchoolCode());
		String password = CommonMethods.generatePassword(8);
		Parents parents = parentsService.saveParentData(student.getParents());
		student.setUserName(userName);
		student.setDecryptPassword(password);
		student.setSchoolTeacher(schoolTeacher);
	 	student.setPassword(CommonMethods.decodePassword(password));
	 	student.setAuthority(AdminRole.ROLE_STUDENT.name());
	 	student.setSchool(school);
	 	student.setParents(parents);
	 	Student rStudent=studentRepository.save(student);
		Set<AdminRole> roles = new HashSet<AdminRole>();
		roles.add(AdminRole.ROLE_STUDENT);
		saveAuthorities(roles,rStudent);
		if(!excelornot) {
			EmailTemplateReader reader = new EmailTemplateReader();
			try {
			String content = 	reader.readUserEmailTemplate(rStudent.getAuthority() , rStudent.getUserName(), rStudent.getDecryptPassword());
				MailEngine.sendMail(content, "Welcome to Sector Seven", "balaji.siddi@gmail.com");
				String parentContent = 	reader.readUserEmailTemplate(parents.getAuthority(), parents.getUserName(), parents.getDecryptPassword());
				MailEngine.sendMail(parentContent, "Welcome to Sector Seven", "balaji.siddi@gmail.com");
			
			} catch (IOException e) { 
				e.printStackTrace();
			}
		}
		return rStudent;
		}
	@Override
	public Student updateStudent(UpdateRequest student,Long userId) throws Exception {
		Student stud=studentRepository.findOne(userId);
		if(student.getFirstName()!=null){
			stud.setFirstName(student.getFirstName());
		}
		 if(student.getLastName()!=null){
			stud.setLastName(student.getLastName());
		}
		 if(student.getEmail()!=null){
			stud.setEmail(student.getEmail());
		}
		 if(student.getMobile()!=0){
			stud.setMobile(student.getMobile());
		}
		if(student.getDescription()!=null){
				stud.setDescription(student.getDescription());
		}
		else{
			throw new UserDefinedException("User Not Found");
		}
		return studentRepository.save(stud);
	}
	

	@Override
	public void saveStudent(Student student, boolean excelornot) throws Exception {
		School school = schoolService.findById(student.getSchool().getId());
		SchoolTeacher schoolTeacher = schoolTeacherRepository.findById(student.getSchoolTeacher().getId());
		String userName = generateUserName(school.getSchoolCode());
		String password = CommonMethods.generatePassword(8);
		student.setUserName(userName);
		student.setDecryptPassword(password);
	 	student.setPassword(CommonMethods.decodePassword(password));
	 	student.setAuthority(AdminRole.ROLE_STUDENT.name());
	 	student.setSchool(school);
	 	student.setSchoolTeacher(schoolTeacher);
	 	Student rStudent=studentRepository.save(student);
	 	Set<AdminRole> roles = new HashSet<AdminRole>();
		roles.add(AdminRole.ROLE_STUDENT);
		saveAuthorities(roles,rStudent);
		EmailTemplateReader reader = new EmailTemplateReader();
		try {
			String content = 	reader.readUserEmailTemplate(rStudent.getAuthority() , rStudent.getUserName(), rStudent.getDecryptPassword());
				MailEngine.sendMail(content, "Welcome to Sector Seven", "balaji.siddi@gmail.com");
			} catch (IOException e) { 
				e.printStackTrace();
			}
	}

	@Override
	public Student findByStudentId(Long id) throws Exception{
		Student student= studentRepository.findOne(id);
		if(student==null){
			throw new UserDefinedException("User Not Found");
		}
		return student;
	}

	@Override
	public Contribution saveContribution(Contribution contribution) throws Exception {
		contribution.setStatus(Status.Active);
		return contributionRepository.save(contribution);
	}

	@Override
    public List<CareerCategory> findAllCategories() {
        return careerCategoryRepo.findAll();
    }
    @Override
    public List<CareerSubcategory> findCareerSubcategories(long subcat) {
        CareerCategory subcatgrs = careerCategoryRepo.findOne(subcat);
        return careerSubCateRepo.findByCareerCategory(subcatgrs);
    }
    @Override
    public List<SubCategoryDetails> findCareerSubCategoryDetails(long subcatid,String type,Integer limit,Integer offset) {
        CareerSubcategory subCatDetails=careerSubCateRepo.findOne(subcatid);
        return subCategoryDetailsRepo.findByCareerSubcatAndType(subCatDetails.getId(),type,limit,offset);
    }
    @Override
    public List<CoCreationHub> findAllCoCreationHubs() {
        return coCreationHubRepo.findAllByHub(Hub.Yes);
    }

	public void loginTime(Student student) {
		TimeSpent timeSpents=new TimeSpent();
		timeSpents.setLoginTime(new Date());
		timeSpents.setStudent(student);
		timeSpentRepo.save(timeSpents);
	}

	@Override
	public void logoutTime(TimeSpent logouttime) {
		Date datetime= new Date();			
		logouttime.setLogoutTime(new Date(datetime.getTime()));
		timeSpentRepo.save(logouttime);
		}
	private void saveAuthorities(Set<AdminRole> roles,Student student) throws Exception{
        for (AdminRole role : roles) {
        	AdministratorAuthority administratorAuthority = new AdministratorAuthority();
            Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(role.name());
           // administratorAuthority.setAdministrator(administrator);
            administratorAuthority.setUserName(student.getUserName());
            administratorAuthority.setDecryptPassword(student.getPassword());
            administratorAuthority.setAuthority(authority);
            administratorAuthority.setUserType(role.name());
            administratorAuthority.setUser(student.getId());
            administratorAuthority.setStatus(Status.Active);
            administratorAuthorityService.saveAdministratorAuthority(administratorAuthority);
        }
    }

/*	@Override
	public Student findStudentByUsername(String userName,String userType) {
		Student student=studentRepository.findByUserName(userName);
		SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd");
   	    SimpleDateFormat pattern = new SimpleDateFormat("dd MMM yyyy");
   	 try {
   		 Date convertedDate =(Date) formatter.parse(student.getDateOfBirth());
   		 String evDate=pattern.format(convertedDate);
   		student.setDateOfBirth(evDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
			return student;
		
	}*/
	
	@Override
	public Mentor findMentorByUsername(String userName,String userType) {
			return mentorRepository.findByUserName(userName);
		
	}
	
	@Override
	public List<Student> findStudentBySchoolTeacher(SchoolTeacher schoolTeacher) {
		return studentRepository.findBySchoolTeacher(schoolTeacher);
	}

	@Override
	public CoCreationHub findAllCoCreationHubById(long hubId) {
		return coCreationHubRepo.findOne(hubId);
	}

	@Override
	public ContributionDocs saveContributionDocs(ContributionDocs contDocs,Long contributionId) throws Exception{
		Contribution contribution=contributionRepository.findOne(contributionId);
		contDocs.setContribution(contribution);
		return contDocRepo.save(contDocs);
	}

	@Override
	public Contribution findAllContributionByAuthorityAndUser(Authority authority, Long userId) {
		return contributionRepository.findAllContributionByAuthorityAndUser(authority,userId);
	}

	@Override
	public List<SubCategoryDetails> findCareerSubCategoryDetailsForMain(long subCatId) {
		CareerSubcategory careerSubCat=careerSubCateRepo.findOne(subCatId);
		return subCategoryDetailsRepo.findByCareerSubcat(careerSubCat);
	}

	@Override
	public List<Responsibilities> findCareerSubCategoryDetailsForRoles(long subCatId) {
		CareerSubcategory careerSubCat=careerSubCateRepo.findOne(subCatId);
		return roleRepository.findByCareerSubCat(careerSubCat);
	}

	@Override
	public List<Institutions> findCareerSubCategoryDetailsForInstitutions(long subCatId) {
		CareerSubcategory careerSubCat=careerSubCateRepo.findOne(subCatId);
		return institutionRepository.findByCareerSubCat(careerSubCat);
	}

	@Override
	public List<ExamPattern> findCareerSubCategoryDetailsForMainExamPatterns(long subCatId) {
		CareerSubcategory careerSubCat=careerSubCateRepo.findOne(subCatId);
		return examPatternRepository.findByCareerSubCat(careerSubCat);
	}

	/*@Override
	public CareerSubcategory findCareerSubCategory(long subCatId) {
		return careerSubCateRepo.findOne(subCatId);
	}*/


	@Override
	public List<UserInterests> findInterestsByUserIdAndAuthority(Long userId, String userType) throws Exception {
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
		return userInterestRepository.findInterestsByUserAndAuthority(userId,authority);
	}

	@Override
	public Student update(Student student,long studentId) {
		return studentRepository.save(student);
	}

	@Override
	public CareerCategory findCategoryById(long catId) throws Exception {
		CareerCategory category= careerCategoryRepo.findOne(catId);
		if(category==null){
			throw new UserDefinedException("Category Not Found");
		}
		return category;
	}

	@Override
	public CareerSubcategory findCareerSubCategoryById(long subCatId) {
		return careerSubCateRepo.findOne(subCatId);
	}

	@Override
	public SubCategoryDetails findSubCatDetailById(long subCatDetailId) {
		return subCategoryDetailsRepo.findOne(subCatDetailId);
	}

	@Override
	public Student forgotPassword(String username) throws Exception {
		Student student=studentRepository.findByUserName(username);
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(student.getAuthority());
		if(student!=null){
			saveOtp(student, authority);
		}
		else{
			throw new UserDefinedException("UserName Not In Our Records Please Enter Correct UserName");
		}
		return student;
			
	}
	public void saveOtp(Student student,Authority authority){
		OtpToken otpToken=otpRepository.findByUserAndAuthorityAndStatus(student.getId(),authority,Status.Active);
		 if(otpToken!=null){
			 otpToken.setStatus(Status.Inactive);
			 otpRepository.save(otpToken);
		 }
		
		 final String uuid = UUID.randomUUID().toString().substring(0, 6);
	        MailEngine.sendMail(uuid,"ForgotPassword",student.getEmail());
	        OtpToken otpTokenn=new OtpToken();
	        otpTokenn.setDateCreated(new Date());
	        otpTokenn.setOtp(uuid);
	        otpTokenn.setStatus(Status.Active);
	        otpTokenn.setUser(student.getId());
	        otpTokenn.setAuthority(authority);
	        otpRepository.save(otpTokenn);
	    
	}
	@Override
	public String verifyOtp(String otp, Long user,String type,String emailId,String mobileNo) throws Exception {
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(type);
		String msg;
        OtpToken otpToken=otpRepository.findByUserAndAuthorityAndStatus(user,authority,Status.Active);
		   Student student=studentRepository.findOne(user);
        if(otpToken!=null){
           if(otpToken.getOtp().equals(otp)){
        	   otpToken.setStatus(Status.Inactive);
        	   otpRepository.save(otpToken);
        	   if(!(emailId.isEmpty())){
        		   student.setEmail(emailId);
        		   studentRepository.save(student);
        	   }
        	   else if((!mobileNo.isEmpty())){
        		   long mob = Long.parseLong(mobileNo);
        		   student.setMobile(mob);
        		   studentRepository.save(student);
        	   }
               msg="verfied Successfully";
               return msg;
            }
            
          throw new UserDefinedException("Incorrect Otp")  ;
        }
        throw new UserDefinedException("OTP Expired Please send Once again");
    	
	}
	@Override
	public String changePassword(String password, Long userId) throws Exception {
		Student student = studentRepository.findOne(userId);
		AdministratorAuthority adminAuthority=administratorAuthorityService.findAdministratorAuthorityByUserName(student.getUserName());
		if (student!=null){
			adminAuthority.setDecryptPassword(CommonMethods.decodePassword(password));
			administratorAuthorityService.saveAdministratorAuthority(adminAuthority);
			student.setPassword(CommonMethods.decodePassword(password));
			student.setDecryptPassword(password);
			student.setId(userId);
	        studentRepository.save(student);
	        return "Password Changed Successfully";
		}
		else{
			throw new NotFoundException("Your Name Not in Our Records");

		}
	}

	@Override
	public List<Student> findStudentsByParent(Parents parent) throws Exception {
		return studentRepository.findByParents(parent);
	}

	@Override
	public List<Student> getAllStudentsBySchoolTeacher(SchoolTeacher schoolTeacher,Integer limit,Integer offset) {
		return studentRepository.findsBySchoolTeacher(schoolTeacher.getId(),limit,offset);
	}

	@Override
	public AcademicYear findYearByAcademicYearId(Long id) {
		return acedemicYearRepository.findOne(id);
	}

	@Override
	public ContributionDocs findContributionById(long contributionId) {
		return contributionDocsRepository.findOne(contributionId);
	}

	@Override
	public void updateProfileImage(Student student) {
 	studentRepository.save(student);	
	}

	@Override
	public Student updatedescStudent(String desc, Long userId) throws Exception{
		Student student=studentRepository.findOne(userId);
		if(student!=null){
			student.setDescription(desc);
			student=studentRepository.save(student);
		}
		else{
			throw new NotFoundException("Student Not found");
		}
		return student;
	}

	@Override
	public String sendOtp(String emailId, Long userId, String userType) throws Exception {
		Student student=studentRepository.findOne(userId);
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
		if(student!=null){
			saveEmailOtp(student, authority,emailId);
		}
		else{
			throw new UserDefinedException("UserName Not In Our Records Please Enter Correct UserName");
		}
		return "Sent Otp To your Registered Email";
		
	}
    
	public void saveEmailOtp(Student student,Authority authority,String emailId){
		OtpToken otpToken=otpRepository.findByUserAndAuthorityAndStatus(student.getId(),authority,Status.Active);
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
	        otpTokenn.setUser(student.getId());
	        otpTokenn.setAuthority(authority);
	        otpRepository.save(otpTokenn);
	    
	}

	@Override
	public String sendMobileOtp(String phoneNo, Long userId, String userType) throws Exception {
		Student student=studentRepository.findOne(userId);
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
		if(student!=null){
			saveMobileOtp(student, authority,phoneNo);
		}
		else{
			throw new UserDefinedException("UserName Not In Our Records Please Enter Correct UserName");
		}
	 	return "Sent Otp To your Mobile Number";
	}

	public void saveMobileOtp(Student student,Authority authority,String phoneNo) throws Exception{
		OtpToken otpToken=otpRepository.findByUserAndAuthorityAndStatus(student.getId(),authority,Status.Active);
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
	        otpTokenn.setUser(student.getId());
	        otpTokenn.setAuthority(authority);
	        otpRepository.save(otpTokenn);
	    
	}
	

	/*@Override
	public List<Contribution> findAllTalksByAutority(String authorityCode) {
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(authorityCode);
		return contributionRepository.findContributionByAcceptanceAndAuthority(AdminAcceptance.Accepted,authority);
	}*/

}
