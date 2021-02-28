package ru.vladrus13;

import ru.vladrus13.bean.Comment;
import ru.vladrus13.bean.Contest;
import ru.vladrus13.bean.Problem;
import ru.vladrus13.bean.RanklistRow;
import ru.vladrus13.excel.ContestStandingsExcel;
import ru.vladrus13.exception.FailedAPIRequestException;
import ru.vladrus13.method.BlogEntryComments;
import ru.vladrus13.method.ContestStandings;
import ru.vladrus13.request.Sender;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Launcher {

    private static void contestStandingTest() throws FailedAPIRequestException, IllegalAccessException, IOException {
        ArrayList<Integer> contestIds = new ArrayList<>(List.of(297987, 298936, 301138, 302111, 303095, 303923, 305026, 306046, 307017, 309011, 309099, 311941, 312936, 313896, 314855, 315740));
        // 296911, 297987, 298936, 301138, 302111, 303095, 303923, 305026, 306046, 307017, 309011, 309099, 311941, 312936, 313896, 314855, 315740
        ArrayList<Contest> contests = new ArrayList<>();
        ArrayList<ArrayList<Problem>> problems = new ArrayList<>();
        ArrayList<ArrayList<RanklistRow>> ranklistRowList = new ArrayList<>();
        for (int id : contestIds) {
            ContestStandings contestStandings = new ContestStandings(id, null, null, null, null, true);
            contestStandings.run();
            contests.add(contestStandings.getContest());
            problems.add(contestStandings.getProblems());
            ranklistRowList.add(contestStandings.getRanklistRows());
        }
        ContestStandingsExcel.write(Path.of("result.xlsx"),
                contests, problems, ranklistRowList);
    }

    private static void blogEntryCommentsTest() throws FailedAPIRequestException, IllegalAccessException {
        BlogEntryComments blogEntryComments = new BlogEntryComments(79);
        blogEntryComments.run();
        ArrayList<Comment> comments = blogEntryComments.getComments();
        for (Comment comment : comments) {
            System.out.println(comment.text);
        }
    }

    public static void main(String[] args) throws IllegalAccessException, FailedAPIRequestException {
        blogEntryCommentsTest();
        Sender.stop();
    }
}