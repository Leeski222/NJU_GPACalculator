package com.lee.nju_gpa_calculator.view.gpa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lee.nju_gpa_calculator.R;
import com.lee.nju_gpa_calculator.contract.GPAContract;
import com.lee.nju_gpa_calculator.model.vopo.AchievementsVO;
import com.lee.nju_gpa_calculator.presenter.gpa.GPAPresenter;

import butterknife.ButterKnife;

/**
 * Created by 果宝 on 2018/2/18.
 */

public class GPAActivity extends AppCompatActivity implements GPAContract.View {

    private static final String ARG_GRADE = "grade";

    private String grade;

    private GPAContract.Presenter gpaPresenter;

    public static void activityStart(Activity activity, String grade) {
        Intent intent = new Intent(activity, GPAActivity.class);
        intent.putExtra(ARG_GRADE, grade);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_gpa);

        grade = getIntent().getStringExtra(ARG_GRADE);
        gpaPresenter = new GPAPresenter(this);
        gpaPresenter.start();

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
