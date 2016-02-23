package com.xnews.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import com.xnews.R;
import com.xnews.activity.PicuterDetailActivity;
import com.xnews.activity.VideoPlayActivity;
import com.xnews.adapter.PictureAdapter;
import com.xnews.base.BaseActivity;
import com.xnews.base.BaseFragment;
import com.xnews.bean.PicuterModle;
import com.xnews.bean.VideoModle;
import com.xnews.callback.PicDataCallback;
import com.xnews.http.HttpRequest;
import com.xnews.http.json.PicuterSinaJson;
import com.xnews.utils.MLog;
import com.xnews.utils.NetWorkHelper;
import com.xnews.utils.SharedPreferencesUtils;
import com.xnews.utils.ToastUtils;
import com.xnews.view.swiptlistview.SwipeListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Request;

/**
 * 图片
 * Created by xiao on 2016/2/16.
 */
public class PicFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.listview)
    SwipeListView listview;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private int index = 0;
    private PictureAdapter pictureAdapter;
    protected List<PicuterModle> listsModles;
    private Map<String, PicuterModle> newHashMap;
    protected Map<String, String> url_maps;
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
        if (pictureAdapter == null) {
            pictureAdapter = new PictureAdapter(mContext);
        }
        listview.setAdapter(pictureAdapter);
        if (listsModles == null) {
            listsModles = new ArrayList<PicuterModle>();
        }
        if (newHashMap == null) {
            newHashMap = new HashMap<String, PicuterModle>();
        }
        if (url_maps == null) {
            url_maps = new HashMap<String, String>();
        }
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MLog.d("图片点击:" + position);
                PicuterModle photoModle = listsModles.get(position);
                Bundle bundle = new Bundle();
                bundle.putString("pic_id", photoModle.getId());
                ((BaseActivity) getActivity()).openActivity(PicuterDetailActivity.class,
                        bundle, 0);
            }
        });
        getDataFromNet(index);
        isFirst = false;
    }

    public void enterDetailActivity(VideoModle videoModle) {
        Bundle bundle = new Bundle();
        bundle.putString("playUrl", videoModle.getMp4Hd_url());
        ((BaseActivity) getActivity()).openActivity(VideoPlayActivity.class, bundle, 0);
    }

    /**
     * 从网络获取数据
     */
    private void getDataFromNet(final int index) {
        if (NetWorkHelper.isNetworkAvailable(mContext)) {
            HttpRequest.getInstance().getPicData(new PicDataCallback(mContext) {

                @Override
                public void onBefore(Request request) {
                    super.onBefore(request);
                    if (index == 0 && isFirst) {
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
                public void onResponse(List<PicuterModle> response) {
                    MLog.d("response=" + response.toString());
                    pictureAdapter.appendList(response);
                    listsModles.addAll(response);
                }
            }, index);
        } else {
            List<PicuterModle> listsModles = new ArrayList<PicuterModle>();
            String str = SharedPreferencesUtils.getString(mContext, "PicFragment");
            List<PicuterModle> list = PicuterSinaJson.instance(mContext).readJsonPhotoListModles(
                    str);
            listsModles.addAll(list);
            pictureAdapter.appendList(listsModles);
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
