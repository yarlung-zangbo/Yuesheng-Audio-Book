package com.yuesheng.cf.repository;

import com.yuesheng.cf.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
