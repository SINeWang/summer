package one.kii.summer.beans.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
public class BasicCopy {

    public static <T> T from(Class<T> klass, Map map) {
        T instance = null;
        try {
            instance = klass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            // ignore
        }
        Field[] fields = klass.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = map.get(field.getName());
            if (value != null) {
                if (field.getType().equals(value.getClass())) {
                    try {
                        field.set(instance, value);
                    } catch (IllegalAccessException e) {
                    }
                } else {
                    if (field.getType().equals(String.class)) {
                        try {
                            field.set(instance, String.valueOf(value));
                        } catch (IllegalAccessException e) {
                        }
                    } else {
                        Object anotherValue = from(field.getType(), value);
                        if (anotherValue == null) {
                            continue;
                        }
                        try {
                            field.set(instance, anotherValue);
                        } catch (IllegalAccessException e) {
                        }
                    }
                }
            }
        }
        return instance;
    }


    public static <T> T from(Class<T> klass, Object src) {
        if (src == null) {
            return null;
        }
        if (klass.isPrimitive() || src.getClass().isPrimitive() || klass.equals(String.class)) {
            T x = primitive(klass, src);
            if (x != null) return x;
        }
        if (src instanceof Map) {
            return from(klass, (Map) src);
        }

        T instance;
        try {
            instance = klass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
        BeanUtils.copyProperties(src, instance);

        return instance;
    }

    private static <T> T primitive(Class<T> klass, Object src) {
        if (klass.equals(String.class)) {
            return (T) String.valueOf(src);
        }
        if (klass.equals(int.class) && src.getClass().equals(Integer.class)) {
            return (T) src;
        }
        if (klass.equals(Integer.class) && src.getClass().equals(int.class)) {
            return (T) src;
        }
        if (klass.equals(boolean.class) && src.getClass().equals(Boolean.class)) {
            return (T) src;
        }
        if (klass.equals(Boolean.class) && src.getClass().equals(boolean.class)) {
            return (T) src;
        }
        if (klass.equals(long.class) && src.getClass().equals(Long.class)) {
            return (T) src;
        }
        if (klass.equals(Long.class) && src.getClass().equals(long.class)) {
            return (T) src;
        }
        if (klass.equals(float.class) && src.getClass().equals(Float.class)) {
            return (T) src;
        }
        if (klass.equals(Float.class) && src.getClass().equals(float.class)) {
            return (T) src;
        }
        if (klass.equals(double.class) && src.getClass().equals(Double.class)) {
            return (T) src;
        }
        if (klass.equals(Double.class) && src.getClass().equals(double.class)) {
            return (T) src;
        }
        if (klass.equals(byte.class) && src.getClass().equals(Byte.class)) {
            return (T) src;
        }
        if (klass.equals(Byte.class) && src.getClass().equals(byte.class)) {
            return (T) src;
        }
        if (klass.equals(char.class) && src.getClass().equals(Character.class)) {
            return (T) src;
        }
        if (klass.equals(Character.class) && src.getClass().equals(char.class)) {
            return (T) src;
        }
        if (klass.equals(short.class) && src.getClass().equals(Short.class)) {
            return (T) src;
        }
        if (klass.equals(Short.class) && src.getClass().equals(short.class)) {
            return (T) src;
        }
        return null;
    }

    public static <T> List<T> from(Class<T> klass, List srcs) {
        List<T> list = new ArrayList<>();
        for (Object src : srcs) {
            if (src != null) {
                T target = from(klass, src);
                list.add(target);
            }
        }
        return list;
    }


}
