package com.pollapp.controller;

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
    public UserData addDataToAnswer(@RequestBody UserData userData, @PathVariable long answerId) {
        UserData data = userDataRepository.save(userData);
        data.getAnswers().add(answerRepository.findOne(answerId));
        return userDataRepository.save(data);
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
