package com.xnews.app;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;

import com.xnews.db.DBHelper;
import com.xnews.service.MyLocationService;
import com.xnews.utils.ToastUtils;

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
    public long l = 1;
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

    /**
     * 阅读新闻视频图片增加积分和活跃度的操作
     */
    public void readNews() {
        ToastUtils.showShort(this, "您的活跃度 +1 ，您的积分 +10");
        person.setVitality((Integer.parseInt(person.getVitality()) + 1) + "");
        person.setUserIntegral((Integer.parseInt(person.getUserIntegral()) + 10) + "");
        if (Integer.parseInt(person.getUserIntegral()) > 200) {
            if (person.getVipLevel() == 1) {
                ToastUtils.showShort(this, "恭喜你，VIP等级提高啦！");
            }
            person.setVipLevel(2);
        }
        if (Integer.parseInt(person.getUserIntegral()) > 400) {
            if (person.getVipLevel() == 2) {
                ToastUtils.showShort(this, "恭喜你，VIP等级提高啦！");
            }
            person.setVipLevel(3);
        }
        if (Integer.parseInt(person.getUserIntegral()) > 800) {
            if (person.getVipLevel() == 3) {
                ToastUtils.showShort(this, "恭喜你，VIP等级提高啦！");
            }
            person.setVipLevel(4);
        }
        if (Integer.parseInt(person.getUserIntegral()) > 1600) {
            if (person.getVipLevel() == 4) {
                ToastUtils.showShort(this, "恭喜你，VIP等级提高啦！");
            }
            person.setVipLevel(5);
        }
        DBHelper.insertOrUpdate(this, person);
    }
}
