package com.chat.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class BeanUtils {

    /**
     * 将一个对象的值拷贝给另一个对象对应的值
     *
     * @param target 目标对象
     * @param source 源对象
     */
    public static void copyValue(Object target, Object source) throws IllegalAccessException {

        Map<String, Object> valueMap = new HashMap<String, Object>();

        //通过反射获取源文件的值
        Class sourceClass = source.getClass();
        Field[] fields = sourceClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            valueMap.put(field.getName(), field.get(source));
        }
        //将值赋给目标文件
        Class targetClass = target.getClass();
        Field[] targetFields = targetClass.getDeclaredFields();
        for (int i = 0; i < targetFields.length; i++) {
            Field field = targetFields[i];
            field.setAccessible(true);
            Object value = valueMap.get(field.getName());
            field.set(target, value);

        }

    }
}
