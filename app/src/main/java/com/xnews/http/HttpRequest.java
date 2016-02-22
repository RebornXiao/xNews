package com.xnews.http;

import com.xnews.callback.NewsDataCallback;
import com.xnews.callback.PicDataCallback;
import com.xnews.callback.VideoDataCallback;
import com.xnews.config.Configure;
import com.xnews.config.Url;
import com.xnews.utils.MLog;
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
    public static void getNewsData(final NewsDataCallback callBack, int index) {
        String url = Url.TopUrl + Url.TopId + "/" + index + Url.endUrl;
        MLog.d("新闻接口=" + url);
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
    public static void getVideoData(final VideoDataCallback callBack, int index) {
        String url = "http://c.3g.163.com/nc/video/list/V9LG4B3A0/n/" + index + "-10.html";
//        Url.Video + videoId + Url.VideoCenter + index + Url.videoEndUrl;
        MLog.d("视频接口=" + url);

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
        MLog.d("图片接口=" + url);
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(callBack);
    }


}
