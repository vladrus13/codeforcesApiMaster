package ru.vladrus13.bean;

import org.json.JSONObject;

import java.math.BigDecimal;

public class Submission extends Bean {

    Integer id;
    @CanBeNull
    Integer contestId;
    Integer creationTimeSeconds;
    Integer relativeTimeSeconds;
    Problem problem;
    Party author;
    String programmingLanguage;
    @CanBeNull
    String verdict;
    @CanBeNull
    String testset;
    Integer passedTestCount;
    Integer timeConsumedMillis;
    Integer memoryConsumedBytes;
    @CanBeNull
    BigDecimal points;

    public Submission(JSONObject object) throws IllegalAccessException {
        super(object);
    }

    public Party createAuthor(Object object) throws IllegalAccessException {
        if (!(object instanceof JSONObject)) {
            throw new IllegalArgumentException();
        }
        return new Party((JSONObject) object);
    }
}
