package com.lee.nju_gpa_calculator.model.vopo;

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

    public List<CourseVO> getCourseList() {
        return this.courseVOList;
    }
}
