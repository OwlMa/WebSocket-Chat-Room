package com.chatroom.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public final class JsonUtil {

    /**
     * convert java object to the json string
     *
     * @param object which need to be converted
     * @return json string or null
     */
    public static String parseObjToJson(Object object) {
        String string = null;
        try {
            string = JSONObject.toJSONString(object);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return string;
    }

    /**
     * convert json string to the java object
     *
     * @param json json string
     * @param c    corresponding object
     */
    public static <T> T parseJsonToObj(String json, Class<T> c) {
        try {
            JSONObject jsonObject = JSON.parseObject(json);
            return JSON.toJavaObject(jsonObject, c);
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
        return null;
    }
}
