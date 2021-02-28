package ru.vladrus13.bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Problem extends Bean {

    @CanBeNull
    public Integer contestId;
    @CanBeNull
    public String problemsetName;
    public String index;
    public String name;
    public String type;
    @CanBeNull
    public BigDecimal points;
    @CanBeNull
    public Integer rating;
    public ArrayList<String> tags;

    public Problem(JSONObject object) throws IllegalAccessException {
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
