package com.mini3team.boo_market.domain.chat;

import com.mini3team.boo_market.dto.request.ChatMessageRequest;
import com.mini3team.boo_market.dto.response.ChatMessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ChatWebSocketController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatService chatService;

    @MessageMapping("/chat/{roomId}")
    public void sendMessage(@DestinationVariable Long roomId,
                            ChatMessageRequest request,
                            Principal principal) {
        Long senderId = Long.valueOf(principal.getName());
        ChatMessageResponse response = chatService.saveMessage(roomId, senderId, request.getMessage());
        messagingTemplate.convertAndSend("/topic/chat/" + roomId, response);
    }
}
