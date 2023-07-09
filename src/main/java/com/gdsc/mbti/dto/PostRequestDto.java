package com.gdsc.mbti.dto;

import com.gdsc.mbti.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private String mbti;
    private String nickname;
    private String content;

    @Builder
    public PostRequestDto(String mbti, String nickname, String content) {
        this.mbti = mbti;
        this.nickname = nickname;
        this.content = content;
    }

    public Post toEntity() {
        return Post.builder()
                .mbti(mbti)
                .nickname(nickname)
                .content(content)
                .build();
    }
}
