package com.sectorseven.service.admin.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.admin.AcademicYear;
import com.sectorseven.model.common.Abilities;
import com.sectorseven.model.common.CareerCategory;
import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.CoCreationHub;
import com.sectorseven.model.common.DemandColors;
import com.sectorseven.model.common.DemandLabels;
import com.sectorseven.model.common.DemandYears;
import com.sectorseven.model.common.ExamPattern;
import com.sectorseven.model.common.InstituteSubcat;
import com.sectorseven.model.common.InstitutionCourses;
import com.sectorseven.model.common.Institutions;
import com.sectorseven.model.common.Notification;
import com.sectorseven.model.common.Responsibilities;
import com.sectorseven.model.common.SevenSigma;
import com.sectorseven.model.common.SevenSigmaDetails;
import com.sectorseven.model.common.Skills;
import com.sectorseven.model.common.SubCategoryDetails;
import com.sectorseven.model.school.School;
import com.sectorseven.model.school.SchoolTeacher;
import com.sectorseven.repository.School.SchoolRepository;
import com.sectorseven.repository.School.SchoolTeacherRepository;
import com.sectorseven.repository.admin.AcademicYearRepository;
import com.sectorseven.repository.common.AbilityRepository;
import com.sectorseven.repository.common.CareerCategoryRepository;
import com.sectorseven.repository.common.CareerSubCategoryRepository;
import com.sectorseven.repository.common.CoCreationHubRepository;
import com.sectorseven.repository.common.CoursesRepositoty;
import com.sectorseven.repository.common.DemandColorsRepository;
import com.sectorseven.repository.common.DemandLabelsRepository;
import com.sectorseven.repository.common.DemandYearsRepository;
import com.sectorseven.repository.common.ExamPatternRepository;
import com.sectorseven.repository.common.InstituteSubcatRepository;
import com.sectorseven.repository.common.InstitutionRepository;
import com.sectorseven.repository.common.NotificatioRepository;
import com.sectorseven.repository.common.RolesAndResponseRepository;
import com.sectorseven.repository.common.SevenSigmaDetailsRepository;
import com.sectorseven.repository.common.SevenSigmaRepository;
import com.sectorseven.repository.common.SkillRepository;
import com.sectorseven.repository.common.SubCategoryDetailsRepository;
import com.sectorseven.repository.common.SubscribeRepository;
import com.sectorseven.service.admin.CareerService;

@Transactional
@Service
public class CareerServiceImpl implements CareerService{
	
	@Autowired
	private CareerCategoryRepository careerCategoryRepository;
	
	@Autowired
	private CareerSubCategoryRepository careerSubCategoryRepository;
	
	@Autowired
	private CoCreationHubRepository coCreationHubRepository;
	
	@Autowired
	private SubCategoryDetailsRepository subCatDetailsRepo;
	
	@Autowired
	private RolesAndResponseRepository rolesAndResponseRepo;
	
	@Autowired
	private AbilityRepository abilityRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private InstitutionRepository institutionRepository;
	
	
	@Autowired
	private ExamPatternRepository examPatternRepository;
	
	@Autowired
	private DemandColorsRepository demandColorsRepository;
	
	@Autowired
	private DemandLabelsRepository demandLabelsRepository;
	
	
	@Autowired
	private DemandYearsRepository demandYearsRepository;
	
	

/*	@Autowired
	private InterestRepository interestRepository;*/
	
	@Autowired
	private SubscribeRepository subscribeRepository;
	
	@Autowired
	private SchoolTeacherRepository schoolTeacherRepository;
	
	@Autowired
	private AcademicYearRepository academicYearRepository;
	
	@Autowired
	private SchoolRepository schoolRepository;
	
	@Autowired
	private NotificatioRepository notificatioRepository;
	
	
	@Autowired
	private CoursesRepositoty coursesRepositoty;
	
	@Autowired
	private SevenSigmaRepository sevenSigmaRepository;
	
	@Autowired
	private SevenSigmaDetailsRepository sevenSigmaDetailsRepository;
	
	
	@Autowired
	private InstituteSubcatRepository instituteSubcatRepository;
	
	@Override
	public CareerCategory saveCareerCate(CareerCategory careerCategory) {
		return careerCategoryRepository.save(careerCategory);
	}
	@Override
	public CareerSubcategory saveCareerSubCate(CareerSubcategory careerSubCategory) {
		CareerCategory careercat=findByCategoryId(careerSubCategory.getCareerCategory().getId());
		careerSubCategory.setCareerCategory(careercat);
	  return careerSubCategoryRepository.save(careerSubCategory);
	}
	
