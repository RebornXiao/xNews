package com.xnews.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    public static void show(final Context context, final String msg,
                            final int duaration) {
        if ("main".equalsIgnoreCase(Thread.currentThread().getName())) {
            Toast.makeText(context, msg, duaration).show();
        } else {
            ((Activity) context).runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(context, msg, duaration).show();
                }
            });
        }
    }

    public static void showShort(final Context context, final String msg) {
        if ("main".equalsIgnoreCase(Thread.currentThread().getName())) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        } else {
            ((Activity) context).runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public static void showLong(final Context context, final String msg) {
        if ("main".equalsIgnoreCase(Thread.currentThread().getName())) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
        } else {
            ((Activity) context).runOnUiThread(new Runnable() {

                @Override
                public void run() {
                    Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
