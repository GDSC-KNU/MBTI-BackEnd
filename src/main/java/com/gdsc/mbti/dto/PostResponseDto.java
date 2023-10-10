package com.gdsc.mbti.dto;


import com.gdsc.mbti.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class PostResponseDto {
    private Long id;
    private String mbti;
    private String name;
    private String content;
    private List<ReplyResponseDto> replies;
//    private Long memberId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostResponseDto(Post entity) {
        this.id = entity.getId();
        this.mbti = entity.getMbti().toString();
        this.name = entity.getName();
        this.content = entity.getContent();
        this.replies = entity.getReplies().stream().map(ReplyResponseDto::new).collect(Collectors.toList());
//        this.memberId = entity.getMember().getId();
        this.createdAt = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}
