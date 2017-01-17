package com.lee.nju_gpa_calculator.utils;

import com.lee.nju_gpa_calculator.activity.GPAActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


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
}
