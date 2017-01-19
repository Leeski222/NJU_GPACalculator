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
     * 记录是否存储账号
     */
    public static void isSaveID(Context context, boolean isSaveID){
        SharedPreferences share = context.getSharedPreferences(Constants.APP_DATA, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putBoolean(Constants.IS_SAVE_ID, isSaveID);
        editor.commit();
    }

    /**
     * 返回账号存储状态
     */
    public static boolean getSaveIDState(Context context) {
        SharedPreferences share = context.getSharedPreferences(Constants.APP_DATA, context.MODE_PRIVATE);
        return share.getBoolean(Constants.IS_SAVE_ID, false);
    }

    /**
     * 记录是否存储密码
     */
    public static void isSavePassword(Context context, boolean isSavePassword){
        SharedPreferences share = context.getSharedPreferences(Constants.APP_DATA, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        editor.putBoolean(Constants.IS_SAVE_PASSWORD, isSavePassword);
        editor.commit();
    }

    /**
     * 返回密码存储状态
     */
    public static boolean getSavePasswordState(Context context) {
        SharedPreferences share = context.getSharedPreferences(Constants.APP_DATA, context.MODE_PRIVATE);
        return share.getBoolean(Constants.IS_SAVE_PASSWORD, false);
    }

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
