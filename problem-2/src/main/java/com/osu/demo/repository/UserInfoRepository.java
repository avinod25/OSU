package com.osu.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.osu.demo.model.UserInfo;

@Transactional
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{

	@Query("select u from UserInfo u where u.userName = :userName")
	UserInfo findByUserName(@Param("userName") String userName);

}
