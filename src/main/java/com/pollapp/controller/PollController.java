package com.pollapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pollapp.entity.Answer;
import com.pollapp.entity.Category;
import com.pollapp.entity.Comment;
import com.pollapp.entity.Poll;
import com.pollapp.repository.PollRepository;
import com.pollapp.response.AnswerResponse;
import com.pollapp.response.PollResponse;
import com.pollapp.service.AnswerService;
import com.pollapp.service.PollService;

@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private AnswerService answerService;

    @GetMapping("")
    public Page<PollResponse> getAllPolls(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "sort", defaultValue = "created") String[] properties, @RequestParam(value = "dir", defaultValue = "desc") String direction) {
        return pollService.getPolls(new PageRequest(page, size, Sort.Direction.fromString(direction), properties));
    }

    @GetMapping("/ongoing")
    public Page<PollResponse> getOpenedPolls(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "sort", defaultValue = "created") String[] properties, @RequestParam(value = "dir", defaultValue = "desc") String direction) {
        return pollService.getOnGoingPolls(new PageRequest(page, size, Sort.Direction.fromString(direction), properties));
    }
    
    @GetMapping("/game")
    public List<Poll> getGamePolls() {
    	return pollRepository.findAll();
    }

    @GetMapping("/closed")
    public Page<PollResponse> getClosedPolls(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "sort", defaultValue = "created") String[] properties, @RequestParam(value = "dir", defaultValue = "desc") String direction) {
        return pollService.getClosedPolls(new PageRequest(page, size, Sort.Direction.fromString(direction), properties));
    }

    @PostMapping("")
    public PollResponse createPoll(@RequestBody Poll poll) {
        return pollService.save(poll);
    }

    @GetMapping("/{pollId}")
    public PollResponse getPoll(@PathVariable long pollId) {
        return pollService.getPoll(pollId);
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
    public PollResponse addCategoryToPoll(@PathVariable long pollId, @PathVariable int categoryId) {
        return pollService.addCategoryToPoll(pollId, categoryId);
    }

    @GetMapping("/{pollId}/answers")
    public List<AnswerResponse> getAnswersByPoll(@PathVariable long pollId) {
        return answerService.getAnswersByPoll(pollId);
    }

    @PostMapping("/{pollId}/answers")
    public AnswerResponse addAnswerToPoll(@RequestBody Answer answer, @PathVariable long pollId) {
        return answerService.addAnswerToPoll(answer, pollId);
    }

    @GetMapping("/{pollId}/comments")
    public List<Comment> getCommentsByPoll(@PathVariable long pollId) {
        // TODO
        return null;
    }
}
