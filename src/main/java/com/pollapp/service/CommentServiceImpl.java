package com.pollapp.service;

import com.pollapp.entity.Comment;
import com.pollapp.repository.CommentRepository;
import com.pollapp.repository.PollRepository;
import com.pollapp.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment add(long pollId, String username, Comment comment) {
        comment.setUserAccount(userAccountRepository.findByUsername(username));
        comment.setPoll(pollRepository.findOne(pollId));
        return commentRepository.save(comment);
    }

    @Override
    public Page<Comment> getCommentsByPollId(long pollId, Pageable pageable) {
        return commentRepository.findByPollId(pollId, pageable);
    }
}
