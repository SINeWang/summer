package one.kii.summer.beans.utils;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Array;
import java.lang.reflect.Field;


/**
 * Created by WangYanJiong on 29/04/2017.
 */
public class MultiValueForm {

    private final static MultiValueMap EMPTY = new LinkedMultiValueMap();

    public static MultiValueMap from(Object object) {
        if (object == null) {
            return EMPTY;
        }
        MultiValueMap map = new LinkedMultiValueMap();

        Class type = object.getClass();
        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value;
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                continue;
            }
            Class vType = value.getClass().getComponentType();
            if (vType == null) {
                map.set(field.getName(), String.valueOf(value));
            } else {
                int length = Array.getLength(value);
                for (int i = 0; i < length; i++) {
                    Object arrayElement = Array.get(value, i);
                    if (arrayElement != null) {
                        map.add(field.getName(), String.valueOf(arrayElement));
                    }
                }
            }
        }
        return map;
    }
}
