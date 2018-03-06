package com.pollapp.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pollapp.entity.Answer;
import com.pollapp.response.data.AnswerNumberData;
import com.pollapp.response.process.AnswerProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerResponse {

    @JsonIgnore
    @Autowired
    private AnswerProcess answerProcess;

    private AnswerNumberData answerNumberData;

    private Answer answer;

    private AnswerResponse() {
    }

    private AnswerResponse(Answer answer, AnswerNumberData answerNumberData) {
        setAnswer(answer);
        setAnswerNumberData(answerNumberData);
    }

    public AnswerResponse create(Answer answer) {
        return new AnswerResponse(answer, answerProcess.process(answer));
    }

    public List<AnswerResponse> create(List<Answer> answers) {
        List<AnswerResponse> response = new ArrayList<>();
        answers.forEach(answer ->
                response.add(create(answer)));
        return response;
    }

    public Page<AnswerResponse> create(Page<Answer> answers, Pageable pageable) {
        List<AnswerResponse> response = new ArrayList<>();
        answers.forEach(answer ->
                response.add(create(answer)));
        return new PageImpl<>(response, pageable, answers.getTotalElements());
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
