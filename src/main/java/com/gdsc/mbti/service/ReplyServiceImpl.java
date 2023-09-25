package com.gdsc.mbti.service;

import com.gdsc.mbti.dto.ReplyRequestDto;
import com.gdsc.mbti.dto.ReplyUpdateRequestDto;
import com.gdsc.mbti.entity.Member;
import com.gdsc.mbti.entity.Reply;
import com.gdsc.mbti.entity.Post;
import com.gdsc.mbti.repository.MemberRepository;
import com.gdsc.mbti.repository.ReplyRepository;
import com.gdsc.mbti.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<Reply> getReplyList(Long postId) {
        return replyRepository.findAllByPostId(postId);
    }

    @Override
    @Transactional
    public Long save(Long id, ReplyRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 게시글이 없습니다.")
        );
        Member member = memberRepository.findByName(requestDto.getNickname()).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자가 없습니다.")
        );
        requestDto.setMember(member);
        requestDto.setPost(post);
        return replyRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    @Transactional
    public Long update(Long id, ReplyUpdateRequestDto requestDto) {
        Reply updateReply = replyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 없습니다.")
        );
        updateReply.updateContent(requestDto.getContent());
        replyRepository.save(updateReply);
        return id;
    }

    @Override
    @Transactional
    public Long delete(Long id) {
        replyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 없습니다.")
        );
        replyRepository.deleteById(id);
        return id;
    }
}
