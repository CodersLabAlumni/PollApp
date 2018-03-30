package com.pollapp.service;

import com.pollapp.entity.Answer;
import com.pollapp.entity.UserData;
import com.pollapp.response.AnswerFormValidationResponse;
import com.pollapp.response.AnswerResponse;
import org.springframework.validation.BindingResult;

import java.util.List;

public interface AnswerService {

    AnswerResponse save(Answer answer);

    void delete(Answer answer);

    void delete(long answerId);

    AnswerFormValidationResponse addAnswerToPoll(Answer answer, long pollId, BindingResult bindingResult);

    AnswerResponse getAnswer(long answerId);

    List<AnswerResponse> getAnswersByPoll(long pollId);

    UserData vote(long answerId);
}
