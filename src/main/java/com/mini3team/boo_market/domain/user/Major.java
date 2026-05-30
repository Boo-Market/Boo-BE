package com.mini3team.boo_market.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "major")
public class Major {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    public Major(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
