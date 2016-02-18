package com.xnews.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

public class GsonUtils {
    public static List<String> getStringList(String jsonData) throws Exception {
        return new Gson().fromJson(jsonData, new TypeToken<List<String>>() {
        }.getType());
    }

    public static <T> T getSingleBean(String jsonData, Class<T> clazz)
            throws Exception {
        return new Gson().fromJson(jsonData, clazz);
    }

    public static <T> List<T> getBeanList(String jsonData) throws Exception {
        return new Gson().fromJson(jsonData, new TypeToken<List<T>>() {
        }.getType());
    }

    public static List<Map<String, Object>> getBeanMapList(String jsonData)
            throws Exception {
        return new Gson().fromJson(jsonData,
                new TypeToken<List<Map<String, Object>>>() {
                }.getType());
    }

    public static String jsonDatetojavaDate(String jsonDate) {
        try {
            JSONObject json = new JSONObject(jsonDate);
            if (json != null) {
                Timestamp s = new Timestamp(json.getInt("year"),
                        json.getInt("month"), json.getInt("date"),
                        json.getInt("hours"), json.getInt("minutes"),
                        json.getInt("seconds"), json.getInt("nanos"));
                SimpleDateFormat format = new SimpleDateFormat(
                        "yyyy-MM-dd HH:mm:ss");
                return format.format(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
