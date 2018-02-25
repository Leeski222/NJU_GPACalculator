package com.lee.nju_gpa_calculator.model.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by 果宝 on 2018/2/3.
 */

public interface LoginService {

    @GET("ValidateCode.jsp")
    Observable<Response<ResponseBody>> getValidateCodeImage();

    @FormUrlEncoded
    @POST("login.do")
    Observable<Response<ResponseBody>> login(
            @Field("userName") String studentID,
            @Field("password") String password,
            @Field("ValidateCode") String validateCode,
            @Field("returnUrl") String returnUrl
    );

}
