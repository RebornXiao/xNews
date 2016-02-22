package com.xnews.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.xnews.R;
import com.xnews.adapter.ImageAdapter;
import com.xnews.base.BaseActivity;
import com.xnews.bean.NewDetailModle;
import com.xnews.bean.NewModle;
import com.xnews.view.flipview.FlipView;
import com.xnews.view.flipview.OverFlipMode;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ImageDetailActivity extends BaseActivity implements FlipView.OnFlipListener,
        FlipView.OnOverFlipListener {
    protected ImageAdapter imageAdapter;
    @Bind(R.id.back)
    TextView back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.new_title)
    TextView newTitle;
    @Bind(R.id.flip_view)
    FlipView  flipView;

    private List<String> imgList;
    private NewDetailModle newDetailModle;
    private String titleString;
    private NewModle newModle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        init();
    }

    public void init() {
        try {
            if (getIntent().getExtras().getSerializable("newDetailModle") != null) {
                newDetailModle = (NewDetailModle) getIntent().getExtras().getSerializable(
                        "newDetailModle");
                imgList = newDetailModle.getImgList();
                titleString = newDetailModle.getTitle();
            } else {
                newModle = (NewModle) getIntent().getExtras().getSerializable("newModle");
                imgList = newModle.getImagesModle().getImgList();
                titleString = newModle.getTitle();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initView() {
        try {
            newTitle.setText(titleString);
            imageAdapter.appendList(imgList);
            mFlipView.setOnFlipListener(this);
            mFlipView.setAdapter(imageAdapter);
            // mFlipView.peakNext(false);
            mFlipView.setOverFlipMode(OverFlipMode.RUBBER_BAND);
            mFlipView.setOnOverFlipListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOverFlip(FlipView v, OverFlipMode mode, boolean overFlippingPrevious,
                           float overFlipDistance, float flipDistancePerPage) {
    }

    @Override
    public void onFlippedToPage(FlipView v, int position, long id) {
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}
