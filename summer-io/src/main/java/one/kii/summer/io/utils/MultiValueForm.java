package one.kii.summer.io.utils;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;


/**
 * Created by WangYanJiong on 29/04/2017.
 */
@SuppressWarnings("unchecked")
public class MultiValueForm {

    private final static MultiValueMap EMPTY = new LinkedMultiValueMap();

    public static MultiValueMap<String, String> from(Object object) {
        if (object == null) {
            return EMPTY;
        }
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();

        Class type = object.getClass();
        ReflectionUtils.doWithFields(type, field -> {
            field.setAccessible(true);
            if (field.isSynthetic()) {
                return;
            }
            Object value;
            try {
                value = field.get(object);
            } catch (IllegalAccessException e) {
                return;
            }
            if (value == null) {
                return;
            }
            if (Collection.class.isAssignableFrom(value.getClass())) {
                ((Collection<Object>) value).stream().filter(Objects::nonNull)
                        .forEachOrdered(v -> map.add(field.getName(), String.valueOf(v)));
            } else if (value.getClass().isArray()) {
                Arrays.stream((Object[]) value).filter(Objects::nonNull)
                        .forEachOrdered(v -> map.add(field.getName(), String.valueOf(v)));
            } else {
                map.set(field.getName(), String.valueOf(value));
            }
        });
        return map;
    }
}
