package com.lee.nju_gpa_calculator.model.modelinterface;

import io.reactivex.Observable;
import io.reactivex.Observer;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by 果宝 on 2018/2/3.
 */

public interface GPAModel {

    void getAchievementList(Observer<Response<ResponseBody>> observer);

    void getAchievementInfoByTerm(Observer<Response<ResponseBody>> observer,
                                  String term);

}
