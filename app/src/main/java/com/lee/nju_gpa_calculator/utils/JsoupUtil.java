package com.lee.nju_gpa_calculator.utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.lee.nju_gpa_calculator.activity.GPAActivity;
import com.lee.nju_gpa_calculator.activity.InfoActivity;
import com.lee.nju_gpa_calculator.activity.LoginActivity;
import com.lee.nju_gpa_calculator.model.Student;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Iterator;


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
    public boolean getLoginStateByParseResponse(byte[] response) {

        String html = new String(response);

        if(html != null) {
            Document document = Jsoup.parse(html);
            Element userInfo = document.getElementById("UserInfo");

            if (userInfo != null) {
                //userInfo的元素格式为 “欢迎您：XX&nbsp;&nbsp;&nbsp;&nbsp;当前身份：学生”
                //所以以“：”和“&”通过两次split直接获取学生姓名
                GPAActivity.setUserName(userInfo.html().split("：")[1].split("&")[0]);
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    /**
     * 以下结构都是通过Chrome浏览器自带的开发者工具观察而得
     */
    public void updateInfoByParseResponse(final Context context, byte[] response) {
        String html = new String(response);

        if(html != null) {
            Log.e("LG", "获取信息html");
            Document document = Jsoup.parse(html);
            Element userInfo = document.getElementById("d11");
            if (userInfo != null) {

                Elements elements = userInfo.getElementsByTag("td");

                Student student = new Student();
                student.setID( elements.get(2).html() );
                student.setName( elements.get(4).html() );
                student.setSex( elements.get(6).html() );
                student.setDepartment( elements.get(12).html() + " " + elements.get(14).html() );
                student.setStartGrade( elements.get(18). html().trim() );
                student.setBelongGrade( elements.get(20).html().trim() );

                InfoActivity.setStudentInfo(student);

                context.startActivity(new Intent(context, InfoActivity.class));
                LoginActivity loginActivity = (LoginActivity) context;
                loginActivity.finish();
                Log.e("LG", "已经更新student个人信息");
            }
        }
    }
}
