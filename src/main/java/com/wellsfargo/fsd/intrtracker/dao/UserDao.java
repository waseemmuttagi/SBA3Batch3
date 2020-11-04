package com.wellsfargo.fsd.intrtracker.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellsfargo.fsd.intrtracker.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Integer>{

}
