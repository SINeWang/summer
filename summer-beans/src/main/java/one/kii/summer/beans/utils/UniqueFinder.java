package one.kii.summer.beans.utils;

import one.kii.summer.beans.annotations.Unique;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WangYanJiong on 06/06/2017.
 */
public class UniqueFinder {

    public static MultiValueMap<String, String> find(Object object) {
        Class klass = object.getClass();
        Field[] fields = klass.getDeclaredFields();

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(Unique.class);
            if (annotation != null) {
                Object value = FieldValueTools.get(field, object);
                if (value == null) {
                    map.set(field.getName(), null);
                } else {
                    map.set(field.getName(), String.valueOf(value));
                }
            }
        }
        return map;
    }

    public static String[] find(Class klass) {
        Field[] fields = klass.getDeclaredFields();

        List<String> conflicts = new ArrayList<>();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(Unique.class);
            if (annotation != null) {
                conflicts.add(field.getName());
            }
        }
        String[] keys = conflicts.toArray(new String[0]);
        Arrays.sort(keys);
        return keys;
    }
}
