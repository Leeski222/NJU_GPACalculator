package com.lee.nju_gpa_calculator.presenter.htmlparser;

import android.util.Log;

import com.lee.nju_gpa_calculator.model.vopo.GradeVO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by 果宝 on 2018/2/25.
 */

public class GPAHtmlParser extends HtmlParser {

    private static final String termRegex =
            "<tr align=\"center\" height=\"22\">\\s*?" +
                "<td><a  href=\"student/studentinfo/achievementinfo\\.do\\?method=searchTermList&termCode=(\\S*?)\"/>(\\S*?)</a> </td>\\s*?" +
            "</tr>";

    public static Map<String, String> getTermsBy (Response<ResponseBody> response) {
        Map<String, String> termMap = new LinkedHashMap<>();

        String html = responseToString(response);

        mMatcher = regexMatching(termRegex, html);

        while(mMatcher.find()) {
            String termCode = mMatcher.group(1);
            String termName = mMatcher.group(2);
            termMap.put(termCode, termName);
        }

        return termMap;
    }

    private static final String courseRegex =
            "<td valign=\"middle\">(.*?)</td>\\s*?" +
                    "<td valign=\"middle\">(.*?)</td>\\s*?" +
                    "<td align=\"center\" valign=\"middle\">\\s*?(.*?)\\s*?</td>\\s*?" +
                    "<td align=\"center\" valign=\"middle\">(.*?)</td>\\s*?" +
                    "<td align=\"center\" valign=\"middle\">\\s*?" +
                    "<ul\\s*?style=\"color:(black|red)\"\\s*?>\\s*?(\\S*?)\\s*?</ul>\\s*?" +
            "</td>";

    public static List<GradeVO> getCoursesBy (Response<ResponseBody> response) {

        List<GradeVO> gradeVOList = new ArrayList();

        String html = responseToString(response);

        mMatcher = regexMatching(courseRegex, html);

        while (mMatcher.find()) {
                String subject = mMatcher.group(1).trim();
                String EnglishName = mMatcher.group(2).trim();
                String category = mMatcher.group(3).trim();

                String credit = mMatcher.group(4).trim();
                if(credit.equals("")) {
                    credit = "无";
                }

                String finalScore = mMatcher.group(6);
                if(finalScore.equals("无成绩")) {
                    finalScore = "0.0";
                }

                GradeVO gradeVO = new GradeVO();
                gradeVO.setSubject(subject);
                gradeVO.setEnglishName(EnglishName);
                gradeVO.setCategory(category);
                gradeVO.setCredit(credit);
                gradeVO.setFinalScore(finalScore);

                gradeVOList.add(gradeVO);
        }

        return gradeVOList;
    }
}
