package com.lee.nju_gpa_calculator.adapter.model;

/**
 * Created by Guo on 2017/1/18.
 *
 * 保存学生个人信息的实体类
 */
public class Student {
    private String name;
    private String ID;
    private String sex;
    private String department;
    private String startGrade;
    private String belongGrade;

    public Student(){
        name = "";
        ID = "";
        sex = "";
        department = "";
        startGrade = "";
        belongGrade = "";
    }

    /**
     * 所有属性的setter和getter方法
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStartGrade() {
        return startGrade;
    }

    public void setStartGrade(String startGrade) {
        this.startGrade = startGrade;
    }

    public String getBelongGrade() {
        return belongGrade;
    }

    public void setBelongGrade(String belongGrade) {
        this.belongGrade = belongGrade;
    }
}
