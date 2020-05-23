package com.sectorseven.web.controller.rest;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.Enum.AdminAcceptance;
import com.sectorseven.model.Enum.AdminRole;
import com.sectorseven.model.Enum.Country;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.Enum.Subscribe;
import com.sectorseven.model.Enum.VideoType;
import com.sectorseven.model.admin.AdministratorAuthority;
import com.sectorseven.model.admin.Authority;
import com.sectorseven.model.admin.Mentor;
import com.sectorseven.model.admin.Parents;
import com.sectorseven.model.common.ActivityLog;
import com.sectorseven.model.common.AddEvent;
import com.sectorseven.model.common.Apk;
import com.sectorseven.model.common.CareerCategory;
import com.sectorseven.model.common.CareerSubcategory;
import com.sectorseven.model.common.CoCreationHub;
import com.sectorseven.model.common.CommonImages;
import com.sectorseven.model.common.CommonResponse;
import com.sectorseven.model.common.Contribution;
import com.sectorseven.model.common.ContributionDocs;
import com.sectorseven.model.common.DemandLabels;
import com.sectorseven.model.common.DemandYears;
import com.sectorseven.model.common.Feedback;
import com.sectorseven.model.common.InstitutionCourses;
import com.sectorseven.model.common.Institutions;
import com.sectorseven.model.common.MentorFollowers;
import com.sectorseven.model.common.MentorsResponse;
import com.sectorseven.model.common.Notification;
import com.sectorseven.model.common.ProfileResponse;
import com.sectorseven.model.common.Recommended;
import com.sectorseven.model.common.ResponseObject;
import com.sectorseven.model.common.Responsibilities;
import com.sectorseven.model.common.Scholorships;
import com.sectorseven.model.common.ServerBroke;
import com.sectorseven.model.common.SevenSigma;
import com.sectorseven.model.common.SevenSigmaDetails;
import com.sectorseven.model.common.Skills;
import com.sectorseven.model.common.StudentQueries;
import com.sectorseven.model.common.SubCategoryDetails;
import com.sectorseven.model.common.SubscribedCareers;
import com.sectorseven.model.common.SuccessPersons;
import com.sectorseven.model.school.SchoolTeacher;
import com.sectorseven.model.school.SchoolUsers;
import com.sectorseven.model.school.Student;
import com.sectorseven.model.school.UserInterests;
import com.sectorseven.model.util.CommonException;
import com.sectorseven.model.util.Encryptor;
import com.sectorseven.model.util.Views;
import com.sectorseven.req.InterestReq;
import com.sectorseven.req.PasswordForgotDto;
import com.sectorseven.req.UpdateRequest;
import com.sectorseven.resp.CategoryResponse;
import com.sectorseven.resp.ChildResponse;
import com.sectorseven.resp.DemandResp;
import com.sectorseven.resp.HubResponse;
import com.sectorseven.resp.InstituteResponse;
import com.sectorseven.resp.InterestResponse;
import com.sectorseven.resp.InterestsResponse;
import com.sectorseven.resp.MediaResponse;
import com.sectorseven.resp.MessageResponse;
import com.sectorseven.resp.PercentageResponse;
import com.sectorseven.resp.PieResponse;
import com.sectorseven.resp.RecVideoResp;
import com.sectorseven.resp.RolesResp;
import com.sectorseven.resp.ScholorshipResponse;
import com.sectorseven.resp.SkillResponse;
import com.sectorseven.resp.SubCareerResponse;
import com.sectorseven.resp.SubscribedResponse;
import com.sectorseven.resp.SuccessfullPersonsResponse;
import com.sectorseven.resp.YearsResponse;
import com.sectorseven.resp.s7ListResp;
import com.sectorseven.resp.s7ProfiledetailsResponse;
import com.sectorseven.service.School.SchoolTeacherService;
import com.sectorseven.service.admin.AuthorityService;
import com.sectorseven.service.admin.MentorService;
import com.sectorseven.service.admin.ParentsService;
import com.sectorseven.service.admin.SevenSigmaService;
import com.sectorseven.service.common.LoginRequest;
import com.sectorseven.service.common.StudentResponseCodes;
import com.sectorseven.service.common.ViewConstants;
import com.sectorseven.service.student.CommonService;
import com.sectorseven.service.student.StudentService;
import com.sectorseven.web.utils.ImageUtility;

@Controller
@RequestMapping(value = "/studt")
public class StudentRestController {

	@Autowired
	private CommonService commonService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private MentorService mentorService;

	@Autowired
	private ParentsService parentService;

	@Autowired
	private SchoolTeacherService schoolTeacherService;

	@Autowired
	private SevenSigmaService sigmaService;
	

