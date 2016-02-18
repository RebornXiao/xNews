package com.xnews.callback;

import com.xnews.utils.StringUtils;
import com.zhy.http.okhttp.callback.Callback;

/**
 * Created by xiao on 2016/2/17.
 */
public abstract class BaseCallback<T> extends Callback<T> {
    public boolean isNullString(String imgUrl) {

        if (StringUtils.isEmpty(imgUrl)) {
            return true;
        }
        return false;
    }
}
