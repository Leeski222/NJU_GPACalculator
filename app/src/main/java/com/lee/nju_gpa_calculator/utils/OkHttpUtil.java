package com.lee.nju_gpa_calculator.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lee.nju_gpa_calculator.activity.GPAActivity;
import com.lee.nju_gpa_calculator.activity.InfoActivity;
import com.lee.nju_gpa_calculator.activity.MainActivity;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Guo on 2017/1/15.
 *
 * 以OkHttp3为基础，封装为专门进行南大教务网相关的网络通信的工具类
 */
public class OkHttpUtil {

    private OkHttpClient okHttpClient;
    private Headers.Builder headersBuilder;
    private FormBody.Builder bodyBuilder;

    public OkHttpUtil(){
        okHttpClient = new OkHttpClient();
        headersBuilder = new Headers.Builder();
        bodyBuilder = new FormBody.Builder();
    }

    /********************* 供外部调用的登录方法 **********************/
    public void confirmLogin(Context context, String studentID, String password){
        GPAActivity.setUserID(studentID);
        Request request = buildRequest(studentID, password);
        postAsync(context, request);
    }


    /********************* 供内部使用的封装方法 **********************/

    /**
     * 封装后的南大教务网的登录post异步请求
     */
    private void postAsync(final Context context, Request request){

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MainActivity.setLoginState(false);
                Log.e("LG", "调用Async失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    boolean result = new JsoupUtil().getLoginStateByParseResponse(response.body().bytes());
                    if(result){
                        Log.e("LG", "成功调用Async");
                        MainActivity.setLoginState(true);
                        context.startActivity(new Intent(context, InfoActivity.class));
                    } else {
                        Log.e("LG", "JsoupUtil失败");
                    }
                } else {
                    MainActivity.setLoginState(false);
                    Log.e("LG", "Response失败");
                }
            }
        });

    }

    /**
     * 封装好的教务网登录Request构建方法
     */
    private Request buildRequest(String studentID, String password){
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
        Request request = new Request.Builder()
                .cacheControl(new CacheControl.Builder().noCache().build())
                .url(Constants.EDUCATION_SYSTEM_LOGIN_URL)
                .headers(headers)
                .post(requestBody)
                .build();

        Log.e("LG", "调用request");
        return request;
    }
}
