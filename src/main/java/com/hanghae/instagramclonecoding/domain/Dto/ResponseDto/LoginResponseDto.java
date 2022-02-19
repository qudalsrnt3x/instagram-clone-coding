package com.hanghae.instagramclonecoding.domain.Dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponseDto
{
    private Boolean result;
    private String token;
}
