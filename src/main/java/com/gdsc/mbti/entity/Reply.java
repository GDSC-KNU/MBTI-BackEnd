package com.gdsc.mbti.entity;

import com.gdsc.mbti.validation.ValidEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor
public class Reply extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Mbti mbti;

    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String password;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;

    @Builder
    public Reply(Mbti mbti, String name, String content, String password, Post post) {
        this.mbti = mbti;
        this.name = name;
        this.content = content;
        this.password = password;
        this.post = post;
//        this.member = member;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
