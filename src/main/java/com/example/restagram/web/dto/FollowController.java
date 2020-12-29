package com.example.restagram.web.dto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.restagram.domain.Users.UserRepository;
import com.example.restagram.domain.follow.Follow;
import com.example.restagram.domain.follow.FollowRepository;
import com.example.restagram.domain.security.InstaUserDetails;

@Controller
public class FollowController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FollowRepository followRepository;
	
	//팔로우 신청
	@PostMapping("/follow/{id}")
	public @ResponseBody String follow(
			//누가
			@AuthenticationPrincipal InstaUserDetails userDetails, 
			//누구를
			@PathVariable Long id) {
		org.springframework.boot.autoconfigure.security.SecurityProperties.User followId=userDetails.getUser();
		//메소도가 반환할 결과값이 없음을 명백하게 표현할 필요가 있고, 
		//null을 반환하면 에러 유발 가능성이 높은 상황에서 메소드의 반환 타입으로 Optional을 사용
		Optional<User> toUser=userRepository.findById(id);
		User followerId=toUser.get();
		
		Follow follow=new Follow();
		follow.setFollowId(followId);
		follow.setFollowerId(followerId);
		
		followRepository.save(follow);
		return "ok";
	}
	
	//팔로우 취소
	@DeleteMapping("/follow/{id}")
	public @ResponseBody String unFollow(
			@AuthenticationPrincipal InstaUserDetails userDetails,
			@PathVariable Long id) {
		User followId=userDetails.getUser();
		Optional<User> toUser=userRepository.findById(id);
		User followerId=toUser.get();
		
		followRepository.deleteByFollowIdAndFollowerId(followId.getId(), followerId.getId());
		
		List<Follow> follows=followRepository.findAll();
		return "ok";
	}
	
	//팔로우하는 사람의 팔로워 목록
	@GetMapping("/follow/follower/{id}")
	public String followFollower(
			@PathVariable Long id,
			@AuthenticationPrincipal InstaUserDetails userDetails,
			Model model) {
		List<Follow> followers=followRepository.findByFollowerId(id);
		//로그인 한 사용자의 팔로우 정보로, 버튼 구현을 위해서 사용 
		List<Follow> principalFollows=followRepository.findByFollowId(userDetails.getUser().getId());
		
		//follower 목록이 가진 id와 principalfollows 목록이 가진 id를 비교해서 같으면 true, 아니면 false
		for(Follow follow1:followers) {
			for(Follow follow2:principalFollows) {
				if(follow1.getFollowId().getId()==follow2.getFollowerId().getId()) {
					follow1.setFollowState(true);
				}
			}
		}
		
		model.addAttribute("followers", followers);
		return "/follow/follower";
	}
	
	
	//팔로우하는 사람이 팔로우하는 목록
	@GetMapping("/follow/follow/{id}")
	public String followFollow(
			@PathVariable Long id,
			@AuthenticationPrincipal InstaUserDetails userDetails,
			Model model) {
		List<Follow> follows=followRepository.findByFollowId(id);
		//로그인 한 사용자의 팔로우 정보로, 버튼 구현을 위해서 사용 
		List<Follow> principalFollows=followRepository.findByFollowId(userDetails.getUser().getId());
		
		//follow 목록이 가진 id와 principalfollows 목록이 가진 id를 비교해서 같으면 true, 아니면 false
		for(Follow follow1:follows) {
			for(Follow follow2:principalFollows) {
				if(follow1.getFollowerId().getId()==follow2.getFollowerId().getId()) {
					follow1.setFollowState(true);
				}
			}
		}
		
		model.addAttribute("follow", follows);
		return "/follow/follow";
		
	}
	
}
