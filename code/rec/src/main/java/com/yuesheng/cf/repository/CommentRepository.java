package com.yuesheng.cf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yuesheng.cf.entity.Comment;


public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
