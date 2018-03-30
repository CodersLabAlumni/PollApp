package com.pollapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.pollapp.entity.Answer;
import com.pollapp.entity.Category;
import com.pollapp.entity.Comment;
import com.pollapp.entity.Poll;
import com.pollapp.repository.PollRepository;
import com.pollapp.response.AnswerFormValidationResponse;
import com.pollapp.response.AnswerResponse;
import com.pollapp.response.CommentValidationResponse;
import com.pollapp.response.PollFormValidationResponse;
import com.pollapp.response.PollResponse;
import com.pollapp.service.AnswerService;
import com.pollapp.service.CommentService;
import com.pollapp.service.PollService;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private AnswerService answerService;

    @Autowired
    private CommentService commentService;

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
    public PollFormValidationResponse createPoll(@Valid @RequestBody Poll poll, BindingResult bindingResult) {
        return pollService.save(poll, bindingResult);
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
    public void deletePoll(@PathVariable long pollId) {
        pollService.delete(pollId);
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
    public AnswerFormValidationResponse addAnswerToPoll(@Valid @RequestBody Answer answer, BindingResult bindingResult, @PathVariable long pollId) {
        return answerService.addAnswerToPoll(answer, pollId, bindingResult);
    }

    @GetMapping("/{pollId}/comments")
    public Page<Comment> getPollComments(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "5") int size, @RequestParam(value = "sort", defaultValue = "created") String[] properties, @RequestParam(value = "dir", defaultValue = "desc") String direction, @PathVariable long pollId) {
        return commentService.getCommentsByPollId(pollId, new PageRequest(page, size, Sort.Direction.fromString(direction), properties));
    }

    @PostMapping("/{pollId}/comments")
    public CommentValidationResponse addCommentToPoll(@PathVariable long pollId, @CookieValue("logged_user") String username, @Valid @RequestBody Comment comment, BindingResult bindingResult) {
        return commentService.add(pollId, username, comment, bindingResult);
    }
}
