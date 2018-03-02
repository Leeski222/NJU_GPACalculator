package com.lee.nju_gpa_calculator.utils;

import com.lee.nju_gpa_calculator.model.vopo.GradeVO;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by 果宝 on 2018/3/2.
 */

public class GPACounter {

    private int courseNum = 0;
    private double sumScore = 0.0;
    private double sumCredit = 0.0;
    private String gpa = "0.0";

    private List<GradeVO> gradeVOs;

    public GPACounter(List<GradeVO> gradeVOs) {
        this.gradeVOs = gradeVOs;
        this.calculate();
    }

    private void calculate() {
        for(GradeVO gradeVO : gradeVOs) {
            try {
                courseNum ++;
                if(!gradeVO.getCredit().equals("无")) {
                    sumCredit += sumCredit + Double.parseDouble(gradeVO.getCredit());
                    sumScore += sumScore * Double.parseDouble(gradeVO.getFinalScore());
                }
            } catch (Exception e) {
                gpa = "Error";
                break;
            }
        }

        if (sumCredit != 0) {
            DecimalFormat format = new DecimalFormat("0.000");
            gpa = format.format( (sumScore * 1.0) / (sumCredit * 20) );
        } else {
            gpa = "0.000";
        }
    }

    public void clear(){
        //退出当前界面时将缓存的分数数据清空
        sumScore = 0;
        sumCredit = 0;
    }

    public String getCourseNum() {
        return courseNum + "";
    }

    public String getSumCredit() {
        return sumCredit + "";
    }

    public String getGPA() {
        return gpa;
    }

}
