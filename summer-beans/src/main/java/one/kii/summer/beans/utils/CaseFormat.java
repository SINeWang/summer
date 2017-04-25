package one.kii.summer.beans.utils;

import java.lang.reflect.Field;

/**
 * Created by WangYanJiong on 25/04/2017.
 */
public class CaseFormat {

    public static <T> T lowerCamel2LowerHyphen(T object) {

        T target = (T) DataTools.copy(object, object.getClass());
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = null;
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                continue;
            }
            if (value == null) {
                continue;
            }
            if (value instanceof String) {
                String str = (String) value;
                String lh = com.google.common.base.CaseFormat.LOWER_CAMEL.to(com.google.common.base.CaseFormat.LOWER_HYPHEN, str);
                try {
                    field.set(target, lh);
                } catch (IllegalAccessException e) {
                    continue;
                }
            }
        }
        return target;
    }
}
