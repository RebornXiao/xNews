package com.xnews.callback;


import android.content.Context;

import com.xnews.bean.VideoModle;
import com.xnews.config.Url;
import com.xnews.http.json.ViedoListJson;
import com.xnews.utils.MLog;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by xiao on 2016/1/11.
 */
public abstract class VideoDataCallback extends Callback<List<VideoModle>> {
    private Context mContext;

    public VideoDataCallback(Context mContext) {
        this.mContext = mContext;

    }

    public List<VideoModle> parseNetworkResponse(Response response) throws IOException {
        List<VideoModle> listsModles = new ArrayList<VideoModle>();
        String str = response.body().string();
        MLog.d("response.body().string()=" + str);
        List<VideoModle> list =
                ViedoListJson.instance(mContext).readJsonVideoModles(str,
                        Url.VideoGaoXiaoId);
        listsModles.addAll(list);
        return list;
    }


}
