package com.wellsfargo.fsd.intrtracker.service;

import java.util.List;

import com.wellsfargo.fsd.intrtracker.exception.InterviewTrackerException;
import com.wellsfargo.fsd.intrtracker.model.UserModel;


public interface UserService {
UserModel add(UserModel user) throws InterviewTrackerException;
	
	boolean deleteUser(int userId) throws InterviewTrackerException;
	
	UserModel getUser(int userId);
	
	List<UserModel> getAllUsers();


}
