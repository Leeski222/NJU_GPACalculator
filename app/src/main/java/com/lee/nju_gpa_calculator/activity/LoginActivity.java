package com.lee.nju_gpa_calculator.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lee.nju_gpa_calculator.R;
import com.lee.nju_gpa_calculator.utils.OkHttpU;

/**
 * Created by Guo on 2017/1/15.
 *
 * 登录界面的逻辑层
 */
public class LoginActivity extends AppCompatActivity{

    private Button confirmButton;
    private EditText userNameEditText;
    private EditText passwordEditText;

    private static boolean loginState = false;
    private static String studentID = null;

    /**
     * 获取登录状态的方法
     */
    public static boolean isLogin() {
        return loginState;
    }

    /**
     * 设置登录状态的方法
     */
    private static void setLoginState(boolean state){
        loginState = state;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();
    }

    /**
     * 通过调用这个方法启动LoginActivity
     */
    public static void actionStart(Context context, int type, byte[] verificationCode) {

    }


    /**
     * 初始化按键
     */
    private void initViews() {
        Toast.makeText(LoginActivity.this, "设置监听成功", Toast.LENGTH_SHORT).show();

        confirmButton = (Button) findViewById(R.id.btn_confirm);
        userNameEditText = (EditText) findViewById(R.id.et_inputUsername);
        passwordEditText = (EditText) findViewById(R.id.et_inputPassword);

        final String userName = userNameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpU.confirmLogin(LoginActivity.this, userName, password);
            }
        });
    }

    /**
     * 通过解析post请求返回的数据，判断是否登录成功了
     */
    private void parseResponseFromLogin(byte[] response) {

    }

    /**
     * 查询成绩的一系列操作
     */
    public static void searchScoreOperation(final Context context) {

    }
}
