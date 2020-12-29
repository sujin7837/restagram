package com.example.restagram.domain.security;

import java.util.Collection;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//principal(접근 주체): Spring Security Context에 보관되며, 세션처럼 사용됩니다. 
public class InstaUserDetails implements UserDetails{
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	//사용자 계정의 권한 목록
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	//사용자 계정의 비밀번호를 리턴
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	//사용자 계정의 이름을 리턴
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	//사용자 계정이 만료되지 않았는지 리턴
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//사용자 계정이 잠겨있지 않은지 확인
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	//사용자 계정 비밀번호가 만료되지 않았는지 확인
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	//사용자 계정이 정지되지 않았는지(사용 가능한지) 확인
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
	
	
	
	
	
	
	
	
}
