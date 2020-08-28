package com.hu4java.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gson 工具类
 * @author chenzhenhu
 */
public class GsonUtils {


    private static final Gson GSON;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    static {
        GSON = new GsonBuilder().enableComplexMapKeySerialization()
                .setDateFormat(DATE_FORMAT)
                .disableHtmlEscaping()
                .create();
    }

    /**
     * 转成JSON字符串
     * @param src   对象
     * @return      字符串
     */
    public static String toJson(Object src) {
        return GSON.toJson(src);
    }

    /**
     * 转成对象
     * @param json      json字符串
     * @param classOfT  对象类型
     * @param <T>       类型
     * @return          对象
     */
    public static <T> T toBean(String json, Class<T> classOfT) {
        return GSON.fromJson(json, classOfT);
    }

    /**
     * 转成list
     * @param json      json字符串
     * @param classOfT  对象类型
     * @param <T>       类型
     * @return          ArrayList
     */
    public static <T> List<T> toList(String json, Class<T> classOfT) {
        return GSON.fromJson(json, TypeToken.getParameterized(ArrayList.class, classOfT).getType());
    }

    /**
     * 转成Map
     * @param json      json字符串
     * @param classOfT  对象类型
     * @param <T>       类型
     * @return          HashMap
     */
    public static <T> Map<String, T> toMap(String json, Class<T> classOfT) {
        return GSON.fromJson(json, TypeToken.getParameterized(HashMap.class, String.class, classOfT).getType());
    }

}
