package com.pollapp.service;

import com.pollapp.entity.Poll;
import com.pollapp.entity.UserData;
import com.pollapp.repository.PollRepository;
import com.pollapp.repository.UserDataRepository;
import com.pollapp.response.PollResponse;
import com.pollapp.response.process.PollProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public PollResponse createPoll(Poll poll) {
        return createPollResponse(pollRepository.save(poll));
    }

    public List<PollResponse> getOpenedPollsAvailableToUserByIp(String ip) {
        if (userDataRepository.existsByIp(ip)) {
            return allAvailableOpenedPolls(ip);
        } else {
            return allOpenedPolls();
        }
    }

    public List<PollResponse> getOpenedPollsAvailableToUserByIpAndCategoryId(String ip, int categoryId) {
        if (userDataRepository.existsByIp(ip)) {
            return allAvailableOpenedPollsByCategory(ip, categoryId);
        } else {
            return allOpenedPollsByCategory(categoryId);
        }
    }

    public List<PollResponse> getClosedPolls() {
        return createPollResponseList(pollRepository.findAll());
    }

    public Page<PollResponse> getClosedPolls(Pageable pageable) {
        return createPollResponsePageableList(pollRepository.findAll(pageable), pageable);
    }

    public List<PollResponse> getClosedPollsByCategoryId(int categoryId) {
        return createPollResponseList(pollRepository.findAllByCategoriesId(categoryId));
    }

    public Page<PollResponse> getClosedPollsByCategoryId(int categoryId, Pageable pageable) {
        return createPollResponsePageableList(pollRepository.findAllByCategoriesId(categoryId, pageable), pageable);
    }

    public PollResponse createPollResponse(Poll poll) {
        return new PollResponse(poll, pollProcess.process(poll));
    }

    public List<PollResponse> createPollResponseList(List<Poll> polls) {
        List<PollResponse> response = new ArrayList<>();
        polls.forEach(poll ->
                response.add(createPollResponse(poll)));
        return response;
    }

    public Page<PollResponse> createPollResponsePageableList(Page<Poll> polls, Pageable pageable) {
        List<PollResponse> list = new ArrayList<>();
        polls.forEach(poll ->
                list.add(createPollResponse(poll)));
        return new PageImpl<>(list, pageable, polls.getTotalElements());
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

    private List<PollResponse> allOpenedPollsByCategory(int categoryId) {
        return createPollResponseList(pollRepository.findAllByCategoriesId(categoryId));
    }

    private List<PollResponse> allAvailableOpenedPollsByCategory(String ip, int categoryId) {
        List<Long> pollsIdList = createPollsIdList(userDataRepository.findByIp(ip));
        return createPollResponseList(pollRepository.findByIdNotInAndCategoriesId(pollsIdList, categoryId));
    }
}