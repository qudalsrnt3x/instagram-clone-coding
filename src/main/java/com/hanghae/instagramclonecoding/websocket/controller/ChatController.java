package com.hanghae.instagramclonecoding.websocket.controller;

import com.hanghae.instagramclonecoding.Security.JwtTokenProvider;
import com.hanghae.instagramclonecoding.User.User;
import com.hanghae.instagramclonecoding.User.UserService;
import com.hanghae.instagramclonecoding.websocket.controller.model.ChatMessage;
import com.hanghae.instagramclonecoding.websocket.controller.model.ChatMessageRepository;
import com.hanghae.instagramclonecoding.websocket.controller.model.MessageRequestDto;
import com.hanghae.instagramclonecoding.websocket.controller.model.MessageResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @MessageMapping("/message") // /app/message // 메세지를 받아서
    @SendTo("/chatroom/api/chat/room") // 여기로 보내준다.
    public MessageResponseDto receivePublicMessage(@RequestBody MessageRequestDto message,
                                                   @Header("token") String token) {

        System.out.println("message = " + message);

        // 로그인 회원 정보를 들어온 메시지에 값 셋팅
        User user = jwtTokenProvider.getAuthenticationUser(token);
        message.setUserId(user.getId());
        message.setSender(user.getNickname());

        // 메시지 생성 시간 삽입
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
        String dateResult = sdf.format(date);
        message.setCreatedAt(dateResult);

        // DTO로 채팅 메시지 객체 생성
        ChatMessage chatMessage = new ChatMessage(message, userService);

        // 웹소켓 통신으로 채팅방 토픽 구독자에게 메시지 보내기

        // MySql DB에 채팅 메시지 저장
        chatMessageRepository.save(chatMessage);

        return chatMessage.toMessageResponseDto();
    }

}
