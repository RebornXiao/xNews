package com.xnews.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.xnews.R;
import com.xnews.app.MyApp;
import com.xnews.utils.ActivityStackControlUtil;

/**
 * 享元类
 * Created by xiao on 2014/1/20.
 */
public class BaseActivity extends FragmentActivity {
    public MyApp app;
    public Context mContext;
    public ActivityStackControlUtil activityUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setTheme(R.style.MyAppTheme);
        mContext = this;
        app = (MyApp) getApplication();
        activityUtil = ActivityStackControlUtil.getInstance();
        activityUtil.onCreate(this);
        app.addActivitys(this);

    }
}
