package com.pollapp.response;

import com.pollapp.entity.Poll;
import com.pollapp.response.data.PollNumberData;

public class PollResponse {

    private PollNumberData pollNumberData;

    private Poll poll;

    public PollResponse() {
    }

    public PollResponse(Poll poll, PollNumberData pollNumberData) {
        setPoll(poll);
        setPollNumberData(pollNumberData);
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
