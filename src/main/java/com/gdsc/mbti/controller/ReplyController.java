package com.gdsc.mbti.controller;

import com.gdsc.mbti.dto.ReplyRequestDto;
import com.gdsc.mbti.dto.ReplyResponseDto;
import com.gdsc.mbti.dto.ReplyUpdateRequestDto;
import com.gdsc.mbti.entity.Reply;
import com.gdsc.mbti.service.ReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class ReplyController {
    private final ReplyService replyService;

    @GetMapping("/{id}/reply")
    public List<ReplyResponseDto> getReplyList(@PathVariable("id") Long id) {
        List<Reply> replyList = replyService.getReplyList(id);
        return replyList.stream().map(ReplyResponseDto::new).collect(Collectors.toList());
    }

    @PostMapping("/{id}/reply")
    public Long writeReply(@PathVariable("id") Long id, @RequestBody ReplyRequestDto requestDto) {
        return replyService.save(id, requestDto);
    }

    @PutMapping("/{id}/reply/{replyId}")
    public Long updateReply(@PathVariable("id") Long id, @PathVariable("replyId") Long replyId, @RequestBody ReplyUpdateRequestDto requestDto) {
        return replyService.update(replyId, requestDto);
    }

    @DeleteMapping("/{id}/reply/{replyId}")
    public Long deleteReply(@PathVariable("id") Long id, @PathVariable("replyId") Long replyId) {
        return replyService.delete(replyId);
    }
}
