package com.lee.nju_gpa_calculator.presenter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by 果宝 on 2018/2/25.
 */

public class HtmlParser {

    public static String responseToString(Response<ResponseBody> response) {
        String str;
        try {
            str = new String( response.body().bytes() );
        } catch (IOException e) {
            str = "";
        }
        return str;
    }
}
