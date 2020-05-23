package com.sectorseven.service.common;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sectorseven.model.Enum.AdminAcceptance;
import com.sectorseven.model.Enum.Country;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.Enum.Subscribe;
import com.sectorseven.model.Enum.Trending;
import com.sectorseven.model.admin.AcademicYear;
import com.sectorseven.model.admin.AdministratorAuthority;
import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.admin.Mentor;
import com.sectorseven.model.admin.Parents;
import com.sectorseven.model.common.Abilities;
import com.sectorseven.model.common.ActivityLog;
import com.sectorseven.model.common.AddEvent;
import com.sectorseven.model.common.Apk;
import com.sectorseven.model.common.CareerCategory;
import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.CoCreationHub;
import com.sectorseven.model.common.CommonImages;
import com.sectorseven.model.common.Contribution;
import com.sectorseven.model.common.ContributionDocs;
import com.sectorseven.model.common.DemandColors;
import com.sectorseven.model.common.DemandLabels;
import com.sectorseven.model.common.DemandSupply;
import com.sectorseven.model.common.DemandYears;
import com.sectorseven.model.common.Feedback;
import com.sectorseven.model.common.InstituteSubcat;
import com.sectorseven.model.common.InstitutionCourses;
import com.sectorseven.model.common.Institutions;
import com.sectorseven.model.common.MentorFollowers;
import com.sectorseven.model.common.Notification;
import com.sectorseven.model.common.Recommended;
import com.sectorseven.model.common.Responsibilities;
import com.sectorseven.model.common.Scholorships;
import com.sectorseven.model.common.ServerBroke;
import com.sectorseven.model.common.SevenSigma;
import com.sectorseven.model.common.SevenSigmaDetails;
import com.sectorseven.model.common.Skills;
import com.sectorseven.model.common.StudentQueries;
import com.sectorseven.model.common.SubCategoryDetails;
import com.sectorseven.model.common.SubscribedCareers;
import com.sectorseven.model.common.SuccessPersons;
import com.sectorseven.model.school.SchoolTeacher;
import com.sectorseven.model.school.Student;
import com.sectorseven.model.school.UserInterests;
import com.sectorseven.model.util.UserDefinedException;
import com.sectorseven.repository.School.SchoolRepository;
import com.sectorseven.repository.School.SchoolTeacherRepository;
import com.sectorseven.repository.admin.AcademicYearRepository;
import com.sectorseven.repository.admin.AdministratorAuthorityRepository;
import com.sectorseven.repository.admin.AuthorityRepository;
import com.sectorseven.repository.admin.DemandSupplyRepository;
import com.sectorseven.repository.admin.MentorFollowersRepository;
import com.sectorseven.repository.admin.MentorRepository;
import com.sectorseven.repository.admin.ParentsRepository;
import com.sectorseven.repository.common.AbilityRepository;
import com.sectorseven.repository.common.ActivityLogRepository;
import com.sectorseven.repository.common.AddEventRepository;
import com.sectorseven.repository.common.ApkRepository;
import com.sectorseven.repository.common.AskMentorRepository;
import com.sectorseven.repository.common.CareerCategoryRepository;
import com.sectorseven.repository.common.CareerSubCategoryRepository;
import com.sectorseven.repository.common.CoCreationHubRepository;
import com.sectorseven.repository.common.CommonImagesRepository;
import com.sectorseven.repository.common.ContributionDocsRepository;
import com.sectorseven.repository.common.ContributionRepository;
import com.sectorseven.repository.common.CoursesRepositoty;
import com.sectorseven.repository.common.DemandColorsRepository;
import com.sectorseven.repository.common.DemandLabelsRepository;
import com.sectorseven.repository.common.DemandYearsRepository;
import com.sectorseven.repository.common.FeedbackRepository;
import com.sectorseven.repository.common.InstituteSubcatRepository;
import com.sectorseven.repository.common.InstitutionRepository;
import com.sectorseven.repository.common.NotificatioRepository;
import com.sectorseven.repository.common.RecommendedRepository;
import com.sectorseven.repository.common.RolesAndResponseRepository;
import com.sectorseven.repository.common.ScholorshipsRepository;
import com.sectorseven.repository.common.ServerBrokeRepository;
import com.sectorseven.repository.common.SevenSigmaDetailsRepository;
import com.sectorseven.repository.common.SevenSigmaRepository;
import com.sectorseven.repository.common.SkillRepository;
import com.sectorseven.repository.common.StudentInterestRepository;
import com.sectorseven.repository.common.SubCategoryDetailsRepository;
import com.sectorseven.repository.common.SubscribeRepository;
import com.sectorseven.repository.common.SuccessfullPersonsRepository;
import com.sectorseven.repository.student.StudentRepository;
import com.sectorseven.req.InterestReq;
import com.sectorseven.resp.CategoryResponse;
import com.sectorseven.resp.InitialInterests;
import com.sectorseven.service.student.CommonService;


@Transactional
@Service
public class CommonServiceImpl implements CommonService {
	
	
	@Autowired
	private SevenSigmaRepository sigmaRepository;
	
	@Autowired
	private SubCategoryDetailsRepository subCategoryDetailsRepository;
	
	@Autowired
	private AddEventRepository addEventRepository;
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	@Autowired
	private SevenSigmaDetailsRepository sigmaDetailsRepo;
	
	@Autowired
	private MentorRepository mentorRepository;

	@Autowired
	private ScholorshipsRepository scholorShipRepo;

	
	@Autowired
	private CareerSubCategoryRepository careerSubCategory;

	
	@Autowired
	private SubCategoryDetailsRepository subCatRepository;

	@Autowired
	private SkillRepository skillRepository;

	@Autowired
	private AbilityRepository abilityRepository;
	
	@Autowired
	private RolesAndResponseRepository roleRepository;

	@Autowired
	private DemandSupplyRepository demandSupplyRepository;

	
	@Autowired
	private AskMentorRepository askMentorRepo;
	
	@Autowired
	private AddEventRepository addEventRepo;

	@Autowired
	private MentorFollowersRepository mentorFollowersRepo;
	
	@Autowired
	private InstitutionRepository institutionRepository;

	
	@Autowired
	private AdministratorAuthorityRepository adminAuthority;
	
	@Autowired
	private ContributionRepository contributionRepository;
	
