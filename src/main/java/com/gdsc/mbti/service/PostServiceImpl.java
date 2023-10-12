package com.gdsc.mbti.service;

import com.gdsc.mbti.dto.PostDeleteRequestDto;
import com.gdsc.mbti.dto.PostRequestDto;
import com.gdsc.mbti.dto.PostResponseDto;
import com.gdsc.mbti.dto.PostUpdateRequestDto;
import com.gdsc.mbti.entity.Post;
import com.gdsc.mbti.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
//    private final MemberRepository memberRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Post> getPostList() {
        return postRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public PostResponseDto getPost(Long id) {
        Post entity = postRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("해당 게시글이 없습니다.")
        );
        return new PostResponseDto(entity);
    }

    @Override
    @Transactional
    public Long save(PostRequestDto requestDto) {
//        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(
//                () -> new IllegalArgumentException("해당 사용자가 없습니다.")
//        );
//        requestDto.setMember(member);
        return postRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    @Transactional
    public Long update(Long id, PostUpdateRequestDto requestDto) {
        Post updatePost = postRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("수정할 게시글이 없습니다.")
        );
        if (requestDto.getPassword() != null && !requestDto.getPassword().equals(updatePost.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        updatePost.updateContent(requestDto.getContent());
        postRepository.save(updatePost);
        return id;
    }

    @Override
    @Transactional
    public Long delete(Long id, PostDeleteRequestDto requestDto) {
        Post deletePost = postRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("삭제할 게시글이 없습니다.")
        );
        if (requestDto.getPassword() != null && !requestDto.getPassword().equals(deletePost.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        postRepository.deleteById(id);
        return id;
    }
}
