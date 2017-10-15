package one.kii.summer.io.utils;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;


/**
 * Created by WangYanJiong on 29/04/2017.
 */
public class MultiValueForm {

    private final static MultiValueMap EMPTY = new LinkedMultiValueMap();

    public static MultiValueMap<String, String> from(Object object) {
        if (object == null) {
            return EMPTY;
        }
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

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
                Arrays.stream((Object[]) value).filter(Objects::nonNull)
                        .forEachOrdered(v -> map.add(field.getName(), String.valueOf(v)));
            }
        }
        return map;
    }
}
