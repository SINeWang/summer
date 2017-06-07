package one.kii.summer.io.validator;

import one.kii.summer.io.annotations.MayHave;
import one.kii.summer.io.exception.Panic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by WangYanJiong on 02/06/2017.
 */
public class NotBadResponse {

    private static final String INTERNAL_CLASS = "this$";

    public static <T> List<T> of(List<T> objects) throws Panic {
        if (objects == null) {
            throw new NullPointerException();
        }
        if (objects.isEmpty()) {
            return Collections.emptyList();
        }
        for (Object object : objects) {
            checkFields(object, MayHave.class);
        }
        return objects;
    }

    public static <T> List<T> of(List<T> objects, Class<? extends Annotation> ignore) throws Panic {
        if (objects == null) {
            throw new NullPointerException();
        }
        if (objects.isEmpty()) {
            return Collections.emptyList();
        }
        for (Object object : objects) {
            checkFields(object, ignore);
        }
        return objects;
    }


    public static <T> T of(T object) throws Panic {
        if (object == null) {
            throw new NullPointerException();
        }
        checkFields(object, MayHave.class);
        return object;
    }

    public static <T> T of(T object, Class<? extends Annotation> ignore) throws Panic {
        if (object == null) {
            throw new NullPointerException();
        }
        checkFields(object, ignore);
        return object;
    }


    private static void checkFields(Object object, Class<? extends Annotation> ignore) throws Panic {
        Class klass = object.getClass();
        Field[] fields = klass.getDeclaredFields();
        List<String> badFields = new ArrayList<>();
        for (Field field : fields) {
            if (field.getName().startsWith(INTERNAL_CLASS)) {
                continue;
            }
            if (ignore != null) {
                Annotation annotation = field.getAnnotation(ignore);
                if (annotation != null) {
                    continue;
                }
            }
            Object value = null;
            try {
                field.setAccessible(true);
                value = field.get(object);
            } catch (IllegalAccessException skip) {
            }

            if (value == null) {
                String fieldName = field.getName();
                char first = Character.toUpperCase(fieldName.charAt(0));
                String getMethodName = "get" + first + fieldName.substring(1);
                Method method;
                try {
                    method = object.getClass().getMethod(getMethodName);
                } catch (NoSuchMethodException e) {
                    badFields.add(field.getName());
                    continue;
                }
                try {
                    value = method.invoke(object);
                } catch (IllegalAccessException | InvocationTargetException e1) {
                    return;
                }
                if (value == null) {
                    badFields.add(field.getName());
                }
            }

        }
        if (badFields.size() > 0) {
            throw new Panic(badFields.toArray(new String[0]));
        }
    }

}
