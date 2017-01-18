package com.lee.nju_gpa_calculator.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lee.nju_gpa_calculator.model.Student;

/**
 * Created by Guo on 2017/1/15.
 *
 * 分数界面的逻辑层
 */
public class GPAActivity extends AppCompatActivity{
    private static String userName;
    private static String userID;
    private static Student studentInfo;

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        GPAActivity.userName = userName;
    }

    public static String getUserID() {
        return userID;
    }

    public static void setUserID(String userID) {
        GPAActivity.userID = userID;
    }

    public static Student getStudentInfo() {
        return studentInfo;
    }

    public static void setStudentInfo(Student studentInfo) {
        GPAActivity.studentInfo = studentInfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
