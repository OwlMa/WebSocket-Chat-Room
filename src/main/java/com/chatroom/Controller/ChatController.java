package com.chatroom.Controller;

import com.chatroom.model.ChatMessage;
import com.chatroom.tools.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @Value("${redis.channel.msgToAll}")
    private String msgToAll;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        redisTemplate.convertAndSend(msgToAll, JsonUtil.parseObjToJson(chatMessage));

    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor simpMessageHeaderAccessor) {
        System.out.println(chatMessage.getSender() + "joins in the meeting");
        simpMessageHeaderAccessor.getSessionAttributes().put("username", chatMessage.getSender());
        return chatMessage;
    }
}
