package com.lee.nju_gpa_calculator.model.service;

import android.util.Log;

import com.lee.nju_gpa_calculator.utils.CookieJarImpl;
import com.lee.nju_gpa_calculator.utils.HeadersInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 果宝 on 2018/2/21.
 */

public class RetrofitServer {
    private static final String BASE_URL = "http://elite.nju.edu.cn/jiaowu/";
    private static final int DEFAULT_TIMEOUT = 10;
    private Retrofit retrofit;

    private static RetrofitServer mRetrofitServer;

    private RetrofitServer() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.cookieJar(new CookieJarImpl());
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
//        httpClientBuilder.addInterceptor(new HeadersInterceptor());

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
    }

    public static synchronized RetrofitServer getInstance() {
        if(mRetrofitServer == null) {
            Log.e("RetrofitServer", "create instance");
            mRetrofitServer = new RetrofitServer();
        }
        return mRetrofitServer;
    }

    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }
}
