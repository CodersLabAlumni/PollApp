package com.pollapp.service;

import com.pollapp.entity.Comment;
import com.pollapp.repository.CommentRepository;
import com.pollapp.repository.PollRepository;
import com.pollapp.repository.UserAccountRepository;
import com.pollapp.response.CommentValidationResponse;
import com.pollapp.validation.CommentValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentValidation commentValidation;

    @Override
    public CommentValidationResponse add(long pollId, String username, Comment comment, BindingResult bindingResult) {
        if (commentValidation.validComment(comment, bindingResult)) {
            comment.setUserAccount(userAccountRepository.findByUsername(username));
            comment.setPoll(pollRepository.findOne(pollId));
            commentRepository.save(comment);
        }
        return commentValidation.getCommentValidationResponse();
    }

    @Override
    public Page<Comment> getCommentsByPollId(long pollId, Pageable pageable) {
        return commentRepository.findByPollId(pollId, pageable);
    }
}
