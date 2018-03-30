package com.pollapp.service;

import com.pollapp.bean.Ip;
import com.pollapp.entity.Answer;
import com.pollapp.entity.UserData;
import com.pollapp.repository.AnswerRepository;
import com.pollapp.repository.PollRepository;
import com.pollapp.repository.UserDataRepository;
import com.pollapp.response.AnswerFormValidationResponse;
import com.pollapp.response.AnswerResponse;
import com.pollapp.validation.AnswerValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerResponse answerResponse;

    @Autowired
    private AnswerValidation answerValidation;

    @Override
    public AnswerResponse save(Answer answer) {
        return answerResponse.create(answerRepository.save(answer));
    }

    @Override
    public void delete(Answer answer) {
        answerRepository.delete(answer);
    }

    @Override
    public void delete(long answerId) {
        answerRepository.delete(answerId);
    }

    @Override
    public AnswerFormValidationResponse addAnswerToPoll(Answer answer, long pollId, BindingResult bindingResult) {
        if (answerValidation.validAnswer(answer, bindingResult)) {
            answer.setPoll(pollRepository.findOne(pollId));
            answerRepository.save(answer);
        }
        answerValidation.getAnswerFormValidationResponse().setAnswerId(answer.getId());
        return answerValidation.getAnswerFormValidationResponse();
    }

    @Override
    public AnswerResponse getAnswer(long answerId) {
        return answerResponse.create(answerRepository.findOne(answerId));
    }

    @Override
    public List<AnswerResponse> getAnswersByPoll(long pollId) {
        return answerResponse.create(answerRepository.findByPollId(pollId));
    }

    @Override
    public UserData vote(long answerId) {
        UserData userData = new UserData();
        String ip = Ip.remote();
        if (userDataRepository.existsByIp(ip)) {
            userData = userDataRepository.findByIp(ip);
        } else {
            userData.setIp(ip);
        }
        userData.getAnswers().add(answerRepository.findOne(answerId));
        return userDataRepository.save(userData);
    }
}
