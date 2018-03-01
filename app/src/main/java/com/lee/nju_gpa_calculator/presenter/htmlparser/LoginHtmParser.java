package com.lee.nju_gpa_calculator.presenter.htmlparser;

import com.lee.nju_gpa_calculator.utils.LoginResult;

import okhttp3.ResponseBody;
import retrofit2.Response;

/**
 * Created by 果宝 on 2018/2/28.
 */

public class LoginHtmParser extends HtmlParser{

    public static LoginResult getLoginResultBy(Response<ResponseBody> response) {
        String html = responseToString(response);

        if(html.contains("验证码错误")) {
            return LoginResult.VALIDATE_CODE_ERROR;
        }else if(html.contains("验证码已过期")) {
            return LoginResult.VALIDATE_CODE_EXPIRED;
        } else if(html.contains("用户名错误") || html.contains("密码错误")) {
            return LoginResult.USERNAME_OR_PASSWORD_ERROR;
        } else {
            return LoginResult.SUCCESS;
        }

    }

}
