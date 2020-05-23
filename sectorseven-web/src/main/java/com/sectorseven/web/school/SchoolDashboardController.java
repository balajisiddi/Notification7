package com.sectorseven.web.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sectorseven.model.school.SchoolUsers;
import com.sectorseven.service.School.SchoolUserService;

@Controller
@RequestMapping("/school")
public class SchoolDashboardController {

	@Autowired
	private SchoolUserService schoolUserService;	
	
	
	 private SchoolUsers currentUser() {
		 SchoolUsers schoolUser = null;
	        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	        if (principal instanceof User) {
	            User user = (User) principal;
	            schoolUser = schoolUserService.findAdministratorByUsername(user.getUsername());
	        }
	        return schoolUser;
	    }
	
	@RequestMapping(value="/dashboard", method= RequestMethod.GET)
	public String showDashboard(Model model) {
		SchoolUsers  userDetails = currentUser();
		model.addAttribute("currentUser", userDetails);
		return "school/dashboard";
	}
}
