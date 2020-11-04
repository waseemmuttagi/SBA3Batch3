package com.wellsfargo.fsd.intrtracker.service;

import java.util.List;
import java.util.Set;

import com.wellsfargo.fsd.intrtracker.exception.InterviewTrackerException;
import com.wellsfargo.fsd.intrtracker.model.AttendeeModel;
import com.wellsfargo.fsd.intrtracker.model.InterviewModel;
import com.wellsfargo.fsd.intrtracker.model.UserModel;

public interface InterviewService {
	
InterviewModel add(InterviewModel interview) throws InterviewTrackerException;
	
	boolean deleteInterview(int interviewId) throws InterviewTrackerException;
	
	InterviewModel updateStatus(Integer interviewid, String status) throws InterviewTrackerException;
	
	List<InterviewModel> getinterview(String interviewName, String interviewerName);
	
	String getInterviewCount();
	
	Set<InterviewModel> getAllInterviewDetails();
	
	Set<UserModel> showUsers(int interviewId) throws InterviewTrackerException;

	String addAttendee(AttendeeModel attendee) throws InterviewTrackerException;

	InterviewModel getInterviewById(int interviewId);;

}
