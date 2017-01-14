package com.lee.nju_gpa_calculator.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lee.nju_gpa_calculator.R;

/**
 * 整个App程序的入口
 */
public class MainActivity extends AppCompatActivity {

    private Button GPAButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GPAButton = (Button) findViewById(R.id.gpa_button);
        GPAButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //根据登录状态的不同跳转至不同的Activity
                if(LoginActivity.isLogin() == true){
                    Intent intent = new Intent(MainActivity.this,GPAActivity.class);
                    MainActivity.this.startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    MainActivity.this.startActivity(intent);
                }
            }
        });
    }
}
