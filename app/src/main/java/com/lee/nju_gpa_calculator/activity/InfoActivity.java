package com.lee.nju_gpa_calculator.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lee.nju_gpa_calculator.R;
import com.lee.nju_gpa_calculator.utils.JsoupUtil;

import org.jsoup.Jsoup;

import java.util.ArrayList;


/**
 * Created by Guo on 2017/1/15.
 *
 * 登录后个人信息界面的逻辑层
 */
public class InfoActivity extends AppCompatActivity{

    private TextView nameText;
    private TextView studentIDText;
    private Button logoutButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_info);
        initView();
    }

    private void initView(){
        nameText = (TextView) findViewById(R.id.tv_name_f);
        nameText.setText(GPAActivity.getUserName());
        studentIDText = (TextView) findViewById(R.id.tv_id_f);
        studentIDText.setText(GPAActivity.getUserID());

        logoutButton = (Button) findViewById(R.id.btn_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.setLoginState(false);
                finish();
            }
        });
    }

}
