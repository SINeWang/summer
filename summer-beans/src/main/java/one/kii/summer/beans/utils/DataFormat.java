package one.kii.summer.beans.utils;

import com.google.common.base.CaseFormat;

import java.lang.reflect.Field;

/**
 * Created by WangYanJiong on 25/04/2017.
 */
public class DataFormat {

    public static <T> T toLowerHyphen(T object) {

        T target = (T) BasicCopy.from(object.getClass(), object);
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value;
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
                String lh = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, str);
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
