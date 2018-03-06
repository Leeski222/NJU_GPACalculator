package com.lee.nju_gpa_calculator.view.gpa;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mobstat.StatService;
import com.lee.nju_gpa_calculator.R;
import com.lee.nju_gpa_calculator.contract.GPAContract;
import com.lee.nju_gpa_calculator.model.vopo.TermVO;
import com.lee.nju_gpa_calculator.presenter.GPAPresenter;
import com.lee.nju_gpa_calculator.utils.GPACounter;
import com.lee.nju_gpa_calculator.view.gpa.adapter.GradeExpandableListAdapter;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhanghai.android.materialprogressbar.IndeterminateProgressDrawable;

/**
 * Created by 果宝 on 2018/2/18.
 */

public class GPAActivity extends AppCompatActivity implements GPAContract.View {

    private static final String ARG_GRADE = "grade";

    private String grade;

    private boolean isExiting = false;
    private boolean isFirstLoading = true;

    private GPAContract.Presenter gpaPresenter;

    @BindView(R.id.gpa_progress_bar)
    ProgressBar mProgressBar;

    @BindView(R.id.gpa_exlv_grade)
    ExpandableListView gradeExpandableListView;

    @BindView(R.id.gpa_tv_training)
    TextView trainingText;
    @BindView(R.id.gpa_tv_platform)
    TextView platformText;
    @BindView(R.id.gpa_tv_core)
    TextView coreText;
    @BindView(R.id.gpa_tv_optional)
    TextView optionalText;
    @BindView(R.id.gpa_tv_general)
    TextView generalText;

    @BindView(R.id.gpa_btn_calculate)
    Button calculateButton;
    @BindView(R.id.gpa_tv_select)
    TextView selectInfoTextView;
    @BindView(R.id.gpa_tv_grade_point)
    TextView gpaTextView;

    @OnClick(R.id.gpa_btn_calculate)
    public void calculate() {
        GPACounter mGPACounter = new GPACounter(GradeExpandableListAdapter.getSelectGrades());
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
        StatService.start(this);
        ButterKnife.bind(this);

        mProgressBar.setIndeterminateDrawable(new IndeterminateProgressDrawable(this));
        grade = getIntent().getStringExtra(ARG_GRADE);
        gpaPresenter = new GPAPresenter(this);
        gpaPresenter.start();

        this.initTextBackground();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GradeExpandableListAdapter.clearSelectGrades();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_gpa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                gpaPresenter.start();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        exitBy2Click();
    }

    /**
     * TimerTask倒计时实现exit by 2 clicks
     */
    private void exitBy2Click() {
        Timer tExit;
        Toast exitToast = Toast.makeText(this, "再按一次退出登录", Toast.LENGTH_SHORT);
        if (!isExiting) {
            isExiting = true; // 准备退出
            exitToast.show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExiting = false; // 取消退出
                }
            }, 1500); // 如果1.5秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            exitToast.cancel();
            super.onBackPressed();
        }
    }

    @Override
    public void setPresenter(GPAContract.Presenter presenter) {
        this.gpaPresenter = presenter;
    }

    @Override
    public void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setAchievementInfo(List<TermVO> termVOs) {
        if( !isFirstLoading ) {
            Toast.makeText(this, "刷新成功", Toast.LENGTH_SHORT).show();
        }
        isFirstLoading = false;
        gradeExpandableListView.setAdapter(new GradeExpandableListAdapter(termVOs, gradeExpandableListView, GPAActivity.this));
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
