package com.lee.nju_gpa_calculator.utils;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.lee.nju_gpa_calculator.activity.GPAActivity;
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
    private Handler handler;

    public OkHttpUtil(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        okHttpClient = builder.cookieJar(new CookieJarImpl()).build();
        headersBuilder = new Headers.Builder();
        bodyBuilder = new FormBody.Builder();
        handler = new Handler();
    }

    /********************* 供外部调用的登录方法 **********************/
    public void confirmLogin(Context context, String studentID, String password){
        GPAActivity.setUserID(studentID);
        Request request = buildLoginRequest(studentID, password);
        postAsyncLogin(context, request);
    }

    /********************* 供内部使用的封装方法 **********************/

    /**
     * 封装后的南大教务网的登录post异步请求
     */
    private void postAsyncLogin(final Context context, Request request){

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

                        Request request = buildInfoRequest();
                        getAsyncInfo(context, request);
                    } else {
                        MainActivity.setLoginState(false);
                        Log.e("LG", "登录失败");
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "登录失败,请重试。", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    MainActivity.setLoginState(false);
                    Log.e("LG", "Response失败");
                }
            }
        });
    }

    /**
     * 封装后的南大教务网的个人信息get异步请求
     */
    private void getAsyncInfo(final Context context, Request request){

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    Log.e("LG", "已经获得个人信息的response");
                    new JsoupUtil().updateInfoByParseResponse(context, response.body().bytes());
                }
            }
        });
    }

    /**
     * 封装后的教务网登录Request构建方法
     */
    private Request buildLoginRequest(String studentID, String password){
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

        return request;
    }

    /**
     * 封装后的教务网个人信息Request构建方法
     */
    private Request buildInfoRequest(){
//        //生成header文件
//        Headers headers = headersBuilder
//                .add(Constants.HEADER_NAME_HOST, Constants.HEADER_VALUE_HOST)
//                .add(Constants.HEADER_NAME_REFERER, Constants.HEADER_VALUE_REFERER_INFO)
//                .add(Constants.HEADER_NAME_AGENT, Constants.HEADER_VALUE_AGENT)
//                .build();

        //生成request文件
        Request request = new Request.Builder()
                .cacheControl(new CacheControl.Builder().noCache().build())
                .url(Constants.EDUCATION_SYSTEM_INFO_URL)
                .build();

        return request;
    }
}
