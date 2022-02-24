package com.hanghae.instagramclonecoding.handler.ex;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    EMAIL_NOT_FOUND("U001", "존재하지 않은 EMAIL입니다."),
    DUPLICATE_EMAIL("U002", "중복된 사용자 EMAIL이 존재합니다."),
    PASSWORD_NOT_COLLECT("U003", "비밀번호가 같지 않습니다."),
    USER_NOT_FOUND("U004", "존재하지 않은 사용자입니다."),
    PROFILE_NOT_FOUND("U005", "프로필 정보가 없습니다.");


    private final String code;
    private final String message;

}
