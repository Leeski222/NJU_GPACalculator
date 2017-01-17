package com.lee.nju_gpa_calculator.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Guo on 2017/1/14.
 *
 * 用于存储和读取App数据
 */
public class AppData {
    /**
     * 保存账号
     */
    public static void saveID(Context context, String ID) {
        SharedPreferences share = context.getSharedPreferences(Constants.APP_DATA, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString(Constants.SAVE_ID, ID);
        editor.commit();
    }

    /**
     * 获取账号
     */
    public static String getID(Context context) {
        SharedPreferences share = context.getSharedPreferences(Constants.APP_DATA, context.MODE_PRIVATE);
        return share.getString(Constants.SAVE_ID, null);
    }

    /**
     * 保存密码
     */
    public static void savePassword(Context context, String password) {
        SharedPreferences share = context.getSharedPreferences(Constants.APP_DATA, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString(Constants.SAVE_PASSWORD, password);
        editor.commit();
    }

    /**
     * 获取密码
     */
    public static String getPassword(Context context) {
        SharedPreferences share = context.getSharedPreferences(Constants.APP_DATA, context.MODE_PRIVATE);
        return share.getString(Constants.SAVE_PASSWORD, null);
    }
}
