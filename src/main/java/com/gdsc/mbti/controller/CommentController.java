package com.gdsc.mbti.controller;

import com.gdsc.mbti.dto.CommentRequestDto;
import com.gdsc.mbti.dto.CommentUpdateRequestDto;
import com.gdsc.mbti.dto.PostRequestDto;
import com.gdsc.mbti.dto.PostUpdateRequestDto;
import com.gdsc.mbti.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/{id}/reply")
    public Long writeReply(@PathVariable("id") Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.save(id, requestDto);
    }

    @PutMapping("/{id}/reply/{replyId}")
    public Long updateReply( @PathVariable("id") Long id, @PathVariable("replyId") Long replyId, @RequestBody CommentUpdateRequestDto requestDto) {
        return commentService.update(replyId, requestDto);
    }

    @DeleteMapping("/{id}/reply/{replyId}")
    public Long deleteReply(@PathVariable("id") Long id, @PathVariable("replyId") Long replyId) {
        return commentService.delete(replyId);
    }
}
