package com.lee.nju_gpa_calculator.utils;

import android.content.Context;
import android.content.Intent;

import com.lee.nju_gpa_calculator.activity.GPAActivity;
import com.lee.nju_gpa_calculator.activity.InfoActivity;
import com.lee.nju_gpa_calculator.activity.LoginActivity;
import com.lee.nju_gpa_calculator.model.Course;
import com.lee.nju_gpa_calculator.model.Student;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 * Created by Guo on 2017/1/18.
 *
 * 以Jsoup为基础，封装为专门对南大教务网上学生信息和成绩信息进行抓取的工具类
 */
public class JsoupUtil {
    /**
     * 通过解析response获取登录状态
     * 如果登录成功则设置GPAActivity中的数据
     */
    public boolean getLoginState(byte[] response) {

        String html = new String(response);

        if (html != null) {
            Document document = Jsoup.parse(html);
            Element userInfo = document.getElementById("UserInfo");

            //通过寻找一个登录界面没有，但是教务网主页界面有的元素ID的结果，来判断是登录成功
            if (userInfo != null) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    /**
     * 通过解析response数据获取学生信息
     * 以下结构定位都是通过Chrome浏览器自带的开发者工具观察而得
     */
    public void updateInfo(final Context context, byte[] response) {
        String html = new String(response);

        if (html != null) {
            Document document = Jsoup.parse(html);
            Element userInfo = document.getElementById("d11");
            if (userInfo != null) {

                Elements elements = userInfo.getElementsByTag("td");

                Student student = new Student();
                student.setID(elements.get(2).html());
                student.setName(elements.get(4).html());
                student.setSex(elements.get(6).html());
                student.setDepartment(elements.get(12).html() + " " + elements.get(14).html());
                student.setStartGrade(elements.get(18).html().trim());
                student.setBelongGrade(elements.get(20).html().trim());

                InfoActivity.setStudentInfo(student);

                context.startActivity(new Intent(context, InfoActivity.class));
                LoginActivity loginActivity = (LoginActivity) context;
                loginActivity.finish();
            }
        }
    }

    public void updateScore(byte[] response) {
        String html = new String(response);

        if (html != null) {
            Document document = Jsoup.parse(html);
            //从网页中提取课程表格，再从课程表格中提取元素，每9个元素为一组
            //依次为序号 课程编号 课程名称 英文名称 课程类型 学分 总评 备注 交换成绩对应课程
            Elements courseProperties = document.select(".TABLE_BODY").first().getElementsByTag("td");
            int total = (courseProperties.size() + 1) / 9;
            for(int i = 0; i < total; i ++) {
                int location = i * 9;

                Course course = new Course();
                course.setSubject( courseProperties.get( location + 2).html().trim() );
                course.setCategory( courseProperties.get(location + 4).html().trim() );
                course.setCredit( courseProperties.get(location + 5).html().trim() );

                //如果该课程有分数，则抓下来的分数数据为 <xxxxx> score <>格式，需要进一步提取信息，如果为无成绩，则为直接可用的字符串
                String finalSocre = courseProperties.get(location + 6).html().trim();
                if(finalSocre.contains(">")){
                    finalSocre = finalSocre.split(">")[1].split("<")[0];
                }
                course.setFinalScore( finalSocre.trim() );

                GPAActivity.addCourse(course);
            }
        }
    }

}
