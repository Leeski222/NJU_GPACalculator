package com.lee.nju_gpa_calculator.model.service;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 果宝 on 2018/2/3.
 */

public interface GPAService {

    @GET("student/studentinfo/achievementinfo.do")
    Observable<Response<ResponseBody>> getAchievementList(
            @Query("method") String method
    );

    @GET("student/studentinfo/achievementinfo.do")
    Observable<Response<ResponseBody>> getAchievementInfo(
            @Query("method") String method,
            @Query("termCode") String termCode
    );

}
