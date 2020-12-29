package com.example.restagram.domain.follow;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Integer>{
	//팔로우 취소
	int deleteByFollowIdAndFollowerId(Long followId, Long followerId);
	
	//follow 여부 체크
	int countByFollowIdAndFollowerId(Long followId, Long followerId);
	
	//팔로우 리스트
	List<Follow> findByFollowId(Long followId);
	
	//팔로워 리스트
	List<Follow> findByFollowerId(Long followerId);
	
	//팔로우 카운트
	int countByFollowId(Long followId);
	
	//팔로워 카운트
	int countByFollowerId(Long followerId);
	
}
