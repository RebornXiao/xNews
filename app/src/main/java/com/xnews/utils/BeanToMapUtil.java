package com.xnews.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by xiao on 2015/12/11.
 */
public class BeanToMapUtil {
//
//    /**
//     * 将一个 Map 对象转化为一个 JavaBean
//     *
//     * @param type 要转化的类型
//     * @param map  包含属性值的 map
//     * @return 转化出来的 JavaBean 对象
//     * @throws IntrospectionException    如果分析类属性失败
//     * @throws IllegalAccessException    如果实例化 JavaBean 失败
//     * @throws InstantiationException    如果实例化 JavaBean 失败
//     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
//     */
//    public static Object convertMap(Class type, Map map)
//            throws IntrospectionException, IllegalAccessException,
//            InstantiationException, InvocationTargetException {
//        BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
//        Object obj = type.newInstance(); // 创建 JavaBean 对象
//
//        // 给 JavaBean 对象的属性赋值
//        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//        for (int i = 0; i < propertyDescriptors.length; i++) {
//            PropertyDescriptor descriptor = propertyDescriptors[i];
//            String propertyName = descriptor.getName();
//
//            if (map.containsKey(propertyName)) {
//                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
//                Object value = map.get(propertyName);
//
//                Object[] args = new Object[1];
//                args[0] = value;
//
//                descriptor.getWriteMethod().invoke(obj, args);
//            }
//        }
//        return obj;
//    }
//
//    /**
//     * 将一个 JavaBean 对象转化为一个  Map
//     *
//     * @param bean 要转化的JavaBean 对象
//     * @return 转化出来的  Map 对象
//     * @throws IntrospectionException    如果分析类属性失败
//     * @throws IllegalAccessException    如果实例化 JavaBean 失败
//     * @throws InvocationTargetException 如果调用属性的 setter 方法失败
//     */
//    public static Map convertBean(Object bean)
//            throws IntrospectionException, IllegalAccessException, InvocationTargetException {
//        Class type = bean.getClass();
//        Map returnMap = new HashMap();
//        BeanInfo beanInfo = Introspector.getBeanInfo(type);
//
//        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//        for (int i = 0; i < propertyDescriptors.length; i++) {
//            PropertyDescriptor descriptor = propertyDescriptors[i];
//            String propertyName = descriptor.getName();
//            if (!propertyName.equals("class")) {
//                Method readMethod = descriptor.getReadMethod();
//                Object result = readMethod.invoke(bean, new Object[0]);
//                if (result != null) {
//                    returnMap.put(propertyName, result);
//                } else {
//                    returnMap.put(propertyName, "");
//                }
//            }
//        }
//        return returnMap;
//    }

    /**
     * Json 转成 Map<>
     *
     * @param jsonStr
     * @return
     */
    public static Map<String, String> getMapForJson(String jsonStr) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonStr);
            Iterator<String> keyIter = jsonObject.keys();
            String key;
            String value;
            Map<String, String> valueMap = new HashMap<String, String>();
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = jsonObject.getString(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Json 转成 List<Map<>>
     *
     * @param jsonStr
     * @return
     */
    public static List<Map<String, String>> getlistForJson(String jsonStr) {
        List<Map<String, String>> list = null;
        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            JSONObject jsonObj;
            list = new ArrayList<Map<String, String>>();
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObj = (JSONObject) jsonArray.get(i);
                list.add(getMapForJson(jsonObj.toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Map<String, String> PO2Map(Object o) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        Field[] fields = null;
        String clzName = o.getClass().getSimpleName();
        MLog.e("类：" + o.getClass().getName());
        fields = o.getClass().getDeclaredFields();
        MLog.e("***" + clzName + "转map开始****");
        for (Field field : fields) {
            field.setAccessible(true);
            String proName = field.getName();
            String proValue = (String) field.get(o);
            map.put(proName.toUpperCase(), proValue);
            MLog.e("key：" + proName + "value:" + proValue);
        }
        MLog.e("***" + clzName + "转map结束****");
        return map;
    }

//    public static AjaxParams PO2Params(Object o) throws Exception {
//        AjaxParams params = new AjaxParams();
//        Field[] fields = null;
//        String clzName = o.getClass().getSimpleName();
//        MLog.e("类：" + o.getClass().getName());
//        fields = o.getClass().getDeclaredFields();
//        MLog.e("***" + clzName + "转params开始****");
//        for (Field field : fields) {
//            field.setAccessible(true);
//            String proName = field.getName();
//            String proValue = (String) field.get(o);
//            params.put(proName.toUpperCase(), proValue);
//            MLog.e("key：" + proName + "value:" + proValue);
//        }
//        MLog.e("***" + clzName + "转params结束****");
//        return params;
//    }


    public static Object map2PO(Map<String, Object> map, Object o) throws Exception {
        if (!map.isEmpty()) {
            for (String k : map.keySet()) {
                Object v = "";
                if (!k.isEmpty()) {
                    v = map.get(k);
                }
                Field[] fields = null;
                fields = o.getClass().getDeclaredFields();
                String clzName = o.getClass().getSimpleName();
                MLog.e("类：" + o.getClass().getName());
                MLog.e("***map转" + clzName + "开始****");
                for (Field field : fields) {
                    int mod = field.getModifiers();
                    if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                        continue;
                    }
                    if (field.getName().toUpperCase().equals(k)) {
                        field.setAccessible(true);
                        field.set(o, v);
                        MLog.e("key：" + k + "value:" + v);
                    }

                }
                MLog.e("***map转" + clzName + "结束****");
            }
        }
        return o;
    }
}
