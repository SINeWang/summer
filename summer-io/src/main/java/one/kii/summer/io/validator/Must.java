package one.kii.summer.io.validator;

import one.kii.summer.io.annotations.MustHave;
import one.kii.summer.io.exception.BadRequest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangYanJiong on 20/04/2017.
 */
public class Must {


    public static void have(Object object, String... fieldsName) throws BadRequest {
        if (object == null) {
            throw new NullPointerException();
        }
        if (fieldsName == null) {
            throw new NullPointerException();
        }
        if (fieldsName.length == 0) {
            return;
        }
        List<String> badFields = new ArrayList<>();
        Class type = object.getClass();
        for (String fieldName : fieldsName) {
            if (fieldName == null) {
                continue;
            }
            Field field = null;
            try {
                field = type.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                badFields.add(fieldName);
            }
            if (field != null) {
                checkValueNotEmpty(object, badFields, field);
            }
        }
        if (badFields.size() > 0) {
            throw new BadRequest(badFields.toArray(new String[0]));
        }
    }


    public static void have(Object object) throws BadRequest {
        if (object == null) {
            throw new NullPointerException();
        }
        Class type = object.getClass();
        Field[] fields = type.getDeclaredFields();
        List<String> badFields = new ArrayList<>();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(MustHave.class);
            if (annotation == null) {
                continue;
            }
            checkValueNotEmpty(object, badFields, field);
        }
        if (badFields.size() > 0) {
            throw new BadRequest(badFields.toArray(new String[0]));
        }
    }

    public static void have(Class klass) throws BadRequest {
        Field[] fields = klass.getDeclaredFields();
        List<String> badFields = new ArrayList<>();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(MustHave.class);
            if (annotation != null) {
                badFields.add(field.getName());
            }
        }
        if (badFields.size() > 0) {
            throw new BadRequest(badFields.toArray(new String[0]));
        }
    }


    private static void checkValueNotEmpty(Object object, List<String> badFields, Field field) {
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
                badFields.add(field.getName());
                return;
            }
            try {
                value = method.invoke(object);
            } catch (IllegalAccessException | InvocationTargetException e1) {
                badFields.add(field.getName());
                return;
            }
        }
        if (value == null) {
            badFields.add(field.getName());
        }
        if (value instanceof String) {
            String str = (String) value;
            if (str.trim().length() == 0) {
                badFields.add(field.getName());
            }
        }
    }
}
