
package com.sectorseven.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.admin.Parents;
import com.sectorseven.model.util.CommonMethods;
import com.sectorseven.service.admin.ParentsService;

@Controller
@RequestMapping("/admin")
public class ParentController {
	@Autowired
	private ParentsService parentService;
	
	
	@RequestMapping(value="/parent/create", method= RequestMethod.GET)
	public String mentorForm(Model model) {
		model.addAttribute("parentObj", new Parents());
		return "admin/parent/create";
	}
	
	@RequestMapping(value="/parent/create", method= RequestMethod.POST)
	public String createMentorData(@ModelAttribute("parentObj")  Parents parent ,BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes)
			throws Exception{
		List<Parents> existingparentsData = new ArrayList<>();
		if(parent.getId() == null) {
			existingparentsData = parentService.findByFatherMobileOrFatherEmail(parent.getFatherMobile(),parent.getFatherEmail());
			
		}else {
			existingparentsData = parentService.findByEmailOrContactNoAndIdNotIn(parent.getFatherEmail(),parent.getFatherMobile(),parent.getId());
		}
		if(existingparentsData.isEmpty()) {
			if(parent.getId() == null) {
				parent.setStatus(Status.Active);
				String password = CommonMethods.generatePassword(8);
				parent.setPassword(CommonMethods.decodePassword(password));
				parent.setDecryptPassword(password);
				parentService.saveParentData(parent);
				redirectAttributes.addFlashAttribute("msg", "Parent data created successfully..!!\n"
						+ "Username and password sent to mail");
			}else { 
				parentService.update(parent);
				redirectAttributes.addFlashAttribute("msg", "Mentor updated successfully..!!");
			}
		}else {
			for (Parents existParentData : existingparentsData) {
					if(existParentData.getFatherEmail().equalsIgnoreCase(parent.getFatherEmail())) {
						model.addAttribute("errorMsg", "Email already taken..!");
						model.addAttribute("parentObj", parent);
						return "admin/parent/create";
					}else if(existParentData.getFatherMobile() == parent.getFatherMobile()) {
						model.addAttribute("errorMsg", "Mobile Number already Taken");
						model.addAttribute("parentObj", parent);
						return "admin/parent/create";
					}
				}
			
			
			}
		redirectAttributes.addFlashAttribute("msg", "Parent Created Successfully");
		return "redirect:/admin/parent/create";
		
		
	}
	
}

