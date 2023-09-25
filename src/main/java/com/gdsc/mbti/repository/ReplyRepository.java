package com.gdsc.mbti.repository;

import com.gdsc.mbti.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByPostId(Long postId);
}
