package com.pollapp.bean.answer;

import com.pollapp.entity.Answer;
import com.pollapp.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class AnswerProcess {

    @Autowired
    private UserDataRepository userDataRepository;

    public AnswerNumberData process(Answer answer) {
        AnswerNumberData answerNumberData = new AnswerNumberData();
        answerNumberData.setPercent(calculatePercent(answer));
        return answerNumberData;
    }

    private double calculatePercent(Answer answer) {
        double all = userDataRepository.countByAnswersPoll(answer.getPoll());
        double one = userDataRepository.countByAnswers(answer);
        double percent = (one / all) * 100;
        DecimalFormat df = new DecimalFormat("##.#");
        try {
            return Double.valueOf(df.format(percent));
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
