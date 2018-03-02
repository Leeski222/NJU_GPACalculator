package com.lee.nju_gpa_calculator.model.vopo;

import java.util.List;

/**
 * Created by 果宝 on 2018/2/27.
 */

public class TermVO {

    private String termName;

    private List<GradeVO> gradeVOList;

    public TermVO(String termName, List<GradeVO> gradeVOList) {
        this.termName = termName;
        this.gradeVOList = gradeVOList;
    }

    public String getTermName() {
        return termName;
    }

    public int getCourseNumber() {
        return gradeVOList.size();
    }

    public int getCreditSum() {
        int sum = 0;
        for(GradeVO gradeVO : gradeVOList) {
            String credit = gradeVO.getCredit();
            if(!credit.equals("无")) {
                sum = sum + (int) Float.parseFloat(credit);
            }
        }
        return sum;
    }

    public List<GradeVO> getCourseList() {
        return gradeVOList;
    }

}
