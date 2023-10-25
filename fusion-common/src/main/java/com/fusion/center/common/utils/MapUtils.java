package com.fusion.center.common.utils;

import cn.hutool.core.map.MapUtil;
import com.google.common.collect.Maps;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Description: MapUtils
 * @Author: haoxd
 * @Version: 1.0
 */
public class MapUtils extends MapUtil {

    /**
     * Transform to string map.
     *
     * @param map source map
     * @return string map
     */
    public static Map<String, String> transStringMap(final Map<String, Object> map) {
        return Optional.ofNullable(map)
                .map(m -> m.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> Objects.toString(e.getValue(), null))))
                .orElse(null);
    }

    /**
     * This is jdk8 performance bug, see: https://bugs.openjdk.java.net/browse/JDK-8161372.
     *
     * @param map source map
     * @param key key
     * @param mappingFunction mappingFunction
     * @param <K> k
     * @param <V> v
     * @return v
     */
    public static <K, V> V computeIfAbsent(final Map<K, V> map, final K key, final Function<? super K, ? extends V> mappingFunction) {
        V v = map.get(key);
        if (Objects.nonNull(v)) {
            return v;
        }
        return map.computeIfAbsent(key, mappingFunction);
    }

    /**
     * 将  Map<String, String>  value 为null 转换为 “”
     *
     * @param map
     * @return Map<String                                                                                                                               ,                                                                                                                                                                                                                                                               String>
     **/
    public static Map<String, String> operNullValueToEmpty(Map<String, String> map) {

        Map<String, String> maps = Maps.newHashMapWithExpectedSize(map.size());

        map.forEach((k, v) -> {

                    if (StringUtils.equals(v, "null")) {
                        v = StringUtils.EMPTY;
                    }
                    maps.put(k, StringUtils.blankToDefault(v, StringUtils.EMPTY));
                }


        );

        return maps;
    }

    /**
     * 将  Map<String, String>  value 为null 的 key-value 删除
     *
     * @param map
     * @return Map<String                                                                                                                               ,                                                                                                                                                                                                                                                               String>
     **/
    public static Map<String, Object> operNullValueRemoveKey(Map<String, Object> map) {

        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            Object o = entry.getValue();

            if (o instanceof String) {
                if (StringUtils.isBlank(String.class.cast(entry.getValue()))) {
                    it.remove();
                }
            } else {
                if (Objects.isNull(o)) {
                    it.remove();
                }
            }

        }

        return map;
    }


    /**
     * 从map集合中获取属性值
     *
     * @param <E>
     * @param map          map集合
     * @param key          键对
     * @param defaultValue 默认值
     * @return
     */
    public static <E> E get(Map map, Object key, E defaultValue) {
        Object o = map.get(key);
        if (o == null) {
            return defaultValue;
        }
        return (E) o;
    }

    public static String getStr(Map<?, ?> map, Object key, String defaultValue) {
        Object o = map.get(key);
        if (o == null) {
            return defaultValue;
        }
        return (String) o;
    }
    /**
     * <p>如果存入map的值是空,null，不进行执行put操作</p>
     *
     * null      = false
     * ("")        = false
     * (" ")       = false
     * ("bob")     = true
     * ("  bob  ") = true
     *
     * @param key
     * @param map
     * @param value
     */
   public static void notPutEmptyValue(Map map , Object key, Object value){

       if (value instanceof String) {
            if(StringUtils.isNotBlank(String.class.cast(value))){
                map.put(key,value);
            }
       } else {
           if (Objects.nonNull(value)) {
               map.put(key,value);
           }
       }

   }


    /**
     * 通过path设置Map<String,Map> 容器中的value
     *
     * @param key       path方式，例如：request/reqHeade/status
     * @param container map容器
     * @return 被设置值的新容器
     */
    public static Map<String, Object> put(String key, Object value, Map<String, Object> container) {
        if (null == container) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(key, "/");
        String mapKey = null;
        Object childObject = null;
        Map parentMap = null;
        while (st.hasMoreTokens()) {
            mapKey = st.nextToken();
            if (org.apache.commons.lang3.StringUtils.isEmpty(mapKey)) {
                return container;
            }
            parentMap = null != childObject ? (Map) ((Map) childObject) : container;
            childObject = parentMap.get(mapKey);
            if (!st.hasMoreElements()) {
                if (value instanceof Map && childObject instanceof Map) {
                    ((Map) value).putAll((Map) childObject);
                }
                parentMap.put(mapKey, value);
                break;
            }

            if (null == childObject) {
                childObject = new HashMap<String, Object>();
                parentMap.put(mapKey, childObject);
            }
            if (!(childObject instanceof Map)) {
                return null;
            }
        }
        return container;
    }

    /**
     * 通过path方式获取Map<String,Map> 容器中的value
     *
     * @param key       path方式，例如：request/reqHeade/status
     * @param container map容器
     * @return path指定在value中的值
     */
    public static <T> T get(String key, Map<String, Object> container) {
        if (org.apache.commons.lang3.StringUtils.isEmpty(key) || null == container) {
            return null;
        }
        StringTokenizer st = new StringTokenizer(key, "/");
        String mapKey = null;
        Object childObject = null;
        Map parentMap = null;
        while (st.hasMoreTokens()) {
            mapKey = st.nextToken();
            parentMap = null != childObject ? (Map) ((Map) childObject) : container;
            childObject = parentMap.get(mapKey);
            if (null != childObject && !st.hasMoreElements()) {
                return (T) childObject;
            }

        }
        return null;
    }




}
