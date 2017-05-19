package one.kii.summer.beans.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by WangYanJiong on 18/04/2017.
 */
public class ArraysAssert {

    private static Logger logger = LoggerFactory.getLogger(ArraysAssert.class);

    public static <T> boolean same(String[] array, List<T> anotherList, String anotherFieldName) {
        if (array == null || anotherList == null) {
            return false;
        }
        if (array.length == 0 && anotherList.size() == 0) {
            return true;
        }
        if (array.length != anotherList.size()) {
            return false;
        }
        List<String> anothers = new ArrayList<>();
        for (T another : anotherList) {
            if (another == null) {
                continue;
            }
            Field field = null;
            try {
                field = another.getClass().getDeclaredField(anotherFieldName);
                field.setAccessible(true);
            } catch (NoSuchFieldException e) {
                logger.debug("no-such-field-exception:{}", anotherFieldName, e);
                return false;
            }
            try {
                Object object = field.get(another);
                if (object != null) {
                    anothers.add(object.toString());
                }
            } catch (IllegalAccessException e) {
                logger.debug("illegal-access-exception:{}", anotherFieldName, e);
                return false;
            }
        }
        String[] anotherArray = anothers.toArray(new String[0]);

        if (array.length != anotherArray.length) {
            return false;
        }
        Arrays.sort(array);
        Arrays.sort(anotherArray);
        return Arrays.equals(array, anotherArray);
    }


}
