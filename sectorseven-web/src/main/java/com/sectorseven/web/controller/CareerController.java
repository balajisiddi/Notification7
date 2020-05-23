package com.sectorseven.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sectorseven.model.Enum.Country;
import com.sectorseven.model.Enum.Hub;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.Enum.Trending;
import com.sectorseven.model.Enum.VideoType;
import com.sectorseven.model.common.AddEvent;
import com.sectorseven.model.common.CareerCategory;
import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.CoCreationHub;
import com.sectorseven.model.common.ContributionDocs;
import com.sectorseven.model.common.DemandColors;
import com.sectorseven.model.common.DemandLabels;
import com.sectorseven.model.common.DemandSupply;
import com.sectorseven.model.common.DemandYears;
import com.sectorseven.model.common.InstituteSubcat;
import com.sectorseven.model.common.InstitutionCourses;
import com.sectorseven.model.common.Institutions;
import com.sectorseven.model.common.Notification;
import com.sectorseven.model.common.Scholorships;
import com.sectorseven.model.common.SevenSigma;
import com.sectorseven.model.common.SevenSigmaDetails;
import com.sectorseven.model.common.SubCategoryDetails;
import com.sectorseven.model.common.SuccessPersons;
import com.sectorseven.model.school.AdminExcelModelTemplate;
import com.sectorseven.service.School.SchoolTeacherService;
import com.sectorseven.service.admin.CareerService;
import com.sectorseven.service.admin.MentorService;
import com.sectorseven.service.admin.ParentsService;
import com.sectorseven.service.admin.SevenSigmaService;
import com.sectorseven.service.student.CommonService;
import com.sectorseven.service.student.StudentService;
import com.sectorseven.web.utils.AdminExcelTemplate;
import com.sectorseven.web.utils.ImageUtility;
import com.sectorseven.web.utils.ViewConstants;

@Controller
@RequestMapping("/admin")
public class CareerController {
	@Autowired
	private CareerService careerService;

	@Autowired
	private SevenSigmaService sigmaService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private MentorService mentorService;

	@Autowired
	private SchoolTeacherService schoolTeacherService;

	@Autowired
	private ParentsService parentsService;
	
	@ModelAttribute("submittedContributions")
	public List<ContributionDocs> findallSubmittedContributions() {
		return commonService.findAllSubmittedContributions();
	}

	@ModelAttribute("catList")
	public List<CareerCategory> getCatList() {
		return careerService.findByStatus(Status.Active);
	}

	@ModelAttribute("hubOrNot")
	public List<Hub> hubOrNot() {
		return Arrays.asList(Hub.values());
	}

	@ModelAttribute("trendingOrNot")
	public List<Trending> getTrendingOrNot() {
		return Arrays.asList(Trending.values());
	}

	@ModelAttribute("privateOrNot")
	public List<VideoType> getPrivateOrNot() {
		return Arrays.asList(VideoType.values());
	}

	@ModelAttribute("countries")
	public List<Country> getCountries() {
		return Arrays.asList(Country.values());
	}

	@ModelAttribute("subCatList")   
	public List<CareerSubcategory> getSubCatList() {
		return careerService.findBySubCatStatus(Status.Active);
	}

	@ModelAttribute("subCatDetailsList")
	public List<SubCategoryDetails> getSubCatDetailList() {
		return careerService.findBySubCatDetailsStatus(Status.Active);
	}

	@ModelAttribute("sigmaList")
	public List<SevenSigma> getAllSigmas() {
		return sigmaService.findBySigmaStatus(Status.Active);
	}

	@ModelAttribute("hubList")
	public List<CoCreationHub> getAllHubs() {
		return commonService.findByHubStatus(Status.Active);
	}

	@ModelAttribute("institutionsList")
	public List<Institutions> getAllInstitutions() {
		return commonService.findAllInstitutions();
	}

	@ModelAttribute("years")
	public List<DemandYears> getYears() {
		return commonService.getDemandYears();
	}

	@ModelAttribute("colors")
	public List<DemandColors> getColors() {
		return commonService.getDemandColors();
	}

	@ModelAttribute("labels")
	public List<DemandLabels> getLabels() {
		return commonService.getDemandLabels();
	}

	@RequestMapping(value = "/event/upload", method = RequestMethod.GET)
	public String eventCreation(Model model) {
		model.addAttribute("eventObject", new AddEvent());
		return "admin/event/upload";
	}

	@RequestMapping(value = "/event/upload", method = RequestMethod.POST)
	public String eventCreation(@ModelAttribute("eventObject") AddEvent event, BindingResult result, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
		event.setStatus(Status.Active);
		commonService.saveEvent(event);
		redirectAttributes.addFlashAttribute("msg", "Event added successfully");
		return "redirect:/admin/event/upload";
	}

	@RequestMapping(value = "/career/create", method = RequestMethod.GET)
	public String createCategory(Model model) {
		model.addAttribute("careerObject", new CareerCategory());
		return "admin/career/create"; 
	}