	@Autowired
	private ContributionDocsRepository contributionDocsRepository;
	
	
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	
	@Autowired
	private MentorFollowersRepository mentorFollowersRepository;
	
	@Autowired
	private StudentInterestRepository userInterestsRepository;
	
	@Autowired
	private CoCreationHubRepository coCreationHubRepository;
	
	/*@Autowired
	private InterestRepository interestRepository;*/
	
	@Autowired
	private AskMentorRepository askMentorRepository;
	
	@Autowired
	private AcademicYearRepository academicYearRepository;
	
	@Autowired
	private SchoolTeacherRepository schoolTeacherRepository;
	
	@Autowired
	private ParentsRepository parentsRepository;
	
	@Autowired
	private SubCategoryDetailsRepository subCatDetailsRepo;
	
	@Autowired
	private SubscribeRepository subscribeRepository;
	
	@Autowired
	private ActivityLogRepository activityRepository;

	
	@Autowired
	private SuccessfullPersonsRepository successPersonlityRepo;
	
	@Autowired
	private CareerSubCategoryRepository careerSubCatRepo;
	
	@Autowired
	private CareerCategoryRepository careerCategoryRepository;
	
	@Autowired
	private NotificatioRepository notificationRepository;
	
	@Autowired
	private RecommendedRepository recommRepository;
	
	@Autowired
	private ApkRepository apkRepository;

	@Autowired
	private DemandYearsRepository demandYearsRepository;
	
	@Autowired
	private DemandLabelsRepository demandLabelsRepository;
	
	
	@Autowired
	private DemandColorsRepository demandColorsRepository;
	
	@Autowired
	private ServerBrokeRepository serverBrokeRepository;
	
	@Autowired
	private CoursesRepositoty coursesRepositoty;
	
	@Autowired
	private CommonImagesRepository commonImagesRepository;
	
	@Autowired
	private InstituteSubcatRepository instituteSubcatRepository;
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	
	@Override
	public List<SevenSigma> getSigmas() throws Exception{
		List<SevenSigma> sigmas = sigmaRepository.findAll();
		if(sigmas==null){
			throw new UserDefinedException("Sigmas Not Found");
		}
		/*return sigmas.stream()
			     .map(sigma-> {sigma.setSigmaIconPath(ViewConstants.SAVE_LOCATION+'/'+sigma.getSigmaIconPath()+'/'+sigma.getSigmaIconName());
			     return sigma;})
			     .collect(Collectors.toList());*/
		return sigmas;
	}
	
	@Override
	public List<SevenSigmaDetails> getSigmaDetails(Long sigmaId,String type,Integer limit,Integer offset) {
		SevenSigma sigma=sigmaRepository.findOne(sigmaId);
		List<SevenSigmaDetails> sigmadetails= sigmaDetailsRepo.findBySevenSigmaAndType(sigma.getId(),type,limit,offset);
		return sigmadetails;
		}
	
	@Override
	public SubCategoryDetails findBySubCategoryId(long subCatId) {
		return subCatRepository.findOne(subCatId);
	}
	

	@Override
	public List<Mentor> getAllMentorsByInterst(Long userId,String authority,Integer limit,Integer offset) throws Exception{
	//public List<Mentor> getAllMentorsByInterst() {
		Authority authoriti=authorityRepository.findAdministratorsAuthorityByAuthorityCode(authority);
		List<UserInterests> interests1=userInterestsRepository.findByUserAndAuthority(userId,authoriti);
		//List<Interests> interests=new ArrayList<>();
		List<CareerCategory> interests=new ArrayList<>();
		List<Long> intere=new ArrayList<Long>();
		for(UserInterests inter : interests1){
			interests.add(inter.getInterests());
			for(CareerCategory ints :interests){
				//intere.add(ints.getCategoryName());
				intere.add(ints.getId());
			}
		}
		//Authority authorit=authorityRepository.findAdministratorsAuthorityByAuthorityCode(AdminRole.ROLE_MENTOR.name());
		//List<Interests> interests2=studentInterestRepository.findByUserAndAuthority(userId,authorit);
		/*Set<Long> ids = interests2.stream()
		        .map(Interests::getId)
		        .collect(Collectors.toSet());
		List<Interests> parentBooks = interests1.stream()
		        .filter(book -> !ids.contains(book.getId()))
		        .collect(Collectors.toList());	
		*/
		return mentorRepository.findAllByInterests(intere,limit,offset);
	}

	@Override
	public Mentor getMentorDetails(long mentorId) throws Exception{
		Mentor mentor= mentorRepository.findOne(mentorId);
		if(mentor==null){
			throw new UserDefinedException("User Not Found");
		}
		return mentor;
	}

	@Override
	public List<CareerSubcategory> getAllTrendingCareers() {
		//List<CareerSubcategory> matches = list.stream().filter(it -> it.contains("bea")).collect(Collectors.toList());
		return careerSubCategory.findByTrending(Trending.IsTrending);
	}

	@Override
	public StudentQueries saveAskMentor(StudentQueries studentQueries) {
		Mentor mentor = mentorRepository.findOne(studentQueries.getMentor().getId());
		studentQueries.setMentor(mentor);
		return askMentorRepo.save(studentQueries);
	}
	
	@Override
    public void saveMentorFollower(MentorFollowers followers,Long studentId,Long mentorId) {
		Mentor mentor = mentorRepository.findOne(mentorId);
		Student student = studentRepository.findOne(mentorId);
		followers.setMentor(mentor);
		followers.setStudent(student);
		mentorFollowersRepository.save(followers);
    }
	
	@Override
    public void saveMentorFollowerFromService(MentorFollowers followers,Long mentorId) {
		Mentor mentor = mentorRepository.findOne(mentorId);
		followers.setMentor(mentor);
		mentorFollowersRepository.save(followers);
    }
	
	@Override
    public List<Mentor> getAllFollowedMentorsByStatusAndStudent(AdminAcceptance accepted, Student students,Integer limit,Integer offset) throws Exception {
		List<MentorFollowers> followers=mentorFollowersRepository.getAllFollowedMentorsByAcceptanceAndStudent(accepted.ordinal(),students.getId(),limit,offset);
			return followers.stream()
			     .map(mentor-> {
			     return mentor.getMentor();})
			     .collect(Collectors.toList());
    }

