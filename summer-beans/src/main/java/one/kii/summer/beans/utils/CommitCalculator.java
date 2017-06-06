package one.kii.summer.beans.utils;

import one.kii.summer.beans.annotations.Commit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WangYanJiong on 18/05/2017.
 */
public class CommitCalculator {

    public static String[] find(Object object) {
        Class klass = object.getClass();
        Field[] fields = klass.getDeclaredFields();

        List<String> keyFactors = new ArrayList<>();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(Commit.class);
            if (annotation != null) {
                keyFactors.add(field.getName());
            }
        }
        String[] keys = keyFactors.toArray(new String[0]);
        Arrays.sort(keys);
        return keys;
    }

    public static String[] find(Class klass) {
        Field[] fields = klass.getDeclaredFields();

        List<String> keyFactors = new ArrayList<>();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(Commit.class);
            if (annotation != null) {
                keyFactors.add(field.getName());
            }
        }
        String[] keys = keyFactors.toArray(new String[0]);
        Arrays.sort(keys);
        return keys;
    }
}
