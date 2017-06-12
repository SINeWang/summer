package one.kii.summer.io.validator;

import one.kii.summer.io.annotations.MayHave;
import one.kii.summer.io.exception.BadRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangYanJiong on 20/04/2017.
 */
public class NotBadRequest {


    public static <T> T from(T object, String... fieldsName) throws BadRequest {
        if (object == null) {
            throw new NullPointerException();
        }
        if (fieldsName == null) {
            throw new NullPointerException();
        }
        if (fieldsName.length == 0) {
            return object;
        }
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        Class type = object.getClass();
        for (String fieldName : fieldsName) {
            if (fieldName == null) {
                continue;
            }
            Field field = null;
            try {
                field = type.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                map.put(field.getName(), null);
            }
            if (field != null) {
                checkValueNotEmpty(object, map, field);
            }
        }
        if (map.size() > 0) {
            throw new BadRequest(map);
        }
        return object;
    }


    public static <T> T from(T object) throws BadRequest {
        if (object == null) {
            throw new NullPointerException();
        }
        Class type = object.getClass();
        Field[] fields = type.getDeclaredFields();
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Field field : fields) {
            Annotation mayHave = field.getAnnotation(MayHave.class);
            if (mayHave != null) {
                continue;
            }
            checkValueNotEmpty(object, map, field);
        }
        if (map.size() > 0) {
            throw new BadRequest(map);
        }
        return object;
    }


    private static void checkValueNotEmpty(Object object, MultiValueMap<String, String> map, Field field) {
        Object value;
        try {
            field.setAccessible(true);
            value = field.get(object);
        } catch (IllegalAccessException e) {
            String fieldName = field.getName();
            char first = Character.toUpperCase(fieldName.charAt(0));
            String getMethodName = "get" + first + fieldName.substring(1);
            Method method;
            try {
                method = object.getClass().getMethod(getMethodName);
            } catch (NoSuchMethodException ignore) {
                map.put(field.getName(), null);
                return;
            }
            try {
                value = method.invoke(object);
            } catch (IllegalAccessException | InvocationTargetException e1) {
                map.put(field.getName(), null);
                return;
            }
        }
        if (value == null) {
            map.put(field.getName(), null);
        }
    }
}
