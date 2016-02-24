package com.xnews.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xnews.R;
import com.xnews.base.BaseActivity;
import com.xnews.utils.SharedPreferencesUtils;
import com.xnews.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiao on 2016/2/24.
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.iv_back_main)
    ImageView ivBackMain;
    @Bind(R.id.tv_main_title)
    TextView tvMainTitle;
    @Bind(R.id.view)
    View view;
    @Bind(R.id.rl_main_top)
    RelativeLayout rlMainTop;
    @Bind(R.id.iv_login_logo)
    ImageView ivLoginLogo;
    @Bind(R.id.iv_login_phone)
    ImageView ivLoginPhone;
    @Bind(R.id.login_phonenumber)
    EditText loginPhonenumber;
    @Bind(R.id.rl_regester_ep_msg)
    RelativeLayout rlRegesterEpMsg;
    @Bind(R.id.iv_login_pwd)
    ImageView ivLoginPwd;
    @Bind(R.id.login_psd)
    EditText loginPsd;
    @Bind(R.id.rl_login_pwd_login)
    RelativeLayout rlLoginPwdLogin;
    @Bind(R.id.view_line_2)
    View viewLine2;
    @Bind(R.id.login_btn)
    Button loginBtn;
    @Bind(R.id.divider_center)
    View dividerCenter;
    @Bind(R.id.tv_register_btn)
    TextView tvRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        iniData();
    }

    /**
     * 返回
     */
    @OnClick(R.id.iv_back_main)
    void back() {
        onBackPressed();
    }

    /**
     * 登录
     */
    @OnClick(R.id.login_btn)
    void login() {
        if (app.person != null) {
            String phone = "";
            String psw = "";
            if (!TextUtils.isEmpty(loginPhonenumber.getText().toString().trim())
                    && loginPhonenumber.getText().toString().trim().equals(app.person.getEquipmentId())) {
                phone = loginPhonenumber.getText().toString().trim();
            } else {
                ToastUtils.showShort(mContext, "请输入正确的手机号码");
                return;
            }
            if (!TextUtils.isEmpty(loginPsd.getText().toString().trim())
                    && loginPsd.getText().toString().trim().equals(app.person.getPsd())) {
                psw = loginPsd.getText().toString().trim();
            } else {
                ToastUtils.showShort(mContext, "请输入正确的密码");
                return;
            }
            ToastUtils.showShort(mContext, "登陆成功！欢迎体验！");
            SharedPreferencesUtils.putString(app, "phone", phone);
            SharedPreferencesUtils.putString(app, "psw", psw);
            onBackPressed();
        } else {
            ToastUtils.showLong(mContext, "请先注册");
            activityUtil.jumpTo(RegiterActivity.class);
        }
    }

    /**
     * 注册
     */
    @OnClick(R.id.tv_register_btn)
    void register() {
        activityUtil.jumpTo(RegiterActivity.class);
    }


    private void iniData() {
        tvMainTitle.setText("登录");
        String phone = SharedPreferencesUtils.getString(app, "phone");
        String psw = SharedPreferencesUtils.getString(app, "psw");
        if (!TextUtils.isEmpty(phone)) {
            loginPhonenumber.setText(phone);
        }
        if (!TextUtils.isEmpty(psw)) {
            loginPsd.setText(psw);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        activityUtil.jumpBackTo(MainActivity.class);
        finish();
    }
}
