package com.hanghae.instagramclonecoding.domain.Dto.ResponseDto;

import com.hanghae.instagramclonecoding.domain.User;
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
