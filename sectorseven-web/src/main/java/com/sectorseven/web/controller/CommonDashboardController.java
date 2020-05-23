package com.sectorseven.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ibm.icu.text.SimpleDateFormat;
import com.sectorseven.model.Enum.AdminAcceptance;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.Enum.Subscribe;
import com.sectorseven.model.Enum.VideoType;
import com.sectorseven.model.admin.AdministratorAuthority;
import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.admin.Mentor;
import com.sectorseven.model.admin.Parents;
import com.sectorseven.model.common.ActivityLog;
import com.sectorseven.model.common.AddEvent;
import com.sectorseven.model.common.CareerCategory;
import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.Contribution;
import com.sectorseven.model.common.ContributionDocs;
import com.sectorseven.model.common.Recommended;
import com.sectorseven.model.common.SubscribedCareers;
import com.sectorseven.model.school.SchoolTeacher;
import com.sectorseven.model.school.Student;
import com.sectorseven.model.school.UserInterests;
import com.sectorseven.req.InterestReq;
import com.sectorseven.resp.CategoryResponse;
import com.sectorseven.resp.InterestResponse;
import com.sectorseven.service.School.SchoolTeacherService;
import com.sectorseven.service.admin.AdministratorAuthorityService;
import com.sectorseven.service.admin.AuthorityService;
import com.sectorseven.service.admin.CareerService;
import com.sectorseven.service.admin.MentorService;
import com.sectorseven.service.admin.ParentsService;
import com.sectorseven.service.student.CommonService;
import com.sectorseven.service.student.StudentService;
import com.sectorseven.web.utils.ImageUtility;
import com.sectorseven.web.utils.ViewConstants;

