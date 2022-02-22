package com.hanghae.instagramclonecoding.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hanghae.instagramclonecoding.User.RequestDto.ProfileChangeRequestDto;
import com.hanghae.instagramclonecoding.User.RequestDto.SignupRequestDto;
import com.hanghae.instagramclonecoding.domain.Timestamped;
import com.hanghae.instagramclonecoding.posts.Post;
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

    private String bio; // 자기소개

    private String profileImageUrl; // 프로필 사진

    private String phone;   // 핸드폰 번호

    private String website; // 웹사이트 주소

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

    public User(ProfileChangeRequestDto profileChangeRequestDto, User user) {
        this.profileImageUrl = profileChangeRequestDto.getProfileImageUrl();

    }
}
