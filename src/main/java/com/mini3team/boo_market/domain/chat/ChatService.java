package com.mini3team.boo_market.domain.chat;

import com.mini3team.boo_market.domain.post.Post;
import com.mini3team.boo_market.domain.post.PostRepository;
import com.mini3team.boo_market.domain.user.User;
import com.mini3team.boo_market.domain.user.UserRepository;
import com.mini3team.boo_market.dto.response.ChatMessageResponse;
import com.mini3team.boo_market.dto.response.ChatRoomResponse;
import com.mini3team.boo_market.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long createRoom(Long postId, Long buyerId) {
        return chatRoomRepository.findByPostIdAndBuyerId(postId, buyerId)
                .map(ChatRoom::getId)
                .orElseGet(() -> {
                    Post post = postRepository.findById(postId)
                            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
                    if (buyerId.equals(post.getAuthorId())) {
                        throw new ApiException(HttpStatus.BAD_REQUEST, "chat", "본인 게시글에는 채팅할 수 없습니다.");
                    }
                    User buyer = userRepository.findById(buyerId)
                            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
                    User seller = userRepository.findById(post.getAuthorId())
                            .orElseThrow(() -> new IllegalArgumentException("판매자를 찾을 수 없습니다."));
                    return chatRoomRepository.save(
                            ChatRoom.builder().post(post).buyer(buyer).seller(seller).build()
                    ).getId();
                });
    }

    @Transactional(readOnly = true)
    public List<ChatRoomResponse> getRooms(Long userId) {
        return chatRoomRepository.findAllByUserId(userId).stream()
                .map(room -> new ChatRoomResponse(room,
                        chatMessageRepository.countByChatRoomIdAndIsReadFalseAndSenderIdNot(room.getId(), userId)))
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ChatMessageResponse> getMessages(Long roomId) {
        return chatMessageRepository.findByChatRoomIdOrderByCreatedAtAsc(roomId).stream()
                .map(ChatMessageResponse::new)
                .toList();
    }

    public void markAsRead(Long roomId, Long userId) {
        chatMessageRepository.markAllAsRead(roomId, userId);
    }

    public ChatMessageResponse saveMessage(Long roomId, Long senderId, String message) {
        ChatRoom room = chatRoomRepository.findById(roomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방입니다."));
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        ChatMessage saved = chatMessageRepository.save(
                ChatMessage.builder().chatRoom(room).sender(sender).message(message).build()
        );
        return new ChatMessageResponse(saved);
    }
}
