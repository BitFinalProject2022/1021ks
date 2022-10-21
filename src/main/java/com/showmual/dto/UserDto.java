package com.showmual.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.showmual.domain.entity.UserEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserDto {
	
	private Long id;
	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Email(message = "올바른 이메일 형식이 아닙니다.")
    private String email;
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
	
    public UserEntity toEntity(){
        return UserEntity.builder()
                .id(id)
                .email(email)
                .password(password)
                .build();
    }
    
    @Builder
    public UserDto(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
	
}
