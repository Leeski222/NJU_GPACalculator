package com.lee.nju_gpa_calculator.view;

/**
 * Created by 果宝 on 2018/2/8.
 */

public interface BaseView<T> {

    /**
     * 设置view的presenter
     *
     * @param presenter
     */
    void setPresenter(T presenter);

}
