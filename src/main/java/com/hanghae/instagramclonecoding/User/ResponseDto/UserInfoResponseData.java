package com.hanghae.instagramclonecoding.User.ResponseDto;

import com.hanghae.instagramclonecoding.User.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserInfoResponseData
{
    private String nickname;
    private String profileImageUrl;
    private String bio;
    private String website;

    public UserInfoResponseData(User user)
    {
        this.nickname = user.getNickname();
        this.profileImageUrl = user.getProfileImageUrl();
        this.bio = user.getBio();
        this.website = user.getWebsite();
    }
}
