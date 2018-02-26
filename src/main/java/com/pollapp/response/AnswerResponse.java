package com.pollapp.response;

import com.pollapp.entity.Answer;
import com.pollapp.response.data.AnswerNumberData;

public class AnswerResponse {

    private AnswerNumberData answerNumberData;

    private Answer answer;

    public AnswerResponse() {}

    public AnswerResponse(Answer answer, AnswerNumberData answerNumberData) {
        setAnswer(answer);
        setAnswerNumberData(answerNumberData);
    }

    public AnswerNumberData getAnswerNumberData() {
        return answerNumberData;
    }

    public void setAnswerNumberData(AnswerNumberData answerNumberData) {
        this.answerNumberData = answerNumberData;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
