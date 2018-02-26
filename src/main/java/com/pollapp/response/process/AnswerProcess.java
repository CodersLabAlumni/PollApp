package com.pollapp.response.process;

import com.pollapp.entity.Answer;
import com.pollapp.repository.UserDataRepository;
import com.pollapp.response.data.AnswerNumberData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class AnswerProcess {

    @Autowired
    private UserDataRepository userDataRepository;

    public AnswerNumberData process(Answer answer) {
        AnswerNumberData answerNumberData = new AnswerNumberData();
        answerNumberData.setPercent(calculatePercent(answer));
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
}
