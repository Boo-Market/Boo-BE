package com.mini3team.boo_market.dto.response;

import com.mini3team.boo_market.domain.chat.ChatRoom;
import lombok.Getter;

@Getter
public class ChatRoomResponse {
    private final Long roomId;
    private final Long postId;
    private final String postTitle;
    private final Long buyerId;
    private final String buyerNickname;
    private final Long sellerId;
    private final String sellerNickname;
    private final long unreadCount;

    public ChatRoomResponse(ChatRoom room, long unreadCount) {
        this.roomId = room.getId();
        this.postId = room.getPost().getId();
        this.postTitle = room.getPost().getTitle();
        this.buyerId = room.getBuyer().getId();
        this.buyerNickname = room.getBuyer().getNickname();
        this.sellerId = room.getSeller().getId();
        this.sellerNickname = room.getSeller().getNickname();
        this.unreadCount = unreadCount;
    }
}
