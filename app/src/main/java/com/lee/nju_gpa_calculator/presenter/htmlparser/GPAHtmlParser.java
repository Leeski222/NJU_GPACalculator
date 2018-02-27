package com.lee.nju_gpa_calculator.presenter.htmlparser;

import android.util.Log;

import com.lee.nju_gpa_calculator.model.vopo.CourseVO;
import com.lee.nju_gpa_calculator.presenter.htmlparser.HtmlParser;
import com.lee.nju_gpa_calculator.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by 果宝 on 2018/2/25.
 */

public class GPAHtmlParser extends HtmlParser {

    private static final String termRegex =
            "<tr align=\"center\" height=\"22\">\\s*?" +
                "<td><a  href=\"(\\S*?)\"/>(\\S*?)</a> </td>\\s*?" +
            "</tr>";

    public static Map<String, String> getTermsBy (Response<ResponseBody> response) {
        Map<String, String> termMap = new HashMap();

        String html = responseToString(response);

        mMatcher = regexMatching(termRegex, html);

        while(mMatcher.find()) {
            // 第二段形式"student/studentinfo/achievementinfo.do?method=searchTermList&termCode=20xxx"
            String termCode = mMatcher.group(1).split("=")[2];
            // 第一段的形式"<tr align="center" height="22"><td><a  href="student/studentinfo/achievementinfo.do?method=searchTermList&termCode=20xxx"/>20xx-20xx学年第一学期</a> </td></tr>"
            String termName = mMatcher.group(0).split("/>")[1].split("</")[0];
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
                    "<ul\\s*?style=\"color:black\"\\s*?>\\s*?(\\S*?)\\s*?</ul>\\s*?" +
            "</td>";

    public static List<CourseVO> getCoursesBy (Response<ResponseBody> response) {

        List<CourseVO> courseVOList = new ArrayList();

        String html = responseToString(response);

        LogUtil.e("getCourses", html);

        mMatcher = regexMatching(courseRegex, html);

        while (mMatcher.find()) {
                String subject = mMatcher.group(1).trim();
                String EnglishName = mMatcher.group(2).trim();
                String category = mMatcher.group(3).trim();
                String credit = mMatcher.group(4).trim();
                String finalScore = mMatcher.group(0)
                        .replace(" ", "")
                        .replace("\n", "")
                        .replace("\t", "");
//                        .split("<ulstyle=\"color:black\">")[1]
            
//                        .split("<")[0];
//                String finalScore = mMatcher.group(0).replace(" ", "").split("<ulstyle=\"color:black\">")[1].split("<")[0];
                Log.e("getCourses", subject + " " + EnglishName + " " + category + " " + credit + " " + finalScore);
        }

        return null;
    }
}
