package com.mini3team.boo_market.domain.category;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
public class Category {

    @Id
    private Long id;

    private String name;
}
