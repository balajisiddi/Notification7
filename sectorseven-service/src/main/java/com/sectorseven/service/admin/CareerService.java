package com.sectorseven.service.admin;

import java.util.List;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.admin.AcademicYear;
import com.sectorseven.model.common.CareerCategory;
import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.CoCreationHub;
import com.sectorseven.model.common.DemandColors;
import com.sectorseven.model.common.DemandLabels;
import com.sectorseven.model.common.DemandYears;
import com.sectorseven.model.common.InstituteSubcat;
import com.sectorseven.model.common.InstitutionCourses;
import com.sectorseven.model.common.Institutions;
import com.sectorseven.model.common.Notification;
import com.sectorseven.model.common.SevenSigma;
import com.sectorseven.model.common.SevenSigmaDetails;
import com.sectorseven.model.common.SubCategoryDetails;
import com.sectorseven.model.school.School;
import com.sectorseven.model.school.SchoolTeacher;

public interface CareerService {
	
	
	CareerCategory saveCareerCate(CareerCategory careerCategory);
	
	CareerSubcategory saveCareerSubCate(CareerSubcategory careerSubCategory);
	
	List<CareerCategory> getAllCategories();
	
	List<CareerSubcategory> getAllSubCategories();
	
	CareerCategory findByCategoryId(long id);
	
	CareerSubcategory findBySubCategoryId(long id);
	
	List<CareerCategory> findByStatus(Status active);
	
	void updateCategeroy(CareerCategory careerCategory);
	
	void updateSubCategeroy(CareerSubcategory careerSubCategory);
	
	List<CareerCategory> findByCategoryName(String catName);
	
	List<CareerSubcategory> findBySubCategoryName(String subCatName);
	
	SubCategoryDetails saveSubCatDetails(SubCategoryDetails subCatDetails,Long subcatId);
	
	List<SubCategoryDetails> getAllSubCatDetails();
	
	void updateSubCatDetails(SubCategoryDetails subCatDetails);
	
	void saveCoCreationHub(CoCreationHub coCreationHub);

	List<CoCreationHub> findByHubName(String hubName);

	void updateCoCreationHub(CoCreationHub hub);

	List<CareerSubcategory> findBySubCatStatus(Status active);

	List<SubCategoryDetails> findBySubCatDetailsStatus(Status active);

	void saveSubCatRoles(String string, Long subcatId);
	
	void saveSubCatExamPatterns(String pattern,Long subCatId);

	Institutions saveSubCatInsttutions(Institutions institutions);

	/*List<Interests> findByInterest(String interest);

	Interests saveInterests(Interests interests);*/


	void saveSubCatAbilities(String ability, Long subcatId);

	void saveSubCatSkills(String skills, Long subcatId);

	DemandColors findColorByDemandColorId(Long id);

	DemandYears findYearByDemandYearId(Long id);

	DemandLabels findLabelByDemandLabel(Long id);

	void saveListofSigmaDetails(List<SevenSigmaDetails> excelSevenSigmaDetails);
	
	SevenSigma findSevenSigmaBySigmaId(Long id);
	
	void saveListofNotifications(List<Notification> notifications);
	
	Institutions getInstituteByUrl(String instituteUrl);
	
	void saveListofInstitutions(List<Institutions> excelInstitutions);
	
	void saveListofInstituteSubcats(List<InstituteSubcat> instituteSubcats);
	
	void saveListofInstitutionCourses(List<InstitutionCourses> excelInstitutionCourse);
	
	void saveInstitution(Institutions institution);
	
	void saveSubcatDetailsYoutubeVideos(List<SubCategoryDetails> youtubeVideos);
	
	School findBySchoolName(String schoolName);
	
	AcademicYear findByAcadamicYearName(String acadamicYearName);
	
	SchoolTeacher findBySchoolTeacherFirstName(String schoolTeacherFirstName);
	
	CareerCategory findByCategouryName(String categoryName);
	
	CareerSubcategory findByCareerSubCategoryName(String subCatName);
	
	DemandYears findDemandYearByYear(String year);
	
	DemandColors findDemandColorByColorName(String color);
	
	DemandLabels findDemandLableByLableName(String label);
	

}
