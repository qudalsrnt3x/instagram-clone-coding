package com.hanghae.instagramclonecoding.socketChat.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanghae.instagramclonecoding.socketChat.dto.ChatMessage;
import com.hanghae.instagramclonecoding.socketChat.dto.ChatRoom;
import com.hanghae.instagramclonecoding.socketChat.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Slf4j
@RequiredArgsConstructor
@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private final ChatService chatService;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("payload {}", payload);
        //TextMessage textMessage = new TextMessage("Welcome chatting");
        //session.sendMessage(textMessage);
        ChatMessage chatMessage = objectMapper.readValue(payload, ChatMessage.class);
        ChatRoom room = chatService.findRoomById(chatMessage.getRoomId());
        room.handleActions(session, chatMessage, chatService);


    }
}
