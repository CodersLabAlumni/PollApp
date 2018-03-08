package com.pollapp.controller;

import com.pollapp.entity.Answer;
import com.pollapp.entity.Poll;
import com.pollapp.entity.UserData;
import com.pollapp.response.AnswerResponse;
import com.pollapp.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @GetMapping("")
    public List<Answer> getAnswers() {
        // TODO
        return null;
    }

    @PostMapping("")
    public AnswerResponse createAnswer(@RequestBody Answer answer) {
        return answerService.save(answer);
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
        return answerService.vote(answerId);
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
