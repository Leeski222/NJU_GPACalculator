package com.lee.nju_gpa_calculator.view.gpa;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lee.nju_gpa_calculator.R;
import com.lee.nju_gpa_calculator.contract.GPAContract;
import com.lee.nju_gpa_calculator.model.vopo.TermVO;
import com.lee.nju_gpa_calculator.presenter.GPAPresenter;
import com.lee.nju_gpa_calculator.utils.GPACounter;
import com.lee.nju_gpa_calculator.view.gpa.adapter.GradeExpandableListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 果宝 on 2018/2/18.
 */

public class GPAActivity extends AppCompatActivity implements GPAContract.View {

    private static final String ARG_GRADE = "grade";

    private String grade;

    private GPAContract.Presenter gpaPresenter;

    @BindView(R.id.gpa_exlv_grade)
    ExpandableListView gradeExpandableListView;

    @BindView(R.id.gpa_tv_training)
    TextView trainingText;
    private boolean isSelectTraining;

    @OnClick(R.id.gpa_tv_training)
    public void selectTraining() {
        if(isSelectTraining) {
            trainingText.setBackground(getResources().getDrawable(R.drawable.tv_round_select));
        } else {
            trainingText.setBackground(getResources().getDrawable(R.drawable.tv_round));
        }
        this.initTextBackground();
        isSelectTraining = !isSelectTraining;
    }

    @BindView(R.id.gpa_tv_platform)
    TextView platformText;
    private boolean isSelectPlatform;

    @OnClick(R.id.gpa_tv_platform)
    public void selectPlatform() {
        if(isSelectPlatform) {
            platformText.setBackground(getResources().getDrawable(R.drawable.tv_round_select));
        } else {
            platformText.setBackground(getResources().getDrawable(R.drawable.tv_round));
        }
        this.initTextBackground();
        isSelectPlatform = !isSelectPlatform;
    }

    @BindView(R.id.gpa_tv_core)
    TextView coreText;
    private boolean isSelectCore;

    @OnClick(R.id.gpa_tv_core)
    public void selectCore() {
        if(isSelectCore) {
            coreText.setBackground(getResources().getDrawable(R.drawable.tv_round_select));
        } else {
            coreText.setBackground(getResources().getDrawable(R.drawable.tv_round));
        }
        this.initTextBackground();
        isSelectCore = !isSelectCore;
    }

    @BindView(R.id.gpa_tv_optional)
    TextView optionalText;
    private boolean isSelectOptional;

    @OnClick(R.id.gpa_tv_optional)
    public void selectOptional() {
        if(isSelectOptional) {
            optionalText.setBackground(getResources().getDrawable(R.drawable.tv_round_select));
        } else {
            optionalText.setBackground(getResources().getDrawable(R.drawable.tv_round));
        }
        this.initTextBackground();
        isSelectOptional = !isSelectOptional;
    }

    @BindView(R.id.gpa_tv_general)
    TextView generalText;
    private boolean isSelectGeneral;

    @OnClick(R.id.gpa_tv_general)
    public void selectGeneral() {
        if(isSelectGeneral) {
            generalText.setBackground(getResources().getDrawable(R.drawable.tv_round_select));
        } else {
            generalText.setBackground(getResources().getDrawable(R.drawable.tv_round));
        }
        this.initTextBackground();
        isSelectGeneral = !isSelectGeneral;
    }

    @BindView(R.id.gpa_btn_calculate)
    Button calculateButton;
    @BindView(R.id.gpa_tv_select)
    TextView selectInfoTextView;
    @BindView(R.id.gpa_tv_grade_point)
    TextView gpaTextView;

    @OnClick(R.id.gpa_btn_calculate)
    public void calculate() {
        GPACounter mGPACounter = new GPACounter(GradeExpandableListAdapter.selectGrades);
        final String courseNum = mGPACounter.getCourseNum() + "";
        final String sumCredit = mGPACounter.getSumCredit() + "";
        final String gpa = mGPACounter.getGPA();
        selectInfoTextView.setText("共" + courseNum +"门课程，" + sumCredit + "个学分");
        gpaTextView.setText(gpa);
    }

    public static void activityStart(Activity activity, String grade) {
        Intent intent = new Intent(activity, GPAActivity.class);
        intent.putExtra(ARG_GRADE, grade);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_gpa);
        ButterKnife.bind(this);

        grade = getIntent().getStringExtra(ARG_GRADE);
        gpaPresenter = new GPAPresenter(this);
        gpaPresenter.start();

        this.initTextBackground();
    }

    @Override
    public void setPresenter(GPAContract.Presenter presenter) {
        this.gpaPresenter = presenter;
    }

    @Override
    public void setAchievementInfo(List<TermVO> termVOs) {
        gradeExpandableListView.setAdapter(new GradeExpandableListAdapter(termVOs, GPAActivity.this));
    }

    @Override
    public void getAchievementsFailed() {
        Toast.makeText(this, "获取成绩失败, 请重试！", Toast.LENGTH_SHORT).show();
    }

    public void initTextBackground() {
        final GradientDrawable trainingTextBackground = (GradientDrawable) trainingText.getBackground();
        trainingTextBackground.setColor(getResources().getColor(R.color.colorRed));

        final GradientDrawable platformTextBackground = (GradientDrawable) platformText.getBackground();
        platformTextBackground.setColor(getResources().getColor(R.color.colorOrange));

        final GradientDrawable coreTextBackground = (GradientDrawable) coreText.getBackground();
        coreTextBackground.setColor(getResources().getColor(R.color.colorBlue));

        final GradientDrawable optionalTextBackground = (GradientDrawable) optionalText.getBackground();
        optionalTextBackground.setColor(getResources().getColor(R.color.colorGreen));

        final GradientDrawable generalTextBackground = (GradientDrawable) generalText.getBackground();
        generalTextBackground.setColor(getResources().getColor(R.color.colorPurple));
    }
}
