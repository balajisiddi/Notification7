package com.sectorseven.web.controller.student;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sectorseven.model.Enum.AdminAcceptance;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.Contribution;
import com.sectorseven.model.common.ContributionDocs;
import com.sectorseven.model.common.MentorFollowers;
import com.sectorseven.model.common.StudentQueries;
import com.sectorseven.model.school.School;
import com.sectorseven.model.school.SchoolTeacher;
import com.sectorseven.model.school.Student;
import com.sectorseven.model.school.UserInterests;
import com.sectorseven.service.School.SchoolService;
import com.sectorseven.service.School.SchoolTeacherService;
import com.sectorseven.service.admin.AuthorityService;
import com.sectorseven.service.student.CommonService;
import com.sectorseven.service.student.StudentService;
import com.sectorseven.web.utils.ImageUtility;
import com.sectorseven.web.utils.ViewConstants;

@Controller
@RequestMapping("/student")
public class StudentDashboardController {

	 @Autowired
	 private StudentService studentService;	
	 
	 @Autowired
	 private CommonService commonService;	
	
	 @Autowired
	 private AuthorityService authorityService;
	 
	 @Autowired
	 private SchoolTeacherService schoolTeacherService;
	 
	 @Autowired
	 private SchoolService schoolService;
	 
	 @RequestMapping(value="/schoolTeacherList/{schoolId}", method= RequestMethod.GET)
	 @ResponseBody
	    public List<SchoolTeacher> getSchoolTeacherList(@PathVariable long schoolId,Model model,RedirectAttributes redirectAttributes){
			School school=schoolService.findById(schoolId);
			model.addAttribute("studentData", new Student());
	        List<SchoolTeacher> schoolTeacherList= schoolTeacherService.findByStatusAndSchool(Status.Active,school);
	        model.addAttribute("schoolTeacherList", schoolTeacherList);
	        return schoolTeacherList;
	    }
	 
	 private Student currentUser()  throws Exception{
		 Student student = null;
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        if (principal instanceof User) {
	            User user = (User) principal;
	            student = studentService.findAdministratorByUsername(user.getUsername());
	        }
	        return student;
	    }
	
	/*@RequestMapping(value="/dashboard", method= RequestMethod.GET)
	public String showDashboard(Model model,RedirectAttributes redirectAttributes,HttpServletResponse response) throws Exception {
		Student  userDetails = currentUser();
		model.addAttribute("currentUser", userDetails);
		 Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		String strDate= formatter.format(date);  
	    List<AddEvent> upcomingEvents = commonService.findAllUpComingEvents(strDate);
        model.addAttribute("upcomingEvents", upcomingEvents);
        List<AddEvent> events = commonService.findAllEvents();
        model.addAttribute("events", events);
        List<AddEvent> previousEvents = commonService.findAllPreviousEvents(strDate);
        model.addAttribute("previousEvents", previousEvents);
		redirectAttributes.addFlashAttribute("msg", "Got Details successfully..!!");
		return "student/dashboard";
	}*/
	/*@RequestMapping(value="/getEvents", method= RequestMethod.GET)
	public String getEvents(Model model,RedirectAttributes redirectAttributes) throws Exception{
		Date date = new Date();  
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
		String strDate= formatter.format(date);  
	    List<AddEvent> upcomingEvents = commonService.findAllUpComingEvents(strDate);
        model.addAttribute("upcomingEvents", upcomingEvents);
        List<AddEvent> events = commonService.findAllEvents();
        model.addAttribute("events", events);
        List<AddEvent> previousEvents = commonService.findAllPreviousEvents(strDate);
        model.addAttribute("previousEvents", previousEvents);
        return "student/events";
	}*/
	/*@RequestMapping(value="/profile", method= RequestMethod.GET)
	public String studentProfile(Model model,RedirectAttributes redirectAttributes)  throws Exception{
		Student  userDetails = currentUser();
		List<UserInterests> userInterests = studentService.findInterestsByUserIdAndAuthority(userDetails.getId(), userDetails.getAuthority());
		model.addAttribute("student", userDetails);
		model.addAttribute("interests", userInterests);
		model.addAttribute("parentt", userDetails.getParents());
		return "student/profile";
	}*/

	
	@RequestMapping("/getAllMentors")
	public String getAllMentorsByInterst(Model model,RedirectAttributes redirectAttributes
			) throws Exception{
		Student  userDetails = currentUser();
		model.addAttribute("userId", userDetails.getId());
		model.addAttribute("userType", userDetails.getAuthority());
		model.addAttribute("headerFlag", true);
		return "student/mentors";
	}
	
