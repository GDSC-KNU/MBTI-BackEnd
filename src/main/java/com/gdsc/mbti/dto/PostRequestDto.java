package com.gdsc.mbti.dto;

import com.gdsc.mbti.entity.Member;
import com.gdsc.mbti.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private String mbti;
    private String nickname;
    private String content;
    private Member member;

    @Builder
    public PostRequestDto(String mbti, String nickname, String content) {
        this.mbti = mbti;
        this.nickname = nickname;
        this.content = content;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Post toEntity() {
        return Post.builder()
                .mbti(mbti)
                .nickname(nickname)
                .content(content)
                .member(member)
                .build();
    }
}
