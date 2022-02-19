package com.hanghae.instagramclonecoding.Controller;

import com.hanghae.instagramclonecoding.Security.UserDetailsImpl;
import com.hanghae.instagramclonecoding.Service.UserService;
import com.hanghae.instagramclonecoding.domain.Dto.LoginRequestDto;
import com.hanghae.instagramclonecoding.domain.Dto.LoginResponseDto;
import com.hanghae.instagramclonecoding.domain.Dto.SignupRequestDto;
import com.hanghae.instagramclonecoding.domain.Response;
import com.hanghae.instagramclonecoding.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public Response registerUser(@RequestBody SignupRequestDto requestDto)
    {
        return userService.registerUser(requestDto);
    }

    // 로그인
    @PostMapping("/user/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto)
    {
        return userService.login(requestDto);
    }


}
