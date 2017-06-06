package one.kii.summer.beans.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by WangYanJiong on 06/06/2017.
 */
class FieldValueTools {

    protected static Object get(Field field, Object object) {
        try {
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException e) {
        }

        String fieldName = field.getName();
        char first = Character.toUpperCase(fieldName.charAt(0));
        String getMethodName = "get" + first + fieldName.substring(1);
        Method method;
        try {
            method = object.getClass().getMethod(getMethodName);
        } catch (NoSuchMethodException e) {
            return null;
        }
        try {
            return method.invoke(object);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return null;
        }
    }

}
