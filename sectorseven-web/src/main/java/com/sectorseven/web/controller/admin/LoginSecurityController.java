package com.sectorseven.web.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sectorseven.model.admin.Administrator;
import com.sectorseven.model.admin.AdministratorAuthority;
import com.sectorseven.model.admin.Mentor;
import com.sectorseven.model.admin.Parents;
import com.sectorseven.model.school.SchoolTeacher;
import com.sectorseven.model.school.SchoolUsers;
import com.sectorseven.model.school.Student;
import com.sectorseven.service.School.SchoolTeacherService;
import com.sectorseven.service.School.SchoolUserService;
import com.sectorseven.service.admin.AdministratorAuthorityService;
import com.sectorseven.service.admin.AdministratorService;
import com.sectorseven.service.admin.MentorService;
import com.sectorseven.service.admin.ParentsService;
import com.sectorseven.service.student.StudentService;

/**
 * @author RameshNaidu
 */
@Controller
public class LoginSecurityController {

    
    @Autowired
    private AdministratorAuthorityService administratorAuthorityService;
  
    
    @Autowired
    private SchoolUserService schoolUserService;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private MentorService mentorService;

    @Autowired
    private ParentsService parentService;
    
    @Autowired
    private SchoolTeacherService schoolTeacherService;

    
    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    public String executeloginSuccess(ModelMap model, HttpServletRequest req)throws Exception {
    	String redire=null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            AdministratorAuthority org = administratorAuthorityService.findAdministratorAuthorityByUserName(user.getUsername());
            if(org.getUserType().equals("ROLE_ADMIN")){
            	  HttpSession session = req.getSession();
                  session.setAttribute("organization", org);
                  redire="redirect:/admin/dashboard";
            }
            else if(org.getUserType().equals("ROLE_STUDENT")){
            	   Student student = studentService.findAdministratorByUsername(user.getUsername());
                   studentService.loginTime(student);
                     HttpSession session = req.getSession();
                     session.setAttribute("student", student);
                redire="redirect:/common/dashboard";
          }
            else if(org.getUserType().equals("ROLE_MENTOR")){
         	   Mentor mentor = mentorService.findAdministratorByUsername(user.getUsername());
                  HttpSession session = req.getSession();
                  session.setAttribute("mentor", mentor);
             redire="redirect:/common/dashboard";
       }
            else if(org.getUserType().equals("ROLE_PARENT")){
          	   Parents parents = parentService.findAdministratorByUsername(user.getUsername());
                   HttpSession session = req.getSession();
                   session.setAttribute("parents", parents);
              redire="redirect:/common/dashboard";
        }
            else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
           	   SchoolTeacher schoolTeacher = schoolTeacherService.findAdministratorByUsername(user.getUsername());
                    HttpSession session = req.getSession();
                    session.setAttribute("schoolTeacher", schoolTeacher);
               redire="redirect:/common/dashboard";
              }
          }
        return redire;
        
    }
    
    @RequestMapping(value = "/loginFail", method = RequestMethod.GET)
    public String loginFailureMesage(Model model, RedirectAttributes atr) {
    	//atr.addFlashAttribute("message", "Username/Password failed");
    	model.addAttribute("message", "Username/Password Incorrect");
    	return "login";
    }
   
    
    /*@RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String executeTalentHRSecurity(ModelMap model, HttpServletRequest req) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            Administrator org = administratorService.findAdministratorByUsername(user.getUsername());
            HttpSession session = req.getSession();
            session.setAttribute("organization", org);
        }
        return "redirect:/admin/dashboard";
    }*/
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String executeTalentHRSecurity(ModelMap model, HttpServletRequest req) throws Exception {
        String redire=null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            AdministratorAuthority org = administratorAuthorityService.findAdministratorAuthorityByUserName(user.getUsername());
            if(org.getUserType().equals("ROLE_ADMIN")){
            	  HttpSession session = req.getSession();
                  session.setAttribute("organization", org);
                  redire="redirect:/admin/dashboard";
            }
            else if(org.getUserType().equals("ROLE_STUDENT")){
            	   Student student = studentService.findAdministratorByUsername(user.getUsername());
                   studentService.loginTime(student);
                     HttpSession session = req.getSession();
                     session.setAttribute("student", student);
                redire="redirect:/common/dashboard";
          }
            else if(org.getUserType().equals("ROLE_MENTOR")){
         	   Mentor mentor = mentorService.findAdministratorByUsername(user.getUsername());
                  HttpSession session = req.getSession();
                  session.setAttribute("mentor", mentor);
             redire="redirect:/common/dashboard";
       }
            else if(org.getUserType().equals("ROLE_PARENT")){
          	   Parents parents = parentService.findAdministratorByUsername(user.getUsername());
                   HttpSession session = req.getSession();
                   session.setAttribute("parents", parents);
              redire="redirect:/common/dashboard";
        }
            else if(org.getUserType().equals("ROLE_SCHOOL_TEACHER")){
           	   SchoolTeacher schoolTeacher = schoolTeacherService.findAdministratorByUsername(user.getUsername());
                    HttpSession session = req.getSession();
                    session.setAttribute("schoolTeacher", schoolTeacher);
               redire="redirect:/common/dashboard";
              }
          }
        return redire;
    }
    
    @RequestMapping(value = "/adminLoginFail", method = RequestMethod.GET)
    public String loginErrorMesage(ModelMap model, RedirectAttributes atr) {
    	atr.addFlashAttribute("message", "Username/Password failed");
    	return "redirect:/login";
    }
    
    
    @RequestMapping(value = "/schoolLoginFail", method = RequestMethod.GET)
    public String schoolLoginFail(ModelMap model, RedirectAttributes atr) {
    	atr.addFlashAttribute("message", "Username/Password failed");
    	return "redirect:/login";
    }
    

   
    
    @RequestMapping(value = "/school", method = RequestMethod.GET)
    public String schoolLogin(ModelMap model, HttpServletRequest req) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            SchoolUsers schoolUser = schoolUserService.findAdministratorByUsername(user.getUsername());
            HttpSession session = req.getSession();
            session.setAttribute("school", schoolUser);
        }
        return "redirect:/school/dashboard";
    }
    
   
    
    @RequestMapping(value = "/student", method = RequestMethod.GET)
    public String studentLogin(ModelMap model, HttpServletRequest req) throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            User user = (User) principal;
            Student student = studentService.findAdministratorByUsername(user.getUsername());
          //  studentService.loginTime(student);
            HttpSession session = req.getSession();
            session.setAttribute("student", student);
        }
        return "redirect:/student/dashboard";
    }
    
    @RequestMapping(value = "/studentLoginFail", method = RequestMethod.GET)
    public String studentLoginFail(ModelMap model, RedirectAttributes atr) {
    	atr.addFlashAttribute("message", "Username/Password failed");
    	return "redirect:/login";
    }
}
