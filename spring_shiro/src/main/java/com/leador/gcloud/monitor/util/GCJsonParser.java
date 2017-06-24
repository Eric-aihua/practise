package com.leador.gcloud.monitor.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

public class GCJsonParser {

  /**
   * 将对象转换为Json字符串
   * 
   * @param obj
   * @return
   */
  public static String object2Json(Object obj) {
    JSONObject jsonObject = new JSONObject();
    if (obj != null) {
      return jsonObject.fromObject(obj).toString();
    }
    return null;
  }

  /**
   * 将集合转换成Json对象
   * 
   * @param objectCollection 对象集合
   */
  public static JSONArray objCollection2JsonArray(Collection<?> objectCollection) {
    if (!objectCollection.isEmpty()) {
      return JSONArray.fromObject(objectCollection);
    }
    return new JSONArray();
  }

  /**
   * 将对象数组转换成Json对象
   * 
   * @param objectArray 对象集合
   */
  public static JSONArray object2JsonArray(Object objectArray) {
    return JSONArray.fromObject(objectArray);
  }

  /**
   * 將Json字符串转换为指定的对象
   * 
   * @param jsonStr
   * @param objClass
   * @return
   */
  public static Object jsonStr2Object(String jsonStr, Class<?> objClass) {
    JSONObject jsonObject = new JSONObject();
    if (StringUtils.isNotBlank(jsonStr)) {
      jsonObject = JSONObject.fromObject(jsonStr);
      return JSONObject.toBean(jsonObject, objClass);
    }
    return null;
  }

  /**
   * 将Json字符串转换成JSONObject
   * 
   * @param jsonString Json数据字符串
   */
  public static JSONObject jsonStr2JsonObj(String jsonString) {
    if (StringUtils.isNotBlank(jsonString)) {
      return JSONObject.fromObject(jsonString);
    }
    return new JSONObject();
  }

  /**
   * 将Json字符串转换成JSONObject
   * 
   * @param jsonString Json数据字符串
   */
  public static JSONArray jsonStr2JsonArray(String jsonString) {
    if (StringUtils.isNotBlank(jsonString)) {
      return JSONArray.fromObject(jsonString);
    }
    return new JSONArray();
  }

  /**
   * 将Json字符串转换成ObjectList
   * 
   * @param jsonString Json数据字符串
   */
  public static List<?> jsonStr2ObjList(String jsonString, Class<?> cls) {
    if (StringUtils.isNotBlank(jsonString)) {
      JSONArray jsonArray = JSONArray.fromObject(jsonString);
      List<?> list = JSONArray.toList(jsonArray, cls);
      return list;
    }
    return new ArrayList();
  }

  /**
   * 将Json字符串转换成ObjectMap
   * 
   * @param jsonString Json数据字符串
   */
  public static Map<String, Object> jsonStr2ObjMap(String jsonString, Map<String, Class<?>> clazzMap) {
    Map<String, Object> destMap = new HashMap<String, Object>();
    if (StringUtils.isNotBlank(jsonString)) {
      JSONObject jsonObject = JSONObject.fromObject(jsonString);
      Map<String, ?> mapBean = (Map) JSONObject.toBean(jsonObject, Map.class, clazzMap);
      Set<String> set = mapBean.keySet();
      Iterator<String> iter = set.iterator();
      while (iter.hasNext()) {
        String key = iter.next();
        destMap.put(key, mapBean.get(key));

      }
    }
    return destMap;
  }



  /**
   * 将MAP转换为Json字符串
   * 
   * @param obj
   * @return
   */
  public static String map2Json(Map<?, ?> map) {
    return object2Json(map);
  }

}
