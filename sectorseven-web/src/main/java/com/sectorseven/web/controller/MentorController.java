package com.sectorseven.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sectorseven.model.Enum.AdminAcceptance;
import com.sectorseven.model.Enum.Gender;
import com.sectorseven.model.Enum.Salutation;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.Enum.YesNo;
import com.sectorseven.model.admin.Mentor;
import com.sectorseven.model.common.CareerCategory;
import com.sectorseven.model.common.StudentQueries;
import com.sectorseven.service.admin.CareerService;
import com.sectorseven.service.admin.MentorService;
import com.sectorseven.service.student.CommonService;
import com.sectorseven.web.utils.ImageUtility;
import com.sectorseven.web.utils.ViewConstants;

@Controller
@RequestMapping("/admin")
public class MentorController {

	@Autowired
	private MentorService mentorService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private CareerService careerService;
	
	
	@ModelAttribute("salutations")
	public List<Salutation> getSalutations(){
		return Arrays.asList(Salutation.values());
	}
	@ModelAttribute("catList")
	public List<CareerCategory> getCatList(){
		return careerService.findByStatus(Status.Active);
	}
	
	@ModelAttribute("genders")
	public List<Gender> getGenders(){
		return Arrays.asList(Gender.values());
	}
	
	@ModelAttribute("YesOrNo")
	public List<YesNo> getYesOrNo(){
		return Arrays.asList(YesNo.values());
	}
	
	@RequestMapping(value="/mentor/create", method= RequestMethod.GET)
	public String mentorForm(Model model) {
		model.addAttribute("mentorObj", new Mentor());
		return "admin/mentor/create";
	}
	
	@RequestMapping(value="/mentor/create", method= RequestMethod.POST)
	public String createMentorData(@ModelAttribute("mentorObj")  Mentor mentor ,BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) 
			throws Exception{
		List<Mentor> existingMentorData = new ArrayList<>();
		if(mentor.getId() == null) {
			existingMentorData = mentorService.findByEmailOrContactNo(mentor.getEmail(),mentor.getMobile());
		}else {
			 existingMentorData = mentorService.findByEmailOrContactNoAndIdNotIn(mentor.getEmail(),mentor.getMobile(),mentor.getId());
		}
		if(existingMentorData.isEmpty()) {
			if(mentor.getId() == null) {
				Mentor mentors=mentorService.save(mentor, false);
				if(mentor.getMentorImg()!=null) {
					MultipartFile file = mentor.getMentorImg();
					String[] pathAndName;
					ImageUtility imu = new ImageUtility();
					try {
						pathAndName = imu.getAbsoluteImagePathWithFileName(file, mentors.getId(),
								ViewConstants.CATEGORY_IMAGES);
						mentors.setImgName(pathAndName[1]);
						mentors.setImgPath(pathAndName[0]);
						mentors.setType(file.getContentType());
						mentorService.save(mentors, false);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				redirectAttributes.addFlashAttribute("msg", "Mentor data created successfully..!!\n"
						+ "Username and password sent to mail");
			}else { 
				mentorService.update(mentor);
				redirectAttributes.addFlashAttribute("msg", "Mentor updated successfully..!!");
			}
		}else {
			for (Mentor existingSchoolTeacher : existingMentorData) {
					if(existingSchoolTeacher.getEmail().equalsIgnoreCase(mentor.getEmail())) {
						model.addAttribute("errorMsg", "Email already taken..!");
						model.addAttribute("mentorObj", mentor);
						return "admin/mentor/create";
					}else if(existingSchoolTeacher.getMobile() == mentor.getMobile()) {
						model.addAttribute("errorMsg", "Mobile Number already Taken");
						model.addAttribute("mentorObj", mentor);
						return "admin/mentor/create";
					}
				}
			}
		redirectAttributes.addFlashAttribute("msg", "MentorCreated Successfully");
		return "redirect:/admin/mentor/create";
		
		
	}
	
	@RequestMapping("/askmentor/questions")
	public String getAllMentorQuestions(Model model) {
		List<StudentQueries> questionsList=commonService.findByAskMentorByStatus(Status.Active);
		model.addAttribute("questionsList", questionsList);
		return "/admin/mentors/questions";
	}
	
	@RequestMapping("/askmentor/update")
	public String updateMentorQuestions(Model model,@ModelAttribute("status") String status,@ModelAttribute("askmentorId") long id) {
		if(status.equals(AdminAcceptance.Accepted)){
		  // StudentQueries askMentor=commonService.updateAskMentor(AdminAcceptance.Accepted,id);
		}
		else if(status.equals(AdminAcceptance.Rejected)){
			//StudentQueries askMentor=commonService.updateAskMentor(AdminAcceptance.Rejected,id);
		}
		List<StudentQueries> questionsList=commonService.findByAskMentorByStatus(Status.Active);
		model.addAttribute("questionsList", questionsList);
		return "/admin/mentors/questions";
	}
	
	
}

