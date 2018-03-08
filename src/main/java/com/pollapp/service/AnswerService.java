package com.pollapp.service;

import com.pollapp.entity.Answer;
import com.pollapp.entity.UserData;
import com.pollapp.response.AnswerResponse;

import java.util.List;

public interface AnswerService {

    AnswerResponse save(Answer answer);

    AnswerResponse addAnswerToPoll(Answer answer, long pollId);

    AnswerResponse getAnswer(long answerId);

    List<AnswerResponse> getAnswersByPoll(long pollId);

    UserData vote(long answerId);
}
