package com.mini3team.boo_market.domain.report;

import com.mini3team.boo_market.domain.post.Post;
import com.mini3team.boo_market.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id", nullable = false)
    private User reporter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "target_user_id", nullable = false)
    private User targetUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String reason;

    private LocalDateTime createdAt;

    public Report(User reporter, User targetUser, Post post, String reason) {
        this.reporter = reporter;
        this.targetUser = targetUser;
        this.post = post;
        this.reason = reason;
        this.createdAt = LocalDateTime.now();
    }
}
