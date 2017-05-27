package one.kii.summer.beans.utils;

import java.util.List;
import java.util.Map;

/**
 * Created by WangYanJiong on 27/05/2017.
 */
public class ModelMapping {

    public static <T> T from(Class<T> target, Object... sources) {
        return MultipleModelMapping.from(target, sources);
    }

    public static <T> T from(Class<T> klass, Map map) {
        return SingleModelMapping.from(klass, map);
    }

    public static <T> T from(Class<T> klass, Object src) {
        return SingleModelMapping.from(klass, src);
    }

    public static <T> List<T> from(Class<T> klass, List sources) {
        return SingleModelMapping.from(klass, sources);
    }
}