	@Override
	public List<StudentQueries> findByAskMentorByStatus(Status inactive) {
		return askMentorRepo.findByStatus(inactive);
	}
	
	@Override
	public List<MentorFollowers> findallMentorFollowers() {
	return mentorFollowersRepository.findAll();
	}

	@Override
	public MentorFollowers findallMentorFollowersById(long id) {
	return mentorFollowersRepository.findById(id);
	}



	@Override
	public StudentQueries updateAskMentor(Status active,long id) {
		StudentQueries studentQueries=askMentorRepo.findOne(id);
		return askMentorRepo.save(studentQueries);
	}

	@Override
	public List<SubCategoryDetails> getAllRecvideos(String interests) {
		List<SubCategoryDetails> videoPaths=subCatRepository.findAll();
		 return videoPaths.stream()
			     .map(subCat-> {
			    	 subCat.setTotalPath(ViewConstants.SAVE_LOCATION+subCat.getSubCategoryDocumentPath()+'/'+subCat.getSubCategoryDocumentName());
			     return subCat;})
			     .collect(Collectors.toList());
		/* return videoPaths.stream()
			     .map(subCat-> {subCat.setTotalPath("file:///"+ImageManipulation.getBase64Image(ViewConstants.SAVE_LOCATION+subCat.getSubCategoryDocumentPath()+'/'+subCat.getSubCategoryDocumentName()));
			     return subCat;})
			     .collect(Collectors.toList());*/
	}

	@Override
	public List<AddEvent> findAllUpComingEvents(String date) throws Exception{
		List<AddEvent> upComingEvents= addEventRepo.findAllUpComingEvents(date);
			return upComingEvents.stream()
				     .map(event-> {
				    		 event.setEvent_date(event.getEvent_date());
							try {
								Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(event.getEvent_date());
								 SimpleDateFormat formatte = new SimpleDateFormat("dd"); 
								 SimpleDateFormat formatte1 = new SimpleDateFormat("MMM");  
	                             String event_day = formatte.format(date1);
	                             String event_month = formatte1.format(date1);
									event.setEvent_day(event_day);
									event.setEvent_month(event_month);
							} catch (ParseException e) {
								e.printStackTrace();
							}
						
					 return event;
				     })
				     .collect(Collectors.toList());
		 
	}
	@Override
	public AddEvent getEvent(Long  eventId) throws Exception{
		return  addEventRepo.findOne(eventId);
			
	}
	@Override
	public void saveEvent(AddEvent event) throws ParseException {
	        CoCreationHub cohub=coCreationHubRepository.findOne(event.getHubZone().getId());
			event.setHubZone(cohub);
	        String s[] = event.getGetDate().split(" ",2);
	        String date = s[0];
	        String time = s[1];
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/dd HH:mm");
			Date convDate = sdf.parse(event.getGetDate());
		    DateFormat inFormat = new SimpleDateFormat("HH:mm");
	        DateFormat outFormat = new SimpleDateFormat("hh:mm a");
	        String convtime = outFormat.format(inFormat.parse(time));
	        DateFormat dateInFormat = new SimpleDateFormat("yyyy/MM/dd");
	        DateFormat dateOutFormat = new SimpleDateFormat("yyyy-MM-dd");
	        String convDate1 = dateOutFormat.format(dateInFormat.parse(date));
			event.setEventDateTime(convDate);
			event.setEvent_date(convDate1);
			event.setEventTime(convtime);
			addEventRepo.save(event);
	}

	@Override
	public AdministratorAuthority commonLogin(LoginRequest login) throws Exception {
		return adminAuthority.findByUserName(login.getUserName());
			}
	
	@Override
	public AdministratorAuthority commonForgotPassword(String userName) throws Exception {
		AdministratorAuthority authority= adminAuthority.findByUserName(userName);
		if(authority==null){
			throw new UserDefinedException("User Not Found");
		}
		return authority;
	}

	@Override
	public List<Contribution> findAllContributions() {
		return contributionRepository.findContributionByAcceptance(AdminAcceptance.Submitted.ordinal());
	}

	@Override
	public Contribution findContributionById(Long contributionId) {
		return contributionRepository.findContributionById(contributionId);
	}

	@Override
	public void saveContribution(Contribution contribution) {
		contributionRepository.save(contribution);		
	}

	@Override
	public List<Contribution> getAllContibutionsForStudents(String userType) {
		Authority authority=authorityRepository.findAdministratorsAuthorityByAuthorityCode(userType);
		List<Contribution> contributions= contributionRepository.findContributionByAcceptanceAndAuthority(AdminAcceptance.Accepted.ordinal(),authority.getId());
			return contributions.stream()
					.map(contribution-> {
                        if(userType.equals("ROLE_STUDENT")) {
                             Student studt=studentRepository.findOne(contribution.getUser());
                             contribution.setUserObj(studt.getFirstName()+" "+studt.getLastName());
                        }
                        else if(userType.equals("ROLE_MENTOR")) {
                             Mentor mentor=mentorRepository.findOne(contribution.getUser());
                             contribution.setUserObj(mentor.getFirstName()+" "+mentor.getLastName());
                        }else if(userType.equals("ROLE_PARENT")) {
                             Parents parent=parentsRepository.findOne(contribution.getUser());
                             contribution.setUserObj("Mr "+parent.getFatherName());
                        }else if(userType.equals("ROLE_SCHOOL_TEACHER")) {
                             SchoolTeacher schoolTeacher=schoolTeacherRepository.findOne(contribution.getUser());
                             contribution.setUserObj(schoolTeacher.getFirstName()+" "+schoolTeacher.getLastName());
                        }    
                     return contribution;})
                     .collect(Collectors.toList());
		
	}

	@Override
	public StudentQueries findStudentQueriesById(Long id) {
		return askMentorRepo.findOne(id);
	}
	
	@Override
    public List<StudentQueries> findByMentor(Long mentorId) {
		Mentor mentor=mentorRepository.findOne(mentorId);
        return askMentorRepo.findByMentor(mentor);
    }

	@Override
	public void saveMentorFollowerss(MentorFollowers followers) {
	mentorFollowersRepo.save(followers);
	}

