package com.pollapp.service;

import com.pollapp.entity.Poll;
import com.pollapp.entity.UserData;
import com.pollapp.repository.PollRepository;
import com.pollapp.repository.UserDataRepository;
import com.pollapp.response.PollResponse;
import com.pollapp.response.process.PollProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PollService {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private PollProcess pollProcess;

    public List<PollResponse> getOpenedPollsAvailableToUserByIp(String ip) {
        if (userDataRepository.existsByIp(ip)) {
            return allAvailableOpenedPolls(ip);
        } else {
            return allOpenedPolls();
        }
    }

    public List<PollResponse> getClosedPolls() {
        return createPollResponseList(pollRepository.findAll());
    }

    public List<PollResponse> createPollResponseList(List<Poll> polls) {
        List<PollResponse> response = new ArrayList<>();
        polls.forEach(poll ->
                response.add(new PollResponse(poll, pollProcess.process(poll))));
        return response;
    }

    private List<Long> createPollsIdList(UserData userData) {
        List<Long> pollsId = new ArrayList<>();
        userData.getAnswers().forEach(answer ->
                pollsId.add(answer.getPoll().getId()));
        return pollsId;
    }

    private List<PollResponse> allOpenedPolls() {
        return createPollResponseList(pollRepository.findAll());
    }

    private List<PollResponse> allAvailableOpenedPolls(String ip) {
        List<Long> pollsIdList = createPollsIdList(userDataRepository.findByIp(ip));
        return createPollResponseList(pollRepository.findByIdNotIn(pollsIdList));
    }
}