package com.lee.nju_gpa_calculator.presenter.htmlparser;

import android.util.Log;

import com.lee.nju_gpa_calculator.presenter.htmlparser.HtmlParser;
import com.lee.nju_gpa_calculator.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by 果宝 on 2018/2/25.
 */

public class GPAHtmlParser extends HtmlParser {

    private static final String termRegex =
            "<tr align=\"center\" height=\"22\">\\s*?" +
                "<td><a  href=\"(\\S*?)\"/>(\\S*?)</a> </td>" +
            "\\s*?</tr>";

    public static List<String> getTermList(Response<ResponseBody> response) {

        if(response != null ) {

            String html = responseToString(response);

            mPattern = Pattern.compile(termRegex);
            mMatcher = mPattern.matcher(html);

            if(mMatcher.find()) {
                Log.e("GPAHtmlParser", mMatcher.groupCount() + "");
                for(int i = 0; i < mMatcher.groupCount(); i ++) {
                    String term = mMatcher.group(i);
                    Log.e(i + "GPAHtmlParser", term);
                }
            }

            List<String> termList = new ArrayList();

            return termList;

        } else {
            return null;
        }
    }
}
