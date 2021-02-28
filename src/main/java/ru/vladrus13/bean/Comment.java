package ru.vladrus13.bean;

import org.json.JSONObject;

public class Comment extends Bean {

    public Integer id;
    public Integer creationTimeSeconds;
    public String commentatorHandle;
    public String locale;
    public String text;
    @CanBeNull
    public Integer parentCommentId;
    public Integer rating;

    public Comment(JSONObject object) throws IllegalAccessException {
        super(object);
    }
}
