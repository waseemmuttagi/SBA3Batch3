package com.wellsfargo.fsd.intrtracker.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity {
	
	@Id
	@Column
	private Integer userId;
	
	@Column
	private String fname;
	
	@Column
	private String lname;
	
	@Column
	private String email;
	
	@Column
	private String mobile;

	public UserEntity() {
	}

	public UserEntity(Integer userId, String fname, String lname, String email, String mobile) {
		super();
		this.userId = userId;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.mobile = mobile;
	}
	
	@ManyToMany(mappedBy = "attendees",fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Set<InterviewEntity> interviews = new HashSet<>();

	public Set<InterviewEntity> getInterviews() {
		return interviews;
	}

	public void setInterviews(Set<InterviewEntity> interviews) {
		this.interviews = interviews;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	 public void removeInterview(InterviewEntity interview) {
	        this.interviews.remove(interview);
	        interview.getAttendees().remove(this);
	    }

	    public void removeInterviews() {
	        for(InterviewEntity interview : new HashSet<>(this.interviews)) {
	        	removeInterview(interview);
	        }
	    }
	    
	    
}