	@RequestMapping(value = "/career/create", method = RequestMethod.POST)
	public String createShool(@ModelAttribute("careerObject") CareerCategory career, BindingResult result, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// model.addAttribute("schoolObject", new School());
		List<CareerCategory> existingCareerList = new ArrayList<>();
		if (career.getId() == null) {
			existingCareerList = careerService.findByCategoryName(career.getCategoryName());
		} else {
			existingCareerList = careerService.findByCategoryName(career.getCategoryName());
		}
		if (existingCareerList.isEmpty()) {
			career.setStatus(Status.Active);
			if (career.getId() == null) {
				if (career.getCategoryImg() != null) {
					CareerCategory careercat = careerService.saveCareerCate(career);
					MultipartFile file = career.getCategoryImg();
					MultipartFile file1 = career.getIntrstImg();

					String[] pathAndName;
					String[] pathAndName1;

					ImageUtility imu = new ImageUtility();
					try {
						pathAndName = imu.getAbsoluteImagePathWithFileName(file, careercat.getId(),
								ViewConstants.CATEGORY_IMAGES);
						pathAndName1 = imu.getAbsoluteImagePathWithFileName(file1, careercat.getId(),
								ViewConstants.INTERESTS_IMAGES);
						career.setCategoryImagName(pathAndName[1]);
						career.setCategoryImagPath(pathAndName[0]);
						career.setIntrstImagName(pathAndName1[1]);
						career.setIntrstImagPath(pathAndName1[0]);
						career.setType(file.getContentType());
						career.setIntrstType(file1.getContentType());
						careerService.saveCareerCate(career);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				redirectAttributes.addFlashAttribute("msg", "Category Added successfully..!!\n");
			} else {
//				school.setStatus(Status.Active);
				careerService.updateCategeroy(career);
				redirectAttributes.addFlashAttribute("msg", "Category updated successfully..!!");
			}
		} else {
			for (CareerCategory existingCategory : existingCareerList) {
				if (existingCategory.getCategoryName().equalsIgnoreCase(career.getCategoryName())) {
					model.addAttribute("errorMsg", "Category already taken..!");
					model.addAttribute("careerObject", career);
					return "admin/school/create";
				}
			}
		}
		return "redirect:/admin/career/create";
	}

	@RequestMapping("/career/list")
	public String getCategories(Model model) {
		List<CareerCategory> careers = careerService.getAllCategories();
		model.addAttribute("careersList", careers);
		return "admin/career/list";

	}

	@RequestMapping("/career/{careerID}/update")
	public String updateCategory(@PathVariable long careerID, Model model) {
		CareerCategory career = careerService.findByCategoryId(careerID);
		model.addAttribute("careerObj", career);
		return "admin/career/update";

	}

	@RequestMapping(value = "/subcareer/create", method = RequestMethod.GET)
	public String createSubCategory(Model model) {
		model.addAttribute("subCareerObject", new CareerSubcategory());
		return "admin/subcareer/create";
	}

	@RequestMapping(value = "/subcareer/create", method = RequestMethod.POST)
	public String createSubCategory(@ModelAttribute("subCareerObject") CareerSubcategory subCareer,
			BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// model.addAttribute("schoolObject", new School());
		List<CareerSubcategory> existingsubCareerList = new ArrayList<>();
		if (subCareer.getId() == null) {
			existingsubCareerList = careerService.findBySubCategoryName(subCareer.getSubCategoryName());
		} else {
			existingsubCareerList = careerService.findBySubCategoryName(subCareer.getSubCategoryName());
		}
		if (existingsubCareerList.isEmpty()) {
			if (subCareer.getId() == null) {
				subCareer.setStatus(Status.Active);
				CareerSubcategory careerSubCat = careerService.saveCareerSubCate(subCareer);
				Notification ntf = new Notification();
				ntf.setSubcategory(careerSubCat);
				ntf.setScreen("subCategory");
				ntf.setAction("careerMain");
				if (careerSubCat.getTrending().name().equals("IsTrending")) {
					ntf.setMessage("do you know that " + careerSubCat.getSubCategoryName()
							+ " has witnessed the highest spike in demand for jobs in 2019? Wanna know more? Click here to browse through our new module "
							+ careerSubCat.getCareerCategory().getCategoryName());
				} else if (careerSubCat.getTrending().name().equals("IsPrevious")) {
					// ntf.setMessage("New Career Added");
					ntf.setMessage("we hope that " + careerSubCat.getSubCategoryName()
							+ " has helped you learn a lot. We are here with a new course "
							+ careerSubCat.getCareerCategory().getCategoryName() + ". Click here to get started.");

				}
				saveNotification(ntf);
				if (subCareer.getSubCategoryImg() != null && subCareer.getLandingImg() != null
						&& subCareer.getAmITheOneImg() != null) {
					MultipartFile file = careerSubCat.getSubCategoryImg();
					String[] pathAndName;
					MultipartFile file2 = careerSubCat.getLandingImg();
					String[] pathAndName2;
					MultipartFile file3 = careerSubCat.getAmITheOneImg();
					String[] pathAndName3;
					ImageUtility imu = new ImageUtility();
					try {
						pathAndName = imu.getAbsoluteImagePathWithFileName(file, careerSubCat.getId(),
								ViewConstants.SUB_CATEGORY_IMAGES);
						pathAndName2 = imu.getAbsoluteImagePathWithFileName(file2, careerSubCat.getId(),
								ViewConstants.LANDING_IMAGES);
						pathAndName3 = imu.getAbsoluteImagePathWithFileName(file3, careerSubCat.getId(),
								ViewConstants.AM_I_THE_ONE_IMAGES);
						careerSubCat.setSubcategoryImgName(pathAndName[1]);
						careerSubCat.setSubcategoryImgPath(pathAndName[0]);
						careerSubCat.setType(file.getContentType());
						careerSubCat.setLandingImgName(pathAndName2[1]);
						careerSubCat.setLandingImgPath(pathAndName2[0]);
						careerSubCat.setLandingType(file2.getContentType());
						careerSubCat.setAmITheOneImgName(pathAndName3[1]);
						careerSubCat.setAmITheOneImgPath(pathAndName3[0]);
						careerSubCat.setLandingType(file3.getContentType());
						careerService.saveCareerSubCate(careerSubCat);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

				redirectAttributes.addFlashAttribute("msg", "SubCategory Added successfully..!!\n");
			}
		} else {
			for (CareerSubcategory existingSubCategory : existingsubCareerList) {
				if (existingSubCategory.getSubCategoryName().equalsIgnoreCase(subCareer.getSubCategoryName())) {
					model.addAttribute("errorMsg", "SubCategory already taken..!");
					model.addAttribute("subCareerObject", subCareer);
					return "admin/subcareer/create";
				}
			}
		}
		saveRolesForSucat(subCareer.getRoles(), subCareer.getId());
		saveSkillsorSucat(subCareer.getSkills(), subCareer.getId());

		return "redirect:/admin/subcareer/create";
	}

	public void saveAbilitiesForSucat(String abilities, Long subcatId) {
		List<String> myarray = Arrays.asList(abilities.split("[,\\s]+"));
		myarray.stream().forEach(ability -> {
			careerService.saveSubCatAbilities(ability, subcatId);
		});

	}

	public void saveSkillsorSucat(String responsibilities, Long subcatId) {
		List<String> myarray = Arrays.asList(responsibilities.split("[@@@]+"));
		myarray.stream().forEach(responsibility -> {
			careerService.saveSubCatSkills(responsibility, subcatId);
		});
	}

	public void saveRolesForSucat(String roles, Long subcatId) {
		List<String> myarray = Arrays.asList(roles.split("[@@@]+"));
		myarray.stream().forEach(role -> {
			careerService.saveSubCatRoles(role, subcatId);
		});
		// careerService.saveSubCatRoles(roles,skills,abilities,subcatId);

	}

	public void saveExamsForSucat(String patterns, Long subcatId) {
		List<String> myarray = Arrays.asList(patterns.split("[,\\s]+"));
		myarray.stream().forEach(pattern -> {
			careerService.saveSubCatExamPatterns(pattern, subcatId);
		});
	}

	@RequestMapping(value = "/subCareerDetails/create", method = RequestMethod.GET)
	public String createsubCareerDetails(Model model) {
		model.addAttribute("subCareerDetailsObj", new SubCategoryDetails());
		return "admin/subCareerDetails/create";
	}

	@RequestMapping(value = "/subCareerDetails/create", method = RequestMethod.POST)
	public String createsubCareerDetails(@ModelAttribute("subCareerDetailsObj") SubCategoryDetails subCatDetails,
			BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (subCatDetails.getId() == null) {
			if (subCatDetails.getSubCategoryDocument() != null) {
				SubCategoryDetails subCateDetail = new SubCategoryDetails();
				Notification ntf = new Notification();
				List<CommonsMultipartFile> files = subCatDetails.getSubCategoryDocument();
				for (CommonsMultipartFile file : files) {
					SubCategoryDetails subCategoryDetails = new SubCategoryDetails();
					String[] pathAndName;
					SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyy");
					ImageUtility imu = new ImageUtility();
					subCategoryDetails.setDatePublished(sdf.format(new Date()));
					subCategoryDetails.setStatus(Status.Active);
					subCateDetail = careerService.saveSubCatDetails(subCategoryDetails,
							subCatDetails.getCareerSubcat().getId());
					try {
						pathAndName = imu.getAbsoluteImagePathWithFileName(file, subCateDetail.getId(),
								ViewConstants.SUB_CATEGORY_DOCUMENTS);
						subCateDetail.setSubCategoryDocumentName(pathAndName[1]);
						subCateDetail.setSubCategoryDocumentPath(pathAndName[0]);
						careerService.saveSubCatDetails(subCateDetail, subCatDetails.getCareerSubcat().getId());

						ntf.setMediaId(subCateDetail.getId());
						ntf.setScreen("subCategory");
						if (file.getContentType().contains("video")) {
							ntf.setAction("Video");
							ntf.setMessage("New Video Has been Added");
							ntf.setVideoType(VideoType.Private);
						} else if (file.getContentType().contains("audio")) {
							ntf.setAction("Audio");
							ntf.setMessage("New Audio Has been Added");
							ntf.setVideoType(VideoType.Private);
						} else if (file.getContentType().contains("pdf")) {
							subCateDetail.setTitle(pathAndName[1].substring(0, pathAndName[1].length() - 4));
							subCateDetail.setVideoType(VideoType.Private);
							subCateDetail.setType(file.getContentType());
							ntf.setVideoType(VideoType.Private);
						} else if (subCatDetails.getYoutubeUrl() != " ") {
							ntf.setAction("Video");
							ntf.setMessage("New Video Has been Added");
							ntf.setVideoType(VideoType.Youtube);
							String[] val = subCatDetails.getYoutubeUrl().split("=", 2);
							subCateDetail.setYoutubeUrl(val[1]);
							subCateDetail.setType("video/mp4");
							subCateDetail.setThumbnailUrl("http://img.youtube.com/vi/" + val[1] + "/0.jpg");
							ntf.setYoutubeUrl(val[1]);
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					redirectAttributes.addFlashAttribute("msg", "SubCategory Details Added successfully..!!\n");
				}
				ntf.setAction("Pdf");
				ntf.setMessage("It's time to know more about the "
						+ subCateDetail.getCareerSubcat().getSubCategoryName() + ". Read the Pdf right away.");
				saveNotification(ntf);
			}
		} else {
			careerService.updateSubCatDetails(subCatDetails);
			redirectAttributes.addFlashAttribute("msg", "SubCategory Details updated successfully..!!");
		}
		redirectAttributes.addFlashAttribute("msg",
				"SubCategory Details added successfully..!!");
		return "redirect:/admin/subCareerDetails/create";
	}

	@RequestMapping(value = "/subCareerInstitutes/create", method = RequestMethod.GET)
	public String saveInstituesForSucat(Model model) {
		model.addAttribute("instituteObj", new Institutions());

		return "admin/subCareerInstitutes/create";
	}

	@RequestMapping(value = "/subCareerInstitutes/create", method = RequestMethod.POST)
	public String saveInstituesForSucat(@ModelAttribute("instituteObj") Institutions institutions, BindingResult result,
			Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		CareerSubcategory careerSubCat = careerService.findBySubCategoryId(institutions.getCareerSubCat().getId());
		institutions.setCareerSubCat(careerSubCat);
		return "redirect:/admin/subCareerInstitutes/create";
	}

	@RequestMapping(value = "/subCareerScholorships/create", method = RequestMethod.GET)
	public String saveScholorshipsForSucat(Model model) {
		model.addAttribute("scholorshipObj", new Scholorships());
		return "admin/subCareerScholorships";
	}   
      
	@RequestMapping(value = "/subCareerScholorships/create", method = RequestMethod.POST)
	public String saveScholorships(@ModelAttribute("scholorshipObj") Scholorships scholorships, BindingResult result,
			Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		CareerSubcategory careerSubCat = careerService.findBySubCategoryId(130);
		scholorships.setCareerSubCat(careerSubCat);
		scholorships.setScholorshipList("noNeed");
		scholorships.setUrl("noNeed");
		commonService.saveScholorShip(scholorships);
		return "redirect:/admin/subCareerScholorships/create"; 
	}

	@RequestMapping(value = "/subCareerPersons/create", method = RequestMethod.GET)
	public String savePersonsForSucat(Model model) {
		model.addAttribute("personpObj", new SuccessPersons());
		return "admin/subCareerScholorships";
	}

	@RequestMapping(value = "/subCareerPersons/create", method = RequestMethod.POST)
	public String savePersonsForSucat(@ModelAttribute("personpObj") SuccessPersons person, BindingResult result,
			Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		CareerSubcategory careerSubCat = careerService.findBySubCategoryId(person.getCareerSubCat().getId());
		person.setCareerSubCat(careerSubCat);
		commonService.savePerson(person);
		return "admin/subCareerScholorships";
	}

	@RequestMapping("/subCareer/list")
	public String getSubCategoriesList(Model model) {
		List<CareerSubcategory> subCareers = careerService.getAllSubCategories();
		model.addAttribute("subCareersList", subCareers);
		return "admin/subcareer/list";

	}

	@RequestMapping("/subCareer/{subCareerID}/update")
	public String updateSubCategory(@PathVariable long subCareerID, Model model) {
		CareerSubcategory career = careerService.findBySubCategoryId(subCareerID);
		model.addAttribute("suCareerObject", career);
		return "admin/subcareer/update";
	}

	@RequestMapping("/subCareer/{subCareerID}")
	public String getSubCategory(@PathVariable long subCareerID, Model model) {
		CareerSubcategory subCareer = careerService.findBySubCategoryId(subCareerID);
		model.addAttribute("suCareerObject", subCareer);
		return "admin/subcareer/subCategory";

	}

	@RequestMapping(value = "/hub/create", method = RequestMethod.GET)
	public String hubCreation(Model model) {
		model.addAttribute("hubObject", new CoCreationHub());
		return "admin/hub/create";
	}

	@RequestMapping(value = "/hub/create", method = RequestMethod.POST)
	public String hubCreation(@ModelAttribute("hubObject") CoCreationHub hub, BindingResult result, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// model.addAttribute("schoolObject", new School());
		List<CoCreationHub> existinghubList = new ArrayList<>();
		if (hub.getId() == null) {
			existinghubList = careerService.findByHubName(hub.getHubName());
		} else {
			existinghubList = careerService.findByHubName(hub.getHubName());
		}
		if (existinghubList.isEmpty()) {
			if (hub.getId() == null) {
				hub.setStatus(Status.Active);
				careerService.saveCoCreationHub(hub);
				redirectAttributes.addFlashAttribute("msg", "Hub Added successfully..!!\n");
			} else {
//				school.setStatus(Status.Active);
				careerService.updateCoCreationHub(hub);
				redirectAttributes.addFlashAttribute("msg", "Hub updated successfully..!!");
			}
		} else {
			for (CoCreationHub existingHub : existinghubList) {
				if (existingHub.getHubName().equalsIgnoreCase(hub.getHubName())) {
					model.addAttribute("errorMsg", "HubName already taken..!");
					model.addAttribute("hubObject", hub);
					return "admin/hub/create";
				}
			}
		}
		return "redirect:/admin/hub/create";
	}

	@RequestMapping(value = "/sigma/create", method = RequestMethod.GET)
	public String createSigma(Model model) {
		model.addAttribute("sigmaObject", new SevenSigma());
		return "admin/sigma/create";
	}

	@RequestMapping(value = "/sigma/create", method = RequestMethod.POST)
	public String createSigma(@ModelAttribute("sigmaObject") SevenSigma sigma, BindingResult result, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// model.addAttribute("schoolObject", new School());
		List<SevenSigma> existingSigmaList = new ArrayList<>();
		if (sigma.getId() == null) {
			existingSigmaList = sigmaService.findBysigmaName(sigma.getSigmaName());
		} else {
			existingSigmaList = sigmaService.findBysigmaName(sigma.getSigmaName());
		}
		if (existingSigmaList.isEmpty()) {
			if (sigma.getId() == null) {
				if (sigma.getSigmaIcon() != null) {
					sigma.setStatus(Status.Active);
					SevenSigma sigmaa = sigmaService.saveSigmas(sigma);
					MultipartFile file = sigma.getSigmaIcon();
					String[] pathAndName;
					ImageUtility imu = new ImageUtility();
					try {
						pathAndName = imu.getAbsoluteImagePathWithFileName(file, sigmaa.getId(),
								ViewConstants.SIGMA_IMAGES);
						sigma.setSigmaIconName(pathAndName[1]);
						sigma.setSigmaIconPath(pathAndName[0]);
						sigmaa.setType(file.getContentType());
						sigmaService.saveSigmas(sigma);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				redirectAttributes.addFlashAttribute("msg", "Sigma Added successfully..!!\n");
			} else {
//				school.setStatus(Status.Active);
				sigmaService.updateSigma(sigma);
				redirectAttributes.addFlashAttribute("msg", "Sigma updated successfully..!!");
			}
		} else {
			for (SevenSigma sevenSigma : existingSigmaList) {
				if (sevenSigma.getSigmaName().equalsIgnoreCase(sigma.getSigmaName())) {
					model.addAttribute("errorMsg", "Sigma already taken..!");
					model.addAttribute("sigmaObject", sigma);
					return "admin/sigma/create";
				}
			}
		}
		return "redirect:/admin/sigma/create";
	}

	@RequestMapping(value = "/sigmaDetails/create", method = RequestMethod.GET)
	public String createsigmaDetails(Model model) {
		model.addAttribute("sigmaDetailsObj", new SevenSigmaDetails());
		return "admin/sigmaDetails/create";
	}

	@RequestMapping(value = "/sigmaDetails/create", method = RequestMethod.POST)
	public String createsigmaDetails(@ModelAttribute("sigmaDetailsObj") SevenSigmaDetails sigmaDetails,
			BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (sigmaDetails.getId() == null) {
			if (sigmaDetails.getSigmaDocument() != null) {
				List<CommonsMultipartFile> files = sigmaDetails.getSigmaDocument();
				SevenSigmaDetails sigmaDetail = new SevenSigmaDetails();
				Notification ntf = new Notification();
				for (CommonsMultipartFile file : files) {
					SevenSigmaDetails sevenSigmaDetails = new SevenSigmaDetails();
					sevenSigmaDetails.setStatus(Status.Active);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					sevenSigmaDetails.setDatePublished(sdf.format(new Date()));
					sigmaDetail = sigmaService.saveSigmaDetails(sevenSigmaDetails,
							sigmaDetails.getSevenSigma().getId());
					String[] pathAndName;
					ImageUtility imu = new ImageUtility();
					try {
						if (file != null) {
							pathAndName = imu.getAbsoluteImagePathWithFileName(file, sigmaDetail.getId(),
									ViewConstants.SIGMA_SUB_DOCUMENTS);
							sigmaDetail.setSigmaDocumentName(pathAndName[1]);
							sigmaDetail.setSigmaDocumentPath(pathAndName[0]);
							sigmaDetail.setName(file.getOriginalFilename());
							sigmaDetail.setType(file.getContentType());
							sigmaDetail.setVideoType(VideoType.Private);
							ntf.setMediaId(sigmaDetail.getId());
							ntf.setScreen("sigma");
							if (file.getContentType().contains("video")) {
								ntf.setAction("Video");
								ntf.setMessage("New Video Has been Added");
							} else if (file.getContentType().contains("mp3")) {
								ntf.setAction("mp3");
								ntf.setMessage("New Audio Has been Added");
							} else if (file.getContentType().contains("pdf")) {
								sigmaDetail.setTitle(pathAndName[1].substring(0, pathAndName[1].length() - 4));
								sigmaDetail.setVideoType(sigmaDetails.getVideoType());
							} else if (file.getContentType().contains("octet-stream")) {
								ntf.setAction("Video");
								ntf.setMessage("New Video Has been Added");
								String[] val = sigmaDetails.getYoutubeUrl().split("=", 2);
								sigmaDetail.setYoutubeUrl(val[1]);
								ntf.setYoutubeUrl(val[1]);
								sigmaDetail.setType("video/mp4");
							}
							sigmaService.saveSigmaDetails(sigmaDetail, sigmaDetails.getSevenSigma().getId());
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				ntf.setAction("Pdf");
				ntf.setMessage("It's time to know more about the " + sigmaDetail.getSevenSigma().getSigmaName()
						+ ". Read the Pdf right away.");
				saveNotification(ntf);
			}
			redirectAttributes.addFlashAttribute("msg", "Sigma Details Added successfully..!!\n");
		} else {
			sigmaService.updateSigmaDetails(sigmaDetails);
			redirectAttributes.addFlashAttribute("msg", "Sigma Details updated successfully..!!");
		}

		return "redirect:/admin/sigmaDetails/create";
	}

	private void saveNotification(Notification ntf) {
		ntf.setStatus(Status.Active);
		commonService.saveNotification(ntf);

	}

	@RequestMapping(value = "/saveDemand/create", method = RequestMethod.GET)
	public String saveDemand(Model model) {
		model.addAttribute("demandObj", new DemandSupply());
		return "admin/saveDemand";
	}

	@RequestMapping(value = "/saveDemand/create", method = RequestMethod.POST)
	public String saveDemand(@ModelAttribute("demandObj") DemandSupply demand, BindingResult result, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		CareerSubcategory careerSubCat = careerService.findBySubCategoryId(demand.getCareerSubCat().getId());
		demand.setCareerSubCat(careerSubCat);
		DemandColors color = careerService.findColorByDemandColorId(demand.getColor().getId());
		demand.setColor(color);
		DemandYears year = careerService.findYearByDemandYearId(demand.getYear().getId());
		demand.setYear(year);
		DemandLabels label = careerService.findLabelByDemandLabel(demand.getLabel().getId());
		demand.setLabel(label);
		commonService.saveDemand(demand);
		return "admin/saveDemand";
	}

	@RequestMapping(value = "/course/create", method = RequestMethod.GET)
	public String saveCourse(Model model) {
		model.addAttribute("coursesObj", new InstitutionCourses());
		return "admin/course";
	}

	@RequestMapping(value = "/course/create", method = RequestMethod.POST)
	public String saveCourse(@ModelAttribute("coursesObj") InstitutionCourses course, BindingResult result, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Institutions institution = commonService.getInstituteById(course.getInstitution().getId());
		course.setInstitution(institution);
		course.setStatus(Status.Active);
		commonService.saveCourses(course);
		return "admin/course";
	}

	@RequestMapping(value = "/sigmaDetailsExcel/upload", method = RequestMethod.GET)
	public String sigmaDetailsExcel(Model model) {
		model.addAttribute("sigmadetailUploadObj", new AdminExcelModelTemplate());
		return "admin/sigmaDetailsExcel/upload";
	}

	@RequestMapping(value = "/sigmaDetailsExcel/upload", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public String sigmaDetailsExcel(@ModelAttribute("sigmadetailUploadObj") AdminExcelModelTemplate sevenSigmaDetails,
			Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
		ImageUtility imageUtility = new ImageUtility();
		String location = imageUtility.getAbsoluteImagePath(sevenSigmaDetails.getDocument(), 1,
				ViewConstants.STUDENT_DATAUPLOAD_LOCATION);
		File uploadedFile = new File(ViewConstants.SAVE_LOCATION + "/" + location);
		SevenSigma sevenSigma = careerService.findSevenSigmaBySigmaId(sevenSigmaDetails.getSigma().getId());
		Map<String, Object> sigmaDetailsList = AdminExcelTemplate.readExcelFile(uploadedFile.listFiles()[0], sevenSigma,
				sevenSigmaDetails.getVideoType());
		List<String> errors = (List<String>) sigmaDetailsList.get("errors");
		if (!sigmaDetailsList.isEmpty() && errors.size() == 0) {
			List<SevenSigmaDetails> excelSevenSigmaDetails = (List<SevenSigmaDetails>) sigmaDetailsList
					.get("sigmaDetails");
			List<Notification> notifications = (List<Notification>) sigmaDetailsList.get("notifications");
			if (!excelSevenSigmaDetails.isEmpty()) {
				careerService.saveListofSigmaDetails(excelSevenSigmaDetails);
			}
			if (!notifications.isEmpty()) {
				careerService.saveListofNotifications(notifications);
			}
			redirectAttributes.addFlashAttribute("msg", "Uploaded details Successfully");
		} else {
			redirectAttributes.addFlashAttribute("errorList", errors);
			return "redirect:/admin/sigmaDetailsExcel/upload";
		}
		return "redirect:/admin/sigmaDetailsExcel/upload";
	}

	@RequestMapping(value = "/instituteExcel/upload", method = RequestMethod.GET)
	public String instituteExcelUpload(Model model) {
		model.addAttribute("instituteExcelObj", new AdminExcelModelTemplate());
		return "admin/instituteExcel/upload";
	}

	@RequestMapping(value = "/instituteExcel/upload", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public String instituteExcelUpload(
			@ModelAttribute("instituteExcelObj") AdminExcelModelTemplate institutionsDataUpload, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
		ImageUtility imageUtility = new ImageUtility();
		String location = imageUtility.getAbsoluteImagePath(institutionsDataUpload.getDocument(), 1,
				ViewConstants.STUDENT_DATAUPLOAD_LOCATION);
		File uploadedFile = new File(ViewConstants.SAVE_LOCATION + "/" + location);
		CareerSubcategory careerSubcategory;
		try {
			careerSubcategory = careerService.findBySubCategoryId(institutionsDataUpload.getSubCategoury().getId());
			Map<String, Object> institutionsList = AdminExcelTemplate.readExcelFile(uploadedFile.listFiles()[0],
					careerSubcategory, institutionsDataUpload.getCountry(), careerService);
			List<String> errors = (List<String>) institutionsList.get("errors");
			if (!institutionsList.isEmpty() && errors.size() == 0) {
				List<Institutions> excelInstitutions = (List<Institutions>) institutionsList.get("institutes");
				List<InstituteSubcat> instituteSubcats = (List<InstituteSubcat>) institutionsList
						.get("instituteSubcats");
				if (!excelInstitutions.isEmpty()) {
					careerService.saveListofInstitutions(excelInstitutions);
				}
				if (!instituteSubcats.isEmpty()) {
					careerService.saveListofInstituteSubcats(instituteSubcats);
				}
				redirectAttributes.addFlashAttribute("msg", "Uploaded details Successfully");
			} else {
				redirectAttributes.addFlashAttribute("errorList", errors);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/admin/instituteExcel/upload";
	}

	@RequestMapping(value = "/instituteCoursesExcel/upload", method = RequestMethod.GET)
	public String instituteCoursesExcelUpload(Model model) {
		model.addAttribute("instituteCoursesExcelObj", new AdminExcelModelTemplate());
		return "admin/instituteCourseExcel/upload";
	}

	@RequestMapping(value = "/instituteCoursesExcel/upload", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public String instituteCoursesExcelUpload(
			@ModelAttribute("instituteCoursesExcelObj") AdminExcelModelTemplate institutionCoursesDataUpload,
			Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
		ImageUtility imageUtility = new ImageUtility();
		String location = imageUtility.getAbsoluteImagePath(institutionCoursesDataUpload.getDocument(), 1,
				ViewConstants.STUDENT_DATAUPLOAD_LOCATION);
		File uploadedFile = new File(ViewConstants.SAVE_LOCATION + "/" + location);
		CareerSubcategory careerSubcategory;
		try {
			careerSubcategory = careerService
					.findBySubCategoryId(institutionCoursesDataUpload.getSubCategoury().getId());
			Institutions institution = commonService
					.getInstituteById(institutionCoursesDataUpload.getInstitution().getId());
			Map<String, Object> institutionCoursesList = AdminExcelTemplate.readExcelFile(uploadedFile.listFiles()[0],
					careerSubcategory, institution);
			List<String> errors = (List<String>) institutionCoursesList.get("errors");
			if (!institutionCoursesList.isEmpty() && errors.size() == 0) {
				List<InstitutionCourses> excelInstitutionCourse = (List<InstitutionCourses>) institutionCoursesList
						.get("instituteCourses");
				if (!excelInstitutionCourse.isEmpty()) {
					careerService.saveListofInstitutionCourses(excelInstitutionCourse);
				}
				redirectAttributes.addFlashAttribute("msg", "Uploaded details Successfully");
			} else {
				redirectAttributes.addFlashAttribute("errorList", errors);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/admin/instituteCoursesExcel/upload";
	}

	@RequestMapping(value = "/institutesCoursesExcel/upload", method = RequestMethod.GET)
	public String institutesCoursesExcelUpload(Model model) {
		model.addAttribute("institutionsCoursesDataUploadObj", new AdminExcelModelTemplate());
		return "admin/institutesCoursesExcel/upload";
	}

	@RequestMapping(value = "/institutesCoursesExcel/upload", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public String institutesCoursesExcelUpload(
			@ModelAttribute("institutionsCoursesDataUploadObj") AdminExcelModelTemplate institutionsCoursesDataUpload,
			Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
		ImageUtility imageUtility = new ImageUtility();
		String location = imageUtility.getAbsoluteImagePath(institutionsCoursesDataUpload.getDocument(), 1,
				ViewConstants.STUDENT_DATAUPLOAD_LOCATION);
		File uploadedFile = new File(ViewConstants.SAVE_LOCATION + "/" + location);
		CareerSubcategory careerSubcategory;
		try {
			careerSubcategory = careerService
					.findBySubCategoryId(institutionsCoursesDataUpload.getSubCategoury().getId());
			Institutions institution = new Institutions();
			Map<String, Object> institutionsAndCoursesList = AdminExcelTemplate.readExcelFile(
					uploadedFile.listFiles()[0], careerSubcategory, institutionsCoursesDataUpload.getCountry(),
					institution, careerService);
			List<String> errors = (List<String>) institutionsAndCoursesList.get("errors");
			if (!institutionsAndCoursesList.isEmpty() && errors.size() == 0) {
				List<Institutions> excelInstitutions = (List<Institutions>) institutionsAndCoursesList
						.get("institutes");
				List<InstituteSubcat> instituteSubcats = (List<InstituteSubcat>) institutionsAndCoursesList
						.get("instituteSubcats");
				List<InstitutionCourses> excelInstitutionCourse = (List<InstitutionCourses>) institutionsAndCoursesList
						.get("instituteCourses");
				if (!excelInstitutions.isEmpty()) {
					careerService.saveListofInstitutions(excelInstitutions);
				}
				if (!instituteSubcats.isEmpty()) {
					careerService.saveListofInstituteSubcats(instituteSubcats);
				}
				if (!excelInstitutionCourse.isEmpty()) {
					careerService.saveListofInstitutionCourses(excelInstitutionCourse);
				}
				redirectAttributes.addFlashAttribute("msg", "Uploaded details Successfully");
			} else {
				redirectAttributes.addFlashAttribute("errorList", errors);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/admin/institutesCoursesExcel/upload";
	}

	@RequestMapping(value = "/subcatDetailYoutubeURLs/upload", method = RequestMethod.GET)
	public String subcatDetailsExcel(Model model) {
		model.addAttribute("subcatDetailsExcelObj", new AdminExcelModelTemplate());
		return "admin/subcatDetailsYoutubeUrlExcel/upload";
	}

	@RequestMapping(value = "/subcatDetailYoutubeURLs/upload", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public String subcatDetailsExcel(
			@ModelAttribute("subcatDetailsExcelObj") AdminExcelModelTemplate subcatDetailsExcel, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
		ImageUtility imageUtility = new ImageUtility();
		String location = imageUtility.getAbsoluteImagePath(subcatDetailsExcel.getDocument(), 1,
				ViewConstants.STUDENT_DATAUPLOAD_LOCATION);
		File uploadedFile = new File(ViewConstants.SAVE_LOCATION + "/" + location);
		CareerSubcategory careerSubcategory;
		try {
			careerSubcategory = careerService.findBySubCategoryId(subcatDetailsExcel.getSubCategoury().getId());
			Map<String, Object> youtubeURLsList = AdminExcelTemplate.readExcelFile(uploadedFile.listFiles()[0],
					careerSubcategory);
			List<String> errors = (List<String>) youtubeURLsList.get("errors");
			Notification notification = new Notification();
			if (!youtubeURLsList.isEmpty() && errors.size() == 0) {
				List<SubCategoryDetails> youtubeVideos = (List<SubCategoryDetails>) youtubeURLsList
						.get("youtubeVideos");
				// List<Notification> notifications = (List<Notification>)
				// youtubeURLsList.get("notifications");
				if (!youtubeVideos.isEmpty()) {
					careerService.saveSubcatDetailsYoutubeVideos(youtubeVideos);
				}
				notification.setStatus(Status.Active);
				notification.setAction("careerMain");
				notification.setScreen("subCategory");
				notification.setSubcategory(careerSubcategory);
				notification.setMessage("It's time to know more about the " + careerSubcategory.getSubCategoryName()
						+ ". Watch the videos right away.");
				commonService.saveNotification(notification);
				redirectAttributes.addFlashAttribute("msg", "Youtube details added successfully");
			} else {
				redirectAttributes.addFlashAttribute("errorList", errors);
				return "redirect:/admin/subcatDetailYoutubeURLs/upload";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/admin/subcatDetailYoutubeURLs/upload";
	}

	// for subcategory excel upload
	@RequestMapping(value = "/subcatExcel/upload", method = RequestMethod.GET)
	public String subcatExcel(Model model) {
		model.addAttribute("SubCategoryExcelObj", new AdminExcelModelTemplate());
		return "admin/subcatExcel/upload";
	}

	@RequestMapping(value = "/subcatExcel/upload", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public String subcatExcel(@ModelAttribute("SubCategoryExcelObj") AdminExcelModelTemplate subCategoryExcel,
			Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
		Notification ntf = new Notification();
		CareerCategory careerCategory;
		ImageUtility imageUtility = new ImageUtility();
		String location = imageUtility.getAbsoluteImagePath(subCategoryExcel.getDocument(), 1,
				ViewConstants.STUDENT_DATAUPLOAD_LOCATION);
		File uploadedFile = new File(ViewConstants.SAVE_LOCATION + "/" + location);
		String[] iconPathAndName;
		CommonsMultipartFile subcatIcon = subCategoryExcel.getSubCategoryIcon();
		String[] landingImagePathAndName;
		CommonsMultipartFile landingImage = subCategoryExcel.getLandingImage();
		CareerSubcategory careerSubCat = new CareerSubcategory();

		try {
			Map<String, Object> careerCategoryList = AdminExcelTemplate.readExcelFile(uploadedFile.listFiles()[0],
					careerSubCat, careerSubCat.getTrending());
			List<String> errors = (List<String>) careerCategoryList.get("errors");
			if (!careerCategoryList.isEmpty() && errors.size() == 0) {
				careerSubCat = (CareerSubcategory) careerCategoryList.get("subcatobj");
				careerSubCat.setStatus(Status.Active);
				careerCategory = careerService.findByCategoryId(subCategoryExcel.getCareerCategory().getId());
				careerSubCat.setCareerCategory(careerCategory);
				careerSubCat = careerService.saveCareerSubCate(careerSubCat);
				saveRolesForSucat(careerSubCat.getRoles(), careerSubCat.getId());
				saveSkillsorSucat(careerSubCat.getSkills(), careerSubCat.getId());
				iconPathAndName = imageUtility.getAbsoluteImagePathWithFileName(subcatIcon, careerSubCat.getId(),
						ViewConstants.SUB_CATEGORY_IMAGES);
				landingImagePathAndName = imageUtility.getAbsoluteImagePathWithFileName(landingImage,
						careerSubCat.getId(), ViewConstants.LANDING_IMAGES);
				careerSubCat.setSubcategoryImgName(iconPathAndName[1]);
				careerSubCat.setSubcategoryImgPath(iconPathAndName[0]);
				careerSubCat.setType(subcatIcon.getContentType());
				careerSubCat.setLandingImgName(landingImagePathAndName[1]);
				careerSubCat.setLandingImgPath(landingImagePathAndName[0]);
				careerSubCat.setLandingType(landingImage.getContentType());
				ntf.setSubcategory(careerSubCat);
				ntf.setScreen("subCategory");
				ntf.setAction("careerMain");
				if (careerSubCat.getTrending().name().equals("IsTrending")) {
					ntf.setMessage("do you know that " + careerSubCat.getSubCategoryName()
							+ " has witnessed the highest spike in demand for jobs in 2019? Wanna know more? Click here to browse through our new module "
							+ careerSubCat.getCareerCategory().getCategoryName());
				} else if (careerSubCat.getTrending().name().equals("IsPrevious")) {
					ntf.setMessage("we hope that " + careerSubCat.getSubCategoryName()
							+ " has helped you learn a lot. We are here with a new course "
							+ careerSubCat.getCareerCategory().getCategoryName() + ". Click here to get started.");
				}
				careerService.saveCareerSubCate(careerSubCat);
				saveNotification(ntf);
				redirectAttributes.addFlashAttribute("msg", "Uploaded details Successfully");
			} else {
				redirectAttributes.addFlashAttribute("errorList", errors);
				return "redirect:/admin/subcatExcel/upload";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		redirectAttributes.addFlashAttribute("msg", "subCategory Created Successfully");
		return "redirect:/admin/subcatExcel/upload";
	}

	@RequestMapping(value = "/studentDataExcel/upload", method = RequestMethod.GET)
	public String studentDataExcel(Model model) {
		model.addAttribute("studentDataUploadObj", new AdminExcelModelTemplate());
		return "admin/studentDataExcel/upload";
	}

	@RequestMapping(value = "/studentDataExcel/upload", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public String studentDataExcel(@ModelAttribute("studentDataUploadObj") AdminExcelModelTemplate studentDataUploadObj,
			Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
		ImageUtility imageUtility = new ImageUtility();
		String location = imageUtility.getAbsoluteImagePath(studentDataUploadObj.getDocument(), 1,
				ViewConstants.STUDENT_DATAUPLOAD_LOCATION);
		File uploadedFile = new File(ViewConstants.SAVE_LOCATION + "/" + location);
		Map<String, Object> errorsList = AdminExcelTemplate.readExcelFile(uploadedFile.listFiles()[0], careerService,
				studentService, parentsService);
		List<String> errors = (List<String>) errorsList.get("errors");
		if (errors.size() == 0) {
			redirectAttributes.addFlashAttribute("msg", errorsList.get("count") + "students created.");
		} else {
			redirectAttributes.addFlashAttribute("errorList", errors);
		}
		return "redirect:/admin/studentDataExcel/upload";
	}

	@RequestMapping(value = "/mentorDataExcel/upload", method = RequestMethod.GET)
	public String mentorDataExcel(Model model) {
		model.addAttribute("studentDataUploadObj", new AdminExcelModelTemplate());
		return "admin/mentorDataExcel/upload";
	}

	@RequestMapping(value = "/mentorDataExcel/upload", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public String mentorDataExcel(@ModelAttribute("studentDataUploadObj") AdminExcelModelTemplate studentDataUploadObj,
			Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
		ImageUtility imageUtility = new ImageUtility();
		String location = imageUtility.getAbsoluteImagePath(studentDataUploadObj.getDocument(), 1,
				ViewConstants.STUDENT_DATAUPLOAD_LOCATION);
		File uploadedFile = new File(ViewConstants.SAVE_LOCATION + "/" + location);
		Map<String, Object> errorsList = AdminExcelTemplate.readExcelFile(uploadedFile.listFiles()[0], careerService,
				mentorService);
		List<String> errors = (List<String>) errorsList.get("errors");
		if (errors.size() == 0) {
			redirectAttributes.addFlashAttribute("msg", errorsList.get("count") + " Mentors Created Successfully");
		} else {
			redirectAttributes.addFlashAttribute("errorList", errors);
		}
		return "redirect:/admin/mentorDataExcel/upload";
	}

	@RequestMapping(value = "/teacherDataExcel/upload", method = RequestMethod.GET)
	public String teacherDataExcel(Model model) {
		model.addAttribute("studentDataUploadObj", new AdminExcelModelTemplate());
		return "admin/teacherDataExcel/upload";
	}

	@RequestMapping(value = "/teacherDataExcel/upload", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public String teacherDataExcel(@ModelAttribute("studentDataUploadObj") AdminExcelModelTemplate studentDataUploadObj,
			Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
		ImageUtility imageUtility = new ImageUtility();
		String location = imageUtility.getAbsoluteImagePath(studentDataUploadObj.getDocument(), 1,
				ViewConstants.STUDENT_DATAUPLOAD_LOCATION);
		File uploadedFile = new File(ViewConstants.SAVE_LOCATION + "/" + location);
		Map<String, Object> errorsList = AdminExcelTemplate.readExcelFile(uploadedFile.listFiles()[0], careerService,
				schoolTeacherService);
		List<String> errors = (List<String>) errorsList.get("errors");
		if (errors.size() == 0) {
			redirectAttributes.addFlashAttribute("msg",
					errorsList.get("count") + " School Teachers created successfully");
		} else {
			redirectAttributes.addFlashAttribute("errorList", errors);
		}
		return "redirect:/admin/teacherDataExcel/upload";
	}

	@RequestMapping(value = "/successfulPersonalityDataExcel/upload", method = RequestMethod.GET)
	public String successfulPersonalityDataExcel(Model model) {
		model.addAttribute("studentDataUploadObj", new AdminExcelModelTemplate());
		return "admin/successfulPersonalityDataExcel/upload";
	}

	@RequestMapping(value = "/successfulPersonalityDataExcel/upload", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public String successfulPersonalityDataExcel(
			@ModelAttribute("studentDataUploadObj") AdminExcelModelTemplate studentDataUploadObj, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
		ImageUtility imageUtility = new ImageUtility();
		String location = imageUtility.getAbsoluteImagePath(studentDataUploadObj.getDocument(), 1,
				ViewConstants.STUDENT_DATAUPLOAD_LOCATION);
		File uploadedFile = new File(ViewConstants.SAVE_LOCATION + "/" + location);
		Map<String, Object> errorsList = AdminExcelTemplate.readExcelFile(uploadedFile.listFiles()[0], careerService,
				commonService);
		List<String> errors = (List<String>) errorsList.get("errors");
		if (errors.size() == 0) {
			redirectAttributes.addFlashAttribute("msg", "successfulPersonalities inserted successfully");

		} else {
			redirectAttributes.addFlashAttribute("errorList", errors);
		}
		return "redirect:/admin/successfulPersonalityDataExcel/upload";
	}

	@RequestMapping(value = "/demandAndSupplyDataExcel/upload", method = RequestMethod.GET)
	public String demandAndSupplyDataExcel(Model model) {
		model.addAttribute("subcatDetailsExcelObj", new AdminExcelModelTemplate());
		return "admin/demandAndSupplyDataExcel/upload";
	}

	@RequestMapping(value = "/demandAndSupplyDataExcel/upload", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public String demandAndSupplyDataExcel(
			@ModelAttribute("subcatDetailsExcelObj") AdminExcelModelTemplate demandAndSupplyobj, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
		ImageUtility imageUtility = new ImageUtility();
		CareerSubcategory careerSubcategory = careerService
				.findBySubCategoryId(demandAndSupplyobj.getSubCategoury().getId());
		String location = imageUtility.getAbsoluteImagePath(demandAndSupplyobj.getDocument(), 1,
				ViewConstants.STUDENT_DATAUPLOAD_LOCATION);
		File uploadedFile = new File(ViewConstants.SAVE_LOCATION + "/" + location);
		Map<String, Object> errorsList = AdminExcelTemplate.readExcelFile(uploadedFile.listFiles()[0], careerService,
				commonService, careerSubcategory);
		List<String> errors = (List<String>) errorsList.get("errors");
		if (errors.size() == 0) {
			redirectAttributes.addFlashAttribute("msg", "D and S created successfully");
		} else {
			redirectAttributes.addFlashAttribute("errorList", errors);
		}
		redirectAttributes.addFlashAttribute("msg", "Congractulations! You have created One Profession Successfully");
		return "redirect:/admin/demandAndSupplyDataExcel/upload";
	}

	@RequestMapping(value = "/demandAndSupplyContent/upload", method = RequestMethod.GET)
	public String demandAndSupplyContentUpload(Model model) {
		model.addAttribute("subcatObj", new AdminExcelModelTemplate());
		return "admin/demandAndSupplyContent/upload";
	}

	@RequestMapping(value = "/demandAndSupplyContent/upload", method = RequestMethod.POST)
	public String demandAndSupplyContentUpload(
			@ModelAttribute("subcatObj") AdminExcelModelTemplate demandAndSupplyContent, Model model,
			HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
		CareerSubcategory careerSubcategory = careerService
				.findBySubCategoryId(demandAndSupplyContent.getSubCategoury().getId());
		careerSubcategory.setSalaryDescription(demandAndSupplyContent.getSalaryDescription());
		careerSubcategory.setJobsDescription(demandAndSupplyContent.getJobsDescription());
		careerService.saveCareerSubCate(careerSubcategory);
		redirectAttributes.addFlashAttribute("msg", "D and S content Created Successfully!!");
		return "redirect:/admin/demandAndSupplyContent/upload";
	}

	@RequestMapping(value = "/eventsDataExcel/upload", method = RequestMethod.GET)
	public String eventsDataExcel(Model model) {
		model.addAttribute("eventsDataObj", new AdminExcelModelTemplate());
		return "admin/eventsDataExcel/upload";
	}

	@RequestMapping(value = "/eventsDataExcel/upload", method = RequestMethod.POST)
	@SuppressWarnings("unchecked")
	public String eventsDataExcel(@ModelAttribute("eventsDataObj") AdminExcelModelTemplate eventsDataObj, Model model,
			RedirectAttributes redirectAttributes) throws IOException {
		ImageUtility imageUtility = new ImageUtility();
		String location = imageUtility.getAbsoluteImagePath(eventsDataObj.getDocument(), 1,
				ViewConstants.STUDENT_DATAUPLOAD_LOCATION);
		File uploadedFile = new File(ViewConstants.SAVE_LOCATION + "/" + location);
		Map<String, Object> errorsList = AdminExcelTemplate.readExcelFile(uploadedFile.listFiles()[0], commonService);
		List<String> errors = (List<String>) errorsList.get("errors");
		if (errors.size() == 0) {
			redirectAttributes.addFlashAttribute("msg", errorsList.get("count") + " event created successfully");
		} else {
			redirectAttributes.addFlashAttribute("errorList", errors);
		}
		return "redirect:/admin/eventsDataExcel/upload";
	}

	// for subcareer single youtube url upload
	@RequestMapping(value = "/singleSubcareerYoutubeUrl/upload", method = RequestMethod.GET)
	public String singleSubcareerYoutubeUrlUpload(Model model) throws IOException {
		model.addAttribute("subcatDetailObj", new SubCategoryDetails());
		return "admin/singleSubcareerYoutubeUrl/upload";
	}

	@RequestMapping(value = "/singleSubcareerYoutubeUrl/upload", method = RequestMethod.POST)
	public String singleSubcareerYoutubeUrlUpload(@ModelAttribute("subcatDetailObj") SubCategoryDetails subcatDetailObj,
			RedirectAttributes redirectAttributes) throws IOException {
		subcatDetailObj.setStatus(Status.Active);
		SubCategoryDetails subCateDetail = careerService.saveSubCatDetails(subcatDetailObj,
				subcatDetailObj.getCareerSubcat().getId());
		subCateDetail.setThumbnailUrl("http://img.youtube.com/vi/" + subcatDetailObj.getYoutubeUrl() + "/0.jpg");
		subCateDetail.setVideoType(VideoType.Youtube);
		Notification ntf = new Notification();
		ntf.setMediaId(subCateDetail.getId());
		ntf.setScreen("subCategory");
		ntf.setAction("Video");
		ntf.setMessage("New Video Has been Added");
		ntf.setVideoType(VideoType.Youtube);
		ntf.setYoutubeUrl(subcatDetailObj.getYoutubeUrl());
		saveNotification(ntf);
		redirectAttributes.addFlashAttribute("msg", "Video Uploaded Successfully");
		return "redirect:/admin/singleSubcareerYoutubeUrl/upload";
	}

	// for sigma single youtube url upload
	@RequestMapping(value = "/singleSigmaYoutubeUrl/upload", method = RequestMethod.GET)
	public String singleSigmaYoutubeUrlUpload(Model model) throws IOException {
		model.addAttribute("SevenSigmaDetailsObj", new SevenSigmaDetails());
		return "admin/singleSigmaYoutubeUrl/upload";
	}

	@RequestMapping(value = "/singleSigmaYoutubeUrl/upload", method = RequestMethod.POST)
	public String singleSigmaYoutubeUrlUpload(
			@ModelAttribute("SevenSigmaDetailsObj") SevenSigmaDetails SevenSigmaDetailsObj,
			RedirectAttributes redirectAttributes) throws IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		SevenSigma sevenSigma = careerService.findSevenSigmaBySigmaId(SevenSigmaDetailsObj.getSevenSigma().getId());
		SevenSigmaDetailsObj.setStatus(Status.Active);
		List<SevenSigmaDetails> listsigmaDetais = new ArrayList<SevenSigmaDetails>();
		String[] val = SevenSigmaDetailsObj.getYoutubeUrl().split("=", 2);
		SevenSigmaDetailsObj.setSevenSigma(sevenSigma);
		SevenSigmaDetailsObj.setYoutubeUrl(val[1]);
		SevenSigmaDetailsObj.setType("video/mp4");
		SevenSigmaDetailsObj.setDatePublished(sdf.format(new Date()));
		SevenSigmaDetailsObj.setAuthorName("SectorSeven");
		SevenSigmaDetailsObj.setVideoType(VideoType.Youtube);
		listsigmaDetais.add(SevenSigmaDetailsObj);
		careerService.saveListofSigmaDetails(listsigmaDetais);
		;
		Notification notification = new Notification();
		notification.setStatus(Status.Active);
		notification.setYoutubeUrl(val[1]);
		notification.setAction("Video");
		notification.setScreen("sigma");
		notification.setMessage("New Video Has been Added");
		saveNotification(notification);
		redirectAttributes.addFlashAttribute("msg", "Video Uploaded Successfully");
		return "redirect:/admin/singleSigmaYoutubeUrl/upload";
	}

}
