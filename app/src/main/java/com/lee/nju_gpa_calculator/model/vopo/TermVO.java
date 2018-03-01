package com.lee.nju_gpa_calculator.model.vopo;

import com.lee.nju_gpa_calculator.adapter.model.Course;

import java.util.List;

/**
 * Created by 果宝 on 2018/2/27.
 */

public class TermVO {

    private String termName;

    private List<CourseVO> courseVOList;

    public TermVO(String termName, List<CourseVO> courseVOList) {
        this.termName = termName;
        this.courseVOList = courseVOList;
    }

    public String getTermName() {
        return termName;
    }

    public int getCourseNumber() {
        return courseVOList.size();
    }

    public int getCreditSum() {
        int sum = 0;
        for(CourseVO courseVO : courseVOList) {
            String credit = courseVO.getCredit();
            if(!credit.equals("无")) {
                sum = sum + Integer.parseInt(credit);
            }
        }
        return sum;
    }

    public List<CourseVO> getCourseList() {
        return courseVOList;
    }

}
