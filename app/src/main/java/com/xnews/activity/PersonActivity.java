package com.xnews.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xnews.R;
import com.xnews.base.BaseActivity;
import com.xnews.utils.TVUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 个人信息
 * Created by xiao on 2016/2/24.
 */
public class PersonActivity extends BaseActivity {

    @Bind(R.id.iv_back_main)
    ImageView ivBackMain;
    @Bind(R.id.tv_main_title)
    TextView tvMainTitle;
    @Bind(R.id.view)
    View view;
    @Bind(R.id.rl_main_top)
    RelativeLayout rlMainTop;
    @Bind(R.id.login_name)
    TextView loginName;
    @Bind(R.id.login_sex)
    TextView loginSex;
    @Bind(R.id.layout_sex_test)
    RelativeLayout layoutSexTest;
    @Bind(R.id.login_phone)
    TextView loginPhone;
    @Bind(R.id.login_status_right)
    ImageView loginStatusRight;
    @Bind(R.id.login_status)
    TextView loginStatus;
    @Bind(R.id.rl_person_status)
    RelativeLayout rlPersonStatus;
    @Bind(R.id.login_dld)
    TextView loginDld;
    @Bind(R.id.login_address)
    TextView loginAddress;
    @Bind(R.id.rl_change_pwd)
    RelativeLayout rlChangePwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        ButterKnife.bind(this);
        iniData();
    }

    private void iniData() {
        tvMainTitle.setText("我的信息");
        TVUtils.setText(loginName, app.person.getUserName());
        TVUtils.setText(loginSex, app.person.getSex());
        TVUtils.setText(loginPhone, app.person.getEquipmentId());
        if (app.person.getIsVIP() == 1) {
            TVUtils.setText(loginStatus, "VIP" + app.person.getVipLevel());
        } else {
            TVUtils.setText(loginStatus, "非VIP");
        }
        TVUtils.setText(loginDld, app.person.getPersonID());

    }

}