	@Override
	public List<AddEvent> findAllEvents() throws Exception {
		List<AddEvent> allEvents= addEventRepo.findAllByDate();
			return allEvents.stream()
				     .map(event-> {
							 event.setEvent_date(event.getEvent_date());
							 try {
									Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(event.getEvent_date());
									 SimpleDateFormat formatte = new SimpleDateFormat("dd"); 
									 SimpleDateFormat formatte1 = new SimpleDateFormat("MMM");  
		                             String event_day = formatte.format(date1);
		                             String event_month = formatte1.format(date1);
										event.setEvent_day(event_day);
										event.setEvent_month(event_month);
								} catch (ParseException e) {
									e.printStackTrace();
								}
				     return event;
				     })
				     .collect(Collectors.toList());
		
	}

	@Override
	public List<AddEvent> findAllPreviousEvents(String strDate) throws Exception {
		List<AddEvent> previousEvents= addEventRepo.findAllPreviousEvents(strDate);
		return previousEvents.stream()
			     .map(event-> {
						 event.setEvent_date(event.getEvent_date());
						 try {
								Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(event.getEvent_date());
								 SimpleDateFormat formatte = new SimpleDateFormat("dd"); 
								 SimpleDateFormat formatte1 = new SimpleDateFormat("MMM");  
	                             String event_day = formatte.format(date1);
	                             String event_month = formatte1.format(date1);
									event.setEvent_day(event_day);
									event.setEvent_month(event_month);
							} catch (ParseException e) {
								e.printStackTrace();
							}
			     return event;})
			     .collect(Collectors.toList());
		
	}

	@Override
	public List<ContributionDocs> getAllContibutionsDetails(String userType, Long userId,String type,Integer latest,Integer limit,Integer offset)  throws Exception{
		Authority authority=authorityRepository.findAdministratorsAuthorityByAuthorityCode(userType);
		List<ContributionDocs> contDocs=null;
		
		 if(latest==0){
			contDocs = contributionDocsRepository.findLatestContributionDetailsByAuthorityAndUserAndType(authority.getId(),userId,type,limit,offset);	
		}
		else{
			contDocs=  contributionDocsRepository.findContributionDetailsByAuthorityAndUserAndType(authority.getId(),userId,type,limit,offset);
		}
		return contDocs;
		
	}

	@Override
	public List<CoCreationHub> findByHubStatus(Status active) {
		return coCreationHubRepository.findHubByStatus(active);
	}

	@Override
	public List<CareerCategory> getAllInterests() throws Exception{
		return careerCategoryRepository.findAll();
	}

	@Override
	public List<UserInterests> getAllUserInterests(Long userId, String userType) throws Exception{
		Authority authority=authorityRepository.findAdministratorsAuthorityByAuthorityCode(userType);
		return userInterestsRepository.findAllByUserAndAuthority(userId,authority);
	}

	@Override
	public void saveUserInterests(Long userId, String userType,InterestReq userInterests) {
		userInterests.getUserInterests().stream()
			     .map(userInt-> {
			    	 Authority authority=authorityRepository.findAdministratorsAuthorityByAuthorityCode(userType);
			    	// Interests ints=interestRepository.findOne(userInt.getInterests().getId());
			    	 CareerCategory cc=careerCategoryRepository.findOne(userInt.getInterests().getId());
			    	 userInt.setInterests(cc);
			    	 userInt.setUser(userId);
			    	 userInt.setAuthority(authority);
			    	 userInt.setStatus(Status.Active);
			    	 userInterestsRepository.save(userInt);
			     return userInt;})
			     .collect(Collectors.toList());
				
	}

	
	@Override
    public List<Student> getAllFollowedStudentsByStatusAndMentor(AdminAcceptance accepted, Mentor mentor,Integer limit,Integer offset) throws Exception{
		List<MentorFollowers> followers=mentorFollowersRepository.getAllFollowedStudentsByAcceptanceAndMentor(accepted.ordinal(),mentor.getId(),limit,offset);
				return followers.stream()
			     .map(student-> {
			     return student.getStudent();})
			     .collect(Collectors.toList());
    }

	@Override
	public List<MentorFollowers> findMentorFollowersByMentorAndStudent(Student student, Mentor mentor) throws Exception{
		return  mentorFollowersRepository.findMentorFollowersByMentorAndStudent(mentor,student);
	}

	
	@Override
	public void saveQuestion(StudentQueries studentQueries) {
		askMentorRepository.save(studentQueries);
	}

	

	@Override
	public List<AcademicYear> findByAcademicList(Status active) {
		return academicYearRepository.findAllYearsByStatus(Status.Active);
	}

	@Override
	public List<StudentQueries> getMessagesByMentorAndStudent(Mentor mentor, Student student) {
		return askMentorRepo.findAllByMentorAndStudent(mentor.getId(),student.getId());
	}

	@Override
	public List<SubCategoryDetails> getAllRecommendedVideoByInterst(Long userId, String authority) {
		Authority authoriti=authorityRepository.findAdministratorsAuthorityByAuthorityCode(authority);
		List<UserInterests> interests1=userInterestsRepository.findByUserAndAuthority(userId,authoriti);
		//List<Interests> interests=new ArrayList<>();
		List<CareerCategory> interests=new ArrayList<>();
		List<String> intere=new ArrayList<String>();
		for(UserInterests inter : interests1){
			interests.add(inter.getInterests());
			for(CareerCategory ints :interests){
				intere.add(ints.getCategoryName());
			}
		}
		List<SubCategoryDetails> subCats= subCatDetailsRepo.findAllByInterests(intere);
		 return subCats;
		 
	}
	
	@Override
	public List<Skills> findSubCategorySkillsBySubCategory(Long subcategoryId) throws Exception {
		CareerSubcategory careerSubCat= careerSubCategory.findOne(subcategoryId);
       return skillRepository.findByCareerSubCat(careerSubCat);		
		
	}

	@Override
	public List<Responsibilities> findSubCategoryResponsesBySubCategory(Long subcategoryId) {
		CareerSubcategory careerSubCat= careerSubCategory.findOne(subcategoryId);
		return roleRepository.findByCareerSubCat(careerSubCat);
	}

	@Override
	public List<Abilities> findSubCategoryAbilitiesBySubCategory(Long subcategoryId) throws Exception{
		CareerSubcategory careerSubCat= careerSubCategory.findOne(subcategoryId);
		return abilityRepository.findByCareerSubCat(careerSubCat);
	}

