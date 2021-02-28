package ru.vladrus13.bean;

import org.json.JSONObject;

public class Member extends Bean {

    public String handle;

    public Member(JSONObject object) throws IllegalAccessException {
        super(object);
    }
}
