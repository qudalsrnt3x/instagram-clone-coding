package com.hanghae.instagramclonecoding.websocket.controller.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MessageRequestDto {

    private String message;
    private Status status;
    private Long userId;
    private String sender;
    private String createdAt;
}
