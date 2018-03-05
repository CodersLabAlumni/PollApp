package com.pollapp.service;

import com.pollapp.bean.Ip;
import com.pollapp.entity.Answer;
import com.pollapp.entity.UserData;
import com.pollapp.repository.AnswerRepository;
import com.pollapp.repository.PollRepository;
import com.pollapp.repository.UserDataRepository;
import com.pollapp.response.AnswerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public AnswerResponse save(Answer answer) {
        return answerResponse.create(answerRepository.save(answer));
    }

    @Override
    public AnswerResponse addAnswerToPoll(Answer answer, long pollId) {
        answer.setPoll(pollRepository.findOne(pollId));
        return answerResponse.create(answerRepository.save(answer));
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
