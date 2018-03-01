package com.lee.nju_gpa_calculator.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Guo on 2017/1/14.
 *
 * 用于存储和读取App数据
 */
public class AppData {

    private static final String APP_DATA = "app_data";
    private static final String IS_SAVE_ID = "is_save_id";
    private static final String IS_SAVE_PASSWORD = "is_save_password";
    private static final String SAVE_ID = "save_id";
    private static final String SAVE_PASSWORD = "save_password";

    /**
     * 记录是否存储账号
     */
    public static void isSaveID(Context context, boolean isSaveID){
        SharedPreferences share = context.getSharedPreferences(APP_DATA, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putBoolean(IS_SAVE_ID, isSaveID);
        editor.apply();
    }

    /**
     * 返回账号存储状态
     */
    public static boolean getSaveIDState(Context context) {
        SharedPreferences share = context.getSharedPreferences(APP_DATA, context.MODE_PRIVATE);
        return share.getBoolean(IS_SAVE_ID, false);
    }

    /**
     * 记录是否存储密码
     */
    public static void isSavePassword(Context context, boolean isSavePassword){
        SharedPreferences share = context.getSharedPreferences(APP_DATA, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putBoolean(IS_SAVE_PASSWORD, isSavePassword);
        editor.apply();
    }

    /**
     * 返回密码存储状态
     */
    public static boolean getSavePasswordState(Context context) {
        SharedPreferences share = context.getSharedPreferences(APP_DATA, context.MODE_PRIVATE);
        return share.getBoolean(IS_SAVE_PASSWORD, false);
    }

    /**
     * 保存账号
     */
    public static void saveID(Context context, String ID) {
        SharedPreferences share = context.getSharedPreferences(APP_DATA, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString(SAVE_ID, ID);
        editor.apply();
    }

    /**
     * 获取账号
     */
    public static String getID(Context context) {
        SharedPreferences share = context.getSharedPreferences(APP_DATA, context.MODE_PRIVATE);
        return share.getString(SAVE_ID, null);
    }

    /**
     * 保存密码
     */
    public static void savePassword(Context context, String password) {
        SharedPreferences share = context.getSharedPreferences(APP_DATA, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putString(SAVE_PASSWORD, password);
        editor.apply();
    }

    /**
     * 获取密码
     */
    public static String getPassword(Context context) {
        SharedPreferences share = context.getSharedPreferences(APP_DATA, context.MODE_PRIVATE);
        return share.getString(SAVE_PASSWORD, null);
    }
}
