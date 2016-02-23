package com.xnews.callback;


import android.content.Context;

import com.xnews.bean.NewDetailModle;
import com.xnews.http.json.NewDetailJson;
import com.xnews.utils.SharedPreferencesUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * 新闻详情信息
 * Created by xiao on 2016/1/11.
 */
public abstract class NewsDetailCallback extends Callback<NewDetailModle> {
    private Context mContext;
    private String newID;
    private String newUrl;

    public NewsDetailCallback(Context mContext, String newID, String newUrl) {
        this.mContext = mContext;
        this.newID = newID;
        this.newUrl = newUrl;
    }

    public NewDetailModle parseNetworkResponse(Response response) throws IOException {
        String str = response.body().string();
        SharedPreferencesUtils.putString(mContext, newUrl, str);
        NewDetailModle newDetailModle = NewDetailJson.instance(mContext).readJsonNewModles(str,
                newID);
        if (newDetailModle == null) {
            return null;
        }
        return newDetailModle;
    }
}
