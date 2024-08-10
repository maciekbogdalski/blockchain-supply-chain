package org.example.planttrackingclient;

import com.owlike.genson.Genson;

public class JsonUtil {
    private static final Genson genson = new Genson();

    public static String serialize(Object obj) {
        return genson.serialize(obj);
    }

    public static <T> T deserialize(String json, Class<T> clazz) {
        return genson.deserialize(json, clazz);
    }
}
