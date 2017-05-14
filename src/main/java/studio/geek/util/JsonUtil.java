package studio.geek.util;

/**
 * Created by think on 2017/2/3.
 */

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


/**
 * @李文浩 封装的是使用Gson解析json的方法
 */

public class JsonUtil {

    private final static GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
    private final static Gson gson = gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    private final static JsonParser jsonParser = new JsonParser();

    /**
     * 把一个object变成json字符串,即map类型变为一个json字符串,list变为一个json数组
     *
     * @param object
     * @return
     */

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * 把一个json字符串变成对象
     *
     * @param json
     * @param cls
     * @return
     */
    public static <T> T fromJson(String json, Class<T> cls) {
        return gson.fromJson(json, cls);
    }


    /**
     * 把json字符串变成map
     *
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(String json) {
        Type type = new TypeToken<HashMap<String, Object>>() {
        }.getType();
        return gson.fromJson(json, type);
    }


    /**
     * 把json字符串变成List
     * params: new TypeToken<List<yourbean>>(){}.getType(),
     * new TypeToken<HashMap<String,Object>>() {}.getType()
     * 传入这个参数,可以变成map
     *
     * @param json
     * @param type new TypeToken<List<yourbean>>(){}.getType()
     * @return
     */
    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    private static JsonObject getJsonObject(String json) {
        JsonObject jsonObject = null;
        JsonElement jsonElement = jsonParser.parse(json);
        if (jsonElement.isJsonObject()) {
            jsonObject = jsonElement.getAsJsonObject();
        }
        return jsonObject;
    }

    /**
     * 得到对象的Value(String)
     *
     * @param json 指定的JSON字符串
     * @param key  指定的Key
     * @return
     */
    public static String getValue(String json, String key) {
        JsonObject jsonObject = getJsonObject(json);
        if (jsonObject.isJsonNull() || jsonObject == null) {
            return null;
        } else {
            return String.valueOf(jsonObject.get(key));
        }
    }
    //如果需要多次进行get()操作，可再考虑此方法
//
//    public String getValue(String key) {
//        JsonObject jsonObject = getJsonObject(myJson);
//        if (jsonObject.isJsonNull() || jsonObject == null) {
//            return null;
//        } else {
//            return String.valueOf(jsonObject.getValue(key));
//        }
//    }
}
