package com.sectorseven.service.student;

import java.util.List;

import com.sectorseven.model.admin.AcademicYear;
import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.admin.Mentor;
import com.sectorseven.model.admin.Parents;
import com.sectorseven.model.common.CareerCategory;
import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.CoCreationHub;
import com.sectorseven.model.common.Contribution;
import com.sectorseven.model.common.ContributionDocs;
import com.sectorseven.model.common.ExamPattern;
import com.sectorseven.model.common.Institutions;
import com.sectorseven.model.common.Responsibilities;
import com.sectorseven.model.common.SubCategoryDetails;
import com.sectorseven.model.common.TimeSpent;
import com.sectorseven.model.school.SchoolTeacher;
import com.sectorseven.model.school.Student;
import com.sectorseven.model.school.UserInterests;
import com.sectorseven.req.UpdateRequest;

public interface StudentService {

	public void save(Student student) throws Exception;

	public void save(List<Student> excelStudents);

	public Student findByMobile(Long valueOf);

	public Student findByEmail(String emailID);

	public String generateUserName(String string);

	public Student saveStudentAndParent(Student student, boolean excelornot) throws Exception;

	public void saveStudent(Student student, boolean excelornot) throws Exception;

	public Student findAdministratorByUsername(String username) throws Exception;

	public Student updateStudent(UpdateRequest student,Long userId) throws Exception;
	
	public Student update(Student student,long studentId);

	public Student findByStudentId(Long id) throws Exception;

	public Contribution saveContribution(Contribution contribution) throws Exception;

	public List<CareerCategory> findAllCategories() throws Exception;

	public List<CareerSubcategory> findCareerSubcategories(long subcat) throws Exception;

	public List<SubCategoryDetails> findCareerSubCategoryDetails(long catid,String type,Integer limit,Integer offset) throws Exception;

	public List<CoCreationHub> findAllCoCreationHubs() throws Exception;

	public void loginTime(Student student);

	public void logoutTime(TimeSpent logouttime);

	//public Student findStudentByUsername(String userName,String userType);
	
	public Mentor findMentorByUsername(String userName,String userType);

	List<Student> findStudentBySchoolTeacher(SchoolTeacher schoolTeacher);

	public CoCreationHub findAllCoCreationHubById(long hubId) throws Exception;

	public ContributionDocs saveContributionDocs(ContributionDocs contDocs,Long contributionId) throws Exception;

	public Contribution findAllContributionByAuthorityAndUser(Authority authority, Long id);

	public List<SubCategoryDetails> findCareerSubCategoryDetailsForMain(long subCatId);

	public List<Responsibilities> findCareerSubCategoryDetailsForRoles(long subCatId);

	public List<Institutions> findCareerSubCategoryDetailsForInstitutions(long subCatId);

	public List<ExamPattern> findCareerSubCategoryDetailsForMainExamPatterns(long subCatId);

	//public CareerSubcategory findCareerSubCategory(long subCatId);

	public List<UserInterests> findInterestsByUserIdAndAuthority(Long userId, String userType) throws Exception;

	public CareerCategory findCategoryById(long catId) throws Exception;

	public CareerSubcategory findCareerSubCategoryById(long subCatId) throws Exception;

	public SubCategoryDetails findSubCatDetailById(long subCatDetailId) throws Exception;

	public Student forgotPassword(String username) throws Exception;

	public String changePassword(String password,  Long userId) throws Exception;

	public String verifyOtp(String otp, Long userId,String type,String emailId,String phoneNo) throws Exception;

	public List<Student> findStudentsByParent(Parents parent) throws Exception;

	public List<Student> getAllStudentsBySchoolTeacher(SchoolTeacher schoolTeacher,Integer limit,Integer offset);

	public AcademicYear findYearByAcademicYearId(Long id);

	public ContributionDocs findContributionById(long contributionId) throws Exception;

	public void updateProfileImage(Student student);

	public Student updatedescStudent(String desc, Long userId) throws Exception;

	public String sendOtp(String emailId, Long userId, String userType) throws Exception;

	public String sendMobileOtp(String phoneNo, Long userId, String userType) throws Exception;
	
	
	/*public List<Contribution> findAllTalksByAutority(String authorityCode);*/
	
	 


}
