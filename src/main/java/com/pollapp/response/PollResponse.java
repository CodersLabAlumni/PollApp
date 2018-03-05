package com.pollapp.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pollapp.entity.Poll;
import com.pollapp.response.data.PollNumberData;
import com.pollapp.response.process.PollProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PollResponse {

    @Autowired
    @JsonIgnore
    private PollProcess pollProcess;

    private PollNumberData pollNumberData;

    private Poll poll;

    private PollResponse() {
    }

    private PollResponse(Poll poll, PollNumberData pollNumberData) {
        setPoll(poll);
        setPollNumberData(pollNumberData);
    }

    public PollResponse create(Poll poll) {
        return new PollResponse(poll, pollProcess.process(poll));
    }

    public List<PollResponse> create(List<Poll> polls) {
        List<PollResponse> response = new ArrayList<>();
        polls.forEach(poll ->
                response.add(create(poll)));
        return response;
    }

    public Page<PollResponse> create(Page<Poll> polls, Pageable pageable) {
        List<PollResponse> response = new ArrayList<>();
        polls.forEach(poll ->
                response.add(create(poll)));
        return new PageImpl<>(response, pageable, polls.getTotalElements());
    }

    public PollNumberData getPollNumberData() {
        return pollNumberData;
    }

    public void setPollNumberData(PollNumberData pollNumberData) {
        this.pollNumberData = pollNumberData;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }
}
