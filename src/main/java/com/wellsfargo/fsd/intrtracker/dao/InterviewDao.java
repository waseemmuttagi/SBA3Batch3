package com.wellsfargo.fsd.intrtracker.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wellsfargo.fsd.intrtracker.entity.InterviewEntity;

@Repository
public interface InterviewDao extends JpaRepository<InterviewEntity, Integer>{
	@Query("SELECT i FROM InterviewEntity i WHERE i.interviewId =:iId")
	List<InterviewEntity> findAllById(Integer iId);
	
	@Query("SELECT i FROM InterviewEntity i WHERE i.interviewName =:interviewName or i.interviewrName=:interviewerName")
	List<InterviewEntity> findByNameAndInterviewer(String interviewName, String interviewerName);

}
