package com.lee.nju_gpa_calculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.lee.nju_gpa_calculator.R;

/**
 * 整个App程序的入口
 */
public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private Button GPAButton;

    private static boolean loginState;

    /**
     * 获取登录状态的方法
     */
    public static boolean getLoginState() {
        return loginState;
    }

    /**
     * 设置登录状态的方法
     */
    public static void setLoginState(boolean state){
        loginState = state;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.btn_confirm);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //根据登录状态的不同跳转至不同的Activity
                if(loginState == true){
                    Intent intent = new Intent(MainActivity.this, InfoActivity.class);
                    MainActivity.this.startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    MainActivity.this.startActivity(intent);
                }
            }
        });

        GPAButton = (Button) findViewById(R.id.btn_gpa);
        GPAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //如果已登录则直接显示成绩列表，如果未登录则提示需要登录
                if(loginState == true){
                    Intent intent = new Intent(MainActivity.this, GPAActivity.class);
                    MainActivity.this.startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "请先登录!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
