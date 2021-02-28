package ru.vladrus13.bean;

import org.json.JSONObject;

public class ProblemStatistics extends Bean {

    @CanBeNull
    Integer contestId;
    String index;
    Integer solvedCount;

    public ProblemStatistics(JSONObject object) throws IllegalAccessException {
        super(object);
    }
}
