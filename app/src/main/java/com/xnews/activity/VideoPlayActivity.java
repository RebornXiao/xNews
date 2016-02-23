package com.xnews.activity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xnews.R;
import com.xnews.base.BaseActivity;
import com.xnews.utils.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoPlayActivity extends BaseActivity
        implements MediaPlayer.OnInfoListener,
        MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener {
    @Bind(R.id.buffer)
    VideoView mVideoView;
    @Bind(R.id.probar)
    ProgressBar mProgressBar;
    @Bind(R.id.load_rate)
    TextView mLoadRate;
    @Bind(R.id.video_title)
    TextView videoTitle;
    @Bind(R.id.video_end)
    ImageView videoEnd;
    protected ImageView mVideoEnd;
    private Uri uri;
    private String playUrl;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_videobuffer);
        ButterKnife.bind(this);

        initView();
        init();

    }

    public void init() {
        try {
            if (!LibsChecker.checkVitamioLibs(this))
                return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initView() {
        playUrl = getIntent().getExtras().getString("playUrl");
        title = getIntent().getExtras().getString("filename");
        if ("".equals(playUrl) || playUrl == null) {
            ToastUtils.showShort(mContext, "请求地址错误");
            finish();
        }
        uri = Uri.parse(playUrl);
        mVideoView.setVideoURI(uri);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnBufferingUpdateListener(this);
        mVideoView.setOnPreparedListener(this);
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        mLoadRate.setText(percent + "%");
//        mVideoView.setFileName(title);
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        System.out.println(what);
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (mVideoView.isPlaying()) {
                    mVideoView.pause();
                    mProgressBar.setVisibility(View.VISIBLE);
                    mLoadRate.setText("");
                    mLoadRate.setVisibility(View.VISIBLE);
                }
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                // mVideoEnd.setVisibility(View.VISIBLE);
                mVideoView.start();
                mProgressBar.setVisibility(View.GONE);
                mLoadRate.setVisibility(View.GONE);
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                break;
        }
        return true;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.setPlaybackSpeed(1.0f);
    }

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

}
