package com.sectorseven.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sectorseven.model.Enum.AdminAcceptance;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.admin.Parents;
import com.sectorseven.model.common.Contribution;
import com.sectorseven.model.common.ContributionDocs;
import com.sectorseven.model.school.Student;
import com.sectorseven.service.admin.AuthorityService;
import com.sectorseven.service.admin.ParentsService;
import com.sectorseven.service.student.StudentService;
import com.sectorseven.web.utils.ImageUtility;
import com.sectorseven.web.utils.ViewConstants;

@Controller
@RequestMapping("/parent")
public class ParentsDashBoardController {
	
	@Autowired
	private ParentsService parentsService;
	
	 @Autowired
	 private AuthorityService authorityService;
	 
	 @Autowired
	 private StudentService studentService;


	private Parents currentUser() throws Exception{
		Parents parents = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof User) {
			User user = (User) principal;
			parents = parentsService.findAdministratorByUsername(user.getUsername());
		}
		return parents;
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String showDashbooard(Model model, RedirectAttributes redirectAttributes, HttpServletResponse response) throws Exception{

		Parents parents = currentUser();
		if (parents != null) {
			model.addAttribute("parentObj", parents);
			redirectAttributes.addFlashAttribute("msg", "Got Mentor successfully..!!");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Got Student Failed..!!");
		}
		return "/parent/dashboard";
	}
	@RequestMapping(value="/profile", method= RequestMethod.GET)
	public String parentProfile(Model model,RedirectAttributes redirectAttributes) throws Exception{
		Parents  parents = currentUser();
			if(parents!=null){
				model.addAttribute("parentObj", parents);
				redirectAttributes.addFlashAttribute("msg", "Got Parents successfully..!!");
				List<Student> childs=parentsService.getChildsByParent(parents);
				 model.addAttribute("childs", childs);
			}
			else{
				redirectAttributes.addFlashAttribute("msg", "Got Parents Failed..!!");
			}
		return "parent/profile";
	}
	
	@RequestMapping(value="/contribution", method= RequestMethod.GET)
	public String contribution(Model model) {
		model.addAttribute("contributionObj", new Contribution());
		return "parent/contribution";
	}

	@RequestMapping(value="/contribution", method= RequestMethod.POST)
	public String contribution(@ModelAttribute("contributionObj") Contribution contribution,Model model,RedirectAttributes redirectAttributes)throws Exception {
		Parents  parents = currentUser();
		Authority authority=authorityService.findAdministratorsAuthorityByAuthorityCode(parents.getAuthority());
		contribution.setAuthority(authority);
		contribution.setUser(parents.getId());
		contribution.setAcceptance(AdminAcceptance.Submitted);
		Contribution contributions=studentService.saveContribution(contribution);
		List<CommonsMultipartFile> file = contribution.getContDocs();
		for(CommonsMultipartFile doc: file){
		  ContributionDocs contDocs=new ContributionDocs();
			contDocs.setUser(parents.getId());
			contDocs.setAuthority(authority);
		    contDocs.setStatus(Status.Active);
			String[] pathAndName;
			ImageUtility imu = new ImageUtility();
			ContributionDocs conttDocc=studentService.saveContributionDocs(contDocs,contributions.getId());
			try {
				pathAndName = imu.getAbsoluteImagePathWithFileName(doc, conttDocc.getId(),
						ViewConstants.CONTRIBUTION_DOCUMENTS);
				conttDocc.setContDocName(pathAndName[1]);
				conttDocc.setContDocPath(pathAndName[0]);
				studentService.saveContributionDocs(conttDocc,contributions.getId());
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
		redirectAttributes.addFlashAttribute("msg", "Contribution Submitted successfully..!!");
	  	return "parent/contribution";
	}

	
	@RequestMapping(value="/childInfo", method= RequestMethod.GET)
	public String childInfo(Model model,RedirectAttributes redirectAttributes)throws Exception {
		Parents  parents = currentUser();
			if(parents!=null){
				 List<Student> childs=parentsService.getChildsByParent(parents);
				 model.addAttribute("childs", childs);
			}
			else{
				redirectAttributes.addFlashAttribute("msg", "Got Parents Failed..!!");
			}
		return "parent/childinfo";
	}
	
}
