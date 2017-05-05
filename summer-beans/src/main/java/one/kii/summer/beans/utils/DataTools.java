package one.kii.summer.beans.utils;

import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
public class DataTools {

    public static <T> T copy(Object src, Class<T> klass) {
        T instance = null;
        try {
            instance = klass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        BeanUtils.copyProperties(src, instance);

//        Field[] fields = klass.getDeclaredFields();
//        for (Field targetField : fields) {
//            if (!targetField.getType().isEnum()) {
//                continue;
//            }
//            Object targetValue = null;
//            try {
//                targetValue = targetField.get(instance);
//            } catch (IllegalAccessException e) {
//                continue;
//            }
//            if (targetValue == null) {
//                Field srcField;
//                try {
//                    srcField = src.getClass().getDeclaredField(targetField.getName());
//                } catch (NoSuchFieldException e) {
//                    continue;
//                }
//                Object srcValue;
//                try {
//                    srcField.setAccessible(true);
//                    srcValue = srcField.get(src);
//                } catch (IllegalAccessException e) {
//                    continue;
//                }
//                if (srcValue != null) {
//                    targetField.setAccessible(true);
//                    targetField.set(instance, Enum.valueOf((Enum)targetField.getType(), String.valueOf(srcValue)));
//                }
//            }
//
//        }

        return instance;
    }

    public static <T> List<T> copy(List srcs, Class<T> klass) {
        List<T> list = new ArrayList<>();
        for (Object src : srcs) {
            if (src != null) {
                T target = copy(src, klass);
                list.add(target);
            }
        }
        return list;
    }
}
