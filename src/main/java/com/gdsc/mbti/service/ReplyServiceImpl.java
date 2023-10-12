package com.gdsc.mbti.service;

import com.gdsc.mbti.dto.ReplyDeleteRequestDto;
import com.gdsc.mbti.dto.ReplyRequestDto;
import com.gdsc.mbti.dto.ReplyUpdateRequestDto;
import com.gdsc.mbti.entity.Reply;
import com.gdsc.mbti.entity.Post;
import com.gdsc.mbti.repository.ReplyRepository;
import com.gdsc.mbti.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService {
    private final ReplyRepository replyRepository;
    private final PostRepository postRepository;
//    private final MemberRepository memberRepository;

    @Override
    public List<Reply> getReplyList(Long postId) {
        return replyRepository.findAllByPostId(postId);
    }

    @Override
    @Transactional
    public Long save(Long id, ReplyRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("해당 게시글이 없습니다.")
        );
//        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(
//                () -> new IllegalArgumentException("해당 사용자가 없습니다.")
//        );
//        requestDto.setMember(member);
        requestDto.setPost(post);
        return replyRepository.save(requestDto.toEntity()).getId();
    }

    @Override
    @Transactional
    public Long update(Long id, ReplyUpdateRequestDto requestDto) {
        Reply updateReply = replyRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("수정할 댓글이 없습니다.")
        );
        if (requestDto.getPassword() != null && !requestDto.getPassword().equals(updateReply.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        updateReply.updateContent(requestDto.getContent());
        replyRepository.save(updateReply);
        return id;
    }

    @Override
    @Transactional
    public Long delete(Long id, ReplyDeleteRequestDto requestDto) {
        Reply deleteReply = replyRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("삭제할 댓글이 없습니다.")
        );
        if (requestDto.getPassword() != null && !requestDto.getPassword().equals(deleteReply.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        replyRepository.deleteById(id);
        return id;
    }
}
