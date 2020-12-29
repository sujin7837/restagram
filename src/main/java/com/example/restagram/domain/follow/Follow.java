package com.example.restagram.domain.follow;

import java.sql.Timestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.userdetails.User;

public class Follow {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;	//팔로우 시퀀스
	
	//fromUser가 toUser를 follow함
	@ManyToOne
	@JoinColumn(name="fromUserId")
	private User followId;
	
	//toUser를 fromUser가 follower함
	@ManyToOne
	@JoinColumn(name="toUserId")
	private User followerId;
	
	public void setId(int id) {
		this.id = id;
	}

	public void setFollowId(User followId) {
		this.followId = followId;
	}

	public void setFollowerId(User followerId) {
		this.followerId = followerId;
	}

	public void setFollowState(boolean followState) {
		this.followState = followState;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	public int getId() {
		return id;
	}

	public User getFollowId() {
		return followId;
	}

	public User getFollowerId() {
		return followerId;
	}



	//follow, follower 창에서 로그인 한 사용자의 팔로우 상태 확인
	@Transient	//DB에 안 들어가게 함
	private boolean followState;
	
	@CreationTimestamp	//자동으로 현재 시간이 세팅됨
	private Timestamp createDate;
	
	@CreationTimestamp	//자동으로 현재 시간이 세팅됨
	private Timestamp updateDate;
}
