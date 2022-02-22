package com.hanghae.instagramclonecoding.domain.Dto.ResponseDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfoResponseDto
{
    private Boolean result;
    private UserInfoResponseData data;
}