	@Override
	public CareerSubcategory findSubCategoryBySubCatId(Long subcategoryId) {
		return careerSubCatRepo.findOne(subcategoryId);
	}

	@Override
	public List<Institutions> findSubCategoryCollegesBySubCatId(Long subcategoryId,Country country) {
		CareerSubcategory careerSubCat= careerSubCategory.findOne(subcategoryId);
		//return institutionRepository.findByCareerSubCatAndCountry(careerSubCat,country);
		List<InstituteSubcat> instituteSubcats= instituteSubcatRepository.findByCareerSubCatIdAndCountry(careerSubCat,country.ordinal());
		return instituteSubcats.stream()
			     .map(instituteSubcat-> {
			     return instituteSubcat.getInstitutions();})
			     .collect(Collectors.toList());
	}

	@Override
	public void subscribeCareer(SubscribedCareers subscribeCareer) throws Exception{
		subscribeRepository.save(subscribeCareer);
	}

	@Override
	public List<SubscribedCareers> findSubscribedByAuthorityAndUserAndCareerSubcat(Authority authority, Long userId,
			CareerSubcategory subCat)  throws Exception{
		return subscribeRepository.findSubscribedByAuthorityAndUserAndCareerSubcat(authority,userId,subCat);
	}

	@Override
	public SubscribedCareers findSubscribedByAuthorityAndUserAndCareerSubcatAndSubscribe(Authority authority,
			Long userId, CareerSubcategory subCat, Subscribe subscribed) throws Exception{
		return subscribeRepository.findSubscribedByAuthorityAndUserAndCareerSubcatAndSubscribe(authority,userId,subCat,subscribed);
	}

	@Override
	public List<SubscribedCareers> findSubscribedByAuthorityAndUseAndSubscribed(Authority authority, Long userId) throws Exception {
		return subscribeRepository.findSubscribedByAuthorityAndUserAndSubscribe(authority,userId,Subscribe.Subscribed);
	}

	@Override
	public List<CareerSubcategory> findTrendingCareers()  throws Exception{
		return careerSubCatRepo.findByTrending(Trending.IsTrending);
	}

	
	@Override
	public void saveScholorShip(Scholorships scholorships) {
		scholorships.setStatus(Status.Active);
		scholorShipRepo.save(scholorships);
	}

	@Override
	public List<Scholorships> findSubCategoryScholorshipsBySubCatId(Long subcategoryId, Country india) {
		CareerSubcategory careerSubCat= careerSubCategory.findOne(subcategoryId);
		return scholorShipRepo.findAllByCareerSubCatAndCountry(careerSubCat,india);
	}


	@Override
	public void saveActivityLog(ActivityLog al) throws Exception{
		 al.setStatus(Status.Active);
		 if(al.getMediaId()!=null){
			 CareerSubcategory scs = null;
			 ActivityLog all=activityRepository.findByMediaIdAndUserAndAuthority(al.getMediaId(),al.getUser(),al.getAuthority());	 
			 if(all!=null && all.getMediaId()!=null) {
				 all.setCount(all.getCount()+1);
				 activityRepository.save(all);
			 }
			 else{
				 if(al.getSubcategory()!=null){
		        	 if(al.getSubcategory().getId()!=null){
		    			  scs=careerSubCategory.findOne(al.getSubcategory().getId());
		        	 }
					 al.setSubcategory(scs);
		         }
				 if(al.getScreen().equals("contribution")){
				 }
				 if(al.getScreen().equals("sigma")){
				 }
				 al.setCount(1);
				 activityRepository.save(al);	 
			 }
		 }
		 else{
			 al.setCount(1);
			 activityRepository.save(al);	 
		 }
		 	}

	
	@Override
	public List<ActivityLog> findAllActivities(Long userId,String authority)  {
		List<ActivityLog> allActivities = activityRepository.findLastRecordsByUserAndAuthority(userId, authority);
			return allActivities.stream().map(
				activity->{
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					String createdDate = formatter.format(activity.getDateCreated());
					 activity.setActivityDate(createdDate);
					return activity;
				}
				).collect(Collectors.toList());
			}
	@Override
	public List<ActivityLog> findAllActivitiesByLimit(Long userId,String authority,Integer pageNo,Integer pages)  {
		
	 //   PageRequest request = new PageRequest(pageNo-1, pages);

	    List<ActivityLog> activities = activityRepository.findLastRecordsByUserAndAuthority(userId, authority,pageNo,pages);
		
		//List<ActivityLog> allActivities=activities.getContent();
		
			return activities.stream().map(
				activity->{
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
					String createdDate = formatter.format(activity.getDateCreated());
					 activity.setActivityDate(createdDate);
					return activity;
				}
				).collect(Collectors.toList());
			}
	
	@Override
	public List<SuccessPersons> findSubCategoryPersonsBySubCatId(Long subcategoryId) throws Exception{
		CareerSubcategory careerSubCat= careerSubCategory.findOne(subcategoryId);
		return successPersonlityRepo.findAllByCareerSubCat(careerSubCat);
	}

	@Override
	public void savePerson(SuccessPersons person) {
		person.setStatus(Status.Active);
		successPersonlityRepo.save(person);		
	}

/*	@Override
	public List<DemandSupply> getDemandSupplyBycareerCat(CareerSubcategory subcategory) {
		return demandSupplyRepository.findAllByCareerSubCat(subcategory);
	}
*/
	@Override
	public CareerCategory findCategoryById(Long id) {
		return careerCategoryRepository.findOne(id);
	}

	@Override
	public void saveNotification(Notification ntf) {
		ntf.setStatus(Status.Active);
		notificationRepository.save(ntf);
	}

	/*@Override
	public List<Notification> findAllNotification() {
		return notificationRepository.findAllCommonNotifications();
	}*/

