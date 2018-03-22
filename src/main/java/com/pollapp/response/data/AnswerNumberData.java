package com.pollapp.response.data;

import java.math.BigDecimal;

public class AnswerNumberData {

    private BigDecimal percent;

    private boolean top;

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public boolean isTop() {
        return top;
    }

    public void setTop(boolean top) {
        this.top = top;
    }
}
