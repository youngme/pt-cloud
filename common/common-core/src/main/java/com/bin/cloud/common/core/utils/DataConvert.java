package com.bin.cloud.common.core.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

/**
 * @Description 数据转换
 * @Author hubin
 * @Date 2019-09-18 15:05
 * @Version 1.0
 **/
public class DataConvert {
    /**
     * 合并(基于反射) 将给定源bean的属性值(不为null)覆盖到给定目标bean中,只要属性匹配
     *
     * @param source 源bean
     * @param target 目标bean
     */
    public static void mergeNotNullReflect(Object source, Object target) {
        if (Objects.nonNull(source) && Objects.nonNull(target)) {
            Class oldClass = source.getClass();
            Class newClass = target.getClass();
            convert(oldClass, newClass, source, target);
        }

    }

    public static<T> T mergeNotNullReflect(Object source, Class<T> targetClazz) {
        if (Objects.nonNull(source)) {
            Class oldClass = source.getClass();
            try {
                T target = targetClazz.newInstance();
                Class newClass = target.getClass();
                convert(oldClass, newClass, source, target);
                return target;
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static void convert(Class oldClass, Class newClass, Object source, Object target) {
        Field[] oldFields = oldClass.getDeclaredFields();
        Arrays.stream(oldFields).filter(field -> {
                    field.setAccessible(true);
                    try {
                        return Objects.nonNull(field.get(source));
                    } catch (IllegalAccessException e) {
                        return Boolean.FALSE;
                    }
                }
        ).forEach(field -> {
            try {
                Field newField = newClass.getDeclaredField(field.getName());
                if (Objects.nonNull(newField)) {
                    newField.setAccessible(true);
                    newField.set(target, field.get(source));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e){

            }
        });
    }
}
