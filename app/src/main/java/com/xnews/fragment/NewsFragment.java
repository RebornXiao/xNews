package com.xnews.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.xnews.R;
import com.xnews.adapter.NewAdapter;
import com.xnews.base.BaseFragment;
import com.xnews.bean.NewModle;
import com.xnews.callback.ReturnCallback;
import com.xnews.http.HttpRequest;
import com.xnews.utils.MLog;
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
 * 新闻
 * Created by xiao on 2016/2/16.
 */
public class NewsFragment extends BaseFragment {
    @Bind(R.id.listview)
    SwipeListView listview;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    private int index = 0;
    private NewAdapter newAdapter;
    private List<NewModle> listsModles;
    private Map<String, NewModle> newHashMap;
    protected Map<String, String> url_maps;

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
        if (newAdapter == null) {
            newAdapter = new NewAdapter(mContext);
        }
        listview.setAdapter(newAdapter);
        if (listsModles == null) {
            listsModles = new ArrayList<NewModle>();
        }
        if (newHashMap == null) {
            newHashMap = new HashMap<String, NewModle>();
        }
        if (url_maps == null) {
            url_maps = new HashMap<String, String>();
        }
        HttpRequest.getInstance().getNewsData(new ReturnCallback(mContext) {

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAfter() {
                super.onAfter();
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onError(Request request, Exception e) {
                progressBar.setVisibility(View.GONE);
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
            }
        });
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

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
