package ru.vladrus13.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.vladrus13.bean.Contest;
import ru.vladrus13.bean.Problem;
import ru.vladrus13.bean.RanklistRow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ContestStandingsExcel {
    public static void write(Path path, Contest contest, ArrayList<Problem> problems, ArrayList<RanklistRow> ranklistRows) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Results");
        Row problemsRow = sheet.createRow(0);
        for (int i = 0; i < problems.size(); i++) {
            problemsRow.createCell(i + 1).setCellValue(problems.get(i).name);
        }
        for (int i = 0; i < ranklistRows.size(); i++) {
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(
                    ranklistRows.get(i).party.members.stream().map(member -> member.handle).collect(Collectors.joining(";")));
            for (int j = 0; j < problems.size(); j++) {
                row.createCell(j + 1).setCellValue(ranklistRows.get(i).problemResults.get(j).points.toString());
            }
        }
        for (int i = 0; i < problems.size(); i++) {
            sheet.autoSizeColumn(i);
        }
        workbook.write(Files.newOutputStream(path, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE_NEW));
        workbook.close();
    }

    public static void write(Path path, ArrayList<Contest> contests, ArrayList<ArrayList<Problem>> problemsList, ArrayList<ArrayList<RanklistRow>> ranklistRowsList) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        for (int contestId = 0; contestId < contests.size(); contestId++) {
            Contest contest = contests.get(contestId);
            ArrayList<Problem> problems = problemsList.get(contestId);
            ArrayList<RanklistRow> ranklistRows = ranklistRowsList.get(contestId);
            Sheet sheet = workbook.createSheet(contest.name);
            Row problemsRow = sheet.createRow(0);
            for (int i = 0; i < problems.size(); i++) {
                problemsRow.createCell(i + 1).setCellValue(problems.get(i).name);
            }
            for (int i = 0; i < ranklistRows.size(); i++) {
                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(
                        ranklistRows.get(i).party.members.stream().map(member -> member.handle).collect(Collectors.joining(";")));
                for (int j = 0; j < problems.size(); j++) {
                    row.createCell(j + 1).setCellValue(ranklistRows.get(i).problemResults.get(j).points.toString());
                }
            }
            for (int i = 0; i < problems.size(); i++) {
                sheet.autoSizeColumn(i);
            }
        }
        workbook.write(Files.newOutputStream(path, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE));
        workbook.close();
    }
}
