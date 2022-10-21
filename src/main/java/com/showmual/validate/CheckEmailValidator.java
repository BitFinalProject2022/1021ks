package com.showmual.validate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.showmual.domain.repository.UserRepository;
import com.showmual.dto.UserDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CheckEmailValidator extends AbstractValidator<UserDto> {

	private final UserRepository userRepository;

	@Override
	protected void doValidate(UserDto userDto, Errors errors) {
		if (userRepository.existsByEmail(userDto.toEntity().getEmail())) {
			errors.rejectValue("email", "이메일 중복 오류", "이미 사용중인 이메일 입니다.");
		}
	}

}
