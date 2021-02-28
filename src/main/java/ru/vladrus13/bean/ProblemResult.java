package ru.vladrus13.bean;

import org.json.JSONObject;

import java.math.BigDecimal;

public class ProblemResult extends Bean {

    public BigDecimal points;
    @CanBeNull
    public Integer penalty;
    public Integer rejectedAttemptCount;
    public String type;
    @CanBeNull
    public Integer bestSubmissionTimeSeconds;

    public ProblemResult(JSONObject object) throws IllegalAccessException {
        super(object);
    }
}
