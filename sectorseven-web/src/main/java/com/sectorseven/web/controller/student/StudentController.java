package com.sectorseven.web.controller.student;

import java.util.Arrays;
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

import com.sectorseven.model.Enum.Gender;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.Enum.YesNo;
import com.sectorseven.model.admin.AcademicYear;
import com.sectorseven.model.admin.Mentor;
import com.sectorseven.model.admin.Parents;
import com.sectorseven.model.school.School;
import com.sectorseven.model.school.Student;
import com.sectorseven.service.School.SchoolService;
import com.sectorseven.service.admin.MentorService;
import com.sectorseven.service.admin.ParentsService;
import com.sectorseven.service.student.CommonService;
import com.sectorseven.service.student.StudentService;

@Controller
@RequestMapping("/admin")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private SchoolService schoolService;
	
	@Autowired
	private ParentsService parentsService;
	
	@Autowired
	private MentorService mentorService;
	
	@Autowired
	private CommonService commonService;
	
	@ModelAttribute("schoolsList")
	public List<School> getSchoolList(){
		return schoolService.findByStatus(Status.Active);
	}
	@ModelAttribute("academicList")
	public List<AcademicYear> getAcademicList(){
		return commonService.findByAcademicList(Status.Active);
	}
	@ModelAttribute("mentorList")
	public List<Mentor> getMentorList(){
		return mentorService.findByStatus(Status.Active);
	}
	
	@ModelAttribute("genders")
	public List<Gender> getGenders(){
		return Arrays.asList(Gender.values());
	}
	
	@ModelAttribute("yesorno")
	public List<YesNo> getYesOrNo(){
		return Arrays.asList(YesNo.values());
	}
	
	
//	@RequestMapping("/student/upload")
//	public String uploadStudentData(Model model) {
//		model.addAttribute("studentDataUpload", new StudentDataUpload());
//		return "admin/student/upload";
//	}
//	
//	 @RequestMapping(value = "/student/upload", method = RequestMethod.POST)
//	 @SuppressWarnings("unchecked")
//	 public String studentDataUpload(@ModelAttribute("studentDataUpload") StudentDataUpload studentDataUpload, Model model, HttpServletRequest request,
//	            RedirectAttributes redirectAttributes) throws IOException {
//
//	        ImageUtility imageUtility = new ImageUtility();
//	        String location = imageUtility.getAbsoluteImagePath(studentDataUpload.getDocument(), 1, ViewConstants.STUDENT_DATAUPLOAD_LOCATION);
//	        File uploadedFile = new File(ViewConstants.SAVE_LOCATION + "/" + location);
//	        School school = schoolService.findById(studentDataUpload.getSchool().getId());
//	        Map<String, Object> studentList = StudentUploadExcelTemplate.readExcelFile(uploadedFile.listFiles()[0],school,studentService);
//			List<String> errors = (List<String>) studentList.get("errors");
//	        if (!studentList.isEmpty() && errors.size() == 0) {
//	            List<Student> excelStudents = (List<Student>) studentList.get("student");
//	            studentService.save(excelStudents);
//	            redirectAttributes.addFlashAttribute("location", location);
//	        } else {
//	        	redirectAttributes.addFlashAttribute("errorList", errors);
//	        }
//	        return "redirect:/admin/student/upload";
//	    }
	

		@RequestMapping(value="/student/create", method= RequestMethod.GET)
		public String createStudentData(Model model) {
			model.addAttribute("studentData", new Student());
			return "admin/student/create";
		}
		
		@RequestMapping(value="/student/create", method= RequestMethod.POST)
		public String createStudentData(@ModelAttribute("studentData") Student student, BindingResult result, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) 
		throws Exception{
			List<Parents> existedParentData  = 	parentsService.findByFatherMobileOrFatherEmail(student.getParents().getFatherMobile(), student.getParents().getFatherEmail());		
			AcademicYear academicYear=studentService.findYearByAcademicYearId(student.getAcedemicYear().getId());
			Student existstudent=null;
			if(student.getId()!=null){
				existstudent=studentService.findByStudentId(student.getId());
			}
			student.setStatus(Status.Active);
			student.setAcedemicYear(academicYear);
			if(student.getId() == null) {
				if(existedParentData.isEmpty()) {
					studentService.saveStudentAndParent(student, false);
					redirectAttributes.addFlashAttribute("msg", "Student Created successfully..!!");
				}else {
					Parents parent = existedParentData.get(0);
					student.setParents(parent);
					studentService.saveStudent(student, false);
					redirectAttributes.addFlashAttribute("msg", "Student Created successfully..!!");
				}
			}
			else if(existstudent!=null){
				studentService.update(student,existstudent.getId());
				redirectAttributes.addFlashAttribute("msg", "Student Updated successfully..!!");
			}
			redirectAttributes.addFlashAttribute("msg", "Student Created Successfully");
			return "redirect:/admin/student/create";
		}
		
}
