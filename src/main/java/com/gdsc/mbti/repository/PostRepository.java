package com.gdsc.mbti.repository;

import com.gdsc.mbti.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByMbti(String mbti);
}
