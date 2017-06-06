package one.kii.summer.beans.utils;

import one.kii.summer.beans.annotations.ConflictFactor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WangYanJiong on 06/06/2017.
 */
public class ConflictFactorTools {

    private static String IS = "=";

    public static String[] find(Object object) {
        Class klass = object.getClass();
        Field[] fields = klass.getDeclaredFields();

        List<String> conflicts = new ArrayList<>();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(ConflictFactor.class);
            if (annotation != null) {
                Object value = FieldValueTools.get(field, object);
                if (value != null) {
                    conflicts.add(field.getName() + IS + value);
                } else {
                    conflicts.add(field.getName());
                }
            }
        }
        String[] keys = conflicts.toArray(new String[0]);
        Arrays.sort(keys);
        return keys;
    }

    public static String[] find(Class klass) {
        Field[] fields = klass.getDeclaredFields();

        List<String> conflicts = new ArrayList<>();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(ConflictFactor.class);
            if (annotation != null) {
                conflicts.add(field.getName());
            }
        }
        String[] keys = conflicts.toArray(new String[0]);
        Arrays.sort(keys);
        return keys;
    }
}
