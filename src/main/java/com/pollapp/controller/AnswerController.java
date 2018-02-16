package com.pollapp.controller;

import com.pollapp.entity.Answer;
import com.pollapp.entity.Poll;
import com.pollapp.entity.UserData;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @GetMapping("")
    public List<Answer> getAnswer() {
        // TODO
        return null;
    }

    @PostMapping("")
    public Answer createAnswer() {
        // TODO
        return null;
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
