package com.example.restagram.web.dto;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.restagram.domain.Users.UserRepository;

import net.slipp.web.HttpSessionUtils;


@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
//	private List<User> users=new ArrayList<User>();
	
	//회원가입 페이지로 이동
	@GetMapping("/signUpForm")
	public String signUpForm() {
		System.out.print("hihi");
		return "/user/signUpForm";
	}
	
	//회원가입
	@PostMapping("")
	public String insta_user(User user) {
		System.out.println("user: "+user);
		userRepository.save(user);
		return "redirect:/users";
	}
	
	//사용자 목록
	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/userlist";
	}
	
	//회원 정보 수정 페이
	@GetMapping("/{id}/signUpForm")
	public String userUpdate(@PathVariable Long id, Model model) {
		model.addAttribute("user", userRepository.findById(id).get());
		return "/user/userUpdate";
	}
	
	//회원 정보 수정
	@PostMapping("/{id}")
	public String update(@PathVariable Long id, User newUser) {
		User user=userRepository.findById(id).get();
		user.update(newUser);
		return "redirect:/users";
		
	}
	//로그인 페이지로 이동
	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}
	
	//로그인
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user=userRepository.findByUserId(userId);
		
		if (user == null) {
			System.out.println("Login Success!"); // 정상적으로 동작하는지 확인하기 위해 콘솔에 문구를 찍어보기 위한 코드
			return "redirect:/users/login";
		}
		if (!user.matchPassword(password)) {
			// user가 가지고 있는 패스워드 정보를 계속 get으로 꺼내오는 것이 아니라, User에게 메시지를 보내서 비밀번호가 일치하는지 확인함
			System.out.println("Login Failure!");
			return "redirect:/users/login";
		}
		System.out.println("Login Success!");
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user); // 세션에 현재 로그인한 사용자의 정보를 저장

		return "redirect:/";
	}
	
	//로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		return "redirect:/";
	}
	
	//팔로우
	@GetMapping("/follow")
	public String follow() {
		return "";
	}
	
	//팔로워 
	@GetMapping("/follower")
	public String follower() {
		return "";
	}
}
