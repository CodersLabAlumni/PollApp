package com.pollapp.service;

import com.pollapp.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    Comment add(long pollId, String username, Comment comment);

    Page<Comment> getCommentsByPollId(long pollId, Pageable pageable);
}
