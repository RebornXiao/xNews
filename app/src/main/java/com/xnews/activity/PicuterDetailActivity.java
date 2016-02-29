package com.xnews.activity;


import android.os.Bundle;
import android.widget.TextView;

import com.xnews.R;
import com.xnews.adapter.PicuterDetailAdapter;
import com.xnews.base.BaseActivity;
import com.xnews.bean.PicuterDetailModle;
import com.xnews.callback.PicDetailCallback;
import com.xnews.config.Tag;
import com.xnews.config.Url;
import com.xnews.http.HttpRequest;
import com.xnews.http.json.PicuterSinaJson;
import com.xnews.utils.MLog;
import com.xnews.utils.NetWorkHelper;
import com.xnews.utils.SharedPreferencesUtils;
import com.xnews.utils.ToastUtils;
import com.xnews.view.flipview.FlipView;
import com.xnews.view.flipview.OverFlipMode;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;

public class PicuterDetailActivity extends BaseActivity implements
        FlipView.OnFlipListener,
        FlipView.OnOverFlipListener {
    protected PicuterDetailAdapter picuterDetailAdapter;
    @Bind(R.id.back)
    TextView back;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.flip_view)
    FlipView mFlipView;

    private String imgUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    public void initData() {
        try {
            if (getIntent().getExtras().getString("pic_id") != null) {
                imgUrl = getIntent().getExtras().getString("pic_id");
                showProgressDialog();
                loadData(Url.JINGXUANDETAIL_ID + imgUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initView() {
        // imageAdapter.appendList(imgList);
        try {
            mFlipView.setOnFlipListener(this);
            if (picuterDetailAdapter == null) {
                picuterDetailAdapter = new PicuterDetailAdapter(mContext);
            }
            mFlipView.setAdapter(picuterDetailAdapter);
            mFlipView.peakNext(false);
            mFlipView.setOverFlipMode(OverFlipMode.RUBBER_BAND);
            mFlipView.setOnOverFlipListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadData(String url) {
        if (NetWorkHelper.isNetworkAvailable(mContext)) {
            HttpRequest.getInstance().getPicDetailData(new PicDetailCallback(mContext, imgUrl) {

                @Override
                public void onBefore(Request request) {
                    super.onBefore(request);
                    showProgressDialog();
//                    if (index == 0 && isFirst) {
//                        progressBar.setVisibility(View.VISIBLE);
//                    }
                }

                @Override
                public void onAfter() {
                    super.onAfter();
//                    if (progressBar != null) {
//                        progressBar.setVisibility(View.GONE);
//                    }
//                    if (listview != null) {
//                        listview.onBottomComplete();
//                    }
//                    if (swipeContainer != null) {
//                        swipeContainer.setRefreshing(false);
//                    }
                    dismissProgressDialog();
                }

                @Override
                public void onError(Call call, Exception e) {
//                    if (progressBar != null) {
//                        progressBar.setVisibility(View.GONE);
//                    }
                    dismissProgressDialog();
                    ToastUtils.showLong(mContext, "请求失败！");
                }

                @Override
                public void onResponse(List<PicuterDetailModle> response) {
                    MLog.d("response=" + response.toString());
                    picuterDetailAdapter.appendList(response);
                }
            }, url, Tag.PICUTERDETAILACTIVITY);

        } else {
            dismissProgressDialog();
            ToastUtils.showShort(mContext, "暂无网络");
            SharedPreferencesUtils.getString(mContext, imgUrl);
            String result = SharedPreferencesUtils.getString(mContext, imgUrl);
            List<PicuterDetailModle> list = PicuterSinaJson.instance(mContext).readJsonPicuterModle(
                    result);
            picuterDetailAdapter.appendList(list);
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
//        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        OkHttpUtils.getInstance().cancelTag(Tag.PICUTERDETAILACTIVITY);
//        MobclickAgent.onPause(this);
    }
}
