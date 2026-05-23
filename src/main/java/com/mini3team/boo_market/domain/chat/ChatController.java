package com.mini3team.boo_market.domain.chat;

import com.mini3team.boo_market.dto.response.ChatMessageResponse;
import com.mini3team.boo_market.dto.response.ChatRoomResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat-rooms")
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/{postId}")
    public ResponseEntity<?> createRoom(@PathVariable Long postId, jakarta.servlet.http.HttpServletRequest request) {
        Long buyerId = (Long) request.getAttribute("userId");
        Long roomId = chatService.createRoom(postId, buyerId);
        return ResponseEntity.ok(Map.of("success", true, "data", Map.of("roomId", roomId)));
    }

    @GetMapping
    public ResponseEntity<?> getRooms(jakarta.servlet.http.HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        List<ChatRoomResponse> rooms = chatService.getRooms(userId);
        return ResponseEntity.ok(Map.of("success", true, "data", rooms));
    }

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<?> getMessages(@PathVariable Long roomId,
                                          jakarta.servlet.http.HttpServletRequest request) {
        Long userId = (Long) request.getAttribute("userId");
        chatService.markAsRead(roomId, userId);
        List<ChatMessageResponse> messages = chatService.getMessages(roomId);
        return ResponseEntity.ok(Map.of("success", true, "data", messages));
    }
}
