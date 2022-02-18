package com.hanghae.instagramclonecoding.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(
                name = "subscribe_uk",
                columnNames = {"from_user_id", "to_user_id"}
        )
})
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "from_user_id")
    @ManyToOne
    private User fromUser; // 구독 (팔로우) 하는 유저

    @JoinColumn(name = "to_user_id")
    @ManyToOne
    private User toUser; // 구독 받는 유저
}
