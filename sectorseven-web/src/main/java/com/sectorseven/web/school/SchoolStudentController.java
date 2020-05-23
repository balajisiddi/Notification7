package com.sectorseven.web.school;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sectorseven.model.school.Student;

@Controller
@RequestMapping("/school")
public class SchoolStudentController {

	
	@RequestMapping(value="/student/create", method= RequestMethod.GET)
	public String createStudentData(Model model) {
		model.addAttribute("studentData", new Student());
		return "school/student/create";
	}
	
	@RequestMapping(value="/student/create", method= RequestMethod.POST)
	public String createStudentData(@ModelAttribute("studentData") Student student, BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		
		
		
		
		return "school/student/create";
	}
	
	
}
