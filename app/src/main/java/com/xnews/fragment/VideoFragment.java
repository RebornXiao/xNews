package com.xnews.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.xnews.R;
import com.xnews.adapter.VideoAdapter;
import com.xnews.base.BaseFragment;
import com.xnews.bean.VideoModle;
import com.xnews.callback.VideoDataCallback;
import com.xnews.config.Url;
import com.xnews.http.HttpRequest;
import com.xnews.http.json.ViedoListJson;
import com.xnews.utils.MLog;
import com.xnews.utils.NetWorkHelper;
import com.xnews.utils.SharedPreferencesUtils;
import com.xnews.utils.ToastUtils;
import com.xnews.view.swiptlistview.SwipeListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * 视频
 * Created by xiao on 2016/2/16.
 */
public class VideoFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.listview)
    SwipeListView listview;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    private VideoAdapter videoAdapter;
    private int index = 0;
    private boolean isFirst = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        ButterKnife.bind(this, view);
        initView(view);
        initData();
        view.setOnClickListener(null);
        return view;
    }

    private void initData() {
        if (videoAdapter == null) {
            videoAdapter = new VideoAdapter(mContext);
        }
        listview.setAdapter(videoAdapter);
        getDataFromNet(index);
        isFirst = false;
    }

    /**
     * 从网络获取数据
     */
    private void getDataFromNet(final int index) {
        if (NetWorkHelper.isNetworkAvailable(mContext)) {
            HttpRequest.getInstance().getVideoData(new VideoDataCallback(mContext) {

                @Override
                public void onBefore(Request request) {
                    super.onBefore(request);
                    if (index == 0&&isFirst) {
                        progressBar.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void onAfter() {
                    super.onAfter();
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    if (listview != null) {
                        listview.onBottomComplete();
                    }
                    if (swipeContainer != null) {
                        swipeContainer.setRefreshing(false);
                    }
                }

                @Override
                public void onError(Request request, Exception e) {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    ToastUtils.showLong(mContext, "请求失败！");
                }

                @Override
                public void onResponse(List<VideoModle> response) {
                    MLog.d("response=" + response.toString());
                    videoAdapter.appendList(response);
                }
            }, index);
        } else {
            List<VideoModle> listsModles = new ArrayList<VideoModle>();
            String str = SharedPreferencesUtils.getString(mContext, "VideoFragment");
            List<VideoModle> list =
                    ViedoListJson.instance(mContext).readJsonVideoModles(str,
                            Url.VideoReDianId);
            listsModles.addAll(list);
            videoAdapter.appendList(listsModles);
        }
    }


    private void initView(View view) {
        swipeContainer.setOnRefreshListener(this);
        listview.setOnBottomListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPagte++;
                index = index + 20;
                getDataFromNet(index);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 下拉刷新中
     */
    @Override
    public void onRefresh() {
        getDataFromNet(index);
    }
}