	@Override
	public List<CareerCategory> getAllCategories() {
		return careerCategoryRepository.findAll();
	}
	@Override
	public List<CareerCategory> findByStatus(Status active) {
		return careerCategoryRepository.findByStatus(active);
	}
	@Override
	public List<CareerSubcategory> findBySubCatStatus(Status active) {
		return careerSubCategoryRepository.findByStatus(active);
	}
	@Override
	public List<CareerSubcategory> getAllSubCategories() {
		return careerSubCategoryRepository.findAll();
	}
	@Override
	public CareerCategory findByCategoryId(long id) {
		return careerCategoryRepository.findOne(id);
	}
	@Override
	public CareerSubcategory findBySubCategoryId(long id) {
		return careerSubCategoryRepository.findOne(id);
	}
	@Override
	public void updateCategeroy(CareerCategory careerCategory) {
		careerCategoryRepository.save(careerCategory);		
	}
	@Override
	public void updateSubCategeroy(CareerSubcategory careerSubCategory) {
		careerSubCategoryRepository.save(careerSubCategory);		
	}
	@Override
	public List<CareerCategory> findByCategoryName(String catName) {
		return careerCategoryRepository.findByCategoryName(catName);
	}
	@Override
	public List<CareerSubcategory> findBySubCategoryName(String subCatName) {
		return careerSubCategoryRepository.findBySubCategoryName(subCatName);
	}
	@Override
	public void saveCoCreationHub(CoCreationHub coCreationHub) {
       coCreationHubRepository.save(coCreationHub);		
	}
	@Override
	public List<CoCreationHub> findByHubName(String hubName) {
		return coCreationHubRepository.findByhubName(hubName);
	}
	@Override
	public void updateCoCreationHub(CoCreationHub hub) {
		coCreationHubRepository.save(hub);
	}
	@Override
	public SubCategoryDetails saveSubCatDetails(SubCategoryDetails subCatDetails,Long subcateId) {
		CareerSubcategory careerSubCat=findBySubCategoryId(subcateId);
		subCatDetails.setTags(careerSubCat.getCareerCategory().getCategoryName());
		if(subCatDetails.getYoutubeUrl()!=null){
			String[] val=subCatDetails.getYoutubeUrl().split("=", 2);
			subCatDetails.setYoutubeUrl(val[1]);
			subCatDetails.setType("video/mp4");
		}
		subCatDetails.setCareerSubcat(careerSubCat);
		return subCatDetailsRepo.save(subCatDetails);
		
	}
	@Override
	public void saveSubCatRoles(String role,Long subCatId) {
		
		Responsibilities roleAndResp=new Responsibilities();
		CareerSubcategory careerSubCat=findBySubCategoryId(subCatId);
		roleAndResp.setCareerSubCat(careerSubCat);
		roleAndResp.setRoles(role);
		roleAndResp.setStatus(Status.Active);
		rolesAndResponseRepo.save(roleAndResp);
		
	}
	@Override
	public void saveSubCatExamPatterns(String pattern,Long subCatId) {
		ExamPattern examPate=new ExamPattern();
		CareerSubcategory careerSubCat=findBySubCategoryId(subCatId);
		examPate.setCareerSubCat(careerSubCat);
		examPate.setPattern(pattern);
		examPate.setStatus(Status.Active);
		examPatternRepository.save(examPate);
		
	}
	@Override
	public Institutions saveSubCatInsttutions(Institutions institutions) {
		institutions.setStatus(Status.Active);
		return institutionRepository.save(institutions);
		
	}
	@Override
	public List<SubCategoryDetails> getAllSubCatDetails() {
		return subCatDetailsRepo.findAll();
	}
	@Override
	public void updateSubCatDetails(SubCategoryDetails subCatDetails) {
		subCatDetailsRepo.save(subCatDetails);		
	}
	@Override
	public List<SubCategoryDetails> findBySubCatDetailsStatus(Status active) {
		return subCatDetailsRepo.findByStatus(active);
	}
	/*@Override
	public List<Interests> findByInterest(String interest) {
		return interestRepository.findAllByInterest(interest);
	}
	@Override
	public Interests saveInterests(Interests interests) {
		return interestRepository.save(interests);
	}*/
	/*@Override
	public void saveAbilities(String pattern, Long subcatId) {
		ExamPattern examPate=new ExamPattern();
		CareerSubcategory careerSubCat=findBySubCategoryId(subCatId);
		examPate.setCareerSubCat(careerSubCat);
		examPate.setPattern(pattern);
		examPate.setStatus(Status.Active);
		examPatternRepository.save(examPate);		
	}*/
	@Override
	public void saveSubCatSkills(String skill, Long subcatId) {
		Skills skills=new Skills();
		CareerSubcategory careerSubCat=findBySubCategoryId(subcatId);
		skills.setCareerSubCat(careerSubCat);
		skills.setSkill(skill);
		skills.setStatus(Status.Active);
		skills.setCareerSubCat(careerSubCat);
		skillRepository.save(skills);		
	}
	@Override
	public void saveSubCatAbilities(String ability, Long subcatId) {
		Abilities roleAndResp=new Abilities();
		CareerSubcategory careerSubCat=findBySubCategoryId(subcatId);
		roleAndResp.setCareerSubCat(careerSubCat);
		roleAndResp.setAbility(ability);
		roleAndResp.setStatus(Status.Active);
		abilityRepository.save(roleAndResp);		
	}
	@Override
	public DemandColors findColorByDemandColorId(Long color) {
		return demandColorsRepository.findOne(color);
	}
	@Override
	public DemandYears findYearByDemandYearId(Long year) {
		return demandYearsRepository.findOne(year);
	}
	@Override
	public DemandLabels findLabelByDemandLabel(Long label) {
		return demandLabelsRepository.findOne(label);
	}
	
	
	
