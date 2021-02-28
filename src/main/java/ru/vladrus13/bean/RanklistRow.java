package ru.vladrus13.bean;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;

public class RanklistRow extends Bean {

    public Party party;
    public Integer rank;
    public BigDecimal points;
    public Integer penalty;
    public Integer successfulHackCount;
    public Integer unsuccessfulHackCount;
    public ArrayList<ProblemResult> problemResults;
    @CanBeNull
    public Integer lastSubmissionTimeSeconds;

    public RanklistRow(JSONObject object) throws IllegalAccessException {
        super(object);
    }

    public Party createParty(Object object) throws IllegalAccessException {
        if (!(object instanceof JSONObject)) {
            throw new IllegalArgumentException();
        }
        return new Party((JSONObject) object);
    }

    public ArrayList<ProblemResult> createProblemResults(Object object) throws IllegalAccessException {
        if (!(object instanceof JSONArray)) {
            throw new IllegalArgumentException();
        }
        JSONArray array = (JSONArray) object;
        ArrayList<ProblemResult> returned = new ArrayList<>();
        for (Object s : array) {
            if (!(s instanceof JSONObject)) {
                throw new IllegalArgumentException();
            }
            returned.add(new ProblemResult((JSONObject) s));
        }
        return returned;
    }
}
