package one.kii.summer.beans.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
public class MagicCopy {

    public static <T> T from(Class<T> target, Object... sources) {
        T instance = null;
        try {
            instance = target.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        if (sources == null || sources.length == 0) {
            return instance;
        }
        BeanUtils.copyProperties(sources[0], instance);
        if (sources.length == 1) {
            return instance;
        }
        fillMissingFields(instance, sources);
        return instance;
    }

    private static <T> void fillMissingFields(T target, Object... sources) {
        Field[] targetFields = target.getClass().getDeclaredFields();
        for (Field targetField : targetFields) {
            Object value;
            try {
                targetField.setAccessible(true);
                value = targetField.get(target);
            } catch (IllegalAccessException e) {
                //ignore
                continue;
            }
            if (value != null) {
                continue;
            }
            for (int i = 1; i < sources.length; i++) {
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
                        value = method.invoke(sources[i]);
                    } catch (IllegalAccessException e) {
                        //ignore
                    } catch (InvocationTargetException e) {
                        //ignore
                    }
                    if (value != null) {
                        try {
                            targetField.set(target, value);
                        } catch (IllegalAccessException e) {
                            // ignore
                        }
                    }
                    break;
                } else {
                    if (sourceField.getType().equals(targetField.getType())) {
                        sourceField.setAccessible(true);
                        try {
                            value = sourceField.get(sources[i]);
                        } catch (IllegalAccessException e) {
                            //ignore
                        }
                        if (value != null) {
                            try {
                                targetField.set(target, value);
                            } catch (IllegalAccessException e) {
                                // ignore
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

}
