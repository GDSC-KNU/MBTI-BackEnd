package com.gdsc.mbti.dto;

import com.gdsc.mbti.entity.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReplyResponseDto {
    private Long id;
    private String mbti;
    private String nickname;
    private String content;
    private Long postId;


    public ReplyResponseDto(Reply reply) {
        this.id = reply.getId();
        this.mbti = reply.getMbti();
        this.nickname = reply.getNickname();
        this.content = reply.getContent();
        this.postId = reply.getPost().getId();
    }
}
