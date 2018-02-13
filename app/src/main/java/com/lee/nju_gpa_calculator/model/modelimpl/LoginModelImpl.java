package com.lee.nju_gpa_calculator.model.modelimpl;

import com.lee.nju_gpa_calculator.model.modelinterface.LoginModel;
import com.lee.nju_gpa_calculator.model.service.LoginService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by 果宝 on 2018/2/3.
 */

public class LoginModelImpl extends BaseModelImpl implements LoginModel {

    private LoginService loginService;

    public LoginModelImpl() {
        loginService = retrofit.create(LoginService.class);
    }

    @Override
    public void getValidateCodeImage(Observer<Response<ResponseBody>> observer) {
        loginService.getValidateCodeImage()
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void login(Observer<Response<ResponseBody>> observer, String cookie, String userName, String password, String validateCode) {
        String returnUrl = "null";
        loginService.login(cookie, userName, password, validateCode, returnUrl)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
