package com.sectorseven.web.school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.school.School;
import com.sectorseven.service.School.SchoolService;

/**
 * @author RameshNaidu
 *
 */
@Controller
@RequestMapping("/admin")
public class SchoolController {

	
	@Autowired
	private SchoolService schoolService;
	
	
	@ModelAttribute("allSchoolsList")
	public List<School> getSchoolsList(){
		return schoolService.findByStatus(Status.Active);
	}
	
	
	@ModelAttribute("status")
	public List<Status> getStatus(){
		return Arrays.asList(Status.values());
	}
	
	
	@RequestMapping("/school/create")
	public String createShool(Model model) {
		model.addAttribute("schoolObject", new School());
		return "admin/school/create";
		
	}
	
	@RequestMapping(value = "/school/create", method = RequestMethod.POST)
	public String createShool(@ModelAttribute("schoolObject")  School school ,BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		//model.addAttribute("schoolObject", new School());
		List<School> existingSchoolList = new ArrayList<>();
		if(school.getId() == null) {
			existingSchoolList = schoolService.findByEmailOrContactNoOrSchoolCode(school.getEmail(),school.getContactNo(),school.getSchoolCode());
		}else {
			 existingSchoolList = schoolService.findByEmailOrContactNoOrSchoolCodeAndIdNotIn(school.getEmail(),school.getContactNo(),school.getSchoolCode(),school.getId());
		}
		if(existingSchoolList.isEmpty()) {
			if(school.getId() == null) {
				school.setStatus(Status.Active);
				schoolService.save(school);
				redirectAttributes.addFlashAttribute("msg", "School created successfully..!!\n"
						+ "Username and password sent to mail");
			}else {
//				school.setStatus(Status.Active);
				schoolService.update(school);
				redirectAttributes.addFlashAttribute("msg", "School updated successfully..!!");
			}
		}else {
			for (School existingSchool : existingSchoolList) {
					if(existingSchool.getEmail().equalsIgnoreCase(school.getEmail())) {
						model.addAttribute("errorMsg", "Email already taken..!");
						model.addAttribute("schoolObject", school);
						return "admin/school/create";
					}else if(existingSchool.getContactNo() == school.getContactNo()) {
						model.addAttribute("errorMsg", "Mobile Number already Taken");
						model.addAttribute("schoolObject", school);
						return "admin/school/create";
					}else if(existingSchool.getSchoolCode().equalsIgnoreCase(school.getSchoolCode())) {
						model.addAttribute("errorMsg", "School code already Taken");
						model.addAttribute("schoolObject", school);
						return "admin/school/create";
					}
				}
			}
		redirectAttributes.addFlashAttribute("msg", "School/Institution created successfully");
		return "redirect:/admin/school/create";
	}
	
	@RequestMapping("/school/list")
	public String getSchoolsList(Model model) {
		List<School> schools =  schoolService.findAll();
		model.addAttribute("schoolsList", schools);
		return "admin/school/list";
		
	}
	
	@RequestMapping("/school/{schoolID}/update")
	public String updateSchool(@PathVariable long schoolID,  Model model) {
		School school = schoolService.findById(schoolID);
		model.addAttribute("schoolObject", school);
		return "admin/school/update";
		
	}
	
}

