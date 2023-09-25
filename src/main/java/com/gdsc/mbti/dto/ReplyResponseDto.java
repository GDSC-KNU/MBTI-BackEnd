package com.gdsc.mbti.dto;

import com.gdsc.mbti.entity.Reply;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReplyResponseDto {
    private Long id;
    private String mbti;
    private String name;
    private String content;
    private Long postId;
    private Long memberId;


    public ReplyResponseDto(Reply reply) {
        this.id = reply.getId();
        this.mbti = reply.getMbti();
        this.name = reply.getMember().getName();
        this.content = reply.getContent();
        this.postId = reply.getPost().getId();
        this.memberId = reply.getMember().getId();
    }
}
