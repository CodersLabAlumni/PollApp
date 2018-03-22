package com.pollapp.controller;

import com.pollapp.entity.Comment;
import com.pollapp.entity.Poll;
import com.pollapp.entity.UserAccount;
import com.pollapp.entity.UserData;
import com.pollapp.response.RegisterResponse;
import com.pollapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("")
    public List<UserAccount> getAccounts() {
        // TODO
        return null;
    }

    @PostMapping("")
    public RegisterResponse createAccount(@Valid @RequestBody UserAccount userAccount, BindingResult bindingResult) {
        return accountService.register(userAccount, bindingResult);
    }

    @GetMapping("/{accountId}")
    public UserAccount getAccount(@PathVariable long accountId) {
        // TODO
        return null;
    }

    @PutMapping("/{accountId}")
    public UserAccount updateAccount(@PathVariable long accountId) {
        // TODO
        return null;
    }

    @DeleteMapping("/{accountId}")
    public UserAccount deleteAccount(@PathVariable long accountId) {
        // TODO
        return null;
    }

    @GetMapping("/{accountId}/data/{dataId}")
    public UserData getDataByAccount(@PathVariable long accountId, @PathVariable long dataId) {
        // TODO
        return null;
    }

    @PostMapping("/{accountId}/data")
    public UserData createDataByAccount(@PathVariable long accountId) {
        // TODO
        return null;
    }

    @PutMapping("/{accountId/data/{dataId}")
    public UserData updateDataByAccount(@PathVariable long accountId, @PathVariable long dataId) {
        // TODO
        return null;
    }

    @GetMapping("/{accountId}/polls")
    public List<Poll> getPollsByAccount(@PathVariable long accountId) {
        // TODO
        return null;
    }

    @GetMapping("/{accountId}/polls/{pollId}")
    public Poll getPollByAccount(@PathVariable long accountId, @PathVariable long pollId) {
        // TODO
        return null;
    }

    @PostMapping("/{accountId}/polls")
    public Poll createPollByAccount(@PathVariable long accountId) {
        // TODO
        return null;
    }

    @GetMapping("/{accountId}/comments")
    public List<Comment> getCommentsByAccount(@PathVariable long accountId) {
        // TODO
        return null;
    }

    @PostMapping("/{accountId}/comments")
    public Comment createCommentByAccount(@PathVariable long accountId) {
        // TODO
        return null;
    }
}
