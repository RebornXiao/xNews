package com.xnews.utils;

import android.text.TextUtils;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * TextView设置判空工具类
 * Created by xiao on 2015/10/20.
 */
public class TVUtils {
    public static void setText(TextView tv, String str) {
        if (!TextUtils.isEmpty(str)) {
            tv.setText(str);
        }
    }

    public static void setText(TextView tv, double d) {
        String str = toString(d);
        if (!TextUtils.isEmpty(str)) {
            tv.setText(str);
        }
    }

    public static String toYuan(String str) {
        double strD = 0;
        if (StringUtils.isIntOrFloat(str)) {
            strD = Double.parseDouble(str);
        }
//        DecimalFormat decimalFormat = new DecimalFormat("0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String yuan = (int) (strD / 100) + "";//format 返回的是字符串
        return yuan;
    }

    public static String toYuan(double dou) {
//        DecimalFormat decimalFormat = new DecimalFormat("0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String yuan = (int) (dou / 100) + "";//format 返回的是字符串
        return yuan;
    }

    public static String toYuanString(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String str = decimalFormat.format(d / 100);//format 返回的是字符串
        return str;
    }

    public static String toString(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String str = decimalFormat.format(d);//format 返回的是字符串
        return str;
    }


    public static String toString(float d) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String str = decimalFormat.format(d);//format 返回的是字符串
        return str;
    }

    public static String toMin(float d) {
        DecimalFormat decimalFormat = new DecimalFormat("00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String str = decimalFormat.format(d);//format 返回的是字符串
        return str;
    }

    public static String toString1(float d) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String str = decimalFormat.format(d);//format 返回的是字符串
        return str;
    }

    public static String toString4(double d) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String str = decimalFormat.format(d);//format 返回的是字符串
        return str;
    }

    public static String toString4(float d) {
        DecimalFormat decimalFormat = new DecimalFormat("0.0000");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String str = decimalFormat.format(d);//format 返回的是字符串
        return str;
    }
}
