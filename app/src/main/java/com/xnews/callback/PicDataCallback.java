package com.xnews.callback;


import android.content.Context;

import com.xnews.bean.PicuterModle;
import com.xnews.http.json.PicuterSinaJson;
import com.xnews.utils.MLog;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

/**
 * Created by xiao on 2016/1/11.
 */
public abstract class PicDataCallback extends Callback<List<PicuterModle>> {
    private Context mContext;

    public PicDataCallback(Context mContext) {
        this.mContext = mContext;
    }

    public List<PicuterModle> parseNetworkResponse(Response response) throws IOException {
        List<PicuterModle> listsModles = new ArrayList<PicuterModle>();
        String str = response.body().string();
        MLog.d("response.body().string()=" + str);
        List<PicuterModle> list = PicuterSinaJson.instance(mContext).readJsonPhotoListModles(
                str);
        listsModles.addAll(list);
        return listsModles;
    }


}
