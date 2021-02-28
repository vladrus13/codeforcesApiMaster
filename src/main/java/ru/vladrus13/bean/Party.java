package ru.vladrus13.bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Party extends Bean {

    @CanBeNull
    public Integer contestId;
    public ArrayList<Member> members;
    public String participantType;
    @CanBeNull
    public Integer teamId;
    @CanBeNull
    public String teamName;
    public Boolean ghost;
    @CanBeNull
    public Integer room;
    @CanBeNull
    public Integer startTimeSeconds;

    public Party(JSONObject object) throws IllegalAccessException {
        super(object);
    }

    public ArrayList<Member> createMembers(Object object) throws IllegalAccessException {
        if (!(object instanceof JSONArray)) {
            throw new IllegalArgumentException();
        }
        JSONArray array = (JSONArray) object;
        ArrayList<Member> returned = new ArrayList<>();
        for (Object s : array) {
            if (!(s instanceof JSONObject)) {
                throw new IllegalArgumentException();
            }
            returned.add(new Member((JSONObject) s));
        }
        return returned;
    }
}
