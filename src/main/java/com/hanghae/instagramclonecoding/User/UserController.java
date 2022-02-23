package com.hanghae.instagramclonecoding.User;

import com.hanghae.instagramclonecoding.Security.UserDetailsImpl;
import com.hanghae.instagramclonecoding.User.RequestDto.LoginRequestDto;
import com.hanghae.instagramclonecoding.User.RequestDto.ProfileChangeRequestDto;
import com.hanghae.instagramclonecoding.User.RequestDto.SignupRequestDto;
import com.hanghae.instagramclonecoding.User.ResponseDto.LoginResponseDto;
import com.hanghae.instagramclonecoding.User.ResponseDto.UserInfoResponseData;
import com.hanghae.instagramclonecoding.User.ResponseDto.UserInfoResponseDto;
import com.hanghae.instagramclonecoding.domain.Response;
import com.hanghae.instagramclonecoding.image.S3Uploader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    private final S3Uploader s3Uploader;

    @Autowired
    public UserController(UserService userService, S3Uploader s3Uploader) {
        this.userService = userService;
        this.s3Uploader = s3Uploader;
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public Response registerUser(@RequestBody SignupRequestDto requestDto) {
        return userService.registerUser(requestDto);
    }

    // 로그인
    @PostMapping("/user/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto) {
        return userService.login(requestDto);
    }


    // 마이페이지 - 유저 정보 조회
    @GetMapping("/user/info")
    public UserInfoResponseDto Userinfo(@AuthenticationPrincipal UserDetailsImpl userDetails)
    {
        return userService.Userinfo(userDetails);
    }

    // 유저 정보 수정
    @PutMapping("/user/info/{userId}")
    public Response profileChange(@RequestPart(value = "profileImageUrl") MultipartFile multipartFile,
//                             @RequestPart(value = "nickname") @Valid String Nickname,
//                             @RequestPart(value = "bio") @Valid String Bio,
//                             @RequestPart(value = "website") @Valid String Website,
                @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {

            String profileImageUrl = s3Uploader.upload(multipartFile, "profileImage");
            User user = userDetails.getUser();

            ProfileChangeRequestDto profileChangeRequestDto = new ProfileChangeRequestDto();

//        profileChangeRequestDto.setNickname(Nickname);
//        profileChangeRequestDto.setBio(Bio);
//        profileChangeRequestDto.setWebsite(Website);
            profileChangeRequestDto.setProfileImageUrl(profileImageUrl);

            userService.updateUserinfo(profileChangeRequestDto, user);

            Response response = new Response();
            response.setResult(true);
            return response;
    }

    // 전체 유저 조회
    @GetMapping("/api/users")
    public List<UserInfoResponseData> getUsers() {
        return userService.getUsers();
    }
}
