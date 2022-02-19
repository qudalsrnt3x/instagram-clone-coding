package com.hanghae.instagramclonecoding.Service;

import com.hanghae.instagramclonecoding.Security.JwtTokenProvider;
import com.hanghae.instagramclonecoding.domain.Dto.LoginRequestDto;
import com.hanghae.instagramclonecoding.domain.Dto.LoginResponseDto;
import com.hanghae.instagramclonecoding.domain.Dto.SignupRequestDto;
import com.hanghae.instagramclonecoding.domain.Response;
import com.hanghae.instagramclonecoding.domain.User;
import com.hanghae.instagramclonecoding.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
