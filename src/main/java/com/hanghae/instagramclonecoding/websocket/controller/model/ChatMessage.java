package com.hanghae.instagramclonecoding.websocket.controller.model;

import com.hanghae.instagramclonecoding.User.User;
import com.hanghae.instagramclonecoding.User.UserService;
import com.hanghae.instagramclonecoding.domain.Timestamped;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Status status; // 메세지타입

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Redis MessageListener 로 뒙소켓을 통해 바로 채팅방에 메시지를 전달해주기 위한 값을 따로 설정해주었다
    @Column
    private Long userId;

    @Column
    private String sender;

    @Column
    private String message;

    @Column
    private String createdAt;

    @Builder
    public ChatMessage(MessageRequestDto chatMessageRequestDto, UserService userService) {
        this.status = chatMessageRequestDto.getStatus();
        this.user = userService.findById(chatMessageRequestDto.getUserId());
        this.userId = chatMessageRequestDto.getUserId();
        this.sender = chatMessageRequestDto.getSender();
        this.message = chatMessageRequestDto.getMessage();
        this.createdAt = chatMessageRequestDto.getCreatedAt();
    }

    // 메시지 응답dto
    public MessageResponseDto toMessageResponseDto() {
        return MessageResponseDto.builder()
                .message(this.getMessage())
                .status(this.status.toString())
                .sender(this.getSender())
                .userId(this.user.getId())
                .build();
    }
}
