package com.gdsc.mbti.dto;

import com.gdsc.mbti.validation.ValidEnum;
import com.gdsc.mbti.entity.Mbti;
import com.gdsc.mbti.entity.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    @ValidEnum(enumClass = Mbti.class, message = "올바르지 않은 MBTI입니다.")
    private String mbti;
    private String name;
    private String content;
    private String password;
//    private Member member;

    @Builder
    public PostRequestDto(String mbti, String name, String content, String password) {
        this.mbti = mbti;
        this.name = name;
        this.content = content;
        this.password = password;
    }

//    public void setMember(Member member) {
//        this.member = member;
//    }

    public Post toEntity() {
        return Post.builder()
                .mbti(Mbti.valueOf(mbti))
                .name(name)
                .content(content)
//                .member(member)
                .password(password)
                .build();
    }
}
