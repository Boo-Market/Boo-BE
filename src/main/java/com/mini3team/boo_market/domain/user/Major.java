package com.mini3team.boo_market.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "major")
public class Major {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;
}
