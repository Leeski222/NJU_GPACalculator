package com.lee.nju_gpa_calculator.presenter.gpa;

import com.lee.nju_gpa_calculator.presenter.HtmlParser;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by 果宝 on 2018/2/25.
 */

public class GPAHtmlParser extends HtmlParser {

//    private let termRegex = try! NSRegularExpression(pattern: "<tr align=\"center\" height=\"22\">\\s*?<td><a  href=\"(\\S*?)\"/>(\\S*?)</a> </td>\\s*?</tr>", options: NSRegularExpression.Options(rawValue:0));

    public static List<String> getTermList(Response<ResponseBody> response) {
        List<String> termList = new ArrayList();
        String html = responseToString(response);

        return termList;
    }
}
