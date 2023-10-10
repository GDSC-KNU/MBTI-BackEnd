package com.gdsc.mbti.dto;

import com.gdsc.mbti.validation.ValidEnum;
import com.gdsc.mbti.entity.Mbti;
import com.gdsc.mbti.entity.Reply;
import com.gdsc.mbti.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyRequestDto {
    @ValidEnum(enumClass = Mbti.class)
    private String mbti;
    private String name;
    private String content;
    private String password;
    private Post post;
//    private Member member;

    @Builder
    public ReplyRequestDto(String mbti, String name, String content, String password, Post post) {
        this.mbti = mbti;
        this.name = name;
        this.content = content;
        this.password = password;
        this.post = post;
    }


    public Reply toEntity() {
        return Reply.builder()
                .mbti(Mbti.valueOf(mbti))
                .name(name)
                .content(content)
                .post(post)
                .password(password)
//                .member(member)
                .build();
    }

    public void setPost(Post post) {
        this.post = post;
    }

//    public void setMember(Member member) {
//        this.member = member;
//    }
}
