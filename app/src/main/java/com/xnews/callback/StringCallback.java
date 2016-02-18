package com.xnews.callback;

import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by xiao on 2016/1/12.
 */
public abstract class StringCallback extends Callback {
    public String parseNetworkResponse(Response response) throws IOException {
        return response.body().toString();
    }

}