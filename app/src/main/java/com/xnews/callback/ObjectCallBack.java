package com.xnews.callback;

import java.lang.reflect.ParameterizedType;

/**
 * Created by xiao on 2016/1/11.
 */
public abstract class ObjectCallBack<T> {
    private Class<T> clazz;

    @SuppressWarnings("unchecked")
    public ObjectCallBack() {
        ParameterizedType type = (ParameterizedType) this.getClass()
                .getGenericSuperclass();
        this.clazz = (Class<T>) type.getActualTypeArguments()[0];
    }

    public abstract void onSuccess(T t);

    public abstract void onError(int error, String msg);

    public Class<T> getClazz() {
        return clazz;
    }

//    public boolean isNullString(String imgUrl) {
//
//        if (StringUtils.isEmpty(imgUrl)) {
//            return true;
//        }
//        return false;
//    }
}
