package com.xnews.http;

import com.xnews.callback.ReturnCallback;
import com.xnews.config.Configure;
import com.zhy.http.okhttp.OkHttpUtils;


/**
 * Created by xiao on 2016/1/11.
 */
public class HttpRequest {
    private static HttpRequest instance;
    private Thread mainThread;

    public static HttpRequest getInstance() {
        if (instance == null) {
            synchronized (HttpRequest.class) {
                if (instance == null) {
                    instance = new HttpRequest();
                }
            }
        }
        return instance;
    }

    private HttpRequest() {
        mainThread = Thread.currentThread();
    }

    public static void getNewsData(final ReturnCallback callBack) {
        String url = Configure.NEW_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(callBack);
    }
}
