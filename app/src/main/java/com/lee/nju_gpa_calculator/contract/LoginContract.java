package com.lee.nju_gpa_calculator.contract;

import android.graphics.Bitmap;

import com.lee.nju_gpa_calculator.presenter.BasePresenter;
import com.lee.nju_gpa_calculator.utils.LoginResult;
import com.lee.nju_gpa_calculator.view.BaseView;

/**
 * Created by 果宝 on 2018/2/3.
 */

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void setValidateCodeImage(Bitmap bitmap);

        void refreshValidateCodeSuccess();

        void refreshValidateCodeFailed();

        void loginSuccess();

        void loginFailed(LoginResult result);
    }

    interface Presenter extends BasePresenter {
        void getValidateCodeImage();

        void login(String id, String password, String validateCode);
    }

}
