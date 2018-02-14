package com.lee.nju_gpa_calculator.utils;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.lee.nju_gpa_calculator.activity.MainActivity;

import java.io.IOException;
import java.util.ArrayList;

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

    private ArrayList<String> termNumList;

    private OkHttpClient okHttpClient;
    private Headers.Builder headersBuilder;
    private FormBody.Builder bodyBuilder;
    private Handler handler;

    public OkHttpUtil(){
        //OkHttpClient必须构造成带有自动Cookie管理的形式，否则无法保存登录状态
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        okHttpClient = builder.cookieJar(new CookieJarImpl()).build();

        headersBuilder = new Headers.Builder();
        bodyBuilder = new FormBody.Builder();
        handler = new Handler();
        termNumList = new ArrayList();
    }


    /********************* 供外部调用的登录方法 **********************/
    public void confirmLogin(Context context, String studentID, String password){
        initTermList(studentID);
        Request request = buildLoginRequest(studentID, password);
        postAsyncLogin(context, request);
    }



    /********************* 供内部使用的封装方法 **********************/

    /**
     * 根据学号初始化学期数
     */
    private void initTermList(String stundentID){
        if(stundentID.length() == 9) {
            String startYear = stundentID.substring(0, 2);

            if (startYear.equals("16")) {
                termNumList.add("20161");
            } else if (startYear.equals("15")) {
                termNumList.add("20161");
                termNumList.add("20152");
                termNumList.add("20151");
            } else if (startYear.equals("14")) {
                termNumList.add("20161");
                termNumList.add("20152");
                termNumList.add("20151");
                termNumList.add("20142");
                termNumList.add("20141");
            } else if (startYear.equals("13")) {
                termNumList.add("20161");
                termNumList.add("20152");
                termNumList.add("20151");
                termNumList.add("20142");
                termNumList.add("20141");
                termNumList.add("20132");
                termNumList.add("20131");
            }
        }
    }

    /**
     * 封装后的南大教务网的登录post异步请求
     */
    private void postAsyncLogin(final Context context, final Request request){

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                MainActivity.setLoginState(false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    boolean result = new JsoupUtil().getLoginState(response.body().bytes());
                    //如果验证通过，则开始抓取学生个人信息和成绩列表
                    if(result){
                        MainActivity.setLoginState(true);

                        for(String termNum : termNumList) {
                            Request socreRequest = buildScoreRequest(termNum);
                            getAsyncScore(socreRequest);
                        }

                        Request infoRequest = buildInfoRequest();
                        getAsyncInfo(context, infoRequest);
                    } else {
                        MainActivity.setLoginState(false);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "登录失败,请重试。", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else {
                    MainActivity.setLoginState(false);
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
                    new JsoupUtil().updateInfo(context, response.body().bytes());
                }
            }
        });
    }

    /**
     * 封装后的南大教务网的课程分数get异步请求
     */
    private void getAsyncScore(Request request){

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    new JsoupUtil().updateScore(response.body().bytes());
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
//                .add(Constants.HEADER_NAME_AGENT, Constants.HEADER_VALUE_AGENT)
                .build();

        //生成body文件
        RequestBody requestBody = bodyBuilder
//                .add(Constants.LOGIN_BODY_NAME_PASSWORD, password)
//                .add(Constants.LOGIN_BODY_NAME_RETURNURL, Constants.LOGIN_BODY_VALUE_RETURNURL)
//                .add(Constants.LOGIN_BODY_NAME_USERNAME, studentID)
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
        //生成request文件
        Request request = new Request.Builder()
                .cacheControl(new CacheControl.Builder().noCache().build())
                .url(Constants.EDUCATION_SYSTEM_INFO_URL)
                .build();

        return request;
    }

    /**
     * 封装后的教务网课程成绩Request构建方法
     */
    private Request buildScoreRequest(String termNum){
        //生成request文件
        Request request = new Request.Builder()
                .cacheControl(CacheControl.FORCE_NETWORK)
                .url(Constants.EDUCATION_SYSTEM_SCORE_URL + termNum)
                .build();

        return request;
    }
}
