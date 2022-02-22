package com.hanghae.instagramclonecoding.User;

import com.hanghae.instagramclonecoding.Security.UserDetailsImpl;
import com.hanghae.instagramclonecoding.User.RequestDto.LoginRequestDto;
import com.hanghae.instagramclonecoding.User.RequestDto.ProfileChangeRequestDto;
import com.hanghae.instagramclonecoding.User.RequestDto.SignupRequestDto;
import com.hanghae.instagramclonecoding.User.ResponseDto.LoginResponseDto;
import com.hanghae.instagramclonecoding.User.ResponseDto.UserInfoResponseData;
import com.hanghae.instagramclonecoding.User.ResponseDto.UserInfoResponseDto;
import com.hanghae.instagramclonecoding.domain.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/user/info")
    public UserInfoResponseDto Userinfo(@AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        return userService.Userinfo(userDetails);
    }

    // 유저 정보 수정
    @PatchMapping("/user/info/{userId}")
    public Response profileChange(@RequestBody ProfileChangeRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long userId )
    {
        return userService.updateUserinfo(requestDto,userDetails, userId);
    }

    // 전체 유저 조회
    @GetMapping("/users")
    public List<UserInfoResponseData> getUsers() {
        return userService.getUsers();
    }
}
