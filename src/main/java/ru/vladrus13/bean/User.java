package ru.vladrus13.bean;

import org.json.JSONObject;

public class User extends Bean {
    String handle;
    @CanBeNull
    String email;
    @CanBeNull
    String vkId;
    @CanBeNull
    String openId;
    @CanBeNull
    String firstName;
    @CanBeNull
    String lastName;
    @CanBeNull
    String country;
    @CanBeNull
    String city;
    @CanBeNull
    String organization;
    Integer contribution;
    String rank;
    Integer rating;
    String maxRank;
    Integer maxRating;
    Integer lastOnlineTimeSeconds;
    Integer registrationTimeSeconds;
    Integer friendOfCount;
    String avatar;
    String titlePhoto;

    public User(JSONObject object) throws IllegalAccessException {
        super(object);
    }
}
