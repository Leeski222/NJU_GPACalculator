package com.lee.nju_gpa_calculator.activity;


import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
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

        //为登录键添加监听
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName = userNameEditText.getText().toString();
                final String password = passwordEditText.getText().toString();

                saveData(userName, password);

                new OkHttpUtil().confirmLogin(LoginActivity.this, userName, password);
            }
        });
    }

    /**
     * 记录复选框的状态
     * 并根据复选框的情况并确定是否在app中保存账号和密码
     */
    private void saveData(String userName, String password){
        if(rIDCheckBox.isChecked()){
            AppData.isSaveID(LoginActivity.this, true);
            AppData.saveID(LoginActivity.this, userName);
        } else {
            AppData.isSaveID(LoginActivity.this, false);
            AppData.saveID(LoginActivity.this, null);
        }

        if(rPaCheckBox.isChecked()){
            AppData.isSavePassword(LoginActivity.this, true);
            AppData.savePassword(LoginActivity.this, password);
        } else {
            AppData.isSavePassword(LoginActivity.this, false);
            AppData.savePassword(LoginActivity.this, null);
        }
    }

    /**
     * 如果用户上次选择了保存，则这次启动时同样自动勾选复选框
     * 如果app中存储有账号和密码，则初始化输入框
     */
    private void initData(){
        if(AppData.getSaveIDState(LoginActivity.this) == true){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    rIDCheckBox.setChecked(true);
                }
            });
        }

        if(AppData.getSavePasswordState(LoginActivity.this) == true){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    rPaCheckBox.setChecked(true);
                }
            });
        }

        if(AppData.getID(LoginActivity.this) != null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    userNameEditText.setText(AppData.getID(LoginActivity.this));
                }
            });
        }

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
