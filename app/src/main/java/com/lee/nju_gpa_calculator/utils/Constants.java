package com.lee.nju_gpa_calculator.utils;

/**
 * Created by Guo on 2017/1/14.
 *
 * 用来统一保存和管理其他类中需要的常量数据
 */
public class Constants {

    private Constants() {}

    // 用于SharedPreferences保存程序状态文件时
    public static final String APP_DATA = "app_data";
    public static final String IS_SAVE_ID = "is_save_id";
    public static final String IS_SAVE_PASSWORD = "is_save_password";
    public static final String SAVE_ID = "save_id";
    public static final String SAVE_PASSWORD = "save_password";

    //教务系统登录界面网址
    public static final String EDUCATION_SYSTEM_LOGIN_URL = "http://219.219.120.48/jiaowu/login.do";

    //教务系统个人信息网址
    public static final String EDUCATION_SYSTEM_INFO_URL = "http://219.219.120.48/jiaowu/student/studentinfo/studentinfo.do?method=searchAllList";

    //查询成绩的网址,后面需要加上年级
    public static final String EDUCATION_SYSTEM_SCORE_URL = "http://219.219.120.48/jiaowu/student/studentinfo/achievementinfo.do?method=searchTermList&termCode=";

    // 请求头
    public static final String HEADER_NAME_REFERER = "Referer";
    public static final String HEADER_VALUE_REFERER = "elite.nju.edu.cn/jiaowu/";

    public static final String HEADER_NAME_HOST = "Host";
    public static final String HEADER_VALUE_HOST =  "elite.nju.edu.cn";

    public static final String HEADER_NAME_CONTENT_VALUE = "Content-Type";
    public static final String HEADER_VALUE_CONTENT_VALUE = "application/x-www-form-urlencoded";

    public static final String HEADER_NAME_USER_AGENT = "User-Agent";
    public static final String HEADER_VALUE_USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko";
}
