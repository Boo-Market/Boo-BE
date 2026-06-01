package com.mini3team.boo_market.domain.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findByChatRoomIdOrderByCreatedAtAsc(Long roomId);

    long countByChatRoomIdAndIsReadFalseAndSenderIdNot(Long roomId, Long senderId);

    @Modifying
    @Query("UPDATE ChatMessage m SET m.isRead = true WHERE m.chatRoom.id = :roomId AND m.sender.id <> :userId AND m.isRead = false")
    void markAllAsRead(@Param("roomId") Long roomId, @Param("userId") Long userId);

    @Modifying
    @Query("DELETE FROM ChatMessage m WHERE m.chatRoom IN :rooms")
    void deleteAllByChatRoomIn(@Param("rooms") List<ChatRoom> rooms);
}
