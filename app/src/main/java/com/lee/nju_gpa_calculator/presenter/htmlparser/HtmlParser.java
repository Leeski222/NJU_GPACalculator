package com.lee.nju_gpa_calculator.presenter.htmlparser;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.adapter.rxjava2.Result;

/**
 * Created by 果宝 on 2018/2/25.
 */

public class HtmlParser {

    protected static Pattern mPattern;

    protected static Matcher mMatcher;

    protected static String responseToString(Response<ResponseBody> response) {

        try {
            return response.body().string();
        } catch (IOException e) {
            return "";
        }

    }
}