	@Override
	public List<Notification> findAllAcceptanceNotification(Long userId,String authority) throws Exception {
		List<Notification> notifications=new ArrayList<Notification>();
		Recommended recommm=recommRepository.findByUserAndAuthority(userId,authority);
		if(authority.equals("ROLE_STUDENT")){
			Long studentId=userId;
			Long mentorId = null;
			notifications=notificationRepository.findAllNotificationsByStudentOrMentorOrDate(studentId,mentorId,recommm.getDateCreated());
			
		}else if(authority.equals("ROLE_MENTOR")){
			Long studentId = null;
			Long mentorId=userId;
			notifications=notificationRepository.findAllNotificationsByStudentOrMentorOrDate(studentId,mentorId,recommm.getDateCreated());
		}
		else if(authority.equals("ROLE_PARENT")){
			notifications=notificationRepository.findAllCommonNotificationsByDate(recommm.getDateCreated());
		}
		else if(authority.equals("ROLE_SCHOOL_TEACHER")){
			notifications=notificationRepository.findAllCommonNotificationsByDate(recommm.getDateCreated());
		}
			 return notifications.stream().map(
					notification->{
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
						String createdDate = formatter.format(notification.getDateCreated());
						notification.setDisplayDate(createdDate);
						if(notification.getSubcategory()!=null){
							notification.setSubCatId(notification.getSubcategory().getId());
						}
						return notification;
					}
					).collect(Collectors.toList());
	}

	@Override
	public List<Notification> findAllAcceptanceNotificationByLimit(Long userId,String authority,Integer pageNo,Long pages) {
		 //  PageRequest request = new PageRequest(pageNo-1, pages);
		List<Notification> notifications=new ArrayList<Notification>();
		Recommended recommm=recommRepository.findByUserAndAuthority(userId,authority);
		if(authority.equals("ROLE_STUDENT")){
			Long studentId=userId;
			Long mentorId=null;
			notifications=notificationRepository.findAllNotificationsByLimit(studentId,mentorId,recommm.getDateCreated(),pageNo,pages,studentId,authority);
		}else if(authority.equals("ROLE_MENTOR")){
			Long studentId=null;
			Long mentorId=userId;
			notifications=notificationRepository.findAllNotificationsByLimit(studentId,mentorId,recommm.getDateCreated(),pageNo,pages,mentorId,authority);
		}
		else if(authority.equals("ROLE_PARENT")){
		 notifications=notificationRepository.findAllCommonNotificationsByDateCreated(recommm.getDateCreated(),pageNo,pages,userId,authority);
		}
		else if(authority.equals("ROLE_SCHOOL_TEACHER")){
			notifications=notificationRepository.findAllCommonNotificationsByDateCreated(recommm.getDateCreated(),pageNo,pages,userId,authority);
           	}
			 return notifications.stream().map(
					notification->{
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
						String createdDate = formatter.format(notification.getDateCreated());
						notification.setDisplayDate(createdDate);
						if(notification.getSubcategory()!=null){
							notification.setSubCatId(notification.getSubcategory().getId());
						}
						return notification;
					}
					).collect(Collectors.toList());
	}

	@Override
	public void saveRecommendedVideos(Recommended recome,Long subCatid) throws Exception{
		CareerSubcategory subcat=careerSubCategory.findOne(subCatid);
		Recommended recVideos=recommRepository.findByCareerSubcatAndUserAndAuthority(subcat.getId(),recome.getUser(),recome.getAuthority());
		if(recVideos==null){
			recome.setStatus(Status.Active);
			recome.setCareerSubcat(subcat);
			recome.setCount(1);
			recome.setCareerCat(subcat.getCareerCategory());
			recommRepository.save(recome);	
		}
		else if(recVideos!=null){
			recVideos.setCount(recVideos.getCount()+1);
			//recVideos.setMediaId(recVideos.getMediaId(),recome.getMediaId());
			recommRepository.save(recVideos);
		}
			
	}

	@Override
	public List<Recommended> getRecommendedVideos(Long userId, String userType) {
		List<Recommended> recVideos=recommRepository.findAllRecommendationsByUserAndAuthority(userId,userType);
		float total = 0;
	    for(int i = 0; i < recVideos.size(); i++){
	        total += recVideos.get(i).getCount();
	    }

	    for(int j = 0; j < recVideos.size(); j++){
	    	// double percentage = Math.round(100.2d * 
	          //       Double.valueOf(recVideos.get(j).getCount()) / total);
	    	//System.out.println("perr"+recVideos.get(j).getCount() / total);
	    	//double percentage = 100.0 * (recVideos.get(j).getCount() / total) ;
	    	
	      double percentage = (recVideos.get(j).getCount() * 100 )/ total ;

	        DecimalFormat df = new DecimalFormat("#.##");

	        df.setRoundingMode(RoundingMode.CEILING);

	        recVideos.get(j).setCatPercent(df.format(percentage));
	    	/*System.out.println("celini"+df.format(percentage1));

	        
	      double percentage = round((float)((recVideos.get(j).getCount() * 100.2f )/ total ),2);

	    	System.out.println("sadsd"+percentage1);
	    	
	    	System.out.println("xczcx"+percentage);

	    	 recVideos.get(j).setCatPercent(percentage);
*/	    
	        
	    }
	   /* recVideos.stream()
	     .map(recVideo-> {
	    	 recVideo.setCatPercent(percentage);
	     return recVideo;})
	     .collect(Collectors.toList());*/
		 return recVideos;
	}
	
	@Override
	public List<UserInterests> getAllPieCharts(Long userId, String userType) {
		Authority authoriti=authorityRepository.findAdministratorsAuthorityByAuthorityCode(userType);
		List<UserInterests> interests1=userInterestsRepository.findByUserAndAuthority(userId,authoriti);
		for(int j = 0; j < interests1.size(); j++){
		   	 double percentage = Math.round(1.0 * Double.valueOf(100) / interests1.size());
		   	interests1.get(j).setCatPercent(percentage);
		   }
		return interests1;
	}

	/*@Override
	public Integer getTotalSumOfCount(Long userId, String userType) {
		return recommRepository.getTotalSumOfCount(userId,userType);
	}*/
	/*@Override
	public List<Integer,CareerSubcategory> getPiechartData(Long userId, String userType) {
		return recommRepository.findAllPieChartDataByUserAndAuthority(userId,userType);
	}*/
	
	@Override
	public List<SubCategoryDetails> getAllRecommendedVideosBySubcat(List<Long> subcats,List<Long> mediaIds,Integer limit,Integer offset) throws Exception {
		return subCatDetailsRepo.findAllRecommendationsByCareerSubcat(subcats,mediaIds,limit,offset);
	}
	
