package com.mini3team.boo_market.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id", nullable = false)
    private Major major;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String nickname;

    private Boolean isActive;
    private Boolean isVerified;
    private Boolean isAgreed;
    private LocalDateTime createdAt;

    @Builder
    public User(Major major, String email, String password, String name, String nickname, Boolean isAgreed) {
        this.major = major;
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.isAgreed = isAgreed;
        this.isActive = true;
        this.isVerified = false;
        this.createdAt = LocalDateTime.now();
    }

    public void verifyEmail() {
        this.isVerified = true;
    }

    public void update(String nickname, Major major, String password) {
        if (nickname != null) this.nickname = nickname;
        if (major != null) this.major = major;
        if (password != null) this.password = password;
    }

    public void withdraw() {
        this.isActive = false;
    }
}
