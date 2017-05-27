package one.kii.summer.beans.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
public class MultipleModelMapping {

    public static <T> T from(Class<T> target, Object... sources) {
        if (sources == null || sources.length == 0) {
            return null;
        }
        T instance;
        if (sources.length == 1) {
            return SingleModelMapping.from(target, sources[0]);
        } else {
            instance = SingleModelMapping.from(target, sources[0]);
            fillMissingFields(instance, sources);
        }
        return instance;
    }

    private static <T> void fillMissingFields(T target, Object... sources) {
        Field[] targetFields = target.getClass().getDeclaredFields();
        for (Field targetField : targetFields) {
            Object targetValue;
            try {
                targetField.setAccessible(true);
                targetValue = targetField.get(target);
            } catch (IllegalAccessException e) {
                continue;
            }
            if (targetValue != null) {
                continue;
            }
            for (int i = 1; i < sources.length; i++) {
                Object sourceValue = null;
                if (sources[i] instanceof Map) {
                    Map map = (Map) sources[i];
                    sourceValue = map.get(targetField.getName());
                    if (sourceValue != null) {
                        if (targetField.getType().equals(sourceValue.getClass())) {
                            setValue(target, targetField, sourceValue);
                        } else {
                            targetValue = SingleModelMapping.from(targetField.getType(), sourceValue);
                            setValue(target, targetField, targetValue);
                        }
                        break;
                    }
                } else {
                    Field sourceField = null;
                    boolean noSuchField = false;
                    try {
                        sourceField = sources[i].getClass().getDeclaredField(targetField.getName());
                    } catch (NoSuchFieldException e) {
                        noSuchField = true;
                    }
                    if (noSuchField) {
                        String fieldName = targetField.getName();
                        char first = Character.toUpperCase(fieldName.charAt(0));
                        String getMethodName = "get" + first + fieldName.substring(1);
                        Method method;
                        try {
                            method = sources[i].getClass().getMethod(getMethodName);
                        } catch (NoSuchMethodException e) {
                            continue;
                        }
                        try {
                            sourceValue = method.invoke(sources[i]);
                        } catch (IllegalAccessException | InvocationTargetException e) {
                            continue;
                        }
                        if (sourceValue != null) {
                            setValue(target, targetField, sourceValue);
                            break;
                        }
                    } else {
                        try {
                            sourceField.setAccessible(true);
                            sourceValue = sourceField.get(sources[i]);
                        } catch (IllegalAccessException e) {
                            continue;
                        }
                        if (sourceValue == null) {
                            continue;
                        }
                        if (sourceField.getType().equals(targetField.getType())) {
                            setValue(target, targetField, sourceValue);
                            break;
                        } else {
                            Object anotherValue = SingleModelMapping.from(targetField.getType(), sourceValue);
                            setValue(target, targetField, anotherValue);
                        }
                        break;
                    }
                }


            }
        }
    }

    private static <T> void setValue(T target, Field targetField, Object value) {
        if (value != null) {
            try {
                targetField.set(target, value);
            } catch (IllegalAccessException e) {
                // ignore
            }
        }
    }

}
