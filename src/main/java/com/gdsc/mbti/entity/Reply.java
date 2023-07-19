package com.gdsc.mbti.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "reply")
@NoArgsConstructor
public class Reply extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mbti;

    private String nickname;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Reply(String mbti, String nickname, String content, Post post) {
        this.mbti = mbti;
        this.nickname = nickname;
        this.content = content;
        this.post = post;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
