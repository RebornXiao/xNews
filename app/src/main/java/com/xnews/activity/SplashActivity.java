package com.xnews.activity;

import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.xnews.R;
import com.xnews.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 欢迎界面
 * Created by xiao on 2016/2/19.
 */
public class SplashActivity extends BaseActivity {

    @Bind(R.id.iv_splash)
    ImageView ivSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        startAnimation();
    }

    // 动画效果
    private void startAnimation() {
        AlphaAnimation alphaAnima = new AlphaAnimation(0.3f, 1);
        alphaAnima.setDuration(1500);
        alphaAnima.setFillEnabled(true);
        alphaAnima.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                activityUtil.jumpTo(GuideActivity.class);
                finish();
            }
        });
        ivSplash.startAnimation(alphaAnima);
    }
}
