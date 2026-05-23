package com.mini3team.boo_market.dto.response;

import com.mini3team.boo_market.domain.chat.ChatMessage;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ChatMessageResponse {
    private final Long messageId;
    private final Long senderId;
    private final String senderNickname;
    private final String message;
    private final boolean isRead;
    private final LocalDateTime createdAt;

    public ChatMessageResponse(ChatMessage msg) {
        this.messageId = msg.getId();
        this.senderId = msg.getSender().getId();
        this.senderNickname = msg.getSender().getNickname();
        this.message = msg.getMessage();
        this.isRead = msg.isRead();
        this.createdAt = msg.getCreatedAt();
    }
}
