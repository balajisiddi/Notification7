package com.sectorseven.web.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.school.SchoolTeacher;
import com.sectorseven.model.school.Student;
import com.sectorseven.model.school.UserInterests;
import com.sectorseven.service.School.SchoolTeacherService;
import com.sectorseven.service.student.StudentService;

@Controller
@RequestMapping("/schoolTeacher")
public class SchoolTeacherDashboardController {
	
    @Autowired
    private SchoolTeacherService schoolTeacherService;

	@Autowired
	private StudentService studentService;

	
	private SchoolTeacher currentUser() throws Exception{
		SchoolTeacher schoolTeacher= null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof User) {
			User user = (User) principal;
			schoolTeacher = schoolTeacherService.findAdministratorByUsername(user.getUsername());
		}
		return schoolTeacher;
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String showDashbooard(Model model, RedirectAttributes redirectAttributes, HttpServletResponse response)throws Exception {

		SchoolTeacher schoolTeacher = currentUser();
		if (schoolTeacher != null) {
			model.addAttribute("schoolTeacherObj", schoolTeacher);
			redirectAttributes.addFlashAttribute("msg", "Got SchoolTeacher successfully..!!");
		} else {
			redirectAttributes.addFlashAttribute("msg", "Got SchoolTeacher Failed..!!");
		}
		return "/schoolTeacher/dashboard";
	}
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String showSchoolTeacherProfile(Model model, RedirectAttributes redirectAttributes, HttpServletResponse response)throws Exception {
		SchoolTeacher schoolTeacher = currentUser();
		List<UserInterests> userInterests = studentService.findInterestsByUserIdAndAuthority(schoolTeacher.getId(), "ROLE_SCHOOL_TEACHER");
		model.addAttribute("schoolTeacher", schoolTeacher);
		model.addAttribute("userInterests", userInterests);
		return "/schoolTeacher/profile";
	}
	
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public String showProfile(Model model, RedirectAttributes redirectAttributes, HttpServletResponse response)throws Exception {
		SchoolTeacher schoolTeacher = currentUser();
		List<Student> students = studentService.findStudentBySchoolTeacher(schoolTeacher);
		model.addAttribute("schoolTeacherStudentsObj", students);
		model.addAttribute("userId", schoolTeacher.getId());
		model.addAttribute("userType", "ROLE_SCHOOL_TEACHER");
		redirectAttributes.addFlashAttribute("msg", "Listing all the your Students");
		return "/schoolTeacher/students";
	}
	@RequestMapping(value="/update", method= RequestMethod.POST)
	public String updateSchoolTeacherData(@ModelAttribute("schoolTeacherObj") SchoolTeacher schoolTeacher, BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
	throws Exception{
		SchoolTeacher existSchoolTeacher = schoolTeacherService.findById(schoolTeacher.getId());
		existSchoolTeacher.setStatus(Status.Active);
		if(schoolTeacher.getFirstName()!=null){
			existSchoolTeacher.setFirstName(schoolTeacher.getFirstName());
		}
		if(schoolTeacher.getLastName()!=null){
			existSchoolTeacher.setLastName(schoolTeacher.getLastName());
		}
		if(schoolTeacher.getEmail()!=null){
			existSchoolTeacher.setEmail(schoolTeacher.getEmail());
		}
		if(schoolTeacher.getMobile()!=0){
			existSchoolTeacher.setMobile(schoolTeacher.getMobile());
		}
		 if(existSchoolTeacher!=null){
			 schoolTeacherService.updateSchoolTeacher(existSchoolTeacher);
			redirectAttributes.addFlashAttribute("msg", "SchoolTeacher Updated successfully..!!");
		}
		return "student/dashboard";
	}
}
