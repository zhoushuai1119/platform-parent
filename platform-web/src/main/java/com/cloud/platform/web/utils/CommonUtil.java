package com.cloud.platform.web.utils;

import com.cloud.platform.common.constants.PlatformCommonConstant;
import com.cloud.platform.common.utils.JsonUtil;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.objenesis.instantiator.util.ClassUtils;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
public class CommonUtil {

    private CommonUtil() {

    }

    /**
     * 判断字符串不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return StringUtils.isNotBlank(str);
    }

    /**
     * 判断字符串为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return StringUtils.isBlank(str);
    }

    /**
     * 判断集合不为空
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection collection) {
        return CollectionUtils.isNotEmpty(collection);
    }

    /**
     * 判断集合为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断Map不为空
     *
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map map) {
        return MapUtils.isNotEmpty(map);
    }

    /**
     * 判断Map为空
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map map) {
        return MapUtils.isEmpty(map);
    }


    /**
     * 对象字符串转换为List集合
     */
    public static <T> List<T> strToList(String str, Class<T> clazz) {
        return JsonUtil.toList(str);
    }

    /**
     * 对象字符串转换为对象
     */
    public static <T> T strToBean(String str, Class<T> clazz) {
        return JsonUtil.toBean(str, clazz);
    }

    /**
     * 对象转Map
     *
     * @param bean
     * @return
     */
    public static Map<String, Object> beanToMap(Object bean) {
        return Objects.isNull(bean) ? null : BeanMap.create(bean);
    }

    /**
     * map转对象
     *
     * @param map
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map<String, ?> map, Class<T> clazz) {
        T bean = ClassUtils.newInstance(clazz);
        BeanMap.create(bean).putAll(map);
        return bean;
    }


    /**
     * List<Bean> 转 list<Map>
     *
     * @param beans
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, Object>> beansToMaps(List<T> beans) {
        if (CollectionUtils.isEmpty(beans)) {
            return beans.stream().map(CommonUtil::beanToMap).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


    /**
     * list<Map> 转 List<Bean>
     *
     * @param maps
     * @param <T>
     * @return
     */
    public static <T> List<T> mapsToBeans(List<? extends Map<String, ?>> maps, Class<T> clazz) {
        if (CollectionUtils.isNotEmpty(maps)) {
            maps.stream().map(e -> {
                return mapToBean(e, clazz);
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    /**
     * 字符串转数组
     *
     * @param str
     * @return
     */
    public static String[] strToArray(String str) {
        return str.split(PlatformCommonConstant.SymbolParam.COMMA);
    }


    /**
     * 字符串转Map
     *
     * @param mapStr -> "John=first,Adam=second"
     * @return
     */
    public static Map strToMap(String mapStr) {
        return Splitter.on(PlatformCommonConstant.SymbolParam.COMMA)
                .withKeyValueSeparator(PlatformCommonConstant.SymbolParam.EQUAL)
                .split(mapStr);
    }

    /**
     * 字符串转List<String>
     *
     * @param str
     * @return
     */
    public static List<String> strToListStr(String str) {
        return Splitter.on(PlatformCommonConstant.SymbolParam.COMMA)
                .trimResults()
                .omitEmptyStrings()
                .splitToList(str);
    }

    /**
     * 数组转字符串
     *
     * @param arrays
     * @return
     */
    public static String arrayToStr(String[] arrays) {
        return StringUtils.join(arrays, PlatformCommonConstant.SymbolParam.COMMA);
    }

    /**
     * 集合转字符串
     *
     * @param list
     * @return
     */
    public static String listToStr(List<String> list) {
        return Joiner.on(PlatformCommonConstant.SymbolParam.COMMA)
                .skipNulls()
                .join(list);
    }

    /**
     * 数组转集合
     *
     * @param array
     * @return
     */
    public static List<String> arrayToList(String[] array) {
        return Arrays.asList(array);
    }

    /**
     * 集合转数组
     *
     * @param list
     * @return
     */
    public static String[] listToArray(List<String> list) {
        String[] str = new String[list.size()];
        return list.toArray(str);
    }

    /**
     * 对象转json字符串
     *
     * @param object
     * @return
     */
    public static String objectToStr(Object object) {
        return JsonUtil.toString(object);
    }

    /**
     * 输出某字符在字符串中出现的次数
     *
     * @param str
     * @param chr
     * @return
     */
    public static Integer countMatches(String str, String chr) {
        return StringUtils.countMatches(str, chr);
    }

    /**
     * 将map型转为请求参数型
     *
     * @param data
     * @return
     */
    public static String urlEncode(Map<String, String> data) {
        StringBuilder sb = new StringBuilder();
        data.forEach((k, v) -> {
            sb.append(k).append("=").append(v).append("&");
        });
        String result = sb.toString();
        result = result.substring(0, result.lastIndexOf("&"));
        return result;
    }

    /**
     * 功能描述: 当表达式为true时执行，否则不执行
     *
     * @param arg      参数
     * @param consumer 执行函数
     */
    public static <T> void doWhenTrue(Boolean expression, T arg, Consumer<T> consumer) {
        Objects.requireNonNull(consumer);
        if (expression) {
            consumer.accept(arg);
        }
    }

}
