package com.wellsfargo.fsd.intrtracker.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="interview")
public class InterviewEntity {
	
	@Id
	@Column
	private Integer interviewId;
	
	@Column
	private String interviewrName;
	
	
	@Column
	private String interviewName;
	
	
	@Column
	private String userSkills;
	
	
	@Column
	private LocalDate date;
	
	
	@Column
	private LocalTime time;
	
	
	@Column
	private String interviewStatus;
	
	@Column
	private String remarks;
	
	
	public InterviewEntity() {
		super();
	}
	
	
	public InterviewEntity(Integer interviewId, String interviewrName, String interviewName, String userSkills,
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

	 @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinTable(name="interview_schedule",joinColumns = @JoinColumn(name= "interviewId"),
	inverseJoinColumns = @JoinColumn(name="userId")  )
	private Set<UserEntity> attendees = new HashSet<>();

	public Set<UserEntity> getAttendees() {
		return attendees;
	}


	public void setAttendees(Set<UserEntity> attendees) {
		this.attendees = attendees;
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

	@Override
	public String toString() {
		return "InterviewModel [interviewId=" + interviewId + ", interviewrName=" + interviewrName + ", interviewName="
				+ interviewName + ", userSkills=" + userSkills + ", date=" + date + ", interviewStatus="
				+ interviewStatus + ", remarks=" + remarks + "]";
	}
}
