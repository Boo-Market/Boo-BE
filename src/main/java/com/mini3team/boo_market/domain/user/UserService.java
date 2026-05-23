package com.mini3team.boo_market.domain.user;

import com.mini3team.boo_market.common.exception.ApiException;
import com.mini3team.boo_market.domain.goods.Goods;
import com.mini3team.boo_market.domain.goods.GoodsRepository;
import com.mini3team.boo_market.domain.post.Post;
import com.mini3team.boo_market.domain.post.PostRepository;
import com.mini3team.boo_market.domain.report.Report;
import com.mini3team.boo_market.domain.report.ReportRepository;
import com.mini3team.boo_market.domain.wishlist.WishListRepository;
import com.mini3team.boo_market.dto.request.GoodsUpdateRequest;
import com.mini3team.boo_market.dto.request.ReportRequest;
import com.mini3team.boo_market.dto.request.UserUpdateRequest;
import com.mini3team.boo_market.dto.response.MyGoodsResponse;
import com.mini3team.boo_market.dto.response.MyPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final MajorRepository majorRepository;
    private final GoodsRepository goodsRepository;
    private final PostRepository postRepository;
    private final WishListRepository wishListRepository;
    private final ReportRepository reportRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public MyPageResponse getMyPage(Long userId) {
        User user = getUser(userId);
        return new MyPageResponse(
                user.getName(),
                user.getNickname(),
                new MyPageResponse.MajorInfo(user.getMajor().getId(), user.getMajor().getName()),
                goodsRepository.countBySellerId(userId),
                goodsRepository.countBySellerIdAndStatus(userId, "SOLD"),
                wishListRepository.countByUserId(userId)
        );
    }

    public void updateMe(Long userId, UserUpdateRequest request) {
        User user = getUser(userId);
        Major major = null;

        if (request.nickname() != null
                && !request.nickname().equals(user.getNickname())
                && userRepository.existsByNickname(request.nickname())) {
            throw new ApiException(HttpStatus.CONFLICT, "nickname", "이미 사용 중인 닉네임입니다.");
        }

        if (request.majorId() != null) {
            major = majorRepository.findById(request.majorId())
                    .orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, "major_id", "존재하지 않는 전공입니다."));
        }

        String encodedPassword = request.password() == null ? null : passwordEncoder.encode(request.password());
        user.update(request.nickname(), major, encodedPassword);
    }

    @Transactional(readOnly = true)
    public List<MyGoodsResponse> getMyGoods(Long userId) {
        return goodsRepository.findBySellerIdOrderByCreatedAtDesc(userId).stream()
                .map(goods -> new MyGoodsResponse(goods.getId(), goods.getTitle(), goods.getPrice(), null))
                .toList();
    }

    public void updateGoods(Long userId, Long goodsId, GoodsUpdateRequest request) {
        Goods goods = goodsRepository.findByIdAndSellerId(goodsId, userId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "goods_id", "게시글을 찾을 수 없습니다."));

        goods.update(
                request.categoryId(),
                request.title(),
                request.type(),
                request.status(),
                request.price(),
                request.rentalPeriod(),
                request.place(),
                request.description(),
                request.condition()
        );
    }

    public void deleteGoods(Long userId, Long goodsId) {
        Goods goods = goodsRepository.findByIdAndSellerId(goodsId, userId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "goods_id", "게시글을 찾을 수 없습니다."));
        goodsRepository.delete(goods);
    }

    public void report(Long reporterId, ReportRequest request) {
        User reporter = getUser(reporterId);
        User targetUser = getUser(request.targetUserId());
        Post post = request.postId() == null ? null : postRepository.findById(request.postId())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "post_id", "게시글을 찾을 수 없습니다."));

        reportRepository.save(new Report(reporter, targetUser, post, request.reason()));
    }

    public void withdraw(Long userId) {
        getUser(userId).withdraw();
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, "user", "로그인이 필요합니다."));
    }
}
