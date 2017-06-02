package one.kii.summer.io.utils;

import one.kii.summer.io.annotations.MustHave;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WangYanJiong on 02/06/2017.
 */
public class MustHaveTools {

    public static String[] find(Object object) {
        Class klass = object.getClass();
        Field[] fields = klass.getDeclaredFields();

        List<String> missing = new ArrayList<>();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(MustHave.class);
            if (annotation != null) {
                Object value = null;
                try {
                    value = field.get(object);
                } catch (IllegalAccessException e) {
                    //ignore
                }
                if (value == null) {
                    missing.add(field.getName());
                    continue;
                }
            }
        }
        String[] keys = missing.toArray(new String[0]);
        Arrays.sort(keys);
        return keys;
    }

}
