package com.pollapp.bean.answer;

import com.pollapp.entity.Answer;

public class AnswerJsonResponse {

    private AnswerNumberData answerNumberData;

    private Answer answer;

    public AnswerJsonResponse() {
        answerNumberData = new AnswerNumberData();
        answer = new Answer();
    }

    public AnswerJsonResponse(Answer answer, AnswerNumberData answerNumberData) {
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
