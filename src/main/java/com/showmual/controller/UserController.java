package com.showmual.controller;

import java.security.Principal;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.showmual.dto.UserDto;
import com.showmual.service.UserService;
import com.showmual.validate.CheckEmailValidator;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class UserController {
	
//	@Autowired
//	FilesService filesService;
	
	private final UserService userService;
	private final CheckEmailValidator checkEmailValidator;
	
	/* 커스텀 유효성 검증을 위해 추가 */
	@InitBinder
	public void validatorBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(checkEmailValidator);
	}
	
	// 메인 페이지
	@GetMapping("/")
	public String index() {
		return "index";
	}

	// 회원가입 페이지
	@GetMapping("/user/signup")
	public String dispSignup() {
		return "signup";
	}

//    // 회원가입 처리
//    @PostMapping("/user/signup")
//    public String execSignup(MemberDto memberDto) {
//        memberService.joinUser(memberDto);
//
//        return "redirect:/user/login";
//    }

	// 회원가입 처리
	@PostMapping("/user/signup")
	public String execSignup(@Valid UserDto userDto, Errors errors, Model model) {
		if (errors.hasErrors()) {

			// 유효성 통과 못한 필드와 메시지를 핸들링
			Map<String, String> validatorResult = userService.validateHandling(errors);
			for (String key : validatorResult.keySet()) {
				model.addAttribute(key, validatorResult.get(key));
			}

			return "signup";
		}

		userService.joinUser(userDto);
		return "redirect:/user/login";
	}

	// 로그인 페이지
	@GetMapping("/user/login")
	public String dispLogin() {
		return "login";
	}

	// 로그인 결과 페이지
	@GetMapping("/user/login/result")
	public String dispLoginResult() {
		return "loginSuccess";
	}

	// 로그아웃 결과 페이지
	@GetMapping("/user/logout/result")
	public String dispLogout() {
		return "logout";
	}

	// 접근 거부 페이지
	@GetMapping("/user/denied")
	public String dispDenied() {
		return "denied";
	}

	// 내 정보 페이지
	@GetMapping("/user/myinfo")
	public String dispMyInfo() {
		return "myinfo";
	}

	// 어드민 페이지
	@GetMapping("/admin")
	public String dispAdmin() {
		
		return "admin";
	}
	
	// 사용자 정보 보기
	@RequestMapping("/user/memberinfo")
	@ResponseBody
	public String memberInfo(Principal principal) {
		String email = principal.getName();
		
		return email;
	}
	
	// 사용자 정보 보기
//	@RequestMapping("/user/memberid")
//	@ResponseBody
//	public String memberId(Principal principal) {
//		String email = principal.getName();
//		String id = memberService.findIdByEmail(email);
//		
//		return id;
//	}
	
}