@Controller
@RequestMapping("/common")
public class CommonDashboardController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private MentorService mentorService;

	@Autowired
	private SchoolTeacherService schoolTeacherService;

	
	 @Autowired
	 private CommonService commonService;	
	 
	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private ParentsService parentService;
	
	@Autowired
	 private CareerService careerService;
	 
	 
	 @Autowired
	 private AdministratorAuthorityService administratorAuthorityService;
	  
	 
	 private AdministratorAuthority currentUser() {
		 AdministratorAuthority org = null;
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        if (principal instanceof User) {
	            User user = (User) principal;
	             org = administratorAuthorityService.findAdministratorAuthorityByUserName(user.getUsername());
	        }
	        return org;
	    }
	

	 @ModelAttribute("subCatList")
		public List<CareerSubcategory> getSubCatList(){
			return careerService.findBySubCatStatus(Status.Active);
		}
	 @ModelAttribute("catList")
		public List<CareerCategory> getCatList(){
			return careerService.findByStatus(Status.Active);
		}
	 
	@RequestMapping(value="/sigmas", method= RequestMethod.GET)
	public String sevenSigmas(Model model,RedirectAttributes redirectAttributes)throws Exception {
		String redire=null;
		AdministratorAuthority org=currentUser();
		model.addAttribute("userId", org.getUser());
		model.addAttribute("userType", org.getUserType());
		model.addAttribute("headerFlag", true);

		if (org.getUserType().equals("ROLE_STUDENT")) {
			redire = "student/sigmas";
		} else if (org.getUserType().equals("ROLE_MENTOR")) {
			redire = "mentor/sigmas";
		}  else if (org.getUserType().equals("ROLE_SCHOOL_TEACHER")) {
			redire = "schoolTeacher/sigmas";
		}
		else if (org.getUserType().equals("ROLE_PARENT")) {
			redire = "parent/sigmas";
		}

		return redire;   
	}
	

	@RequestMapping(value="/sigmasDetails",method=RequestMethod.POST)
	public String sevenSigmasDetails(Model model,@RequestParam(value="sigmaId") Long sigmaId)throws Exception {
		String redire=null;
		AdministratorAuthority org=currentUser();
		model.addAttribute("userId", org.getUser());
		model.addAttribute("userType", org.getUserType());
		model.addAttribute("screenType", "sigma");
		model.addAttribute("sigmaId", sigmaId);

        if(org.getUserType().equals("ROLE_STUDENT")){
        	redire= "student/sigmasDetails";
        }
        else if(org.getUserType().equals("ROLE_MENTOR")){
        	redire= "mentor/sigmasDetails";
        }
        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
        	redire= "schoolTeacher/sigmasDetails";
        }
        else if(org.getUserType().equals("ROLE_PARENT")){
        	redire= "parent/sigmasDetails";
        }
    return redire;
	}	
	
	@ResponseBody
	@RequestMapping(value="/checkMail",method=RequestMethod.GET)
	public String checkEmail(Model model,@RequestParam(value="email") String userEmail)throws Exception {
		String redire=null;
		Long mobile=null;
		AdministratorAuthority org=currentUser();

        if(org.getUserType().equals("ROLE_STUDENT")){
        	Student student=studentService.findByEmail(userEmail);
        	System.out.println(student);
        	if(student!=null) {
        		redire= "true";
        	}
        	else {
        		redire= "false";        		
        	}
        }
        else if(org.getUserType().equals("ROLE_MENTOR")){
        	List<Mentor> mentors= mentorService.findByEmailOrContactNo(userEmail, mobile);
        	if(mentors.size()!=0) {
        		redire= "true";
        	}
        	else
        		return "false";
        }
        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
        	List<SchoolTeacher> schoolTeacher= schoolTeacherService.findByEmailOrContactNo(userEmail, mobile);
        	if(schoolTeacher.size()!=0) {
        		redire= "true";
        	}
        	else
        		return "false";
        } 
        else if(org.getUserType().equals("ROLE_PARENT")){ 
        	List<Parents> parents= parentService.findByFatherMobileOrFatherEmail(mobile, userEmail);
        	if(parents.size()!=0) {
        		redire= "true";
        	}
        	else     
        		return "false";
        }
    return redire;
	}	

		 @RequestMapping("/trending")
	    public String getTrendingCareers(Model model)throws Exception
	    {
			    String redire=null;
				AdministratorAuthority org=currentUser();
				model.addAttribute("userId", org.getUser());
				model.addAttribute("userType", org.getUserType());

		        if(org.getUserType().equals("ROLE_STUDENT")){
		        	redire= "student/trending";
		        }
		        else if(org.getUserType().equals("ROLE_MENTOR")){
		        	redire= "mentor/trending";
		        }
		        else if(org.getUserType().equals("ROLE_PARENT")){
		        	redire= "parent/trending";
		        }
		        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
		        	redire= "schoolTeacher/trending";
		        }
		    return redire;

	    }
	 @RequestMapping(value="/percentages",method = RequestMethod.POST)
	    public String getPercentages(Model model,@RequestParam(value="subCatId") Long subCatId,@RequestParam(value="stId",required=false) Long stId,
	    		@RequestParam(value="scrType",required=false) String scrType,
	    		@RequestParam(value="subScrType",required=false) String subScrType,
	    		@RequestParam(value="subCatName",required=false) String subCatName)throws Exception
	    {
			    String redire=null;
				AdministratorAuthority org=currentUser();
				model.addAttribute("userId", org.getUser());
				model.addAttribute("userType", org.getUserType());
				model.addAttribute("subCatId", subCatId);
				model.addAttribute("stId", stId);
				model.addAttribute("scrType", scrType);
				model.addAttribute("subScrType", subScrType);
				model.addAttribute("subCatName", subCatName);
				System.out.println(subScrType);
		        if(org.getUserType().equals("ROLE_STUDENT")){
		        	redire= "student/history";
		        }
		        else if(org.getUserType().equals("ROLE_MENTOR")){
		        	redire= "mentor/history";
		        }
		        else if(org.getUserType().equals("ROLE_PARENT")){
		        	redire= "parent/history";
		        }
		        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
		        	redire= "schoolTeacher/history";
		        }
		    return redire;

	    }

	 @RequestMapping(value="/videos",method = RequestMethod.POST)
	    public String getRecMore(Model model,@RequestParam(value="subCatId",required=false) Long subCatId,@RequestParam(value="screenType") String screenType,
	    		@RequestParam(value="stId",required=false) Long stId,@RequestParam(value="scrType",required=false) String scrType,
	    		@RequestParam(value="subScrType",required=false) String subScrType)throws Exception
	    {
			  String redire=null;
				AdministratorAuthority org=currentUser();
				model.addAttribute("userId", org.getUser());
				model.addAttribute("userType", org.getUserType());
				model.addAttribute("subCatId", subCatId);
				model.addAttribute("screenType", screenType);
				model.addAttribute("stId", stId);
				model.addAttribute("scrType", scrType);
				model.addAttribute("subScrType", subScrType);

		        if(org.getUserType().equals("ROLE_STUDENT")){
		        	redire= "student/videos";
		        }
		        else if(org.getUserType().equals("ROLE_MENTOR")){
		        	redire= "mentor/videos";
		        }
		        else if(org.getUserType().equals("ROLE_PARENT")){
		        	redire= "parent/videos";
		        }
		        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
		        	redire= "schoolTeacher/videos";
		        }
		    return redire;

	    }
	 @RequestMapping(value="/pdfs",method = RequestMethod.POST)
	    public String getHistoryPdfs(Model model,@RequestParam(value="subCatId",required=false) Long subCatId,@RequestParam(value="screenType") String screenType,
	    		@RequestParam(value="stId") Long stId,@RequestParam(value="scrType",required=false) String scrType,
	    		@RequestParam(value="subScrType",required=false) String subScrType)throws Exception
	    {
			  String redire=null;
				AdministratorAuthority org=currentUser();
				model.addAttribute("userId", org.getUser());
				model.addAttribute("userType", org.getUserType());
				model.addAttribute("subCatId", subCatId);
				model.addAttribute("screenType", screenType);
				model.addAttribute("stId", stId);
				model.addAttribute("scrType", scrType);
				model.addAttribute("subScrType", subScrType);

		        if(org.getUserType().equals("ROLE_STUDENT")){
		        	redire= "student/pdfs";
		        }
		        else if(org.getUserType().equals("ROLE_MENTOR")){
		        	redire= "mentor/pdfs";
		        }
		        else if(org.getUserType().equals("ROLE_PARENT")){
		        	redire= "parent/pdfs";
		        }
		        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
		        	redire= "schoolTeacher/pdfs";
		        }
		    return redire;

	    }
	 @RequestMapping(value="/gethubAddress", method = RequestMethod.POST)
	    public String gethubAddress(Model model,@RequestParam(value="eventId" ,required=false) Long eventId) throws Exception
	    {
	        AdministratorAuthority org = currentUser();
	        String redire = null;
	        model.addAttribute("userId", org.getUser());
			model.addAttribute("userType", org.getUserType());
			AddEvent event=commonService.getEvent(eventId);
			Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(event.getEvent_date());
			 SimpleDateFormat formatte = new SimpleDateFormat("dd"); 
			 SimpleDateFormat formatte1 = new SimpleDateFormat("MMM");  

            String event_day = formatte.format(date1);
            String event_month = formatte1.format(date1);

            event.setEvent_day(event_day);
			event.setEvent_month(event_month);

			model.addAttribute("hubAddress",event);

			if (org.getUserType().equals("ROLE_STUDENT")) {
				redire = "student/hubDetails";
			} else if (org.getUserType().equals("ROLE_MENTOR")) {
				redire = "mentor/hubDetails";
			} else if (org.getUserType().equals("ROLE_PARENT")) {
				redire = "parent/hubDetails";
			} else if (org.getUserType().equals("ROLE_SCHOOL_TEACHER")) {
				redire = "schoolTeacher/hubDetails";
			}
			return redire;
	    }

	    @RequestMapping(value="/gethubMap", method = RequestMethod.POST)
	    public String getCoCreationhubMap(Model model,@RequestParam(value="hubId" ,required=false) Long hubId) throws Exception
	    {
	        AdministratorAuthority org = currentUser();
	        String redire = null;
	        model.addAttribute("userId", org.getUser());
			model.addAttribute("userType", org.getUserType());
			model.addAttribute("hubId", hubId);

			if (org.getUserType().equals("ROLE_STUDENT")) {
				redire = "student/hubMap";
			} else if (org.getUserType().equals("ROLE_MENTOR")) {
				redire = "mentor/hubMap";
			} else if (org.getUserType().equals("ROLE_PARENT")) {
				redire = "parent/hubMap";
			} else if (org.getUserType().equals("ROLE_SCHOOL_TEACHER")) {
				redire = "schoolTeacher/hubMap";
			}
			return redire;
	    }
		    @RequestMapping(value="/talks", method = RequestMethod.POST)
	    public String getStudentTalks(Model model,@RequestParam(value="userType" ,required=false) String talkType) throws Exception
	    {
	        String redire=null;
			AdministratorAuthority org=currentUser();
			 model.addAttribute("userId", org.getUser());
				model.addAttribute("userType", org.getUserType());

       	 	List<Contribution> contributions = commonService.getAllContibutionsForStudents(talkType);
	        if(org.getUserType().equals("ROLE_STUDENT")){
	        	redire= "student/userTalks";
	        }
	        else if(org.getUserType().equals("ROLE_MENTOR")){
	        	redire= "mentor/userTalks";
	        }
	        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
	        	redire= "schoolTeacher/userTalks";
	        }
	        else if(org.getUserType().equals("ROLE_PARENT")){
	        	redire= "parent/userTalks";
	        }
 	        model.addAttribute("contributions", contributions);
 	        model.addAttribute("talkType", talkType);
	    return redire;
	    }
	    
	  
	    @RequestMapping(value="/s7TalksDetails", method = RequestMethod.POST)
	    public String getTalksDetails(Model model,
    		@RequestParam(value = "s7TalksUserId", required = false) Long userId,
    		@RequestParam(value = "s7TalksUserType", required = false) String authority,
			@RequestParam(value = "mediaType", required = false) String type,
			@RequestParam(value = "latest", required = false) Integer latest,
			@RequestParam(value = "limit") Integer limit,
			@RequestParam(value="offset") Integer offset ) throws Exception{
	        String redire=null;
			AdministratorAuthority org=currentUser();
			if(org.getUserType().equals("ROLE_STUDENT")){
	        	redire= "student/s7TalksDetails";
	        }
	        else if(org.getUserType().equals("ROLE_MENTOR")){
	        	redire= "mentor/s7TalksDetails";
	        }
	        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
	        	redire= "schoolTeacher/s7TalksDetails";
	        }
	        else if(org.getUserType().equals("ROLE_PARENT")){
	        	redire= "parent/s7TalksDetails";
	        }
 	        model.addAttribute("s7TalksUserId", userId);
 	        model.addAttribute("s7TalksUserType", authority);
 	       model.addAttribute("userId", userId);
	        model.addAttribute("userType", authority);
 	        model.addAttribute("mediaType", type);
 	        model.addAttribute("latest", latest);
 	        model.addAttribute("limit", limit);
 	        model.addAttribute("offset", offset);  

	    return redire;
	    }
	    
	    @RequestMapping(value="/contribution", method= RequestMethod.GET)
		public String contribution(Model model, RedirectAttributes redirectAttributes) {
			model.addAttribute("contributionObj", new Contribution());
			String redire=null;
			AdministratorAuthority org=currentUser();
			  model.addAttribute("userId", org.getUser());
		      model.addAttribute("userType", org.getUserType());
			if (org.getUserType().equals("ROLE_STUDENT")) {
				redire = "student/contribution";
			} else if (org.getUserType().equals("ROLE_MENTOR")) {
				redire = "mentor/contribution";
			}  else if (org.getUserType().equals("ROLE_SCHOOL_TEACHER")) {
				redire = "schoolTeacher/contribution";
			}
			else if (org.getUserType().equals("ROLE_PARENT")) {
				redire = "parent/contribution";
			}
			return redire;
		}

		@RequestMapping(value="/contribution", method= RequestMethod.POST)
		public String contribution(@ModelAttribute("contributionObj") Contribution contribution, Model model, RedirectAttributes redirectAttributes) 
				throws Exception{
			AdministratorAuthority org=currentUser();
			String redire=null;
			Authority authority= null;
			try {
				ActivityLog al = new ActivityLog();
				al.setAuthority(org.getUserType());
				al.setAction("Before_Published");
				al.setScreen("Before_Published");
				al.setStatus(Status.Active);
				al.setMessage("Thanks for submitting your valuable content to 'S7'. We will verify it and notify you when it is online. Keep learning, keep sharing.");
				CareerSubcategory careerSubcategory= commonService.findSubCategoryBySubCatId(contribution.getSubSubject().getId());
				CareerCategory cc = commonService.findCategoryById(contribution.getSubject().getId());
				contribution.setSubSubject(careerSubcategory);
				al.setSubcategory(careerSubcategory);
				contribution.setSubject(cc);
				if (org.getUserType().equals("ROLE_STUDENT")) {
					Student student = studentService.findAdministratorByUsername(org.getUserName());
					al.setUser(student.getId());
					authority = authorityService.findAdministratorsAuthorityByAuthorityCode(org.getUserType());
					contribution.setAuthority(authority);
					contribution.setUser(student.getId());
					redire = "redirect:/common/contribution";
				} else if (org.getUserType().equals("ROLE_MENTOR")) {
					Mentor mentor = mentorService.findAdministratorByUsername(org.getUserName());
					al.setUser(mentor.getId());
					authority = authorityService.findAdministratorsAuthorityByAuthorityCode(org.getUserType());
					contribution.setAuthority(authority);
					contribution.setUser(mentor.getId());
					redire = "redirect:/common/contribution";
				}  else if (org.getUserType().equals("ROLE_SCHOOL_TEACHER")) {
					SchoolTeacher schoolTeacher = schoolTeacherService.findAdministratorByUsername(org.getUserName());
					al.setUser(schoolTeacher.getId());
					authority = authorityService.findAdministratorsAuthorityByAuthorityCode(org.getUserType());
					contribution.setAuthority(authority);
					contribution.setUser(schoolTeacher.getId());
					redire = "redirect:/common/contribution";
				}
				else if (org.getUserType().equals("ROLE_PARENT")) {
					Parents parents = parentService.findAdministratorByUsername(org.getUserName());
					al.setUser(parents.getId());
					authority = authorityService.findAdministratorsAuthorityByAuthorityCode(org.getUserType());
					contribution.setAuthority(authority);
					contribution.setUser(parents.getId());
					redire = "redirect:/common/contribution";
				}
				contribution.setAcceptance(AdminAcceptance.Submitted);
				contribution= studentService.saveContribution(contribution);
				//files processing
				for (CommonsMultipartFile ContDoc : contribution.getContDocs()) {
					ContributionDocs contDocs = new ContributionDocs();
					contDocs.setAuthority(authority);
					contDocs.setStatus(Status.Active);
					contDocs.setUser(contribution.getUser());
					String[] pathAndName;
					ImageUtility imu = new ImageUtility();
					contDocs = studentService.saveContributionDocs(contDocs, contribution.getId());
					try {
						pathAndName = imu.getAbsoluteImagePathWithFileName(ContDoc, contDocs.getId(),
								ViewConstants.CONTRIBUTION_DOCUMENTS);
						contDocs.setContDocName(pathAndName[1]);
						contDocs.setContDocPath(pathAndName[0]);
						contDocs.setVideoType(VideoType.Private);
//						contDocs.setTitle(pathAndName[1].substring(0, pathAndName[1].length()-4));
						contDocs.setAcceptance(AdminAcceptance.Submitted);
						contDocs.setType(ContDoc.getContentType());
						studentService.saveContributionDocs(contDocs, contribution.getId());
						commonService.saveActivityLog(al);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			catch (Exception e) {
					e.printStackTrace();
			}
			redirectAttributes.addFlashAttribute("msg", "Your files contributed successfully. Yout will get notified once admin accepted them");
			return redire;
		}		

	 
	    @RequestMapping(value="/activitylog",method = RequestMethod.POST)
	    public String getActivityLogs(Model model,@RequestParam(value="scType", required = false) String scType,
	    		@RequestParam(value="stId", required = false) Long stId)
	    {
			AdministratorAuthority org=currentUser();
			String redire = null;
			model.addAttribute("userId", org.getUser());
			model.addAttribute("userType", org.getUserType());
			model.addAttribute("scType", scType);
			model.addAttribute("stId", stId);
			model.addAttribute("headerFlag", true);

			if (org.getUserType().equals("ROLE_STUDENT")) {
				redire = "student/activitylog";
			} else if (org.getUserType().equals("ROLE_MENTOR")) {
				redire = "mentor/activitylog";
			} else if (org.getUserType().equals("ROLE_PARENT")) {
				redire = "parent/activitylog";
			} else if (org.getUserType().equals("ROLE_SCHOOL_TEACHER")) {
				redire = "schoolTeacher/activitylog";
			}
			return redire;
	    }
	    @RequestMapping(value="/notifications",method = RequestMethod.GET)
	    public String getAllNotifications(Model model)
	    {
			AdministratorAuthority org=currentUser();
			String redire = null;
			model.addAttribute("userId", org.getUser());
			model.addAttribute("userType", org.getUserType());

			if (org.getUserType().equals("ROLE_STUDENT")) {
				redire = "student/notifications";
			} else if (org.getUserType().equals("ROLE_MENTOR")) {
				redire = "mentor/notifications";
			} else if (org.getUserType().equals("ROLE_PARENT")) {
				redire = "parent/notifications";
			} else if (org.getUserType().equals("ROLE_SCHOOL_TEACHER")) {
				redire = "schoolTeacher/notifications";
			}
			return redire;
	    }
	    @RequestMapping(value="/s7Talks", method= RequestMethod.GET)
	    public String getS7Talks(Model model)
	    {
	    	String redire=null;
			AdministratorAuthority org=currentUser();
			model.addAttribute("headerFlag", true);
			model.addAttribute("userId", org.getUser());
			model.addAttribute("userType", org.getUserType());

	        if(org.getUserType().equals("ROLE_STUDENT")){
	        	redire= "student/s7Talks";
	        }
	        else if(org.getUserType().equals("ROLE_MENTOR")){
	        	redire= "mentor/s7Talks";
	        }
	        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
	        	redire= "schoolTeacher/s7Talks";
	        }
	        else if(org.getUserType().equals("ROLE_PARENT")){
	        	redire= "parent/s7Talks";
	        }
		    
	    return redire;
	    }
		  @RequestMapping(value="/profile", method= RequestMethod.GET)
			public String studentProfile(Model model,RedirectAttributes redirectAttributes)  throws Exception{
				String redire = null;
    			 AdministratorAuthority org = currentUser();
				model.addAttribute("userId", org.getUser());
				model.addAttribute("userType", org.getUserType());

				if (org.getUserType().equals("ROLE_STUDENT")) {
					redire = "student/profile";
				} else if (org.getUserType().equals("ROLE_MENTOR")) {
					redire = "mentor/profile";
				} else if (org.getUserType().equals("ROLE_PARENT")) {
					redire = "parent/profile";
				} else if (org.getUserType().equals("ROLE_SCHOOL_TEACHER")) {
					redire = "schoolTeacher/profile";
				}
              return redire;
			}
		  @RequestMapping(value="/dashboard", method= RequestMethod.GET)
			public String showDashboard(Model model,RedirectAttributes redirectAttributes,HttpServletResponse response) throws Exception {
				String redire = null;
				AdministratorAuthority org = currentUser();
				model.addAttribute("userId", org.getUser());
				model.addAttribute("userType", org.getUserType());
				model.addAttribute("headerFlag", true);

				List<UserInterests> interests =  commonService.getAllUserInterests(org.getUser(), org.getUserType());
				List<Recommended> recomm=commonService.getAlluserSubcategories(org.getUser(), org.getUserType());
				if(interests.size() == 0 ){
					redire = "common/interests";
				}
				 else if(recomm.size() == 0 && interests.size() > 0 ){
					redire = "common/subInterests";
				}
				if (org.getUserType().equals("ROLE_STUDENT")) {
					Student student = studentService.findByStudentId(org.getUser());
					if(student.getDescription()!=null){
						 if( student.getDescription().isEmpty() && interests.size() > 0 && recomm.size() > 0){
								redire = "common/bio";
							}
							 else if (interests.size() > 0 && recomm.size() > 0 && !student.getDescription().isEmpty()) {
								 redire = "student/dashboard";
						}
					}
					else if(student.getDescription()==null){
						redire = "common/bio";
					}
					
				} else if (org.getUserType().equals("ROLE_MENTOR")) {
					Mentor mentor = mentorService.findById(org.getUser());
					if(mentor.getDescription()!=null){
						 if( mentor.getDescription().isEmpty() && interests.size() > 0 && recomm.size() > 0){
								redire = "common/bio";
							}
							 else if (interests.size() > 0 && recomm.size() > 0 && !mentor.getDescription().isEmpty()) {
								 redire = "mentor/dashboard";
						}
					}
					else if(mentor.getDescription()==null){
						redire = "common/bio";
					}
				} else if (org.getUserType().equals("ROLE_PARENT")) {
					Parents parents = parentService.findParentById(org.getUser());
					interests = commonService.getAllUserInterests(org.getUser(), org.getUserType());
					if(parents.getDescription()!=null){
						 if( parents.getDescription().isEmpty() && interests.size() > 0 && recomm.size() > 0){
								redire = "common/bio";
							}
							 else if (interests.size() > 0 && recomm.size() > 0 && !parents.getDescription().isEmpty()) {
								 redire = "parent/dashboard";
						}
					}
					else if(parents.getDescription()==null){
						redire = "common/bio";
					}
				} else if (org.getUserType().equals("ROLE_SCHOOL_TEACHER")) {
					SchoolTeacher schoolTeacher = schoolTeacherService.findById(org.getUser());
					interests = commonService.getAllUserInterests(org.getUser(), org.getUserType());
					if(schoolTeacher.getDescription()!=null){
						 if( schoolTeacher.getDescription().isEmpty() && interests.size() > 0 && recomm.size() > 0){
								redire = "common/bio";
							}
							 else if (interests.size() > 0 && recomm.size() > 0 && !schoolTeacher.getDescription().isEmpty()) {
								 redire = "schoolTeacher/dashboard";
						}
					}
					else if(schoolTeacher.getDescription()==null){
						redire = "common/bio";
					}
				}
              return redire;
			}
			@RequestMapping(value="/getEvents", method= RequestMethod.POST)
			public String getEvents(Model model,RedirectAttributes redirectAttributes) throws Exception{
				AdministratorAuthority org = currentUser();
				/*Date date = new Date();  
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
				String strDate= formatter.format(date);  
			    List<AddEvent> upcomingEvents = commonService.findAllUpComingEvents(strDate);
		        List<AddEvent> events = commonService.findAllEvents();
		        List<AddEvent> previousEvents = commonService.findAllPreviousEvents(strDate);
		    	upcomingEvents.stream().map(
						event->{
							try {
								Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(event.getEvent_date());
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
						}
						).collect(Collectors.toList());
		        events.stream().map(
						event->{
						    try {
								Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(event.getEvent_date());
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
						}
						).collect(Collectors.toList());
				
		        previousEvents.stream().map(
						event->{
							try {
								Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(event.getEvent_date());
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
						}
						).collect(Collectors.toList());
				
		        model.addAttribute("upcomingEvents", upcomingEvents);
		        model.addAttribute("events", events);
		        model.addAttribute("previousEvents", previousEvents);*/
		        model.addAttribute("userId", org.getUser());
				model.addAttribute("userType", org.getUserType());
		        
		        String redire=null;
		        if(org.getUserType().equals("ROLE_STUDENT")){
		        	redire= "student/events";
		        }
		        else if(org.getUserType().equals("ROLE_MENTOR")){
		        	redire= "mentor/events";
		        }
		        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
		        	redire= "schoolTeacher/events";
		        }
		        else if(org.getUserType().equals("ROLE_PARENT")){
		        	redire= "parent/events";
		        }
			    
		    return redire;
			}
			
			@RequestMapping(value="/getCategories")
		    public String getCareerCategory(Model model)throws Exception
		    {
				String redire=null;
				AdministratorAuthority org=currentUser();
				   model.addAttribute("userId", org.getUser());
					model.addAttribute("userType", org.getUserType());
					model.addAttribute("headerFlag", true);

		        if(org.getUserType().equals("ROLE_STUDENT")){
		        	redire= "student/careercategories";
		        }
		        else if(org.getUserType().equals("ROLE_MENTOR")){
		        	redire= "mentor/careercategories";
		        }
		        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
		        	redire= "schoolTeacher/careercategories";
		        }
		        else if(org.getUserType().equals("ROLE_PARENT")){
		        	redire= "parent/careercategories";
		        }
			    return redire;
		    }


			@RequestMapping(value="/subCategories",method= RequestMethod.POST)
		    public String careerSubCategory(@ModelAttribute("catId") long catId, Model model, RedirectAttributes redirectAttributes) throws Exception
		    {
				String redire = null;
				AdministratorAuthority org = currentUser();
				if (org.getUserType().equals("ROLE_STUDENT")) {
					redire = "student/careersubcategories";
				} else if (org.getUserType().equals("ROLE_MENTOR")) {
					redire = "mentor/careersubcategories";
				} else if (org.getUserType().equals("ROLE_PARENT")) {
					redire = "parent/careersubcategories";
				} else if (org.getUserType().equals("ROLE_SCHOOL_TEACHER")) {
					redire = "schoolTeacher/careersubcategories";
				}
				model.addAttribute("catId", catId);
				  model.addAttribute("userId", org.getUser());
					model.addAttribute("userType", org.getUserType());

				return redire;
		    }	    
			
			
			// for contribution
			@RequestMapping(value="/getSubCategories/{catId}", method= RequestMethod.GET)
			@ResponseBody
			public List<CareerSubcategory> getCareerSubCategory(@PathVariable long catId, Model model, RedirectAttributes redirectAttributes)
					throws Exception {
				List<CareerSubcategory> careerSubCategories = studentService.findCareerSubcategories(catId);
				return careerSubCategories;
			}
				 @RequestMapping(value="/getAllHubs", method= RequestMethod.GET)
		    public String getCoCreationhubs(Model model) throws Exception
		    {
		        String redire=null;
				AdministratorAuthority org=currentUser();
				model.addAttribute("userId", org.getUser());
				model.addAttribute("userType", org.getUserType());

		        if(org.getUserType().equals("ROLE_STUDENT")){
		        	redire= "student/cocreationhub";
		        }
		        else if(org.getUserType().equals("ROLE_MENTOR")){
		        	redire= "mentor/cocreationhub";
		        }
		        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
		        	redire= "schoolTeacher/cocreationhub";
		        }
		        else if(org.getUserType().equals("ROLE_PARENT")){
		        	redire= "parent/cocreationhub";
		        }
			    
		    return redire;
		    }
	 
	 @RequestMapping("/studentInfo")
	    public String getStudentInfo(Model model,@RequestParam(value="screenType") String screenType)throws Exception
	    {
			  String redire=null;
				AdministratorAuthority org=currentUser();
				model.addAttribute("userId", org.getUser());
				model.addAttribute("userType", org.getUserType());
				model.addAttribute("screenType", screenType);
				model.addAttribute("headerFlag", true);

		         if(org.getUserType().equals("ROLE_MENTOR")){
		        	redire= "mentor/mentorStudents";
		        }
		        return redire;

	    }
	 @RequestMapping("/studentDetails")
	    public String getStudentDetails(Model model,@RequestParam(value="stId") Long stId)throws Exception
	    {
			  String redire=null;
				AdministratorAuthority org=currentUser();
				model.addAttribute("userId", org.getUser());
				model.addAttribute("stId", stId);

				model.addAttribute("userType", org.getUserType());
		         if(org.getUserType().equals("ROLE_MENTOR")){
		        	redire= "mentor/studentDetails";
		        }
		         if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
			        	redire= "schoolTeacher/studentDetails";
			        }
		        return redire;
	    }
	 @RequestMapping("/childDetails")
	    public String getChildDetails(Model model)throws Exception
	    {
			  String redire=null;
				AdministratorAuthority org=currentUser();
				model.addAttribute("userId", org.getUser());
				model.addAttribute("userType", org.getUserType());

		         if(org.getUserType().equals("ROLE_PARENT")){
		        	redire= "parent/childDetails";
		        }
		        return redire;
	    }
	 @RequestMapping("/studentFootPrints")
	    public String getstudentFootPrints(Model model,@RequestParam(value="screenType") String screenType)throws Exception
	    {
			  String redire=null;
				AdministratorAuthority org=currentUser();
				model.addAttribute("userId", org.getUser());
				model.addAttribute("userType", org.getUserType());
				model.addAttribute("screenType", screenType);

		         if(org.getUserType().equals("ROLE_MENTOR")){
		        	redire= "mentor/studentFootPrints";
		        }
		        return redire;

	    }
	 @RequestMapping("/askStudent")
	    public String getAskStudent(Model model)throws Exception
	    {
			  String redire=null;
				AdministratorAuthority org=currentUser();
				model.addAttribute("userId", org.getUser());
				model.addAttribute("userType", org.getUserType());
				model.addAttribute("headerFlag", true);

		         if(org.getUserType().equals("ROLE_MENTOR")){
		        	redire= "mentor/chat";
		        }
		        return redire;

	    }
	 @RequestMapping(value="/playVideo", method = RequestMethod.POST)
	    public String getPlayVideo(Model model,@RequestParam(value="mediaId",required=false) Long mediaId,
	    		@RequestParam(value="screenType",required=false) String screenType,
	    		@RequestParam(value="action",required=false) String action,
	    		@RequestParam(value="videoType",required=false) String videoType,
	    		@RequestParam(value="youtubeUrl",required=false) String youtubeUrl,
	    		@RequestParam(value="mediaType",required=false) String mediaType,
	    		@RequestParam(value="subCatId",required=false) Long subCatId,
	    		@RequestParam(value="scrType",required=false) String scrType,
	    		@RequestParam(value="subScrType",required=false) String subScrType

	    		)throws Exception
	    {
		    String redire=null;
				AdministratorAuthority org=currentUser();
				model.addAttribute("userId", org.getUser());
				model.addAttribute("userType", org.getUserType());
				model.addAttribute("mediaId", mediaId);
				model.addAttribute("screenType", screenType);
				model.addAttribute("scrType", scrType);
				model.addAttribute("action", action);
				model.addAttribute("videoType", videoType);
				model.addAttribute("youtubeUrl", youtubeUrl);
				model.addAttribute("mediaType", mediaType);
				model.addAttribute("subCatId", subCatId);
				model.addAttribute("subScrType", subScrType);


		        if(org.getUserType().equals("ROLE_STUDENT")){
		        	redire= "student/playVideo";
		        }
		        else if(org.getUserType().equals("ROLE_MENTOR")){
		        	redire= "mentor/playVideo";
		        }
		        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
		        	redire= "schoolTeacher/playVideo";
		        }
		        else if(org.getUserType().equals("ROLE_PARENT")){
		        	redire= "parent/playVideo";
		        }
              return redire;

	    }
	 @RequestMapping(value="/privateVORP", method = RequestMethod.POST)
	    public String getPlayPrivateVideo(Model model,
	    		@RequestParam(value="mediaId",required=false) Long mediaId,
	    		@RequestParam(value="screenType",required=false) String screenType,
	    		@RequestParam(value="action",required=false) String action,
	    		@RequestParam(value="videoType",required=false) String videoType,
	    		@RequestParam(value="youtubeUrl",required=false) String youtubeUrl,
	    		@RequestParam(value="mediaType",required=false) String mediaType,
	    		@RequestParam(value="subCatId",required=false) Long subCatId,
	    		@RequestParam(value="scrType",required=false) String scrType,
	    		@RequestParam(value="subScrType",required=false) String subScrType


	    		)throws Exception
	    {
		    String redire=null;
				AdministratorAuthority org=currentUser();
				model.addAttribute("userId", org.getUser());
				model.addAttribute("userType", org.getUserType());
				model.addAttribute("mediaId", mediaId);
				model.addAttribute("screenType", screenType);
				model.addAttribute("scrType", scrType);
				model.addAttribute("action", action);
				model.addAttribute("videoType", videoType);
				model.addAttribute("youtubeUrl", youtubeUrl);
				model.addAttribute("mediaType", mediaType);
				model.addAttribute("subCatId", subCatId);
				model.addAttribute("subScrType", subScrType);

		        if(org.getUserType().equals("ROLE_STUDENT")){
		        	redire= "student/privateVideo";
		        }
		        else if(org.getUserType().equals("ROLE_MENTOR")){
		        	redire= "mentor/privateVideo";
		        }
		        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
		        	redire= "schoolTeacher/privateVideo";
		        }
		        else if(org.getUserType().equals("ROLE_PARENT")){
		        	redire= "parent/privateVideo";
		        }
           return redire;

	    }
	 @RequestMapping(value="/subCategory",method=RequestMethod.POST)
		public String subCategoryDetailForMain(@ModelAttribute("subCatId") Long subCatId, Model model,RedirectAttributes redirectAttributes)
	    throws Exception{
			
		Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(currentUser().getUserType());
		CareerSubcategory subCat = commonService.findSubCategoryBySubCatId(subCatId);
		SubscribedCareers subscribeCareers = commonService.findSubscribedByAuthorityAndUserAndCareerSubcatAndSubscribe(
				authority, currentUser().getId(), subCat, Subscribe.Subscribed);
		if (subscribeCareers == null) {
			model.addAttribute("subscribeOrNo", "Subscribed");
		} else {
			model.addAttribute("subscribeOrNo", "UnSubscribed");
		}
		String redire = null;
		AdministratorAuthority org = currentUser();
		if (org.getUserType().equals("ROLE_STUDENT")) {
			redire = "student/careersubcategorydetails";
		} else if (org.getUserType().equals("ROLE_MENTOR")) {
			redire = "mentor/careersubcategorydetails";
		} else if (org.getUserType().equals("ROLE_PARENT")) {
			redire = "parent/careersubcategorydetails";
		} else if (org.getUserType().equals("ROLE_SCHOOL_TEACHER")) {
			redire = "schoolTeacher/careersubcategorydetails";
		}
    	model.addAttribute("careeSubCat", studentService.findCareerSubCategoryById(subCatId));
		model.addAttribute("subCatId", subCatId);
		model.addAttribute("userId", org.getUser());
		model.addAttribute("userType", org.getUserType());

	    return redire;
		}


		@RequestMapping(value="/demandAndSupply",method=RequestMethod.POST)
		  public String getdemandAndSupply(@ModelAttribute("subCatId") Long subCatId, Model model) throws Exception {
			String redire=null;
			AdministratorAuthority org=currentUser();
	        
	        if(org.getUserType().equals("ROLE_STUDENT")){
	        	redire= "student/demand";
	        }
	        else if(org.getUserType().equals("ROLE_MENTOR")){
	        	redire= "mentor/demand";
	        }
	        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
	        	redire= "schoolTeacher/demand";
	        }
	        else if(org.getUserType().equals("ROLE_PARENT")){
	        	redire= "parent/demand";
	        }
	        model.addAttribute("subCatId", subCatId);
	        model.addAttribute("userId", org.getUser());
			model.addAttribute("userType", org.getUserType());

	        return redire;
		  }


		@RequestMapping(value="/amITheOne",method=RequestMethod.POST)
		  public String getAmITheOne( @ModelAttribute("subCatId") Long subCatId, Model model) throws Exception {
			String redire=null;
			AdministratorAuthority org=currentUser();
	        
	        if(org.getUserType().equals("ROLE_STUDENT")){
	        	redire= "student/amITheOne";
	        }
	        else if(org.getUserType().equals("ROLE_MENTOR")){
	        	redire= "mentor/amITheOne";
	        }
	        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
	        	redire= "schoolTeacher/amITheOne";
	        }
	        else if(org.getUserType().equals("ROLE_PARENT")){
	        	redire= "parent/amITheOne";
	        }
		    model.addAttribute("subCatId", subCatId);
		    model.addAttribute("userId", org.getUser());
			model.addAttribute("userType", org.getUserType());

	        return redire;
		  }


	@RequestMapping(value = "/media", method = RequestMethod.POST)
	public String media(@RequestParam(value = "subcatId") Long subCatId, Model model)
	throws Exception {
	String redire = null;
	AdministratorAuthority org = currentUser();

	if (org.getUserType().equals("ROLE_STUDENT")) {
		redire = "student/media";
	} else if (org.getUserType().equals("ROLE_MENTOR")) {
		redire = "mentor/media";
	} else if (org.getUserType().equals("ROLE_SCHOOL_TEACHER")) {
		redire = "schoolTeacher/media";
	} else if (org.getUserType().equals("ROLE_PARENT")) {
		redire = "parent/media";
	}
	model.addAttribute("subCatId", subCatId);
	model.addAttribute("userId", org.getUser());
	model.addAttribute("userType", org.getUserType());

	return redire;
}

		@RequestMapping(value="/HGT",method=RequestMethod.POST)
		  public String getHGT(@ModelAttribute("subCatId") Long subCatId, Model model) throws Exception {
			String redire=null;
			AdministratorAuthority org=currentUser();
	        
	        if(org.getUserType().equals("ROLE_STUDENT")){
	        	redire= "student/HGT";
	        }
	        else if(org.getUserType().equals("ROLE_MENTOR")){
	        	redire= "mentor/HGT";
	        }
	        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
	        	redire= "schoolTeacher/HGT";
	        }
	        else if(org.getUserType().equals("ROLE_PARENT")){
	        	redire= "parent/HGT";
	        }
	        model.addAttribute("userId", org.getUser());
			model.addAttribute("userType", org.getUserType());
	        return redire;
		  }

		@RequestMapping(value="/skilAndRes",method=RequestMethod.POST)
		public String getSkillsAndResps(Model model, @ModelAttribute("subCatId") Long subCatId) throws Exception {
			String redire=null;
			AdministratorAuthority org=currentUser();
	        
	        if(org.getUserType().equals("ROLE_STUDENT")){
	        	redire= "student/skills";
	        }
	        else if(org.getUserType().equals("ROLE_MENTOR")){
	        	redire= "mentor/skills";
	        }
	        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
	        	redire= "schoolTeacher/skills";
	        }
	        else if(org.getUserType().equals("ROLE_PARENT")){
	        	redire= "parent/skills";
	        }
		    model.addAttribute("subCatId", subCatId);
		    model.addAttribute("userId", org.getUser());
			model.addAttribute("userType", org.getUserType());

		    return redire;
		  }
		@RequestMapping(value = "/saveInterests", method = RequestMethod.POST)
		public String saveUserInterests(Model model, @RequestParam("interest") String[]  arrUserInterests) throws Exception{
			AdministratorAuthority userDetails=currentUser();
			try {
				List<UserInterests> listofUserInterestsSelected=new ArrayList<UserInterests>();
				for (String interestId : arrUserInterests) {
					CareerCategory cc= commonService.findCategoryById(Long.parseLong(interestId));
					UserInterests singleUserInterest=new UserInterests();
					singleUserInterest.setInterests(cc);
					listofUserInterestsSelected.add(singleUserInterest);
				}
				InterestReq userInterests=new InterestReq();
				userInterests.setUserInterests(listofUserInterestsSelected);
				List<UserInterests> userInts = commonService.findInterestsByUserIdAndUserType(userDetails.getUser(), userDetails.getUserType());
				if (userInts != null) {
					commonService.deleteAllUserInterests(userDetails.getUser(), userDetails.getUserType());
				}
				commonService.saveUserInterests(userDetails.getUser(), userDetails.getUserType(), userInterests);
				List<InterestResponse> intrResps = new ArrayList<InterestResponse>();
				List<UserInterests> userIntsforSubcats = commonService.findInterestsByUserIdAndUserType(userDetails.getUser(), userDetails.getUserType());
					if(!userIntsforSubcats.isEmpty()){
						for (UserInterests catId : userIntsforSubcats) {
							List<CategoryResponse> catResps = new ArrayList<CategoryResponse>();
							InterestResponse csr = new InterestResponse();
							//CareerCategory cc = studentService.findCategoryById(catId.getInterests().getId());
							csr.setTitle(catId.getInterests().getCategoryName());
							csr.setCategouryId(catId.getInterests().getId());
							List<CareerSubcategory> careerSubCategories = studentService
									.findCareerSubcategories(catId.getInterests().getId());
							if(!careerSubCategories.isEmpty()){
								for (CareerSubcategory csc : careerSubCategories) {
									CategoryResponse cr = new CategoryResponse();
									cr.setName(csc.getSubCategoryName());
									cr.setId(csc.getId());
									catResps.add(cr);
								}	
							}
							csr.setResult(catResps);
							intrResps.add(csr);
						model.addAttribute("listofSubcats", intrResps);
						}	
					}
			}  catch (Exception e) {
				e.printStackTrace();
			}
			return "common/subInterests";
		}
		@RequestMapping(value="/saveUserInterests", method= RequestMethod.POST)
		public String saveUserInterests(Model model,RedirectAttributes redirectAttributes, @RequestParam("subInterests") String[]  arrUsersubInterests) throws Exception{
			try {
				for (String subInterestId : arrUsersubInterests) {
				Recommended recobj = new Recommended();
				recobj.setUser(currentUser().getUser());
				recobj.setAuthority(currentUser().getUserType());
				recobj.setStatus(Status.Active);
				commonService.saveRecommendedVideos(recobj, Long.parseLong(subInterestId));	
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:dashboard";
		}
		@RequestMapping(value="/subscribeCareer", method= RequestMethod.POST)
		public String subscribeCareer(Model model,RedirectAttributes redirectAttributes){
			String redire=null;
			AdministratorAuthority org=currentUser();
	        
	        if(org.getUserType().equals("ROLE_STUDENT")){
	        	redire= "student/skills";
	        }
	        else if(org.getUserType().equals("ROLE_MENTOR")){
	        	redire= "mentor/skills";
	        }
	        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
	        	redire= "schoolTeacher/skills";
	        }
	        else if(org.getUserType().equals("ROLE_PARENT")){
	        	redire= "parent/skills";
	        }
		    model.addAttribute("userId", org.getUser());
			model.addAttribute("userType", org.getUserType());
		    return redire;
	
		}
		@RequestMapping(value="/otp", method= RequestMethod.POST)
		public String verifyOtp(Model model,RedirectAttributes redirectAttributes){
		    return "common/otp";
	
		}
		
		@ResponseBody
		@RequestMapping(value="/checkEmail", method= RequestMethod.POST)
		public boolean checkEmail(@RequestParam("email") String email){
			boolean redire = false;
			AdministratorAuthority org=currentUser();
	        
	        if(org.getUserType().equals("ROLE_STUDENT")){
	        	Student student=studentService.findByEmail(email);
	        	if(student==null) {
	        		redire=true;
	        	}
	        	else {
	        		redire=false;
	        	}
	        }
	        else if(org.getUserType().equals("ROLE_MENTOR")){
	        	List<Mentor> mentors= mentorService.findByEmailOrContactNo(email, (long) 999999);
	        	if(mentors.size()==0) {
	        		redire=true;
	        	}
	        	else {
	        		redire=false;
	        	}
	        }
	        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
	        	List<SchoolTeacher> schoolTeachers= schoolTeacherService.findByEmailOrContactNo(email, (long) 999999);
	        	if(schoolTeachers.size()==0) {
	        		redire=true;
	        	}
	        	else {
	        		redire=false;
	        	}
	        }
	        else if(org.getUserType().equals("ROLE_PARENT")){
	        	List<Parents> parents= parentService.findByFatherMobileOrFatherEmail((long) 999999, email);
	        	
	        	if(parents.size()==0) {
	        		redire=true;
	        	}
	        	else {
	        		redire=false;
	        	}
	        }
		    return redire;
		}
		
		@ResponseBody
		@RequestMapping(value="/checkMobile", method= RequestMethod.POST)
		public boolean checkMobile(@RequestParam("mobile") Long mobile){
			boolean redire = false;
			AdministratorAuthority org=currentUser();
	        
	        if(org.getUserType().equals("ROLE_STUDENT")){
	        	Student student=studentService.findByMobile(mobile);
	        	if(student==null) {
	        		redire=true;
	        	}
	        	else {
	        		redire=false;
	        	}
	        }
	        else if(org.getUserType().equals("ROLE_MENTOR")){
	        	List<Mentor> mentors= mentorService.findByEmailOrContactNo("email", mobile);
	        	if(mentors.size()==0) {
	        		redire=true;
	        	}
	        	else {
	        		redire=false;
	        	}
	        }
	        else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
	        	List<SchoolTeacher> schoolTeachers= schoolTeacherService.findByEmailOrContactNo("email", mobile);
	        	if(schoolTeachers.size()==0) {
	        		redire=true;
	        	}
	        	else {
	        		redire=false;
	        	}
	        }
	        else if(org.getUserType().equals("ROLE_PARENT")){
	        	List<Parents> parents= parentService.findByFatherMobileOrFatherEmail(mobile, "email");
	        	
	        	if(parents.size()==0) {
	        		redire=true;
	        	}
	        	else {
	        		redire=false;
	        	}
	        }
		    return redire;
		}

}
