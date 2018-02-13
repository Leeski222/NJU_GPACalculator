package com.lee.nju_gpa_calculator.model.service;

import com.lee.nju_gpa_calculator.utils.Constants;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by 果宝 on 2018/2/3.
 */

public interface LoginService {
    @Headers({
            "Referer: elite.nju.edu.cn/jiaowu/",
            "Host: elite.nju.edu.cn",
            "Content-Type: application/x-www-form-urlencoded",
            "User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko"
    })

    @GET("ValidateCode.jsp")
    Observable<Response<ResponseBody>> getValidateCodeImage();

    @FormUrlEncoded
    @POST("login.do")
    Observable<Response<ResponseBody>> login(
            @Header("Cookie") String cookie,
            @Field("userName") String studentID,
            @Field("password") String password,
            @Field("ValidateCode") String validateCode,
            @Field("returnUrl") String returnUrl
    );

}
