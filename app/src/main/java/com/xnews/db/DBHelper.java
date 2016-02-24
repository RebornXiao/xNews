package com.xnews.db;

import android.content.Context;

import com.xnews.app.MyApp;

import java.util.List;

import greendao.Person;
import greendao.PersonDao;

/**
 * 数据库操作类
 * Created by xiao on 2016/2/23.
 */
public class DBHelper {
    public static void insertOrUpdate(Context context, Person person) {
        getPersonDao(context).insertOrReplace(person);
    }

    public static void clearPerson(Context context) {
        getPersonDao(context).deleteAll();
    }

    public static void deleteBoxWithId(Context context, long id) {
        getPersonDao(context).delete(getPersonForId(context, id));
    }

    public static Person getPersonForId(Context context, long id) {
        return getPersonDao(context).load(id);
    }

    public static List<Person> GetAllPerson(Context context) {
        return getPersonDao(context).loadAll();
    }

    private static PersonDao getPersonDao(Context c) {
        return ((MyApp) c.getApplicationContext()).getDaoSession().getPersonDao();
    }
}