	@RequestMapping(value="/mentorDetails", method= RequestMethod.POST)
	public String getAllMentorDetails(@ModelAttribute("mentorId") long mentorId,
			Model model,RedirectAttributes redirectAttributes)
			throws Exception{
	 //  Mentor mentorDetails=commonService.getMentorDetails(mentorId);
	   // model.addAttribute("mentor", mentorDetails);
	    Student  userDetails = currentUser();
		model.addAttribute("userId", userDetails.getId());
		model.addAttribute("userType", userDetails.getAuthority());
		model.addAttribute("mentorId", mentorId);
		redirectAttributes.addFlashAttribute("msg", "Got Mentor Details successfully..!!");
		return "student/mentorDetails";
	}
	
	/*@RequestMapping("/trendingCareers")
	public String getAllTrendingCareers(Model model,RedirectAttributes redirectAttributes) {
		List<CareerSubcategory> trendingCareers=commonService.getAllTrendingCareers();
			model.addAttribute("trendingCareers", trendingCareers);
			redirectAttributes.addFlashAttribute("msg", "Got Trending Careers successfully..!!");
      		return "student/trendCareers";
	}*/
	
	/*@RequestMapping(value="/contribution", method= RequestMethod.GET)
	public String contribution(Model model) {
		model.addAttribute("contributionObj", new Contribution());
		return "student/contribution";
	}

	@RequestMapping(value="/contribution", method= RequestMethod.POST)
	public String contribution(@ModelAttribute("contributionObj") Contribution contribution,Model model,RedirectAttributes redirectAttributes) 
			throws Exception{
		Student  userDetails = currentUser();
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(userDetails.getAuthority());
		contribution.setAuthority(authority);
		contribution.setUser(userDetails.getId());
		contribution.setAcceptance(AdminAcceptance.Submitted);
		Contribution contributionn=studentService.findAllContributionByAuthorityAndUser(authority,userDetails.getId());
		if(contributionn == null){
			Contribution contributions=studentService.saveContribution(contribution);
		}
		List<CommonsMultipartFile> file = contribution.getContDocs();
		for(CommonsMultipartFile doc: file){
		  ContributionDocs contDocs=new ContributionDocs();
			contDocs.setUser(userDetails.getId());
			contDocs.setAuthority(authority);
		    contDocs.setStatus(Status.Active);
			String[] pathAndName;
			ImageUtility imu = new ImageUtility();
			ContributionDocs conttDocc=studentService.saveContributionDocs(contDocs,contributionn.getId());
			try {
				pathAndName = imu.getAbsoluteImagePathWithFileName(doc, conttDocc.getId(),
						ViewConstants.CONTRIBUTION_DOCUMENTS);
				conttDocc.setContDocName(pathAndName[1]);
				conttDocc.setContDocPath(pathAndName[0]);
				studentService.saveContributionDocs(conttDocc,contributionn.getId());
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		
		redirectAttributes.addFlashAttribute("msg", "Contribution Submitted successfully..!!");
	  	return "student/contribution";
	}
*/	@RequestMapping(value="/askmentor", method= RequestMethod.GET)
	public String askMentor(Model model
			) throws Exception {
		Student student=currentUser();
		model.addAttribute("userId", student.getId());
		model.addAttribute("userType", student.getAuthority());
		model.addAttribute("headerFlag", true);

		return "student/askmentor";
	}
	
	
/*@RequestMapping(value="/askmentor", method= RequestMethod.POST)
	public String askMentor(@ModelAttribute("askmentorObj") StudentQueries askMentor,Model model,RedirectAttributes redirectAttributes)throws Exception {
		CommonsMultipartFile file = askMentor.getQuestionImage();
		Student student=currentUser();
		askMentor.setStudent(student);
		StudentQueries askMentord=commonService.saveAskMentor(askMentor);
		String[] pathAndName;
		ImageUtility imu = new ImageUtility();
		try {
			pathAndName = imu.getAbsoluteImagePathWithFileName(file, askMentord.getId(),
					ViewConstants.ASKMENTOR_IMAGES);
			askMentor.setStatus(Status.Inactive);
			askMentor.setAttachmentName(pathAndName[1]);
			askMentor.setAttachmentPath(pathAndName[0]);
//			askMentor.setAskBase64Img("base64image");
			commonService.saveAskMentor(askMentor);
		} catch (IOException e) {
			e.printStackTrace();
		}
		redirectAttributes.addFlashAttribute("msg", "You requested successfully..!!");
	  	return "student/askmentor";
	}
*/@RequestMapping("/requestMentor")
public String saveMentorFollower(@ModelAttribute("mentorId") long mentorId, Model model,RedirectAttributes redirectAttributes)  throws Exception{
	MentorFollowers followers =new MentorFollowers();
	Student student= currentUser();
	followers.setStudent(student);
	followers.setStatus(Status.Inactive);
	commonService.saveMentorFollowerFromService(followers,mentorId);
	redirectAttributes.addFlashAttribute("msg", "Requested mentor Successfully...");
	return "student/mentors";
}



/*	@RequestMapping(value="/askmentor", method= RequestMethod.GET)
	public String askMentor(Model model) {
		model.addAttribute("askmentorObj", new StudentQueries());
		return "student/askmentor";
	}

	@RequestMapping(value="/askmentor", method= RequestMethod.POST)
	public String askMentor(@ModelAttribute("askmentorObj") StudentQueries askMentor,Model model,RedirectAttributes redirectAttributes) {
		askMentor.setStatus(Status.Active);
		StudentQueries askmentor=commonService.saveAskMentor(askMentor);
		CommonsMultipartFile file = askMentor.getQuestionImage();
		String[] pathAndName;
		ImageUtility imu = new ImageUtility();
		try {
			pathAndName = imu.getAbsoluteImagePathWithFileName(file, askmentor.getId(),
					ViewConstants.ASKMENTOR_IMAGES);
			askmentor.setAttachmentName(pathAndName[1]);
			askmentor.setAttachmentPath(pathAndName[0]);
			commonService.saveAskMentor(askmentor);
		} catch (IOException e) {
			e.printStackTrace();
		}
		redirectAttributes.addFlashAttribute("msg", "Your Commrent successfully..!!");
	  	return "student/askmentor";
	}*/
/*	@RequestMapping(value = "/download/{docId}", method = RequestMethod.GET)
	public String downloadDocument(@PathVariable long docId, HttpServletResponse response,Model model) throws IOException {
		
		SevenSigmaDetails document = sigmaService.findBySigmaDetailsId(docId);
		String path=ViewConstants.SAVE_LOCATION+document.getSigmaDocumentPath()+"/"+document.getSigmaDocumentName();
		File file = new File(path);
		  try (InputStream fileInputStream = new FileInputStream(file);
		      OutputStream output = response.getOutputStream()) {
		    response.reset();
		    response.setContentType("application/octet-stream");
		    response.setContentLength((int) (file.length()));
		    response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
		    IOUtils.copyLarge(fileInputStream, output);
		    output.flush();
		  } catch (IOException e) {
		    throw new IOException("Failed to download file: " + e.getMessage(), e);
		  }
 		return "student/sigmasDetails";
	}
	@RequestMapping(value = "/view/{docId}", method = RequestMethod.GET)
	public String viewDocument(@PathVariable long docId, HttpServletResponse response,Model model) throws IOException {
		SevenSigmaDetails document = sigmaService.findBySigmaDetailsId(docId);
		 String path=ViewConstants.SAVE_LOCATION+document.getSigmaDocumentPath()+"/"+document.getSigmaDocumentName();		
		 File downloadFile = new File(path);
		   FileInputStream inputStream = new FileInputStream(downloadFile);
		   if(path.endsWith(".pdf")){
			   response.setContentType("application/pdf");
		   }
		   if(path.endsWith(".mp4")){
			   response.setContentType("video/mp4");
		   }
		   OutputStream outStream = response.getOutputStream();
		   byte[] buffer = new byte[12000000];
		   int bytesRead = -1;
		   while ((bytesRead = inputStream.read(buffer)) != -1) {
		       outStream.write(buffer, 0, bytesRead);
		   }
		   inputStream.close();
		   outStream.close();
	        return "student/sigmasDetails";
	}
	 @RequestMapping("/getCategories")
	    public String getCareerCategory(Model model)
	    {
	        List<CareerCategory> careerCategories = studentService.findAllCategories();
	        model.addAttribute("careerCategories", careerCategories);
	        return "student/careercategories";
	    }
	    
	    @RequestMapping("/getSubCategories/{catId}")
	    public String getCareerSubCategory(@PathVariable long catId, Model model)
	    {
	        
	        List<CareerSubcategory> careerSubCategories = studentService.findCareerSubcategories(catId);
	        model.addAttribute("careerSubCategories", careerSubCategories);
	        return "student/careersubcategories";
	    }
	    
	    @RequestMapping("/subCategoryDetails/{subCatId}")
	    public String getCareerSubCategoryDetails(@PathVariable long subCatId, Model model)
	    {
	        List<SubCategoryDetails> subcategoryDetails =  studentService.findCareerSubCategoryDetails(subCatId);
	        model.addAttribute("subcatdetails",subcategoryDetails);
	        return "student/careersubcategorydetails";
	        
	    }
	    
	    @RequestMapping("/getAllHubs")
	    public String getCoCreationhubs(Model model)
	    {
	        List<CoCreationHub> coHubs = studentService.findAllCoCreationHubs();
	        model.addAttribute("coHubs", coHubs);
	        return "student/cocreationhub";
	    }
	    @RequestMapping("/getTalksByAutority")
	    public String getStudentTalks(Model model,@RequestParam(value="authority" ,required=false) String authority)
	    {
	        List<Contribution> contributions = studentService.findAllTalksByAutority(authority);
	        model.addAttribute("contributions", contributions);
	        return "common/studentTalks";
	    }
	    @RequestMapping(value="/sigmas", method= RequestMethod.GET)
	public String sevenSigmas(Model model,RedirectAttributes redirectAttributes) {
		List<SevenSigma> sigmas=commonService.getSigmas();
			model.addAttribute("sigmaList", sigmas);
			redirectAttributes.addFlashAttribute("msg", "Got Sigmas successfully..!!");
			return "student/sigmas";
		
	}
	
	@RequestMapping(value="/sigmasDetails/{sigmaId}",method=RequestMethod.GET)
	public String sevenSigmas(@PathVariable long sigmaId,@RequestParam(value="type" ,required=false) String type,  Model model,RedirectAttributes redirectAttributes) {
		List<SevenSigmaDetails> sigmasDetails=commonService.getSigmaDetails(sigmaId,type);
			model.addAttribute("sigmaDetails", sigmasDetails);
			model.addAttribute("sigmaId", sigmaId);
			redirectAttributes.addFlashAttribute("msg", "Got SigmaDetails successfully..!!");
		return "student/sigmasDetails";
	}*/
	    @RequestMapping("/activitylog")
	    public String getActivityLogs(Model model)
	    {
	        return "student/activitylog";
	    }
	    /*@RequestMapping("/askMentor")
	    public String getAskMentor(Model model)
	    {
	        return "student/askMentor";
	    }*/

