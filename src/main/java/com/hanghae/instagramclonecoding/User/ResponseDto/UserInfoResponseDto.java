package com.hanghae.instagramclonecoding.User.ResponseDto;

import com.hanghae.instagramclonecoding.User.ResponseDto.UserInfoResponseData;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfoResponseDto
{
    private Boolean result;
    private UserInfoResponseData data;
}
