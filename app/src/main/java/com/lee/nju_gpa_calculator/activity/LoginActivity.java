package com.lee.nju_gpa_calculator.activity;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.lee.nju_gpa_calculator.R;
import com.lee.nju_gpa_calculator.utils.AppData;
import com.lee.nju_gpa_calculator.utils.OkHttpUtil;

/**
 * Created by Guo on 2017/1/15.
 *
 * 登录界面的逻辑层
 */
public class LoginActivity extends AppCompatActivity{
    private Button confirmButton;
    private EditText userNameEditText;
    private EditText passwordEditText;
    private CheckBox rIDCheckBox;
    private CheckBox rPaCheckBox;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        handler = new Handler();
        initViews();
        initData();
    }

    /**
     * 初始化按键
     */
    private void initViews() {
        confirmButton = (Button) findViewById(R.id.btn_confirm);
        userNameEditText = (EditText) findViewById(R.id.et_inputUsername);
        passwordEditText = (EditText) findViewById(R.id.et_inputPassword);
        rIDCheckBox = (CheckBox) findViewById(R.id.cb_remember_name);
        rPaCheckBox = (CheckBox) findViewById(R.id.cb_remember_password);

        //为登录键添加监听
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = userNameEditText.getText().toString();
                final String password = passwordEditText.getText().toString();
                Log.e("LG", userName + "　" + password);

                saveData(userName, password);

                new OkHttpUtil().confirmLogin(LoginActivity.this, userName, password);
            }
        });
    }

    /**
     * 根据复选框的情况而确定是否在app中保存账号和密码
     */
    private void saveData(String userName, String password){
        if(rIDCheckBox.isChecked()){
            AppData.saveID(LoginActivity.this, userName);
        } else {
            AppData.saveID(LoginActivity.this, null);
        }

        if(rPaCheckBox.isChecked()){
            AppData.savePassword(LoginActivity.this, password);
        } else {
            AppData.savePassword(LoginActivity.this, null);
        }
    }

    /**
     * 如果app中存储有账号和密码，则初始化输入框
     */
    private void initData(){
        if(AppData.getID(LoginActivity.this) != null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    userNameEditText.setText(AppData.getID(LoginActivity.this));
                }
            });
        }

        if(AppData.getPassword(LoginActivity.this) != null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    passwordEditText.setText(AppData.getPassword(LoginActivity.this));
                }
            });
        }
    }
}
