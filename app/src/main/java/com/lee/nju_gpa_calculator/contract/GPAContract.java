package com.lee.nju_gpa_calculator.contract;

import com.lee.nju_gpa_calculator.model.vopo.AchievementsVO;
import com.lee.nju_gpa_calculator.presenter.BasePresenter;
import com.lee.nju_gpa_calculator.view.BaseView;

/**
 * Created by 果宝 on 2018/2/18.
 */

public interface GPAContract {

    interface View extends BaseView<GPAContract.Presenter> {
        void setAchievementInfo(AchievementsVO achievementsVO);

        void getAchievementsFailed();
    }

    interface Presenter extends BasePresenter {
        void getAchievementList();

        void getAchievementInfo();
    }

}
