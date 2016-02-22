package com.xnews.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.xnews.app.MyApp;
import com.xnews.event.LocationEvent;
import com.xnews.utils.MLog;

import de.greenrobot.event.EventBus;

/**
 * 定位服务
 * Created by xiao on 2015/8/2.
 */
public class MyLocationService extends Service implements AMapLocationListener {
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;

    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

    private Handler mainHandler;

    private MyApp application;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = (MyApp) getApplication();
        mainHandler = new Handler(getMainLooper());
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        getLocationManager();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        if (amapLocation != null
                && amapLocation.getErrorCode() == 0) {
            // 定位成功回调信息，设置相关消息
            application.latitude = amapLocation.getLatitude() + "";
            application.longitude = amapLocation.getLongitude() + "";
            application.gps = amapLocation.getLongitude() + ","
                    + amapLocation.getLatitude();
            application.addressNowCity = amapLocation.getCity();
            String address = amapLocation.getAddress();
            if (address != null && address.length() > 0) {
                application.curLocationSite = address;
            }
            EventBus.getDefault().post(new LocationEvent());
            MLog.d("定位得到信息=" + application.gps);
        } else {
            Log.e("AmapErr", "Location ERR:"
                    + amapLocation.getErrorCode());
        }
    }

    private void getLocationManager() {
        mainHandler.post(new Runnable() {
            @Override
            public void run() {
                if (mLocationClient == null) {
                    //初始化定位
                    mLocationClient = new AMapLocationClient(getApplicationContext());
                    mLocationClient.setLocationListener(MyLocationService.this);
                    //设置定位间隔,单位毫秒,默认为2000ms
                    mLocationOption.setInterval(10*60*1000);
                    //给定位客户端对象设置定位参数
                    mLocationClient.setLocationOption(mLocationOption);
                    //启动定位
                    mLocationClient.startLocation();
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
