package ru.vladrus13.bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class BlogEntry extends Bean {

    public Integer id;
    public String originalLocale;
    public Integer creationTimeSeconds;
    public String authorHandle;
    public String title;
    @CanBeNull
    public String content;
    public String locale;
    public Integer modificationTimeSeconds;
    public Boolean allowViewHistory;
    public ArrayList<String> tags;
    public Long rating;

    public BlogEntry(JSONObject object) throws IllegalAccessException {
        super(object);
    }

    public ArrayList<String> createTags(Object object) {
        if (!(object instanceof JSONArray)) {
            throw new IllegalArgumentException();
        }
        JSONArray array = (JSONArray) object;
        ArrayList<String> returned = new ArrayList<>();
        for (Object s : array) {
            if (!(s instanceof String)) {
                throw new IllegalArgumentException();
            }
            returned.add((String) s);
        }
        return returned;
    }
}
