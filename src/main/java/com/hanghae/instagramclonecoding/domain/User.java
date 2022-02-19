package com.hanghae.instagramclonecoding.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hanghae.instagramclonecoding.domain.Dto.RequestDto.ProfileChangeRequestDto;
import com.hanghae.instagramclonecoding.domain.Dto.RequestDto.SignupRequestDto;
import lombok.*;

import javax.persistence.*;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String nickname;

    @Column(nullable = false)
    private String bio; // 자기소개

    @Column(nullable = false)
    private String profileImageUrl; // 프로필 사진

    @Column(nullable = false)
    private String phone;   // 핸드폰 번호

    @Column(nullable = false)
    private String website; // 웹사이트 주소

    @Column(nullable = false)
    private String gender; // 성별

    @Enumerated(value = EnumType.STRING)
    private Role role; // ROLE_USER, ROLE_ADMIN

    // 게시글
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties({"user"})
    private List<Post> posts;


    public User(SignupRequestDto requestDto, String hashPassword)
    {
        this.email = requestDto.getEmail();
        this.password = hashPassword;
        this.nickname = requestDto.getNickname();
        this.bio = "";
        this.profileImageUrl = "";
        this.phone = "";
        this.website = "";
        this.gender = "";
        this.role = Role.USER;
    }

    public void update(ProfileChangeRequestDto requestDto)
    {
//        this.email = requestDto.getEmail();

        this.nickname = requestDto.getNickname();
        this.profileImageUrl = requestDto.getProfileImageUrl();
        this.bio = requestDto.getBio();
        this.website = requestDto.getWebsite();
        this.phone = requestDto.getPhone();
    }
}
