package com.xnews.app;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import com.xnews.db.DBHelper;
import com.xnews.service.MyLocationService;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import greendao.DaoMaster;
import greendao.DaoSession;
import greendao.Person;

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
    public DaoSession daoSession;
    private long l = 0;
    public boolean isLogin;

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public Person person;

    public void exitApp() {
        for (SoftReference<Activity> activity : activitys) {
            Activity temp;
            if ((temp = activity.get()) != null) {
                temp.finish();
            }
        }
    }

    //数据库名，表名是自动被创建的
    public static final String DB_NAME = "person.db";

    public void addActivitys(Activity activity) {
        activitys.add(new SoftReference<Activity>(activity));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(getBaseContext(), MyLocationService.class));
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
        person = DBHelper.getPersonForId(getApplicationContext(), l);
        if (person == null) {
            isLogin = false;
        } else {
            isLogin = true;
        }


    }
}
