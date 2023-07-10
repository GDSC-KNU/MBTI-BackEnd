package com.gdsc.mbti.service;

import com.gdsc.mbti.dto.PostRequestDto;
import com.gdsc.mbti.dto.PostResponseDto;
import com.gdsc.mbti.dto.PostUpdateRequestDto;
import com.gdsc.mbti.entity.Post;
import com.gdsc.mbti.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<Post> getPostList(String mbti) {
        if (mbti.equals("all"))
            return postRepository.findAll();
        else
            return postRepository.findAllByMbti(mbti);
    }

    @Override
    public PostResponseDto getPost(Long id) {
        Post entity = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        return new PostResponseDto(entity);
    }

    @Override
    @Transactional
    public Long save(PostRequestDto requestDto) {
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post updatePost = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        updatePost.updateContent(requestDto.getContent());
        postRepository.save(updatePost);
        return id;
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        postRepository.deleteById(id);
        return id;
    }
}
