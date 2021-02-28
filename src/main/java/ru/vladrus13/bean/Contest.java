package ru.vladrus13.bean;

import org.json.JSONObject;

public class Contest extends Bean {

    public Integer id;
    public String name;
    public String type;
    public String phase;
    public Boolean frozen;
    public Integer durationSeconds;
    @CanBeNull
    public Integer startTimeSeconds;
    @CanBeNull
    public Integer relativeTimeSeconds;
    @CanBeNull
    public String preparedBy;
    @CanBeNull
    public String websiteUrl;
    @CanBeNull
    public String description;
    @CanBeNull
    public Integer difficulty;
    @CanBeNull
    public String kind;
    @CanBeNull
    public String icpcRegion;
    @CanBeNull
    public String country;
    @CanBeNull
    public String city;
    @CanBeNull
    public String season;

    public Contest(JSONObject object) throws IllegalAccessException {
        super(object);
    }
}
