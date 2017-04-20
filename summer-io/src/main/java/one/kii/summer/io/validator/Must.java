package one.kii.summer.io.validator;

import one.kii.summer.io.annotations.MustHave;
import one.kii.summer.io.exception.BadRequest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
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


    private static void checkValueNotEmpty(Object object, List<String> badFields, Field field) {
        field.setAccessible(true);
        Object value = null;
        try {
            value = field.get(object);
        } catch (IllegalAccessException e) {
            badFields.add(field.getName());
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
