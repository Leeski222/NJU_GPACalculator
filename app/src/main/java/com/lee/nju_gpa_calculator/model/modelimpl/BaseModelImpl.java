package com.lee.nju_gpa_calculator.model.modelimpl;

import com.lee.nju_gpa_calculator.utils.CookieJarImpl;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 果宝 on 2018/2/8.
 */


public class BaseModelImpl {
    private static final String BASE_URL = "http://elite.nju.edu.cn/jiaowu/";
    private static final int DEFAULT_TIMEOUT = 10;
    Retrofit retrofit;

    protected BaseModelImpl() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.cookieJar(new CookieJarImpl());
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }
}
