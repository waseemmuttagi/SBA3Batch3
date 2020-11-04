package com.wellsfargo.fsd.intrtracker.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.intrtracker.dao.InterviewDao;
import com.wellsfargo.fsd.intrtracker.dao.UserDao;
import com.wellsfargo.fsd.intrtracker.entity.InterviewEntity;
import com.wellsfargo.fsd.intrtracker.entity.UserEntity;
import com.wellsfargo.fsd.intrtracker.exception.InterviewTrackerException;
import com.wellsfargo.fsd.intrtracker.model.AttendeeModel;
import com.wellsfargo.fsd.intrtracker.model.InterviewModel;
import com.wellsfargo.fsd.intrtracker.model.UserModel;


@Service
public class InterviewServiceImpl implements InterviewService {
	
	@Autowired
	private InterviewDao intwRepo;
	
	@Autowired
	private UserDao userRepo;
	
	private InterviewEntity toInterviewEntity(InterviewModel intwModel) {
			return new InterviewEntity(intwModel.getInterviewId(),intwModel.getInterviewrName(),
					intwModel.getInterviewName(),intwModel.getUserSkills(),intwModel.getDate(),intwModel.getTime(),intwModel.getInterviewStatus(),intwModel.getRemarks());
	}
	
	private InterviewModel toInterviewModel(InterviewEntity entity) {		
			return new InterviewModel(entity.getInterviewId(), entity.getInterviewrName(), entity.getInterviewName(), entity.getUserSkills(),entity.getDate(),entity.getTime(), entity.getInterviewStatus(), entity.getRemarks());
	}
	
	
	  private UserModel toUserModel(UserEntity entity) {
			return new UserModel(entity.getUserId(),entity.getFname(), entity.getLname(), entity.getEmail(), entity.getMobile());
		}
	  
	@Override
	public InterviewModel add(InterviewModel interview) throws InterviewTrackerException {
		if(interview!=null) {
            if(intwRepo.existsById(interview.getInterviewId())) {
                throw new InterviewTrackerException("Interview Id already used!");
            }
        	interview = toInterviewModel(intwRepo.save(toInterviewEntity(interview)));
        }
        return interview;
	}

	@Override
	public boolean deleteInterview(int interviewId) throws InterviewTrackerException {
		if(!intwRepo.existsById(interviewId)) {
			throw new InterviewTrackerException("Interview Id Not Found!");
		}		
		intwRepo.deleteById(interviewId);
		return false;
	}

	@Override
	public InterviewModel updateStatus(Integer interviewId, String status) throws InterviewTrackerException {
		if(!intwRepo.existsById(interviewId)) {
			throw new InterviewTrackerException("Interview Id Not Found!");
		}
		InterviewModel interview = getInterviewById(interviewId);
		interview.setInterviewStatus(status);
        intwRepo.save(toInterviewEntity(interview));
		return toInterviewModel(toInterviewEntity(interview));
	}

	@Override
	public List<InterviewModel> getinterview(String interviewName, String interviewerName) {
		List<InterviewEntity> interviewList = intwRepo.findByNameAndInterviewer(interviewName, interviewerName);
		List<InterviewModel> interviewModelList = interviewList.stream().map(e -> toInterviewModel(e)).collect(Collectors.toList());
		return interviewModelList;
	}

	@Override
	public String getInterviewCount() {
		Set<InterviewEntity> entities=  new HashSet<InterviewEntity>(intwRepo.findAll());
		return "Total no. of Interviews: " + entities.size();
	}

	@Override
	public Set<InterviewModel> getAllInterviewDetails() {
		Set<InterviewEntity> entities= new HashSet<InterviewEntity>(intwRepo.findAll());
		Set<InterviewModel> models=null;
		if(entities!=null && !entities.isEmpty()) {
			models = entities.stream().map(e -> toInterviewModel(e)).collect(Collectors.toSet());
		}
		return models;
	}

	
	  @Override 
	  public Set<UserModel> showUsers(int interviewId) throws InterviewTrackerException 
	  { 
		  if(!intwRepo.existsById(interviewId))
		  { throw new InterviewTrackerException("Interview Id Not Found!"); 
		  } 
		 Set<UserModel> attendees = intwRepo.findById(interviewId).orElse(null).getAttendees().
				 stream().map(e -> toUserModel(e)).collect(Collectors.toSet());
		return attendees; 
		 
	  }
	 

	@Override
	public InterviewModel getInterviewById(int iId) {
		InterviewEntity entity = intwRepo.findById(iId).orElse(null);
		System.out.println("entity by jyoo " + entity.getAttendees());
        return entity!=null?toInterviewModel(entity):null;
	}

	
	@Override
	@Transactional
	public String addAttendee(AttendeeModel attendee) throws InterviewTrackerException {
		if(attendee!=null) {
			if(!userRepo.existsById(attendee.getUserId())) {
				throw new InterviewTrackerException("User Not Found");
			}
	        if(!intwRepo.existsById(attendee.getInterviewId())) {
	            throw new InterviewTrackerException("Interview Id Not Found!");
	        }
	        
	        InterviewEntity interview = intwRepo.findById(attendee.getInterviewId()).orElse(null);
	       // InterviewModel interview = getInterviewById(attendee.getInterviewId());
	        System.out.println("interview  " + interview);
	        for(UserEntity user: interview.getAttendees()) {
	        	System.out.println("user  " + user);
	        	if(user.getUserId() == attendee.getUserId()) {
	        		throw new InterviewTrackerException("User Id already exists on Interview!");
	        	}
	        }
	        
	        System.out.println("after interview  " + interview);
	        Set<UserEntity> users=interview.getAttendees();
	        System.out.println("users  " + users);
	        UserEntity userModel = userRepo.findById(attendee.getUserId()).orElse(null);
	        System.out.println("userModel  " + userModel);
	        users.add(userModel);
	        interview.setAttendees(users);
	        try
	        {
	        intwRepo.save(interview);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	        }
	        return "User with Id: " +  userModel + " added successfully to Interview";
		}
		else
		{
			throw new InterviewTrackerException("Please provide data ");
		}
	}

}
