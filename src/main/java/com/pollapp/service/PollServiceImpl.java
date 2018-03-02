package com.pollapp.service;

import com.pollapp.bean.Ip;
import com.pollapp.entity.Poll;
import com.pollapp.entity.UserData;
import com.pollapp.repository.CategoryRepository;
import com.pollapp.repository.PollRepository;
import com.pollapp.repository.UserDataRepository;
import com.pollapp.response.PollResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class PollServiceImpl implements PollService {

    @Autowired
    private PollResponse pollResponse;

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PollResponse save(Poll poll) {
        return pollResponse.create(pollRepository.save(poll));
    }

    @Override
    public PollResponse getPoll(long pollId) {
        return pollResponse.create(pollRepository.findOne(pollId));
    }

    @Override
    public PollResponse addCategoryToPoll(long pollId, int categoryId) {
        Poll poll = pollRepository.findOne(pollId);
        poll.getCategories().add(categoryRepository.findOne(categoryId));
        return pollResponse.create(pollRepository.save(poll));
    }

    @Override
    public Page<PollResponse> getPolls(Pageable pageable) {
        return getPollsByCategory(0, pageable);
    }

    @Override
    public Page<PollResponse> getClosedPolls(Pageable pageable) {
        return getClosedPollsByCategory(0, pageable);
    }

    @Override
    public Page<PollResponse> getOnGoingPolls(Pageable pageable) {
        return getOnGoingPollsByCategory(0, pageable);
    }

    @Override
    public Page<PollResponse> getPollsByCategory(int categoryId, Pageable pageable) {
        if (isAllCategories(categoryId)) {
            return pollResponse.create(pollRepository.findAll(pageable), pageable);
        } else {
            return pollResponse.create(pollRepository.findAllByCategoriesId(categoryId, pageable), pageable);
        }
    }

    @Override
    public Page<PollResponse> getClosedPollsByCategory(int categoryId, Pageable pageable) {
        if (isAllCategories(categoryId)) {
            return pollResponse.create(pollRepository.findAllByClosedBeforeOrClosedIsNull(Calendar.getInstance(), pageable), pageable);
        } else {
            return pollResponse.create(pollRepository.findAllByClosedBeforeOrClosedIsNullAndCategoriesId(Calendar.getInstance(), categoryId, pageable), pageable);
        }
    }

    @Override
    public Page<PollResponse> getOnGoingPollsByCategory(int categoryId, Pageable pageable) {
        if (isAllCategories(categoryId)) {
            return pollResponse.create(pollRepository.findAllByClosedAfter(Calendar.getInstance(), pageable), pageable);
        } else {
            return pollResponse.create(pollRepository.findAllByCategoriesIdAndClosedAfter(categoryId, Calendar.getInstance(), pageable), pageable);
        }
    }

    @Override
    public Page<PollResponse> getAvailablePollsByCategory(int categoryId, Pageable pageable) {
        List<Long> idList = new ArrayList<>();
        if (userDataRepository.existsByIp(Ip.remote())) {
            idList = createPollsIdList(userDataRepository.findByIp(Ip.remote()));
        }
        if (isAllCategories(categoryId)) {
            return pollResponse.create(pollRepository.findAllByClosedAfterAndIdNotIn(Calendar.getInstance(), idList, pageable), pageable);
        } else {
            return pollResponse.create(pollRepository.findAllByCategoriesIdAndClosedAfterAndIdNotIn(categoryId, Calendar.getInstance(), idList, pageable), pageable);
        }
    }

    private boolean isAllCategories(int categoryId) {
        return categoryId == 0;
    }

    private List<Long> createPollsIdList(UserData userData) {
        List<Long> pollsId = new ArrayList<>();
        userData.getAnswers().forEach(answer ->
                pollsId.add(answer.getPoll().getId()));
        return pollsId;
    }
}