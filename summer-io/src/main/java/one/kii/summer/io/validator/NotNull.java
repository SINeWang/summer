package one.kii.summer.io.validator;

import one.kii.summer.io.exception.NotFound;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangYanJiong on 02/06/2017.
 */
public class NotNull {

    private static final String INTERNAL_CLASS = "this$";

    public static void of(Class klass) throws NotFound {
        Field[] fields = klass.getDeclaredFields();
        List<String> badFields = new ArrayList<>();
        for (Field field : fields) {
            if (field.getName().startsWith(INTERNAL_CLASS)) {
                continue;
            }
            badFields.add(field.getName());
        }
        if (badFields.size() > 0) {
            throw new NotFound(badFields.toArray(new String[0]));
        }
    }

    public static <T> T of(Class<T> klass, T object) throws NotFound {
        if (object == null) {
            of(klass);
        }
        return object;
    }

    public static <T> T of(Class<T> klass, Class<? extends Annotation> ignore, T object) throws NotFound {
        if (object == null) {
            of(klass, ignore);
        }
        return object;
    }


    public static void of(Class klass, Class<? extends Annotation> ignore) throws NotFound {
        Field[] fields = klass.getDeclaredFields();
        List<String> badFields = new ArrayList<>();
        for (Field field : fields) {
            if (field.getName().startsWith(INTERNAL_CLASS)) {
                continue;
            }
            Annotation annotation = field.getAnnotation(ignore);
            if (annotation == null) {
                badFields.add(field.getName());
            }
        }
        if (badFields.size() > 0) {
            throw new NotFound(badFields.toArray(new String[0]));
        }
    }

}
