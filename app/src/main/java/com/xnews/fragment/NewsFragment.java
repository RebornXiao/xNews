package com.xnews.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;

import com.xnews.R;
import com.xnews.activity.DetailsActivity;
import com.xnews.activity.ImageDetailActivity;
import com.xnews.adapter.NewAdapter;
import com.xnews.base.BaseFragment;
import com.xnews.bean.NewModle;
import com.xnews.callback.NewsDataCallback;
import com.xnews.config.Tag;
import com.xnews.config.Url;
import com.xnews.http.HttpRequest;
import com.xnews.http.json.NewListJson;
import com.xnews.utils.MLog;
import com.xnews.utils.NetWorkHelper;
import com.xnews.utils.SharedPreferencesUtils;
import com.xnews.utils.ToastUtils;
import com.xnews.view.swiptlistview.SwipeListView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;

/**
 * 新闻
 * Created by xiao on 2016/2/16.
 */
public class NewsFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.listview)
    SwipeListView listview;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private int index = 0;
    private NewAdapter newAdapter;
    private Map<String, NewModle> newHashMap;
    protected Map<String, String> url_maps;
    private boolean isFirst = true;
    private ArrayList<NewModle> listsModles;

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
        if (listsModles == null) {
            listsModles = new ArrayList<NewModle>();
        }
        if (newHashMap == null) {
            newHashMap = new HashMap<String, NewModle>();
        }
        if (url_maps == null) {
            url_maps = new HashMap<String, String>();
        }
        if (newAdapter == null) {
            newAdapter = new NewAdapter(mContext);
        }
        listview.setAdapter(newAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MLog.d("新闻点击:" + position);
                NewModle newModle = listsModles.get(position);
                enterDetailActivity(newModle);
                app.readNews();
            }
        });
        getDataFromNet(index);
        isFirst = false;

    }

    public void enterDetailActivity(NewModle newModle) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("newModle", newModle);
        Class<?> class1;
        if (newModle.getImagesModle() != null && newModle.getImagesModle().getImgList().size() > 1) {
            class1 = ImageDetailActivity.class;
        } else {
            class1 = DetailsActivity.class;
        }
        Intent intent = new Intent(mContext, class1);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 从网络获取数据
     */
    private void getDataFromNet(final int index) {
        if (NetWorkHelper.isNetworkAvailable(mContext)) {
            HttpRequest.getInstance().getNewsData(new NewsDataCallback(mContext) {

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
                public void onError(Call call, Exception e) {
                    if (progressBar != null) {
                        progressBar.setVisibility(View.GONE);
                    }
                    ToastUtils.showLong(mContext, "请求失败！");
                }

                @Override
                public void onResponse(List<NewModle> response) {
                    MLog.d("response=" + response.toString());
                    if (index == 0 && response.size() >= 4) {
                        initSliderLayout(response);
                    } else {
                        newAdapter.appendList(response);
                    }
                    listsModles.addAll(response);
                }
            }, index, Tag.NEWSFRAGMENT);
        } else {
            String str = SharedPreferencesUtils.getString(mContext, "NewsFragment");
            List<NewModle> list = NewListJson.instance(mContext).readJsonNewModles(str,
                    Url.TopId);
            listsModles.addAll(list);
            if (index == 0 && listsModles.size() >= 4) {
                initSliderLayout(listsModles);
            } else {
                newAdapter.appendList(listsModles);
            }
        }
    }

    private void initSliderLayout(List<NewModle> newModles) {
        if (!isNullString(newModles.get(0).getImgsrc()))
            newHashMap.put(newModles.get(0).getImgsrc(), newModles.get(0));
        if (!isNullString(newModles.get(1).getImgsrc()))
            newHashMap.put(newModles.get(1).getImgsrc(), newModles.get(1));
        if (!isNullString(newModles.get(2).getImgsrc()))
            newHashMap.put(newModles.get(2).getImgsrc(), newModles.get(2));
        if (!isNullString(newModles.get(3).getImgsrc()))
            newHashMap.put(newModles.get(3).getImgsrc(), newModles.get(3));

        if (!isNullString(newModles.get(0).getImgsrc()))
            url_maps.put(newModles.get(0).getTitle(), newModles.get(0).getImgsrc());
        if (!isNullString(newModles.get(1).getImgsrc()))
            url_maps.put(newModles.get(1).getTitle(), newModles.get(1).getImgsrc());
        if (!isNullString(newModles.get(2).getImgsrc()))
            url_maps.put(newModles.get(2).getTitle(), newModles.get(2).getImgsrc());
        if (!isNullString(newModles.get(3).getImgsrc()))
            url_maps.put(newModles.get(3).getTitle(), newModles.get(3).getImgsrc());

//        for (String name : url_maps.keySet()) {
//            TextSliderView textSliderView = new TextSliderView(getActivity());
//            textSliderView.setOnSliderClickListener(this);
//            textSliderView
//                    .description(name)
//                    .image(url_maps.get(name));
//            textSliderView.getBundle()
//                    .putString("extra", name);
//            mDemoSlider.addSlider(textSliderView);
//        }
//
//        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Right_Bottom);
//        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        newAdapter.appendList(newModles);
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
    public void onPause() {
        super.onPause();
        OkHttpUtils.getInstance().cancelTag(Tag.NEWSFRAGMENT);
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
