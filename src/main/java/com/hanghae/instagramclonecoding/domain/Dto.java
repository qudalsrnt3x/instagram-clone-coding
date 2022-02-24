package com.hanghae.instagramclonecoding.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Dto {
    private String date; // String으로 선언해 줄 것 - "1 일전", "12 시간 전" ..

    public String getDate() {
        return date;
    }

    public Dto setDate(Date date) {
        this.date = Time.calculateTime(date); // 기존의 getter, setter에서 변경된 부분
        return null;
    }
}
