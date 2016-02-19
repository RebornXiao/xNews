package com.xnews.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.xnews.R;
import com.xnews.base.BaseActivity;
import com.xnews.event.LocationEvent;
import com.xnews.utils.MLog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 当前个人位置
 * Created by xiao on 2016/2/18.
 */
public class MapActivity extends BaseActivity {
    @Bind(R.id.tv_main_title)
    TextView tvMainTitle;
    @Bind(R.id.view)
    View view;
    @Bind(R.id.rl_main_top)
    RelativeLayout rlMainTop;
    @Bind(R.id.map_show)
    MapView mapShow;
    @Bind(R.id.iv_back_main)
    ImageView ivBackMain;

    private AMap aMap;
    private LatLng mLatLng_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);
        initView(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    private void initView(Bundle savedInstanceState) {
        mapShow.onCreate(savedInstanceState);
    }

    @OnClick(R.id.iv_back_main)
    void backMain() {
        activityUtil.jumpBackTo(MainActivity.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initMap();
    }

    /**
     * 地图相关方法
     */
    private void initMap() {
        if (aMap == null) {
            aMap = mapShow.getMap();
        }
        setParkMarker();
    }

    public void onEventMainThread(LocationEvent event) {
        MLog.d("信息来了");
        setParkMarker();
    }

    /**
     * 设置个人位置
     */
    private void setParkMarker() {
        if (!TextUtils.isEmpty(app.latitude) && !TextUtils.isEmpty(app.longitude)) {
            mLatLng_user = new LatLng(Double.valueOf(app.latitude),
                    Double.valueOf(app.longitude));
        } else {
            return;
        }
        aMap.clear();
        aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .position(mLatLng_user)
                .title("我的位置")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.location_marker)).snippet(app.curLocationSite)
                .draggable(true).period(10));
        LatLng mLatLng_user1 = new LatLng(Double.valueOf(app.latitude) - 0.01,
                Double.valueOf(app.longitude));
        LatLng mLatLng_user2 = new LatLng(Double.valueOf(app.latitude),
                Double.valueOf(app.longitude) - 0.01);
        LatLng mLatLng_user3 = new LatLng(
                Double.valueOf(app.latitude) - 0.005,
                Double.valueOf(app.longitude) - 0.005);
        LatLng mLatLng_user4 = new LatLng(
                Double.valueOf(app.latitude) + 0.014,
                Double.valueOf(app.longitude) + 0.006);
        LatLng mLatLng_user5 = new LatLng(
                Double.valueOf(app.latitude) + 0.014,
                Double.valueOf(app.longitude) - 0.006);
        LatLng mLatLng_user6 = new LatLng(
                Double.valueOf(app.latitude) - 0.005,
                Double.valueOf(app.longitude) + 0.005);
        LatLng mLatLng_user7 = new LatLng(
                Double.valueOf(app.latitude) + 0.0155,
                Double.valueOf(app.longitude) + 0.013);
        LatLng mLatLng_user8 = new LatLng(
                Double.valueOf(app.latitude) + 0.0155,
                Double.valueOf(app.longitude) - 0.013);
        LatLng mLatLng_user9 = new LatLng(
                Double.valueOf(app.latitude) + 0.009,
                Double.valueOf(app.longitude) + 0.014);
        LatLng mLatLng_user10 = new LatLng(
                Double.valueOf(app.latitude) + 0.009,
                Double.valueOf(app.longitude) - 0.014);
        LatLng mLatLng_user11 = new LatLng(
                Double.valueOf(app.latitude) + 0.01,
                Double.valueOf(app.longitude));
        LatLng mLatLng_user12 = new LatLng(Double.valueOf(app.latitude),
                Double.valueOf(app.longitude) + 0.01);

        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLng_user, 15.0f));
        aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .position(mLatLng_user)
                .title("我的位置")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.location_marker)).snippet("")
                .draggable(true).period(10));
        aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .position(mLatLng_user1)
                .title("我的位置")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.park_location)).snippet("")
                .draggable(true).period(10));
        aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .position(mLatLng_user2)
                .title("我的位置")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.park_location)).snippet("")
                .draggable(true).period(10));
        aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .position(mLatLng_user3)
                .title("我的位置")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.park_location)).snippet("")
                .draggable(true).period(10));
        aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .position(mLatLng_user4)
                .title("我的位置")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.park_location)).snippet("")
                .draggable(true).period(10));
        aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .position(mLatLng_user5)
                .title("我的位置")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.park_location)).snippet("")
                .draggable(true).period(10));
        aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .position(mLatLng_user6)
                .title("我的位置")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.park_location)).snippet("")
                .draggable(true).period(10));
        aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .position(mLatLng_user7)
                .title("我的位置")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.park_location)).snippet("")
                .draggable(true).period(10));
        aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .position(mLatLng_user8)
                .title("我的位置")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.park_location)).snippet("")
                .draggable(true).period(10));
        aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .position(mLatLng_user9)
                .title("我的位置")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.park_location)).snippet("")
                .draggable(true).period(10));
        aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .position(mLatLng_user10)
                .title("我的位置")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.park_location)).snippet("")
                .draggable(true).period(10));
        aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .position(mLatLng_user11)
                .title("我的位置")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.park_location)).snippet("")
                .draggable(true).period(10));
        aMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .position(mLatLng_user12)
                .title("我的位置")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.park_location)).snippet("")
                .draggable(true).period(10));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapShow.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapShow.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mapShow.onDestroy();
    }
}
