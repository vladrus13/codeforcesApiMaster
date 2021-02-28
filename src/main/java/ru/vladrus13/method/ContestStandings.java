package ru.vladrus13.method;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.vladrus13.Pair;
import ru.vladrus13.bean.Contest;
import ru.vladrus13.bean.Problem;
import ru.vladrus13.bean.RanklistRow;
import ru.vladrus13.exception.FailedAPIRequestException;
import ru.vladrus13.utils.MethodsUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class ContestStandings {

    private final Integer contestId;
    private final Integer from;
    private final Integer count;
    private final ArrayList<String> handles;
    private final Integer room;
    private final Boolean showUnofficial;

    private Contest contest;
    private ArrayList<Problem> problems;
    private ArrayList<RanklistRow> ranklistRows;

    public ContestStandings(Integer contestId, Integer from, Integer count, ArrayList<String> handles, Integer room, Boolean showUnofficial) {
        if (contestId == null) {
            throw new IllegalArgumentException("Contest id can't be null");
        }
        this.contestId = contestId;
        this.from = from;
        this.count = count;
        this.handles = handles;
        this.room = room;
        this.showUnofficial = showUnofficial;
    }

    public void run() throws FailedAPIRequestException, IllegalAccessException {
        ArrayList<Pair<String, String>> arguments = MethodsUtils.getArguments(
                Arrays.asList("contestId", "from", "count", "handles", "room", "showUnofficial"),
                Arrays.asList(contestId, from, count, handles, room, showUnofficial)
        );
        JSONObject response = (JSONObject) MethodsUtils.send("contest.standings", arguments);
        contest = new Contest(response.getJSONObject("contest"));
        problems = new ArrayList<>();
        JSONArray problemArray = (JSONArray) response.get("problems");
        for (Object jsonObject : problemArray) {
            problems.add(new Problem((JSONObject) jsonObject));
        }
        JSONArray ranklist = (JSONArray) response.get("rows");
        ranklistRows = new ArrayList<>();
        for (Object jsonObject : ranklist) {
            ranklistRows.add(new RanklistRow((JSONObject) jsonObject));
        }
    }

    public Contest getContest() {
        return contest;
    }

    public ArrayList<Problem> getProblems() {
        return problems;
    }

    public ArrayList<RanklistRow> getRanklistRows() {
        return ranklistRows;
    }
}
