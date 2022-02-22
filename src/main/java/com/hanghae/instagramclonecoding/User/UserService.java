package com.hanghae.instagramclonecoding.User;

import com.hanghae.instagramclonecoding.Security.JwtTokenProvider;
import com.hanghae.instagramclonecoding.Security.UserDetailsImpl;
import com.hanghae.instagramclonecoding.User.RequestDto.LoginRequestDto;
import com.hanghae.instagramclonecoding.User.RequestDto.ProfileChangeRequestDto;
import com.hanghae.instagramclonecoding.User.RequestDto.SignupRequestDto;
import com.hanghae.instagramclonecoding.User.ResponseDto.LoginResponseDto;
import com.hanghae.instagramclonecoding.User.ResponseDto.UserInfoResponseData;
import com.hanghae.instagramclonecoding.User.ResponseDto.UserInfoResponseDto;
import com.hanghae.instagramclonecoding.domain.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;


    // 회원 가입
    public Response registerUser(SignupRequestDto requestDto)
    {
        // 해당 email을 사용중인 유저가 존재하는지 확인
        Optional<User> found = userRepository.findByEmail(requestDto.getEmail());

        // 있다면 예외 처리
        if (found.isPresent())
        {
            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
        }

        // 비밀번호 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        // SignupRequestDto를 바탕으로 유저 객체 생성
        User user = new User(requestDto, password);

        // DB에 유저 저장
        userRepository.save(user);

        // result : true 반환
        Response response = new Response();
        response.setResult(true);
        return response;
    }

    // 로그인
    public LoginResponseDto login(LoginRequestDto requestDto)
    {
        // id가 없다면 예외 처리
       User user = userRepository.findByEmail(requestDto.getEmail())
               .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID 입니다."));

        // 비밀번호가 다르다면 예외 처리
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword()))
        {
            throw new IllegalArgumentException("비밀번호를 다시 확인해 주세요.");
        }

       // response ( true, token값 ) 반환
        LoginResponseDto loginResponseDto = new LoginResponseDto();
        loginResponseDto.setResult(true);
        loginResponseDto.setToken(jwtTokenProvider.createToken(user.getEmail()));

        return loginResponseDto;
    }

    // 마이 페이지 - 내 정보
    public UserInfoResponseDto Userinfo(UserDetailsImpl userDetails)
    {

        User user = userDetails.getUser();

        // api 변경으로 인해 주석처리
//        // 전달받은 userId 값과 토큰에 저장된 유저 정보의 id 값이 다르다면 예외 처리
//        if (!userDetails.getUser().getId().equals(userId))
//        {
//            throw new IllegalArgumentException("잘못된 접근입니다. ( 토큰과 다른 아이디)");
//        }
//
//        User user = userRepository.findById(userId).orElseThrow(
//                ()-> new IllegalArgumentException("해당 유저 없음") );

        // response 객체 생성 및 응답
        UserInfoResponseData userInfoResponseData = new UserInfoResponseData(user);
        UserInfoResponseDto userInfoResponseDto = new UserInfoResponseDto();
        userInfoResponseDto.setData(userInfoResponseData);
        userInfoResponseDto.setResult(true);

        return userInfoResponseDto;
    }

    @Transactional
    public Response updateUserinfo(ProfileChangeRequestDto requestDto, UserDetailsImpl userDetails, Long userId)
    {
//        // 전달받은 userId 값과 토큰에 저장된 유저 정보의 id 값이 다르다면 예외 처리
//        if (!userDetails.getUser().getId().equals(userId))
//        {
//            throw new IllegalArgumentException("잘못된 접근입니다. ( 토큰과 다른 아이디)");
//        }

        User user = userRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("해당 유저 없음") );

        System.out.println(user.getId());
        System.out.println(user.getBio());
        System.out.println(user.getProfileImageUrl());


        // 유저 정보 변경 ( Patch 방식 )
        user.update(requestDto);

        System.out.println(user.getId());
        System.out.println(user.getBio());
        System.out.println(user.getProfileImageUrl());

        // result : true 반환
        Response response = new Response();
        response.setResult(true);
        return response;
    }


}
