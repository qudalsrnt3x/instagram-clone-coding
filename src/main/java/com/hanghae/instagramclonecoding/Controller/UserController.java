package com.hanghae.instagramclonecoding.Controller;

import com.hanghae.instagramclonecoding.Security.UserDetailsImpl;
import com.hanghae.instagramclonecoding.Service.UserService;
import com.hanghae.instagramclonecoding.domain.Dto.RequestDto.LoginRequestDto;
import com.hanghae.instagramclonecoding.domain.Dto.RequestDto.ProfileChangeRequestDto;
import com.hanghae.instagramclonecoding.domain.Dto.ResponseDto.LoginResponseDto;
import com.hanghae.instagramclonecoding.domain.Dto.RequestDto.SignupRequestDto;
import com.hanghae.instagramclonecoding.domain.Dto.ResponseDto.UserInfoResponseDto;
import com.hanghae.instagramclonecoding.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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


    // 마이페이지 - 유저 정보 조회
    @GetMapping("/user/info/{userId}")
    public UserInfoResponseDto Userinfo(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long userId)
    {
        return userService.Userinfo(userDetails, userId);
    }

    // 유저 정보 수정
    @PatchMapping("/user/info/{userId}")
    public Response profileChange(@RequestBody ProfileChangeRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long userId )
    {
        return userService.updateUserinfo(requestDto,userDetails, userId);
    }


}
