package com.lee.nju_gpa_calculator.model.vopo;

import com.lee.nju_gpa_calculator.utils.CourseType;

/**
 * Created by Guo on 2017/1/15.
 *
 * 保存单门学科成绩等相关属性的实体类
 *
 */
public class CourseVO {

    private String subject;         // 课程中文名
    private String EnglishName;     // 课程英文名
    private String category;        // 类别
    private String credit;          // 学分
    private String finalScore;      // 总评

    private CourseType type;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEnglishName() {
        return EnglishName;
    }

    public void setEnglishName(String englishName) {
        EnglishName = englishName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;

        if(category.equals("通修")) {
            type = CourseType.Training;
        } else if(category.equals("平台")) {
            type = CourseType.PlatForm;
        } else if(category.equals("核心")) {
            type = CourseType.Core;
        } else if(category.equals("选修")) {
            type = CourseType.Optional;
        } else if(category.equals("通识")) {
            type = CourseType.General;
        } else if(category.equals("公共")) {
            type = CourseType.Public;
        }
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

    private CourseType getType() {
        return type;
    }
}
