package com.lee.nju_gpa_calculator.utils;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by 果宝 on 2018/2/13.
 */

public class HeadersInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request().newBuilder()
                .cacheControl(new CacheControl.Builder().noCache().build())
                .addHeader(Constants.HEADER_NAME_REFERER, Constants.HEADER_VALUE_REFERER)
                .addHeader(Constants.HEADER_NAME_HOST, Constants.HEADER_VALUE_HOST)
                .addHeader(Constants.HEADER_NAME_CONTENT_VALUE, Constants.HEADER_VALUE_CONTENT_VALUE)
                .addHeader(Constants.HEADER_NAME_USER_AGENT, Constants.HEADER_VALUE_USER_AGENT)
                .build();
        return chain.proceed(request);
    }
}
