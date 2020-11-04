package com.wellsfargo.fsd.intrtracker.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.fsd.intrtracker.exception.InterviewTrackerException;
import com.wellsfargo.fsd.intrtracker.model.AttendeeModel;
import com.wellsfargo.fsd.intrtracker.model.InterviewModel;
import com.wellsfargo.fsd.intrtracker.model.UserModel;
import com.wellsfargo.fsd.intrtracker.service.InterviewService;

@RestController
@RequestMapping("/interview")
public class InterviewrestController {
	
	@Autowired
	private InterviewService interviewService;
	
	
	@GetMapping("/showInterviews")
	public ResponseEntity<Set<InterviewModel>> getAllInterviews(){
		return new ResponseEntity<Set<InterviewModel>>(interviewService.getAllInterviewDetails(),HttpStatus.OK);
	}
	
	@GetMapping("/count")
	public ResponseEntity<String> getCountOfInterviews(){
		return new ResponseEntity<>(interviewService.getInterviewCount(),HttpStatus.OK);
	}

	@GetMapping("/showAttendee/{id}")
	public ResponseEntity<Set<UserModel>> showAttendees(@PathVariable("id")int interviewId) throws InterviewTrackerException{
		Set<UserModel> showUsers = interviewService.showUsers(interviewId);
		if(null != showUsers && !showUsers.isEmpty())
		{
			return new ResponseEntity<>(showUsers,HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/showInterviews/{InterviewName}/{InterviewerName}")
	public ResponseEntity<List<InterviewModel>> getInterviews(@PathVariable("InterviewName")String interviewName, @PathVariable("InterviewerName")String interviewerName){
		ResponseEntity<List<InterviewModel>> resp=null;
		List<InterviewModel> interview = interviewService.getinterview(interviewName, interviewerName);
		
		if(interview!=null) {
			resp =new ResponseEntity<>(interview,HttpStatus.OK);
		}else {
			resp =new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return resp;
	}
	
	@PostMapping
	public ResponseEntity<InterviewModel> createInterview(@RequestBody @Valid InterviewModel interview,BindingResult result) throws InterviewTrackerException{
		if(result.hasErrors()) {
			throw new InterviewTrackerException(GlobalExceptionController.errMsgFrom(result));
		}
		return new ResponseEntity<>(interviewService.add(interview),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteInterview(@PathVariable("id")int interviewId) throws InterviewTrackerException{
		interviewService.deleteInterview(interviewId);		
		return new ResponseEntity<>("Interview Deleted Successfully",HttpStatus.OK);
	}
	
	@PutMapping("/addAttendee")
	public ResponseEntity<String> addAttendee(@RequestBody @Valid AttendeeModel attendee,BindingResult result) throws InterviewTrackerException{
		if(result.hasErrors()) {
			throw new InterviewTrackerException(GlobalExceptionController.errMsgFrom(result));
		}
		return new ResponseEntity<>(interviewService.addAttendee(attendee),HttpStatus.OK);
	}
	
	@PutMapping("/updateStatus/{interviewid}/{status}")
	public ResponseEntity<InterviewModel> updateStatus(@PathVariable("interviewid")int interviewId,@PathVariable("status")String status) throws InterviewTrackerException{
		return new ResponseEntity<>(interviewService.updateStatus(interviewId, status),HttpStatus.OK);
	}

}
