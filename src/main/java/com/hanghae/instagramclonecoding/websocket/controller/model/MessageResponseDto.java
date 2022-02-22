package com.hanghae.instagramclonecoding.websocket.controller.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MessageResponseDto {

    private Long userId;

    private String message;

    private String status;

    private String sender;
}
