package ru.vladrus13.bean;

import org.json.JSONObject;

public class RecentAction extends Bean {

    Integer timeSeconds;
    @CanBeNull
    BlogEntry blogEntry;
    @CanBeNull
    Comment comment;

    public RecentAction(JSONObject object) throws IllegalAccessException {
        super(object);
    }

    public BlogEntry createBlogEntry(Object object) throws IllegalAccessException {
        if (!(object instanceof JSONObject)) {
            throw new IllegalArgumentException();
        }
        return new BlogEntry((JSONObject) object);
    }

    public Comment createComment(Object object) throws IllegalAccessException {
        if (!(object instanceof JSONObject)) {
            throw new IllegalArgumentException();
        }
        return new Comment((JSONObject) object);
    }
}
