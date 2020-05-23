package com.sectorseven.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sectorseven.model.Enum.AdminAcceptance;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.admin.Mentor;
import com.sectorseven.model.common.Contribution;
import com.sectorseven.model.common.ContributionDocs;
import com.sectorseven.model.common.MentorFollowers;
import com.sectorseven.model.common.StudentQueries;
import com.sectorseven.model.school.UserInterests;
import com.sectorseven.service.admin.AuthorityService;
import com.sectorseven.service.admin.MentorService;
import com.sectorseven.service.student.CommonService;
import com.sectorseven.service.student.StudentService;
import com.sectorseven.web.utils.ImageUtility;
import com.sectorseven.web.utils.ViewConstants;

@Controller
@RequestMapping("/mentor")
public class MentorDashboardController {


	@Autowired
	private CommonService commonService;
	
	
	@Autowired
	private MentorService mentorService;
	
	 @Autowired
	 private AuthorityService authorityService;
	 
	 @Autowired
	 private StudentService studentService;


	private Mentor currentUser() throws Exception{
		Mentor mentor = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof User) {
			User user = (User) principal;
			mentor = mentorService.findAdministratorByUsername(user.getUsername());
		}
		return mentor;
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String showDashbooard(Model model, RedirectAttributes redirectAttributes, HttpServletResponse response) throws Exception{

		Mentor mentor = currentUser();
		if (mentor != null) {
			model.addAttribute("mentorObj", mentor);
			redirectAttributes.addFlashAttribute("msg", "Got Mentor successfully..!!");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Got Student Failed..!!");
		}
		return "/mentor/dashboard";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String showProfile(Model model, RedirectAttributes redirectAttributes, HttpServletResponse response)throws Exception {
		Mentor mentor = currentUser();
		List<UserInterests> userInterests = studentService.findInterestsByUserIdAndAuthority(mentor.getId(), mentor.getAuthority());
		model.addAttribute("mentor", mentor);
		model.addAttribute("userInterests", userInterests);
		return "/mentor/profile";
	}
	
	  // finds studentqueries by id dashboard
		@RequestMapping("/queriesById/{Id}")
		public String getsAllMentorQuestionsById(Model model, @PathVariable Long Id) {
			StudentQueries studentQueries = commonService.findStudentQueriesById(Id);
			model.addAttribute("queriesById", studentQueries);
			return "/mentors/questionsbyId";
		}

		@RequestMapping("/studentQueries")
		public String getAllMentorQuestions(Model model) {
			List<StudentQueries> questionsList = commonService.findByAskMentorByStatus(Status.Inactive);
			model.addAttribute("queriesList", questionsList);
			return "/mentors/questions";
		}

		// to find all the mentor followers
		@RequestMapping("/findAllMentorFollowers")
		public String getAllMentorFollowers(Model model) {
			List<MentorFollowers> followersList = commonService.findallMentorFollowers();
			model.addAttribute("followersList", followersList);
			return "/mentors/mentorFollowers";
		}

		// to find all the mentor followers by id
		@RequestMapping("/findAllMentorFollowersById/{id}")
		public String getAllMentorFollowersById(Model model, @PathVariable int Id) {
			MentorFollowers follower = commonService.findallMentorFollowersById(Id);
			model.addAttribute("queriesList", follower);
			return "/admin/mentors/questions";
		}
/*	// finds studentqueries by id dashboard
	@RequestMapping("/queriesById/{Id}")
	public String getsAllMentorQuestionsById(Model model, @PathVariable int Id) {
		StudentQueries studentQueries = commonService.findStudentQueriesById(Id);
		model.addAttribute("queriesById", studentQueries);
		return "/admin/mentors/questionsbyId";
	}

	@RequestMapping("/studentQueries")
	public String getAllMentorQuestions(Model model) {
		List<StudentQueries> questionsList = commonService.findByAskMentorByStatus(Status.Inactive);
		model.addAttribute("queriesList", questionsList);
		return "/mentors/questions";
	}

	// to find all the mentor followers
	@RequestMapping("/findAllMentorFollowers")
	public String getAllMentorFollowers(Model model) {
		List<MentorFollowers> followersList = commonService.findallMentorFollowers();
		model.addAttribute("followersList", followersList);
		return "/mentors/mentorFollowers";
	}

	// to find all the mentor followers by id
	@RequestMapping("/findAllMentorFollowersById/{id}")
	public String getAllMentorFollowersById(Model model, @PathVariable int Id) {
		MentorFollowers follower = commonService.findallMentorFollowersById(Id);
		model.addAttribute("queriesList", follower);
		return "/admin/mentors/questions";
	}*/
	
	@RequestMapping(value="/contribution", method= RequestMethod.GET)
	public String contribution(Model model) {
		model.addAttribute("contributionObj", new Contribution());
		return "mentors/contribution";
	}

	@RequestMapping(value="/contribution", method= RequestMethod.POST)
	public String contribution(@ModelAttribute("contributionObj") Contribution contribution,Model model,RedirectAttributes redirectAttributes)throws Exception {
		Mentor  mentor = currentUser();
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(mentor.getAuthority());
		contribution.setAuthority(authority);
		contribution.setUser(mentor.getId());
		contribution.setAcceptance(AdminAcceptance.Submitted);
		Contribution contributions=studentService.saveContribution(contribution);
		List<CommonsMultipartFile> file = contribution.getContDocs();
		for(CommonsMultipartFile doc: file){
			ContributionDocs contDocs=new ContributionDocs();
			contDocs.setStatus(Status.Active);
			contDocs.setUser(mentor.getId());
			contDocs.setAuthority(authority);
			String[] pathAndName;
			ImageUtility imu = new ImageUtility();
			ContributionDocs contDocc=studentService.saveContributionDocs(contDocs,contributions.getId());
			try {
				pathAndName = imu.getAbsoluteImagePathWithFileName(doc, contDocs.getId(),
						ViewConstants.CONTRIBUTION_DOCUMENTS);
				contDocs.setContDocName(pathAndName[1]);
				contDocs.setContDocPath(pathAndName[0]);
				studentService.saveContributionDocs(contDocs,contributions.getId());
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		redirectAttributes.addFlashAttribute("msg", "Contribution Submitted successfully..!!");
	  	return "mentors/contribution";
	}

	@RequestMapping(value="/update", method= RequestMethod.POST)
	public String updateMentorData(@ModelAttribute("mentorObj") Mentor mentor, BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		Mentor existMentor = mentorService.findMentorById(mentor.getId());
		existMentor.setStatus(Status.Active);
		if(mentor.getFirstName()!=null){
			existMentor.setFirstName(mentor.getFirstName());
		}
		if(mentor.getLastName()!=null){
			existMentor.setLastName(mentor.getLastName());
		}
		if(mentor.getEmail()!=null){
			existMentor.setEmail(mentor.getEmail());
		}
		if(mentor.getMobile()!=0){
			existMentor.setMobile(mentor.getMobile());
		}
		 if(existMentor!=null){
			 mentorService.update(existMentor);
			redirectAttributes.addFlashAttribute("msg", "mentor Updated successfully..!!");
		}
		return "/mentor/dashboard";
	}
	@RequestMapping("/acceptStudentFootPrint/{id}")
	public String acceptStudentFootPrint(@PathVariable int Id, RedirectAttributes redirectAttributes) {
		MentorFollowers follower = commonService.findallMentorFollowersById(Id);
		follower.setStatus(Status.Active);
		commonService.saveMentorFollowerss(follower);
		redirectAttributes.addFlashAttribute("msg", "Response Noted");
		return "/mentors/mentorFollowers";
	}
	
	@RequestMapping("/rejectStudentFootPrint/{id}")
	public String rejectStudentFootPrint(@PathVariable int Id, RedirectAttributes redirectAttributes) {
		MentorFollowers follower = commonService.findallMentorFollowersById(Id);
		follower.setStatus(Status.Inactive);
		commonService.saveMentorFollowerss(follower);
		redirectAttributes.addFlashAttribute("msg", "Response Noted");
		return "/mentors/mentorFollowers";
	}
	
	/*
	 * 	@RequestMapping("/sevensigmas")
	public String getSigmas(Model model,  RedirectAttributes redirectAttributes) {
		List<SevenSigma> sigmas=commonService.getSigmas();
		model.addAttribute("sigmaList", sigmas);
		redirectAttributes.addFlashAttribute("msg", "Got Sigmas successfully..!!");
	
		return "/mentors/sevensigmas";
	}
	
	@RequestMapping("/libraries")
	public String getCategouries(Model model,  RedirectAttributes redirectAttributes) {
		 List<CareerCategory> careerCategories = studentService.findAllCategories();
	        model.addAttribute("careerCategories", careerCategories);
		return "/mentors/libraries";
	}
	
	@RequestMapping("/s7talks")
	public String gets7talks(Model model,  RedirectAttributes redirectAttributes) {
		Mentor mentor = currentUser();
		if (mentor != null) {
			model.addAttribute("mentorObj", mentor);
			redirectAttributes.addFlashAttribute("msg", "Got Mentor successfully..!!");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Got Student Failed..!!");
		}
		return "/mentors/s7talks";
	}
	
	@RequestMapping("/hubs")
	public String getAllHubs(Model model,  RedirectAttributes redirectAttributes) {
		 List<CoCreationHub> coHubs = studentService.findAllCoCreationHubs();
	        model.addAttribute("coHubs", coHubs);
		return "/mentors/hubs";
	}

	@RequestMapping("/events")
	public String getEvents(Model model,  RedirectAttributes redirectAttributes) {
		Mentor mentor = currentUser();
		if (mentor != null) {
			model.addAttribute("mentorObj", mentor);
			redirectAttributes.addFlashAttribute("msg", "Got Mentor successfully..!!");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Got Student Failed..!!");
		}
		return "/mentors/events";
	}
	
	
*/
}
