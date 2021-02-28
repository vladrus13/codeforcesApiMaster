package ru.vladrus13.bean;

import org.json.JSONObject;

public class RatingChange extends Bean {

    Integer contestId;
    String contestName;
    String handle;
    Integer rank;
    Integer ratingUpdateTimeSeconds;
    Integer oldRating;
    Integer newRating;

    public RatingChange(JSONObject object) throws IllegalAccessException {
        super(object);
    }
}
