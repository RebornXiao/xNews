package com.xnews.http;

import android.content.Context;

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

    public static void recharge(Context context, String str3, String str5,
                                String str6, String str7, String str8,
                                final ReturnCallback callBack) {
//        MyApp application = (MyApp) context.getApplicationContext();
//        RechargeMessage mRechargeMessage = new RechargeMessage(
//                application.getUserInfo().getUserid(), "", str3, application.getUserInfo().getEquipmentId(), str5,
//                str6, str7, str8);
//        Map<String, String> params = new HashMap<String, String>();
//        try {
//            params = BeanToMapUtil.PO2Map(mRechargeMessage);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        String url = "http://app.xzyd88.cn/strip/notice_clearing.capi";
        OkHttpUtils
                .get()
                .url(url)
                .params(null)
                .build()
                .execute(callBack);
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
