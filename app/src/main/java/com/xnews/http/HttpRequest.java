package com.xnews.http;

import com.xnews.callback.NewsDataCallback;
import com.xnews.callback.PicDataCallback;
import com.xnews.callback.VideoDataCallback;
import com.xnews.config.Configure;
import com.zhy.http.okhttp.OkHttpUtils;


/**
 * 网络请求
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

    /**
     * 获取新闻客户端数据
     *
     * @param callBack
     */
    public static void getNewsData(final NewsDataCallback callBack) {
        String url = Configure.NEW_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(callBack);
    }

    /**
     * 获取搞笑视频数据
     *
     * @param callBack
     */
    public static void getVideoData(final VideoDataCallback callBack) {
        String url = Configure.VIDEO_URL;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(callBack);
    }

    /**
     * 获取图片数据
     *
     * @param callBack
     */
    public static void getPicData(final PicDataCallback callBack, int index) {
        String url = Configure.PIC_URL + index;
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(callBack);
    }

}
