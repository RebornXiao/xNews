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
import com.xnews.db.DBHelper;
import com.xnews.utils.SharedPreferencesUtils;
import com.xnews.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import greendao.Person;

/**
 * Created by xiao on 2016/2/24.
 */
public class RegiterActivity extends BaseActivity {
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
    @Bind(R.id.rl_regester_ep_msg)
    RelativeLayout rlRegesterEpMsg;
    @Bind(R.id.view_line_1)
    View viewLine1;
    @Bind(R.id.iv_login_pwd)
    ImageView ivLoginPwd;
    @Bind(R.id.pwd_et)
    TextView pwdEt;
    @Bind(R.id.rl_regester_ep_address)
    RelativeLayout rlRegesterEpAddress;
    @Bind(R.id.view_line_2)
    View viewLine2;
    @Bind(R.id.btn_register_next_step)
    Button btnRegisterNextStep;
    @Bind(R.id.tv_no_number_phone)
    TextView tvNoNumberPhone;
    @Bind(R.id.tv_register_tologin)
    RelativeLayout tvRegisterTologin;
    @Bind(R.id.et_phone)
    EditText etPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initData();
    }


    private void initData() {
        tvMainTitle.setText("注册");

    }

    @OnClick({R.id.iv_back_main, R.id.tv_register_tologin})
    void back() {
        onBackPressed();
    }

    @OnClick(R.id.btn_register_next_step)
    void register() {
        showProgressDialog();
        String phone = etPhone.getText().toString().trim();
        if (TextUtils.isEmpty(phone) || !phone.matches("1[3|4|5|7|8|][0-9]{9}")) {
            dismissProgressDialog();
            ToastUtils.showLong(mContext, "请输入正确的手机号");
            return;
        }
        int length = phone.length();
        String psw = phone.substring(length - 6);
        if (app.person == null) {
            app.person = new Person(phone, psw);
        }
        app.person.setUserIntegral("100");
        app.person.setDeposit("0");
        app.person.setBalance("0");
        app.person.setVitality("10");
        app.person.setIsVIP(1);
        app.person.setVipLevel(1);
        SharedPreferencesUtils.putString(app, "phone", phone);
        SharedPreferencesUtils.putString(app, "psw", psw);
        DBHelper.insertOrUpdate(app, app.person);
        dismissProgressDialog();
        app.isLogin = true;
        activityUtil.jumpBackTo(MainActivity.class);
        finish();
        ToastUtils.showShort(mContext, "注册成功！");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        activityUtil.jumpBackTo(LoginActivity.class);
        finish();
    }
}
