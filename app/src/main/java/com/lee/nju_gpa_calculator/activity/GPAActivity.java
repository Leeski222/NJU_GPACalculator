package com.lee.nju_gpa_calculator.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.lee.nju_gpa_calculator.R;
import com.lee.nju_gpa_calculator.model.Course;

import java.util.ArrayList;

/**
 * Created by Guo on 2017/1/15.
 *
 * 分数界面的逻辑层
 */
public class GPAActivity extends AppCompatActivity{

    private static ArrayList<Course> courseList = new ArrayList();

    private Button testButton;

    public static void addCourse(Course course){
        courseList.add(course);
    }

    public static void clearList(){
        courseList.clear();
        Log.e("LG", "list清空");
    }

    public void print(){
        for(Course course : courseList){
            Log.e("LG", course.getSubject());
            Log.e("LG", course.getCategory());
            Log.e("LG", course.getCredit());
            Log.e("LG", course.getFinalScore());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gpa);

        testButton = (Button) findViewById(R.id.tv_test);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                print();
            }
        });
    }

}
