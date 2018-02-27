package com.lee.nju_gpa_calculator.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.lee.nju_gpa_calculator.contract.LoginContract;
import com.lee.nju_gpa_calculator.model.ModelRepository;
import com.lee.nju_gpa_calculator.model.modelinterface.LoginModel;
import com.lee.nju_gpa_calculator.utils.LoginResult;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by 果宝 on 2018/2/3.
 */

public class LoginPresenter implements LoginContract.Presenter{

    private LoginModel loginModel;
    private LoginContract.View loginView;

    public LoginPresenter(LoginContract.View loginView) {
        this.loginModel = ModelRepository.getInstance().getLoginModel();
        this.loginView = loginView;
    }

    @Override
    public void start() {
        this.getValidateCodeImage();
    }

    @Override
    public void getValidateCodeImage() {
        loginModel.getValidateCodeImage(new Observer<Response<ResponseBody>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<ResponseBody> response) {
                Log.e("LoginPresenter", "getValidateImage");

                if(response.isSuccessful()) {
                    Headers headers = response.headers();
////                    String sessionID = headers.get("Set-Cookie").split(";")[0];
//                    String sessionID = null;
//
//                    cookie = sessionID;

                    byte[] bytes = new byte[0];

                    try {
                        bytes = response.body().bytes();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    final Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

                    loginView.setValidateCodeImage(bitmap);
                    loginView.refreshValidateCodeSuccess();
                }
            }

            @Override
            public void onError(Throwable e) {
                loginView.refreshValidateCodeFailed();
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void login(String id, String password, String validateCode) {

        loginModel.login(new Observer<Response<ResponseBody>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    String html = "";

                    try {
                        html = new String(response.body().bytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Log.e("LoginPresenter", html);

                    loginView.loginSuccess();
                } else {
                }
            }

            @Override
            public void onError(Throwable e) {
                loginView.loginFailed(LoginResult.NETWORK_CONNECTION_ERROR);
            }

            @Override
            public void onComplete() {

            }
        }, id, password, validateCode);
    }

}
