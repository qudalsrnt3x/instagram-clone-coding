package com.hanghae.instagramclonecoding.domain.Dto.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileChangeRequestDto
{
//    private String email;

    private String nickname;

    private String profileImageUrl;

    private String bio;

    private String website;

    private String phone;
}
