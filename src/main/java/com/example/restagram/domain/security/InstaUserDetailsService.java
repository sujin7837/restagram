package com.example.restagram.domain.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.restagram.domain.Users.UserRepository;

@Service
public class InstaUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;
	
	
	//loginForm에서 action="user/login"
	//로그인 버튼을 누르면 SecurityConfig에서 낚아채서 여기서 작동함
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=userRepository.findByUserName(username);
		InstaUserDetails userDetails=null;
		
		if(user!=null) {
			userDetails=new InstaUserDetails();
			userDetails.setUser(user);
		} else {
			throw new UsernameNotFoundException("사용자 이름이 존재하지 않습니다. : "+username);
		}
		
		return userDetails;
	}
}
