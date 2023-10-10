package com.gdsc.mbti.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Mbti mbti;

    private String name;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String password;

    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Reply> replies = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member;

    @Builder
    public Post(Mbti mbti, String name,String content, String password, List<Reply> replies) {
        this.mbti = mbti;
        this.name = name;
        this.content = content;
        this.password = password;
        this.replies = replies;
    }

    public void updateContent(String content) {
        this.content = content;
    }
}
