package com.gdsc.mbti.service;

import com.gdsc.mbti.dto.ReplyRequestDto;
import com.gdsc.mbti.dto.ReplyUpdateRequestDto;
import com.gdsc.mbti.entity.Reply;

import java.util.List;

public interface ReplyService {
    public List<Reply> getReplyList(Long postId);
    public Long save(Long id, ReplyRequestDto requestDto);

    public Long update(Long id, ReplyUpdateRequestDto requestDto);

    public Long delete(Long id);
}
