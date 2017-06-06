package one.kii.summer.beans.utils;

import one.kii.summer.beans.annotations.Conflict;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by WangYanJiong on 06/06/2017.
 */
public class ConflictFinder {

    public static Map<String, Object> find(Object object) {
        Class klass = object.getClass();
        Field[] fields = klass.getDeclaredFields();

        Map<String, Object> map = new HashMap<>();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(Conflict.class);
            if (annotation != null) {
                Object value = FieldValueTools.get(field, object);
                map.put(field.getName(), value);
            }
        }
        return map;
    }

    public static String[] find(Class klass) {
        Field[] fields = klass.getDeclaredFields();

        List<String> conflicts = new ArrayList<>();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(Conflict.class);
            if (annotation != null) {
                conflicts.add(field.getName());
            }
        }
        String[] keys = conflicts.toArray(new String[0]);
        Arrays.sort(keys);
        return keys;
    }
}
