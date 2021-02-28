package ru.vladrus13.method;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.vladrus13.Pair;
import ru.vladrus13.bean.Comment;
import ru.vladrus13.exception.FailedAPIRequestException;
import ru.vladrus13.utils.MethodsUtils;

import java.util.ArrayList;
import java.util.Collections;

public class BlogEntryComments {
    private final Integer blogEntryId;
    private ArrayList<Comment> comments;

    public BlogEntryComments(Integer blogEntryId) {
        this.blogEntryId = blogEntryId;
    }

    public void run() throws FailedAPIRequestException, IllegalAccessException {
        ArrayList<Pair<String, String>> arguments = MethodsUtils.getArguments(
                Collections.singletonList("blogEntryId"),
                Collections.singletonList(blogEntryId)
        );
        JSONArray response = (JSONArray) MethodsUtils.send("blogEntry.comments", arguments);
        comments = new ArrayList<>();
        for (Object jsonObject : response) {
            comments.add(new Comment((JSONObject) jsonObject));
        }
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }
}
