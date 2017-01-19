package com.lee.nju_gpa_calculator.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lee.nju_gpa_calculator.R;
import com.lee.nju_gpa_calculator.model.Student;
import com.lee.nju_gpa_calculator.utils.CookieJarImpl;
import com.lee.nju_gpa_calculator.utils.JsoupUtil;

import org.jsoup.Jsoup;

import java.util.ArrayList;


/**
 * Created by Guo on 2017/1/15.
 *
 * 登录后个人信息界面的逻辑层
 */
public class InfoActivity extends AppCompatActivity{

    //保存已经登录学生的个人信息
    private static Student studentInfo;

    private TextView nameText;
    private TextView studentIDText;
    private TextView sexText;
    private TextView departmentText;
    private TextView startGradeText;
    private TextView belongGradeText;

    private Button logoutButton;
    private Button returnButton;

    private Handler handler;

    public static Student getStudentInfo() {
        return studentInfo;
    }

    public static void setStudentInfo(Student studentInfo) {
        InfoActivity.studentInfo = studentInfo;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("LG", "已经被创建");

        handler = new Handler();

        setContentView(R.layout.activity_info);
        initView();
    }

    private void initView(){
        nameText = (TextView) findViewById(R.id.tv_name_f);
        studentIDText = (TextView) findViewById(R.id.tv_id_f);
        sexText = (TextView) findViewById(R.id.tv_sex_f);
        departmentText = (TextView) findViewById(R.id.tv_department_f);
        startGradeText = (TextView) findViewById(R.id.tv_startGrade_f);
        belongGradeText = (TextView) findViewById(R.id.tv_belongGrade_f);

        handler.post(new Runnable() {
            @Override
            public void run() {
                nameText.setText(studentInfo.getName());
                studentIDText.setText(studentInfo.getID());
                sexText.setText(studentInfo.getSex());
                departmentText.setText(studentInfo.getDepartment());
                startGradeText.setText(studentInfo.getStartGrade());
                belongGradeText.setText(studentInfo.getBelongGrade());
            }
        });

        logoutButton = (Button) findViewById(R.id.btn_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //必须将其中静态的cookie清空，否则影响到下一次信息的获取
                CookieJarImpl.clearCookies();
                GPAActivity.clearList();
                MainActivity.setLoginState(false);
                finish();
            }
        });

        returnButton = (Button) findViewById(R.id.btn_return);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
