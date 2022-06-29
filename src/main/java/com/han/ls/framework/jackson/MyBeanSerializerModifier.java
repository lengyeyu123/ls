package com.han.ls.framework.jackson;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * 此modifier主要做的事情为：
 * 1.当序列化类型为数组集合时，当值为null时，序列化成[]
 * 2.String类型值序列化为""
 */
public class MyBeanSerializerModifier extends BeanSerializerModifier {

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        // 循环所有的beanPropertyWriter
        for (BeanPropertyWriter writer : beanProperties) {
            // 判断字段的类型，如果是数组或集合则注册nullASerializer
            if (isArrayType(writer)) {
                // 给writer注册一个自己的nullSerializer
                writer.assignNullSerializer(new CustomizeNullJsonSerializer.NullArrayJsonSerializer());
            } else if (isStringType(writer)) {
                writer.assignNullSerializer(new CustomizeNullJsonSerializer.NullStringJsonSerializer());
            } else if (isNumberType(writer)) {
                writer.assignNullSerializer(new CustomizeNullJsonSerializer.NullNumberJsonSerializer());
            } else if (isBooleanType(writer)) {
                writer.assignNullSerializer(new CustomizeNullJsonSerializer.NullBooleanJsonSerializer());
            } else if (isDateType(writer)) {
                writer.assignNullSerializer(new CustomizeNullJsonSerializer.NullDateJsonSerializer());
            } else {
                writer.assignNullSerializer(new CustomizeNullJsonSerializer.NullObjectJsonSerializer());
            }
        }
        return beanProperties;
    }

    /**
     * 是否是Date
     */
    private boolean isDateType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return Date.class.isAssignableFrom(clazz) || LocalDate.class.isAssignableFrom(clazz) || LocalDateTime.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是数组
     */
    private boolean isArrayType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.isArray() || Collection.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是String
     */
    private boolean isStringType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return CharSequence.class.isAssignableFrom(clazz) || Character.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是数值类型
     */
    private boolean isNumberType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return Number.class.isAssignableFrom(clazz);
    }

    /**
     * 是否是boolean
     */
    private boolean isBooleanType(BeanPropertyWriter writer) {
        Class<?> clazz = writer.getType().getRawClass();
        return clazz.equals(Boolean.class);
    }
}
