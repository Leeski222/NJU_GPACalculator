package com.lee.nju_gpa_calculator.contract;

import com.lee.nju_gpa_calculator.model.vopo.TermVO;
import com.lee.nju_gpa_calculator.presenter.BasePresenter;
import com.lee.nju_gpa_calculator.view.BaseView;

import java.util.List;

/**
 * Created by 果宝 on 2018/2/18.
 */

public interface GPAContract {

    interface View extends BaseView<GPAContract.Presenter> {
        void setAchievementInfo(List<TermVO> termVOs);

        void getAchievementsFailed();
    }

    interface Presenter extends BasePresenter {
        void getAchievementList();

        void getAchievementInfo();
    }

}