	@Override
	public SevenSigma findSevenSigmaBySigmaId(Long sigmaId) {
		return sevenSigmaRepository.findOne(sigmaId);
	}
	
	
	
	@Override
	public Institutions getInstituteByUrl(String instituteUrl) {
		return institutionRepository.findByInstituteUrl(instituteUrl);
	}
	@Override
	public void saveListofInstitutions(List<Institutions> excelInstitutions) {
		institutionRepository.save(excelInstitutions);
	}
	
	@Override
	public void saveInstitution(Institutions institution) {
		institutionRepository.save(institution);
	}
	@Override
	public void saveSubcatDetailsYoutubeVideos(List<SubCategoryDetails> youtubeVideos) {
		subCatDetailsRepo.save(youtubeVideos);
	}
	@Override
	public School findBySchoolName(String schoolName) {
		return schoolRepository.findBySchoolName(schoolName);
	}
	@Override
	public AcademicYear findByAcadamicYearName(String acadamicYearName) {
		return academicYearRepository.findByAcadamicYearName(acadamicYearName);
	}
	@Override
	public SchoolTeacher findBySchoolTeacherFirstName(String schoolTeacherFirstName) {
		return schoolTeacherRepository.findByFirstName(schoolTeacherFirstName);
	}
	@Override
	public CareerCategory findByCategouryName(String categoryName) {
		return careerCategoryRepository.findByCategouryName(categoryName);
	}
	@Override
	public CareerSubcategory findByCareerSubCategoryName(String subCatName) {
		return careerSubCategoryRepository.findByCareerSubCategoryName(subCatName);
	}
	@Override
	public DemandYears findDemandYearByYear(String year) {
		return demandYearsRepository.findDemandYearByYear(year);
	}
	@Override
	public DemandColors findDemandColorByColorName(String color) {
		return demandColorsRepository.findDemandColorByColorName(color);
	}
	@Override
	public DemandLabels findDemandLableByLableName(String label) {
		return demandLabelsRepository.findDemandLableByLableName(label);
	}
	@Override
	public void saveListofSigmaDetails(List<SevenSigmaDetails> excelSevenSigmaDetails) {
		sevenSigmaDetailsRepository.save(excelSevenSigmaDetails);
		
	}
	@Override
	public void saveListofNotifications(List<Notification> notifications) {
		notificatioRepository.save(notifications);
	}
	@Override
	public void saveListofInstituteSubcats(List<InstituteSubcat> instituteSubcats) {
		instituteSubcatRepository.save(instituteSubcats);
		
	}
	@Override
	public void saveListofInstitutionCourses(List<InstitutionCourses> excelInstitutionCourse) {
		coursesRepositoty.save(excelInstitutionCourse);
		
	}
	
}