	@Override
	public List<InstitutionCourses> getCoursesByInstitution(Institutions institution) {
		List<InstitutionCourses> courses= coursesRepositoty.findByInstitution(institution);
		 return courses;
	}

	@Override
	public List<ActivityLog> findAllByUserAndAuthorityAndSubcategoryByVideos(Long userId, String userType, Long subCatId) throws Exception {
		return  activityRepository.findAllByUserAndAuthorityAndSubcategoryByVideos(userId,userType,subCatId);
	}
	
	@Override
	public List<ActivityLog> findAllByUserAndAuthorityAndSubcategoryByAudios(Long userId, String userType, Long subCatId)  throws Exception{
		return  activityRepository.findAllByUserAndAuthorityAndSubcategoryByAudios(userId,userType,subCatId);
	}
	
	@Override
	public List<ActivityLog> findAllByUserAndAuthorityAndSubcategoryByPdfs(Long userId, String userType, Long subCatId)  throws Exception{
		return  activityRepository.findAllByUserAndAuthorityAndSubcategoryByPdfs(userId,userType,subCatId);
	}

	@Override
	public List<ActivityLog> findAllByUserAndAuthorityAndSubcategoryByType(Long userId, String userType, Long mediaId,
			String type,Integer limit,Integer offset) {
		 return  activityRepository.findAllByUserAndAuthorityAndSubcategoryByType(userId,userType,mediaId,type,limit,offset);
	}

	@Override
	public List<Double> getDemandSupplyBycareerCatAndLabel(CareerSubcategory subcategory, DemandLabels ind,Integer demandType) throws Exception {
		List<Double> vals=new ArrayList<Double>();
		if(demandType==0){
			vals=demandSupplyRepository.findAllByCareerSubCatAndLabel(subcategory.getId(),ind.getId());
		}
		else if(demandType==1){
			vals=demandSupplyRepository.findAllManpowerByCareerSubCatAndCountry(subcategory.getId(),ind.getId());
		}
		return vals;
	}

	
	
	@Override
	public Apk findApkUpdateAPI() throws Exception{
		return apkRepository.findApkUpdateAPI();
	}

	@Override
	public void updateApk(Apk apk) {
		apkRepository.save(apk);		
	}

	@Override
	public List<CareerSubcategory> findAllByCategoryids(List<Long> categoryIds) {
		return careerSubCategory.findByCategoryIds(categoryIds);
	}

	@Override
	public String getDemandSupplyBycareerByYear(DemandYears year) {
		Long id= demandSupplyRepository.getDemandSupplyBycareerByYear(year.getId());
		return demandColorsRepository.findColorById(id);
	}

	@Override
	public void saveDemand(DemandSupply demand) {
		demand.setStatus(Status.Active);
		//demand.setBackgroundColor(demand.getColor().getColor());
		demandSupplyRepository.save(demand);
	}

	@Override
	public List<DemandYears> findAllYears() throws Exception{
		return demandYearsRepository.findAll();
	}

	@Override
	public List<DemandColors> findAllColors() throws Exception {
		return demandColorsRepository.findAll();
	}

	@Override
	public List<DemandLabels> findAllLabels() throws Exception {
		return demandLabelsRepository.findAll();
	}

	@Override
	public List<UserInterests> findInterestsByUserIdAndUserType(Long userId, String userType) {
		Authority authority=authorityRepository.findAdministratorsAuthorityByAuthorityCode(userType);
		return userInterestsRepository.findByUserAndAuthority(userId, authority);
	}

	@Override
	public void deleteAllUserInterests(Long userId, String userType) throws Exception{
		Authority authority=authorityRepository.findAdministratorsAuthorityByAuthorityCode(userType);
		userInterestsRepository.deleteAllByUserAndAuthority(userId, authority.getId());		
	}
	
	@Override
	public ServerBroke findserverBrokeFlag() {
		return serverBrokeRepository.findServerBrokeFlag();
	}

	@Override
	public List<DemandYears> getDemandYears() {
		return demandYearsRepository.findAll();
	}

	@Override
	public List<DemandColors> getDemandColors() {
		return demandColorsRepository.findAll();
	}

	@Override
	public List<DemandLabels> getDemandLabels() {
		return demandLabelsRepository.findAll();
	}
	@Override
	public List<Recommended> getAlluserSubcategories(Long userId, String userType)  throws Exception{
		List<Recommended> recommended= recommRepository.findAllRecommendationsByUserAndAuthority(userId, userType);
		if(recommended==null){
			throw new UserDefinedException("There is No Interests For You");
		}
		return recommended;
	}

	@Override
	public Institutions getInstituteById(Long id) {
		return institutionRepository.findOne(id);
	}

	@Override
	public void saveCourses(InstitutionCourses courses) {
		coursesRepositoty.save(courses);
	}

	@Override
	public List<Institutions> findAllInstitutions() {
		return institutionRepository.findAll();
	}

	
	@Override
	public List<SubCategoryDetails> getAllRecommendedVideosBySubcatNoActivity(List<Long> subcats,Integer limit,Integer offset) throws Exception {
		return subCatDetailsRepo.findAllRecommendationsByCareerSubcatNoAct(subcats,limit,offset);
	}

	@Override
	public List<Scholorships> findSubCategoryScholorshipsByCountry(Country india) {
		return scholorShipRepo.findAllByCountry(india);
	}

	@Override
	public CommonImages getCommonImage(String screenType) {
		return commonImagesRepository.findAllByScreen(screenType);
	}

	@Override
	public CommonImages saveCommonImages(CommonImages ci) {
		return commonImagesRepository.save(ci);
	}

