package com.pollapp.response.process;

import com.pollapp.entity.Poll;
import com.pollapp.repository.CommentRepository;
import com.pollapp.repository.UserDataRepository;
import com.pollapp.response.data.PollNumberData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PollProcess {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private CommentRepository commentRepository;

    public PollNumberData process(Poll poll) {
        PollNumberData pollNumberData = new PollNumberData();
        pollNumberData.setTotalAnswers(calculateTotalAnswers(poll));
        pollNumberData.setComments(calculateComments(poll));
        return pollNumberData;
    }

    private BigDecimal calculateTotalAnswers(Poll poll) {
        return new BigDecimal(userDataRepository.countByAnswersPoll(poll));
    }

    private long calculateComments(Poll poll) {
        return commentRepository.countByPoll(poll);
    }
}
