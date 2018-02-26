package com.pollapp.controller;

import com.pollapp.bean.Ip;
import com.pollapp.entity.Answer;
import com.pollapp.entity.Poll;
import com.pollapp.entity.UserData;
import com.pollapp.repository.AnswerRepository;
import com.pollapp.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @GetMapping("")
    public List<Answer> getAnswer() {
        // TODO
        return null;
    }

    @PostMapping("")
    public Answer createAnswer(@RequestBody Answer answer) {
        return answerRepository.save(answer);
    }

    @GetMapping("/{answerId}")
    public Answer getAnswer(@PathVariable long answerId) {
        // TODO
        return null;
    }

    @PutMapping("/{answerId}")
    public Answer updateAnswer(@PathVariable long answerId) {
        // TODO
        return null;
    }

    @DeleteMapping("/{answerId}")
    public Answer deleteAnswer(@PathVariable long answerId) {
        // TODO
        return null;
    }

    @GetMapping("/{answerId}/data")
    public List<UserData> getDataByAnswer(@PathVariable long answerId) {
        // TODO
        return null;
    }

    @PostMapping("/{answerId}/data")
    public UserData addDataToAnswer(@PathVariable long answerId) {
        String ip = Ip.remote();
        UserData userData = new UserData();
        if (userDataRepository.existsByIp(ip)) {
            userData = userDataRepository.findByIp(ip);
        } else {
            userData.setIp(ip);
        }
        userData.getAnswers().add(answerRepository.findOne(answerId));
        return userDataRepository.save(userData);
    }

    @GetMapping("/{answerId}/data/{dataId}")
    public UserData getDataByAnswer(@PathVariable long answerId, @PathVariable long dataId) {
        // TODO
        return null;
    }

    @GetMapping("/{answerId}/poll/{pollId}")
    public Poll getPollByAnswer(@PathVariable long answerId, @PathVariable long pollId) {
        // TODO
        return null;
    }
}
