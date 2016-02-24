package com.xnews.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xnews.R;
import com.xnews.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

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
    @Bind(R.id.pwd_forget_btn)
    TextView pwdForgetBtn;
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
    @Bind(R.id.tv_register_enterprise_btn)
    TextView tvRegisterEnterpriseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        iniData();
    }

    private void iniData() {


    }
}
