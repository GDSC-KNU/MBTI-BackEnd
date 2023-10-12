package com.gdsc.mbti.controller;

import com.gdsc.mbti.dto.PostDeleteRequestDto;
import com.gdsc.mbti.dto.PostRequestDto;
import com.gdsc.mbti.dto.PostResponseDto;
import com.gdsc.mbti.dto.PostUpdateRequestDto;
import com.gdsc.mbti.entity.Post;
import com.gdsc.mbti.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class PostController {

    private final PostService postService;
    @GetMapping
    public List<PostResponseDto> getPostList() {
        List<Post> postList =  postService.getPostList();
        return postList.stream().map(PostResponseDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPost(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.getPost(id));
    }

    @PostMapping("/write")
    public ResponseEntity<Long> writePost(@RequestBody @Valid PostRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postService.save(requestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updatePost(@PathVariable("id") Long id, @RequestBody PostUpdateRequestDto requestDto) {
        return ResponseEntity.ok(postService.update(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deletePost(@PathVariable("id") Long id, @RequestBody PostDeleteRequestDto requestDto) {
        return ResponseEntity.ok(postService.delete(id, requestDto));
    }
}
