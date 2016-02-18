package com.xnews.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xnews.R;
import com.xnews.adapter.MainPagerAdapter;
import com.xnews.base.BaseActivity;
import com.xnews.event.LocationEvent;
import com.xnews.fragment.MineFragment;
import com.xnews.fragment.NewsFragment;
import com.xnews.fragment.PicFragment;
import com.xnews.fragment.VideoFragment;
import com.xnews.view.HorizontalScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * 首页测试
 */
public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    @Bind(R.id.rb_bottombar_ordercar)
    RadioButton rbBottombarOrdercar;
    @Bind(R.id.rb_bottombar_parkadress)
    RadioButton rbBottombarParkadress;
    @Bind(R.id.rb_bottombar_mycount)
    RadioButton rbBottombarMycount;
    @Bind(R.id.rb_bottombar_contact)
    RadioButton rbBottombarContact;
    @Bind(R.id.rg_home_bottom)
    RadioGroup rgHomeBottom;
    @Bind(R.id.ll_tab)
    RelativeLayout llTab;
    @Bind(R.id.viewpager_content)
    HorizontalScrollViewPager mViewPager;
    @Bind(R.id.main_center)
    RelativeLayout mainCenter;
    @Bind(R.id.tv_main_title)
    TextView tvMainTitle;
    private List<Class> fragments = new ArrayList<Class>();
    private MainPagerAdapter mainPagerAdapter;
    private int currIndex = 0;
    private int nexItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);

        initData();
    }

    private void initData() {
        fragments.clear();
        fragments.add(NewsFragment.class);
        fragments.add(VideoFragment.class);
        fragments.add(PicFragment.class);
        fragments.add(MineFragment.class);
        if (mainPagerAdapter == null) {
            mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragments);
        }
        mViewPager.setAdapter(mainPagerAdapter);
        mViewPager.setOnPageChangeListener(this);
        mainPagerAdapter.notifyDataSetChanged();
        mViewPager.setCurrentItem(currIndex);
        checkButton(currIndex);

    }

    public void checkButton(int i) {
        switch (i) {
            case 0:
                rbBottombarOrdercar.setChecked(true);
                refreshTextColor(rbBottombarOrdercar);
                break;
            case 1:
                rbBottombarParkadress.setChecked(true);
                refreshTextColor(rbBottombarParkadress);
                break;
            case 2:
                rbBottombarMycount.setChecked(true);
                refreshTextColor(rbBottombarMycount);
                break;
            case 3:
                rbBottombarContact.setChecked(true);
                refreshTextColor(rbBottombarContact);
                break;
        }
    }

    private void refreshTextColor(View view) {
        if (view.getId() == rbBottombarOrdercar.getId()) {
            rbBottombarOrdercar.setTextColor(getResources().getColor(R.color.bottom_text_selected));
            rbBottombarParkadress.setTextColor(getResources().getColor(R.color.bottom_text_default));
            rbBottombarMycount.setTextColor(getResources().getColor(R.color.bottom_text_default));
            rbBottombarContact.setTextColor(getResources().getColor(R.color.bottom_text_default));
        } else if (view.getId() == rbBottombarParkadress.getId()) {
            rbBottombarOrdercar.setTextColor(getResources().getColor(R.color.bottom_text_default));
            rbBottombarParkadress.setTextColor(getResources().getColor(R.color.bottom_text_selected));
            rbBottombarMycount.setTextColor(getResources().getColor(R.color.bottom_text_default));
            rbBottombarContact.setTextColor(getResources().getColor(R.color.bottom_text_default));
        } else if (view.getId() == rbBottombarMycount.getId()) {
            rbBottombarOrdercar.setTextColor(getResources().getColor(R.color.bottom_text_default));
            rbBottombarParkadress.setTextColor(getResources().getColor(R.color.bottom_text_default));
            rbBottombarMycount.setTextColor(getResources().getColor(R.color.bottom_text_selected));
            rbBottombarContact.setTextColor(getResources().getColor(R.color.bottom_text_default));
        } else if (view.getId() == rbBottombarContact.getId()) {
            rbBottombarOrdercar.setTextColor(getResources().getColor(R.color.bottom_text_default));
            rbBottombarParkadress.setTextColor(getResources().getColor(R.color.bottom_text_default));
            rbBottombarMycount.setTextColor(getResources().getColor(R.color.bottom_text_default));
            rbBottombarContact.setTextColor(getResources().getColor(R.color.bottom_text_selected));
        }
    }

    public void onEventMainThread(LocationEvent event) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int i) {
        currIndex = i;
        checkButton(currIndex);
        if (i == 0) {
            tvMainTitle.setText("新闻");
        } else if (i == 1) {
            tvMainTitle.setText("视频");
        } else if (i == 2) {
            tvMainTitle.setText("图片");
        } else if (i == 3) {
            tvMainTitle.setText("我");
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.rb_bottombar_ordercar)
    void viewNews() {
        nexItem = 0;
        mViewPager.setCurrentItem(nexItem);
    }

    @OnClick(R.id.rb_bottombar_parkadress)
    void viewVideo() {
        nexItem = 1;
        mViewPager.setCurrentItem(nexItem);
    }

    @OnClick(R.id.rb_bottombar_mycount)
    void viewPic() {
        nexItem = 2;
        mViewPager.setCurrentItem(nexItem);
    }

    @OnClick(R.id.rb_bottombar_contact)
    void viewNMine() {
        nexItem = 3;
        mViewPager.setCurrentItem(nexItem);
    }

}
