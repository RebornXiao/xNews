package com.xnews.activity;

import android.os.Bundle;

import com.xnews.R;
import com.xnews.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * 设置
 * Created by xiao on 2016/1/20.
 */
public class SettingsActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
    }
}
