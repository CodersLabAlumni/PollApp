package com.pollapp.service;

import com.pollapp.entity.Poll;
import com.pollapp.response.PollResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PollService {

    PollResponse save(Poll poll);

    PollResponse getPoll(long pollId);

    PollResponse addCategoryToPoll(long pollId, int categoryId);

    Page<PollResponse> getPolls(Pageable pageable);

    Page<PollResponse> getClosedPolls(Pageable pageable);

    Page<PollResponse> getOnGoingPolls(Pageable pageable);

    Page<PollResponse> getPollsByCategory(int categoryId, Pageable pageable);

    Page<PollResponse> getClosedPollsByCategory(int categoryId, Pageable pageable);

    Page<PollResponse> getOnGoingPollsByCategory(int categoryId, Pageable pageable);

    Page<PollResponse> getAvailablePollsByCategory(int categoryId, Pageable pageable);
}

