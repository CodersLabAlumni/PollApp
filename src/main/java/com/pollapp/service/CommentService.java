package com.pollapp.service;

import com.pollapp.entity.Comment;
import com.pollapp.response.CommentValidationResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

public interface CommentService {

    CommentValidationResponse add(long pollId, String username, Comment comment, BindingResult bindingResult);

    Page<Comment> getCommentsByPollId(long pollId, Pageable pageable);
}
