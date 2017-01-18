package com.lee.nju_gpa_calculator.utils;

/**
 * Created by Guo on 2017/1/14.
 *
 * 用来统一保存和管理其他类中需要的常量数据
 */
public class Constants {
    // 用于SharedPreferences保存程序状态文件时
    public static final String APP_DATA = "app_data";
    public static final String FIRST_INSTALL = "first_install";
    public static final String SAVE_ID = "save_id";
    public static final String SAVE_PASSWORD = "save_password";

    //教务系统登录界面网址
    public static final String EDUCATION_SYSTEM_LOGIN_URL = "http://219.219.120.48/jiaowu/login.do";

    //教务系统个人信息网址
    public static final String EDUCATION_SYSTEM_INFO_URL = "http://219.219.120.48/jiaowu/student/studentinfo/studentinfo.do?method=searchAllList";

    //查询成绩的网址,后面需要加上年级
    public static final String EDUCATION_SYSTEM_SCORE_URL = "http://219.219.120.48/jiaowu/student/studentinfo/achievementinfo.do?method=searchTermList&termCode=";

    // 请求头
    public static final String HEADER_NAME_HOST = "Host";
    public static final String HEADER_VALUE_HOST = "219.219.120.48";

    public static final String HEADER_NAME_REFERER = "Referer";
    public static final String HEADER_VALUE_REFERER = "http://219.219.120.48/jiaowu/";

    public static final String HEADER_NAME_AGENT = "User-Agent";
    public static final String HEADER_VALUE_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko";


    // 登录时的请求参数
    public static final String LOGIN_BODY_NAME_USERNAME = "userName";

    public static final String LOGIN_BODY_NAME_RETURNURL = "returnUrl";
    public static final String LOGIN_BODY_VALUE_RETURNURL = "null";

    public static final String LOGIN_BODY_NAME_PASSWORD = "password";
}
