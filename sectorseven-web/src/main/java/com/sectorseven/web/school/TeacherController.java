package com.sectorseven.web.school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.sectorseven.model.Enum.Gender;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.Enum.YesNo;
import com.sectorseven.model.common.CareerCategory;
import com.sectorseven.model.school.School;
import com.sectorseven.model.school.SchoolTeacher;
import com.sectorseven.model.school.SchoolUsers;
import com.sectorseven.service.School.SchoolService;
import com.sectorseven.service.School.SchoolTeacherService;
import com.sectorseven.service.School.SchoolUserService;
import com.sectorseven.service.admin.CareerService;

@Controller
@RequestMapping("/admin")
public class TeacherController {
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private SchoolUserService schoolUserService;	
	
	@Autowired
	private SchoolTeacherService schoolTeacherService;
	
	@Autowired
	private CareerService careerService;
	
	
	@ModelAttribute("schoolsList")
	public List<School> getSchoolList(){
		return schoolService.findByStatus(Status.Active);
	}
	
	@ModelAttribute("YesOrNo")
	public List<YesNo> getYesOrNo(){
		return Arrays.asList(YesNo.values());
	}
	
	@ModelAttribute("catList")
	public List<CareerCategory> getCatList(){
		return careerService.findByStatus(Status.Active);
	}
	
	private SchoolUsers currentUser() {
	 SchoolUsers schoolUser = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            schoolUser = schoolUserService.findAdministratorByUsername(user.getUsername());
        }
	     return schoolUser;
	   }
	

	@ModelAttribute("genders")
	public List<Gender> getGender(){
		return Arrays.asList(Gender.values());
	}
	
	
	@RequestMapping(value="/teacher/create", method= RequestMethod.GET)
	public String createTeacherData(Model model) {
		model.addAttribute("teacherObj", new SchoolTeacher());
		return "admin/teacher/create";
	}
	
	@RequestMapping(value="/teacher/create", method= RequestMethod.POST)
	public String createTeacherData(@ModelAttribute("teacherObj")  SchoolTeacher schoolTeacher ,BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception{
		
		List<SchoolUsers> existingSchoolList = new ArrayList<>();
		if(schoolTeacher.getId() == null) {
			existingSchoolList = schoolUserService.findByEmailOrContactNo(schoolTeacher.getEmail(),schoolTeacher.getMobile());
		}else {
			 existingSchoolList = schoolUserService.findByEmailOrContactNoAndIdNotIn(schoolTeacher.getEmail(),schoolTeacher.getMobile(),schoolTeacher.getId());
		}
		if(existingSchoolList.isEmpty()) {
			if(schoolTeacher.getId() == null) {
				schoolTeacher.setStatus(Status.Active);
				//schoolTeacher.setSchool(currentUser().getSchool());
				schoolTeacherService.save(schoolTeacher, false);
				redirectAttributes.addFlashAttribute("msg", "Your data created successfully..!!\n"
						+ "Username and password sent to mail");
			}else {
				schoolTeacher.setSchool(currentUser().getSchool());
				schoolTeacherService.update(schoolTeacher);
				redirectAttributes.addFlashAttribute("msg", "School updated successfully..!!");
			}
		}else {
			for (SchoolUsers existingSchoolTeacher : existingSchoolList) {
					if(existingSchoolTeacher.getEmail().equalsIgnoreCase(schoolTeacher.getEmail())) {
						model.addAttribute("errorMsg", "Email already taken..!");
						model.addAttribute("teacherObj", schoolTeacher);
						return "school/teacher/create";
					}else if(existingSchoolTeacher.getPhone() == schoolTeacher.getMobile()) {
						model.addAttribute("errorMsg", "Mobile Number already Taken");
						model.addAttribute("teacherObj", schoolTeacher);
						return "school/teacher/create";
					}
				}
			}
		redirectAttributes.addFlashAttribute("msg", "schoolTeacher Created Successfully");
		return "redirect:/admin/teacher/create";
		
		
	}

}