		@RequestMapping(value="/update", method= RequestMethod.POST)
		public String createStudentData(@ModelAttribute("studentData") Student student, BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
				throws Exception{
			Student existstudent=studentService.findByStudentId(student.getId());
			existstudent.setStatus(Status.Active);
			if(student.getFirstName()!=null){
				existstudent.setFirstName(student.getFirstName());
			}
			if(student.getLastName()!=null){
				existstudent.setLastName(student.getLastName());
			}
			if(student.getEmail()!=null){
				existstudent.setEmail(student.getEmail());
			}
			if(student.getMobile()!=0){
				existstudent.setMobile(student.getMobile());
			}
			 if(existstudent!=null){
				studentService.update(existstudent,existstudent.getId());
				redirectAttributes.addFlashAttribute("msg", "Student Updated successfully..!!");
			}
			return "student/dashboard";
		}
		/*@RequestMapping(value="/updateInterests", method= RequestMethod.POST)
		public String updateStudentInterests(@ModelAttribute("interestsForm") Student student, BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {		 
		 Long id=Long.parseLong(request.getParameter("uid"));
		 String [] interests = request.getParameterValues("5ctgrs");
		 StringBuilder intrs1 = new StringBuilder();
		 for (int i = 0; i < interests.length; i++) {
			intrs1.append(interests[i]);
			intrs1.append(", ");
		}
		student = studentService.findByStudentId(id);
		//student.setInterests("java");
		student.setHobbies("chess");
		studentService.updateStudent(student);
		 
	 	return "student/dashboard";
			
		}*/
		
		
}
