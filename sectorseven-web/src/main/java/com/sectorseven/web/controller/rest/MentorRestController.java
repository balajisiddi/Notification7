package com.sectorseven.web.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonView;
import com.sectorseven.model.Enum.Status;
import com.sectorseven.model.common.MentorFollowers;
import com.sectorseven.model.common.ResponseObject;
import com.sectorseven.model.util.Views;
import com.sectorseven.service.common.StudentResponseCodes;
import com.sectorseven.service.student.CommonService;

@Controller
@RequestMapping(value = "/mntr")
public class MentorRestController {

	@Autowired
	private CommonService commonService;

	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping("/queriesById")
	public ResponseEntity<ResponseObject> getsAllMentorQuestionsById(@RequestParam("queryId") long queryId) {
		ResponseObject resp = new ResponseObject();
		try {
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setResponse(commonService.findStudentQueriesById(queryId));

		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatusMessage("Get All StudentQueries failed. Reason: " + e.getMessage());
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}
	
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping("/getAllStudentQuestionsByMentor")
	public   ResponseEntity<ResponseObject> getAllStudentQuestionsByMentor(@RequestParam("mentorId") long menotrId) {
		ResponseObject resp = new ResponseObject();
		try {
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setResponse(commonService.findByMentor(menotrId));

		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatusMessage("Get All StudentQueriesMentor failed. Reason: " + e.getMessage());
		}
		
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}
	
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping("/studentQuceries")
	public ResponseEntity<ResponseObject> getAllMentorQuestions(Model model) {
		ResponseObject resp = new ResponseObject();
		try {
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setResponse(commonService.findByAskMentorByStatus(Status.Inactive));

		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatusMessage("Get StudentQuerie failed. Reason: " + e.getMessage());
		}

		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}
	
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping(value = "/requestMentor", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public ResponseEntity<ResponseObject> saveMentorFollower(@RequestParam("mentorId") long mentorId, @RequestParam("studentId") long studentId
		) {
		MentorFollowers followers=new MentorFollowers();
		ResponseObject resp = new ResponseObject();
		try {
			followers.setStatus(Status.Inactive);
			commonService.saveMentorFollower(followers,studentId,mentorId);
			resp.setResponse("saved request success");
 			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);

		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatusMessage("save erequest failed. Reason: " + e.getMessage());
		}
		
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	// Find all the mentor followers
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping("/findAllMentorFollowers")
	public ResponseEntity<ResponseObject> getAllMentorFollowers(Model model) {
		ResponseObject resp = new ResponseObject();
		try {
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setResponse(commonService.findallMentorFollowers());

		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatusMessage("Get All MentorFollowers failed. Reason: " + e.getMessage());
		}
		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}

	// Find all the mentor followers by id
	@ResponseBody
	@JsonView(Views.Public.class)
	@RequestMapping("/findAllMentorFollowersById")
	public ResponseEntity<ResponseObject> getAllMentorFollowersById(@RequestParam("fId") long followerId) {
		ResponseObject resp = new ResponseObject();
		try {
			resp.setStatusCode(StudentResponseCodes.SUCCESS_CODE);
			resp.setResponse(commonService.findallMentorFollowersById(followerId));

		} catch (Exception e) {
			e.printStackTrace();
			resp.setStatusMessage("Get All MentorFollowers failed. Reason: " + e.getMessage());
		}

		return new ResponseEntity<ResponseObject>(resp, HttpStatus.OK);
	}}
