package com.lee.nju_gpa_calculator.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Guo on 2017/1/15.
 *
 * 保存单门学科成绩等相关属性的实体类
 *
 */
public class Course{

    private String subject;         // 科目
    private String category;        // 类别
    private String finalScore;      // 总评
    private String credit;          // 学分

    /**
     * 所有属性的setter和getter方法
     */
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

}
