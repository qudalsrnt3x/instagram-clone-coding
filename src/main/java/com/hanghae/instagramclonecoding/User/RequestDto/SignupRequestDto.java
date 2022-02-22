package com.hanghae.instagramclonecoding.User.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto
{
    private String email;

    private String nickname;

    private String password;
}
