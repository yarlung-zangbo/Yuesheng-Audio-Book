package com.yuesheng.cf.daoimpl;

import com.yuesheng.cf.dao.CommentDao;
import com.yuesheng.cf.entity.Comment;
import com.yuesheng.cf.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }
}
