package com.pollapp.response.process;

import com.pollapp.entity.Answer;
import com.pollapp.entity.Poll;
import com.pollapp.repository.AnswerRepository;
import com.pollapp.repository.UserDataRepository;
import com.pollapp.response.data.AnswerNumberData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class AnswerProcess {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private AnswerRepository answerRepository;

    public AnswerNumberData process(Answer answer) {
        AnswerNumberData answerNumberData = new AnswerNumberData();
        answerNumberData.setPercent(calculatePercent(answer));
        answerNumberData.setTop(markIfTop(answer));
        return answerNumberData;
    }

    private BigDecimal calculatePercent(Answer answer) {
        BigDecimal all = new BigDecimal(userDataRepository.countByAnswersPoll(answer.getPoll()));
        BigDecimal one = new BigDecimal(userDataRepository.countByAnswers(answer));
        try {
            return one.divide(all, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
        } catch (ArithmeticException e) {
            return new BigDecimal(0);
        }
    }

    private boolean markIfTop(Answer answer) {
        Map<Answer, BigDecimal> map = initializeMap(answer.getPoll());
        return getMaxValues(map).contains(answer);
    }

    private Map<Answer, BigDecimal> initializeMap(Poll poll) {
        Map<Answer, BigDecimal> map = new HashMap<>();
        answerRepository.findByPoll(poll).forEach(e -> map.put(e, calculatePercent(e)));
        return map;
    }

    private List<Answer> getMaxValues(Map<Answer, BigDecimal> map) {
        if (map.isEmpty())
            return Collections.emptyList();
        BigDecimal max = map.values().stream().max(Comparator.naturalOrder()).get();
        return map.entrySet().stream()
                .filter(e -> e.getValue().equals(max))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
