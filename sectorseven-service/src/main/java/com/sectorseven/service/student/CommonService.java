package com.sectorseven.service.student;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.sectorseven.model.Enum.AdminAcceptance;
import com.sectorseven.model.Enum.Country;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.Enum.Subscribe;
import com.sectorseven.model.admin.AcademicYear;
import com.sectorseven.model.admin.AdministratorAuthority;
import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.admin.Mentor;
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
import com.sectorseven.model.school.Student;
import com.sectorseven.model.school.UserInterests;
import com.sectorseven.req.InterestReq;
import com.sectorseven.resp.InitialInterests;
import com.sectorseven.service.common.LoginRequest;

public interface CommonService {

	public Map<String, Long> getadminHomeData();
	
	public List<SevenSigma> getSigmas() throws Exception;
	
	public SevenSigma findSigma(Long sigmaId) throws Exception;

	
	public List<SevenSigmaDetails> getSigmaDetails(Long sigmaId,String type,Integer limit,Integer offset) throws Exception;
	
	public AddEvent getEvent(Long  eventId) throws Exception;

	public List<Mentor> getAllMentorsByInterst(Long studentId,String authority,Integer limit,Integer offset) throws Exception;

	//public List<Mentor> getAllMentorsByInterst();

	
	public Mentor getMentorDetails(long mentorId) throws Exception;

	public List<CareerSubcategory> getAllTrendingCareers();

	public StudentQueries saveAskMentor(StudentQueries askMentor);

	public List<StudentQueries> findByAskMentorByStatus(Status inactive);

	public StudentQueries updateAskMentor(Status active,long id);

	public List<SubCategoryDetails> getAllRecvideos(String interests);
	
	public List<AddEvent> findAllUpComingEvents(String date) throws Exception;	
	
	void saveEvent(AddEvent event) throws ParseException;

	public AdministratorAuthority commonLogin(LoginRequest login) throws Exception;
	
	public AdministratorAuthority commonForgotPassword(String userName) throws Exception;

	public List<Contribution> findAllContributions();

	public Contribution findContributionById(Long contributionId);

	public void saveContribution(Contribution contribution);

	public List<Contribution> getAllContibutionsForStudents(String userType) throws Exception;
	
	public List<MentorFollowers> findallMentorFollowers();

	public MentorFollowers findallMentorFollowersById(long id);

	public StudentQueries findStudentQueriesById(Long id);
	
	public void saveMentorFollower(MentorFollowers followers,Long studentId,Long mentorId);
	
	public void saveMentorFollowerFromService(MentorFollowers followers,Long mentorId);
	
	public List<Mentor> getAllFollowedMentorsByStatusAndStudent(AdminAcceptance accepted, Student students,Integer limit,Integer offset) throws Exception;
	
	public List<StudentQueries> findByMentor(Long mentorId);

	public void saveMentorFollowerss(MentorFollowers follower);

	public SubCategoryDetails findBySubCategoryId(long subCatId);

	public List<AddEvent> findAllEvents() throws Exception;

	public List<AddEvent> findAllPreviousEvents(String strDate) throws Exception;

	public List<ContributionDocs> getAllContibutionsDetails(String authority,Long userId,String type,Integer latest,
			Integer limit,Integer offset) throws Exception;

	public List<CoCreationHub> findByHubStatus(Status active);

	public List<CareerCategory> getAllInterests() throws Exception;

	public List<UserInterests> getAllUserInterests(Long userId, String userType) throws Exception;

	public void saveUserInterests(Long userId, String userType,InterestReq userInterests);

	public List<Student> getAllFollowedStudentsByStatusAndMentor(AdminAcceptance accepted, Mentor mentor,Integer limit,Integer offset) throws Exception;

	public List<MentorFollowers> findMentorFollowersByMentorAndStudent(Student student, Mentor mentor) throws Exception;

	/*public Interests getInterestDetails(long interestId) throws Exception;*/

	public void saveQuestion(StudentQueries studentQueries);


	public List<AcademicYear> findByAcademicList(Status active);

	public List<StudentQueries> getMessagesByMentorAndStudent(Mentor mentor, Student student) throws Exception;

	public List<SubCategoryDetails> getAllRecommendedVideoByInterst(Long userId, String authority);

	public List<Skills> findSubCategorySkillsBySubCategory(Long subcategoryId) throws Exception;

	public List<Responsibilities> findSubCategoryResponsesBySubCategory(Long subcategoryId) throws Exception;

	public List<Abilities> findSubCategoryAbilitiesBySubCategory(Long subcategoryId) throws Exception;

	public CareerSubcategory findSubCategoryBySubCatId(Long subcategoryId) throws Exception;

	public List<Institutions> findSubCategoryCollegesBySubCatId(Long subcategoryId,Country country) throws Exception;

	public void subscribeCareer(SubscribedCareers subCareer) throws Exception;

	public List<SubscribedCareers> findSubscribedByAuthorityAndUserAndCareerSubcat(Authority authority, Long userId,
			CareerSubcategory subCat) throws Exception;

	public SubscribedCareers findSubscribedByAuthorityAndUserAndCareerSubcatAndSubscribe(Authority authority,
			Long userId, CareerSubcategory subCat, Subscribe subscribed) throws Exception;

	public List<SubscribedCareers> findSubscribedByAuthorityAndUseAndSubscribed(Authority authority, Long userId) throws Exception;

	public List<CareerSubcategory> findTrendingCareers() throws Exception;