	@Override
	public List<Double> getDemandSupplyByYearAndCareerCat(CareerSubcategory subcategory, DemandYears year, Integer demandType) {
		List<Double> vals=new ArrayList<Double>();
		if(demandType==0){
			vals=demandSupplyRepository.findAllByCareerSubCatAndYear(subcategory.getId(),year.getId());
		}
		else if(demandType==1) {
			vals=demandSupplyRepository.findAllBySalaryCareerSubCatAndYear(subcategory.getId(),year.getId());
		}
		return vals;
	}
	private static double round(double value, int numberOfDigitsAfterDecimalPoint) {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(numberOfDigitsAfterDecimalPoint,
                BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }

	@Override
	public Notification findNotificationBYUserAndmentor(Student student, Mentor mentor) {
		return notificationRepository.findByStudentAndMentor(student,mentor);
	}

	@Override
	public ActivityLog findAllActivitiesByUserAndTypeAndSubCatAndScreen(Long userId, String authority,CareerSubcategory subCat, Subscribe subscribed) {
		return activityRepository.findAllByUserAndTypeAndSubCatAndScreen(userId, authority,subCat.getId(),subscribed.name());
	}

	@Override
	public CommonImages findCommonImagesByScreen(String screen) {
		return commonImagesRepository.findAllByScreen(screen);
	}
	@Override
	public List<InstitutionCourses> getCoursesByInstitutionAndSubcat(Institutions institution, CareerSubcategory careerSubcategory) {
		List<InstitutionCourses> courses= coursesRepositoty.findByInstitutionAndSubcatId(institution, careerSubcategory);
		 return courses;
	}

	@Override
	public List<ContributionDocs> findAllContributionDocuments() {
		return contributionDocsRepository.findContributionDocsByAcceptance(AdminAcceptance.Submitted);
	}

	@Override
	public ContributionDocs findContributionDocById(Long contributionDocId) {
		return contributionDocsRepository.findOne(contributionDocId);
	}

	@Override
	public void saveContributionDocs(ContributionDocs contributionDoc) {
		contributionDocsRepository.save(contributionDoc);		
	}

	@Override
	public ContributionDocs findAllContributionDocById(Long contributionDocId) {
		return contributionDocsRepository.findOne(contributionDocId);
	}
	
	@Override
	public void saveFeedback(Feedback feedback) {
		feedbackRepository.save(feedback);
	}

	@Override
	public List<InitialInterests> getInitialInterests(Long userId, String userType) {
		List<InitialInterests> ii=new ArrayList<InitialInterests>();
		//List<Recommended> recVideos=recommRepository.findAllRecommendationByUserAndAuthority(userId,userType);
		Authority authority=authorityRepository.findAdministratorsAuthorityByAuthorityCode(userType);
		List<UserInterests> userIntss=userInterestsRepository.findAllByUserAndAuthority(userId,authority);
		userIntss.stream()
			     .map(rec-> {
			    	 InitialInterests iis=new InitialInterests();
			    	 iis.setCategoryName(rec.getInterests().getCategoryName());
			    	 List<CategoryResponse> ccss=new ArrayList<CategoryResponse>();
			    	 List<Recommended> recVideo=recommRepository.findAllRecommendationByUserAndAuthorityAndCareerCat(userId,userType,rec.getInterests().getId());
			    			 recVideo.stream().map(subcat-> {
					    		 CategoryResponse cr=new CategoryResponse();
					    		 cr.setName(subcat.getCareerSubcat().getSubCategoryName());
					    		 cr.setId(subcat.getCareerSubcat().getId());
						    		ccss.add(cr);
						    		return subcat; 
						    	 }).collect(Collectors.toList());
			    			 iis.setResult(ccss);
			    	 ii.add(iis);
			    	 iis.setId(ii.size());
			     return rec;
			     })
			     
			     .collect(Collectors.toList());
		return ii;
		}
	@Override
	public CoCreationHub findByHubNameAndStatus(String hubZoneName, Status active) {
		return coCreationHubRepository.findByHubNameAndStatus(hubZoneName, Status.Active);
	}

	@Override
	public SevenSigma findSigma(Long sigmaId) throws Exception {
		return sigmaRepository.findOne(sigmaId);
	}
	
	@Override
	public List<Feedback> findAllfeedbacks() {
		return feedbackRepository.findAll();
	}

	@Override
	public SevenSigmaDetails findSigmaDetails(Long mediaId) {
		return sigmaDetailsRepo.findOne(mediaId);
	}

	@Override
	public Map<String, Long>  getadminHomeData() {
		Map<String, Long> adminHomeData = new HashMap<String, Long>();
		//finding all contributors
		adminHomeData.put("allUsers", studentRepository.count()+mentorRepository.count()+schoolTeacherRepository.count()+parentsRepository.count());
		adminHomeData.put("allStudents", studentRepository.count());
		adminHomeData.put("allMentors", mentorRepository.count());
		adminHomeData.put("allschoolTeachers", schoolTeacherRepository.count());
		adminHomeData.put("allParents", parentsRepository.count());
		adminHomeData.put("allschools", schoolRepository.count());
		adminHomeData.put("allEvents", addEventRepository.count());
		adminHomeData.put("allHubs", coCreationHubRepository.count());
		adminHomeData.put("studentContributors", contributionRepository.findAllStudentContributors());
		adminHomeData.put("mentorContributors", contributionRepository.findAllMentorContributors());
		adminHomeData.put("schoolTeacherContributors", contributionRepository.findAllschoolTeacherContributors());
		adminHomeData.put("parentContributors", contributionRepository.findAllParentContributors());
		adminHomeData.put("allContributors", contributionRepository.findAllStudentContributors()+contributionRepository.findAllMentorContributors()+contributionRepository.findAllMentorContributors()+contributionRepository.findAllschoolTeacherContributors());
		
		adminHomeData.put("studentContributions", contributionDocsRepository.findAllStudentContributions());
		adminHomeData.put("mentorContributions", contributionDocsRepository.findAllMentorContributions());
		adminHomeData.put("schoolTeacherContributions", contributionDocsRepository.findAllschoolTeacherContributions());
		adminHomeData.put("parentContributions", contributionDocsRepository.findAllParentContributions());
		adminHomeData.put("allContributions", contributionDocsRepository.findAllStudentContributions()+contributionDocsRepository.findAllMentorContributions()+contributionDocsRepository.findAllschoolTeacherContributions()+contributionDocsRepository.findAllParentContributions());
		
		adminHomeData.put("allVideos", subCategoryDetailsRepository.findAllSubcareerVideos());
		adminHomeData.put("allPDFs", subCategoryDetailsRepository.findAllSubcareerPDFs());
		adminHomeData.put("allMedia", subCategoryDetailsRepository.findAllSubcareerPDFs()+subCategoryDetailsRepository.findAllSubcareerVideos());
		return adminHomeData;
	}

	@Override
	public List<ContributionDocs> findAllSubmittedContributions() {
		return contributionDocsRepository.findAllsubmittedContributions();
	}
}