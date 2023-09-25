package com.gdsc.mbti.dto;

import com.gdsc.mbti.entity.Member;
import com.gdsc.mbti.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private String mbti;
    private String email;
    private String content;
    private Member member;

    @Builder
    public PostRequestDto(String mbti, String email, String content) {
        this.mbti = mbti;
        this.email = email;
        this.content = content;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Post toEntity() {
        return Post.builder()
                .mbti(mbti)
                .content(content)
                .member(member)
                .build();
    }
}
