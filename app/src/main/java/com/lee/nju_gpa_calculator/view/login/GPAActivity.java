package com.lee.nju_gpa_calculator.view.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lee.nju_gpa_calculator.R;
import com.lee.nju_gpa_calculator.contract.GPAContract;
import com.lee.nju_gpa_calculator.model.vopo.AchievementsVO;
import com.lee.nju_gpa_calculator.presenter.GPAPresenter;
import com.lee.nju_gpa_calculator.utils.LoginResult;

import butterknife.ButterKnife;

/**
 * Created by 果宝 on 2018/2/18.
 */

public class GPAActivity extends AppCompatActivity implements GPAContract.View {

    private GPAContract.Presenter gpaPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_gpa);
        ButterKnife.bind(this);
    }

    @Override
    public void setPresenter(GPAContract.Presenter presenter) {
        this.gpaPresenter = presenter;
    }

    @Override
    public void setAchievementInfo(AchievementsVO achievementsVO) {

    }

    @Override
    public void getAchievementsFailed() {

    }

}
