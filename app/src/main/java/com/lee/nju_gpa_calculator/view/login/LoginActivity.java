package com.lee.nju_gpa_calculator.view.login;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.mobstat.StatService;
import com.lee.nju_gpa_calculator.R;
import com.lee.nju_gpa_calculator.contract.LoginContract;
import com.lee.nju_gpa_calculator.presenter.LoginPresenter;
import com.lee.nju_gpa_calculator.utils.AppData;
import com.lee.nju_gpa_calculator.utils.LoginResult;
import com.lee.nju_gpa_calculator.view.gpa.GPAActivity;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 果宝 on 2018/2/3.
 */

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private String grade;

    private Handler handler;

    private boolean isFirstLoading = true;

    private LoginContract.Presenter loginPresenter;

    @BindView(R.id.login_et_studentID)
    EditText studentIDEditText;

    @BindView(R.id.login_et_password)
    EditText passwordEditText;

    @BindView(R.id.login_iv_validate_code)
    ImageView validateCodeImageView;

    @BindView(R.id.login_et_validate_code)
    EditText validateCodeEditText;

    @BindView(R.id.login_btn_next)
    Button nextValidateButton;

    @BindView(R.id.login_btn_login)
    Button loginButton;

    @BindView(R.id.login_cb_save_id)
    CheckBox saveIDCheckBox;

    @BindView(R.id.login_cb_save_password)
    CheckBox savePasswordCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StatService.start(this);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenter(this);
        handler = new Handler();
        initData();
        loginPresenter.start();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        loginPresenter.getValidateCodeImage();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.loginPresenter = presenter;
    }

    @Override
    public void setValidateCodeImage(Bitmap bitmap) {
        validateCodeImageView.setImageBitmap(bitmap);
    }

    @Override
    public void refreshValidateCodeSuccess() {
        if( isFirstLoading ) {
            isFirstLoading = false;
        } else {
            Toast.makeText(this, "刷新成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void refreshValidateCodeFailed() {
        Toast.makeText(this, "刷新失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
        GPAActivity.activityStart(this, grade);
    }

    @Override
    public void loginFailed(LoginResult result) {
        switch (result) {
            case USERNAME_OR_PASSWORD_ERROR :
                Toast.makeText(this, "学号或密码错误", Toast.LENGTH_SHORT).show();
                break;
            case VALIDATE_CODE_ERROR :
                Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show();
                break;
            case VALIDATE_CODE_EXPIRED :
                Toast.makeText(this, "验证码已过期", Toast.LENGTH_SHORT).show();
                break;
            case NETWORK_CONNECTION_ERROR :
                Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @OnClick(R.id.login_btn_login)
    public void login() {
        final String studentID = studentIDEditText.getText().toString();
        final String password = passwordEditText.getText().toString();
        final String validateCode = validateCodeEditText.getText().toString();

        grade = studentID.substring(0, 2);

        saveData(studentID, password);

        loginPresenter.login(studentID, password, validateCode);
    }

    @OnClick(R.id.login_btn_next)
    public void refreshValidateCode() {
        loginPresenter.getValidateCodeImage();
    }

    /**
     * 记录复选框的状态
     * 并根据复选框的情况并确定是否在app中保存账号和密码
     */
    private void saveData(String userName, String password){
        if(saveIDCheckBox.isChecked()){
            AppData.isSaveID( LoginActivity.this, true);
            AppData.saveID(LoginActivity.this, userName);
        } else {
            AppData.isSaveID(LoginActivity.this, false);
            AppData.saveID(LoginActivity.this, null);
        }

        if(savePasswordCheckBox.isChecked()){
            AppData.isSavePassword(LoginActivity.this, true);
            AppData.savePassword(LoginActivity.this, password);
        } else {
            AppData.isSavePassword(LoginActivity.this, false);
            AppData.savePassword(LoginActivity.this, null);
        }
    }

    /**
     * 如果用户上次选择了保存，则这次启动时同样自动勾选复选框
     * 如果app中存储有账号和密码，则初始化输入框
     */
    private void initData(){
        if(AppData.getSaveIDState(LoginActivity.this) == true){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    saveIDCheckBox.setChecked(true);
                }
            });
        }

        if(AppData.getSavePasswordState(LoginActivity.this) == true){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    savePasswordCheckBox.setChecked(true);
                }
            });
        }

        if(AppData.getID(LoginActivity.this) != null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    studentIDEditText.setText(AppData.getID(LoginActivity.this));
                }
            });
        }

        if(AppData.getID(LoginActivity.this) != null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    studentIDEditText.setText(AppData.getID(LoginActivity.this));
                }
            });
        }

        if(AppData.getPassword(LoginActivity.this) != null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    passwordEditText.setText(AppData.getPassword(LoginActivity.this));
                }
            });
        }
    }
}
