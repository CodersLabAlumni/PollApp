package com.pollapp.bean.poll;

import com.pollapp.entity.Poll;
import com.pollapp.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PollProcess {

    @Autowired
    private UserDataRepository userDataRepository;

    public PollNumberData process(Poll poll) {
        PollNumberData pollNumberData = new PollNumberData();
        pollNumberData.setTotalAnswers(calculateTotalAnswers(poll));
        return pollNumberData;
    }

    private long calculateTotalAnswers(Poll poll) {
        return userDataRepository.countByAnswersPoll(poll);
    }
}
