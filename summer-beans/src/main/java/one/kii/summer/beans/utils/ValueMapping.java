package one.kii.summer.beans.utils;

import java.util.List;
import java.util.Map;

/**
 * Created by WangYanJiong on 27/05/2017.
 */
public class ValueMapping {

    public static <T> T from(Class<T> target, Object... sources) {
        return MultipleValueMapping.from(target, sources);
    }

    public static <T> T from(Class<T> klass, Map map) {
        return SingleValueMapping.from(klass, map);
    }

    public static <T> T from(Class<T> klass, Object src) {
        return SingleValueMapping.from(klass, src);
    }

    public static <T> List<T> from(Class<T> klass, List sources) {
        return SingleValueMapping.from(klass, sources);
    }
}