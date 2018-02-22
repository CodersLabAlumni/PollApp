package com.pollapp.bean.poll;

import com.pollapp.entity.Poll;

public class PollJsonResponse {

    private PollNumberData pollNumberData;

    private Poll poll;

    public PollJsonResponse() {
        pollNumberData = new PollNumberData();
        poll = new Poll();
    }

    public PollJsonResponse(Poll poll, PollNumberData pollNumberData) {
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
