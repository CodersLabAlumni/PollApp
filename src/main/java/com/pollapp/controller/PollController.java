package com.pollapp.controller;

import com.pollapp.bean.Ip;
import com.pollapp.entity.Answer;
import com.pollapp.entity.Category;
import com.pollapp.entity.Comment;
import com.pollapp.entity.Poll;
import com.pollapp.repository.AnswerRepository;
import com.pollapp.repository.CategoryRepository;
import com.pollapp.repository.PollRepository;
import com.pollapp.response.AnswerResponse;
import com.pollapp.response.PollResponse;
import com.pollapp.response.process.AnswerProcess;
import com.pollapp.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private AnswerProcess answerProcess;

    @GetMapping("/ongoing")
    public List<PollResponse> getOpenedPolls() {
        return pollService.getOpenedPollsAvailableToUserByIp(Ip.remote());
    }

    @GetMapping("/closed")
    public List<PollResponse> getClosedPolls() {
        return pollService.getClosedPolls();
    }

    @PostMapping("")
    public Poll createPoll(@RequestBody Poll poll) {
        return pollRepository.save(poll);
    }

    @GetMapping("/{pollId}")
    public Poll getPoll(@PathVariable long pollId) {
        return pollRepository.findOne(pollId);
    }

    @PutMapping("/{pollId}")
    public Poll updatePoll(@RequestBody Poll poll, @PathVariable long pollId) {
        // TODO
        return null;
    }

    @DeleteMapping("/{pollId}")
    public Poll deletePoll(@PathVariable long pollId) {
        // TODO
        return null;
    }

    @GetMapping("/{pollId}/categories")
    public List<Category> getCategoriesByPoll(@PathVariable long pollId) {
        // TODO
        return null;
    }

    @PostMapping("/{pollId}/categories/{categoryId}")
    public Poll addCategoryToPoll(@PathVariable long pollId, @PathVariable int categoryId) {
        Poll poll = pollRepository.findOne(pollId);
        poll.getCategories().add(categoryRepository.findOne(categoryId));
        return pollRepository.save(poll);
    }

    @GetMapping("/{pollId}/answers")
    public List<AnswerResponse> getAnswersByPoll(@PathVariable long pollId) {
        List<AnswerResponse> response = new ArrayList<>();
        answerRepository.findByPollId(pollId).forEach(answer ->
                response.add(new AnswerResponse(answer, answerProcess.process(answer))));
        return response;
    }

    @PostMapping("/{pollId}/answers")
    public Answer addAnswerToPoll(@RequestBody Answer answer, @PathVariable long pollId) {
        Poll p = pollRepository.findOne(pollId);
        answer.setPoll(p);
        return answerRepository.save(answer);
    }

    @GetMapping("/{pollId}/comments")
    public List<Comment> getCommentsByPoll(@PathVariable long pollId) {
        // TODO
        return null;
    }
}
