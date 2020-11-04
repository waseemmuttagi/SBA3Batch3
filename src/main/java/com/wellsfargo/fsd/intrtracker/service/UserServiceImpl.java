package com.wellsfargo.fsd.intrtracker.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.intrtracker.dao.InterviewDao;
import com.wellsfargo.fsd.intrtracker.dao.UserDao;
import com.wellsfargo.fsd.intrtracker.entity.UserEntity;
import com.wellsfargo.fsd.intrtracker.exception.InterviewTrackerException;
import com.wellsfargo.fsd.intrtracker.model.UserModel;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userRepo;

	@Autowired
	private InterviewDao intwRepo;
	
	private UserEntity toEntity(UserModel model) {
		return new UserEntity(model.getUserId(), model.getFname(), model.getLname(),model.getEmail(), model.getMobile());
	}
	
	private UserModel toModel(UserEntity entity) {
		return new UserModel(entity.getUserId(),entity.getFname(), entity.getLname(), entity.getEmail(), entity.getMobile());
	}

	
	@Override
	@Transactional
	public UserModel add(UserModel user) throws InterviewTrackerException {
        if(user!=null) {
            if(userRepo.existsById(user.getUserId())) {
                throw new InterviewTrackerException("User Id already used!");
            }
            
            user = toModel(userRepo.save(toEntity(user)));
        }
        return user;
	}

	@Override
	@Transactional
	public boolean deleteUser(int userId) throws InterviewTrackerException {
		if(!userRepo.existsById(userId)) {
			throw new InterviewTrackerException("User Not Found");
		}
		UserEntity userEntity = userRepo.findById(userId).orElse(null);

		userEntity.removeInterviews();
		userRepo.flush();
		userRepo.delete(userEntity);
		return true;
	}

	@Override
	public UserModel getUser(int userId) {
        UserEntity entity = userRepo.findById(userId).orElse(null);
        return entity!=null?toModel(entity):null;
	}

	@Override
	public List<UserModel> getAllUsers() {
		List<UserEntity> entities= userRepo.findAll();
		List<UserModel> models=null;
		if(entities!=null && !entities.isEmpty()) {
			models = entities.stream().map(e -> toModel(e)).collect(Collectors.toList());
		}
		return models;
	}
}
