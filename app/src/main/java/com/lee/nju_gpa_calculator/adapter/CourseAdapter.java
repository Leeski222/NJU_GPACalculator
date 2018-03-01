package com.lee.nju_gpa_calculator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.lee.nju_gpa_calculator.R;
import com.lee.nju_gpa_calculator.activity.GPAActivity;
import com.lee.nju_gpa_calculator.adapter.model.Course;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Guo on 2017/1/19.
 *
 * ListView的适配器
 */
public class CourseAdapter extends BaseAdapter {
    private static HashMap<String, Boolean> selectSubject;     //记录是否选中该复选框
    private ArrayList<Course> courseList;               //数据源
    private LayoutInflater layoutInflater;              //布局装载器对象

    public CourseAdapter(Context context, ArrayList<Course> list){
        selectSubject = new HashMap<>();
        courseList = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    //指定的索引对应的数据项
    public Object getItem(int position) {
        return courseList.get(position);
    }

    @Override
    //指定的索引对应的数据项ID
    public long getItemId(int position) {
        return position;
    }

    @Override
    //返回每一项的显示内容
    public View getView(int position, View convertView, ViewGroup parent) {
        //将布局文件转化为View对象
        View view = layoutInflater.inflate(R.layout.item_course, null);

//        //找到item布局文件中对应的控件
//        CheckBox selectCB = (CheckBox) view.findViewById(R.id.cb_selected);
//        TextView subjectTV = (TextView) view.findViewById(R.id.tv_subject);
//        TextView categoryTV = (TextView) view.findViewById(R.id.tv_category);
//        TextView creditTV = (TextView) view.findViewById(R.id.tv_credit);
//        TextView finalScoreTV = (TextView) view.findViewById(R.id.tv_final_score);
//
//        //获取相应索引的ItemBean对象
//        final Course course = courseList.get(position);
//
//        //设置控件的对应属性值
//        subjectTV.setText(course.getSubject());
//        categoryTV.setText(course.getCategory());
//        creditTV.setText(" 学分: " + course.getCredit());
//        finalScoreTV.setText("总评: " + course.getFinalScore());
//
//        if(course.getCredit().equals("") || course.getFinalScore().equals("无成绩")){
//            selectCB.setClickable(false);
//        }
//
//        Boolean isCheck = selectSubject.get(course.getSubject());
//        if(isCheck != null && isCheck.equals(Boolean.TRUE)) {
//            selectCB.setChecked(true);
//        }
//
//        selectCB.setFocusable(false);
//        this.setCBListener(selectCB, course);
//
        return view;
    }

    private void setCBListener(CheckBox selectCB, final Course course){

        selectCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    selectSubject.put(course.getSubject(), Boolean.TRUE);
                    double credit = 0;
                    try {
                        credit = Double.parseDouble(course.getCredit());
                    } catch (Exception e) {
                        GPAActivity.addError();
                    }

                    double finalScore = 0;
                    try {
                        finalScore = Double.parseDouble(course.getFinalScore());
                    } catch (Exception e) {
                        GPAActivity.addError();
                    }

                    GPAActivity.addSumCredit(credit);
                    GPAActivity.addSumScore(finalScore * credit);
                } else {
                    selectSubject.put(course.getSubject(), Boolean.FALSE);
                    double credit = 0;
                    try {
                        credit = Double.parseDouble(course.getCredit());
                    } catch (Exception e) {
                        GPAActivity.subError();
                    }

                    double finalScore = 0;
                    try {
                        finalScore = Double.parseDouble(course.getFinalScore());
                    } catch (Exception e) {
                        GPAActivity.subError();
                    }

                    GPAActivity.subSumCredit(credit);
                    GPAActivity.subSumScore(finalScore * credit);
                }
            }
        });
    }
}
