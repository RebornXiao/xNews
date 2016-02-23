package com.xnews.callback;


import android.content.Context;

import com.xnews.bean.PicuterDetailModle;
import com.xnews.http.json.PicuterSinaJson;
import com.xnews.utils.SharedPreferencesUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;

/**
 * 图片详情信息
 * Created by xiao on 2016/1/11.
 */
public abstract class PicDetailCallback extends Callback<List<PicuterDetailModle>> {
    private Context mContext;
    private String picUrl;

    public PicDetailCallback(Context mContext, String picUrl) {
        this.mContext = mContext;
        this.picUrl = picUrl;
    }

    public List<PicuterDetailModle> parseNetworkResponse(Response response) throws IOException {
        String str = response.body().string();
        SharedPreferencesUtils.putString(mContext, picUrl, str);
        List<PicuterDetailModle> list = PicuterSinaJson.instance(mContext).readJsonPicuterModle(
                str);
        return list;
    }
}
