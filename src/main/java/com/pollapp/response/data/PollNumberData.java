package com.pollapp.response.data;

import java.math.BigDecimal;

public class PollNumberData {

    private BigDecimal totalAnswers;

    private long comments;

    public BigDecimal getTotalAnswers() {
        return totalAnswers;
    }

    public void setTotalAnswers(BigDecimal totalAnswers) {
        this.totalAnswers = totalAnswers;
    }

    public long getComments() {
        return comments;
    }

    public void setComments(long comments) {
        this.comments = comments;
    }
}
