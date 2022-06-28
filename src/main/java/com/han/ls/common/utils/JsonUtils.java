package com.han.ls.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    static {
        /*
          默认非空不输出，时间格式
         */
        objectMapper.setDateFormat(new SimpleDateFormat(DateUtils.YYYY_MM_DD_HH_MM_SS));
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }

    @SneakyThrows
    public static String toJson(Object bean) {
        return objectMapper.writeValueAsString(bean);
    }

    @SneakyThrows
    public static <T> T json2Bean(String json, Class<T> beanType) {
        return objectMapper.readValue(json, beanType);
    }

    @SneakyThrows
    public static List<String> josn2StrList(String str) {
        return objectMapper.readValue(str, new TypeReference<List<String>>() {
        });
    }

    @SneakyThrows
    public static <T> List<T> json2BeanList(String str, Class<T> beanType) {
        // JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, beanType);
        CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class, beanType);
        return objectMapper.readValue(str, collectionType);
    }

}
