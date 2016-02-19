package com.xnews.callback;


import android.content.Context;

import com.xnews.bean.NewModle;
import com.xnews.config.Url;
import com.xnews.http.json.NewListJson;
import com.xnews.utils.MLog;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by xiao on 2016/1/11.
 */
public abstract class NewsDataCallback extends Callback<List<NewModle>> {
    private Context mContext;
    private int index = 0;

    public NewsDataCallback(Context mContext) {
        this.mContext = mContext;

    }

    public List<NewModle> parseNetworkResponse(Response response) throws IOException {
        List<NewModle> listsModles = new ArrayList<NewModle>();
        String str = response.body().string();
        MLog.d("response.body().string()=" + str);
        List<NewModle> list = NewListJson.instance(mContext).readJsonNewModles(str,
                Url.TopId);
        listsModles.addAll(list);
        return listsModles;
    }


}
