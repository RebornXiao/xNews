package com.xnews.app;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.xnews.service.MyLocationService;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiao on 2016/1/20.
 */
public class MyApp extends Application {
    private List<SoftReference<Activity>> activitys = new ArrayList<SoftReference<Activity>>();
    public String latitude = "";
    public String longitude = "";
    public String gps = "";
    public String curLocationSite = "火星";
    public String addressNowCity = "深圳市";
    public String areaCodeNow = "";

    public void exitApp() {
        for (SoftReference<Activity> activity : activitys) {
            Activity temp;
            if ((temp = activity.get()) != null) {
                temp.finish();
            }
        }
    }

    public void addActivitys(Activity activity) {
        activitys.add(new SoftReference<Activity>(activity));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(getBaseContext(), MyLocationService.class));

    }
}
