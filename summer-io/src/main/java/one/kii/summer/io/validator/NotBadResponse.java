package one.kii.summer.io.validator;

import one.kii.summer.io.exception.Panic;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangYanJiong on 02/06/2017.
 */
public class NotBadResponse {

    private static final String INTERNAL_CLASS = "this$";

    public static void of(Class klass) throws Panic {
        Field[] fields = klass.getDeclaredFields();
        List<String> badFields = new ArrayList<>();
        for (Field field : fields) {
            if (field.getName().startsWith(INTERNAL_CLASS)) {
                continue;
            }
            badFields.add(field.getName());
        }
        if (badFields.size() > 0) {
            throw new Panic(badFields.toArray(new String[0]));
        }
    }

    public static <T> T of(Class<T> klass, T object) throws Panic {
        if (object == null) {
            of(klass);
        } else {
            checkFields(object, null);
        }
        return object;
    }

    public static <T> T of(Class<T> klass, Class<? extends Annotation> ignore, T object) throws Panic {
        if (object == null) {
            of(klass, ignore);
        } else {
            checkFields(object, ignore);
        }
        return object;
    }

    public static void of(Class klass, Class<? extends Annotation> ignore) throws Panic {
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
            throw new Panic(badFields.toArray(new String[0]));
        }
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
