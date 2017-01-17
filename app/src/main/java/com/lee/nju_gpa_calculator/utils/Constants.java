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

    //查询成绩的网址
    public static final String SEARCH_SCORE_URL = "";

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


    // 查询成绩时的请求参数
    public static final String SCORE_BODY_NAME_VIEWSTATE = "__VIEWSTATE";
    public static final String SCORE_BODY_VALUE_VIEWSTATE = "dDw0MTg3MjExMDA7dDw7bDxpPDE+Oz47bDx0PDtsPGk8MT47aTwxNT47aTwxNz47aTwyMz47aTwyNT47aTwyNz47aTwyOT47aTwzMD47aTwzMj47aTwzND47aTwzNj47aTw0OD47aTw1Mj47PjtsPHQ8dDw7dDxpPDE3PjtAPFxlOzIwMDEtMjAwMjsyMDAyLTIwMDM7MjAwMy0yMDA0OzIwMDQtMjAwNTsyMDA1LTIwMDY7MjAwNi0yMDA3OzIwMDctMjAwODsyMDA4LTIwMDk7MjAwOS0yMDEwOzIwMTAtMjAxMTsyMDExLTIwMTI7MjAxMi0yMDEzOzIwMTMtMjAxNDsyMDE0LTIwMTU7MjAxNS0yMDE2OzIwMTYtMjAxNzs+O0A8XGU7MjAwMS0yMDAyOzIwMDItMjAwMzsyMDAzLTIwMDQ7MjAwNC0yMDA1OzIwMDUtMjAwNjsyMDA2LTIwMDc7MjAwNy0yMDA4OzIwMDgtMjAwOTsyMDA5LTIwMTA7MjAxMC0yMDExOzIwMTEtMjAxMjsyMDEyLTIwMTM7MjAxMy0yMDE0OzIwMTQtMjAxNTsyMDE1LTIwMTY7MjAxNi0yMDE3Oz4+Oz47Oz47dDxwPDtwPGw8b25jbGljazs+O2w8cHJldmlldygpXDs7Pj4+Ozs+O3Q8cDw7cDxsPG9uY2xpY2s7PjtsPHdpbmRvdy5jbG9zZSgpXDs7Pj4+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85a2m5Y+377yaMTIxMDAzNTMxMDE3Oz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzlp5PlkI3vvJrpmYjlhajmtIs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOWtpumZou+8mueUteWtkOS/oeaBr+WtpumZojs+Pjs+Ozs+O3Q8cDxwPGw8VGV4dDs+O2w85LiT5Lia77yaOz4+Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDzova/ku7blt6XnqIs7Pj47Pjs7Pjt0PHA8cDxsPFRleHQ7PjtsPOihjOaUv+ePre+8mui9r+S7tjEzMTE7Pj47Pjs7Pjt0PEAwPDs7Ozs7Ozs7Ozs+Ozs+O3Q8QDA8Ozs7Ozs7Ozs7Oz47Oz47dDxwPHA8bDxUZXh0Oz47bDxTSERKWFk7Pj47Pjs7Pjt0PEAwPDs7Ozs7Ozs7Ozs+Ozs+Oz4+Oz4+Oz6Ias3pbdZ5OrR6552SwC/h8yZ3eA==";
    public static final String SCORE_BODY_NAME_VIEWSTATEGENERATOR = "__VIEWSTATEGENERATOR";
    public static final String SCORE_BODY_VALUE_VIEWSTATEGENERATOR = "8963BEEC";
    public static final String SCORE_BODY_NAME_BUTTON1 = "Button1";
    public static final String SCORE_BODY_VALUE_BUTTON1 = "按学期查询";
    public static final String SCORE_BODY_NAME_SCHOOLYEAR = "ddlXN";
    public static final String SCORE_BODY_VALUE_SCHOOLYEAR = "2013-2014";
    public static final String SCORE_BODY_NAME_TERM = "ddlXQ";
    public static final String SCORE_BODY_VALUE_TERM = "1";
    public static final String SCORE_BODY_NAME_TXTQSCJ = "txtQSCJ";
    public static final String SCORE_BODY_VALUE_TXTQSCJ = "0";
    public static final String SCORE_BODY_NAME_TXTZZCJ = "txtZZCJ";
    public static final String SCORE_BODY_VALUE_TXTZZCJ = "100";
}
