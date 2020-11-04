package com.wellsfargo.fsd.intrtracker.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InterviewModel {
	
	@NotNull(message = "Interview Id is manadatory")
	private Integer interviewId;
	
	@NotBlank(message = "Interviewer Name is mandatory")
	@Size(min = 5,max=30,message = "Interviewer Name should be between 5 to 30 charcaters")
	private String interviewrName;
	
	
	@NotBlank(message = "Interview Name is mandatory")
	@Size(min = 3,max=30,message = "Interview Name should be between 3 to 30 charcaters")
	private String interviewName;
	
	
	@NotBlank(message = "User Skills is mandatory")
	@Size(min = 5,max=30,message = "UserSkills should be between 5 to 30 charcaters")
	private String userSkills;
	
	
	
	private LocalDate date;
	
	
	
	private LocalTime time;
	
	
	@NotBlank(message = "Interview status is mandatory")
	@Size(min = 5,max=100,message = "Interview Status should be between 5 to 100 charcaters")
	private String interviewStatus;
	
	@NotBlank(message = "Remarks is mandatory")
	@Size(min = 5,max=100,message = "remarks should be between 5 to 100 charcaters")
	private String remarks;
	
	
	public InterviewModel() {
		super();
	}
	
	
	public InterviewModel(Integer interviewId, String interviewrName, String interviewName, String userSkills,
			LocalDate date, LocalTime time, String interviewStatus, String remarks) {
		super();
		this.interviewId = interviewId;
		this.interviewrName = interviewrName;
		this.interviewName = interviewName;
		this.userSkills = userSkills;
		this.date = date;
		this.time = time;
		this.interviewStatus = interviewStatus;
		this.remarks = remarks;
	}


	public Integer getInterviewId() {
		return interviewId;
	}
	public void setInterviewId(Integer interviewId) {
		this.interviewId = interviewId;
	}
	public String getInterviewrName() {
		return interviewrName;
	}
	public void setInterviewrName(String interviewrName) {
		this.interviewrName = interviewrName;
	}
	public String getInterviewName() {
		return interviewName;
	}
	public void setInterviewName(String interviewName) {
		this.interviewName = interviewName;
	}
	public String getUserSkills() {
		return userSkills;
	}
	public void setUserSkills(String userSkills) {
		this.userSkills = userSkills;
	}
	
	public String getInterviewStatus() {
		return interviewStatus;
	}
	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}


	public LocalTime getTime() {
		return time;
	}


	public void setTime(LocalTime time) {
		this.time = time;
	}


	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Valid
	private Set<UserModel> attendee;

	public Set<UserModel> getAttendee() {
		return attendee;
	}


	public void setAttendee(Set<UserModel> attendee) {
		this.attendee = attendee;
	}


	@Override
	public String toString() {
		return "InterviewModel [interviewId=" + interviewId + ", interviewrName=" + interviewrName + ", interviewName="
				+ interviewName + ", userSkills=" + userSkills + ", date=" + date + ", interviewStatus="
				+ interviewStatus + ", remarks=" + remarks + "]";
	}
}
