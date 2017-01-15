package com.lee.nju_gpa_calculator.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.framed.Header;

/**
 * Created by Guo on 2017/1/15.
 */
public class OkHttpU {

    private static OkHttpClient okHttpClient;
    private static Headers.Builder headersBuilder;
    private static FormBody.Builder bodyBuilder;
    private static Request.Builder requestBuilder;

    public OkHttpU(){
        okHttpClient = new OkHttpClient();
        headersBuilder = new Headers.Builder();
        bodyBuilder = new FormBody.Builder();
        requestBuilder = new Request.Builder();
    }

    public static void confirmLogin(Context context, String studentID, String password){
        Log.e("LG", studentID);
        Log.e("LG", password);
        Request request = buildRequest(studentID, password);
        postAsync(context, request);
    }

    private static void postAsync(final Context context, Request request){
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    Toast.makeText(context, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private static Request buildRequest(String studentID, String password){
        //生成header文件
        Headers headers = headersBuilder
                .add(Constants.HEADER_NAME_HOST, Constants.HEADER_VALUE_HOST)
                .add(Constants.HEADER_NAME_REFERER, Constants.HEADER_VALUE_REFERER)
                .add(Constants.HEADER_NAME_AGENT, Constants.HEADER_VALUE_AGENT)
                .build();

        //生成body文件
        RequestBody requestBody = bodyBuilder
                .add(Constants.LOGIN_BODY_NAME_PASSWORD, password)
                .add(Constants.LOGIN_BODY_NAME_RETURNURL, Constants.LOGIN_BODY_VALUE_RETURNURL)
                .add(Constants.LOGIN_BODY_NAME_USERNAME, studentID)
                .build();

        //生成request文件
        Request request = requestBuilder
                .url(Constants.EDUCATION_SYSTEM_LOGIN_URL)
                .headers(headers)
                .post(requestBody)
                .build();

        return request;
    }
}
