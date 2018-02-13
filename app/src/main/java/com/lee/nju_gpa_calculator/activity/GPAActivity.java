package com.lee.nju_gpa_calculator.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lee.nju_gpa_calculator.R;
import com.lee.nju_gpa_calculator.adapter.CourseAdapter;
import com.lee.nju_gpa_calculator.adapter.model.Course;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Guo on 2017/1/15.
 *
 * 分数界面的逻辑层
 */
public class GPAActivity extends AppCompatActivity{

    private static double sumScore = 0;
    private static double sumCredit = 0;
    private static int errorNumber = 0;     //每勾选一个错误数据，则增加一个错误数，每减少一个错误数据，则减少一个错误数
    private static ArrayList<Course> courseList = new ArrayList();

    private ListView courseListView;
    private Button calculateButton;
    private TextView resultArea;

    private Handler handler;

    public static void addCourse(Course course){
        courseList.add(course);
    }

    public static void addSumScore(double number){
        sumScore = sumScore + number;
    }

    public static void subSumScore(double number){
        sumScore = sumScore - number;
    }

    public static void addSumCredit(double number){
        sumCredit = sumCredit + number;
    }

    public static void subSumCredit(double number){
        sumCredit = sumCredit - number;
    }

    public static void addError(){
        errorNumber ++;
    }

    public static void subError(){
        errorNumber --;
    }

    public static void clear(){
        courseList.clear();
        sumCredit = 0;
        sumScore = 0;
        errorNumber = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa);

        handler = new Handler();

        resultArea = (TextView) findViewById(R.id.tv_gpa_area);

        courseListView = (ListView) findViewById(R.id.lv_course);
        courseListView.setAdapter(new CourseAdapter(this, courseList));

        calculateButton = (Button) findViewById(R.id.bt_calculate);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (errorNumber != 0) {
                            resultArea.setText("抱歉，程序出错啦T^T;");
                        } else {
                            if (sumCredit != 0) {
                                DecimalFormat format = new DecimalFormat("0.0000");
                                String result = format.format( (sumScore * 1.0) / (sumCredit * 20) );
                                resultArea.setText(result);
                            } else {
                                resultArea.setText("0.0000");
                            }
                        }
                    }
               });

            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //退出当前界面时将缓存的分数数据清空
        sumScore = 0;
        sumCredit = 0;
        errorNumber = 0;
    }
}
