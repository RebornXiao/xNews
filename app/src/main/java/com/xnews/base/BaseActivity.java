package com.xnews.base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.xnews.R;
import com.xnews.app.MyApp;
import com.xnews.config.Url;
import com.xnews.utils.ActivityStackControlUtil;
import com.xnews.utils.DialogUtil;
import com.xnews.utils.IntentUtils;

/**
 * 享元类
 * Created by xiao on 2014/1/20.
 */
public class BaseActivity extends FragmentActivity {
    public MyApp app;
    public Context mContext;
    public ActivityStackControlUtil activityUtil;
    private Dialog progressDialog;


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

    public String getUrl(String newId) {
        return Url.NewDetail + newId + Url.endDetailUrl;
    }


    /**
     * 显示dialog
     *
     * @param
     */
    public void showProgressDialog() {
        try {
            if (progressDialog == null) {
                progressDialog = DialogUtil.createLoadingDialog(this);

            }
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 隐藏dialog
     */
    public void dismissProgressDialog() {
        try {

            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * dialog是否显示
     */
    public boolean isShow() {
        try {

            if (progressDialog != null && progressDialog.isShowing()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 更具类打开acitvity,并携带参数
     */
    public void openActivity(Class<?> pClass, Bundle pBundle, int requestCode) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        if (requestCode == 0) {
            IntentUtils.startPreviewActivity(this, intent, 0);
            // startActivity(intent);
        } else {
            IntentUtils.startPreviewActivity(this, intent, requestCode);
            // startActivityForResult(intent, requestCode);
        }
        // actityAnim();
    }
}