    private static final Logger LOGGER = Logger.getLogger(StudentRestController.class);

    
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseObject> citizenLogin(@Valid @RequestBody LoginRequest login,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseObject resp = new ResponseObject();
		CommonResponse cr = new CommonResponse();
		try {
			AdministratorAuthority adminAuthority = this.commonService.commonLogin(login);
			if (adminAuthority != null && adminAuthority.getUserName().equals(login.getUserName())
					&& Encryptor.check(login.getPassword(), adminAuthority.getDecryptPassword())) {
				cr.setUserId(adminAuthority.getUser());
				cr.setUserName(adminAuthority.getUserName());
				if (adminAuthority.getUserType().equals("ROLE_STUDENT")) {
					Student stude = studentService.findAdministratorByUsername(adminAuthority.getUserName());
					cr.setName(stude.getFirstName() + " " + stude.getLastName());
				} else if (adminAuthority.getUserType().equals("ROLE_MENTOR")) {
					Mentor stude = mentorService.findAdministratorByUsername(adminAuthority.getUserName());
					cr.setName(stude.getFirstName() + " " + stude.getLastName());
				} else if (adminAuthority.getUserType().equals("ROLE_PARENT")) {
					Parents stude = parentService.findAdministratorByUsername(adminAuthority.getUserName());
					cr.setName(stude.getFatherName() + " " + stude.getMotherName());
				} else if (adminAuthority.getUserType().equals("ROLE_SCHOOL_TEACHER")) {
					SchoolUsers schoolUsers = schoolTeacherService
							.findSchoolUserByUsername(adminAuthority.getUserName());
					cr.setName(schoolUsers.getFirstName() + " " + schoolUsers.getLastName());
				}
				cr.setUserType(adminAuthority.getUserType());
				resp.setResponse(cr);
				resp.setStatusMessage(StudentResponseCodes.SUCCESS);
				resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			} else {
				resp.setResponse(null);
				resp.setStatusMessage(StudentResponseCodes.INVALID_USER);
				resp.setStatusCode(StudentResponseCodes.INVALID_USER_CODE);
			}
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs While Login The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/studentProfileForAcceptAndReject", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getStudentInfoAcceptReject(@RequestParam("userId") Long userId,
			@RequestParam("userType") String userType) throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			Student student = studentService.findByStudentId(userId);
			Map<Object, Object> stdinfo = new HashMap<Object, Object>();
			if(student!=null){
				stdinfo.put("rewards", 0);
				stdinfo.put("stream", student.getStudentStream());
				stdinfo.put("class", student.getStudentClass());
				stdinfo.put("name", student.getFirstName());
			}
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got Student Details Successfully ");
			resp.setResponse(stdinfo);
		} catch (Exception ex) {
			LOGGER.info("Exception Occurs While Getting Profile For Accept/Reject The Reason Was"+ex.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getBasicUserDetails", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getUserFirstName(@RequestParam("userId") Long userId,
			@RequestParam("userType") String userType)  throws Exception{
		ResponseObject resp = new ResponseObject();
		Map<Object, Object> userFirstName = new HashMap<Object, Object>();
		try {
			if (userType.equals("ROLE_STUDENT")) {
				Student student = studentService.findByStudentId(userId);
				if(student!=null){
					userFirstName.put("userFirstName", student.getFirstName());
					userFirstName.put("imgPath", student.getStudentImgPath());
					userFirstName.put("imgName", student.getStudentImgName());
					userFirstName.put("description", student.getDescription());
				}
			} else if (userType.equals("ROLE_MENTOR")) {
				Mentor mentor = mentorService.findById(userId);
				if(mentor!=null){
					userFirstName.put("userFirstName", mentor.getFirstName());
					userFirstName.put("imgPath", mentor.getImgPath());
					userFirstName.put("imgName", mentor.getImgName());
					userFirstName.put("description", mentor.getDescription());
				}
			} else if (userType.equals("ROLE_PARENT")) {
				Parents parents = parentService.findParentById(userId);
				if(parents!=null){
					userFirstName.put("userFirstName", "Mr & Mrs "+parents.getFatherName());
					userFirstName.put("imgPath", parents.getParentImgPath());
					userFirstName.put("imgName", parents.getParentImgName());
					userFirstName.put("description", parents.getDescription());
				}
			} else if (userType.equals("ROLE_SCHOOL_TEACHER")) {
				SchoolTeacher schoolTeacher = schoolTeacherService.findById(userId);
				if(schoolTeacher!=null){
					userFirstName.put("userFirstName", schoolTeacher.getFirstName());
					userFirstName.put("imgPath", schoolTeacher.getTeacherImgPath());
					userFirstName.put("imgName", schoolTeacher.getTeacherImgName());
					userFirstName.put("description", schoolTeacher.getDescription());
				}
			}
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got User Basic Details ");
			resp.setResponse(userFirstName);
		} catch (Exception ex) {
			LOGGER.info("Exception Occurs While Getting User Basic Details The Reason Was"+ex.getMessage());
			throw new CommonException("SomeThing Went Wrong");

		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

/*	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getProfileDetails", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getProfileDetails(
			@RequestParam("userName") String userName,
			@RequestParam("userType") String userType, @RequestParam("userId") Long userId) throws Exception {
		ResponseObject resp = new ResponseObject();
		List<ProfileResponse> profileResponss = new ArrayList<ProfileResponse>();
		try {
			if (userType.equals("ROLE_STUDENT")) {
				//Student student = studentService.findAdministratorByUsername(userName);
				Student student = studentService.findByStudentId(userId);
				List<UserInterests> userInterests = studentService.findInterestsByUserIdAndAuthority(userId, userType);
				ProfileResponse studentt = new ProfileResponse(1, "student", student);
				ProfileResponse interests = new ProfileResponse(2, "interests", userInterests);
				ProfileResponse parentt = new ProfileResponse(3, "parent", student.getParents());
				profileResponss.add(0, studentt);
				profileResponss.add(1, interests);
				profileResponss.add(2, parentt);
			} else if (userType.equals("ROLE_MENTOR")) {
				//Mentor mentor = mentorService.findAdministratorByUsername(userName);
				Mentor mentor = mentorService.findById(userId);
				List<UserInterests> userInterests = studentService.findInterestsByUserIdAndAuthority(userId, userType);
				profileResponss.add(0, new ProfileResponse(1, "mentor", mentor));
				profileResponss.add(1, new ProfileResponse(2, "interests", userInterests));
			} else if (userType.equals("ROLE_PARENT")) {
				//Parents parents = parentService.findAdministratorByUsername(userName);
				Parents parents = parentService.findParentById(userId);
				List<UserInterests> userInterests = studentService.findInterestsByUserIdAndAuthority(userId, userType);
				profileResponss.add(new ProfileResponse(1, "parents", parents));
				profileResponss.add(1, new ProfileResponse(2, "interests", userInterests));
			} else if (userType.equals("ROLE_SCHOOL_TEACHER")) {
				//SchoolTeacher schoolTeacher = schoolTeacherService.findAdministratorByUsername(userName);
				SchoolTeacher schoolTeacher = schoolTeacherService.findById(userId);
				List<UserInterests> userInterests = studentService.findInterestsByUserIdAndAuthority(userId, userType);
				profileResponss.add(new ProfileResponse(1, "schoolTeacher", schoolTeacher));
				profileResponss.add(1, new ProfileResponse(2, "interests", userInterests));
			}
			resp.setResponse(profileResponss);
			resp.setStatusMessage(StudentResponseCodes.SUCCESS);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while getting profile with interests The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}*/

	

	@ResponseBody
@JsonView(Views.Public.class)
@RequestMapping(value = "/getMyProfileInfo", method = RequestMethod.GET)
public ResponseEntity<ResponseObject> getMyProfileInfo(
		/*@RequestParam("userName") String userName,*/	
		@RequestParam("userType") String userType, @RequestParam("userId") Long userId) throws Exception {
	ResponseObject resp = new ResponseObject();
	List<ProfileResponse> profileResponss = new ArrayList<ProfileResponse>();
	try {
		if (userType.equals("ROLE_STUDENT")) {
			//Student student = studentService.findAdministratorByUsername(userName);
			Student student = studentService.findByStudentId(userId);
			ProfileResponse studentt = new ProfileResponse(1, "student", student);
			resp.setResponse(studentt);
			profileResponss.add(0, studentt);
		} else if (userType.equals("ROLE_MENTOR")) {
			//Mentor mentor = mentorService.findAdministratorByUsername(userName);
			Mentor mentor = mentorService.findById(userId);
			ProfileResponse mentorr= new ProfileResponse(1, "mentor", mentor);
			resp.setResponse(mentorr);
		} else if (userType.equals("ROLE_PARENT")) {
			//Parents parents = parentService.findAdministratorByUsername(userName);
			Parents parents = parentService.findParentById(userId);
			ProfileResponse parentt= new ProfileResponse(1, "parents", parents);
			resp.setResponse(parentt);
		} else if (userType.equals("ROLE_SCHOOL_TEACHER")) {
			//SchoolTeacher schoolTeacher = schoolTeacherService.findAdministratorByUsername(userName);
			SchoolTeacher schoolTeacher = schoolTeacherService.findById(userId);
			ProfileResponse teacherr= new ProfileResponse(1, "schoolTeacher", schoolTeacher);
			resp.setResponse(teacherr);
		}
		resp.setStatusMessage(StudentResponseCodes.SUCCESS);
		resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
	}  catch (Exception e) {
		LOGGER.info("Exception Occurs While Getting Personal Info The Reason Was"+e.getMessage());
		throw new CommonException("SomeThing Went Wrong");
	}
	return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
}
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/saveFeedback", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> saveFeedback(@RequestParam("userId") Long userId,
			@RequestParam("userType") String authority, @RequestBody Feedback feedback)throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			feedback.setStatus(Status.Active);
			feedback.setUserId(userId);
			feedback.setUserType(authority);
			commonService.saveFeedback(feedback);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Successfully Saved Feedback ");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs While saving feedback The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);

	}
	

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/updateProfileImage", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> updateProfileImage(@RequestParam("userType") String userType,
			@RequestParam("userId") Long userId, @RequestParam("file") CommonsMultipartFile files) throws Exception {
		ResponseObject resp = new ResponseObject();
		Map<String, String> imag = new HashMap<String, String>();
		try {
			ActivityLog al = new ActivityLog();
			al.setAuthority(userType);
			al.setUser(userId);
			al.setMessage("Your Profile Image Updated Successfully");
			al.setAction("Update");
			al.setScreen("Profile");
			CommonsMultipartFile file = files;
			String[] pathAndName;
			ImageUtility imu = new ImageUtility();
			if (userType.equals("ROLE_STUDENT")) {
				Student student = studentService.findByStudentId(userId);
					pathAndName = imu.getAbsoluteImagePathWithFileName(file, student.getId(), ViewConstants.STUDEMT_IMAGES);
					student.setStudentImgName(pathAndName[1]);
					student.setStudentImgPath(pathAndName[0]);
					student.setType(file.getContentType());
					studentService.updateProfileImage(student);
					imag.put("imagePath", pathAndName[0]);
					imag.put("imageName", pathAndName[1]);
			} else if (userType.equals("ROLE_MENTOR")) {
				Mentor mentor = mentorService.findById(userId);
				pathAndName = imu.getAbsoluteImagePathWithFileName(file, mentor.getId(), ViewConstants.MENTOR_IMAGES);
				mentor.setImgName(pathAndName[1]);
				mentor.setImgPath(pathAndName[0]);
				mentor.setType(file.getContentType());
				mentorService.updateProfileImage(mentor);
				imag.put("imagePath", pathAndName[0]);
				imag.put("imageName", pathAndName[1]);
			} else if (userType.equals("ROLE_PARENT")) {
				Parents parents = parentService.findParentById(userId);
				pathAndName = imu.getAbsoluteImagePathWithFileName(file, parents.getId(), ViewConstants.PARENT_IMAGES);
				parents.setParentImgName(pathAndName[1]);
				parents.setParentImgPath(pathAndName[0]);
				parents.setType(file.getContentType());
				parentService.updateProfileImage(parents);
				imag.put("imagePath", pathAndName[0]);
				imag.put("imageName", pathAndName[1]);
			} else if (userType.equals("ROLE_SCHOOL_TEACHER")) {
				SchoolTeacher schoolTeacher = schoolTeacherService.findById(userId);
				pathAndName = imu.getAbsoluteImagePathWithFileName(file, schoolTeacher.getId(),
						ViewConstants.SCHOOL_TEACHER_IMAGES);
				schoolTeacher.setTeacherImgName(pathAndName[1]);
				schoolTeacher.setTeacherImgPath(pathAndName[0]);
				schoolTeacher.setType(file.getContentType());
				schoolTeacherService.updateProfileImage(schoolTeacher);
				imag.put("imagePath", pathAndName[0]);
				imag.put("imageName", pathAndName[1]);
			}
			commonService.saveActivityLog(al);
			resp.setResponse(imag);
			resp.setStatusMessage(StudentResponseCodes.SUCCESS);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while update Profile Image The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getEvents", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getEvents(@RequestParam("eventId") Integer eventId) throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = formatter.format(date);
			List<AddEvent> events=new ArrayList<AddEvent>();
             if(eventId==0){
            	 events = commonService.findAllUpComingEvents(strDate);
             }
             else if(eventId==1){
     			 events = commonService.findAllEvents();
             }
             else if(eventId==2){
            	 events = commonService.findAllPreviousEvents(strDate);
              }
			resp.setResponse(events);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got All getEvents Successfully");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs While getting Events The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/update", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseObject> studentUpdate(@RequestBody UpdateRequest user,
			@RequestParam("userType") String userType, @RequestParam("userId") Long userId, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResponseObject resp = new ResponseObject();
		CommonResponse cr = new CommonResponse();
		try {
			ActivityLog al = new ActivityLog();
			al.setAuthority(userType);
			al.setUser(userId);
			//al.setMessage("Your Profile Updated Successfully");
			al.setMessage("Your profile has been updated. Click here to preview");
			al.setAction("Update");
			if (userType.equals("ROLE_STUDENT")) {
				Student stud = studentService.updateStudent(user, userId);
				cr.setUserId(stud.getId());
			} else if (userType.equals("ROLE_MENTOR")) {
				Mentor mentor = mentorService.updateMentor(user, userId);
				cr.setUserId(mentor.getId());

			} else if (userType.equals("ROLE_SCHOOL_TEACHER")) {
				SchoolTeacher schoolTeacher = schoolTeacherService.updateTeacher(user, userId);
				cr.setUserId(schoolTeacher.getId());

			}
			commonService.saveActivityLog(al);
			resp.setResponse(cr);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Updated Successfully");

		} catch (Exception e) {
			LOGGER.info("Exception Occurs While update profile The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/updateDesc", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseObject> updateDesc(@RequestParam("userType") String userType,
			@RequestParam("userId") Long userId, @RequestBody UpdateRequest updateRequest, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ResponseObject resp = new ResponseObject();
		CommonResponse cr = new CommonResponse();
		try {
			if (userType.equals("ROLE_STUDENT")) {
				Student stud = studentService.updatedescStudent(updateRequest.getDescription(), userId);
				cr.setUserId(stud.getId());
			} else if (userType.equals("ROLE_MENTOR")) {
				Mentor mentor = mentorService.updatedescMentor(updateRequest.getDescription(), userId);
				cr.setUserId(mentor.getId());

			} else if (userType.equals("ROLE_SCHOOL_TEACHER")) {
				SchoolTeacher schoolTeacher = schoolTeacherService.updatedescTeacher(updateRequest.getDescription(),
						userId);
				cr.setUserId(schoolTeacher.getId());
			} else if (userType.equals("ROLE_PARENT")) {
				Parents parent = parentService.updatedescParents(updateRequest.getDescription(), userId);
				cr.setUserId(parent.getId());
			}
			resp.setResponse(cr);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Updated Successfully");

		}  catch (Exception e) {
			LOGGER.info("Exception Occurs While update description The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/updateParent", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseObject> updateParent(@RequestBody Parents user, @RequestParam("userId") Long userId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseObject resp = new ResponseObject();
		CommonResponse cr = new CommonResponse();
		try {
			Parents parent = parentService.updateParents(user, userId);
			ActivityLog al = new ActivityLog();
			al.setAuthority(parent.getAuthority());
			al.setUser(userId);
			al.setMessage("Your Profile Updated Successfully");
			al.setAction("Update");
			cr.setUserId(parent.getId());
			commonService.saveActivityLog(al);
			resp.setResponse(cr);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Parent updated Successfully");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while update parent The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getAllList", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getAllMentorsByInterst(@RequestParam("userId") Long userId,
			@RequestParam("userType") String authority, @RequestParam("type") String type,
			@RequestParam("limit") Integer limit,@RequestParam("offset") Integer offset) throws Exception {
		ResponseObject resp = new ResponseObject();

		try {
			List<MentorsResponse> custommentor = new ArrayList<MentorsResponse>();
			if (type.equals("mentors")) {
				List<Mentor> ment = commonService.getAllMentorsByInterst(userId, authority,limit,offset);
				if(!ment.isEmpty()){
					for (Mentor mentor : ment) {
						MentorsResponse mres = new MentorsResponse();
						List<UserInterests> userInterestss=commonService.findInterestsByUserIdAndUserType(mentor.getId(),mentor.getAuthority());
						mres.setId(mentor.getId());
						mres.setName(mentor.getFirstName());
						mres.setImgPath(mentor.getImgPath());
						mres.setImgName(mentor.getImgName());
						mres.setExpertize(mentor.getExpertise());
						if(!userInterestss.isEmpty()){
							custommentor.add(mres);
						}
					}	
				}
				resp.setStatusMessage("Got All Mentors Successfully");
			} else if (type.equals("askMentor")) {
				Student students = studentService.findByStudentId(userId);
				List<Mentor> mentors = commonService.getAllFollowedMentorsByStatusAndStudent(AdminAcceptance.Accepted,
						students,limit,offset);
				if(!mentors.isEmpty()){
				for (Mentor mentor : mentors) {
					MentorsResponse mres = new MentorsResponse();
					mres.setId(mentor.getId());
					mres.setName(mentor.getFirstName());
					mres.setExpertize(mentor.getExpertise());
					mres.setImgPath(mentor.getImgPath());
					mres.setImgName(mentor.getImgName());
					custommentor.add(mres);
				}
				}
				resp.setStatusMessage("Got All Mentors Successfully");
			} else if (type.equals("askStudent")) {
				Mentor mentor = mentorService.findById(userId);
				List<Student> students = commonService.getAllFollowedStudentsByStatusAndMentor(AdminAcceptance.Accepted,
						mentor,limit,offset);
				if(!students.isEmpty()){
				for (Student stud : students) {
					MentorsResponse mres = new MentorsResponse();
					List<UserInterests> userInterests = studentService.findInterestsByUserIdAndAuthority(stud.getId(), "ROLE_STUDENT");
					mres.setId(stud.getId());
					mres.setName(stud.getFirstName());
					mres.setImgPath(stud.getStudentImgPath());
					mres.setImgName(stud.getStudentImgName());
					mres.setExpertize(stud.getDescription());
					if(!userInterests.isEmpty()){
						mres.setData(true);
					}
					else if(userInterests.isEmpty()){
						mres.setData(false);
					}
					custommentor.add(mres);
				}
				}
				resp.setStatusMessage("Got All Students Successfully");
			} else if (type.equals("reqStudents")) {
				Mentor mentor = mentorService.findById(userId);
				List<Student> students = commonService
						.getAllFollowedStudentsByStatusAndMentor(AdminAcceptance.Submitted, mentor,limit,offset);
				if(!students.isEmpty()){
				for (Student stud : students) {
					MentorsResponse mres = new MentorsResponse();
					List<UserInterests> userInterests = studentService.findInterestsByUserIdAndAuthority(stud.getId(), "ROLE_STUDENT");
					mres.setId(stud.getId());
					mres.setName(stud.getFirstName());
					mres.setImgPath(stud.getStudentImgPath());
					mres.setImgName(stud.getStudentImgName());
					if(!userInterests.isEmpty()){
						mres.setData(true);
					}
					else if(userInterests.isEmpty()){
						mres.setData(false);
					}
					custommentor.add(mres);
				}
				}
				resp.setStatusMessage("Got All Students Successfully");
			} else if (type.equals("teacherStudents")) {
				SchoolTeacher schoolTeacher = schoolTeacherService.findById(userId);
				List<Student> students = studentService.getAllStudentsBySchoolTeacher(schoolTeacher,limit,offset);
				if(!students.isEmpty()){
				for (Student stud : students) {
					MentorsResponse mres = new MentorsResponse();
					List<UserInterests> userInterests = studentService.findInterestsByUserIdAndAuthority(stud.getId(), "ROLE_STUDENT");
					mres.setId(stud.getId());
					mres.setName(stud.getFirstName());
					mres.setImgPath(stud.getStudentImgPath());
					mres.setImgName(stud.getStudentImgName());
					if(!userInterests.isEmpty()){
						mres.setData(true);
					}
					else if(userInterests.isEmpty()){
						mres.setData(false);
					}
					custommentor.add(mres);
				}
				}
				resp.setStatusMessage("Got All Students Successfully");
			}
			resp.setResponse(custommentor);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);

		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while getting list for common scenerios The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/mentorDetails", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getAllMentorDetails(@RequestParam("mentorId") Long mentorId)
			throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			resp.setResponse(commonService.getMentorDetails(mentorId));
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got Mentor Details Successfully");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while getting mentor details The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/requestMentor", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> requestMentor(@RequestParam("mentorId") Long mentorId,
			@RequestParam("studentId") Long studentId) throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			Student student = studentService.findByStudentId(studentId);
			Mentor mentor = mentorService.findById(mentorId);
			List<MentorFollowers> followerss = commonService.findMentorFollowersByMentorAndStudent(student, mentor);
			if (followerss.isEmpty()) {
				MentorFollowers followers = new MentorFollowers();
				followers.setStudent(student);
				followers.setStatus(Status.Active);
				followers.setAcceptance(AdminAcceptance.Submitted);
				commonService.saveMentorFollowerFromService(followers, mentorId);
				Notification ntf = new Notification();
				ntf.setScreen("acceptRej");
				ntf.setAction("Request");
				ntf.setStudent(student);
				ntf.setMentor(mentor);
				ntf.setAcceptance(AdminAcceptance.Submitted);
				//ntf.setMessage("You Got Request from Student");
				ntf.setMessage("You have received a request from "+student.getFirstName()+""+student.getLastName()+"(Read more.) (Accept/Reject)");
				commonService.saveNotification(ntf);
				//resp.setResponse("Thank you for your request. Please be patent until the mentor accepts your request.");
				resp.setResponse("Your request has been submitted to "+mentor.getFirstName()+""+mentor.getLastName());
				resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
				resp.setStatusMessage("Successfully requested the mentor");
			} else {
				resp.setResponse("Already sent Request");
				resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
				resp.setStatusMessage("Already requested the mentor");
			}
		} catch (Exception e) {
			LOGGER.info("Exception Occurs while requesting mentor The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getRequestEnable", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> getRequestEnable(@RequestParam("mentorId") Long mentorId,
			@RequestParam("studentId") Long studentId) throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			Student student = studentService.findByStudentId(studentId);
			Mentor mentor = mentorService.findById(mentorId);
			List<MentorFollowers> followerss = commonService.findMentorFollowersByMentorAndStudent(student, mentor);
			Map<String, AdminAcceptance> request = new HashMap<String, AdminAcceptance>();
			if (!followerss.isEmpty()) {
				for (MentorFollowers mf : followerss) {
					request.put("request", mf.getAcceptance());
					resp.setResponse(request);
					resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
					resp.setStatusMessage("Got Details from Mentor Followers");
				}
			} else if (followerss.isEmpty()) {
				request.put("request", AdminAcceptance.ReqNotRaised);
				resp.setResponse(request);
				resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
				resp.setStatusMessage("Request not raised");

			}
		} catch (Exception e) {
			LOGGER.info("Exception Occurs while getting request enable The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/acceptStudent", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> acceptStudent(@RequestParam("mentorId") Long mentorId,
			@RequestParam("studentId") Long studentId, @RequestParam("acceptance") AdminAcceptance acceptance)
			throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			Student student = studentService.findByStudentId(studentId);
			Mentor mentor = mentorService.findById(mentorId);
			List<MentorFollowers> followerss = commonService.findMentorFollowersByMentorAndStudent(student, mentor);
			if (!followerss.isEmpty()) {
				for (MentorFollowers followe : followerss) {
					followe.setAcceptance(acceptance);
					commonService.saveMentorFollowerFromService(followe, mentorId);
					//Notification ntf = new Notification();
					//ntf.setScreen("acceptRej");
					//ntf.setStudent(student);
					//ntf.setMentor(mentor);
					Notification ntf=commonService.findNotificationBYUserAndmentor(student,mentor);
					if (acceptance.name().equals("Accepted")) {
						ntf.setAction("Accepted");
						ntf.setAcceptance(AdminAcceptance.Accepted);
						//resp.setResponse("Thank you for your Acceptance.");
						resp.setResponse("You have accepted the request from "+student.getFirstName()+""+student.getLastName()+", continue your work in the inbox.");
						resp.setStatusMessage("Successfully Accepted");
						//ntf.setMessage("Successfully Accepted ");
						ntf.setMessage("Your Request has been accepted by "+mentor.getFirstName()+" "+mentor.getLastName());
					} else if ((acceptance.name().equals("Rejected"))) {
						ntf.setAction("Rejected");
						ntf.setAcceptance(AdminAcceptance.Rejected);
						//resp.setResponse("You Successfully Rejected This student");
						resp.setResponse("You have rejected the request from "+student.getFirstName()+""+student.getLastName());
						resp.setStatusMessage("Successfully Rejected");
						ntf.setMessage("This Mentor Was Busy Please Send Request to another Mentor");

					}
					saveNotification(ntf);
					resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
				}
			} else {
				resp.setResponse("Already sent Request");
				resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
				resp.setStatusMessage("Already requested the mentor");
			}
		} catch (Exception e) {
			LOGGER.info("Exception Occurs while accept/reject student The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/gets7TalksList", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getStudentTalks(@RequestParam("userType") String authority) throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			List<s7ListResp> s7ListResp = new ArrayList<s7ListResp>();
			List<Contribution> contributions = commonService.getAllContibutionsForStudents(authority);
			if(!contributions.isEmpty()){
				for (Contribution contribution : contributions) {
					s7ListResp s7ListResps = new s7ListResp();
					s7ListResps.setUserId(contribution.getUser());
					s7ListResps.setName(contribution.getUserObj());
					s7ListResps.setSubject(contribution.getSubject().getCategoryName());
					s7ListResp.add(s7ListResps);
				}
			}
			resp.setResponse(s7ListResp);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got S7 Talks List Successfully");
		} catch (Exception e) {
			LOGGER.info("Exception Occurs while getting list of students/mentor/parent/teacher The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/getImageUrl", method = RequestMethod.GET)
	public ResponseEntity<?> getListImages(@RequestParam("userId") long userId, @RequestParam("type") String type,
			@RequestParam("imgPath") String imgPath, @RequestParam("imgName") String imgName) throws Exception {
		String path = null;
		String imgType = null;
		try {
			if (type.equals("teacherStudents") || type.equals("reqStudents") || type.equals("askStudent")) {
				Student student = studentService.findByStudentId(userId);
				path = ViewConstants.SAVE_LOCATION + student.getStudentImgPath() + "/" + student.getStudentImgName();
				imgType = student.getType();
			} else if (type.equals("askMentor") || type.equals("mentors")) {
				Mentor mentor = mentorService.findById(userId);
				path = ViewConstants.SAVE_LOCATION + mentor.getImgPath() + "/" + mentor.getImgName();
				imgType = mentor.getType();
			}
			 if(imgType==null || imgType.isEmpty()){
					CommonImages ci=commonService.findCommonImagesByScreen("user");
					path = ViewConstants.SAVE_LOCATION + ci.getImgPath()+ "/"
							+ ci.getImgName();
					imgType = ci.getImgType();

				}
		}
       catch (Exception e) {
			LOGGER.info("Exception Occurs while getting image url The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return getImageResponse(path, imgType);
	}
	@RequestMapping(value = "/userImage", method = RequestMethod.GET)
	public ResponseEntity<?> profileImage(@RequestParam("userId") Long userId,
			@RequestParam("userType") String userType, @RequestParam("imgPath") String imgPath,
			@RequestParam("imgName") String imgName) throws Exception {
		String path = null;
		String imgType = null;
		try{
		if (userType.equals("ROLE_STUDENT")) {
			Student student = studentService.findByStudentId(userId);
			path = ViewConstants.SAVE_LOCATION + student.getStudentImgPath() + "/" + student.getStudentImgName();
			imgType = student.getType();

		} else if (userType.equals("ROLE_MENTOR")) {
			Mentor mentor = mentorService.findById(userId);
			path = ViewConstants.SAVE_LOCATION + mentor.getImgPath() + "/" + mentor.getImgName();
			imgType = mentor.getType();

		} else if (userType.equals("ROLE_PARENT")) {
			Parents parent = parentService.findParentById(userId);
			path = ViewConstants.SAVE_LOCATION + parent.getParentImgPath() + "/" + parent.getParentImgName();
			imgType = parent.getType();

		} else if (userType.equals("ROLE_SCHOOL_TEACHER")) {
			SchoolTeacher schoolTeacher = schoolTeacherService.findById(userId);
			path = ViewConstants.SAVE_LOCATION + schoolTeacher.getTeacherImgPath() + "/"
					+ schoolTeacher.getTeacherImgName();
			imgType = schoolTeacher.getType();

		}
		 if(imgType==null || imgType.isEmpty()){
			CommonImages ci=commonService.findCommonImagesByScreen("user");
			path = ViewConstants.SAVE_LOCATION + ci.getImgPath()+ "/"
					+ ci.getImgName();
			imgType = ci.getImgType();

		}
		}
      catch (Exception e) {
			LOGGER.info("Exception Occurs while getting user image The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return getImageResponse(path, imgType);
	}
	
	@RequestMapping(value = "/iconImage", method = RequestMethod.GET)
	public ResponseEntity<?> iconImage(@RequestParam("iconId") Long iconId,
			@RequestParam("iconScreen") String iconScreen, @RequestParam("imgPath") String imgPath,
			@RequestParam("imgName") String imgName) throws Exception {
		String path = null;
		String imgType = null;
		try{
		if (iconScreen.equals("category")) {
			CareerCategory category = studentService.findCategoryById(iconId);
			 path = ViewConstants.SAVE_LOCATION + category.getCategoryImagPath() + "/"
					+ category.getCategoryImagName();
			 imgType=category.getType();
		} else if (iconScreen.equals("subCategory")) {
			CareerSubcategory subCategory = studentService.findCareerSubCategoryById(iconId);
			 path = ViewConstants.SAVE_LOCATION + subCategory.getSubcategoryImgPath() + "/"
					+ subCategory.getSubcategoryImgName();
			 imgType=subCategory.getType();
			
		} else if (iconScreen.equals("sigma")) {
			SevenSigma document = sigmaService.findSigmaById(iconId);
			path = ViewConstants.SAVE_LOCATION + document.getSigmaIconPath() + "/" + document.getSigmaIconName();
			imgType = document.getType();
		} 
		else if(iconScreen.equals("interest")){
			 CareerCategory category = studentService.findCategoryById(iconId);
			 path = ViewConstants.SAVE_LOCATION + category.getIntrstImagPath() + "/"
					+ category.getIntrstImagName();
			 imgType=category.getIntrstType();
		
		}
		}
      catch (Exception e) {
			LOGGER.info("Exception Occurs while getting icon image The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return getImageResponse(path, imgType);
	}


		@RequestMapping(value = "/getCareerMainImage", method = RequestMethod.GET)
	public ResponseEntity<?> getCareerMainImage(@RequestParam("subcategoryId") long subCatId,
			@RequestParam("imgPath") String imgPath, @RequestParam("imgName") String imgName)
			throws Exception {
		String path=null;
		String imgType=null;
		try{
		
		CareerSubcategory careerSubCategory = commonService.findSubCategoryBySubCatId(subCatId);
		 path = ViewConstants.SAVE_LOCATION + careerSubCategory.getLandingImgPath() + "/"
				+ careerSubCategory.getLandingImgName();
		 imgType=careerSubCategory.getLandingType();
	}
	 catch (Exception e) {
			LOGGER.info("Exception Occurs  while getting careermain image The Reason Was"+e.getMessage());
		throw new CommonException("SomeThing Went Wrong");
	}
		return getImageResponse(path,imgType);

	}
	@RequestMapping(value = "/getAmITheOneImage", method = RequestMethod.GET)
	public ResponseEntity<?> getAmITheOneImage(@RequestParam("subcategoryId") long subCatId,
			@RequestParam("imgPath") String imgPath, @RequestParam("imgName") String imgName)
			throws Exception {
		String path=null;
		String imgType=null;
		try{
		
		//CareerSubcategory careerSubCategory = commonService.findSubCategoryBySubCatId(subCatId);
	   //path = ViewConstants.SAVE_LOCATION + careerSubCategory.getAmITheOneImgPath() + "/"
		//		+ careerSubCategory.getAmITheOneImgName();
			CommonImages ci=commonService.findCommonImagesByScreen("amItheOne");
			path = ViewConstants.SAVE_LOCATION + ci.getImgPath()+ "/"
					+ ci.getImgName();
			imgType = ci.getImgType();
	}
	 catch (Exception e) {
	   LOGGER.info("Exception Occurs  while getting am i the one image The Reason Was"+e.getMessage());
		throw new CommonException("SomeThing Went Wrong");
	}
		return getImageResponse(path, imgType);

	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/gets7TalksProfile", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getTalksProfile(@RequestParam("s7TalksUserId") Long userId,
			@RequestParam(value = "s7TalksUserType", required = false) String authority) throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			s7ProfiledetailsResponse s7profile = new s7ProfiledetailsResponse();
			if (authority.equals("ROLE_STUDENT")) {
				Student student = studentService.findByStudentId(userId);
				s7profile.setDescription(student.getDescription());
				s7profile.setName(student.getFirstName() + " " + student.getLastName());
				s7profile.setStClass(student.getStudentClass());
			} else if (authority.equals("ROLE_MENTOR")) {
				Mentor mentor = mentorService.findById(userId);
				s7profile.setDescription(mentor.getDescription());
				s7profile.setName(mentor.getFirstName() + " " + mentor.getLastName());
				s7profile.setExpertize(mentor.getExpertise());
			} else if (authority.equals("ROLE_PARENT")) {
				Parents parent = parentService.findParentById(userId);
				s7profile.setDescription(parent.getDescription());
				s7profile.setName("Mrs" + parent.getFatherName());
			} else if (authority.equals("ROLE_SCHOOL_TEACHER")) {
				SchoolTeacher schoolTeacher = schoolTeacherService.findById(userId);
				s7profile.setDescription(schoolTeacher.getDescription());
				s7profile.setName(schoolTeacher.getFirstName() + " " + schoolTeacher.getLastName());
				s7profile.setExpertize(schoolTeacher.getExpertise());
			}
			resp.setResponse(s7profile);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got Profile Details Successfully");

		} 	catch (Exception e) {
			LOGGER.info("Exception Occurs  while getting s7 talks profle The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/gets7TalksDetails", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getStudentTalksDetails(@RequestParam("s7TalksUserId") Long userId,
			@RequestParam(value = "s7TalksUserType", required = false) String authority,
			@RequestParam(value = "mediaType", required = false) String type,
			@RequestParam(value = "latest", required = false) Integer latest,
			@RequestParam(value = "limit") Integer limit,
			@RequestParam(value="offset") Integer offset) throws Exception{
		ResponseObject resp = new ResponseObject();
		List<MediaResponse> mediaResps = new ArrayList<MediaResponse>();
		try {
			List<ContributionDocs> contributionDocs = commonService.getAllContibutionsDetails(authority, userId, type,
					latest,limit,offset);
			if(!contributionDocs.isEmpty()){
				for (ContributionDocs contDocs : contributionDocs) {
					MediaResponse mres = new MediaResponse();
					mres.setMediaId(contDocs.getId());
					mres.setTitle(contDocs.getTitle());
					mres.setDescription(contDocs.getDescription());
					mres.setVideoType(contDocs.getVideoType().name());
					mres.setYoutubeUrl("");
					mediaResps.add(mres);
				}	
			}
			resp.setResponse(mediaResps);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got Talks Details Successfully");
		} catch (Exception e) {
			LOGGER.info("Exception Occurs  while getting s7talks details The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/allSigmas", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> sevenSigmas() throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			resp.setResponse(commonService.getSigmas());
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got Sigmas Successfully");
		} catch (Exception e) {
			LOGGER.info("Exception Occurs  while getting sigmas The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getCategories", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getCareerCategory() throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			List<CareerCategory> careerCategories = studentService.findAllCategories();
			List<InterestsResponse> catResponses = new ArrayList<InterestsResponse>();
			if(!careerCategories.isEmpty()){
				for (CareerCategory careerCat : careerCategories) {
					InterestsResponse catRes = new InterestsResponse();
					catRes.setId(careerCat.getId());
					catRes.setCategoryName(careerCat.getCategoryName());
					catRes.setImgPath(careerCat.getCategoryImagPath());
					catRes.setImgName(careerCat.getCategoryImagName());
					catResponses.add(catRes);
				}	
			}
			resp.setResponse(catResponses);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got Categories Details Successfully");
		} catch (Exception e) {
			LOGGER.info("Exception Occurs  while getting categories The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getSubCategories", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getCareerSubCategory(@RequestParam("catId") long catId) throws Exception{
		ResponseObject resp = new ResponseObject();
		try {
			List<CareerSubcategory> careerSubCategories = studentService.findCareerSubcategories(catId);
			List<CategoryResponse> catResponses = new ArrayList<CategoryResponse>();
			if(!careerSubCategories.isEmpty())
			{
				for (CareerSubcategory careerSubCat : careerSubCategories) {
				CategoryResponse catRes = new CategoryResponse();
				catRes.setId(careerSubCat.getId());
				catRes.setName(careerSubCat.getSubCategoryName());
				catRes.setImgPath(careerSubCat.getSubcategoryImgPath());
				catRes.setImgName(careerSubCat.getSubcategoryImgName());
				catResponses.add(catRes);
				}
				}
			resp.setResponse(catResponses);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got SubCategory Details Successfully");
		} catch (Exception e) {
			LOGGER.info("Exception Occurs  while getting subcategories The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	// media Id means either subcategory or sigma
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/mediaDetails", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> mediadetails(@RequestParam("userId") Long userId,
			@RequestParam(value = "userType") String userType,
			@RequestParam(value="mediaId", required = false) Long mediaId,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "screenType", required = false) String screenType,
			@RequestParam(value = "limit") Integer limit,
			@RequestParam(value = "offset") Integer offset,HttpServletRequest request
			) throws Exception{
		ResponseObject resp = new ResponseObject();

		try {
			List<MediaResponse> mediaResps = new ArrayList<MediaResponse>();
			if (screenType.equals("sigma")) {
				List<SevenSigmaDetails> sigmaDetails = commonService.getSigmaDetails(mediaId, type,limit,offset);
				if(!sigmaDetails.isEmpty()){
					for (SevenSigmaDetails sigmaDetail : sigmaDetails) {
						MediaResponse mres = new MediaResponse();
						mres.setMediaId(sigmaDetail.getId());
						mres.setTitle(sigmaDetail.getTitle());
						mres.setDescription(sigmaDetail.getDescription());
						mres.setVideoType(sigmaDetail.getVideoType().name());
						mres.setYoutubeUrl(sigmaDetail.getYoutubeUrl());
						mediaResps.add(mres);
					}	
				}
			} else if (screenType.equals("subCategory")) {
				List<SubCategoryDetails> subCatDetails = studentService.findCareerSubCategoryDetails(mediaId, type,limit,offset);
				if(!subCatDetails.isEmpty()){
					for (SubCategoryDetails subCatDetail : subCatDetails) {
						//SubCategoryDetails scd=commonService.findBySubCategoryId(mediaId);
						MediaResponse mres = new MediaResponse();
						mres.setMediaId(subCatDetail.getId());
						mres.setTitle(subCatDetail.getTitle());
						mres.setDescription(subCatDetail.getDescription());
						mres.setVideoType(subCatDetail.getVideoType().name());
						mres.setYoutubeUrl(subCatDetail.getYoutubeUrl());
						mres.setSubCatId(subCatDetail.getCareerSubcat().getId());
						mediaResps.add(mres);
					}	
				}
			} else if (screenType.equals("stats")) {
				List<ActivityLog> activities = commonService.findAllByUserAndAuthorityAndSubcategoryByType(userId,
						userType, mediaId, type,limit,offset);
				if(!activities.isEmpty()){
					for (ActivityLog activity : activities) {
						MediaResponse mres = new MediaResponse();
						SubCategoryDetails scd=commonService.findBySubCategoryId(activity.getMediaId());
						mres.setMediaId(activity.getMediaId());
						mres.setVideoType(activity.getVideoType().name());
						mres.setYoutubeUrl(activity.getYoutubeUrl());
						mres.setDescription(scd.getDescription());
						mres.setTitle(scd.getTitle());
						mres.setSubCatId(scd.getCareerSubcat().getId());
						mediaResps.add(mres);
					}	
				}
			}
			else if(screenType.equals("recomm")) {
				List<Recommended> recomm = commonService.getRecommendedVideos(userId, userType);
				List<ActivityLog> activities = commonService.findAllActivities(userId, userType);
				List<Long> subcats = new ArrayList<Long>();
				for (Recommended inter : recomm) {
					subcats.add(inter.getCareerSubcat().getId());
				}
				List<Long> actMediaId = new ArrayList<Long>();
				for (ActivityLog al : activities) {
					if (al.getMediaId() != null) {
						actMediaId.add(al.getMediaId());
					}
				}
				List<Long> newList = subcats.stream().distinct().collect(Collectors.toList());
				List<Long> mediaList = actMediaId.stream().distinct().collect(Collectors.toList());
				List<SubCategoryDetails> subCats=null;
				if(mediaList.isEmpty())
				{
					subCats = commonService.getAllRecommendedVideosBySubcatNoActivity(newList,limit,offset);
				}
				else if(!mediaList.isEmpty()){
					subCats = commonService.getAllRecommendedVideosBySubcat(newList, mediaList,limit,offset);
				}
				if(!subcats.isEmpty()){
					for (SubCategoryDetails subCat : subCats) {
						MediaResponse mres = new MediaResponse();
						mres.setMediaId(subCat.getId());
						mres.setVideoType(subCat.getVideoType().name());
						mres.setYoutubeUrl(subCat.getYoutubeUrl());
						mres.setDescription(subCat.getTitle());
						mres.setTitle(subCat.getTitle());
						mres.setThumbnailUrl(subCat.getThumbnailUrl());
						mres.setSubCatId(subCat.getCareerSubcat().getId());
						mediaResps.add(mres);
					}	
				}
			}
			request.setAttribute("type",type);
			resp.setResponse(mediaResps);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got  Details Successfully");
		} catch (Exception e) {
			LOGGER.info("Exception Occurs T while getting media details he Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getAllHubs", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getCoCreationhubs() throws Exception{
		ResponseObject resp = new ResponseObject();
		try {
			List<CoCreationHub> hubs = studentService.findAllCoCreationHubs();
			List<HubResponse> hubResponses = new ArrayList<HubResponse>();
			if(!hubs.isEmpty()){
				for (CoCreationHub hub : hubs) {
					HubResponse hubRes = new HubResponse();
					hubRes.setId(hub.getId());
					hubRes.setName(hub.getHubName());
					hubRes.setHubZone(hub.getHubZone());
					hubResponses.add(hubRes);
				}	
			}
			resp.setResponse(hubResponses);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got All Hubs Successfully");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs  while getting all hubs The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/gethubDetails", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getCoCreationhubMap(@RequestParam("hubId") Long hubId) throws Exception  {
		ResponseObject resp = new ResponseObject();
		try {
			CoCreationHub coHub = studentService.findAllCoCreationHubById(hubId);
			resp.setResponse(coHub);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got All HubDetails  while getting hub details Successfully");
		}   catch (Exception e) {
			LOGGER.info("Exception Occurs The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/contribution", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> contribution(@RequestParam("userName") String userName,
			@RequestParam("userType") String userType, @RequestBody Contribution contribution) throws Exception{
		ResponseObject resp = new ResponseObject();
		try {
			ActivityLog al = new ActivityLog();
			al.setAuthority(userType);
			//al.setAction("contribution");
			//al.setScreen("contribution");
			al.setStatus(Status.Active);
			al.setAction("Before_Published");
			al.setScreen("Before_Published");
			//al.setMessage("Thanks for submitting your valuable content to 'S7'.Contributed Your");
			al.setMessage("Thanks for submitting your valuable content to 'S7'. We will verify it and notify you when it is online. Keep learning, keep sharing.");
			Map<String, Long> contId = new HashMap<String, Long>();
			CareerSubcategory careerSubcategory= commonService.findSubCategoryBySubCatId(contribution.getSubSubject().getId());
			CareerCategory cc = commonService.findCategoryById(contribution.getSubject().getId());
			contribution.setSubSubject(careerSubcategory);
			al.setSubcategory(careerSubcategory);
			contribution.setSubject(cc);
			if (userType.equals("ROLE_STUDENT")) {
				Student student = studentService.findAdministratorByUsername(userName);
				al.setUser(student.getId());
				Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
				contribution.setAuthority(authority);
				contribution.setUser(student.getId());
			} else if (userType.equals("ROLE_MENTOR")) {
				Mentor mentor = mentorService.findAdministratorByUsername(userName);
				al.setUser(mentor.getId());
				Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
				contribution.setAuthority(authority);
				contribution.setUser(mentor.getId());
			} else if (userType.equals("ROLE_PARENT")) {
				Parents parents = parentService.findAdministratorByUsername(userName);
				al.setUser(parents.getId());
				Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
				contribution.setAuthority(authority);
				contribution.setUser(parents.getId());
			} else if (userType.equals("ROLE_SCHOOL_TEACHER")) {
				SchoolTeacher schoolTeacher = schoolTeacherService.findAdministratorByUsername(userName);
				al.setUser(schoolTeacher.getId());
				Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
				contribution.setAuthority(authority);
				contribution.setUser(schoolTeacher.getId());
			}
			commonService.saveActivityLog(al);
			contribution.setAcceptance(AdminAcceptance.Submitted);
			Contribution contributions = studentService.saveContribution(contribution);
			contId.put("contributionId", contributions.getId());
			resp.setResponse(contId);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Contributed Successfully");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs  while contributin  The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/contributionDocs", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> contributionDocs(@RequestParam("userName") String userName,
			@RequestParam("userType") String userType, @RequestParam("contributionId") Long contributionId,
			@RequestParam("files") List<CommonsMultipartFile> files) throws Exception {
		ResponseObject resp = new ResponseObject();
		List<CommonsMultipartFile> file = files;
		if(!files.isEmpty()){
			for (CommonsMultipartFile doc : file) {
				ContributionDocs contDocs = new ContributionDocs();
				if (userType.equals("ROLE_STUDENT")) {
					Student student = studentService.findAdministratorByUsername(userName);
					Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
					contDocs.setAuthority(authority);
					contDocs.setUser(student.getId());
				} else if (userType.equals("ROLE_MENTOR")) {
					Mentor mentor = mentorService.findAdministratorByUsername(userName);
					Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
					contDocs.setAuthority(authority);
					contDocs.setUser(mentor.getId());
				} else if (userType.equals("ROLE_PARENT")) {
					Parents parents = parentService.findAdministratorByUsername(userName);
					Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
					contDocs.setAuthority(authority);
					contDocs.setUser(parents.getId());
				} else if (userType.equals("ROLE_SCHOOL_TEACHER")) {
					SchoolTeacher schoolTeacher = schoolTeacherService.findAdministratorByUsername(userName);
					Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
					contDocs.setAuthority(authority);
					contDocs.setUser(schoolTeacher.getId());
				}
				contDocs.setVideoType(VideoType.Private);
				contDocs.setStatus(Status.Active);
				contDocs.setAcceptance(AdminAcceptance.Submitted);
				String[] pathAndName;
				ImageUtility imu = new ImageUtility();
				ContributionDocs conttDocc = studentService.saveContributionDocs(contDocs, contributionId);
				try {
					pathAndName = imu.getAbsoluteImagePathWithFileName(doc, conttDocc.getId(),
							ViewConstants.CONTRIBUTION_DOCUMENTS);
					conttDocc.setContDocName(pathAndName[1]);
					conttDocc.setContDocPath(pathAndName[0]);
					conttDocc.setType(doc.getContentType());
					studentService.saveContributionDocs(conttDocc, contributionId);
					resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
					resp.setStatusMessage("Contributed Your Files Successfully");
				} catch (Exception e) {
					LOGGER.info("Exception Occurs  while contributing docs The Reason Was"+e.getMessage());
					throw new CommonException("SomeThing Went Wrong");
				}	
				}	
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}
	
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getInitialInterests", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getInitialInterests(@RequestParam("userId") Long userId,
			@RequestParam("userType") String userType) throws Exception{
		ResponseObject resp = new ResponseObject();
		try {
			resp.setResponse(commonService.getInitialInterests(userId,userType));
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got All Interests Successfully");
		} catch (Exception e) {	
			LOGGER.info("Exception Occurs  while getting interests The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getUserInterests", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getUserInterests(@RequestParam("userId") Long userId,
			@RequestParam("userType") String userType) throws Exception{
		ResponseObject resp = new ResponseObject();
		try {
             resp.setResponse(commonService.getAlluserSubcategories(userId,userType));		
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got All User Interests Successfully");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs  while getting user interests The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/saveInterests", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseObject> saveUserInterests(@RequestParam("userId") Long userId,
			@RequestParam("userType") String userType, @RequestBody InterestReq userInterests) throws Exception{
		ResponseObject resp = new ResponseObject();
		try {
			List<UserInterests> userInts = commonService.findInterestsByUserIdAndUserType(userId, userType);
			if (userInts != null) {
				commonService.deleteAllUserInterests(userId, userType);
			}
			commonService.saveUserInterests(userId, userType, userInterests);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Successfully Save User Interests Successfully");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while saving interests The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@RequestMapping(value = "/mediaType", method = RequestMethod.GET)
	public ResponseEntity<?> sigmaDetail(@RequestParam("mediaId") long mediaId,
			@RequestParam("screenType") String screenType
			/*,@RequestParam("userId") Long userId,
			@RequestParam("userType") String userTypee*/)
			throws Exception {
		String path = null;
		String contentType = null;
		try{
		if (screenType.equals("sigma")) {
			SevenSigmaDetails sigmaDetail = sigmaService.findSigmaDetailById(mediaId);
			path = ViewConstants.SAVE_LOCATION + sigmaDetail.getSigmaDocumentPath() + "/"
					+ sigmaDetail.getSigmaDocumentName();
			contentType = sigmaDetail.getType();
		} else if (screenType.equals("subCategory") || screenType.equals("stats")) {
			SubCategoryDetails subCatDetails = studentService.findSubCatDetailById(mediaId);
			path = ViewConstants.SAVE_LOCATION + subCatDetails.getSubCategoryDocumentPath() + "/"
					+ subCatDetails.getSubCategoryDocumentName();
			contentType = subCatDetails.getType();
		} else if (screenType.equals("contribution")) {
			ContributionDocs contributionDocs = studentService.findContributionById(mediaId);
			path = ViewConstants.SAVE_LOCATION + contributionDocs.getContDocPath() + "/"
					+ contributionDocs.getContDocName();
			contentType = contributionDocs.getType();
		}
		} catch (Exception e) {
    			LOGGER.info("Exception Occurs while getting file using mediaType The Reason Was"+e.getMessage());
				throw new CommonException("SomeThing Went Wrong");
			}
		return getImageResponse(path, contentType);

			}
	
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> forgotPassword(@Valid @RequestBody PasswordForgotDto loginRequest,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			CommonResponse cr = new CommonResponse();
			AdministratorAuthority adminAuthority = this.commonService.commonForgotPassword(loginRequest.getUsername());
			if (adminAuthority != null && adminAuthority.getUserName().equals(loginRequest.getUsername())) {
				if (adminAuthority.getUserType().equals("ROLE_STUDENT")) {
					Student student = studentService.forgotPassword(loginRequest.getUsername());
					cr.setUserId(student.getId());
					cr.setUserType(student.getAuthority());
					cr.setEmail(student.getEmail());
				} else if (adminAuthority.getUserType().equals("ROLE_MENTOR")) {
					Mentor mentor = mentorService.forgotPassword(loginRequest.getUsername());
					cr.setUserId(mentor.getId());
					cr.setUserType(mentor.getAuthority());
					cr.setEmail(mentor.getEmail());
				} else if (adminAuthority.getUserType().equals("ROLE_PARENT")) {
					Parents parent = parentService.forgotPassword(loginRequest.getUsername());
					cr.setUserId(parent.getId());
					cr.setUserType(parent.getAuthority());
					if(!parent.getFatherEmail().isEmpty()){
						cr.setEmail(parent.getFatherEmail());
					}
					if(!parent.getMotherEmail().isEmpty()){
						cr.setEmail(parent.getFatherEmail());
					}
				} else if (adminAuthority.getUserType().equals("ROLE_SCHOOL_TEACHER")) {
					SchoolTeacher schoolTeacher = schoolTeacherService.forgotPassword(loginRequest.getUsername());
					cr.setUserId(schoolTeacher.getId());
					cr.setUserType(AdminRole.ROLE_SCHOOL_TEACHER.name());
					cr.setEmail(schoolTeacher.getEmail());
				}
				resp.setStatusMessage("OTP Sent SuccessFully To Your Email");
				resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
				resp.setResponse(cr);
			} else {
				resp.setStatusMessage("UserName DoesNot Exist");
				resp.setStatusCode(StudentResponseCodes.NOTFOUND_CODE);
			}

		} catch (Exception e) {
			LOGGER.info("Exception Occurs while sending otp The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@SuppressWarnings("null")
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/sendotp", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> sendotp(@RequestParam(value="emailId",required=false) String emailId,
			@RequestParam(value="phoneNo",required=false) String phoneNo,
			@RequestParam("userId") Long userId, @RequestParam("userType") String userType) throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			String msg=null;
			if (userType.equals("ROLE_STUDENT")) {
				if(!emailId.isEmpty() ){
					msg=studentService.sendOtp(emailId,userId,userType);
				}
				if(!phoneNo.isEmpty())
				 {
					msg=studentService.sendMobileOtp(phoneNo,userId,userType);
				}
				
			} else if (userType.equals("ROLE_MENTOR")) {
				if(!(emailId.isEmpty())){
				msg=mentorService.sendOtp(emailId,userId,userType);
				}
				 if(!(phoneNo.isEmpty())){
					msg=studentService.sendMobileOtp(phoneNo,userId,userType);
				}
			} else if (userType.equals("ROLE_PARENT")) {
				if(!emailId.isEmpty()){
				msg=parentService.sendOtp(emailId,userId,userType);
			}
			 if(!(phoneNo.isEmpty())){
				msg=parentService.sendMobileOtp(phoneNo,userId,userType);
			}
			} else if (userType.equals("ROLE_SCHOOL_TEACHER")) {
				if(!emailId.isEmpty()){
				msg=schoolTeacherService.sendOtp(emailId,userId,userType);
			}
			 if(!(phoneNo.isEmpty())){
				msg=schoolTeacherService.sendMobileOtp(phoneNo,userId,userType);
			}
			}

			resp.setStatusMessage(msg);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setResponse(HttpStatus.OK);
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while Sending otp The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}		
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/verifyOtp", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> verifyOtp(@RequestParam(value="emailId",required=false) String emailId,
			@RequestParam(value="phoneNo",required=false) String phoneNo,@RequestParam("otp") String otp,
			@RequestParam("userId") Long userId, @RequestParam("userType") String userType,
			@RequestParam(value="parent",required=false) Integer parent) throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			if (userType.equals("ROLE_STUDENT")) {
				studentService.verifyOtp(otp, userId, userType,emailId,phoneNo);
			} else if (userType.equals("ROLE_MENTOR")) {
				mentorService.verifyOtp(otp, userId, userType,emailId,phoneNo);
			} else if (userType.equals("ROLE_PARENT")) {
				parentService.verifyOtp(otp, userId, userType,emailId,phoneNo,parent);
			} else if (userType.equals("ROLE_SCHOOL_TEACHER")) {
				schoolTeacherService.verifyOtp(otp, userId, userType,emailId,phoneNo);
			}

			resp.setStatusMessage("OTP Verified Successfully");
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setResponse(HttpStatus.OK);
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while verifying otp The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}		
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}
	
	

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> changePassword(@RequestParam("password") String password,
			@RequestParam("userId") Long userId, @RequestParam("userType") String userType) throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			ActivityLog al = new ActivityLog();
			al.setAuthority(userType);
			al.setUser(userId);
			al.setMessage("Your password has been updated successfully.");
			al.setScreen("Password");
			al.setAction("ChangePassword");
			if (userType.equals("ROLE_STUDENT")) {
				studentService.changePassword(password, userId);
			} else if (userType.equals("ROLE_MENTOR")) {
				mentorService.changePassword(password, userId);
			} else if (userType.equals("ROLE_PARENT")) {
				parentService.changePassword(password, userId);
			} else if (userType.equals("ROLE_SCHOOL_TEACHER")) {
				schoolTeacherService.changePassword(password, userId);
			}
			commonService.saveActivityLog(al);
			resp.setStatusMessage("Change Password Successfully");
			resp.setResponse(HttpStatus.OK);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
		} catch (Exception e) {
			LOGGER.info("Exception Occurs while changing password The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/messageSending", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> messageToMentor(@RequestParam("mentorId") Long mentorId,
			@RequestParam("studentId") Long studentId, @RequestParam("type") String type,
			@RequestParam("message") String message) throws Exception{
		ResponseObject resp = new ResponseObject();
		try {
			Student student = studentService.findByStudentId(studentId);
			Mentor mentor = mentorService.findById(mentorId);
			StudentQueries studentQueries = new StudentQueries();
			studentQueries.setStudent(student);
			studentQueries.setType(type);
			studentQueries.setQuestion(message);
			studentQueries.setStatus(Status.Active);
			studentQueries.setMentor(mentor);
			commonService.saveQuestion(studentQueries);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Successfully Sent Message");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while sending message The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getMessages", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> messageFromMentor(@RequestParam("mentorId") Long mentorId,
			@RequestParam("studentId") Long studentId) throws Exception{
		ResponseObject resp = new ResponseObject();
		try {
			Mentor mentor = mentorService.findById(mentorId);
			Student student = studentService.findByStudentId(studentId);
			List<MessageResponse> mRess = new ArrayList<MessageResponse>();
			List<StudentQueries> mentorMessages = commonService.getMessagesByMentorAndStudent(mentor, student);
			if(!mentorMessages.isEmpty()){
				for (StudentQueries stQueries : mentorMessages) {
					MessageResponse mres = new MessageResponse();
					mres.setId(stQueries.getId());
					mres.setMentorId(stQueries.getMentor().getId());
					mres.setQuestion(stQueries.getQuestion());
					mres.setStudentId(stQueries.getStudent().getId());
					mres.setType(stQueries.getType());
					mRess.add(mres);
				}	
			}
			resp.setResponse(mRess);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Successfully Got Messages From Mentor");
		} catch (Exception e) {
			LOGGER.info("Exception Occurs while getting messages The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getCareerMain", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getCareerMain(@RequestParam("subcategoryId") Long subcategoryId) throws Exception{
		ResponseObject resp = new ResponseObject();
		try {
			SubCareerResponse subCareeResp = new SubCareerResponse();
			CareerSubcategory subCategory = commonService.findSubCategoryBySubCatId(subcategoryId);
			subCareeResp.setDescription(subCategory.getLandingDesc());
			subCareeResp.setQuote(subCategory.getLandingQuote());
			subCareeResp.setId(subCategory.getId());
			subCareeResp.setImgPath(subCategory.getLandingImgPath());
			subCareeResp.setImgName(subCategory.getLandingImgName());
			resp.setResponse(subCareeResp);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got Career Main Details Successfully");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while getting careermain The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getAmITheOne", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getAmITheOne(@RequestParam("subcategoryId") Long subcategoryId) throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			SubCareerResponse subCareeResp = new SubCareerResponse();
			CareerSubcategory subCategory = commonService.findSubCategoryBySubCatId(subcategoryId);
			subCareeResp.setDescription(subCategory.getAmITheOne());
			subCareeResp.setId(subCategory.getId());
			subCareeResp.setImgPath(subCategory.getAmITheOneImgPath());
			subCareeResp.setImgName(subCategory.getAmITheOneImgName());
			resp.setResponse(subCareeResp);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got SRA Am I The One Successfully");
		} catch (Exception e) {
			LOGGER.info("Exception Occurs The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getSRASkills", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getSRASkills(@RequestParam("subcategoryId") Long subcategoryId)throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			List<Skills> skills = commonService.findSubCategorySkillsBySubCategory(subcategoryId);
			List<SkillResponse> skillResps = new ArrayList<SkillResponse>();
			if(!skills.isEmpty()){
				for (Skills skill : skills) {
					SkillResponse skillResp = new SkillResponse();
					skillResp.setId(skill.getId());
					skillResp.setSkill(skill.getSkill());
					skillResps.add(skillResp);
				}	
			}
			resp.setResponse(skillResps);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got SRA Skills Successfully");
		} catch (Exception e) {
			LOGGER.info("Exception Occurs while getting getSRASkills The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getSRAResponsibilities", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getSubCategorySRA(@RequestParam("subcategoryId") Long subcategoryId)throws Exception {
		ResponseObject resp = new ResponseObject();
		try {

			List<Responsibilities> responsibilities = commonService
					.findSubCategoryResponsesBySubCategory(subcategoryId);
			List<RolesResp> rolesResps = new ArrayList<RolesResp>();
			if(!responsibilities.isEmpty()){
				for (Responsibilities role : responsibilities) {
					RolesResp roleResp = new RolesResp();
					roleResp.setId(role.getId());
					roleResp.setRole(role.getRoles());
					rolesResps.add(roleResp);
				}	
			}
			resp.setResponse(rolesResps);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got SRA Responsibilities Successfully");
		} catch (Exception e) {
			LOGGER.info("Exception Occurs while getting SRAResponsibilities The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

		@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getHGTHome", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getHGTHome(@RequestParam("subcategoryId") Long subcategoryId) throws Exception {
		ResponseObject resp = new ResponseObject();
		Map<String, String> hwt = new HashMap<String, String>();
		try {
			CareerSubcategory subCategory = commonService.findSubCategoryBySubCatId(subcategoryId);
			hwt.put("hgtHome", subCategory.getHowToGetThere());
			resp.setResponse(hwt);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got Home HGT  Successfully");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while getting HGTHome The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getHGTCourses", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getHGTCourses(@RequestParam("subcategoryId") Long subcategoryId,
			@RequestParam("professionId") Long professionId) throws Exception{
		ResponseObject resp = new ResponseObject();
		Map<String, String> hwtCourses = new HashMap<String, String>();
		try {
			CareerSubcategory subCategory = commonService.findSubCategoryBySubCatId(subcategoryId);
			if(professionId==1){
				hwtCourses.put("courses", subCategory.getCourseCat1());
			}
			if(professionId==2){
				hwtCourses.put("courses", subCategory.getCourseCat2());
			}

			if(professionId==3){
				hwtCourses.put("courses", subCategory.getCourseCat3());
			}

			if(professionId==4){
				hwtCourses.put("courses", subCategory.getCourseCat4());
			}

			if(professionId==5){
				hwtCourses.put("courses", subCategory.getCourseCat5());
			}

			if(professionId==6){
				hwtCourses.put("courses", subCategory.getCourseCat6());
			}

			resp.setResponse(hwtCourses);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got Courses HGT Successfully");
		} catch (Exception e) {
			LOGGER.info("Exception Occurs while getting HGT Courses The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getHGTExams", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getHGTExams(@RequestParam("subcategoryId") Long subcategoryId)throws Exception {
		ResponseObject resp = new ResponseObject();
		Map<String, String> hwtCourses = new HashMap<String, String>();
		try {
			CareerSubcategory subCategory = commonService.findSubCategoryBySubCatId(subcategoryId);
			hwtCourses.put("exam1", subCategory.getEntranceCat1());
			hwtCourses.put("exam2", subCategory.getEntranceCat2());
			hwtCourses.put("exam3", subCategory.getEntranceCat3());
			hwtCourses.put("exam4", subCategory.getEntranceCat4());
			resp.setResponse(hwtCourses);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got Exams HGT Successfully");
		} catch (Exception e) {
			LOGGER.info("Exception Occurs while getting HGT Exams The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getHGTScholorships", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getScholorships(@RequestParam(value="subcategoryId",required=false) Long subcategoryId,
			@RequestParam("country") String contry) throws Exception{
		ResponseObject resp = new ResponseObject();
		List<ScholorshipResponse> iResps = new ArrayList<ScholorshipResponse>();
		try {
			List<Scholorships> catScholorships = new ArrayList<Scholorships>();
			if (contry.equals("India")) {
				catScholorships = commonService.findSubCategoryScholorshipsByCountry(Country.India);
			} else if (contry.equals("Abroad")) {
				catScholorships = commonService.findSubCategoryScholorshipsByCountry(Country.Abroad);
			}
			if(!catScholorships.isEmpty()){
				for (Scholorships scholorship : catScholorships) {
					ScholorshipResponse ischolor = new ScholorshipResponse();
					ischolor.setName(scholorship.getScholorshipName());
					ischolor.setScholorships(scholorship.getScholorshipList());
					ischolor.setUrl(scholorship.getUrl());
					iResps.add(ischolor);
				}	
			}
			resp.setResponse(iResps);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got  Scholorships HGT Successfully");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while getting HGT Scholorships The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getHGTSuccessPersons", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getHGTSuccessPersons(@RequestParam("subcategoryId") Long subcategoryId) throws Exception{
		ResponseObject resp = new ResponseObject();
		List<SuccessfullPersonsResponse> iResps = new ArrayList<SuccessfullPersonsResponse>();
		try {
			List<SuccessPersons> persons = commonService.findSubCategoryPersonsBySubCatId(subcategoryId);
			if(!persons.isEmpty()){
				for (SuccessPersons sp : persons) {
					SuccessfullPersonsResponse person = new SuccessfullPersonsResponse();
					person.setName(sp.getName());
					person.setDescription(sp.getDescription());
					iResps.add(person);
				}	
			}
			resp.setResponse(iResps);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got  Successfull Persons HGT Successfully");
		} catch (Exception e) {
			LOGGER.info("Exception Occurs while getting SuccessPersons The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/studentInfo", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> studentInfo(@RequestParam("studentId") Long studentId) throws Exception {
		ResponseObject resp = new ResponseObject();
		List<ProfileResponse> profileResponss = new ArrayList<ProfileResponse>();
		try {
			s7ProfiledetailsResponse s7profile = new s7ProfiledetailsResponse();
			Student student = studentService.findByStudentId(studentId);
			if(student!=null){
				s7profile.setName(student.getFirstName() + " " + student.getLastName());
				s7profile.setStClass(student.getStudentClass());
				s7profile.setStudentStream(student.getStudentStream());
			}
			List<UserInterests> userInterests = studentService.findInterestsByUserIdAndAuthority(studentId,
					student.getAuthority());
			ProfileResponse studentt = new ProfileResponse(0, "student", s7profile);
			ProfileResponse interests = new ProfileResponse(1, "interests", userInterests);
			profileResponss.add(0, studentt);
			profileResponss.add(1, interests);
			resp.setResponse(profileResponss);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Successfully Got Student Info");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while getting student info The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getChilds", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getChilds(@RequestParam("parentId") Long parentId) throws Exception{
		ResponseObject resp = new ResponseObject();
		List<ChildResponse> childResps = new ArrayList<ChildResponse>();
		try {

			Parents parent = parentService.findParentById(parentId);
			List<Student> students = studentService.findStudentsByParent(parent);
			if(!students.isEmpty()){
				for (Student stdt : students) {
					ChildResponse childRes = new ChildResponse();
					childRes.setChildId(stdt.getId());
					List<UserInterests> userInterests = studentService.findInterestsByUserIdAndAuthority(stdt.getId(), "ROLE_STUDENT");
					childRes.setName(stdt.getFirstName() + " " + stdt.getLastName());
					if(!userInterests.isEmpty()){
						childRes.setData(true);
					}
					else if(userInterests.isEmpty()){
						childRes.setData(false);
					}
					childResps.add(childRes);
				}	
			}
			resp.setResponse(childResps);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Successfully Got Student Info");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while getting childs The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getChildInfo", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getChildInfo(@RequestParam("childId") Long childId) throws Exception{
		ResponseObject resp = new ResponseObject();
		List<ProfileResponse> childResps = new ArrayList<ProfileResponse>();
		try {
			Student students = studentService.findByStudentId(childId);
			List<UserInterests> interests = studentService.findInterestsByUserIdAndAuthority(childId,
					students.getAuthority());
			//ProfileResponse child = new ProfileResponse(1, "child", students);
			ProfileResponse interest = new ProfileResponse(2, "interests", interests);
			//childResps.add(0, child);
			childResps.add(1, interest);
			resp.setResponse(childResps);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Successfully Got Student Info");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs getting child info The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/recommendedVideos", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getRecommendedVideos(@RequestParam("userId") Long userId,
			@RequestParam("userType") String userType) throws Exception{
		Integer limit=6;
		Integer offset=0;
		ResponseObject resp = new ResponseObject();
		List<RecVideoResp> recResps = new ArrayList<RecVideoResp>();
		try {
			List<Recommended> recomm = commonService.getRecommendedVideos(userId, userType);
			List<ActivityLog> activities = commonService.findAllActivities(userId, userType);
			List<Long> subcats = new ArrayList<Long>();
			for (Recommended inter : recomm) {
				subcats.add(inter.getCareerSubcat().getId());
			}
			List<Long> actMediaId = new ArrayList<Long>();
			for (ActivityLog al : activities) {
				if (al.getMediaId() != null) {
					actMediaId.add(al.getMediaId());
				}
			}
			List<Long> newList = subcats.stream().distinct().collect(Collectors.toList());
			List<Long> mediaList = actMediaId.stream().distinct().collect(Collectors.toList());
			List<SubCategoryDetails> subCats=null;
			if(mediaList.isEmpty())
			{
				subCats = commonService.getAllRecommendedVideosBySubcatNoActivity(newList,limit,offset);
			}
			else if(!mediaList.isEmpty()){
				subCats = commonService.getAllRecommendedVideosBySubcat(newList, mediaList,limit,offset);
			}
			Integer id=0;
			if(!subCats.isEmpty() ){
				for (SubCategoryDetails subCat : subCats) {
					RecVideoResp recResp = new RecVideoResp();
					recResp.setMediaId(subCat.getId());
					recResp.setVideoType(subCat.getVideoType());
					recResp.setYoutubeUrl(subCat.getYoutubeUrl());
					recResp.setDescription(subCat.getTitle());
					recResp.setTitle(subCat.getTitle());
					recResp.setThumbnaiUrl(subCat.getThumbnailUrl());
					recResp.setId(id);
					recResp.setSubCatId(subCat.getCareerSubcat().getId());
					recResps.add(recResp);
					id++;
				}	
			}
			resp.setResponse(recResps);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Successfully Got All Recomended Videos");
		}catch (Exception e) {
			LOGGER.info("Exception Occurs while getting recommended videos The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getPieChartSubcategories", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getPieChartSubcategories(@RequestParam("userId") Long userId,
			@RequestParam("userType") String userType) throws Exception{
		ResponseObject resp = new ResponseObject();
		List<PieResponse> recResps = new ArrayList<PieResponse>();
		try {
			List<Recommended> pieData = commonService.getRecommendedVideos(userId, userType);
			if(!pieData.isEmpty()){
				for (Recommended subCat : pieData) {
					PieResponse mres = new PieResponse();
					mres.setId(subCat.getCareerSubcat().getId());
					mres.setName(subCat.getCareerSubcat().getSubCategoryName());
					mres.setData(subCat.getCatPercent());
					recResps.add(mres);
				}	
			}
			resp.setResponse(recResps);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Successfully Got All PieChartSubcategories");
		} catch (Exception e) {
			LOGGER.info("Exception Occurs while getting piechart subcategories The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getSeenPercentages", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getPieChartSubcategories(@RequestParam("userId") Long userId,
			@RequestParam("userType") String userType, @RequestParam("subCatId") Long subCatId) throws Exception {
		ResponseObject resp = new ResponseObject();
		PercentageResponse resps = new PercentageResponse();
		try {
			List<ActivityLog> activities = commonService.findAllByUserAndAuthorityAndSubcategoryByVideos(userId,
					userType, subCatId);

			List<ActivityLog> activities1 = commonService.findAllByUserAndAuthorityAndSubcategoryByAudios(userId,
					userType, subCatId);

			List<ActivityLog> activities2 = commonService.findAllByUserAndAuthorityAndSubcategoryByPdfs(userId,
					userType, subCatId);

			int total = activities.size() + activities1.size() + activities2.size();

			Double percentage = (double) Math.round(100.0 * Double.valueOf(activities.size()) / total);

			Double percentage1 = (double) Math.round(100.0 * Double.valueOf(activities1.size()) / total);

			Double percentage2 = (double) Math.round(100.0 * Double.valueOf(activities2.size()) / total);

			resps.setVideos(percentage);

			resps.setAudios(percentage1);

			resps.setPdfs(percentage2);

			resps.setNoOfVideos(activities.size());

			resps.setNoOfAudios(activities1.size());

			resps.setNoOfPdfs(activities2.size());

			resp.setResponse(resps);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Successfully Got Seen percentages ");
		} catch (Exception e) {
			LOGGER.info("Exception Occurs while getting seen percentages The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

    @ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/subscribeCareer", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> subscribeCareer(@RequestParam("userId") Long userId,
			@RequestParam("userType") String userType, @RequestParam("subscribe") String subscribe,
			@RequestParam("subCatId") Long subCatId) throws Exception{
		ResponseObject resp = new ResponseObject();
		try {
			Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
			CareerSubcategory subCat = commonService.findSubCategoryBySubCatId(subCatId);
			SubscribedCareers subscribeCareers;
			SubscribedCareers unsubscribeCareers;
			SubscribedCareers subscri = new SubscribedCareers();
			subscri.setAuthority(authority);
			subscri.setStatus(Status.Active);
			subscri.setUser(userId);
			subscri.setCareerSubcat(subCat);
			ActivityLog al = new ActivityLog();
			al.setSubcategory(subCat);
			al.setAuthority(userType);
			al.setUser(userId);
			al.setScreen("Subscribed");
			subscribeCareers = commonService.findSubscribedByAuthorityAndUserAndCareerSubcatAndSubscribe(authority,
					userId, subCat, Subscribe.Subscribed);
			unsubscribeCareers = commonService.findSubscribedByAuthorityAndUserAndCareerSubcatAndSubscribe(authority,
					userId, subCat, Subscribe.UnSubscribed);
			List<SubscribedCareers> careers = commonService.findSubscribedByAuthorityAndUserAndCareerSubcat(authority,
					userId, subCat);
			if (careers.isEmpty()) {
				//al.setMessage("Subscribed Successfully");
				al.setMessage("You Have subscribed to "+subCat.getSubCategoryName()+", click here to get started.");
				subscri.setSubscribe(Subscribe.Subscribed);
				al.setAction("Subscribed");
				commonService.subscribeCareer(subscri);
				resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
				resp.setStatusMessage("Successfully Subscribed");
				commonService.saveActivityLog(al);
			} else if (subscribeCareers != null && subscribe.equals("UnSubscribed")) {
				ActivityLog al1=commonService.findAllActivitiesByUserAndTypeAndSubCatAndScreen(userId,userType,subCat,Subscribe.Subscribed);
				//al1.setMessage("UnSubscribed Successfully");
				al1.setMessage("You Have unsubscribed from "+subCat.getSubCategoryName()+", please let us know what we could do better. Thanks.");
				al1.setAction("UnSubscribed");
				subscribeCareers.setSubscribe(Subscribe.UnSubscribed);
				commonService.subscribeCareer(subscribeCareers);
				resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
				resp.setStatusMessage("Successfully UnSubscribed");
				commonService.saveActivityLog(al1);
			} else if (unsubscribeCareers != null && subscribe.equals("Subscribed")) {
				ActivityLog al1=commonService.findAllActivitiesByUserAndTypeAndSubCatAndScreen(userId,userType,subCat,Subscribe.UnSubscribed);
				//al1.setMessage("Subscribed Successfully");
				al.setMessage("You Have subscribed to "+subCat.getSubCategoryName()+", click here to get started.");
				al1.setAction("Subscribed");
				unsubscribeCareers.setSubscribe(Subscribe.Subscribed);
				commonService.subscribeCareer(unsubscribeCareers);
				resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
				resp.setStatusMessage("Successfully Subscribed");
				commonService.saveActivityLog(al1);
			} else {
				resp.setStatusCode(StudentResponseCodes.NOTFOUND_CODE);
				resp.setStatusMessage("Already Sent Request");
			}

		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while subscribing career The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getAllSubscribeCareers", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getAllSubscribeCareers(@RequestParam("userId") Long userId,
			@RequestParam("userType") String userType) throws Exception{
		ResponseObject resp = new ResponseObject();
		List<SubscribedResponse> subcareerResps = new ArrayList<SubscribedResponse>();
		try {
			Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
			List<SubscribedCareers> subscribeCareers = commonService
					.findSubscribedByAuthorityAndUseAndSubscribed(authority, userId);
			if(!subscribeCareers.isEmpty()){
				for (SubscribedCareers subscribeCareer : subscribeCareers) {
					SubscribedResponse careerResp = new SubscribedResponse();
					careerResp.setId(subscribeCareer.getCareerSubcat().getId());
					careerResp.setName(subscribeCareer.getCareerSubcat().getSubCategoryName());
					careerResp.setImgPath(subscribeCareer.getCareerSubcat().getSubcategoryImgPath());
					careerResp.setImgName(subscribeCareer.getCareerSubcat().getSubcategoryImgName());
					subcareerResps.add(careerResp);
				}	
			}
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got All  Subscribed Creers");
			resp.setResponse(subcareerResps);
		} catch (Exception e) {
			LOGGER.info("Exception Occurs while getting subscribed careers The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getSubscribeOrNot", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getSubscribedOrNot(@RequestParam("userId") Long userId,
			@RequestParam("userType") String userType, @RequestParam("subCatId") Long subCatId) throws Exception{
		ResponseObject resp = new ResponseObject();
		Map<String, Boolean> resps = new HashMap<String, Boolean>();
		try {
			Boolean subscribed;
			Authority authority = authorityService.findAdministratorsAuthorityByAuthorityCode(userType);
			CareerSubcategory subCat = commonService.findSubCategoryBySubCatId(subCatId);
			List<SubscribedCareers> subscribeCareer = commonService
					.findSubscribedByAuthorityAndUserAndCareerSubcat(authority, userId, subCat);
			if (!subscribeCareer.isEmpty()) {
				resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
				for (SubscribedCareers sc : subscribeCareer) {
					if (sc.getSubscribe().name().equals("UnSubscribed")) {
						subscribed = false;
						resps.put("subscribed", subscribed);
					} else if (sc.getSubscribe().name().equals("Subscribed")) {
						subscribed = true;
						resps.put("subscribed", subscribed);
					}
				}
			} else if (subscribeCareer.isEmpty()) {
				subscribed = false;
				resps.put("subscribed", subscribed);
				resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			}

			resp.setResponse(resps);
		} catch (Exception e) {
			LOGGER.info("Exception Occurs while getting Subscribed or not boolean The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getAllTrendingCareers", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getAllTrendingCareers() throws Exception{
		ResponseObject resp = new ResponseObject();
		List<SubscribedResponse> subcareerResps = new ArrayList<SubscribedResponse>();
		try {
			List<CareerSubcategory> trendingCareers = commonService.findTrendingCareers();
			if(!trendingCareers.isEmpty()){
					for (CareerSubcategory trendingcareer : trendingCareers) {
							SubscribedResponse careerResp = new SubscribedResponse();
							careerResp.setId(trendingcareer.getId());
							careerResp.setName(trendingcareer.getSubCategoryName());
							careerResp.setImgPath(trendingcareer.getSubcategoryImgPath());
							careerResp.setImgName(trendingcareer.getSubcategoryImgName());
							subcareerResps.add(careerResp);
					}	
			}
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got All Trending Creers");
			resp.setResponse(subcareerResps);
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while getting all ternding careers The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}
	@ResponseBody
	@RequestMapping(value = "/getDemand", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getdemandAndSupply(@RequestParam("subcategoryId") Long subcategoryId,
			@RequestParam("demandType") Integer demandType) throws Exception {
		ResponseObject resp = new ResponseObject();
		try {

			DemandResp dr = new DemandResp();
			List<String> labels = new ArrayList<String>();
			CareerSubcategory subcategory = commonService.findSubCategoryBySubCatId(subcategoryId);
			List<DemandYears> years = commonService.findAllYears();
			List<DemandLabels> demandLabels = commonService.findAllLabels();
			for (DemandLabels label : demandLabels) {
				labels.add(label.getLabel());
			}
			YearsResponse yr = new YearsResponse();
			  YearsResponse yr1 = new YearsResponse();
			for (DemandYears year : years) {
				if(year.getYear().equals("2020")){
					String color = commonService.getDemandSupplyBycareerByYear(year);
					yr.setId(year.getId());
					yr.setColor(color);
					yr.setYearLabel(year.getYear());
					yr.setData(commonService.getDemandSupplyByYearAndCareerCat(subcategory, year, demandType));
				}
			   if(year.getYear().equals("2030")){
					String color = commonService.getDemandSupplyBycareerByYear(year);
					yr1.setId(year.getId());
					yr1.setColor(color);
					yr1.setYearLabel(year.getYear());
				    yr1.setData(commonService.getDemandSupplyByYearAndCareerCat(subcategory, year, demandType));
				   }
			  		}
			 dr.setYear1Data(yr);
			 dr.setYear2Data(yr1);
		     dr.setId(1);
			      if(demandType==0){
				   dr.setLongTitle(subcategory.getJobsDescription());
				   }
				   if(demandType==1){
					   dr.setLongTitle(subcategory.getSalaryDescription());
				   }
			dr.setCountries(labels);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got All Demand And Supply ");
			resp.setResponse(dr);
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while getting demand The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getAllActivities", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getAllActivities(@RequestParam("userId") Long userId,
			@RequestParam("userType") String authority,@RequestParam("limit") Integer pageNo,@RequestParam("offset") Integer pages) throws Exception{
		ResponseObject resp = new ResponseObject();
		try{

			List<ActivityLog> activities = commonService.findAllActivitiesByLimit(userId, authority,pageNo,pages);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got All Activities ");
			resp.setResponse(activities);	
		}
			catch (Exception e) {
				LOGGER.info("Exception Occurs while getting all activities The Reason Was"+e.getMessage());
				throw new CommonException("SomeThing Went Wrong");
			}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);

	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/saveActivity", method = RequestMethod.POST)
	public ResponseEntity<ResponseObject> saveYoutueVideoActivity(@RequestParam("userId") Long userId,
			@RequestParam("userType") String authority, @RequestBody ActivityLog al)throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			al.setUser(userId);
			al.setAuthority(authority);
			 String title = null;
	         String subCareerName = null;
	         CareerSubcategory scs11 = null;
	         SubCategoryDetails subCatDetails=commonService.findBySubCategoryId(al.getMediaId());
			 ContributionDocs contDocs=commonService.findContributionDocById(al.getMediaId());
			 SevenSigmaDetails sigmaDetails=commonService.findSigmaDetails(al.getMediaId());
	         if(al.getSubcategory()!=null){
	        	 if(al.getSubcategory().getId()!=null){
	       		  scs11=commonService.findSubCategoryBySubCatId(al.getSubcategory().getId());
	        	 }
	         }
	          if(subCatDetails!=null && (subCatDetails.getTitle()!=null  || scs11.getSubCategoryName()!=null) && (al.getScreen().equals("subCategory") || al.getScreen().equals("recomm"))){
		          title=subCatDetails.getTitle();
			      subCareerName=scs11.getSubCategoryName();
			 }
			  if(contDocs!=null && (contDocs.getTitle()!=null || contDocs.getTitle()!="") && al.getScreen().equals("contribution")){
		          title=contDocs.getTitle();
		          if(contDocs.getContribution().getSubSubject().getId()!=null){
				      subCareerName=contDocs.getContribution().getSubSubject().getSubCategoryName();
		          }
			 }
			  if(sigmaDetails!=null && (sigmaDetails.getTitle()!=null || sigmaDetails.getTitle()!="") && al.getScreen().equals("sigma")){
		          title=sigmaDetails.getTitle();
				  subCareerName=sigmaDetails.getSevenSigma().getSigmaName();
			 }
			 else{
				 if(title==null || title==" "){
					 title="";
					if(subCareerName== null || subCareerName==" ") {
						 subCareerName="";
					 }
				 }
			 }
			 if(al.getAction().equals("Pdf")){
					al.setMessage("You just completed reading "+title+" article.Good going with "+subCareerName);
				}
			 if(al.getAction().equals("Video")){
					al.setMessage("You just completed watching "+title+" video.Good going with "+subCareerName);
				}
			 commonService.saveActivityLog(al);
			if(al.getSubcategory()!=null){
				saveRecommendations(userId, authority, al.getSubcategory().getId());
			}
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Successfully Saved Activity ");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while saving activity The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);

	}

	private void saveRecommendations(Long userId, String authority, Long subcatId) throws Exception{
		ResponseObject resp = new ResponseObject();
		try {
			Recommended recome = new Recommended();
			recome.setUser(userId);
			recome.setAuthority(authority);
			commonService.saveRecommendedVideos(recome, subcatId);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Successfully Saved Recommendation ");
		}  catch (Exception e) {
			LOGGER.info("Exception Occured while saving recommendation The Reason Was "+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
	}
	private void saveNotification(Notification ntf) {
		ntf.setStatus(Status.Active);
		commonService.saveNotification(ntf);
	}
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getAllNotifications", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getAllNotifications(
			@RequestParam(value = "userId", required = false) Long userId,
			@RequestParam(value = "userType", required = false) String authority,
			@RequestParam("limit") Integer pageNo,@RequestParam("offset") Long pages
			)  throws Exception{
		ResponseObject resp = new ResponseObject();
		try {
			List<Notification> notifications = new ArrayList<Notification>();
			notifications = commonService.findAllAcceptanceNotificationByLimit(userId, authority,pageNo,pages);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got All Notifications ");
			resp.setResponse(notifications);
		}  catch (Exception e) {
			LOGGER.info("Exception Occured while getting notifications The Reason Was "+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);

	}



	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getUpdateAPK", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getUpdateAPK() throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			Apk apk = commonService.findApkUpdateAPI();
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got APk ");
			resp.setResponse(apk);
		} catch (Exception e) {
			LOGGER.info("Exception Occured while getting update APK The Reason Was "+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getAllSubcategories", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getAllSubcategories(@RequestParam("userId") Long userId,
			@RequestParam("userType") String userType) throws Exception{
		ResponseObject resp = new ResponseObject();
		List<InterestResponse> intrResps = new ArrayList<InterestResponse>();
		try {
			List<UserInterests> userInterests = commonService.getAllUserInterests(userId, userType);
			if(!userInterests.isEmpty()){
				for (UserInterests catId : userInterests) {
					List<CategoryResponse> catResps = new ArrayList<CategoryResponse>();
					InterestResponse csr = new InterestResponse();
					//CareerCategory cc = studentService.findCategoryById(catId.getInterests().getId());
					csr.setTitle(catId.getInterests().getCategoryName());
					csr.setCategouryId(catId.getInterests().getId());
					List<CareerSubcategory> careerSubCategories = studentService
							.findCareerSubcategories(catId.getInterests().getId());
					if(!careerSubCategories.isEmpty()){
						for (CareerSubcategory csc : careerSubCategories) {
							CategoryResponse cr = new CategoryResponse();
							cr.setName(csc.getSubCategoryName());
							cr.setId(csc.getId());
							catResps.add(cr);
						}	
					}
					csr.setResult(catResps);
					intrResps.add(csr);
				}	
			}
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got All Subcategories ");
			resp.setResponse(intrResps);
		} catch (Exception e) {
			LOGGER.info("Exception Occured while getting all subcategories based on userId and userType The Reason Was "+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/saveAllRecSubcategories", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseObject> saveAllRecSubcategories(@RequestParam("userId") Long userId,
			@RequestParam("userType") String userType, @RequestBody List<InterestResponse> recSubcats) throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			for (InterestResponse recSubcat : recSubcats) {
				List<CategoryResponse> careerResponseobjs = recSubcat.getResult();
				for (CategoryResponse subCareerResponseobj : careerResponseobjs) {
					Recommended recobj = new Recommended();
					recobj.setUser(userId);
					recobj.setAuthority(userType);
					recobj.setStatus(Status.Active);
					//CareerCategory cc=commonService.findCategoryById(recSubcat.getCategouryId());
					//recobj.setCareerCat(cc);
					commonService.saveRecommendedVideos(recobj, subCareerResponseobj.getId());
				}
			}
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("subcategouries saves successfully");
		}  catch (Exception e) {
			LOGGER.info("Exception Occured while saving recsubcategories  The Reason Was "+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);

	}

	
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getServerBroke", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getServerBroke() throws Exception {
		ResponseObject resp = new ResponseObject();
		try {
			ServerBroke serverBrokeFlags = commonService.findserverBrokeFlag();
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got server broke ");
			resp.setResponse(serverBrokeFlags);
		} catch (Exception e) {
			LOGGER.info("Exception Occured while getting server broke The Reason Was "+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	/* common for all Images */
	private ResponseEntity<?> getImageResponse(String path, String type) throws Exception {
		ResponseObject resp = new ResponseObject();
       try{
		if (path != null && type!=null) {
			File file = new File(path);
			return ResponseEntity.ok().contentType(MediaType.valueOf(type)).body(Files.readAllBytes(file.toPath()));
		}
		else {
			resp.setStatusCode(StudentResponseCodes.FAILED_CODE);
			resp.setStatusMessage("Image Not Found");
			return ResponseEntity.ok().body(resp);
		}
	}
	 catch (Exception e) {
			LOGGER.info("Exception Occured while getting image response The Reason Was "+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
	}
	@ResponseBody
	  @JsonView(Views.Public.class)
	  @RequestMapping(value="/getHGTInstitutes",method=RequestMethod.GET)
	  public ResponseEntity<ResponseObject> getHGTAbroadCollges(@RequestParam("subcategoryId") Long subcategoryId,@RequestParam("country") String country) 
	throws Exception{
	             ResponseObject resp = new ResponseObject();
	             List<InstituteResponse> iResps= new ArrayList<InstituteResponse>();
	           try{
	        	   CareerSubcategory careerSubcategory= commonService.findSubCategoryBySubCatId(subcategoryId);
 
	        	   List<Institutions> instituitions=new ArrayList<Institutions>();
	        	   if(country.equals("India")){
	        		   instituitions =commonService.findSubCategoryCollegesBySubCatId(subcategoryId,Country.India);
	        	   }
	        	   else if(country.equals("Abroad")){
	        		   instituitions =commonService.findSubCategoryCollegesBySubCatId(subcategoryId,Country.Abroad);
	        	   }
	        	   if(!instituitions.isEmpty()){
	        		   for (Institutions institute : instituitions) {
	                    	 InstituteResponse iResp= new InstituteResponse();
	      		            // List<InstitutionCourses> courses= commonService.getCoursesByInstitution(institute);
	                    	 List<InstitutionCourses> courses= commonService.getCoursesByInstitutionAndSubcat(institute, careerSubcategory);
	      		             iResp.setId(institute.getId());
	                    	 iResp.setName(institute.getName());
	                    	 iResp.setResult(courses);
	                    	 iResps.add(iResp);
	                     }	   
	        	   }
	        	resp.setResponse(iResps);
	            resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
	            resp.setStatusMessage("Got  Colleges HGT Successfully");
	                }
	          catch(Exception ex){
	  			LOGGER.info("Exception Occured while getting HGT institutes/colleges The Reason Was "+ex.getMessage());
	  			throw new CommonException("SomeThing Went Wrong");
	           }
	           return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	  }
/*	@RequestMapping(value = "/gets7ImageUrls", method = RequestMethod.GET)
	public ResponseEntity<?> gets7ImageUrls(@RequestParam("userId") long userId, @RequestParam("userType") String type,
			@RequestParam("imgPath") String imgPath, @RequestParam("imgName") String imgName) throws Exception {
		String path = null;
		String imgType = null;
		try {
			if (type.equals("ROLE_STUDENT")) {
				Student student = studentService.findByStudentId(userId);
				path = ViewConstants.SAVE_LOCATION + student.getStudentImgPath() + "/" + student.getStudentImgName();
				imgType = student.getType();
			} else if (type.equals("ROLE_MENTOR")) {
				Mentor mentor = mentorService.findById(userId);
				path = ViewConstants.SAVE_LOCATION + mentor.getImgPath() + "/" + mentor.getImgName();
				imgType = mentor.getType();

			} else if (type.equals("ROLE_PARENT")) {
				Parents parent = parentService.findParentById(userId);
				path = ViewConstants.SAVE_LOCATION + parent.getParentImgPath() + "/" + parent.getParentImgName();
				imgType = parent.getType();

			} else if (type.equals("ROLE_SCHOOLTEACHER")) {
				SchoolTeacher teacher = schoolTeacherService.findById(userId);
				path = ViewConstants.SAVE_LOCATION + teacher.getTeacherImgPath() + "/" + teacher.getTeacherImgName();
				imgType = teacher.getType();

			}
			 if(imgType==null || imgType.isEmpty()){
					CommonImages ci=commonService.findCommonImagesByScreen("user");
					path = ViewConstants.SAVE_LOCATION + ci.getImgPath()+ "/"
							+ ci.getImgName();
					imgType = ci.getImgType();

				}

		} catch (Exception e) {
			LOGGER.info("Exception Occurs while getting s7 image url The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return getImageResponse(path, imgType);
	}
	@RequestMapping(value = "/getStudentImage", method = RequestMethod.GET)
	public ResponseEntity<?> getStudentImage(@RequestParam("studentId") long studentId,
			@RequestParam(value = "imgPath", required = false) String imgPath,
			@RequestParam(value = "imgName", required = false) String imgName) throws Exception {
		String path=null;
		String imgType=null;
		try{
		Student student = studentService.findByStudentId(studentId);
		 path = ViewConstants.SAVE_LOCATION + student.getStudentImgPath() + "/" + student.getStudentImgName();
		 imgType=student.getType();
		 if(imgType==null || imgType.isEmpty()){
			CommonImages ci=commonService.findCommonImagesByScreen("user");
			path = ViewConstants.SAVE_LOCATION + ci.getImgPath()+ "/"
					+ ci.getImgName();
			imgType = ci.getImgType();
		}
		}
		 catch (Exception e) {
		    LOGGER.info("Exception Occurs while getting student image The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		
		return getImageResponse(path, imgType);

	}

	@RequestMapping(value = "/getParentImage", method = RequestMethod.GET)
	public ResponseEntity<?> getParentImage(@RequestParam("parentId") long parentId,
			@RequestParam(value = "imgPath", required = false) String imgPath,
			@RequestParam(value = "imgName", required = false) String imgName, HttpServletResponse response)
			throws Exception {
		String path=null;
		String imgType=null;
		try{
		Parents parents = parentService.findParentById(parentId);
		path= ViewConstants.SAVE_LOCATION + parents.getParentImgPath() + "/" + parents.getParentImgName();
		imgType=parents.getType();
		 if(imgType==null || imgType.isEmpty()){
			CommonImages ci=commonService.findCommonImagesByScreen("user");
			path = ViewConstants.SAVE_LOCATION + ci.getImgPath()+ "/"
					+ ci.getImgName();
			imgType = ci.getImgType();

		}
		}
		catch (Exception e) {
			LOGGER.info("Exception Occurs while getting parent image The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return getImageResponse(path, imgType);
	}

	@RequestMapping(value = "/getTeacherImage", method = RequestMethod.GET)
	public ResponseEntity<?> getTeacherImage(@RequestParam("teacherId") long teacherId,
			@RequestParam(value = "imgPath", required = false) String imgPath,
			@RequestParam(value = "imgName", required = false) String imgName, HttpServletResponse response)
			throws Exception {
		String path=null;
		String imgType=null;
		try{
		SchoolTeacher schoolTeacher = schoolTeacherService.findById(teacherId);
		 path = ViewConstants.SAVE_LOCATION + schoolTeacher.getTeacherImgPath() + "/"
				+ schoolTeacher.getTeacherImgName();
		 imgType=schoolTeacher.getType();
		 if(imgType==null || imgType.isEmpty()){
			CommonImages ci=commonService.findCommonImagesByScreen("user");
			path = ViewConstants.SAVE_LOCATION + ci.getImgPath()+ "/"
					+ ci.getImgName();
			imgType = ci.getImgType();

		}
		}
	     catch (Exception e) {
		  LOGGER.info("Exception Occurs  while getting teacher image The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return getImageResponse(path,imgType);

	}

	@RequestMapping(value = "/getMentorImage", method = RequestMethod.GET)
	public ResponseEntity<?> getMentorImage(@RequestParam("mentorId") long mentorId,
			@RequestParam(value = "imgPath", required = false) String imgPath,
			@RequestParam(value = "imgName", required = false) String imgName, HttpServletResponse response)
			throws Exception {
		String path=null;
		String imgType=null;
		try{
		
		Mentor mentor = commonService.getMentorDetails(mentorId);
		 path = ViewConstants.SAVE_LOCATION + mentor.getImgPath() + "/" + mentor.getImgName();
		 imgType=mentor.getType();
		 if(imgType==null || imgType.isEmpty()) {
			CommonImages ci=commonService.findCommonImagesByScreen("user");
			path = ViewConstants.SAVE_LOCATION + ci.getImgPath()+ "/"
					+ ci.getImgName();
			imgType = ci.getImgType();

		}
	}
     catch (Exception e) {
			LOGGER.info("Exception Occurs  while getting mentor image The Reason Was"+e.getMessage());
		throw new CommonException("SomeThing Went Wrong");
	}
		return getImageResponse(path, imgType);

	}
@RequestMapping(value = "/categoryImage", method = RequestMethod.GET)
	public ResponseEntity<?> getCatImage(@RequestParam("catId") long catId, @RequestParam("imgPath") String imgPath,
			@RequestParam("imgName") String imgName) throws Exception {
		String path=null;
		String imgType=null;
		try{
		CareerCategory category = studentService.findCategoryById(catId);
		 path = ViewConstants.SAVE_LOCATION + category.getCategoryImagPath() + "/"
				+ category.getCategoryImagName();
		 imgType=category.getType();
	}
	 catch (Exception e) {
			LOGGER.info("Exception Occurs while getting category image The Reason Was"+e.getMessage());
		throw new CommonException("SomeThing Went Wrong");
	}
		return getImageResponse(path, imgType);
	}

	@RequestMapping(value = "/subCategoryImage", method = RequestMethod.GET)
	public ResponseEntity<?> getSubCatImage(@RequestParam("subcategoryId") long subCatId,
			@RequestParam("imgPath") String imgPath, @RequestParam("imgName") String imgName)
			throws Exception {
		String path=null;
		String imgType=null;
		try{
		CareerSubcategory subCategory = studentService.findCareerSubCategoryById(subCatId);
		 path = ViewConstants.SAVE_LOCATION + subCategory.getSubcategoryImgPath() + "/"
				+ subCategory.getSubcategoryImgName();
		 imgType=subCategory.getType();
		}
		 catch (Exception e) {
				LOGGER.info("Exception Occurs while getting subcategory image The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		
		return getImageResponse(path, imgType);
	}

		@RequestMapping(value = "/getInterestImage", method = RequestMethod.GET)
	public ResponseEntity<?> getInterestImage(@RequestParam("interestId") long interestId,
			@RequestParam("imgPath") String imgPath, @RequestParam("imgName") String imgName)
			throws Exception {
		String path=null;
		String imgType=null;
		try{
		 CareerCategory category = studentService.findCategoryById(interestId);
		 path = ViewConstants.SAVE_LOCATION + category.getIntrstImagPath() + "/"
				+ category.getIntrstImagName();
		 imgType=category.getIntrstType();
	}
    catch (Exception e) {
		LOGGER.info("Exception Occurs  while getting interest image The Reason Was"+e.getMessage());
		throw new CommonException("SomeThing Went Wrong");
	}
		return getImageResponse(path, imgType);

	}
@RequestMapping(value = "/sevenSigmaImage", method = RequestMethod.GET)
	public ResponseEntity<?> getSevenSigmaImage(@RequestParam("sigmaId") long sigmaId,
			@RequestParam("imgPath") String imgPath, @RequestParam("imgName") String imgName) throws Exception {
		// SevenSigma document = sigmaService.findSigmaById(sigmaId);
		String path = null;
		String imgType = null;
		try {
			SevenSigma document = sigmaService.findSigmaById(sigmaId);
			path = ViewConstants.SAVE_LOCATION + document.getSigmaIconPath() + "/" + document.getSigmaIconName();
			imgType = document.getType();
		} catch (Exception e) {
			LOGGER.info("Exception Occurs while getting seven sigma image The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return getImageResponse(path, imgType);
	}
@RequestMapping(value = "/contView", method = RequestMethod.GET)
	public ResponseEntity<?> contributionDocs(@RequestParam("contributionDocId") long mediaId)
			throws Exception {
		String path = null;
		String contentType = null;
		try{
			ContributionDocs contributionDocs = studentService.findContributionById(mediaId);
			path = ViewConstants.SAVE_LOCATION + contributionDocs.getContDocPath() + "/"
					+ contributionDocs.getContDocName();
			contentType = contributionDocs.getType();
		} catch (Exception e) {
    			LOGGER.info("Exception Occurs The Reason Was"+e.getMessage());
				throw new CommonException("SomeThing Went Wrong");
			}
		return getImageResponse(path, contentType);

			}
	@RequestMapping(value = "/contributionDetail", method = RequestMethod.GET)
	public  ResponseEntity<?> getContributionDetail(@RequestParam("contributionId") long contributionId,
			HttpServletResponse response) throws Exception {
		String path=null;
		String contentType=null;
		try{
		ContributionDocs contributionDocs = studentService.findContributionById(contributionId);
		 path = ViewConstants.SAVE_LOCATION + contributionDocs.getContDocPath() + "/"
				+ contributionDocs.getContDocName();
		} catch (Exception e) {
			LOGGER.info("Exception Occurs The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}
		return getImageResponse(path, contentType);
	}
@RequestMapping(value = "/getCommonImages", method = RequestMethod.GET)
	public ResponseEntity<?> getCommonImages(@RequestParam("screen") String screen,
			@RequestParam("imgPath") String imgPath, @RequestParam("imgName") String imgName)
			throws Exception {
		String path=null;
		String imgType=null;
		try{
		
		//CareerSubcategory careerSubCategory = commonService.findSubCategoryBySubCatId(subCatId);
			CommonImages ci=commonService.getCommonImage(screen);
	   path = ViewConstants.SAVE_LOCATION + ci.getImgPath() + "/"
				+ ci.getImgName();
		 imgType=ci.getImgType();
	}
	 catch (Exception e) {
	   LOGGER.info("Exception Occurs while getting common images The Reason Was"+e.getMessage());
		throw new CommonException("SomeThing Went Wrong");
	}
		return getImageResponse(path, imgType);

	}
@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getSRAAbilities", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getSRAAbilities(@RequestParam("subcategoryId") Long subcategoryId) throws Exception {
		ResponseObject resp = new ResponseObject();
		try {

			List<Abilities> abilities = commonService.findSubCategoryAbilitiesBySubCategory(subcategoryId);
			List<AbilityResponse> abilityResps = new ArrayList<AbilityResponse>();
			if(!abilities.isEmpty()){
				for (Abilities ability : abilities) {
					AbilityResponse abilityResp = new AbilityResponse();
					abilityResp.setId(ability.getId());
					abilityResp.setAbility(ability.getAbility());
					abilityResps.add(abilityResp);
				}	
			}
			resp.setResponse(abilityResps);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got Abilities SRA Successfully");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs getting SRAAbilities The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getHGTJobRoles", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getHGTJobRoles(@RequestParam("subcategoryId") Long subcategoryId) throws Exception{
		ResponseObject resp = new ResponseObject();
		Map<String, String> hwtCourses = new HashMap<String, String>();
		try {
			CareerSubcategory subCategory = commonService.findSubCategoryBySubCatId(subcategoryId);
			hwtCourses.put("jobRoles", subCategory.getJobRoles());
			resp.setResponse(hwtCourses);
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got Abroad HGT Successfully");
		}  catch (Exception e) {
			LOGGER.info("Exception Occurs while getting HGT JOb Roles The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/getInterests", method = RequestMethod.GET)
	public ResponseEntity<ResponseObject> getInterests() throws Exception{
		ResponseObject resp = new ResponseObject();
		try {
			resp.setResponse(commonService.getAllInterests());
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setStatusMessage("Got All Interests Successfully");
		} catch (Exception e) {	
			LOGGER.info("Exception Occurs  while getting interests The Reason Was"+e.getMessage());
			throw new CommonException("SomeThing Went Wrong");
		}	
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}
*/
	
}
