package com.lee.nju_gpa_calculator.view.login;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.lee.nju_gpa_calculator.R;
import com.lee.nju_gpa_calculator.contract.LoginContract;
import com.lee.nju_gpa_calculator.presenter.LoginPresenter;
import com.lee.nju_gpa_calculator.utils.AppData;

import butterknife.ButterKnife;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 果宝 on 2018/2/3.
 */

public class LoginActivity extends AppCompatActivity implements LoginContract.View{

    private Handler handler;

    private LoginContract.Presenter loginPresenter;

    @BindView(R.id.login_et_studentID) EditText studentIDEditText;
    @BindView(R.id.login_et_password) EditText passwordEditText;
    @BindView(R.id.login_iv_validate_code) ImageView validateCodeImageView;
    @BindView(R.id.login_et_validate_code) EditText validateCodeEditText;
    @BindView(R.id.login_btn_next) Button nextValidateButton;
    @BindView(R.id.login_btn_login) Button loginButton;

    @BindView(R.id.login_cb_save_id) CheckBox saveIDCheckBox;
    @BindView(R.id.login_cb_save_password) CheckBox savePasswordCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hideTitleBar();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenter(this);
        handler = new Handler();
        initData();
        loginPresenter.start();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.loginPresenter = presenter;
    }

    @Override
    public void setValidateCodeImage(Bitmap bitmap) {
        validateCodeImageView.setImageBitmap(bitmap);
    }

    @OnClick(R.id.login_btn_login)
    public void login() {
        final String studentID = studentIDEditText.getText().toString();
        final String password = passwordEditText.getText().toString();
        final String validateCode = validateCodeEditText.getText().toString();

        saveData(studentID, password);

        Toast.makeText(LoginActivity.this, "登录", Toast.LENGTH_LONG).show();
        loginPresenter.login(studentID, password, validateCode);
        //发送请求
//        new OkHttpUtil().confirmLogin(LoginActivity.this, userName, password);
    }

    private void hideTitleBar() {
        Window window = getWindow();

        int noTitle = Window.FEATURE_NO_TITLE;
        this.requestWindowFeature(noTitle);

//        int fullscreen = WindowManager.LayoutParams.FLAG_FULLSCREEN;
//        window.setFlags(fullscreen, fullscreen);
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
