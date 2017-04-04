package one.kii.summer.beans.utils;

import org.springframework.beans.BeanUtils;

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
