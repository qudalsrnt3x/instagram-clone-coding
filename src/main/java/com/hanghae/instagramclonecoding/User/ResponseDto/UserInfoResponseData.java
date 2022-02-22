package com.hanghae.instagramclonecoding.User.ResponseDto;

import com.hanghae.instagramclonecoding.User.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfoResponseData
{

    private Long id;
    private String email;
    private String nickname;
    private String profileImageUrl;
    private String bio;
    private String website;

    public UserInfoResponseData(User user)
    {
        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.profileImageUrl = user.getProfileImageUrl();
        this.bio = user.getBio();
        this.website = user.getWebsite();
    }
}
