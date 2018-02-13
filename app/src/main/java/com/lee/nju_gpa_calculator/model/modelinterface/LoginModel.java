package com.lee.nju_gpa_calculator.model.modelinterface;

import io.reactivex.Observer;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by 果宝 on 2018/2/3.
 */

public interface LoginModel {
    void getValidateCodeImage(Observer<Response<ResponseBody>> observer);

    void login(Observer<Response<ResponseBody>> observer,
               String cookie, String id, String password, String validateCode);
}
