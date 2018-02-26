package com.pollapp.service;

import com.pollapp.entity.Answer;
import com.pollapp.entity.UserData;
import com.pollapp.repository.AnswerRepository;
import com.pollapp.repository.UserDataRepository;
import com.pollapp.response.AnswerResponse;
import com.pollapp.response.process.AnswerProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private AnswerProcess answerProcess;

    public AnswerResponse createAnswer(Answer answer) {
        return createAnswerResponse(answerRepository.save(answer));
    }

    public UserData vote(String ip, long answerId) {
        UserData userData = new UserData();
        if (userDataRepository.existsByIp(ip)) {
            userData = userDataRepository.findByIp(ip);
        } else {
            userData.setIp(ip);
        }
        userData.getAnswers().add(answerRepository.findOne(answerId));
        return userDataRepository.save(userData);
    }

    public AnswerResponse createAnswerResponse(Answer answer) {
        return new AnswerResponse(answer, answerProcess.process(answer));
    }

    public List<AnswerResponse> createAnswerResponseList(List<Answer> answers) {
        List<AnswerResponse> response = new ArrayList<>();
        answers.forEach(answer ->
                response.add(createAnswerResponse(answer)));
        return response;
    }
}
