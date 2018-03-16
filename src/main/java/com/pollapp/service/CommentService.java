package com.pollapp.service;

import com.pollapp.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment add(long pollId, String username, Comment comment);

    List<Comment> getCommentsByPollId(long pollId);
}
