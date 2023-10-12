package com.gdsc.mbti.service;

import com.gdsc.mbti.dto.PostDeleteRequestDto;
import com.gdsc.mbti.dto.PostRequestDto;
import com.gdsc.mbti.dto.PostResponseDto;
import com.gdsc.mbti.dto.PostUpdateRequestDto;
import com.gdsc.mbti.entity.Post;

import java.util.List;

public interface PostService {
    public List<Post> getPostList();

    public PostResponseDto getPost(Long id);
    public Long save(PostRequestDto requestDto);

    public Long update(Long id, PostUpdateRequestDto requestDto);

    public Long delete(Long id, PostDeleteRequestDto requestDto);
}