	public void saveScholorShip(Scholorships scholorships);

	public List<Scholorships> findSubCategoryScholorshipsBySubCatId(Long subcategoryId, Country india)throws Exception;

	
	public void saveActivityLog(ActivityLog al) throws Exception;

	public List<ActivityLog> findAllActivities(Long userId,String authority) ;

	public List<SuccessPersons> findSubCategoryPersonsBySubCatId(Long subcategoryId) throws Exception;

	public void savePerson(SuccessPersons person);

//	public List<DemandSupply> getDemandSupplyBycareerCat(CareerSubcategory subcategory);

	public CareerCategory findCategoryById(Long id) throws Exception;

	public void saveNotification(Notification ntf);

	//public List<Notification> findAllNotification();

	public List<Notification> findAllAcceptanceNotification(Long userId,String authority) throws Exception;

	public void saveRecommendedVideos(Recommended recome,Long subcatId) throws Exception;

	public List<Recommended> getRecommendedVideos(Long userId, String userType) throws Exception;

	public List<SubCategoryDetails> getAllRecommendedVideosBySubcat(List<Long> subcats,List<Long> mediaIds,Integer limit,Integer offset) throws Exception;

	//public List<Recommended> getPiechartData(Long userId, String userType);

	// public List<ContributionDocs> getAllContibutionsDetails(Long contributionId);

	//public Object findMentorQuestionsById(int id);
	
	public List<UserInterests> getAllPieCharts(Long userId, String userType);

	
	public List<ActivityLog> findAllByUserAndAuthorityAndSubcategoryByVideos(Long userId, String userType, Long subCatId) throws Exception;
	
	public List<ActivityLog> findAllByUserAndAuthorityAndSubcategoryByAudios(Long userId, String userType, Long subCatId) throws Exception;
	
	public List<ActivityLog> findAllByUserAndAuthorityAndSubcategoryByPdfs(Long userId, String userType, Long subCatId) throws Exception;
	
	public List<ActivityLog> findAllByUserAndAuthorityAndSubcategoryByType(Long userId, String userType, Long mediaId,
			String type,Integer limit,Integer offset) throws Exception;

	public List<Double> getDemandSupplyBycareerCatAndLabel(CareerSubcategory subcategory, DemandLabels label,Integer demandType) throws Exception;

	public Apk findApkUpdateAPI() throws Exception;

	public void updateApk(Apk apk);

	public List<CareerSubcategory> findAllByCategoryids(List<Long> categoryIds);

	public String getDemandSupplyBycareerByYear(DemandYears year) throws Exception;
	
	public void saveDemand(DemandSupply demand);

	public List<DemandYears> findAllYears() throws Exception;

	public List<DemandColors> findAllColors() throws Exception;

	public List<DemandLabels> findAllLabels() throws Exception;

	public List<UserInterests> findInterestsByUserIdAndUserType(Long userId, String userType) throws Exception;

	public void deleteAllUserInterests(Long userId, String userType) throws Exception ;
	
	public ServerBroke findserverBrokeFlag() throws Exception;

	public List<Recommended> getAlluserSubcategories(Long userId, String userType) throws Exception;

	public List<DemandYears> getDemandYears();

	public List<DemandColors> getDemandColors();

	public List<DemandLabels> getDemandLabels();

	public Institutions getInstituteById(Long id);

	public void saveCourses(InstitutionCourses course);

	public List<Institutions> findAllInstitutions();

	public List<SubCategoryDetails> getAllRecommendedVideosBySubcatNoActivity(List<Long> newList,Integer limit,Integer offset) throws Exception;

	public List<InstitutionCourses> getCoursesByInstitution(Institutions institute);

	public List<Scholorships> findSubCategoryScholorshipsByCountry(Country india) throws Exception;
	
	public CommonImages getCommonImage(String screenType) throws Exception;

	public CommonImages saveCommonImages(CommonImages ci);

	public List<Double> getDemandSupplyByYearAndCareerCat(CareerSubcategory subcategory, DemandYears year, Integer demandType);

	public Notification findNotificationBYUserAndmentor(Student student, Mentor mentor);


	public ActivityLog findAllActivitiesByUserAndTypeAndSubCatAndScreen(Long userId, String userType,
			CareerSubcategory subCat, Subscribe subscribed);

	public CommonImages findCommonImagesByScreen(String screen);

	List<InstitutionCourses> getCoursesByInstitutionAndSubcat(Institutions institution,
			CareerSubcategory careerSubcategory);

	public List<ContributionDocs> findAllContributionDocuments();

	public ContributionDocs findContributionDocById(Long contributionDocId);

	public void saveContributionDocs(ContributionDocs contributionDoc);

	public ContributionDocs findAllContributionDocById(Long contributionDocId);

	public List<ActivityLog> findAllActivitiesByLimit(Long userId, String authority, Integer pageNo,Integer pages);

	public List<Notification> findAllAcceptanceNotificationByLimit(Long userId, String authority, Integer pageNo,Long pages);
	
	public void saveFeedback(Feedback feedback);

	public List<InitialInterests> getInitialInterests(Long userId, String userType);

	public CoCreationHub findByHubNameAndStatus(String hubZoneName, Status active);
	
	public List<Feedback> findAllfeedbacks();

	public SevenSigmaDetails findSigmaDetails(Long mediaId);
	
	public List<ContributionDocs> findAllSubmittedContributions();


}
