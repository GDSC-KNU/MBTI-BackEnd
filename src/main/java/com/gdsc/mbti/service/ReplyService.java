package com.gdsc.mbti.service;

import com.gdsc.mbti.dto.ReplyRequestDto;
import com.gdsc.mbti.dto.ReplyUpdateRequestDto;

public interface ReplyService {
    public Long save(Long id, ReplyRequestDto requestDto);

    public Long update(Long id, ReplyUpdateRequestDto requestDto);

    public Long delete(Long id);
}
