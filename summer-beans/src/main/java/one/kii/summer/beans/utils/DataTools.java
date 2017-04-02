package one.kii.summer.beans.utils;

import org.springframework.beans.BeanUtils;

/**
 * Created by WangYanJiong on 02/04/2017.
 */
public class DataTools {

    public static <T> T clone(Object src, Class<T> klass) {
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
}
